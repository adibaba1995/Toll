package com.apsit.toll.presentation.application;

import android.app.Application;

import com.apsit.toll.data.network.sync.TollSyncAdapter;
import com.apsit.toll.presentation.dagger.components.ApplicationComponent;
import com.apsit.toll.presentation.dagger.components.DaggerApplicationComponent;
import com.apsit.toll.presentation.dagger.components.DirectionComponent;
import com.apsit.toll.presentation.dagger.components.DisplayMapComponent;
import com.apsit.toll.presentation.dagger.modules.ApplicationModule;
import com.apsit.toll.presentation.dagger.modules.DirectionModule;
import com.apsit.toll.presentation.dagger.modules.DisplayMapModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by adityathanekar on 27/06/16.
 */

public final class TollApplication extends Application {
    private ApplicationComponent applicationComponent;
    private DirectionComponent directionComponent;
    private DisplayMapComponent displayMapComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("toll.realm")
                .build();

        // Make this Realm the default
        Realm.setDefaultConfiguration(realmConfiguration);
        TollSyncAdapter.initializeSyncAdapter(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public DirectionComponent createSelectLocationComponent() {
        directionComponent =  applicationComponent.plus(new DirectionModule());
        return directionComponent;
    }

    public void releaseSelectLocationComponent() {
        directionComponent = null;
    }

    public DisplayMapComponent createDisplayMapComponent() {
        displayMapComponent =  applicationComponent.plus(new DisplayMapModule());
        return displayMapComponent;
    }

    public void releaseDisplayMapComponent() {
        displayMapComponent = null;
    }

}
