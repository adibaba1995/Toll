package com.apsit.toll.domain.model;

/**
 * Created by adityathanekar on 23/03/17.
 */

public class DirectionData implements Data {
    private String origin, destination;

    public DirectionData(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {

        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
