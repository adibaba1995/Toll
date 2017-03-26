package com.apsit.toll.data.network.service;

import com.google.maps.PendingResult;
import com.google.maps.model.AutocompletePrediction;

/**
 * Created by adityathanekar on 23/03/17.
 */

public abstract class Service {

    private Callback callback;

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void success(Object data);
    }
}
