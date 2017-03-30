package com.apsit.toll.presentation.view.fragment;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.presentation.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 29/03/17.
 */

public class ManualOtpFragment extends Fragment {

    private static final String EXTRA_MOBILE = "com.apsit.toll.EXTRA_MOBILE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.enter_otp_mobile)
    TextView otpMobile;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_otp_manual, container, false);
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
        String phone = arguments.getString(EXTRA_MOBILE);
        otpMobile.setText(getResources().getString(R.string.enter_otp, phone));
    }

    public static Fragment newInstance(String mobile) {
        Fragment fragment = new ManualOtpFragment();
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_MOBILE, mobile);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (((MainActivity) getActivity()).isOptionItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
