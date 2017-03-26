
package com.apsit.toll.data.network.pojo.directions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GeocodedWaypoint implements Parcelable
{

    private String geocoderStatus;
    private String placeId;
    private List<String> types = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<GeocodedWaypoint> CREATOR = new Creator<GeocodedWaypoint>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GeocodedWaypoint createFromParcel(Parcel in) {
            GeocodedWaypoint instance = new GeocodedWaypoint();
            instance.geocoderStatus = ((String) in.readValue((String.class.getClassLoader())));
            instance.placeId = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.types, (String.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public GeocodedWaypoint[] newArray(int size) {
            return (new GeocodedWaypoint[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GeocodedWaypoint() {
    }

    /**
     * 
     * @param geocoderStatus
     * @param placeId
     * @param types
     */
    public GeocodedWaypoint(String geocoderStatus, String placeId, List<String> types) {
        super();
        this.geocoderStatus = geocoderStatus;
        this.placeId = placeId;
        this.types = types;
    }

    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    public GeocodedWaypoint withGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
        return this;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public GeocodedWaypoint withPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public GeocodedWaypoint withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public GeocodedWaypoint withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(geocoderStatus);
        dest.writeValue(placeId);
        dest.writeList(types);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
