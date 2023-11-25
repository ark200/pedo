public class TrackGPS {
    double altitude;
    private final Context ctxt;
    boolean checkGPS = false;
    Location mylocation; // variable to store current location
    protected LocationManager locationManager;
    double latitude;
    double longitude;
    private static final long MINDELAY = 1000 * 60;
    private static final long MINDISTANCE = 10;
    public TrackGPS(Context ctxt) {
        this.ctxt = ctxt;
        getLocation();
    }
    private Location getLocation() {
        try {
            locationManager = (LocationManager) ctxt.getSystemService(LOCATION_SERVICE);
            checkGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (checkGPS) {
                try {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINDELAY, MINDISTANCE, this);
                    if (locationManager != null) {
                        mylocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (mylocation != null) {
                            latitude = mylocation.getLatitude();
                            longitude = mylocation.getLongitude();
                        }
                    }
                } catch (SecurityException e) {
                    Toast.makeText(ctxt, "No permission to access GPS", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ctxt, "No service provider available", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mylocation;
    }
    @Override
    public void onLocationChanged(Location location) {
        altitude = location.getAltitude();
    }
    public double getAltitude() {
        if (mylocation != null) {
            return mylocation.getAltitude();
        }
        return altitude;
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
    public double getLatitude() {
        if (mylocation != null) {
            return mylocation.getLatitude();
        }
        return latitude;
    }
    public double getLongitude() {
        if (mylocation != null) {
            return mylocation.getLongitude();
        }
        return longitude;
    }
    public boolean canGetLocation() {
        return this.checkGPS;
    }
    public void showAlert(Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("GPS disabled");
        dialog.setMessage("Do you want to turn on GPS?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void stopGPS() {
        if (locationManager != null) {
            try {
                locationManager.removeUpdates(TrackGPS.this);
            } catch (SecurityException e) {
                Toast.makeText(ctxt, "No permission to access GPS", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

}
