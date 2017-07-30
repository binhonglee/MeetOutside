package com.example.outsidehacks17.outsidehacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MatchedProfiles extends AppCompatActivity {

    private ImageView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_matched_profiles);
        final String address = this.getIntent().getStringExtra("Email");

        email = (ImageView) findViewById(R.id.contact);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /* Create the Intent */
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                /* Fill it with Data */
                emailIntent.setType("text/plain");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{address});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");

                /* Send it off to the Activity-Chooser */
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });
    }

    public void goToLyft(View view) {
        Intent lyftIntent = new Intent(this, LyftAPI.class);
        startActivity(lyftIntent);
        finish();
    }
}
