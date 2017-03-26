
package com.apsit.toll.data.network.pojo.directions;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Step implements Parcelable
{

    private Distance_ distance;
    private Duration_ duration;
    private EndLocation_ endLocation;
    private String htmlInstructions;
    private Polyline polyline;
    private StartLocation_ startLocation;
    private String travelMode;
    private String maneuver;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Step> CREATOR = new Creator<Step>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Step createFromParcel(Parcel in) {
            Step instance = new Step();
            instance.distance = ((Distance_) in.readValue((Distance_.class.getClassLoader())));
            instance.duration = ((Duration_) in.readValue((Duration_.class.getClassLoader())));
            instance.endLocation = ((EndLocation_) in.readValue((EndLocation_.class.getClassLoader())));
            instance.htmlInstructions = ((String) in.readValue((String.class.getClassLoader())));
            instance.polyline = ((Polyline) in.readValue((Polyline.class.getClassLoader())));
            instance.startLocation = ((StartLocation_) in.readValue((StartLocation_.class.getClassLoader())));
            instance.travelMode = ((String) in.readValue((String.class.getClassLoader())));
            instance.maneuver = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Step[] newArray(int size) {
            return (new Step[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Step() {
    }

    /**
     * 
     * @param duration
     * @param distance
     * @param polyline
     * @param endLocation
     * @param htmlInstructions
     * @param startLocation
     * @param maneuver
     * @param travelMode
     */
    public Step(Distance_ distance, Duration_ duration, EndLocation_ endLocation, String htmlInstructions, Polyline polyline, StartLocation_ startLocation, String travelMode, String maneuver) {
        super();
        this.distance = distance;
        this.duration = duration;
        this.endLocation = endLocation;
        this.htmlInstructions = htmlInstructions;
        this.polyline = polyline;
        this.startLocation = startLocation;
        this.travelMode = travelMode;
        this.maneuver = maneuver;
    }

    public Distance_ getDistance() {
        return distance;
    }

    public void setDistance(Distance_ distance) {
        this.distance = distance;
    }

    public Step withDistance(Distance_ distance) {
        this.distance = distance;
        return this;
    }

    public Duration_ getDuration() {
        return duration;
    }

    public void setDuration(Duration_ duration) {
        this.duration = duration;
    }

    public Step withDuration(Duration_ duration) {
        this.duration = duration;
        return this;
    }

    public EndLocation_ getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation_ endLocation) {
        this.endLocation = endLocation;
    }

    public Step withEndLocation(EndLocation_ endLocation) {
        this.endLocation = endLocation;
        return this;
    }

    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public Step withHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
        return this;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Step withPolyline(Polyline polyline) {
        this.polyline = polyline;
        return this;
    }

    public StartLocation_ getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation_ startLocation) {
        this.startLocation = startLocation;
    }

    public Step withStartLocation(StartLocation_ startLocation) {
        this.startLocation = startLocation;
        return this;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public Step withTravelMode(String travelMode) {
        this.travelMode = travelMode;
        return this;
    }

    public String getManeuver() {
        return maneuver;
    }

    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

    public Step withManeuver(String maneuver) {
        this.maneuver = maneuver;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Step withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(distance);
        dest.writeValue(duration);
        dest.writeValue(endLocation);
        dest.writeValue(htmlInstructions);
        dest.writeValue(polyline);
        dest.writeValue(startLocation);
        dest.writeValue(travelMode);
        dest.writeValue(maneuver);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
