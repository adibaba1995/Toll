package com.apsit.toll.data.network.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.apsit.toll.data.network.pojo.payment.Payment;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.domain.model.PaymentDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 01/04/17.
 */

public class PaymentService {
    private Payment service;
    private Context context;

    @Inject
    public PaymentService(Retrofit retrofit, Context context) {
        service = retrofit.create(Payment.class);
        this.context = context;
    }

    public Observable<com.apsit.toll.data.network.pojo.payment.Payment> getPaymentObservable(final PaymentDetails paymentDetails) {
        return Observable.create(new ObservableOnSubscribe<com.apsit.toll.data.network.pojo.payment.Payment>() {
            @Override
            public void subscribe(ObservableEmitter<com.apsit.toll.data.network.pojo.payment.Payment> e) throws Exception {
                JSONArray jsonArray = new JSONArray();
                List<Toll> tolls = paymentDetails.getTollList();
                for (Toll toll : tolls) {
                    if(toll.isSelected()) {
                        jsonArray.put(toll.getId());
                    }
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                String token = preferences.getString("token", "");

                Log.d("Aditya", token);

                com.apsit.toll.data.network.pojo.payment.Payment payment = service.makePayment(paymentDetails.getVehicle_no(), 1234, token, jsonArray.toString()).execute().body();

                e.onNext(payment);
            }
        });
    }

    public interface Payment {
        @GET("/insert_toll_transaction.php")
        Call<com.apsit.toll.data.network.pojo.payment.Payment> makePayment(@Query("vehicleno") String vehicle_no, @Query("amount") float amount, @Query("token") String token, @Query("tolls") String tolls);
    }
}
