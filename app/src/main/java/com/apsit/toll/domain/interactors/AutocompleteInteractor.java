package com.apsit.toll.domain.interactors;

import com.apsit.toll.data.network.service.GooglePlaceAutocompleteService;

import io.reactivex.Observable;

/**
 * Created by adityathanekar on 16/03/17.
 */

public  class AutocompleteInteractor extends UseCase<String> {

    private GooglePlaceAutocompleteService service;

    public AutocompleteInteractor(GooglePlaceAutocompleteService service) {
        this.service = service;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }


    @Override
    protected Observable buildUseCaseObservable(String input) {
        return service.getAutocompleteObservable(input);
    }
}
