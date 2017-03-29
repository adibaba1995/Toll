
package com.apsit.toll.data.network.pojo.geocode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Address implements Parcelable
{

    private List<Result> results = null;
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Address> CREATOR = new Creator<Address>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Address createFromParcel(Parcel in) {
            Address instance = new Address();
            in.readList(instance.results, (Result.class.getClassLoader()));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Address[] newArray(int size) {
            return (new Address[size]);
        }

    }
    ;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Address withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address withStatus(String status) {
        this.status = status;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Address withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
        dest.writeValue(status);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
