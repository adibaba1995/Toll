package com.apsit.toll.interactors.base;



import com.apsit.toll.model.Data;

import java.util.List;

/**
 * Created by adityathanekar on 27/01/17.
 */

public abstract class Interactor<T extends Data> {
    private Callback callback;

    public void insert(T data) {

    }

    public void update(T data) {

    }

    public void delete(T data) {

    }

    public List<T> getAllData() {
        return null;
    }

    public interface Callback {
        void addSuccess();
        void dataChanged(Data data);
        void clear();
        void addFailure();
        void updateSuccess();
        void updateFailure();
        void deleteSuccess();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }
}
