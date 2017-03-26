package com.apsit.toll.service;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apsit.toll.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.data.Geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<Status>, LocationListener {

    protected static final String TAG = "MapsActivity";

    public GoogleMap mMap;
    private Button button;
    private TextView textView;
    private SeekBar seekBar;
    protected GoogleApiClient mGoogleApiClient;
    protected ArrayList<Geofence> mGeofenceList;
    private boolean mGeofencesAdded;
    private PendingIntent mGeofencePendingIntent;
    private SharedPreferences mSharedPreferences;
    private double lat;
    private double lon;
    private Circle circle;
    private CircleOptions circleOptions;
    private int radius;
    private int newRad;
    private MarkerOptions markerOptions;
    private Marker marker;
    private ArrayList<LatLng> points;
    private Polyline polyline;
    private PolylineOptions options;
    private final static int MY_REQUEST = 6;
    private boolean permSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        final MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text_view);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        points = new ArrayList<>();


        mGeofenceList = new ArrayList<Geofence>();
        mGeofencePendingIntent = null;
        mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME,
                MODE_PRIVATE);
        mGeofencesAdded = mSharedPreferences.getBoolean(Constants.GEOFENCES_ADDED_KEY, false);

        lat = getDouble(mSharedPreferences, "LAT", 34.412612);
        lon = getDouble(mSharedPreferences, "LON", -119.848411);
        radius = mSharedPreferences.getInt("radius", 500);
        newRad = mSharedPreferences.getInt("newRad", 500);

        if (savedInstanceState != null) {
            radius = savedInstanceState.getInt("Radius");
            lat = savedInstanceState.getDouble("Latitude");
            lon = savedInstanceState.getDouble("Longitude");
            newRad = savedInstanceState.getInt("newRad");
            points = savedInstanceState.getParcelableArrayList("LocList");
            permSet = savedInstanceState.getBoolean("Perm");
        }

        textView.setText(String.format("LAT: %.6f", lat) + String.format(", LON: %.6f", lon) + ", RAD: " + newRad + "m");
        seekBar.setProgress(newRad);

        if (mGeofencesAdded) {
            seekBar.setEnabled(false);
            button.setText("EDIT GEOFENCE");
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_REQUEST);
        }

        else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String provider = locationManager.getBestProvider(new Criteria(), true);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MapsActivity.this);
            buildGoogleApiClient();
            mGoogleApiClient.connect();
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (circle != null) {
                    circle.remove();
                    circleOptions.center(new LatLng(lat, lon)).radius(radius + progress - 500).strokeColor(Color.GREEN).strokeWidth(8).fillColor(Color.TRANSPARENT);
                    circle = mMap.addCircle(circleOptions);
                    newRad = radius + progress - 500;
                    textView.setText(String.format("LAT: %.6f", lat) + String.format(", LON: %.6f", lon) + ", RAD: " + (newRad) + "m");
                }
                else
                    return;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (circle != null) {
                    circle.remove();
                    circleOptions.center(new LatLng(lat, lon)).strokeColor(Color.BLUE).strokeWidth(8).fillColor(0x300000ff);
                    circle = mMap.addCircle(new CircleOptions().center(new LatLng(lat, lon)).radius(newRad).strokeColor(Color.BLUE).strokeWidth(8).fillColor(0x300000ff));
                    textView.setText(String.format("LAT: %.6f", lat) + String.format(", LON: %.6f", lon) + ", RAD: " + newRad + "m");
                }
                else
                    return;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mGeofencesAdded) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "Allow location access to set/edit Geofence", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    button.setText("EDIT GEOFENCE");
                    populateGeofenceList();

                    LocationServices.GeofencingApi.addGeofences(mGoogleApiClient, getGeofencingRequest(), getGeofencePendingIntent()).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()) {
                                // Update state and save in shared preferences.
                                mGeofencesAdded = !mGeofencesAdded;
                                SharedPreferences.Editor editor = mSharedPreferences.edit();
                                editor.putBoolean(Constants.GEOFENCES_ADDED_KEY, mGeofencesAdded);
                                editor.putInt("radius", radius);
                                editor.putInt("newRad", newRad);
                                Log.d("lat", "" + lat);
                                Log.d("lon", "" + lon);
                                putDouble(editor, "LAT", lat);
                                putDouble(editor, "LON", lon);
                                editor.apply();

                                Toast.makeText(getApplicationContext(), getString(mGeofencesAdded ? R.string.geofences_added : R.string.geofences_removed), Toast.LENGTH_SHORT).show();
                            } else {
                                // Get the status code for the error and log it using a user-friendly message.
                                String errorMessage = GeofenceErrorMessages.getErrorString(getApplicationContext(),
                                        status.getStatusCode());
                                Log.e(TAG, errorMessage);
                            }

                        }
                    });

                    seekBar.setEnabled(false);
                    if (circle != null) {
                        circle.remove();
                        marker.remove();
                        circleOptions.center(new LatLng(lat, lon)).strokeColor(Color.GREEN).strokeWidth(8).fillColor(0x300000ff);
                        circle = mMap.addCircle(circleOptions);
                        marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Current Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    } else
                        return;

                } else if (mGeofencesAdded) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "Allow location access to set/edit a Geofence", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    button.setText("SET GEOFENCE");
                    seekBar.setEnabled(true);
                    populateGeofenceList();

                    LocationServices.GeofencingApi.removeGeofences(mGoogleApiClient, getGeofencePendingIntent()).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()) {
                                // Update state and save in shared preferences.
                                mGeofencesAdded = !mGeofencesAdded;
                                SharedPreferences.Editor editor = mSharedPreferences.edit();
                                editor.putBoolean(Constants.GEOFENCES_ADDED_KEY, mGeofencesAdded);
                                editor.apply();

                                Toast.makeText(
                                        getApplicationContext(),
                                        getString(mGeofencesAdded ? R.string.geofences_added :
                                                R.string.geofences_removed),
                                        Toast.LENGTH_SHORT
                                ).show();
                            } else {
                                // Get the status code for the error and log it using a user-friendly message.
                                String errorMessage = GeofenceErrorMessages.getErrorString(getApplicationContext(),
                                        status.getStatusCode());
                                Log.e(TAG, errorMessage);
                            }
                        }
                    });

                    if (circle != null) {
                        circle.remove();
                        marker.remove();
                        circleOptions.center(new LatLng(lat, lon)).strokeColor(Color.BLUE).strokeWidth(8).fillColor(0x300000ff);
                        circle = mMap.addCircle(circleOptions);
                        marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Current Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    } else
                        return;
                }
            }
        });

    }




    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        points.add(latLng);
        drawLine();
    }


    public void drawLine() {
        if (polyline != null) {
            polyline.remove();
            PolylineOptions options = new PolylineOptions().width(16).color(Color.BLUE).geodesic(true);
            for (int i = 0; i < points.size(); i++) {
                LatLng p = points.get(i);
                options.add(p);
            }
            polyline = mMap.addPolyline(options);

        }
        else {
            return;

        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        circleOptions = new CircleOptions().center(new LatLng(lat, lon)).radius(newRad)
                .fillColor(0x300000ff).strokeWidth(8).strokeColor(Color.BLUE);
        //PolylineOptions options = new PolylineOptions().width(16).color(Color.BLUE).geodesic(true);
        PolylineOptions o = new PolylineOptions().width(0).color(Color.TRANSPARENT).geodesic(true);

        polyline = mMap.addPolyline(o);

        if (mGeofencesAdded) {
            CircleOptions c = new CircleOptions().center(new LatLng(lat, lon)).radius(newRad)
                    .fillColor(0x300000ff).strokeWidth(8).strokeColor(Color.GREEN);
            circle = mMap.addCircle(c);
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Current Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
        else {
            CircleOptions c = new CircleOptions().center(new LatLng(lat, lon)).radius(newRad)
                    .fillColor(0x300000ff).strokeWidth(8).strokeColor(Color.BLUE);
            circle = mMap.addCircle(c);
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Current Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15.0f));



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {

                if (mGeofencesAdded) {

                } else {
                    lat = point.latitude;
                    lon = point.longitude;
                    CircleOptions f = new CircleOptions();
                    f.center(new LatLng(lat, lon)).radius(newRad).fillColor(0x300000ff).strokeWidth(8).strokeColor(Color.BLUE);
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(lat, lon)).title("Current Marker");
                    marker.remove();
                    circle.remove();
                    circle = mMap.addCircle(f);
                    marker = mMap.addMarker(markerOptions);
                    textView.setText(String.format("LAT: %.6f", lat) + String.format(", LON: %.6f", lon) + ", RAD: " + newRad + "m");

                }


            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                  //  permSet = true;

                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    String provider = locationManager.getBestProvider(new Criteria(), true);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MapsActivity.this);

                    buildGoogleApiClient();
                    mGoogleApiClient.connect();



                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Allow location access to set a Geofence", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mGoogleApiClient.connect();
        }
        else
            return;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mGoogleApiClient.disconnect();
        }
        else
            return;
    }

    @Override
    protected  void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("Latitude", lat);
        savedInstanceState.putDouble("Longitude", lon);
        savedInstanceState.putInt("newRad", newRad);
        savedInstanceState.putInt("Radius", radius);
        savedInstanceState.putParcelableArrayList("LocList", points);
        savedInstanceState.putBoolean("Perm", permSet);

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "Connected to GoogleApiClient");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason.
        Log.i(TAG, "Connection suspended");

        // onConnected() will be called again automatically when the service reconnects
    }

    /**
     * Builds and returns a GeofencingRequest. Specifies the list of geofences to be monitored.
     * Also specifies how the geofence notifications are initially triggered.
     */
    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();

        // The INITIAL_TRIGGER_ENTER flag indicates that com.brandonwicka.geofencing service should trigger a
        // GEOFENCE_TRANSITION_ENTER notification when the geofence is added and if the device
        // is already inside that geofence.
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);

        // Add the geofences to be monitored by com.brandonwicka.geofencing service.
        builder.addGeofences(mGeofenceList);

        // Return a GeofencingRequest.
        return builder.build();
    }

    /**
     * Adds geofences, which sets alerts to be notified when the device enters or exits one of the
     * specified geofences. Handles the success or failure results returned by addGeofences().
     */
    public void addGeofencesButtonHandler(View view) {
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            LocationServices.GeofencingApi.addGeofences(
                    mGoogleApiClient,
                    // The GeofenceRequest object.
                    getGeofencingRequest(),
                    // A pending intent that that is reused when calling removeGeofences(). This
                    // pending intent is used to generate an intent when a matched geofence
                    // transition is observed.
                    getGeofencePendingIntent()
            ).setResultCallback(this); // Result processed in onResult().
        } catch (SecurityException securityException) {
            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
            logSecurityException(securityException);
        }
    }

    /**
     * Removes geofences, which stops further notifications when the device enters or exits
     * previously registered geofences.
     */
    public void removeGeofencesButtonHandler(View view) {
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            // Remove geofences.
            LocationServices.GeofencingApi.removeGeofences(
                    mGoogleApiClient,
                    // This is the same pending intent that was used in addGeofences().
                    getGeofencePendingIntent()
            ).setResultCallback(this); // Result processed in onResult().
        } catch (SecurityException securityException) {
            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
            logSecurityException(securityException);
        }
    }

    private void logSecurityException(SecurityException securityException) {
        Log.e(TAG, "Invalid location permission. " +
                "You need to use ACCESS_FINE_LOCATION with geofences", securityException);
    }

    public void onResult(Status status) {
        if (status.isSuccess()) {
            // Update state and save in shared preferences.
            mGeofencesAdded = !mGeofencesAdded;
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(Constants.GEOFENCES_ADDED_KEY, mGeofencesAdded);
            editor.apply();

            // Update the UI. Adding geofences enables the Remove Geofences button, and removing
            // geofences enables the Add Geofences button.
            //setButtonsEnabledState();

            Toast.makeText(
                    this,
                    getString(mGeofencesAdded ? R.string.geofences_added :
                            R.string.geofences_removed),
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            // Get the status code for the error and log it using a user-friendly message.
            String errorMessage = GeofenceErrorMessages.getErrorString(this,
                    status.getStatusCode());
            Log.e(TAG, errorMessage);
        }
    }


    /**
     * Gets a PendingIntent to send with the request to add or remove Geofences. Location Services
     * issues the Intent inside this PendingIntent whenever a geofence transition occurs for the
     * current list of geofences.
     *
     * @return A PendingIntent for the IntentService that handles geofence transitions.
     */
    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(this, GeofenceIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
        // addGeofences() and removeGeofences().
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    /**
     * This sample hard codes geofence data. A real app might dynamically create geofences based on
     * the user's location.
     */
    public void populateGeofenceList() {
        HashMap<String, LatLng> fence = new HashMap<>();
        fence.put("My Geofence", new LatLng(lat, lon));
        for (Map.Entry<String, LatLng> entry : fence.entrySet()) {

            mGeofenceList.add(new Geofence.Builder()
                    // Set the request ID of the geofence. This is a string to identify this
                    // geofence.
                    .setRequestId(entry.getKey())

                            // Set the circular region of this geofence.
                    .setCircularRegion(
                            entry.getValue().latitude,
                            entry.getValue().longitude,
                            newRad
                    )

                            // Set the expiration duration of the geofence. This geofence gets automatically
                            // removed after this period of time.
                    .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)

                            // Set the transition types of interest. Alerts are only generated for these
                            // transition. We track entry and exit transitions in this sample.
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                            Geofence.GEOFENCE_TRANSITION_EXIT)

                            // Create the geofence.
                    .build());
        }
    }



    @Override
    public void onProviderDisabled(String provider) {
        //Toast.makeText(this.context, "GPS Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        //Toast.makeText(this.context, "GPS Enabled", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle bundle) {

    }

    SharedPreferences.Editor putDouble(final SharedPreferences.Editor edit, final String key, final double value) {
        return edit.putLong(key, Double.doubleToRawLongBits(value));
    }

    double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
    }

}
