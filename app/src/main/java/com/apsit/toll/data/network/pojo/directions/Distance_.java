
package com.apsit.toll.data.network.pojo.directions;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Distance_ implements Parcelable
{

    private String text;
    private long value;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Distance_> CREATOR = new Creator<Distance_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Distance_ createFromParcel(Parcel in) {
            Distance_ instance = new Distance_();
            instance.text = ((String) in.readValue((String.class.getClassLoader())));
            instance.value = ((long) in.readValue((long.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Distance_[] newArray(int size) {
            return (new Distance_[size]);
        }

    }
    ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Distance_() {
    }

    /**
     * 
     * @param text
     * @param value
     */
    public Distance_(String text, long value) {
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

    public Distance_ withText(String text) {
        this.text = text;
        return this;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Distance_ withValue(long value) {
        this.value = value;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Distance_ withAdditionalProperty(String name, Object value) {
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
