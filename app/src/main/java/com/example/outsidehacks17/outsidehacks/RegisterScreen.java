package com.example.outsidehacks17.outsidehacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bRegister = (Button) findViewById(R.id.bRegister);
    }
}
