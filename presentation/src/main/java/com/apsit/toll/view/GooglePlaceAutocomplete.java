package com.apsit.toll.view;

import com.apsit.toll.pojo.autocomplete.Addresses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 20/03/17.
 */

public interface GooglePlaceAutocomplete {
    @GET("maps/api/place/autocomplete/json")
    Call<Addresses> getSuggestions(@Query("input") String input, @Query("types") String types, @Query("language") String language, @Query("key") String key);
}
