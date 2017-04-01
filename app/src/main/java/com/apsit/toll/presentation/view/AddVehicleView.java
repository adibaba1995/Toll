package com.apsit.toll.presentation.view;

import com.apsit.toll.data.network.pojo.vehicle.Vehicle;

/**
 * Created by adityathanekar on 31/03/17.
 */

public interface AddVehicleView extends BaseView {
    void showVehicleDetails(Vehicle vehicle);
}
