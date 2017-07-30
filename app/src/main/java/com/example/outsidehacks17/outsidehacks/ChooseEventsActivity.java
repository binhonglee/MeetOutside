package com.example.outsidehacks17.outsidehacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.R.attr.animation;

public class ChooseEventsActivity extends AppCompatActivity {
    boolean[] selection = new boolean[8];
    private UserFactory users;
    private int currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_events);

        users = this.getIntent().getParcelableExtra("UserFactory");
        currentUser = this.getIntent().getIntExtra("id", 0);
    }

    public void selectItem (View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.aids_memorial_grove:
                selection[0] = checked;
                break;
            case R.id.botanical_garden:
                selection[1] = checked;
                break;
            case R.id.academy_of_sciences:
                selection[2] = checked;
                break;
            case R.id.conservatory_of_flowers:
                selection[3] = checked;
                break;
            case R.id.de_young_museum:
                selection[4] = checked;
                break;
            case R.id.japanese_tea_garden:
                selection[5] = checked;
                break;
            case R.id.eco_lands:
                selection[6] = checked;
                break;
            case R.id.art_and_paintings:
                selection[7] = checked;
                break;
        }
    }

    public void doneButton (View v) {
        //TODO add events to profile
        Intent i = new Intent(this, ProfileDisplay.class);
        i.putExtra("UserFactory", users);
        i.putExtra("id", currentUser);
        startActivity(i);
        finish();
    }


}
