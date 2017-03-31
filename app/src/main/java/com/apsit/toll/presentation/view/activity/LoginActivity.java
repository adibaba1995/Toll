package com.apsit.toll.presentation.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.presentation.application.TollApplication;
import com.apsit.toll.presentation.presenter.SignInPresenter;
import com.apsit.toll.presentation.view.SignInView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 27/03/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, SignInView {

    public static final int SIGN_UP_RESULT_CODE = 123;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.signin)
    Button signin;
    @BindView(R.id.signup)
    Button signup;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;

    private Unbinder unbinder;

    @Inject
    SignInPresenter presenter;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TollApplication) getApplication()).createSignInComponent().inject(this);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        Typeface titleFont = Typeface.
                createFromAsset(getAssets(), "fonts/future.ttf");
        title.setTypeface(titleFont);
        toolbar.setTitle("");
        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                setResult(SIGN_UP_RESULT_CODE);
                finish();
                break;
            case R.id.signin:
                presenter.getSignInStatus(phone.getText().toString(), password.getText().toString());
                break;
        }
    }

    @Override
    public void showSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showFailure() {

    }
}
