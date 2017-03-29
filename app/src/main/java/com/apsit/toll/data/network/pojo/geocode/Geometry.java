
package com.apsit.toll.data.network.pojo.geocode;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Geometry implements Parcelable
{

    private Bounds bounds;
    private Location location;
    private String locationType;
    private Viewport viewport;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Geometry> CREATOR = new Creator<Geometry>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Geometry createFromParcel(Parcel in) {
            Geometry instance = new Geometry();
            instance.bounds = ((Bounds) in.readValue((Bounds.class.getClassLoader())));
            instance.location = ((Location) in.readValue((Location.class.getClassLoader())));
            instance.locationType = ((String) in.readValue((String.class.getClassLoader())));
            instance.viewport = ((Viewport) in.readValue((Viewport.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Geometry[] newArray(int size) {
            return (new Geometry[size]);
        }

    }
    ;

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Geometry withBounds(Bounds bounds) {
        this.bounds = bounds;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Geometry withLocation(Location location) {
        this.location = location;
        return this;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Geometry withLocationType(String locationType) {
        this.locationType = locationType;
        return this;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public Geometry withViewport(Viewport viewport) {
        this.viewport = viewport;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Geometry withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bounds);
        dest.writeValue(location);
        dest.writeValue(locationType);
        dest.writeValue(viewport);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
