package com.apsit.toll.domain.model;

import com.apsit.toll.data.network.pojo.toll.Toll;

import java.util.List;

/**
 * Created by adityathanekar on 01/04/17.
 */

public class PaymentDetails {

    private String vehicle_no;
    private List<Toll> tollList;

    public PaymentDetails(String vehicle_no, List<Toll> tollList) {
        this.vehicle_no = vehicle_no;
        this.tollList = tollList;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public List<Toll> getTollList() {
        return tollList;
    }

    public void setTollList(List<Toll> tollList) {
        this.tollList = tollList;
    }
}
