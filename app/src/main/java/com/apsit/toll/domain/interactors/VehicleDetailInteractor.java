package com.apsit.toll.domain.interactors;

import com.apsit.toll.data.network.pojo.vehicle.Vehicle;
import com.apsit.toll.data.network.service.TollService;
import com.apsit.toll.data.network.service.VehicleDetailService;
import com.apsit.toll.domain.model.MinMaxLatLong;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class VehicleDetailInteractor extends UseCase<String> {

    private VehicleDetailService service;

    @Inject
    public VehicleDetailInteractor(VehicleDetailService service) {
        this.service = service;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }

    @Override
    protected Observable buildUseCaseObservable(String data) {
        return service.getVehicleDetailsObservable(data);
    }
}
