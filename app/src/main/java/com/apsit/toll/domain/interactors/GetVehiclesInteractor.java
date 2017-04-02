package com.apsit.toll.domain.interactors;

import com.apsit.toll.data.network.service.VehicleDetailService;
import com.apsit.toll.data.storage.GetVehicleService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by adityathanekar on 02/04/17.
 */

public class GetVehiclesInteractor extends UseCase<Void> {

    private GetVehicleService service;

    @Inject
    public GetVehiclesInteractor(GetVehicleService service) {
        this.service = service;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return service.getVehicleObservable();
    }

    @Override
    protected Observable buildUseCaseObservable(Void data) {
        return null;
    }
}