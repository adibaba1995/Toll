package com.apsit.toll.domain.model;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class Credentials {
    private String phone, password;

    public Credentials(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
