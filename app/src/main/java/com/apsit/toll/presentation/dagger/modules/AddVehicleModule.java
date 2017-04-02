package com.apsit.toll.presentation.dagger.modules;

import android.content.Context;

import com.apsit.toll.data.network.service.SignInService;
import com.apsit.toll.data.network.service.VehicleDetailService;
import com.apsit.toll.data.storage.AddVehicleService;
import com.apsit.toll.domain.interactors.AddVehicleInteractor;
import com.apsit.toll.domain.interactors.SignInInteractor;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.domain.interactors.VehicleDetailInteractor;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.apsit.toll.presentation.presenter.AddVehiclePresenter;
import com.apsit.toll.presentation.presenter.SignInPresenter;
import com.google.gson.Gson;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityathanekar on 31/03/17.
 */

@Module
public class AddVehicleModule {

    @Provides
    @FragmentScope
    Retrofit provideRetrofitInstance(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("http://www.adisoftwares.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @FragmentScope
    VehicleDetailService provideVehicleDetailService(Retrofit instance) {
        return new VehicleDetailService(instance);
    }

    @Provides
    @FragmentScope
    @Named("detail")
    UseCase provideVehicleDetailInteractor(VehicleDetailService service) {
        return new VehicleDetailInteractor(service);
    }

    @Provides
    @FragmentScope
    AddVehicleService provideAddVehicleService() {
        return new AddVehicleService();
    }

    @Provides
    @FragmentScope
    @Named("add")
    UseCase provideAddVehicleInteractor(AddVehicleService service) {
        return new AddVehicleInteractor(service);
    }

    @Provides
    @FragmentScope
    AddVehiclePresenter provideAddVehiclePresenter(@Named("detail") UseCase vehicleDetailUseCase, @Named("add") UseCase addVehicleUseCase) {
        return new AddVehiclePresenter(vehicleDetailUseCase, addVehicleUseCase);
    }

}
