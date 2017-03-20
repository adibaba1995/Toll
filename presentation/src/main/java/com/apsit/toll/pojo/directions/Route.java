
package com.apsit.toll.pojo.directions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Route implements Parcelable
{

    private Bounds bounds;
    private String copyrights;
    private List<Leg> legs = null;
    private Overview_polyline overview_polyline;
    private String summary;
    private List<Object> warnings = null;
    private List<Object> waypoint_order = null;
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
            instance.overview_polyline = ((Overview_polyline) in.readValue((Overview_polyline.class.getClassLoader())));
            instance.summary = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.warnings, (Object.class.getClassLoader()));
            in.readList(instance.waypoint_order, (Object.class.getClassLoader()));
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
     * @param summary
     * @param bounds
     * @param copyrights
     * @param waypoint_order
     * @param legs
     * @param warnings
     * @param overview_polyline
     */
    public Route(Bounds bounds, String copyrights, List<Leg> legs, Overview_polyline overview_polyline, String summary, List<Object> warnings, List<Object> waypoint_order) {
        super();
        this.bounds = bounds;
        this.copyrights = copyrights;
        this.legs = legs;
        this.overview_polyline = overview_polyline;
        this.summary = summary;
        this.warnings = warnings;
        this.waypoint_order = waypoint_order;
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

    public Overview_polyline getOverview_polyline() {
        return overview_polyline;
    }

    public void setOverview_polyline(Overview_polyline overview_polyline) {
        this.overview_polyline = overview_polyline;
    }

    public Route withOverview_polyline(Overview_polyline overview_polyline) {
        this.overview_polyline = overview_polyline;
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

    public List<Object> getWaypoint_order() {
        return waypoint_order;
    }

    public void setWaypoint_order(List<Object> waypoint_order) {
        this.waypoint_order = waypoint_order;
    }

    public Route withWaypoint_order(List<Object> waypoint_order) {
        this.waypoint_order = waypoint_order;
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
        dest.writeValue(overview_polyline);
        dest.writeValue(summary);
        dest.writeList(warnings);
        dest.writeList(waypoint_order);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
