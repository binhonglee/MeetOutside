package com.example.outsidehacks17.outsidehacks

import java.util.ArrayList

class UserFactory {
    private var id: Int = 0
    private var users = ArrayList<User>()

    constructor() {
        id = 0
    }

    fun get(index: Int): User {
        return users[search(index, 0, users.size)];
    }

    fun addUser(name: String, birthYear: Int, email: String, password: String): User {
        users
                .asSequence()
                .filter { it.email == email }
                .forEach { throw NullPointerException() }

        var newUser: User = User(id, name, birthYear, email, password)
        users.add(newUser)
        id++
        return newUser
    }

    fun login(email: String, password: String): Boolean {
        users
                .asSequence()
                .filter { it.email == email }
                .forEach { return (it.checkPass(password)) }

        return false
    }

    fun update(oldUser: User, newUser: User): Boolean {
        if (oldUser.id != newUser.id) {
            return false
        }

        try {
            users[search(oldUser.id, 0, id)] = newUser
        } catch (e: Exception) {
            throw NullPointerException()
        }

        return false
    }

    private fun search(index: Int, start: Int, end: Int): Int {
        if (start == end && users[start].id == index)
        {
            return start
        }

        if (start >= end)
        {
            throw NullPointerException()
        }

        val currentId = (start + end) / 2

        if (users[currentId].id == index)
        {
            return currentId
        }
        else if (users[currentId].id > index)
        {
            return search(index, start, currentId - 1)
        }
        else
        {
            return search(index, currentId + 1, end)
        }
    }
}
