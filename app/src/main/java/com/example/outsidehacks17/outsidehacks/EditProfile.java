package com.example.outsidehacks17.outsidehacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {
    private UserFactory users;
    private int currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        users = this.getIntent().getParcelableExtra("UserFactory");
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        currentUser = this.getIntent().getIntExtra("id", 0);
        etEmail.setText(users.get(currentUser).getEmail());


        //EditText etPassword = (EditText) findViewById(R.id.etPassword);
    }
}
