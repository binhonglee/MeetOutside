package com.example.outsidehacks17.outsidehacks

class User {
    var name: String = ""
    var birthYear: Int = 0
    var email: String = ""
    internal var password: String = ""

    constructor(name: String, birthYear: Int, email: String, password: String) {
        this.name = name
        this.birthYear = birthYear
        this.email = email
        this.password = password
    }

    fun checkPass(inputPass: String): Boolean {
        return (password == inputPass)
    }
}