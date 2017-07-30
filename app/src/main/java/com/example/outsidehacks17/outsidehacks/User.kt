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
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var favArtists1: String = ""
    var favArtists2: String = ""
    var favArtists3: String = ""
    var image: String = ""
    var toDo = ArrayList<String>()

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
}