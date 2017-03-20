package com.apsit.toll.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.apsit.toll.DirectionService;
import com.apsit.toll.pojo.directions.Data;
import com.apsit.toll.pojo.directions.Overview_polyline;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.PolyUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityathanekar on 19/03/17.
 */

public class JholActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://maps.googleapis.com/";
    private DirectionService directionService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create(gson))
                .build();

        directionService = retrofit.create(DirectionService.class);
        //https://maps.googleapis.com/maps/api/directions/json?origin=19.181474,72.980297&destination=19.183904,72.965463&key=AIzaSyBrHlkV3P6-dFmMo_0BXkx_jmv5-ljdVL0
        directionService.getData("19.181474,72.980297", "19.183904,72.965463", "AIzaSyBrHlkV3P6-dFmMo_0BXkx_jmv5-ljdVL0").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful()) {
                    Data data = response.body();
                    Overview_polyline overview_polyline = data.getRoutes().get(0).getOverview_polyline();
                    String encodedPolyline = overview_polyline.getPoints();
                    if(PolyUtil.containsLocation(19.18634, 72.97844, PolyUtil.decode(encodedPolyline), true)) {
                        Toast.makeText(JholActivity.this, "hurray", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(JholActivity.this, "madarchod", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });

//        PolyUtil.containsLocation(19.18257, 72.98015, PolyUtil.decode(""), true);
    }
}
