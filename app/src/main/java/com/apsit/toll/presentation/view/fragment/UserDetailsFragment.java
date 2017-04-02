package com.apsit.toll.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.apsit.toll.R;
import com.apsit.toll.presentation.view.activity.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 30/03/17.
 */

public class UserDetailsFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.cont)
    Button cont;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.first)
    EditText first;
    @BindView(R.id.last)
    EditText last;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        cont.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cont:
                ((SignUpActivity)getActivity()).completeSignUp(first.getText().toString(), last.getText().toString(),password.getText().toString());
                break;
        }
    }
}
