package com.apsit.toll.presentation.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.balance.Balance;
import com.apsit.toll.data.network.pojo.register.Register;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class WalletActivity extends AppCompatActivity {

    @BindView(R.id.amount)
    TextView amount;

    Balance service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.adisoftwares.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(Balance.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("token", "");
        service.getBalance(1, token).enqueue(new Callback<com.apsit.toll.data.network.pojo.balance.Balance>() {
            @Override
            public void onResponse(Call<com.apsit.toll.data.network.pojo.balance.Balance> call, Response<com.apsit.toll.data.network.pojo.balance.Balance> response) {
                if(response.isSuccessful())
                    amount.setText(response.body().getBalance() + "");
            }

            @Override
            public void onFailure(Call<com.apsit.toll.data.network.pojo.balance.Balance> call, Throwable t) {

            }
        });
    }

    public interface Balance {
        @GET("/checkbalance.php")
        Call<com.apsit.toll.data.network.pojo.balance.Balance> getBalance(@Query("update") int update, @Query("token") String token);
    }
}
