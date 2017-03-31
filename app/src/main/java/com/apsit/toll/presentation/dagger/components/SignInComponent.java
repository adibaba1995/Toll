package com.apsit.toll.presentation.dagger.components;

import com.apsit.toll.presentation.dagger.modules.DisplayMapModule;
import com.apsit.toll.presentation.dagger.modules.GenericModule;
import com.apsit.toll.presentation.dagger.modules.SignInModule;
import com.apsit.toll.presentation.dagger.scopes.FragmentScope;
import com.apsit.toll.presentation.view.activity.LoginActivity;
import com.apsit.toll.presentation.view.fragment.DisplayMapFragment;

import dagger.Subcomponent;

/**
 * Created by adityathanekar on 31/03/17.
 */

@FragmentScope
@Subcomponent(modules = { SignInModule.class, GenericModule.class})
public interface SignInComponent {
    void inject(LoginActivity target);
}
