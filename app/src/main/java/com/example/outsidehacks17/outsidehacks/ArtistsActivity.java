package com.example.outsidehacks17.outsidehacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.view.MotionEvent;
import android.widget.Toast;


public class ArtistsActivity extends AppCompatActivity {

    private UserFactory users;
    private int currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        users = this.getIntent().getParcelableExtra("UserFactory");
        currentUser = this.getIntent().getIntExtra("id", 0);

        final AutoCompleteTextView textView1 = (AutoCompleteTextView) findViewById(R.id.artist1);
        final AutoCompleteTextView textview2 = (AutoCompleteTextView) findViewById(R.id.artist2);
        final AutoCompleteTextView textView3 = (AutoCompleteTextView) findViewById(R.id.artist3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ARTISTNAMES);
        textView1.setAdapter(adapter);
        textview2.setAdapter(adapter);
        textView3.setAdapter(adapter);

        textView1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                textView1.showDropDown();
                return false;
            }
        });

        textview2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                textview2.showDropDown();
                return false;
            }
        });

        textView3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                textView3.showDropDown();
                return false;
            }
        });

      //  textView1.showDropDown();
      //  textview2.showDropDown();
       // textView3.showDropDown();

    }



    private static final String[] ARTISTNAMES = new String[] {
            "Metallica", "The Who", "Gorillaz", "Lorde", "A Tribe Called Quest", "alt-J", "Queens Of The Stone Age", "Above & Beyond", "Fleet Foxes", "Empire Of The Sun", "The Avett Brothers",
            "Belle and Sebastian", "Solange", "Future Islands", "ScHoolboy Q", "Rebelution", "Young The Giant", "Little Dragon", "Vance Joy", "Kaytranada", "Bleachers", "Tove Lo", "Action Bronson",
            "Sleigh Bells", "Louis The Child", "Royal Blood", "Shovels & Rope", "Thundercat", "Dawes", "Warpaint", "Dr. Octagon", "Rag’n’Bone Man",
            "Bomba Estéreo", "Real Estate", "James Vincent McMorrow", "RAC", "Temples", "K.Flay", "Hamilton Leithauser", "Maggie Rogers", "Sofi Tukker", "Foxygen", "How To Dress Well", "SOHN", "Electric Guest",
            "Goldroom", "Hundred Waters", "Noname", "Khruangbin", "Kamaiyah", "S U R V I V E", "Kali Uchis", "San Fermin", "Joseph", "The Japanese House", "Mon Laferte", "Lee Fields & The Expressions", "The Lemon Twigs",
            "Grace Mitchell", "Porches", "Jacob Banks", "MUNA", "Mondo Cozmo", "John Moreland", "Frenship", "Sam Dew", "Oliver Tree", "Lawrence", "The She’s", "Berklee College of Music"

    };

    public void buttonContinue(View theView) {
        AutoCompleteTextView textView1 = (AutoCompleteTextView) findViewById(R.id.artist1);
        AutoCompleteTextView textview2 = (AutoCompleteTextView) findViewById(R.id.artist2);
        AutoCompleteTextView textView3 = (AutoCompleteTextView) findViewById(R.id.artist3);

        users.get(currentUser).setFavArtists1(textView1.getText().toString());
        users.get(currentUser).setFavArtists2(textview2.getText().toString());
        users.get(currentUser).setFavArtists3(textView3.getText().toString());

        String artist1 = textView1.getText().toString().trim();
        String artist2 = textview2.getText().toString().trim();
        String artist3 = textView3.getText().toString().trim();

        if (artist1.isEmpty() || artist1.equals("") ||
                artist2.isEmpty() || artist2.equals("") ||
                artist3.isEmpty() || artist2.equals("")) {

            Context context = getApplicationContext();
            CharSequence text = "Please fill in empty field(s)";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Intent i = new Intent(this, ChooseEventsActivity.class);
        i.putExtra("UserFactory", users);
        i.putExtra("id", currentUser);
        startActivity(i);
        finish();
    }

}
