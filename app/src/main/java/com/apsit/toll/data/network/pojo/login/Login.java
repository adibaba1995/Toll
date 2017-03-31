package com.apsit.toll.data.network.pojo.login;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class Login
{
    private String message;

    private String token;

    private int status;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", token = "+token+", status = "+status+"]";
    }
}

