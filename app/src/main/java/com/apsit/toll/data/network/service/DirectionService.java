package com.apsit.toll.data.network.service;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.directions.Data;
import com.apsit.toll.data.network.pojo.directions.Leg;
import com.apsit.toll.data.network.pojo.directions.Route;
import com.apsit.toll.data.network.pojo.directions.Step;
import com.apsit.toll.domain.model.Direction;
import com.apsit.toll.domain.model.MinMaxLatLong;
import com.apsit.toll.presentation.utility.Utility;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.single.SingleFlatMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 23/03/17.
 */

public class DirectionService {
    private Direction directionService;
    private Context context;

    @Inject
    public DirectionService(Retrofit retrofitAutocomplete, Context context) {
        directionService = retrofitAutocomplete.create(Direction.class);
        this.context = context;
    }

    public Observable<List<com.apsit.toll.domain.model.Direction>> getDirectionObservable(String origin, String destination) {

        return directionService.getData(origin, destination, "true", "AIzaSyBrHlkV3P6-dFmMo_0BXkx_jmv5-ljdVL0")
                .flatMapSingle(new Function<Data, SingleSource<List<com.apsit.toll.domain.model.Direction>>>() {
                    @Override
                    public SingleSource<List<com.apsit.toll.domain.model.Direction>> apply(Data data) throws Exception {
                        boolean condition = true;
                        double minlat = 0, minlong = 0, maxlat = 0, maxlong = 0;
                        long distance = 0;
                        List<com.apsit.toll.domain.model.Direction> directionList = new ArrayList<>();
                        for (Route route : data.getRoutes()) {
                            PolylineOptions options = new PolylineOptions()
                                    .width(Utility.convertDpToPixel(5, context))
                                    .color(ContextCompat.getColor(context, R.color.mapGray))
                                    .geodesic(true);
                            for (Leg leg : route.getLegs()) {
                                for (Step step : leg.getSteps()) {
                                    distance = step.getDistance().getValue();
                                    List<LatLng> list = PolyUtil.decode(step.getPolyline().getPoints());
                                    if (condition) {
                                        condition = false;
                                        maxlong = list.get(0).longitude;
                                        minlong = list.get(0).longitude;
                                        maxlat = list.get(0).latitude;
                                        minlat = list.get(0).longitude;
                                    }
                                    for (int i = 0; i < list.size(); i += 2) {
                                        LatLng var = list.get(i);
                                        if (var.longitude > maxlong)
                                            maxlong = var.longitude;
                                        if (var.longitude < minlong)
                                            minlong = var.longitude;
                                        if (var.latitude > maxlat)
                                            maxlat = var.latitude;
                                        if (var.latitude < minlat)
                                            minlat = var.latitude;
                                        options.add(var);
                                        options.clickable(true);
                                    }
                                }
                            }
                            condition = true;
                            directionList.add(new com.apsit.toll.domain.model.Direction(options, new MinMaxLatLong(minlat, minlong, maxlat, maxlong), distance));
                        }
                        return SingleFlatMap.just(directionList);
                    }
                });
    }

    private interface Direction {
        //https://maps.googleapis.com/maps/api/directions/json?origin=19.211612,73.006749&destination=19.088953,72.924225&key=AIzaSyBrHlkV3P6-dFmMo_0BXkx_jmv5-ljdVL0
        @GET("maps/api/directions/json")
        Observable<Data> getData(@Query("origin") String origin, @Query("destination") String destination, @Query("alternatives") String alternatives, @Query("key") String key);
    }
}
