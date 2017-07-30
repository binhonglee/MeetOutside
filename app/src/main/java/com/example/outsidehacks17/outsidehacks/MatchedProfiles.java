package com.example.outsidehacks17.outsidehacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MatchedProfiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_matched_profiles);
    }

    public void goToLyft(View view) {
        Intent lyftIntent = new Intent(this, LyftAPI.class);
        startActivity(lyftIntent);
        finish();
    }
}
