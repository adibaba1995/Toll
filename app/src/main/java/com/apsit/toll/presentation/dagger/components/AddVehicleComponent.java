package com.apsit.toll.presentation.dagger.components;

import com.apsit.toll.presentation.dagger.modules.AddVehicleModule;
import com.apsit.toll.presentation.dagger.modules.GenericModule;
import com.apsit.toll.presentation.dagger.modules.SignInModule;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.apsit.toll.presentation.view.activity.AddVehicleActivity;
import com.apsit.toll.presentation.view.activity.LoginActivity;

import dagger.Subcomponent;

/**
 * Created by adityathanekar on 31/03/17.
 */

@FragmentScope
@Subcomponent(modules = { AddVehicleModule.class, GenericModule.class})
public interface AddVehicleComponent {
    void inject(AddVehicleActivity target);
}
