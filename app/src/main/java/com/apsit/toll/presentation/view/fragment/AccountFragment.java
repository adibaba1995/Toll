package com.apsit.toll.presentation.view.fragment;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apsit.toll.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class AccountFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
