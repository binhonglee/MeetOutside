package com.example.outsidehacks17.outsidehacks

import java.util.ArrayList

class UserFactory {
    internal var id: Int = 0
    internal var users = ArrayList<User>()

    constructor() {
        id = 0
    }

    fun addUser(name: String, birthYear: Int, email: String, password: String): User {
        users
                .asSequence()
                .filter { it.email == email }
                .forEach { throw NullPointerException() }

        var newUser: User = User(name, birthYear, email, password)
        users.add(newUser)
        return newUser
    }

    fun login(email: String, password: String): Boolean {
        users
                .asSequence()
                .filter { it.email == email }
                .forEach { return (it.checkPass(password)) }

        return false
    }
}
