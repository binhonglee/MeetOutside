package meetoutside.meetoutsidev2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class EditUser : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    internal val TAG = "SignIn"

    private var email: EditText? = null
    private var displayName: EditText? = null
    private var currentPassword: EditText? = null
    private var newPassword: EditText? = null
    private var newPasswordConfirm: EditText? = null
    private var saveBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        email = findViewById(R.id.edit_userEditTxt) as EditText
        displayName = findViewById(R.id.edit_nameEditTxt) as EditText
        currentPassword = findViewById(R.id.edit_currentPasswordEditTxt) as EditText
        newPassword = findViewById(R.id.edit_newPasswordEditTxt) as EditText
        newPasswordConfirm = findViewById(R.id.edit_confirmNewPasswordEditTxt) as EditText
        saveBtn = findViewById(R.id.edit_saveBtn) as Button

        saveBtn!!.setOnClickListener {
            save()
            finish()
        }

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.uid)
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out")
            }
        }

        val user: FirebaseUser = mAuth!!.currentUser!!

        email!!.setText(user.email)
        displayName!!.setText(user.displayName)
    }

    fun save() {
        val user: FirebaseUser = mAuth!!.currentUser!!

        val profileUpdate: UserProfileChangeRequest.Builder = UserProfileChangeRequest.Builder()
        profileUpdate.setDisplayName(displayName!!.text.toString())

        user.updateProfile(profileUpdate.build())
        user.updateEmail(email!!.text.toString())
    }
}