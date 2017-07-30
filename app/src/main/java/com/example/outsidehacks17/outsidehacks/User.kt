package com.example.outsidehacks17.outsidehacks

import java.util.ArrayList

class User {
    var name: String = ""
    var birthYear: Int = 0
    var email: String = ""
    private var password: String = ""
    var favArtists1: String = ""
    var favArtists2: String = ""
    var favArtists3: String = ""
    var id: Int = 0
    var image: String = ""
    var toDo = ArrayList<String>()


    constructor(id: Int, name: String, birthYear: Int, email: String, password: String) {
        this.id = id
        this.name = name
        this.birthYear = birthYear
        this.email = email
        this.password = password
    }

    fun checkPass(inputPass: String): Boolean {
        return (password == inputPass)
    }
}