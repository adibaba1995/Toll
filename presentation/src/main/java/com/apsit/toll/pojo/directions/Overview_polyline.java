
package com.apsit.toll.pojo.directions;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Overview_polyline implements Parcelable
{

    private String points;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Overview_polyline> CREATOR = new Creator<Overview_polyline>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Overview_polyline createFromParcel(Parcel in) {
            Overview_polyline instance = new Overview_polyline();
            instance.points = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Overview_polyline[] newArray(int size) {
            return (new Overview_polyline[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Overview_polyline() {
    }

    /**
     * 
     * @param points
     */
    public Overview_polyline(String points) {
        super();
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Overview_polyline withPoints(String points) {
        this.points = points;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Overview_polyline withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(points);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
