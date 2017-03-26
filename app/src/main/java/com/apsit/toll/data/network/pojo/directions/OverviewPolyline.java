
package com.apsit.toll.data.network.pojo.directions;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class OverviewPolyline implements Parcelable
{

    private String points;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<OverviewPolyline> CREATOR = new Creator<OverviewPolyline>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OverviewPolyline createFromParcel(Parcel in) {
            OverviewPolyline instance = new OverviewPolyline();
            instance.points = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public OverviewPolyline[] newArray(int size) {
            return (new OverviewPolyline[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OverviewPolyline() {
    }

    /**
     * 
     * @param points
     */
    public OverviewPolyline(String points) {
        super();
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public OverviewPolyline withPoints(String points) {
        this.points = points;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public OverviewPolyline withAdditionalProperty(String name, Object value) {
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
