package com.apsit.toll.presentation.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.apsit.toll.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 23/03/17.
 */

public class PickLocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener, View.OnClickListener {

    public static final String EXTRA_SOURCE_DESTINATION = "com.apsit.toll.EXTRA_SOURCE_DESTINATION";
    public static final String EXTRA_POSITION = "com.apsit.toll.EXTRA_POSITION";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.ok)
    Button ok;

    private Unbinder unbinder;
    private boolean sourcedest;

    private GoogleMap mMap;
    private Marker position;
    private LocationManager locationManager;
    private Criteria criteria;
    private String bestProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_location);
        unbinder = ButterKnife.bind(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        init();
    }

    private void init() {
        sourcedest = getIntent().getBooleanExtra(EXTRA_SOURCE_DESTINATION, true);
        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        if (sourcedest)
            toolbar.setTitle("Select source");
        else
            toolbar.setTitle("Select destination");
        toolbar.setSubtitle("Place marker on the map by clicking");
        setSupportActionBar(toolbar);
        fab.setOnClickListener(this);
        back.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (position != null)
            position.remove();

        addMarker(latLng.latitude, latLng.longitude);

        position = mMap.addMarker(new MarkerOptions().position(latLng));
    }

    protected void getLocation() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

        //You can still do this if you like, you might get lucky:
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            Log.e("TAG", "GPS is on");
            addMarker(location.getLatitude(), location.getLongitude());
            zoomMap(location);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //Hey, a non null location! Sweet!
        Log.d("TAG", "location changed");
        //remove location callback:
        locationManager.removeUpdates(this);

        addMarker(location.getLatitude(), location.getLongitude());
        zoomMap(location);
    }

    private void addMarker(double latitude, double longitude) {
        if (position != null)
            position.remove();

        position = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude)));

        ok.setEnabled(true);
    }

    private void zoomMap(Location location) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                .zoom(17)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                getLocation();
                break;
            case R.id.back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.ok:
                if (position != null) {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_SOURCE_DESTINATION, sourcedest);
                    intent.putExtra(EXTRA_POSITION, position.getPosition());
                    setResult(RESULT_OK, intent);
                } else
                    setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}