package com.apsit.toll.presentation.view.viewmodel;

import com.apsit.toll.data.network.pojo.toll.Toll;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by adityathanekar on 27/03/17.
 */

public class TollCarrier {
    private Toll toll;
    private MarkerOptions markerOptions;

    public TollCarrier(Toll toll, MarkerOptions markerOptions) {
        this.toll = toll;
        this.markerOptions = markerOptions;
    }

    public Toll getToll() {
        return toll;
    }

    public void setToll(Toll toll) {
        this.toll = toll;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }
}
