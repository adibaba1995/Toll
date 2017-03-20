package com.apsit.toll;

import com.apsit.toll.pojo.directions.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 19/03/17.
 */

public interface DirectionService {
    //https://maps.googleapis.com/maps/api/directions/json?origin=19.211612,73.006749&destination=19.088953,72.924225&key=AIzaSyBrHlkV3P6-dFmMo_0BXkx_jmv5-ljdVL0
    @GET("maps/api/directions/json")
    Call<Data> getData(@Query("origin") String origin, @Query("destination") String destination,@Query("key") String key);
}