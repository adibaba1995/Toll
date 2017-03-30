package com.apsit.toll.presentation.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.presentation.view.activity.LoginActivity;
import com.apsit.toll.presentation.view.activity.SignUpActivity;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 29/03/17.
 */

public class SignUpEnterMobileFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.signup)
    Button signup;
    @BindView(R.id.signin)
    Button signin;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up_mobile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        Typeface titleFont = Typeface.
                createFromAsset(getActivity().getAssets(), "fonts/future.ttf");
        title.setTypeface(titleFont);
        toolbar.setTitle("");
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.signup:
                if (isValidMobile(phone.getText().toString()))
                    ((SignUpActivity) getActivity()).showPasswordFragment(phone.getText().toString());
                else
                    showSnackbar("Please enter a valid Mobile number");
                break;
        }
    }

    private boolean isValidMobile(String phone) {
        boolean check = false;
        if (Pattern.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$", phone)) {
            if (phone.length() < 6 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE).show();
    }
}
