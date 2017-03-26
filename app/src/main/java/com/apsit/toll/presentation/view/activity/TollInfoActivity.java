package com.apsit.toll.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.apsit.toll.R;

/**
 * Created by adityathanekar on 27/03/17.
 */

public class TollInfoActivity extends AppCompatActivity {
    public static final String EXTRA_TOLL = "com.apsit.toll.EXTRA_TOLL";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toll_info);
    }
}
