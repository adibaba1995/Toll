package com.apsit.toll.presentation.dagger.modules;

import android.content.Context;

import com.apsit.toll.data.network.service.DirectionService;
import com.apsit.toll.data.network.service.GooglePlaceAutocompleteService;
import com.apsit.toll.data.network.service.TollService;
import com.apsit.toll.domain.interactors.AutocompleteInteractor;
import com.apsit.toll.domain.interactors.DirectionInteractor;
import com.apsit.toll.domain.interactors.TollInteractor;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.apsit.toll.presentation.presenter.DisplayMapPresenter;
import com.google.gson.Gson;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityathanekar on 24/03/17.
 */

@Module
public class DisplayMapModule {

    @Provides
    @FragmentScope
    @Named("direction")
    Retrofit provideRetrofitDirectionInstance(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @FragmentScope
    @Named("toll")
    Retrofit provideRetrofitTollInstance(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.43.151/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @FragmentScope
    DirectionService provideDirectionService(@Named("direction") Retrofit instance, Context context) {
        return new DirectionService(instance, context);
    }

    @Provides
    @FragmentScope
    TollService provideTollService(@Named("toll") Retrofit instance) {
        return new TollService(instance);
    }

    @Provides
    @FragmentScope
    @Named("directionUsecase")
    UseCase provideDirectionInteractor(DirectionService service) {
        return new DirectionInteractor(service);
    }

    @Provides
    @FragmentScope
    @Named("tollUsecase")
    UseCase provideTollInteractor(TollService service) {
        return new TollInteractor(service);
    }

    @Provides
    @FragmentScope
    DisplayMapPresenter provideDisplayMapPresenter(@Named("directionUsecase") UseCase directionUseCase,@Named("tollUsecase") UseCase tollUseCase) {
        return new DisplayMapPresenter(directionUseCase, tollUseCase);
    }
}
