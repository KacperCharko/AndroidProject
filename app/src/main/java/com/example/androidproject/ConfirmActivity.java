package com.example.androidproject;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.database.Task;
import com.google.android.gms.maps.model.LatLng;

public class ConfirmActivity extends AppCompatActivity {

    private Task task;
    private Button btnNav;
    private Button btnCamera;
    private TextView text;
    private ImageView imageView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double longitude=0;
    private double latitude=0;
    private LatLng loc;
    public static final String KEY_EXTRA_ANSWER_SHOWN = "answerShown";


    private void setAnswerShownResult(boolean answerWasShown) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_activity);

        btnNav=findViewById(R.id.btnNav);
        text = findViewById(R.id.textViewX);
        imageView = findViewById(R.id.imageView2);
        btnCamera=findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                text.append("\n"+ location.getLatitude()+ " "+ location.getLongitude());

               longitude= location.getLongitude();
               latitude= location.getLatitude();
               loc= new LatLng(latitude,longitude);
                Log.d("x", String.valueOf(latitude));

                btnNav.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    intent.putExtra("loc", new LatLng(latitude,longitude));
                    startActivity(intent);

                    }
                });

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

        };
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET
            },10);

            return;
        }else {
            configureButton();
        }



    }
    @Override
    public void onBackPressed() {
        Log.d("back","pressed");
        LatLng data = loc;
        Intent resultIntent = new Intent();
        resultIntent.putExtra("loc", data);
        if(data!=null)
            setResult(-1, resultIntent);
        else
            setResult(0,resultIntent);
        finish();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    @SuppressLint("MissingPermission")
    private void configureButton(){
        locationManager.requestLocationUpdates("gps", 500, 0, locationListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap= (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}
