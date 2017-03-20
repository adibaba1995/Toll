
package com.apsit.toll.pojo.directions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Geocoded_waypoint implements Parcelable
{

    private String geocoder_status;
    private String place_id;
    private List<String> types = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Geocoded_waypoint> CREATOR = new Creator<Geocoded_waypoint>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Geocoded_waypoint createFromParcel(Parcel in) {
            Geocoded_waypoint instance = new Geocoded_waypoint();
            instance.geocoder_status = ((String) in.readValue((String.class.getClassLoader())));
            instance.place_id = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.types, (String.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Geocoded_waypoint[] newArray(int size) {
            return (new Geocoded_waypoint[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Geocoded_waypoint() {
    }

    /**
     * 
     * @param place_id
     * @param geocoder_status
     * @param types
     */
    public Geocoded_waypoint(String geocoder_status, String place_id, List<String> types) {
        super();
        this.geocoder_status = geocoder_status;
        this.place_id = place_id;
        this.types = types;
    }

    public String getGeocoder_status() {
        return geocoder_status;
    }

    public void setGeocoder_status(String geocoder_status) {
        this.geocoder_status = geocoder_status;
    }

    public Geocoded_waypoint withGeocoder_status(String geocoder_status) {
        this.geocoder_status = geocoder_status;
        return this;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public Geocoded_waypoint withPlace_id(String place_id) {
        this.place_id = place_id;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Geocoded_waypoint withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Geocoded_waypoint withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(geocoder_status);
        dest.writeValue(place_id);
        dest.writeList(types);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
