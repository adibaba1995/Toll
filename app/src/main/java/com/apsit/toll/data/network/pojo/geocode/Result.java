
package com.apsit.toll.data.network.pojo.geocode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Result implements Parcelable
{

    private List<AddressComponent> addressComponents = null;
    private String formattedAddress;
    private Geometry geometry;
    private String placeId;
    private List<String> types = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            Result instance = new Result();
            in.readList(instance.addressComponents, (com.apsit.toll.data.network.pojo.geocode.AddressComponent.class.getClassLoader()));
            instance.formattedAddress = ((String) in.readValue((String.class.getClassLoader())));
            instance.geometry = ((Geometry) in.readValue((Geometry.class.getClassLoader())));
            instance.placeId = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.types, (String.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public Result withAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
        return this;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Result withFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Result withGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Result withPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Result withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(addressComponents);
        dest.writeValue(formattedAddress);
        dest.writeValue(geometry);
        dest.writeValue(placeId);
        dest.writeList(types);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
