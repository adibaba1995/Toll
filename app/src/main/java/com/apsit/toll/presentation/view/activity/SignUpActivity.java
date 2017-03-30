package com.apsit.toll.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.register.Register;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.presentation.view.fragment.ManualOtpFragment;
import com.apsit.toll.presentation.view.fragment.PasswordFragment;
import com.apsit.toll.presentation.view.fragment.SignUpEnterMobileFragment;
import com.apsit.toll.presentation.view.fragment.VerifyOtpFragment;
import com.apsit.toll.service.TollService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 29/03/17.
 */

public class SignUpActivity extends AppCompatActivity {

    private SignUp service;

    private String phone, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.adisoftwares.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(SignUp.class);

        init();
    }

    private void init() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new SignUpEnterMobileFragment();
            fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

    public void showPasswordFragment(String mobile) {
        this.phone = mobile;
        swapFragment(new PasswordFragment());
    }

    public void showVerifyOtpFragment(String mobile) {
        swapFragment(VerifyOtpFragment.newInstance(mobile));
    }

    public void showManualOtpFragment(String mobile) {
        swapFragment(ManualOtpFragment.newInstance(mobile));
    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
        }
    }


    public void completeSignUp(String password) {
        this.password = password;
        if (phone != null && password != null)
            service.register(phone, password).enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    if (response.isSuccessful()) {
                        Register register = response.body();
                        if(register.getStatus() == Register.SUCCESS) {
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {

                }
            });
    }

    public interface SignUp {
        @GET("/register.php")
        Call<Register> register(@Query("phone_no") String phone, @Query("password") String password);
    }
}
