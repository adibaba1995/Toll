
package com.apsit.toll.data.network.pojo.geocode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class AddressComponent implements Parcelable
{

    private String longName;
    private String shortName;
    private List<String> types = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<AddressComponent> CREATOR = new Creator<AddressComponent>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AddressComponent createFromParcel(Parcel in) {
            AddressComponent instance = new AddressComponent();
            instance.longName = ((String) in.readValue((String.class.getClassLoader())));
            instance.shortName = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.types, (String.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public AddressComponent[] newArray(int size) {
            return (new AddressComponent[size]);
        }

    }
    ;

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public AddressComponent withLongName(String longName) {
        this.longName = longName;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public AddressComponent withShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public AddressComponent withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public AddressComponent withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(longName);
        dest.writeValue(shortName);
        dest.writeList(types);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
