package com.apsit.toll.presentation.application;

import android.app.Application;

import com.apsit.toll.data.network.sync.TollSyncAdapter;
import com.apsit.toll.presentation.dagger.components.AddVehicleComponent;
import com.apsit.toll.presentation.dagger.components.ApplicationComponent;
import com.apsit.toll.presentation.dagger.components.DaggerApplicationComponent;
import com.apsit.toll.presentation.dagger.components.DirectionComponent;
import com.apsit.toll.presentation.dagger.components.DisplayMapComponent;
import com.apsit.toll.presentation.dagger.components.SignInComponent;
import com.apsit.toll.presentation.dagger.modules.AddVehicleModule;
import com.apsit.toll.presentation.dagger.modules.ApplicationModule;
import com.apsit.toll.presentation.dagger.modules.DirectionModule;
import com.apsit.toll.presentation.dagger.modules.DisplayMapModule;
import com.apsit.toll.presentation.dagger.modules.SignInModule;


/**
 * Created by adityathanekar on 27/06/16.
 */

public final class TollApplication extends Application {
    private ApplicationComponent applicationComponent;
    private DirectionComponent directionComponent;
    private DisplayMapComponent displayMapComponent;
    private SignInComponent signInComponent;
    private AddVehicleComponent addVehicleComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
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

    public SignInComponent createSignInComponent() {
        signInComponent =  applicationComponent.plus(new SignInModule());
        return signInComponent;
    }

    public void releaseSignInComponent() {
        signInComponent = null;
    }

    public AddVehicleComponent createAddVehicleComponent() {
        addVehicleComponent =  applicationComponent.plus(new AddVehicleModule());
        return addVehicleComponent;
    }

    public void releaseAddVehicleComponent() {
        addVehicleComponent = null;
    }

}
