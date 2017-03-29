package com.apsit.toll.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.apsit.toll.R;
import com.apsit.toll.presentation.view.fragment.ManualOtpFragment;
import com.apsit.toll.presentation.view.fragment.SignUpEnterMobileFragment;
import com.apsit.toll.presentation.view.fragment.VerifyOtpFragment;

/**
 * Created by adityathanekar on 29/03/17.
 */

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
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

    public void recieveData(String data) {

    }
}
