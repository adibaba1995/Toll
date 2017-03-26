package com.apsit.toll.data.network.pojo.toll;

import android.os.Parcel;
import android.os.Parcelable;

public class Toll implements Parcelable
{
    private long id;

    private String place_id;

    private double upto_three_axle;

    private double four_axle_more;

    private String address;

    private String name;

    private double two_axle_heavy;

    private String state;

    private double longitude;

    private double latitude;

    private String LCV;

    private double two_axle;

    private String country;

    protected Toll(Parcel in) {
        id = in.readLong();
        place_id = in.readString();
        upto_three_axle = in.readDouble();
        four_axle_more = in.readDouble();
        address = in.readString();
        name = in.readString();
        two_axle_heavy = in.readDouble();
        state = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
        LCV = in.readString();
        two_axle = in.readDouble();
        country = in.readString();
    }

    public static final Creator<Toll> CREATOR = new Creator<Toll>() {
        @Override
        public Toll createFromParcel(Parcel in) {
            return new Toll(in);
        }

        @Override
        public Toll[] newArray(int size) {
            return new Toll[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace_id ()
    {
        return place_id;
    }

    public void setPlace_id (String place_id)
    {
        this.place_id = place_id;
    }

    public double getUpto_three_axle() {
        return upto_three_axle;
    }

    public void setUpto_three_axle(double upto_three_axle) {
        this.upto_three_axle = upto_three_axle;
    }

    public double getFour_axle_more() {
        return four_axle_more;
    }

    public void setFour_axle_more(double four_axle_more) {
        this.four_axle_more = four_axle_more;
    }

    public double getTwo_axle_heavy() {
        return two_axle_heavy;
    }

    public void setTwo_axle_heavy(double two_axle_heavy) {
        this.two_axle_heavy = two_axle_heavy;
    }

    public double getTwo_axle() {
        return two_axle;
    }

    public void setTwo_axle(double two_axle) {
        this.two_axle = two_axle;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLCV ()
    {
        return LCV;
    }

    public void setLCV (String LCV)
    {
        this.LCV = LCV;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", place_id = "+place_id+", upto_three_axle = "+upto_three_axle+", four_axle_more = "+four_axle_more+", address = "+address+", name = "+name+", two_axle_heavy = "+two_axle_heavy+", state = "+state+", longitude = "+longitude+", latitude = "+latitude+", LCV = "+LCV+", two_axle = "+two_axle+", country = "+country+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(place_id);
        dest.writeDouble(upto_three_axle);
        dest.writeDouble(four_axle_more);
        dest.writeString(address);
        dest.writeString(name);
        dest.writeDouble(two_axle_heavy);
        dest.writeString(state);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(LCV);
        dest.writeDouble(two_axle);
        dest.writeString(country);
    }
}