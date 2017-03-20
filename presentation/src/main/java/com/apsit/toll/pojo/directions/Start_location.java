
package com.apsit.toll.pojo.directions;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Start_location implements Parcelable
{

    private double lat;
    private double lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Start_location> CREATOR = new Creator<Start_location>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Start_location createFromParcel(Parcel in) {
            Start_location instance = new Start_location();
            instance.lat = ((double) in.readValue((double.class.getClassLoader())));
            instance.lng = ((double) in.readValue((double.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Start_location[] newArray(int size) {
            return (new Start_location[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Start_location() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public Start_location(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Start_location withLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Start_location withLng(double lng) {
        this.lng = lng;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Start_location withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}