package com.apsit.toll.presentation.dagger.modules;

import android.content.Context;

import com.apsit.toll.data.network.service.GooglePlaceAutocompleteService;
import com.apsit.toll.data.network.service.SignInService;
import com.apsit.toll.domain.interactors.AutocompleteInteractor;
import com.apsit.toll.domain.interactors.SignInInteractor;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.apsit.toll.presentation.presenter.DisplayMapPresenter;
import com.apsit.toll.presentation.presenter.SignInPresenter;
import com.google.gson.Gson;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityathanekar on 31/03/17.
 */

@Module
public class SignInModule {
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
    SignInService provideSignInService(Retrofit instance) {
        return new SignInService(instance);
    }

    @Provides
    @FragmentScope
    UseCase provideSignInInteractor(SignInService service) {
        return new SignInInteractor(service);
    }

    @Provides
    @FragmentScope
    SignInPresenter provideSignInPresenter(UseCase signInUseCase, Context context) {
        return new SignInPresenter(signInUseCase, context);
    }
}
