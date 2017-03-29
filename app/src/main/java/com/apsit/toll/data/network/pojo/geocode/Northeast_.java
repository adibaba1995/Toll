
package com.apsit.toll.data.network.pojo.geocode;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Northeast_ implements Parcelable
{

    private double lat;
    private double lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Northeast_> CREATOR = new Creator<Northeast_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Northeast_ createFromParcel(Parcel in) {
            Northeast_ instance = new Northeast_();
            instance.lat = ((double) in.readValue((double.class.getClassLoader())));
            instance.lng = ((double) in.readValue((double.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Northeast_[] newArray(int size) {
            return (new Northeast_[size]);
        }

    }
    ;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Northeast_ withLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Northeast_ withLng(double lng) {
        this.lng = lng;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Northeast_ withAdditionalProperty(String name, Object value) {
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
