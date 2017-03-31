package com.apsit.toll.presentation.view.viewmodel;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class VehicleType {
    private String text;
    private int icon;

    public VehicleType(String text, int icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
