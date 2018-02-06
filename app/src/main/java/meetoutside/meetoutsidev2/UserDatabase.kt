package meetoutside.meetoutsidev2

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener



class UserDatabase {
    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference

    constructor() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val post = dataSnapshot.getValue<User>(User::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        mDatabase.addValueEventListener(postListener)
    }

    fun addUser(newUser: User) {
        mDatabase.child("users").child(newUser.getId().toString()).setValue(newUser)
    }

    fun deleteUser(toDelete: User) {
        mDatabase.child("users").child(toDelete.getId().toString()).setValue(null)
    }

    fun getUser(): User {
        mDatabase.child("users").
    }
}