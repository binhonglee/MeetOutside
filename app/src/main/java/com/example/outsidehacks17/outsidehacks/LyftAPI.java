package com.example.outsidehacks17.outsidehacks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lyft.lyftbutton.LyftButton;
import com.lyft.lyftbutton.RideParams;
import com.lyft.lyftbutton.RideTypeEnum;
import com.lyft.networking.ApiConfig;

public class LyftAPI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyft_api);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ApiConfig apiConfig = new ApiConfig.Builder()
                .setClientId("nC2KHzgfc4WM")
                .setClientToken("pbUdrdfc6OC7gVy6Q5/lAz2UsnMUEynB9pF8jSLgEbyuwTjCi5qBmNnt+wMVANSJVvZoe/rSQz+nrPU9ULIo6W55r1j0hOKKPd+ObicrkU7tjFYqTiNrSfI=")
                .build();
    }

}
