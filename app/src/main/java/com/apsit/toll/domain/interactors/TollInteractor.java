package com.apsit.toll.domain.interactors;

import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.data.network.service.DirectionService;
import com.apsit.toll.data.network.service.TollService;
import com.apsit.toll.domain.model.DirectionData;
import com.apsit.toll.domain.model.MinMaxLatLong;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by adityathanekar on 25/03/17.
 */

public class TollInteractor extends UseCase<MinMaxLatLong> {

    private TollService service;

    @Inject
    public TollInteractor(TollService service) {
        this.service = service;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }

    @Override
    protected Observable buildUseCaseObservable(MinMaxLatLong data) {
        return service.getTollsObservable(data);
    }
}
