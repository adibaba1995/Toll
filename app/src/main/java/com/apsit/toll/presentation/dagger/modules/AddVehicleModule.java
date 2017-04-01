package com.apsit.toll.presentation.dagger.modules;

import android.content.Context;

import com.apsit.toll.data.network.service.SignInService;
import com.apsit.toll.data.network.service.VehicleDetailService;
import com.apsit.toll.domain.interactors.SignInInteractor;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.domain.interactors.VehicleDetailInteractor;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.apsit.toll.presentation.presenter.AddVehiclePresenter;
import com.apsit.toll.presentation.presenter.SignInPresenter;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
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
    UseCase provideVehicleDetailInteractor(VehicleDetailService service) {
        return new VehicleDetailInteractor(service);
    }

    @Provides
    @FragmentScope
    AddVehiclePresenter provideVehicleDetailPresenter(UseCase signInUseCase) {
        return new AddVehiclePresenter(signInUseCase);
    }

}
