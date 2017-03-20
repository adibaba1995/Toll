package com.apsit.toll.presenter.base;

import com.apsit.toll.view.BaseView;

import java.lang.ref.WeakReference;

/**
 * Created by adityathanekar on 17/06/16.
 */

public abstract class BasePresenter<T extends BaseView> {

    private WeakReference<T> view;

    public void attachView(T view) {
        this.view = new WeakReference<T>(view);
    }

    public void detachView() {
        view.clear();
        view = null;
    }

    public WeakReference<T> getView() {
        return view;
    }
}
