package com.apsit.toll.presentation.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.apsit.toll.data.network.pojo.login.Login;
import com.apsit.toll.data.network.pojo.vehicle.Vehicle;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.domain.model.Credentials;
import com.apsit.toll.presentation.view.AddVehicleView;
import com.apsit.toll.presentation.view.SignInView;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class AddVehiclePresenter extends BasePresenter<AddVehicleView> {

    private UseCase useCase;

    @Inject
    public AddVehiclePresenter(UseCase useCase) {
        this.useCase = useCase;
    }

    public void getVehicleDetails(String vehicle_no) {
        useCase.executeWithInput(new VehicleDetailObservable(), vehicle_no);
    }

    private final class VehicleDetailObservable implements Consumer<Vehicle> {

        @Override
        public void accept(Vehicle vehicle) throws Exception {
            AddVehicleView view = getView().get();
            if(view != null) {
                view.showVehicleDetails(vehicle);
            }
        }
    }

}
