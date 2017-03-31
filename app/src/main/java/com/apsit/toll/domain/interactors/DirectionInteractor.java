package com.apsit.toll.domain.interactors;

import android.util.Log;

import com.apsit.toll.data.network.service.DirectionService;
import com.apsit.toll.domain.executor.PostExecutionThread;
import com.apsit.toll.domain.executor.ThreadExecutor;
import com.apsit.toll.domain.model.DirectionData;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by adityathanekar on 23/03/17.
 */

public class DirectionInteractor extends UseCase<DirectionData> {

    private DirectionService service;

    @Inject
    public DirectionInteractor(DirectionService service) {
        this.service = service;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }

    @Override
    protected Observable buildUseCaseObservable(DirectionData data) {
        return service.getDirectionObservable(data.getOrigin(), data.getDestination());
    }
}
