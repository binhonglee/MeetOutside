package com.example.outsidehacks17.outsidehacks;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    private UserFactory users = new UserFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Parcelable myUser = this.getIntent().getParcelableExtra("UserFactory");

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bLogin = (Button) findViewById(R.id.bLogin);

        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginScreen.this, RegisterScreen.class);
                registerIntent.putExtra("UserFactory", users);
                startActivity(registerIntent);
                finish();
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = users.login(etEmail.getText().toString(), etPassword.getText().toString());
                    Intent profile = new Intent(LoginScreen.this, ProfileDisplay.class);
                    profile.putExtra("UserFactory", users);
                    profile.putExtra("id", id);
                    startActivity(profile);
                    finish();
                } catch (Exception e) {
                    etEmail.setText("");
                    etPassword.setText("");
                }
            }
        });


    }
}
