package com.apsit.toll.domain.interactors;

import com.apsit.toll.data.network.service.GooglePlaceAutocompleteService;
import com.apsit.toll.data.network.service.PaymentService;
import com.apsit.toll.domain.model.PaymentDetails;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by adityathanekar on 01/04/17.
 */

public  class PaymentInteractor extends UseCase<PaymentDetails> {

    private PaymentService service;

    @Inject
    public PaymentInteractor(PaymentService service) {
        this.service = service;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }


    @Override
    protected Observable buildUseCaseObservable(PaymentDetails details) {
        return service.getPaymentObservable(details);
    }
}
