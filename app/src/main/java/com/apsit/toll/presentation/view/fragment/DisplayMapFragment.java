package com.apsit.toll.presentation.view.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.domain.model.Direction;
import com.apsit.toll.domain.model.DirectionData;
import com.apsit.toll.domain.model.MinMaxLatLong;
import com.apsit.toll.presentation.application.TollApplication;
import com.apsit.toll.presentation.presenter.DisplayMapPresenter;
import com.apsit.toll.presentation.utility.Utility;
import com.apsit.toll.presentation.view.DisplayMapView;
import com.apsit.toll.presentation.view.activity.DirectionActivity;
import com.apsit.toll.presentation.view.activity.MainActivity;
import com.apsit.toll.presentation.view.activity.TollInfoActivity;
import com.apsit.toll.presentation.view.adapter.TollRecyclerViewAdapter;
import com.apsit.toll.presentation.view.viewmodel.TollCarrier;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.maps.android.PolyUtil;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sothree.slidinguppanel.ScrollableViewHelper;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * Created by adityathanekar on 16/03/17.
 */

public class DisplayMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener, View.OnClickListener, DisplayMapView, MultiplePermissionsListener, LocationListener {

    public static final int DIRECTION_ACTIVITY_REQUEST_CODE = 11;

    @BindView(R.id.directionfab)
    FloatingActionButton directionfab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.locationfab)
    FloatingActionButton locationFab;
    @BindView(R.id.tollList)
    RecyclerView tollList;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingUpPanelLayout;
    @BindView(R.id.dragView)
    View dragView;
    @BindView(R.id.total)
    TextView total;

    @Inject
    DisplayMapPresenter presenter;

    private Unbinder unbinder;

    private GoogleMap mMap;
    private Polyline previousPolyline;
    private Map<String, MinMaxLatLong> rectangles;
    private Map<String, Toll> tolls;

    private LocationManager locationManager;
    private Criteria criteria;
    private String bestProvider;
    private TollRecyclerViewAdapter adapter;
    private boolean zoomToLocationClick = false;
    private ArrayList<Toll> tollsList;
    private double totalPrice;

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TollApplication) getActivity().getApplication()).createDisplayMapComponent().inject(this);
        rectangles = new HashMap<>();
        tolls = new HashMap<>();
        tollsList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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
        ((MainActivity) getActivity()).setCallback(new MainActivity.Callback() {
            @Override
            public void backPressed() {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
        FragmentManager fragmentManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            fragmentManager = getChildFragmentManager();
        } else{
            fragmentManager = getFragmentManager();
        }
        com.google.android.gms.maps.MapFragment mapFragment = (com.google.android.gms.maps.MapFragment) fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        directionfab.setOnClickListener(this);
        locationFab.setOnClickListener(this);
        adapter = new TollRecyclerViewAdapter(tollsList, getActivity());
        adapter.setCallback(new TollRecyclerViewAdapter.Callback() {
            @Override
            public void checkChanged(Toll toll, boolean isChecked) {
                if (isChecked)
                    totalPrice += toll.getTwo_axle();
                else
                    totalPrice -= toll.getTwo_axle();
                total.setText("Total:  ₹ " + Utility.formatFloat(totalPrice));
            }
        });
        tollList.setLayoutManager(new LinearLayoutManager(getActivity()));
        tollList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        tollList.setAdapter(adapter);
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                switch (newState) {
                    case EXPANDED:
                        ((MainActivity) getActivity()).lockNavigationView();
                        break;
                    case COLLAPSED:
                        ((MainActivity) getActivity()).unlockNavigationView();
                        break;
                }
            }
        });
        slidingUpPanelLayout.setPanelHeight(0);
        slidingUpPanelLayout.setScrollableViewHelper(new NestedScrollableViewHelper());
        FirebaseMessaging.getInstance().subscribeToTopic("tolls");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (((MainActivity) getActivity()).isOptionItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnPolylineClickListener(this);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getActivity(), TollInfoActivity.class);
                intent.putExtra(TollInfoActivity.EXTRA_TOLL, tolls.get(marker.getId()));
                startActivity(intent);
            }
        });
        UiSettings settings = mMap.getUiSettings();
        settings.setMapToolbarEnabled(false);
        settings.setCompassEnabled(false);
        settings.setMyLocationButtonEnabled(false);
        checkLocationPermission();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DIRECTION_ACTIVITY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    mMap.clear();
                    LatLng source = data.getParcelableExtra(DirectionActivity.EXTRA_SOURCE_CO_ORDINATES);
                    LatLng destination = data.getParcelableExtra(DirectionActivity.EXTRA_DESTINATION_CO_ORDINATES);
                    mMap.addMarker(new MarkerOptions().position(source));
                    mMap.addMarker(new MarkerOptions().position(destination));
                    presenter.getPath(new DirectionData(source.latitude + "," + source.longitude, destination.latitude + "," + destination.longitude));
                }
                break;
        }
    }


    @Override
    public void onPolylineClick(Polyline polyline) {
        polyline.setColor(ContextCompat.getColor(getActivity(), R.color.mapBlue));
        polyline.setZIndex(1);
        if (previousPolyline != null) {
            previousPolyline.setColor(ContextCompat.getColor(getActivity(), R.color.mapGray));
            previousPolyline.setZIndex(0);
        }
        previousPolyline = polyline;

        presenter.getTolls(rectangles.get(previousPolyline.getId()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.directionfab:
                Intent intent = new Intent(getActivity(), DirectionActivity.class);
                startActivityForResult(intent, DIRECTION_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.locationfab:
                getLocation();
                break;
        }
    }

    @Override
    public void setPath(List<Direction> directions) {
        long minDistance = 0;
        for (Direction direction : directions) {
            PolylineOptions option = direction.getPolylineOption();
            Polyline polyline = mMap.addPolyline(option);
            if (minDistance == 0) {
                minDistance = direction.getDistance();
                previousPolyline = polyline;
            }
            if (minDistance > direction.getDistance()) {
                minDistance = direction.getDistance();
                previousPolyline = polyline;
            }
            rectangles.put(polyline.getId(), direction.getMinMaxLatLong());
        }
        if (previousPolyline != null) {
            previousPolyline.setColor(ContextCompat.getColor(getActivity(), R.color.mapBlue));
            previousPolyline.setZIndex(1);
        }
        presenter.getTolls(rectangles.get(previousPolyline.getId()));
    }

    @Override
    public void setTolls(List<Toll> tolls) {
        checkTollOnPolyline(previousPolyline.getPoints(), tolls);
    }

    public void checkTollOnPolyline(final List<LatLng> polyline, final List<Toll> tollList) {
        Single.create(new SingleOnSubscribe<List<TollCarrier>>() {
            @Override
            public void subscribe(SingleEmitter<List<TollCarrier>> e) throws Exception {
                totalPrice = 0;
                List<TollCarrier> carriers = new ArrayList<>();
                for (Toll toll : tollList) {
                    LatLng pos = new LatLng(toll.getLatitude(), toll.getLongitude());
                    if (PolyUtil.isLocationOnPath(pos, polyline, true, 100.0)) {
                        totalPrice += toll.getTwo_axle();
                        carriers.add(new TollCarrier(toll, new MarkerOptions().title(toll.getName()).snippet(toll.getAddress()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(pos)));
                    }
                }
                e.onSuccess(carriers);
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TollCarrier>>() {
                    @Override
                    public void accept(List<TollCarrier> carriers) throws Exception {
                        tollsList.clear();
                        for (TollCarrier carrier : carriers) {
                            tolls.put(mMap.addMarker(carrier.getMarkerOptions()).getId(), carrier.getToll());
                            tollsList.add(carrier.getToll());
                        }
                        adapter.notifyDataSetChanged();
                        coordinatorLayout.setPadding(0, 0, 0, (int) Utility.convertDpToPixel(60, getActivity()));
                        dragView.setVisibility(View.VISIBLE);
                        slidingUpPanelLayout.setPanelHeight((int) Utility.convertDpToPixel(60, getActivity()));
                        total.setText("Total:  ₹ " + Utility.formatFloat(totalPrice));
                    }
                });
    }

    protected void getLocation() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
        zoomToLocationClick = true;
        checkLocationPermission();
    }

    private void zoomMap(Location location) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                .zoom(10)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
            showPermissionGranted(response.getPermissionName());
        }
    }

    public void showPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_COARSE_LOCATION:
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (zoomToLocationClick) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                } else {
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
        showPermissionRationale(token);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showPermissionRationale(final PermissionToken token) {
        token.continuePermissionRequest();
    }

    @Override
    public void onLocationChanged(Location location) {
        locationManager.removeUpdates(this);
        zoomMap(location);
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

    private void checkLocationPermission() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(this)
                .check();
    }

    public class NestedScrollableViewHelper extends ScrollableViewHelper {
        public int getScrollableViewScrollPosition(View scrollableView, boolean isSlidingUp) {
            if (tollList instanceof RecyclerView) {
                if(isSlidingUp){
                    return tollList.getScrollY();
                } else {
                    View child = tollList.getChildAt(0);
                    return (child.getBottom() - (tollList.getHeight() + tollList.getScrollY()));
                }
            } else {
                return 0;
            }
        }
    }
}