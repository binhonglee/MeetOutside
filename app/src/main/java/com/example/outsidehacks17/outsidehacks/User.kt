package com.example.outsidehacks17.outsidehacks

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class User : Parcelable {
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
        parcel.writeList(labelled)
    }

    override fun describeContents(): Int {
        return 0
    }

    var id: Int = 0
    var name: String = ""
    var birthYear: Int = 0
    var email: String = ""
    private var password: String = ""
    var favArtists1: String = ""
    var favArtists2: String = ""
    var favArtists3: String = ""
    var image: String = ""
    var toDo = ArrayList<String>()
    var events = BooleanArray(8)
    var matched = ArrayList<Int>()
    var labelled = ArrayList<Int>()

    constructor(id: Int, name: String, birthYear: Int, email: String, password: String) {
        this.id = id
        this.name = name
        this.birthYear = birthYear
        this.email = email
        this.password = password
        labelled.add(id)
    }

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
        parcel.readList(labelled, ArrayList<Int>().javaClass.classLoader)
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

    fun compatibility(users: UserFactory): User {
        val comparables = HashMap<Int, Int>()

        for (i in 0..users.size() - 1) {
            val user = users.location(i)

            val existed = labelled.any { it == user.id }

            if (existed) {
                break
            }

            var toCompare = (0..events.size - 1).count { user.events[it] && user.events[it] == events[it] }

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

        comparables.toList().sortedBy { (_, value) -> value }.toMap()

        if (comparables.isEmpty()) {
            throw NullPointerException();
        }

        val next: Int = comparables.iterator().next().key
        labelled.add(next)
        return(users.get(next))
    }
}