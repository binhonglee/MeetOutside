package com.example.outsidehacks17.outsidehacks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ProfileDisplay extends AppCompatActivity implements LoadImageTask.Listener {

    private Handler x;
    private TextView name;
    private TextView email;
    private TextView age;
    private TextView mTextMessage;
    private ImageView mImageView;
    private UserFactory users;
    private int currentUser;
    private Button editBtn;
    private ImageView yesBtn;
    private ImageView noBtn;
    private Button matchingBtn;
    boolean toReturn;

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
        getSupportActionBar().hide();
        users = this.getIntent().getParcelableExtra("UserFactory");
        currentUser = this.getIntent().getIntExtra("id", users.size() - 1);
        final int toShow = this.getIntent().getIntExtra("othersId", currentUser);

        setContentView(R.layout.activity_profile_display);

        name = (TextView) findViewById(R.id.name);
        name.setText("Name: " + users.get(toShow).getName());
        age = (TextView) findViewById(R.id.age);
        age.setText("Age: " + (2017 - users.get(toShow).getBirthYear()));
        email = (TextView) findViewById(R.id.email);
        email.setText("Email: " + users.get(toShow).getEmail());
        mImageView = (ImageView) findViewById(R.id.image);
        matchingBtn = (Button) findViewById(R.id.startMatching);
        yesBtn = (ImageView) findViewById(R.id.yesBtn);
        noBtn = (ImageView) findViewById(R.id.noBtn);
        editBtn = (Button) findViewById(R.id.editBtn);

        if (toShow == currentUser) {
            yesBtn.setVisibility(View.GONE);
            noBtn.setVisibility(View.GONE);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent registerIntent = new Intent(ProfileDisplay.this, EditProfile.class);
                    registerIntent.putExtra("UserFactory", users);
                    registerIntent.putExtra("id", currentUser);
                    startActivity(registerIntent);
                    finish();
                }
            });
            matchingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //HashMap<Integer, Integer> comparisons = users.get(currentUser).compatibility(users);
                    //Map<Integer, Integer> sortedMap = sortHashMapByValues(comparisons);
                    //Map.Entry<Integer, Integer> entry = sortedMap.entrySet().iterator().next();

                    /*
                    while (entry.getKey() == currentUser) {
                        sortedMap.remove(entry);
                        entry = sortedMap.entrySet().iterator().next();
                    }
                    */

                    triggered();
                }
            });

            new LoadImageTask(this).execute(IMAGE_URL);
        } else {
            //final Map<Integer, Integer> matches = this.getIntent().getParcelableExtra("Matches");
            mImageView.setVisibility(View.GONE);
            editBtn.setVisibility(View.GONE);
            matchingBtn.setVisibility(View.GONE);
            email.setVisibility(View.GONE);

            yesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toReturn = true;
                    Intent matched = new Intent(ProfileDisplay.this, MatchedProfiles.class);
                    matched.putExtra("Email", users.get(toShow).getEmail());
                    startActivity(matched);
                }
            });

            noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toReturn = false;
                    triggered();
                }
            });
        }
    }

    public Map<Integer, Integer> sortHashMapByValues(
            HashMap<Integer, Integer> passedMap) {
        List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Integer, Integer> sortedMap =
                new LinkedHashMap<>();

        for (Integer val : mapValues) {
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                Integer comp1 = passedMap.get(key);

                if (comp1.equals(val)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public void triggered() {
        Intent newMatchSuggestion = new Intent(ProfileDisplay.this, ProfileDisplay.class);

        try {
            newMatchSuggestion.putExtra("othersId", users.get(currentUser).compatibility(users).getId());
        } catch(Exception e) {
            e.printStackTrace();
        }
        newMatchSuggestion.putExtra("UserFactory", users);
        newMatchSuggestion.putExtra("id", currentUser);
        //newMatchSuggestion.putExtra("Matches", (Parcelable) sortedMap);
        startActivity(newMatchSuggestion);
    }

}
