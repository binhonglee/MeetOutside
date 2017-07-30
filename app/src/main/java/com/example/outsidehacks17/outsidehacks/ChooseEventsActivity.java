package com.example.outsidehacks17.outsidehacks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class ChooseEventsActivity extends AppCompatActivity {
    ArrayList<String> selection = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_events);
    }

    public void selectItem (View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.aids_memorial_grove:
                if (checked) {
                    selection.add("Aids Memorial Grove");
                } else {
                    selection.remove("Aids Memorial Grove");
                }
                break;
            case R.id.botanical_garden:
                if (checked) {
                    selection.add("Botanical Garden");
                } else {
                    selection.remove("Botanical Garden");
                }
                break;
            case R.id.academy_of_sciences:
                if (checked) {
                    selection.add("Academy of Sciences");
                } else {
                    selection.remove("Academy of Sciences");
                }
                break;
            case R.id.conservatory_of_flowers:
                if (checked) {
                    selection.add("Conservatory of Flowers");
                } else {
                    selection.remove("Conservatory of Flowers");
                }
                break;
            case R.id.de_young_museum:
                if (checked) {
                    selection.add("De Young Museum");
                } else {
                    selection.remove("De Young Museum");
                }
                break;
            case R.id.japanese_tea_garden:
                if (checked) {
                    selection.add("Japanese Tea Garden");
                } else {
                    selection.remove("Japanese Tea Garden");
                }
                break;
            case R.id.eco_lands:
                if (checked) {
                    selection.add("Eco Lands");
                } else {
                    selection.remove("Eco Lands");
                }
                break;
            case R.id.art_and_paintings:
                if (checked) {
                    selection.add("Art and paintings");
                } else {
                    selection.remove("Art and paintings");
                }
                break;
        }
    }

}
