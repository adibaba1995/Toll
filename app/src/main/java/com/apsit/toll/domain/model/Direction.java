package com.apsit.toll.domain.model;

import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Created by adityathanekar on 23/03/17.
 */

public class Direction {
    private PolylineOptions polylineOption;
    private MinMaxLatLong minMaxLatLong;
    private long distance;

    public Direction(PolylineOptions option, MinMaxLatLong minMaxLatLong, long distance) {
        this.polylineOption = option;
        this.minMaxLatLong = minMaxLatLong;
        this.distance = distance;
    }

    public PolylineOptions getPolylineOption() {
        return polylineOption;
    }

    public void setPolylineOption(PolylineOptions polylineOption) {
        this.polylineOption = polylineOption;
    }

    public MinMaxLatLong getMinMaxLatLong() {
        return minMaxLatLong;
    }

    public void setMinMaxLatLong(MinMaxLatLong minMaxLatLong) {
        this.minMaxLatLong = minMaxLatLong;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}

