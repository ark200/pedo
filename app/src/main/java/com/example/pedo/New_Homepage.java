package com.example.pedo;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

public class New_Homepage extends AppCompatActivity {

    private TrackGPS gps;
    private TextView stepcpunt_view;
    private TextView distance_view;
    private TextView calories_view;

    private Button start;
    private Button stop;

    private static final int LOCATION_PERMISSION_REQUEST_CODE =1;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private Location previousLocation;
    private double totalDistance;
    private LocationRequest locationRequest;
    int locationInterval = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_homepage);

//        stepcpunt_view          =   findViewById(R.id.step_count_view_new);
        distance_view           =   findViewById(R.id.distance_view_new);
//        calories_view           =   findViewById(R.id.calories_view_new);
        start                   =   findViewById(R.id.start_button_new);
        stop                    =   findViewById(R.id.stop_button_new);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLocationPermission();
                startLocationUpdates();
                createLocationCallback();
            }
        });


    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                          @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                startLocationUpdates();
            }else{
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void createLocationCallback(){
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if(locationResult == null){
                    return;
                }
                Location currentLocation = locationResult.getLastLocation();
                if(previousLocation!=null){
                    float distance = previousLocation.distanceTo(currentLocation);
                    totalDistance += distance;
                    updateDistance();
                }
                previousLocation = currentLocation;
            }
        };
    }

    private void updateDistance(){
        distance_view.setText("TOTAL DISTANCE: "+totalDistance +"m");
    }
    private void checkLocationPermission(){
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION};

        boolean fineLocationGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED;
        boolean coarseLocationGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED;
        if(!fineLocationGranted || !coarseLocationGranted){
            ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
        }
        else{
            startLocationUpdates();
        }
    }

    private void startLocationUpdates(){


//        locationRequest = new LocationRequest.Builder(PRIORITY_HIGH_ACCURACY).setMinUpdateIntervalMillis(1000).setMaxUpdateDelayMillis(100).build();
//        if(locationRequest == null){
//            return;
//        }
//        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

//        locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 100)
//                .setIntervalMillis(1000).
//                setMaxUpdateDelayMillis(100).
//                build();
//        fusedLocationProviderClient.removeLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());


//        locationRequest = new LocationRequest.Builder(PRIORITY_HIGH_ACCURACY)
//                .setMinUpdateIntervalMillis(1000)
//                .setMaxUpdateDelayMillis(100)
//                .build();
//        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                updateDistance();
//                handler.postDelayed(this, 100);
//            }
//        }, 100);

//     locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, locationInterval)
//             .setWaitForAccurateLocation(false)
//             .setMinUpdateIntervalMillis(2000)
//             .setMaxUpdateDelayMillis(100)
//             .build();

    }


}