package meetoutside.meetoutsidev2

class User {
    private var id: Int? = null
    private var username: String? = null
    private var password: String? = null
    private var email: String? = null
    private var birthYear: Int? = null
    private var image: String? = null

    constructor() {

    }

    constructor(id: Int, username: String, password: String, email: String) {
        this.id = id
        this.username = username
        this.password = password
        this.email = email
    }

    constructor(id: Int, username: String, password: String, email: String, birthYear: Int) : this(id, username, password, email) {
        this.birthYear = birthYear
    }

    fun updateImg(newURL: String): Boolean {
        try {
            image = newURL
            return true
        } catch (e : Exception) {
            return false
        }
    }

    fun checkPassword(given: String): Boolean {
        return given.trim() == password
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }
}