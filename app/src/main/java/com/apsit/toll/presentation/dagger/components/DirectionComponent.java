package com.apsit.toll.presentation.dagger.components;

import com.apsit.toll.presentation.dagger.modules.DirectionModule;
import com.apsit.toll.presentation.dagger.modules.GenericModule;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.apsit.toll.presentation.view.activity.DirectionActivity;

import dagger.Subcomponent;

/**
 * Created by adityathanekar on 23/03/17.
 */

@FragmentScope
@Subcomponent(modules = { DirectionModule.class, GenericModule.class})
public interface DirectionComponent {
    void inject(DirectionActivity target);
}
