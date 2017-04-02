package com.apsit.toll.data.network.pojo.vehicle;

import io.realm.RealmObject;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class Vehicle extends RealmObject
{
    private String color;

    private String company;

    private String number;

    private int type;

    private String customer_id;

    private String model_name;

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }

    public String getCompany ()
    {
        return company;
    }

    public void setCompany (String company)
    {
        this.company = company;
    }

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCustomer_id ()
    {
        return customer_id;
    }

    public void setCustomer_id (String customer_id)
    {
        this.customer_id = customer_id;
    }

    public String getModel_name ()
    {
        return model_name;
    }

    public void setModel_name (String model_name)
    {
        this.model_name = model_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [color = "+color+", company = "+company+", number = "+number+", type = "+type+", customer_id = "+customer_id+", model_name = "+model_name+"]";
    }
}
