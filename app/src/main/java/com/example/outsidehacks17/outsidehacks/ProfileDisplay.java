package com.example.outsidehacks17.outsidehacks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileDisplay extends AppCompatActivity implements LoadImageTask.Listener {

    private TextView name;
    private TextView email;
    private TextView age;
    private TextView mTextMessage;
    private ImageView mImageView;
    private UserFactory users;
    private int currentUser;

    public static final String IMAGE_URL = "http://www.socialmediasearch.co.uk/wp-content/uploads/2014/12/s5.jpg";

    @Override
    public void onImageLoaded(Bitmap bitmap) {

        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error Loading Image !", Toast.LENGTH_SHORT).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        users = this.getIntent().getParcelableExtra("UserFactory");
        currentUser = this.getIntent().getParcelableExtra("id");
        setContentView(R.layout.activity_profile_display);

        name = (TextView) findViewById(R.id.name);
        name.setText("Name: " + users.get(currentUser).getName());
        age = (TextView) findViewById(R.id.age);
        age.setText("Age: " + (2017 - users.get(currentUser).getBirthYear()));
        email = (TextView) findViewById(R.id.email);
        email.setText("Email: " + users.get(currentUser).getEmail());
        mImageView = (ImageView) findViewById(R.id.image);

        new LoadImageTask(this).execute(IMAGE_URL);
    }
}
