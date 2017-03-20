package com.apsit.toll.repository;


import com.apsit.toll.domain.model.Data;

import java.util.List;


/**
 * Created by adityathanekar on 07/12/16.
 */

public abstract class Repository<T extends Data> {

    private Callback callback;

    public abstract void insert(T data);
    public abstract void update(T data);
    public abstract void delete(T data);
    public abstract List<T> getAllData();
    public interface Callback {
        void addSuccess();
        void addFailure();
        void updateSuccess();
        void updateFailure();
        void deleteSuccess();
        void deleteFailure();
    }
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
    public Callback getCallback() {
        return callback;
    }
}