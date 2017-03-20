
package com.apsit.toll.pojo.directions;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable
{

    private Distance_ distance;
    private Duration_ duration;
    private End_location_ end_location;
    private String html_instructions;
    private Polyline polyline;
    private Start_location_ start_location;
    private String travel_mode;
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
            instance.end_location = ((End_location_) in.readValue((End_location_.class.getClassLoader())));
            instance.html_instructions = ((String) in.readValue((String.class.getClassLoader())));
            instance.polyline = ((Polyline) in.readValue((Polyline.class.getClassLoader())));
            instance.start_location = ((Start_location_) in.readValue((Start_location_.class.getClassLoader())));
            instance.travel_mode = ((String) in.readValue((String.class.getClassLoader())));
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
     * @param html_instructions
     * @param duration
     * @param distance
     * @param end_location
     * @param polyline
     * @param start_location
     * @param maneuver
     * @param travel_mode
     */
    public Step(Distance_ distance, Duration_ duration, End_location_ end_location, String html_instructions, Polyline polyline, Start_location_ start_location, String travel_mode, String maneuver) {
        super();
        this.distance = distance;
        this.duration = duration;
        this.end_location = end_location;
        this.html_instructions = html_instructions;
        this.polyline = polyline;
        this.start_location = start_location;
        this.travel_mode = travel_mode;
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

    public End_location_ getEnd_location() {
        return end_location;
    }

    public void setEnd_location(End_location_ end_location) {
        this.end_location = end_location;
    }

    public Step withEnd_location(End_location_ end_location) {
        this.end_location = end_location;
        return this;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    public Step withHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
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

    public Start_location_ getStart_location() {
        return start_location;
    }

    public void setStart_location(Start_location_ start_location) {
        this.start_location = start_location;
    }

    public Step withStart_location(Start_location_ start_location) {
        this.start_location = start_location;
        return this;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    public Step withTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
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
        dest.writeValue(end_location);
        dest.writeValue(html_instructions);
        dest.writeValue(polyline);
        dest.writeValue(start_location);
        dest.writeValue(travel_mode);
        dest.writeValue(maneuver);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
