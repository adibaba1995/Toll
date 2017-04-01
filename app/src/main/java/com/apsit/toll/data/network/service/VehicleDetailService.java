package com.apsit.toll.data.network.service;

import com.apsit.toll.data.network.pojo.vehicle.Vehicle;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class VehicleDetailService {
    private VehicleDetail service;

    @Inject
    public VehicleDetailService(Retrofit retrofit) {
        service = retrofit.create(VehicleDetail.class);
    }

    public Observable<Vehicle> getVehicleDetailsObservable(String vehicle_no) {
        return service.getVehicleDetails(vehicle_no);
    }

    public interface VehicleDetail {
        @GET("/vehicledetails.php")
        Observable<Vehicle> getVehicleDetails(@Query("vehicle_no") String vehicle_no);
    }
}
