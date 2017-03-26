package com.apsit.toll.presentation.dagger.components;

import com.apsit.toll.presentation.dagger.modules.DirectionModule;
import com.apsit.toll.presentation.dagger.modules.DisplayMapModule;
import com.apsit.toll.presentation.dagger.modules.GenericModule;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.apsit.toll.presentation.view.activity.DirectionActivity;
import com.apsit.toll.presentation.view.fragment.DisplayMapFragment;

import dagger.Subcomponent;

/**
 * Created by adityathanekar on 24/03/17.
 */

@FragmentScope
@Subcomponent(modules = { DisplayMapModule.class, GenericModule.class})
public interface DisplayMapComponent {
    void inject(DisplayMapFragment target);
}
