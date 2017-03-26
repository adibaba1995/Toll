
package com.apsit.toll.data.network.pojo.directions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Route implements Parcelable
{

    private Bounds bounds;
    private String copyrights;
    private List<Leg> legs = null;
    private OverviewPolyline overviewPolyline;
    private String summary;
    private List<Object> warnings = null;
    private List<Object> waypointOrder = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Route> CREATOR = new Creator<Route>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Route createFromParcel(Parcel in) {
            Route instance = new Route();
            instance.bounds = ((Bounds) in.readValue((Bounds.class.getClassLoader())));
            instance.copyrights = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.legs, (Leg.class.getClassLoader()));
            instance.overviewPolyline = ((OverviewPolyline) in.readValue((OverviewPolyline.class.getClassLoader())));
            instance.summary = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.warnings, (Object.class.getClassLoader()));
            in.readList(instance.waypointOrder, (Object.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Route[] newArray(int size) {
            return (new Route[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Route() {
    }

    /**
     * 
     * @param waypointOrder
     * @param summary
     * @param bounds
     * @param copyrights
     * @param legs
     * @param warnings
     * @param overviewPolyline
     */
    public Route(Bounds bounds, String copyrights, List<Leg> legs, OverviewPolyline overviewPolyline, String summary, List<Object> warnings, List<Object> waypointOrder) {
        super();
        this.bounds = bounds;
        this.copyrights = copyrights;
        this.legs = legs;
        this.overviewPolyline = overviewPolyline;
        this.summary = summary;
        this.warnings = warnings;
        this.waypointOrder = waypointOrder;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Route withBounds(Bounds bounds) {
        this.bounds = bounds;
        return this;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public Route withCopyrights(String copyrights) {
        this.copyrights = copyrights;
        return this;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public Route withLegs(List<Leg> legs) {
        this.legs = legs;
        return this;
    }

    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public Route withOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Route withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public Route withWarnings(List<Object> warnings) {
        this.warnings = warnings;
        return this;
    }

    public List<Object> getWaypointOrder() {
        return waypointOrder;
    }

    public void setWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

    public Route withWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Route withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bounds);
        dest.writeValue(copyrights);
        dest.writeList(legs);
        dest.writeValue(overviewPolyline);
        dest.writeValue(summary);
        dest.writeList(warnings);
        dest.writeList(waypointOrder);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
