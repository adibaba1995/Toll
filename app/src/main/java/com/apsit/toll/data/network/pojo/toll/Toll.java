package com.apsit.toll.data.network.pojo.toll;

import android.util.Log;

public class Toll {

    public static final int SELECT_TYPE_TWO_AXLE = 1;
    public static final int SELECT_TYPE_TWO_AXLE_HEAVY = 2;
    public static final int SELECT_TYPE_LCV = 3;
    public static final int SELECT_TYPE_UPTO_THREE_AXLE = 4;
    public static final int SELECT_TYPE_FOUR_AXLE_MORE = 5;

    private boolean selected, paid;

    public static int selectType;

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

    private double LCV;

    private double two_axle;

    private String country;

    public Toll() {
        super();
        selected = true;
        paid = false;
    }

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

    public double getLCV() {
        return LCV;
    }

    public void setLCV(double LCV) {
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public double getPrice() {
        switch (selectType) {
            case Toll.SELECT_TYPE_TWO_AXLE:
                return getTwo_axle();
            case Toll.SELECT_TYPE_TWO_AXLE_HEAVY:
                return getTwo_axle_heavy();
            case Toll.SELECT_TYPE_LCV:
                return getLCV();
            case Toll.SELECT_TYPE_UPTO_THREE_AXLE:

                return getUpto_three_axle();
            case Toll.SELECT_TYPE_FOUR_AXLE_MORE:
                return getFour_axle_more();
            default:
                return 0;
        }
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", place_id = "+place_id+", upto_three_axle = "+upto_three_axle+", four_axle_more = "+four_axle_more+", address = "+address+", name = "+name+", two_axle_heavy = "+two_axle_heavy+", state = "+state+", longitude = "+longitude+", latitude = "+latitude+", LCV = "+LCV+", two_axle = "+two_axle+", country = "+country+"]";
    }
}