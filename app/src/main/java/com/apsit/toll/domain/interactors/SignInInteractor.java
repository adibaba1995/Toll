package com.apsit.toll.domain.interactors;

import com.apsit.toll.data.network.pojo.register.Register;
import com.apsit.toll.data.network.service.DirectionService;
import com.apsit.toll.data.network.service.SignInService;
import com.apsit.toll.domain.model.Credentials;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class SignInInteractor extends UseCase<Credentials> {

    private SignInService service;

    @Inject
    public SignInInteractor(SignInService service) {
        this.service = service;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }

    @Override
    protected Observable buildUseCaseObservable(Credentials data) {
        return service.getDirectionObservable(data);
    }
}
