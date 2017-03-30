package com.apsit.toll.data.network.pojo.register;

/**
 * Created by adityathanekar on 30/03/17.
 */

public class Register {

    public static int SUCCESS = 0;
    public static int FAILURE = 1;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}