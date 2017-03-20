package com.apsit.toll.view.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apsit.toll.DirectionService;
import com.apsit.toll.R;
import com.apsit.toll.pojo.autocomplete.Addresses;
import com.apsit.toll.pojo.directions.Data;
import com.apsit.toll.pojo.directions.Overview_polyline;
import com.apsit.toll.view.GooglePlaceAutocomplete;
import com.apsit.toll.view.activity.JholActivity;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.PolyUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityathanekar on 16/03/17.
 */

public class SelectFragment extends Fragment implements OnMapReadyCallback{

    @BindView(R.id.source)
    FloatingSearchView source;
    @BindView(R.id.destination)
    FloatingSearchView destination;

    private Unbinder unbinder;

    private GooglePlaceAutocomplete autocompleteService;
    private DirectionService directionService;
    private Retrofit retrofitDirection;

    private GoogleMap mMap;
    Marker sourceMarker, destinationMarker;

    private LatLng sourcelatlng, destinationlatlng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        MapFragment mapFragment = (MapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofitAutocomplete = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        autocompleteService = retrofitAutocomplete.create(GooglePlaceAutocomplete.class);

        retrofitDirection = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(
                        GsonConverterFactory.create(gson))
                .build();

        directionService = retrofitDirection.create(DirectionService.class);

        source.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                autocompleteService.getSuggestions(newQuery, "geocode", "en", "AIzaSyAY42JwA3Zn8vpbM-E5lBvakPZ6lVLGu74").enqueue(new Callback<Addresses>() {
                    @Override
                    public void onResponse(Call<Addresses> call, Response<Addresses> response) {
                        if(response.isSuccessful()) {
                            Addresses addresses = response.body();
                            source.swapSuggestions(addresses.getPredictions());
                        }
                    }

                    @Override
                    public void onFailure(Call<Addresses> call, Throwable t) {

                    }
                });
            }
        });

        source.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                try {
                    for(Address address: geocoder.getFromLocationName(searchSuggestion.getBody(), 2)) {
                        if(sourceMarker != null)
                            sourceMarker.remove();
                        sourcelatlng = new LatLng(address.getLatitude(), address.getLongitude());
                        sourceMarker = mMap.addMarker(new MarkerOptions().position(sourcelatlng));
                        break;
                    }

                    getTolls();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });

        destination.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                autocompleteService.getSuggestions(newQuery, "geocode", "en", "AIzaSyAY42JwA3Zn8vpbM-E5lBvakPZ6lVLGu74").enqueue(new Callback<Addresses>() {
                    @Override
                    public void onResponse(Call<Addresses> call, Response<Addresses> response) {
                        if(response.isSuccessful()) {
                            Addresses addresses = response.body();
                            destination.swapSuggestions(addresses.getPredictions());
                        }
                    }

                    @Override
                    public void onFailure(Call<Addresses> call, Throwable t) {

                    }
                });
            }
        });

        destination.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                try {
                    for(Address address: geocoder.getFromLocationName(searchSuggestion.getBody(), 2)) {
                        if(destinationMarker != null)
                            destinationMarker.remove();
                        destinationlatlng = new LatLng(address.getLatitude(), address.getLongitude());
                        destinationMarker = mMap.addMarker(new MarkerOptions().position(destinationlatlng));
                        break;
                    }
                    getTolls();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getTolls() {
        if (sourcelatlng != null && destinationlatlng != null) {
            directionService = retrofitDirection.create(DirectionService.class);
            //https://maps.googleapis.com/maps/api/directions/json?origin=19.181474,72.980297&destination=19.183904,72.965463&key=AIzaSyBrHlkV3P6-dFmMo_0BXkx_jmv5-ljdVL0
            directionService.getData(sourcelatlng.latitude + "," + sourcelatlng.longitude, destinationlatlng.latitude + "," + destinationlatlng.longitude, "AIzaSyBrHlkV3P6-dFmMo_0BXkx_jmv5-ljdVL0").enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    if (response.isSuccessful()) {
                        Data data = response.body();
                        Overview_polyline overview_polyline = data.getRoutes().get(0).getOverview_polyline();
                        String encodedPolyline = overview_polyline.getPoints();
                        List<LatLng> list = PolyUtil.decode(encodedPolyline);
                        mMap.addPolyline(new PolylineOptions()
                                .addAll(list)
                                .width(25)
                                .color(Color.BLUE)
                                .geodesic(true));

                        List<LatLng> posList = new ArrayList<>();

                        posList.add(new LatLng(19.261018, 72.969054));
                        posList.add(new LatLng(19.257038, 72.970665));
                        posList.add(new LatLng(19.251352, 72.972971));
                        posList.add(new LatLng(19.217018, 72.978022));

                        for(LatLng pos: posList) {
                            if (PolyUtil.isLocationOnPath(pos, list, true, 100.0)) {
                                mMap.addMarker(new MarkerOptions().position(new LatLng(pos.latitude, pos.longitude)));
                            } else {

                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {

                }
            });
        }
    }
}
