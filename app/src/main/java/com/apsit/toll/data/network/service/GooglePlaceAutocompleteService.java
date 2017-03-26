package com.apsit.toll.data.network.service;

import android.graphics.Color;

import com.apsit.toll.data.network.pojo.autocomplete.Addresses;
import com.apsit.toll.data.network.pojo.directions.Data;
import com.apsit.toll.data.network.pojo.directions.Leg;
import com.apsit.toll.data.network.pojo.directions.Route;
import com.apsit.toll.data.network.pojo.directions.Step;
import com.apsit.toll.domain.model.Direction;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.DirectionsResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 22/03/17.
 */

public class GooglePlaceAutocompleteService {

    private GooglePlaceAutocomplete service;

    @Inject
    public GooglePlaceAutocompleteService(Retrofit retrofitAutocomplete) {
        service = retrofitAutocomplete.create(GooglePlaceAutocomplete.class);
    }

    public Observable<Addresses> getAutocompleteObservable(String input) {
        return service.getSuggestions(input, "geocode", "en", "AIzaSyAY42JwA3Zn8vpbM-E5lBvakPZ6lVLGu74");
    }

    public interface GooglePlaceAutocomplete {
        @GET("maps/api/place/autocomplete/json")
        Observable<Addresses> getSuggestions(@Query("input") String input, @Query("types") String types, @Query("language") String language, @Query("key") String key);
    }
}
