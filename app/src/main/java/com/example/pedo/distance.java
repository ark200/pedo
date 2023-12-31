package com.example.pedo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class distance extends AppCompatActivity {
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private TextView distanceTextView, bravo;
    private Button startButton, stopButton;
    private Location lastLocation;
    private double totalDistance = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_distance);

        distanceTextView = findViewById(R.id.distance_display);
        bravo           =   findViewById(R.id.bravo);
        startButton = findViewById(R.id.start_distance);
        stopButton = findViewById(R.id.stop_distance);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        startButton.setClickable(true);
        stopButton.setClickable(false);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(distance.this,"You've started walking.",Toast.LENGTH_SHORT).show();
                startButton.setClickable(false);
                stopButton.setClickable(true);
                distanceTextView.setText("YOU HAVE WALKED: ");
                startLocationUpdates();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bravo.setText("CONGRATULATIONS");
                Toast.makeText(distance.this,"You've walked a distance of "+totalDistance,Toast.LENGTH_SHORT).show();
                startButton.setClickable(true);
                stopButton.setClickable(false);
                stopLocationUpdates();
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(distance.this, Options_page.class);
        startActivity(intent);
        finish();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    if (lastLocation != null) {
                        totalDistance += lastLocation.distanceTo(location);
                        updateUI(totalDistance);
                    }
                    lastLocation = location;
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    private void stopLocationUpdates() {
        locationManager.removeUpdates(locationListener);
    }

    private void updateUI(final double distance) {
        distanceTextView.setText("YOU HAVE WALKED " + distance + " meters");
//        bravo.setText("CONGRATULATIONS! ");

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                distanceTextView.setText("Distance: " + distance + " meters");
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopLocationUpdates();
    }
}