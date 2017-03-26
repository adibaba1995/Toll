package com.apsit.toll.data.network.service;

import com.apsit.toll.data.network.pojo.autocomplete.Addresses;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.domain.model.MinMaxLatLong;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 25/03/17.
 */

public class TollService {

    private Toll service;

    @Inject
    public TollService(Retrofit retrofitAutocomplete) {
        service = retrofitAutocomplete.create(Toll.class);
    }

    public Observable<List<com.apsit.toll.data.network.pojo.toll.Toll>> getTollsObservable(MinMaxLatLong minMaxLatLong) {
        return service.getTolls(minMaxLatLong.getMinLat() + "," + minMaxLatLong.getMinLong(), minMaxLatLong.getMaxLat() + "," + minMaxLatLong.getMaxLong());
    }

    public interface Toll {
        @GET("/toll/tollinfo.php")
        Observable<List<com.apsit.toll.data.network.pojo.toll.Toll>> getTolls(@Query("min") String min, @Query("max") String max);
    }
}
