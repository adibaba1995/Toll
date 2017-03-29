
package com.apsit.toll.data.network.pojo.geocode;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Viewport implements Parcelable
{

    private Northeast_ northeast;
    private Southwest_ southwest;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Viewport> CREATOR = new Creator<Viewport>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Viewport createFromParcel(Parcel in) {
            Viewport instance = new Viewport();
            instance.northeast = ((Northeast_) in.readValue((Northeast_.class.getClassLoader())));
            instance.southwest = ((Southwest_) in.readValue((Southwest_.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Viewport[] newArray(int size) {
            return (new Viewport[size]);
        }

    }
    ;

    public Northeast_ getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast_ northeast) {
        this.northeast = northeast;
    }

    public Viewport withNortheast(Northeast_ northeast) {
        this.northeast = northeast;
        return this;
    }

    public Southwest_ getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest_ southwest) {
        this.southwest = southwest;
    }

    public Viewport withSouthwest(Southwest_ southwest) {
        this.southwest = southwest;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Viewport withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(northeast);
        dest.writeValue(southwest);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
