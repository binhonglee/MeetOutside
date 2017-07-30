package com.example.outsidehacks17.outsidehacks;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lyft.lyftbutton.LyftButton;
import com.lyft.lyftbutton.RideParams;
import com.lyft.lyftbutton.RideTypeEnum;
import com.lyft.networking.ApiConfig;
import android.content.Intent;
import android.view.View;

import java.io.IOException;
import java.util.List;

public class LyftAPI extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyft_api);


        // map...
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // lyft....
        ApiConfig apiConfig = new ApiConfig.Builder()
                .setClientId("nC2KHzgfc4WM")
                .setClientToken("pbUdrdfc6OC7gVy6Q5/lAz2UsnMUEynB9pF8jSLgEbyuwTjCi5qBmNnt+wMVANSJVvZoe/rSQz+nrPU9ULIo6W55r1j0hOKKPd+ObicrkU7tjFYqTiNrSfI=")
                .build();

        LyftButton lyftButton = (LyftButton) findViewById(R.id.lyft_button);
        lyftButton.setApiConfig(apiConfig);

        RideParams.Builder rideParamsBuilder = new RideParams.Builder()
                .setPickupLocation(37.7766048, -122.3943629)
                .setDropoffLocation(37.759234, -122.4135125);
        rideParamsBuilder.setRideTypeEnum(RideTypeEnum.CLASSIC);

        lyftButton.setRideParams(rideParamsBuilder.build());
        lyftButton.load();



    }




    @Override
    public void onMapReady(GoogleMap map) {

      // map.setLatLngBoundsForCameraTarget(new LatLngBounds(37.7766048, -122.3943629));
        LatLng uponOpen = new LatLng(37.7766048, -122.3943629);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(uponOpen, 15));
        final GoogleMap gmap = map;

        gmap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng pt) {
                MarkerOptions marker = new MarkerOptions()
                        .position(new LatLng(pt.latitude, pt.longitude))
                        .title("Marker")
                        .draggable(true);
                gmap.addMarker(marker);
               Geocoder geocoder = new Geocoder(getApplicationContext());
                EditText addressText = findViewById(R.id.txtAddress);
                if(geocoder.isPresent()) {
                    List<Address> list = null;
                    try {
                        list = geocoder.getFromLocation(pt.latitude, pt.longitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (list != null && list.size() > 0) {
                        Address address = list.get(0);
                        addressText.setText(address.toString());
                    }
                    else
                    {
                        addressText.setText(Double.toString(pt.latitude) + ", " + Double.toString(pt.longitude));
                        RideParams.Builder rideParamsBuilder = new RideParams.Builder()
                                .setPickupLocation(pt.latitude, pt.longitude);
                        rideParamsBuilder.setDropoffLocation(37.766521, -122.494656);
                        rideParamsBuilder.setRideTypeEnum(RideTypeEnum.CLASSIC);
                        LyftButton lyftButton = (LyftButton) findViewById(R.id.lyft_button);
                        lyftButton.setRideParams(rideParamsBuilder.build());
                        lyftButton.load();
                    }
                }
            }
        }
            );

        gmap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                EditText addressText = findViewById(R.id.txtAddress);
                addressText.setText(Double.toString(marker.getPosition().latitude) + ", " + Double.toString(marker.getPosition().longitude));
                RideParams.Builder rideParamsBuilder = new RideParams.Builder()
                        .setPickupLocation(marker.getPosition().latitude, marker.getPosition().longitude);
                rideParamsBuilder.setDropoffLocation(37.766521, -122.494656);
                rideParamsBuilder.setRideTypeEnum(RideTypeEnum.CLASSIC);
                LyftButton lyftButton = (LyftButton) findViewById(R.id.lyft_button);
                lyftButton.setRideParams(rideParamsBuilder.build());
                lyftButton.load();
            }
        });



    }



    public void updateButton(View v) {
        EditText addressText = findViewById(R.id.txtAddress);
        RideParams.Builder rideParamsBuilder = new RideParams.Builder().setPickupAddress(addressText.getText().toString());
        rideParamsBuilder.setDropoffLocation(37.766521, -122.494656);
        rideParamsBuilder.setRideTypeEnum(RideTypeEnum.CLASSIC);
        LyftButton lyftButton = (LyftButton) findViewById(R.id.lyft_button);
        lyftButton.setRideParams(rideParamsBuilder.build());
        lyftButton.load();

    }
}
