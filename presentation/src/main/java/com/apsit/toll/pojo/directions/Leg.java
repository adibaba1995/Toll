
package com.apsit.toll.pojo.directions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Leg implements Parcelable
{

    private Distance distance;
    private Duration duration;
    private String end_address;
    private End_location end_location;
    private String start_address;
    private Start_location start_location;
    private List<Step> steps = null;
    private List<Object> traffic_speed_entry = null;
    private List<Object> via_waypoint = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Leg> CREATOR = new Creator<Leg>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Leg createFromParcel(Parcel in) {
            Leg instance = new Leg();
            instance.distance = ((Distance) in.readValue((Distance.class.getClassLoader())));
            instance.duration = ((Duration) in.readValue((Duration.class.getClassLoader())));
            instance.end_address = ((String) in.readValue((String.class.getClassLoader())));
            instance.end_location = ((End_location) in.readValue((End_location.class.getClassLoader())));
            instance.start_address = ((String) in.readValue((String.class.getClassLoader())));
            instance.start_location = ((Start_location) in.readValue((Start_location.class.getClassLoader())));
            in.readList(instance.steps, (Step.class.getClassLoader()));
            in.readList(instance.traffic_speed_entry, (Object.class.getClassLoader()));
            in.readList(instance.via_waypoint, (Object.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Leg[] newArray(int size) {
            return (new Leg[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Leg() {
    }

    /**
     * 
     * @param duration
     * @param distance
     * @param end_location
     * @param start_address
     * @param end_address
     * @param start_location
     * @param traffic_speed_entry
     * @param via_waypoint
     * @param steps
     */
    public Leg(Distance distance, Duration duration, String end_address, End_location end_location, String start_address, Start_location start_location, List<Step> steps, List<Object> traffic_speed_entry, List<Object> via_waypoint) {
        super();
        this.distance = distance;
        this.duration = duration;
        this.end_address = end_address;
        this.end_location = end_location;
        this.start_address = start_address;
        this.start_location = start_location;
        this.steps = steps;
        this.traffic_speed_entry = traffic_speed_entry;
        this.via_waypoint = via_waypoint;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Leg withDistance(Distance distance) {
        this.distance = distance;
        return this;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Leg withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public Leg withEnd_address(String end_address) {
        this.end_address = end_address;
        return this;
    }

    public End_location getEnd_location() {
        return end_location;
    }

    public void setEnd_location(End_location end_location) {
        this.end_location = end_location;
    }

    public Leg withEnd_location(End_location end_location) {
        this.end_location = end_location;
        return this;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public Leg withStart_address(String start_address) {
        this.start_address = start_address;
        return this;
    }

    public Start_location getStart_location() {
        return start_location;
    }

    public void setStart_location(Start_location start_location) {
        this.start_location = start_location;
    }

    public Leg withStart_location(Start_location start_location) {
        this.start_location = start_location;
        return this;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Leg withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public List<Object> getTraffic_speed_entry() {
        return traffic_speed_entry;
    }

    public void setTraffic_speed_entry(List<Object> traffic_speed_entry) {
        this.traffic_speed_entry = traffic_speed_entry;
    }

    public Leg withTraffic_speed_entry(List<Object> traffic_speed_entry) {
        this.traffic_speed_entry = traffic_speed_entry;
        return this;
    }

    public List<Object> getVia_waypoint() {
        return via_waypoint;
    }

    public void setVia_waypoint(List<Object> via_waypoint) {
        this.via_waypoint = via_waypoint;
    }

    public Leg withVia_waypoint(List<Object> via_waypoint) {
        this.via_waypoint = via_waypoint;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Leg withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(distance);
        dest.writeValue(duration);
        dest.writeValue(end_address);
        dest.writeValue(end_location);
        dest.writeValue(start_address);
        dest.writeValue(start_location);
        dest.writeList(steps);
        dest.writeList(traffic_speed_entry);
        dest.writeList(via_waypoint);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
