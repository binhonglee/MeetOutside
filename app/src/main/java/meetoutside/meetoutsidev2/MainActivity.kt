package meetoutside.meetoutsidev2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("message")

        myRef.setValue("Hello, World!")
        startActivity(Intent(this@MainActivity, SignInActivity::class.java))
        finish()
    }
}
