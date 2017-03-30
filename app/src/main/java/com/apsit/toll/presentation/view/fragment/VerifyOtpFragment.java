package com.apsit.toll.presentation.view.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apsit.toll.R;
import com.apsit.toll.presentation.reciever.MessageBroadcastReciever;
import com.apsit.toll.presentation.reciever.SmsListener;
import com.apsit.toll.presentation.view.activity.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 29/03/17.
 */

public class VerifyOtpFragment extends Fragment implements View.OnClickListener {

    private static final String EXTRA_MOBILE = "com.apsit.toll.EXTRA_MOBILE";

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.verifying)
    TextView verifying;
    @BindView(R.id.manualcode)
    Button manualCode;

    private Unbinder unbinder;

    private String phone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_verify_otp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        Bundle arguments = getArguments();

        Typeface titleFont = Typeface.
                createFromAsset(getActivity().getAssets(), "fonts/future.ttf");
        title.setTypeface(titleFont);
        toolbar.setTitle("");
        phone = arguments.getString(EXTRA_MOBILE);
        verifying.setText(getResources().getString(R.string.verifying, phone));
        manualCode.setOnClickListener(this);

        MessageBroadcastReciever.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                Toast.makeText(getActivity(),messageText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static Fragment newInstance(String mobile) {
        Fragment fragment = new VerifyOtpFragment();
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_MOBILE, mobile);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manualcode:
                ((SignUpActivity) getActivity()).showManualOtpFragment(phone);
                break;
        }
    }

    private void checkMessage(String message) {
        if(message != null) {
            String arr[] = message.split(" ", 2);

            String firstWord = arr[0];

            Toast.makeText(getActivity(), firstWord, Toast.LENGTH_SHORT).show();
        }
    }
}
