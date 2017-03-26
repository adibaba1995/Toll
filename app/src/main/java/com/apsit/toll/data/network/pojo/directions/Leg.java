
package com.apsit.toll.data.network.pojo.directions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Leg implements Parcelable
{

    private Distance distance;
    private Duration duration;
    private String endAddress;
    private EndLocation endLocation;
    private String startAddress;
    private StartLocation startLocation;
    private List<Step> steps = null;
    private List<Object> trafficSpeedEntry = null;
    private List<Object> viaWaypoint = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Leg> CREATOR = new Creator<Leg>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Leg createFromParcel(Parcel in) {
            Leg instance = new Leg();
            instance.distance = ((Distance) in.readValue((Distance.class.getClassLoader())));
            instance.duration = ((Duration) in.readValue((Duration.class.getClassLoader())));
            instance.endAddress = ((String) in.readValue((String.class.getClassLoader())));
            instance.endLocation = ((EndLocation) in.readValue((EndLocation.class.getClassLoader())));
            instance.startAddress = ((String) in.readValue((String.class.getClassLoader())));
            instance.startLocation = ((StartLocation) in.readValue((StartLocation.class.getClassLoader())));
            in.readList(instance.steps, (com.apsit.toll.data.network.pojo.directions.Step.class.getClassLoader()));
            in.readList(instance.trafficSpeedEntry, (Object.class.getClassLoader()));
            in.readList(instance.viaWaypoint, (Object.class.getClassLoader()));
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
     * @param startAddress
     * @param duration
     * @param distance
     * @param trafficSpeedEntry
     * @param endLocation
     * @param startLocation
     * @param steps
     * @param endAddress
     * @param viaWaypoint
     */
    public Leg(Distance distance, Duration duration, String endAddress, EndLocation endLocation, String startAddress, StartLocation startLocation, List<Step> steps, List<Object> trafficSpeedEntry, List<Object> viaWaypoint) {
        super();
        this.distance = distance;
        this.duration = duration;
        this.endAddress = endAddress;
        this.endLocation = endLocation;
        this.startAddress = startAddress;
        this.startLocation = startLocation;
        this.steps = steps;
        this.trafficSpeedEntry = trafficSpeedEntry;
        this.viaWaypoint = viaWaypoint;
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

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public Leg withEndAddress(String endAddress) {
        this.endAddress = endAddress;
        return this;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    public Leg withEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
        return this;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public Leg withStartAddress(String startAddress) {
        this.startAddress = startAddress;
        return this;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    public Leg withStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
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

    public List<Object> getTrafficSpeedEntry() {
        return trafficSpeedEntry;
    }

    public void setTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
    }

    public Leg withTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
        return this;
    }

    public List<Object> getViaWaypoint() {
        return viaWaypoint;
    }

    public void setViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
    }

    public Leg withViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
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
        dest.writeValue(endAddress);
        dest.writeValue(endLocation);
        dest.writeValue(startAddress);
        dest.writeValue(startLocation);
        dest.writeList(steps);
        dest.writeList(trafficSpeedEntry);
        dest.writeList(viaWaypoint);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
