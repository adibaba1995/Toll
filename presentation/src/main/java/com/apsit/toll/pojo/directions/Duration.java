
package com.apsit.toll.pojo.directions;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Duration implements Parcelable
{

    private String text;
    private long value;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Duration> CREATOR = new Creator<Duration>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Duration createFromParcel(Parcel in) {
            Duration instance = new Duration();
            instance.text = ((String) in.readValue((String.class.getClassLoader())));
            instance.value = ((long) in.readValue((long.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Duration[] newArray(int size) {
            return (new Duration[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Duration() {
    }

    /**
     * 
     * @param text
     * @param value
     */
    public Duration(String text, long value) {
        super();
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Duration withText(String text) {
        this.text = text;
        return this;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Duration withValue(long value) {
        this.value = value;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Duration withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(text);
        dest.writeValue(value);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
