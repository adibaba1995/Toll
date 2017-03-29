package com.apsit.toll.service;

import com.apsit.toll.data.network.pojo.toll.Toll;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 27/03/17.
 */

public interface TollService {
    @GET("/tollinfo.php")
    Call<List<Toll>> getTolls(@Query("min") String min, @Query("max") String max);
}