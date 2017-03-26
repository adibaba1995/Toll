package com.apsit.toll.data.network.pojo.toll;

public class Toll
{
    private long id;

    private String place_id;

    private String upto_three_axle;

    private String four_axle_more;

    private String address;

    private String name;

    private String two_axle_heavy;

    private String state;

    private double longitude;

    private double latitude;

    private String LCV;

    private String two_axle;

    private String country;

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

    public String getUpto_three_axle ()
    {
        return upto_three_axle;
    }

    public void setUpto_three_axle (String upto_three_axle)
    {
        this.upto_three_axle = upto_three_axle;
    }

    public String getFour_axle_more ()
    {
        return four_axle_more;
    }

    public void setFour_axle_more (String four_axle_more)
    {
        this.four_axle_more = four_axle_more;
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

    public String getTwo_axle_heavy ()
    {
        return two_axle_heavy;
    }

    public void setTwo_axle_heavy (String two_axle_heavy)
    {
        this.two_axle_heavy = two_axle_heavy;
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

    public String getTwo_axle ()
    {
        return two_axle;
    }

    public void setTwo_axle (String two_axle)
    {
        this.two_axle = two_axle;
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
}