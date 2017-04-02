package com.apsit.toll.domain.interactors;

import com.apsit.toll.data.network.pojo.vehicle.Vehicle;
import com.apsit.toll.data.network.service.TollService;
import com.apsit.toll.data.storage.AddVehicleService;
import com.apsit.toll.domain.model.MinMaxLatLong;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by adityathanekar on 02/04/17.
 */

public class AddVehicleInteractor extends UseCase<Vehicle> {

    private AddVehicleService service;

    @Inject
    public AddVehicleInteractor(AddVehicleService service) {
        this.service = service;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }

    @Override
    protected Observable buildUseCaseObservable(Vehicle data) {
        return service.getVehicleObservable(data);
    }
}
