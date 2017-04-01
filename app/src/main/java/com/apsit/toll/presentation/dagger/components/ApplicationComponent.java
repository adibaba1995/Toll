package com.apsit.toll.presentation.dagger.components;

import com.apsit.toll.presentation.application.TollApplication;
import com.apsit.toll.presentation.dagger.modules.AddVehicleModule;
import com.apsit.toll.presentation.dagger.modules.ApplicationModule;
import com.apsit.toll.presentation.dagger.modules.DirectionModule;
import com.apsit.toll.presentation.dagger.modules.DisplayMapModule;
import com.apsit.toll.presentation.dagger.modules.SignInModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by adityathanekar on 15/06/16.
 */

@Component(modules = { ApplicationModule.class }) @Singleton
public interface ApplicationComponent {
    void inject(TollApplication target);
    DirectionComponent plus(DirectionModule module);
    DisplayMapComponent plus(DisplayMapModule module);
    SignInComponent plus(SignInModule module);
    AddVehicleComponent plus(AddVehicleModule module);
}
