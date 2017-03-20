package com.apsit.toll.application;

import android.app.Application;

import com.apsit.toll.dagger.components.ApplicationComponent;
import com.apsit.toll.dagger.components.DaggerApplicationComponent;
import com.apsit.toll.dagger.modules.ApplicationModule;


/**
 * Created by adityathanekar on 27/06/16.
 */

public final class TollApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
