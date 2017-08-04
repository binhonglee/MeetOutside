package meetoutside.meetoutsidev2

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInActivity : AppCompatActivity() {

    private var currentUser: FirebaseUser? = null
    internal val TAG = "SignIn"

    private var mAuth: FirebaseAuth? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private var usernameTxtField: EditText? = null
    private var passwordTxtField: EditText? = null
    private var statusTxtView: TextView? = null
    private var emailPostLoginTxtView: TextView? = null
    private var signInBtn: Button? = null
    private var registerBtn: Button? = null
    private var signOutBtn: Button? = null
    private var editProfileBtn: Button? = null

    private var preLogin: LinearLayout? = null
    private var postLogin: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        preLogin = findViewById(R.id.login_pre_login) as LinearLayout
        postLogin = findViewById(R.id.login_post_login) as LinearLayout

        usernameTxtField = findViewById(R.id.login_email_pre_login) as EditText
        passwordTxtField = findViewById(R.id.login_password_pre_login) as EditText
        signInBtn = findViewById(R.id.login_signin_pre_login) as Button
        statusTxtView = findViewById(R.id.login_status_pre_login) as TextView
        signInBtn = findViewById(R.id.login_signin_pre_login) as Button
        registerBtn = findViewById(R.id.login_signup_pre_login) as Button

        emailPostLoginTxtView = findViewById(R.id.login_email_post_login) as TextView
        signOutBtn = findViewById(R.id.login_signout_post_login) as Button
        editProfileBtn = findViewById(R.id.login_edit_profile) as Button

        signInBtn!!.setOnClickListener { signIn(usernameTxtField!!.text.toString(), passwordTxtField!!.text.toString()) }
        registerBtn!!.setOnClickListener { createAccount(usernameTxtField!!.text.toString(), passwordTxtField!!.text.toString()) }

        signOutBtn!!.setOnClickListener { signOut() }

        editProfileBtn!!.setOnClickListener {
            startActivity(Intent(this@SignInActivity, EditUser::class.java))
        }

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + currentUser!!.uid)
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out")
            }
            // ...
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:" + email)
        if (!validateForm()) {
            return
        }

        showProgressDialog()

        // [START create_user_with_email]
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = mAuth!!.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(this@SignInActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                updateUI(null)
            }

            // [START_EXCLUDE]
            hideProgressDialog()
            // [END_EXCLUDE]
        }
        // [END create_user_with_email]
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:" + email)
        if (!validateForm()) {
            return
        }

        showProgressDialog()

        // [START sign_in_with_email]
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                val user = mAuth!!.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(this@SignInActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                updateUI(null)
            }

            // [START_EXCLUDE]
            if (!task.isSuccessful) {
                statusTxtView!!.setText(R.string.auth_failed)
            }
            hideProgressDialog()
            // [END_EXCLUDE]
        }
        // [END sign_in_with_email]
    }

    private fun signOut() {
        mAuth!!.signOut()
        updateUI(null)
    }

    public override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }

        hideProgressDialog()
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = usernameTxtField!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            usernameTxtField!!.error = "Required."
            valid = false
        } else {
            usernameTxtField!!.error = null
        }

        val password = passwordTxtField!!.text.toString()
        if (TextUtils.isEmpty(password)) {
            passwordTxtField!!.error = "Required."
            valid = false
        } else {
            passwordTxtField!!.error = null
        }

        return valid
    }

    var mProgressDialog: ProgressDialog? = null

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setMessage(getString(R.string.loading))
            mProgressDialog!!.isIndeterminate = true
        }

        mProgressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()
        if (user != null) {
            preLogin!!.visibility = View.GONE
            postLogin!!.visibility = View.VISIBLE

            emailPostLoginTxtView!!.text = getString(R.string.emailpassword_status_fmt, user.email)

            //findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
        } else {
            preLogin!!.visibility = View.VISIBLE
            postLogin!!.visibility = View.GONE

            usernameTxtField!!.setText("")
            passwordTxtField!!.setText("")
            statusTxtView!!.setText(R.string.signed_out)
        }
    }
}
