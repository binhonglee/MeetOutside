package com.example.outsidehacks17.outsidehacks

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class User(var id: Int, var name: String, var birthYear: Int, var email: String, private var password: String) : Parcelable {
    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel!!.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(birthYear)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(favArtists1)
        parcel.writeString(favArtists2)
        parcel.writeString(favArtists3)
        parcel.writeString(image)
        parcel.writeStringList(toDo)
        parcel.writeList(matched)
    }

    override fun describeContents(): Int {
        return 0
    }

    var favArtists1: String = ""
    var favArtists2: String = ""
    var favArtists3: String = ""
    var image: String = ""
    var toDo = ArrayList<String>()
    var events = BooleanArray(8)
    var matched = ArrayList<Int>()

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()) {
        favArtists1 = parcel.readString()
        favArtists2 = parcel.readString()
        favArtists3 = parcel.readString()
        image = parcel.readString()
        parcel.readStringList(toDo)
        parcel.readList(matched, ArrayList<Int>().javaClass.classLoader)
    }


    fun checkPass(inputPass: String): Boolean {
        return (password == inputPass)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

    fun compatibility(users: UserFactory): Map<Int, Int> {
        var comparables = HashMap<Int, Int>()

        for (i in 0..users.size()) {
            var user = users.location(i)
            var toCompare = 0
            for (j in 0..events.size) {
                if (user.events[j] == true && user.events[j] == events[j]) {
                    toCompare++
                }
            }

            if (user.favArtists1 == favArtists1 || user.favArtists2 == favArtists1 || user.favArtists3 == favArtists1) {
                toCompare+=5
            }

            if (user.favArtists1 == favArtists2 || user.favArtists2 == favArtists2 || user.favArtists3 == favArtists2) {
                toCompare+=5
            }

            if (user.favArtists1 == favArtists3 || user.favArtists3 == favArtists2 || user.favArtists3 == favArtists3) {
                toCompare+=5
            }

            comparables.put(i, toCompare)
        }

        return comparables
    }
}