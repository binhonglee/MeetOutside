package com.example.outsidehacks17.outsidehacks;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        //getWindow().getDecorView().setBackgroundColor(Color.BLACK);


        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserFactory myUser = new UserFactory();
                myUser.addUser(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), etEmail.getText().toString(), etPassword.getText().toString());
                Intent registerIntent = new Intent(RegisterScreen.this, LoginScreen.class);
                RegisterScreen.this.startActivity(registerIntent);
            }
        });
    }
}


