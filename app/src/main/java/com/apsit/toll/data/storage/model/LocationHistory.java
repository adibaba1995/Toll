package com.apsit.toll.data.storage.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by adityathanekar on 23/03/17.
 */

public class LocationHistory extends RealmObject {

    @PrimaryKey
    private long id;
    @Required
    private String location;
    private String address;

    public LocationHistory() {

    }

    public LocationHistory(long id, String location) {
        this(id, location, null);
    }

    public LocationHistory(long id, String location, String address) {
        this.id = id;
        this.location = location;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

