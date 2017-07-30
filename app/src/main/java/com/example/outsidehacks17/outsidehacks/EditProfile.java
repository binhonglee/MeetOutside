package com.example.outsidehacks17.outsidehacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;

public class EditProfile extends AppCompatActivity {
    private UserFactory users;
    private int currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_profile);
        users = this.getIntent().getParcelableExtra("UserFactory");

        currentUser = this.getIntent().getIntExtra("id", 0);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final User myUser = users.get(currentUser);
        etEmail.setText(myUser.getEmail());
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etArtist1 = (EditText) findViewById(R.id.etArtist1);
        final EditText etArtist2 = (EditText) findViewById(R.id.etArtist2);
        final EditText etArtist3 = (EditText) findViewById(R.id.etArtist3);
        etName.setText(myUser.getName());
        etArtist1.setText(myUser.getFavArtists1());
        etArtist2.setText(myUser.getFavArtists2());
        etArtist3.setText(myUser.getFavArtists3());

        Button doneButton = (Button) findViewById(R.id.doneBt);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myUser.setEmail(etEmail.getText().toString());
                myUser.setFavArtists1(etArtist1.getText().toString());
                myUser.setFavArtists2(etArtist2.getText().toString());
                myUser.setFavArtists3(etArtist3.getText().toString());
                myUser.setName(etName.getText().toString());

                Intent i = new Intent(EditProfile.this, ProfileDisplay.class);
                i.putExtra("UserFactory", users);
                i.putExtra("id", currentUser);
                startActivity(i);
                finish();
            }
        }
        );

    }


}
