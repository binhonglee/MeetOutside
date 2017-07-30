package com.example.outsidehacks17.outsidehacks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.outsidehacks17.outsidehacks.R.id.bRegister;
import static com.example.outsidehacks17.outsidehacks.R.id.etName;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_screen);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            EditText etName = (EditText) findViewById(R.id.etName);
            EditText etEmail = (EditText) findViewById(R.id.etEmail);
            EditText etAge = (EditText) findViewById(R.id.etAge);
            EditText etPassword = (EditText) findViewById(R.id.etPassword);

            String et_Name = etName.getText().toString().trim();
            String et_Email = etEmail.getText().toString().trim();
            String et_Age = etAge.getText().toString().trim();
            String et_Password = etPassword.getText().toString().trim();

            if (et_Name.isEmpty() || et_Name.equals("") ||
                    et_Email.isEmpty() || et_Email.equals("") ||
                    et_Age.isEmpty() || et_Email.equals("") ||
                    et_Password.isEmpty() || et_Password.equals("")) {

                Context context = getApplicationContext();
                CharSequence text = "Please fill in empty field(s)";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }

            UserFactory myUser = new UserFactory();
            User newUser = myUser.addUser(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), etEmail.getText().toString(), etPassword.getText().toString());
            Intent registerIntent = new Intent(RegisterScreen.this, ArtistsActivity.class);
            registerIntent.putExtra("UserFactory", myUser);
            registerIntent.putExtra("id", newUser.getId());
            RegisterScreen.this.startActivity(registerIntent);
            finish();
            }
        });
    }
}


