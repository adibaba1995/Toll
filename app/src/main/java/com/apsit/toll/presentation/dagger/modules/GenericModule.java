package com.apsit.toll.presentation.dagger.modules;

import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adityathanekar on 23/03/17.
 */

@Module
public class GenericModule {
    @Provides
    @FragmentScope
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }
}
