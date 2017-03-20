package com.apsit.toll.dagger.components;


import com.apsit.toll.application.TollApplication;
import com.apsit.toll.dagger.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by adityathanekar on 15/06/16.
 */

@Component(modules = { ApplicationModule.class }) @Singleton
public interface ApplicationComponent {
    void inject(TollApplication target);
}
