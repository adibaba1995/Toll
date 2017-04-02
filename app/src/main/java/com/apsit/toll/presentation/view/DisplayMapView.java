package com.apsit.toll.presentation.view;

import com.apsit.toll.data.network.pojo.autocomplete.Prediction;
import com.apsit.toll.data.network.pojo.payment.Payment;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.data.network.pojo.vehicle.Vehicle;
import com.apsit.toll.domain.model.Direction;

import java.util.List;

/**
 * Created by adityathanekar on 24/03/17.
 */

public interface DisplayMapView extends BaseView{
    void setPath(List<Direction> directions);
    void setTolls(List<Toll> tolls);
    void showPaymentStatus(Payment payment);
    void showVehicles(List<Vehicle> vehicles);
}
