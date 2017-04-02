package com.apsit.toll.data.storage;

import com.apsit.toll.data.network.pojo.vehicle.Vehicle;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by adityathanekar on 02/04/17.
 */

public class GetVehicleService {

    public Observable<List<Vehicle>> getVehicleObservable() {
        return Observable.create(new ObservableOnSubscribe<List<Vehicle>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Vehicle>> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                List<Vehicle> vehicles = realm.where(Vehicle.class).findAll();
                e.onNext(realm.copyFromRealm(vehicles));
            }
        });
    }
}
