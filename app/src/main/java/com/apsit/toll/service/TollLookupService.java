package com.apsit.toll.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.presentation.view.activity.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityathanekar on 27/03/17.
 */

public class TollLookupService extends Service implements LocationListener, ResultCallback<Status> {

    public static final String EXTRA_LAT_LNG = "EXTRA_LAT_LNG";
    private static final String ACTION_GEOFENCE = "ACTION_GEOFENCE";

    private static final int LOCATION_INTERVAL = 0;
    private static final float LOCATION_DISTANCE = 0;

    private static final int NOTIFICATION_ID = 100;

    private LocationManager locationManager;

    private LatLngBounds inner;

    private NotificationManager manager;

    private TollService service;

    private List<Geofence> geofences;
    private List<String> geofenceIds;

    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        geofences = new ArrayList<>();
        geofenceIds = new ArrayList<>();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        Log.d("Aditya", "Connection successful");
                        startLocationService();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Log.d("Aditya", "google api client suspend");
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d("Aditya", "google api client fail");
                    }
                })
                .addApi(LocationServices.API)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.adisoftwares.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(TollService.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startLocationService() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.putExtra(MainActivity.EXTRA_FRAGMENT_POSITION, MainActivity.ON_THE_GO);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Toll")
                .setContentText("On the go toll service")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(
                        Bitmap.createScaledBitmap(icon, 128, 128, false))
                .setContentIntent(pendingIntent)
                .setOngoing(true).build();
        startForeground(Constant.NOTIFICATION_ID.FOREGROUND_SERVICE,
                notification);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    this);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    this);

        } catch (java.lang.SecurityException ex) {

        } catch (IllegalArgumentException ex) {

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(Constant.ACTION.STARTFOREGROUND_ACTION)) {
            mGoogleApiClient.connect();
        } else if (intent.getAction().equals(Constant.ACTION.ON_THE_GO_ACTION)) {

        } else if (intent.getAction().equals(
                Constant.ACTION.STOPFOREGROUND_ACTION)) {
            stopForeground(true);
            stopSelf();
        } else if (intent.getAction().equals(ACTION_GEOFENCE)) {
            Log.d("Aditya", "Geofence");
            GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
            if (geofencingEvent.hasError()) {
                String errorMessage = GeofenceErrorMessages.getErrorString(this,
                        geofencingEvent.getErrorCode());
                Log.e("Aditya", errorMessage);
//                return;
            }

            // Get the transition type.
            int geofenceTransition = geofencingEvent.getGeofenceTransition();

            // Test that the reported transition was of interest.
            if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                    geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

                // Get the geofences that were triggered. A single event can trigger
                // multiple geofences.
                List triggeringGeofences = geofencingEvent.getTriggeringGeofences();

                // Get the transition details as a String.
                String geofenceTransitionDetails = getGeofenceTransitionDetails(
                        this,
                        geofenceTransition,
                        triggeringGeofences
                );

                // Send notification and log the transition details.
                sendNotification(geofenceTransitionDetails);
                Log.d("Aditya", geofenceTransitionDetails);
            } else {
                // Log the error.
                Log.e("Aditya", getString(R.string.geofence_transition_invalid_type,
                        geofenceTransition));
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.cancel(NOTIFICATION_ID);
    }

    @Override
    public void onLocationChanged(Location location) {
        sendLocationBroadcast(new LatLng(location.getLatitude(), location.getLongitude()));
        if (inner != null) {
            if (location.getLatitude() > inner.northeast.latitude || location.getLatitude() < inner.southwest.latitude || location.getLongitude() > inner.northeast.longitude || location.getLongitude() < inner.southwest.longitude) {
                Log.d("Aditya", "Inner is not null");
                getGeofences(new LatLng(location.getLatitude(), location.getLongitude()));
            }
        } else {
            getGeofences(new LatLng(location.getLatitude(), location.getLongitude()));
        }
//        geofences.add(new Geofence.Builder()
//                // Set the request ID of the geofence. This is a string to identify this
//                // geofence.
//                .setRequestId("thane")
//
//                .setCircularRegion(
//                        19.268029,
//                        72.967392,
//                        Constant.GEOFENCE.GEOFENCE_RADIUS_IN_METERS
//                )
//                .setExpirationDuration(Constant.GEOFENCE.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
//                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
//                        Geofence.GEOFENCE_TRANSITION_EXIT)
//                .build());
//
//        LocationServices.GeofencingApi.addGeofences(
//                mGoogleApiClient,
//                getGeofencingRequest(geofences),
//                getGeofencePendingIntent()
//        ).setResultCallback(TollLookupService.this);
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

    private void sendLocationBroadcast(LatLng latLng) {
        Intent intent = new Intent("positionChanged");
        intent.putExtra(EXTRA_LAT_LNG, latLng);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void getGeofences(LatLng latLng) {
        LatLngBounds innerBounds = toBounds(latLng, 50000);
        inner = innerBounds;

        LatLngBounds outerBounds = toBounds(latLng, 55000);

        service.getTolls(innerBounds.southwest.latitude + "," + innerBounds.southwest.longitude, innerBounds.northeast.latitude + "," + innerBounds.northeast.longitude).enqueue(new Callback<List<Toll>>() {
            @Override
            public void onResponse(Call<List<Toll>> call, Response<List<Toll>> response) {
                if (response.isSuccessful()) {
                    HashMap<String, LatLng> fence = new HashMap<>();
                    geofences.clear();
//                    removeGeofences();
                    for (Toll toll : response.body()) {
                        fence.put(toll.getPlace_id(), new LatLng(toll.getLatitude(), toll.getLongitude()));
                        geofenceIds.add(toll.getPlace_id());
                        geofences.add(new Geofence.Builder()
                                // Set the request ID of the geofence. This is a string to identify this
                                // geofence.
                                .setRequestId(toll.getPlace_id())

                                .setCircularRegion(
                                        toll.getLatitude(),
                                        toll.getLongitude(),
                                        Constant.GEOFENCE.GEOFENCE_RADIUS_IN_METERS
                                )
                                .setExpirationDuration(Constant.GEOFENCE.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                                        Geofence.GEOFENCE_TRANSITION_EXIT)
                                .build());
                    }

                    if (geofences.size() > 0)
                        LocationServices.GeofencingApi.addGeofences(
                                mGoogleApiClient,
                                getGeofencingRequest(geofences),
                                getGeofencePendingIntent()
                        ).setResultCallback(TollLookupService.this);
                }
            }

            @Override
            public void onFailure(Call<List<Toll>> call, Throwable t) {

            }
        });
    }

    private void removeGeofences() {
        LocationServices.GeofencingApi.removeGeofences(mGoogleApiClient, geofenceIds)
                .setResultCallback(TollLookupService.this);
        geofenceIds.clear();
    }

    private PendingIntent getGeofencePendingIntent() {
        Intent intent = new Intent(this, TollLookupService.class);
        intent.setAction(ACTION_GEOFENCE);
        return PendingIntent.getService(this, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
    }

    private GeofencingRequest getGeofencingRequest(List<Geofence> geofences) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofences);
        return builder.build();
    }

    public LatLngBounds toBounds(LatLng center, double radius) {
        LatLng southwest = SphericalUtil.computeOffset(center, radius * Math.sqrt(2.0), 225);
        LatLng northeast = SphericalUtil.computeOffset(center, radius * Math.sqrt(2.0), 45);
        return new LatLngBounds(southwest, northeast);
    }

    @Override
    public void onResult(@NonNull Status status) {
        if (status.isSuccess()) {
            Toast.makeText(this, "Geofence set", Toast.LENGTH_SHORT).show();
        }
    }

    private String getGeofenceTransitionDetails(
            Context context,
            int geofenceTransition,
            List<Geofence> triggeringGeofences) {

        String geofenceTransitionString = getTransitionString(geofenceTransition);

        // Get the Ids of each geofence that was triggered.
        ArrayList triggeringGeofencesIdsList = new ArrayList();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesIdsList.add(geofence.getRequestId());
        }
        String triggeringGeofencesIdsString = TextUtils.join(", ", triggeringGeofencesIdsList);

        return geofenceTransitionString + ": " + triggeringGeofencesIdsString;
    }

    private String getTransitionString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return getString(R.string.geofence_transition_entered);
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return getString(R.string.geofence_transition_exited);
            default:
                return getString(R.string.unknown_geofence_transition);
        }
    }

    private void sendNotification(String notificationDetails) {
        // Create an explicit content Intent that starts the main Activity.
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);

        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(MainActivity.class);

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get a notification builder that's compatible with platform versions >= 4
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // Define the notification settings.
        builder.setSmallIcon(R.mipmap.ic_launcher)
                //In a real app, you may want to use a library like Volley
                // to decode the Bitmap.
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher))
                .setColor(Color.RED)
                .setContentTitle(notificationDetails)
                .setContentText(getString(R.string.geofence_transition_notification_text))
                .setContentIntent(notificationPendingIntent);

        // Dismiss notification once the user touches it.
        builder.setAutoCancel(true);

        // Get an instance of the Notification manager
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Issue the notification
        mNotificationManager.notify(0, builder.build());
    }
}