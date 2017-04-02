package com.apsit.toll.data.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.apsit.toll.data.network.pojo.payment.Payment;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.data.network.pojo.vehicle.Vehicle;
import com.apsit.toll.domain.model.MinMaxLatLong;

import org.json.JSONArray;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 02/04/17.
 */

public class AddVehicleService {

    public Observable<Boolean> getVehicleObservable(final Vehicle vehicle) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealm(vehicle);
                realm.commitTransaction();
                e.onNext(true);
            }
        });
    }
}