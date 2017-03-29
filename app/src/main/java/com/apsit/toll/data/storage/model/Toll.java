package com.apsit.toll.data.storage.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by adityathanekar on 23/03/17.
 */

public class Toll extends RealmObject {

    @PrimaryKey
    private long id;
    private String name, state, country, address, place_id;
    private double two_axle, lcv, two_axle_heavy, upto_three_axle, four_axle_more, latitude, longitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public double getTwo_axle() {
        return two_axle;
    }

    public void setTwo_axle(double two_axle) {
        this.two_axle = two_axle;
    }

    public double getLcv() {
        return lcv;
    }

    public void setLcv(double lcv) {
        this.lcv = lcv;
    }

    public double getTwo_axle_heavy() {
        return two_axle_heavy;
    }

    public void setTwo_axle_heavy(double two_axle_heavy) {
        this.two_axle_heavy = two_axle_heavy;
    }

    public double getUpto_three_axle() {
        return upto_three_axle;
    }

    public void setUpto_three_axle(double upto_three_axle) {
        this.upto_three_axle = upto_three_axle;
    }

    public double getFour_axle_more() {
        return four_axle_more;
    }

    public void setFour_axle_more(double four_axle_more) {
        this.four_axle_more = four_axle_more;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

