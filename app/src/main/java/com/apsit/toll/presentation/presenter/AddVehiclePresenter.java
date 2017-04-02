package com.apsit.toll.presentation.presenter;

import com.apsit.toll.data.network.pojo.vehicle.Vehicle;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.presentation.view.AddVehicleView;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class AddVehiclePresenter extends BasePresenter<AddVehicleView> {

    private UseCase getDetailUseCase, addVehicleUseCase;

    @Inject
    public AddVehiclePresenter(UseCase getDetailUseCase, UseCase addVehicleUseCase) {
        this.getDetailUseCase = getDetailUseCase;
        this.addVehicleUseCase = addVehicleUseCase;
    }

    public void getVehicleDetails(String vehicle_no) {
        getDetailUseCase.executeWithInput(new VehicleDetailObservable(), vehicle_no);
    }

    public void addVehicle(Vehicle vehicle) {
        addVehicleUseCase.executeWithInput(new AddVehicleObservable(), vehicle);
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

    private final class AddVehicleObservable implements Consumer<Boolean> {

        @Override
        public void accept(Boolean status) throws Exception {
            AddVehicleView view = getView().get();
            if(view != null && status) {
                view.showAddVehicleSuccess();
            }
        }
    }

}
