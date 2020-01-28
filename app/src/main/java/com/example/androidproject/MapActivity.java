package com.example.androidproject;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.androidproject.database.Task;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

   // GoogleMap map;
    LatLng location=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
            if(bundle.get("loc")!= null)
            {
                location= (LatLng) bundle.get("loc");
            }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng directions = new LatLng(53.1170123,23.1463981);

        if(location!=null)
            directions=location;


        map.addMarker(new MarkerOptions().position(directions).title("polibuda"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(directions,18));
    }
}
