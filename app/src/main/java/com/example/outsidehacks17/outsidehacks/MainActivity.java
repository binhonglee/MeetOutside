package com.example.outsidehacks17.outsidehacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent loginScreen = new Intent(MainActivity.this, LoginScreen.class);
        loginScreen.putExtra("UserFactory", new UserFactory());
        Intent artistScreen = new Intent(MainActivity.this, ArtistsActivity.class);
       // startActivity(artistScreen);
        startActivity(loginScreen);
        finish();
    }
}
