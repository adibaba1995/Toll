package com.apsit.toll.presentation.view.fragment;

import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.presentation.view.activity.MainActivity;
import com.apsit.toll.service.Constant;
import com.apsit.toll.service.TollLookupService;
import com.apsit.toll.service.TollService;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.SphericalUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityathanekar on 29/03/17.
 */

public class OnTheGoFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.prestart)
    View prestart;
    @BindView(R.id.container)
    FrameLayout container;

    private GoogleMap map;

    private Unbinder unbinder;

    private MapFragment mapFragment;

    private LatLngBounds inner;

    Marker marker;

    private TollService service;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LatLng latLng = intent.getParcelableExtra(TollLookupService.EXTRA_LAT_LNG);

            if (marker != null)
                marker.remove();
            marker = map.addMarker(new MarkerOptions().position(latLng).draggable(true));

            if (inner != null) {
                if (latLng.latitude > inner.northeast.latitude || latLng.latitude < inner.southwest.latitude || latLng.longitude > inner.northeast.longitude || latLng.longitude < inner.southwest.longitude) {
                    drawRectangle(latLng);
                }
            } else {
                drawRectangle(latLng);
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_on_the_go_toll, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        init();
    }

    private void init() {
        Typeface titleFont = Typeface.
                createFromAsset(getActivity().getAssets(), "fonts/future.ttf");
        title.setTypeface(titleFont);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).syncToolbar();

        start.setOnClickListener(this);

        if(isMyServiceRunning(TollLookupService.class)) {
            showMap();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startOnTheGoService();
                showMap();
                break;
        }
    }

    private void showMap() {
        container.removeAllViews();
        mapFragment = new MapFragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (marker != null)
                    marker.remove();
                marker = map.addMarker(new MarkerOptions().position(latLng).draggable(true));

                if (inner != null) {
                    if (latLng.latitude > inner.northeast.latitude || latLng.latitude < inner.southwest.latitude || latLng.longitude > inner.northeast.longitude || latLng.longitude < inner.southwest.longitude) {
                        drawRectangle(latLng);
                    }
                } else {
                    drawRectangle(latLng);
                }
            }
        });
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                mMessageReceiver, new IntentFilter("positionChanged"));
    }

    public void startOnTheGoService() {
        Intent intent = new Intent(getActivity(), TollLookupService.class);
        intent.setAction(Constant.ACTION.STARTFOREGROUND_ACTION);
        getActivity().startService(intent);
    }

    private void drawRectangle(LatLng latLng) {
        map.clear();
        marker = map.addMarker(new MarkerOptions().position(latLng).draggable(true));
        LatLngBounds innerBounds = toBounds(latLng, 50000);
        inner = innerBounds;
        PolylineOptions innerOptions = new PolylineOptions();
        innerOptions.width(10);
        innerOptions.color(Color.BLUE);
        innerOptions.add(innerBounds.southwest, new LatLng(innerBounds.southwest.latitude, innerBounds.northeast.longitude), innerBounds.northeast, new LatLng(innerBounds.northeast.latitude, innerBounds.southwest.longitude), innerBounds.southwest);

        LatLngBounds outerBounds = toBounds(latLng, 55000);
        PolylineOptions outerOptions = new PolylineOptions();
        outerOptions.width(10);
        outerOptions.color(Color.GREEN);
        outerOptions.add(outerBounds.southwest, new LatLng(outerBounds.southwest.latitude, outerBounds.northeast.longitude), outerBounds.northeast, new LatLng(outerBounds.northeast.latitude, outerBounds.southwest.longitude), outerBounds.southwest);

        map.addPolyline(innerOptions);
        map.addPolyline(outerOptions);

        service.getTolls(innerBounds.southwest.latitude + "," + innerBounds.southwest.longitude, innerBounds.northeast.latitude + "," + innerBounds.northeast.longitude).enqueue(new Callback<List<Toll>>() {
            @Override
            public void onResponse(Call<List<Toll>> call, Response<List<Toll>> response) {
                if(response.isSuccessful()) {
                    for(Toll toll: response.body()) {
                        map.addMarker(new MarkerOptions().position(new LatLng(toll.getLatitude(), toll.getLongitude())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Toll>> call, Throwable t) {

            }
        });
    }

    public LatLngBounds toBounds(LatLng center, double radius) {
        LatLng southwest = SphericalUtil.computeOffset(center, radius * Math.sqrt(2.0), 225);
        LatLng northeast = SphericalUtil.computeOffset(center, radius * Math.sqrt(2.0), 45);
        return new LatLngBounds(southwest, northeast);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
