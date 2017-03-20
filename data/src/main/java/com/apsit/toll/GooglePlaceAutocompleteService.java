package com.apsit.toll;

import com.apsit.toll.pojo.Addresses;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 15/03/17.
 */

public class GooglePlaceAutocompleteService {

    private static final String BASE_URL = "https://maps.googleapis.com/";
    private GooglePlaceAutocomplete mGooglePlaceAutocompleteApi;

    public GooglePlaceAutocompleteService() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create(gson))
                .build();

        mGooglePlaceAutocompleteApi = retrofit.create(GooglePlaceAutocomplete.class);
    }

    public GooglePlaceAutocomplete getApi() {
        return mGooglePlaceAutocompleteApi;
    }

    public interface GooglePlaceAutocomplete {
        @GET("maps/api/place/autocomplete/json")
        Call<Addresses> getSuggestions(@Query("input") String input, @Query("types") String types, @Query("language") String language, @Query("key") String key);
    }

}
