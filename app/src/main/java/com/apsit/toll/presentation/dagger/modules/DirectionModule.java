package com.apsit.toll.presentation.dagger.modules;

import com.apsit.toll.data.network.service.GooglePlaceAutocompleteService;
import com.apsit.toll.domain.interactors.AutocompleteInteractor;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityathanekar on 22/03/17.
 */

@Module
public class DirectionModule {

    @Provides
    @FragmentScope
    Retrofit provideRetrofitInstance(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @FragmentScope
    GooglePlaceAutocompleteService provideAutocompleteService(Retrofit instance) {
        return new GooglePlaceAutocompleteService(instance);
    }

    @Provides
    @FragmentScope
    UseCase provideAutocompleteInteractor(GooglePlaceAutocompleteService service) {
        return new AutocompleteInteractor(service);
    }

}
