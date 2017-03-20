package com.apsit.toll.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apsit.toll.DistanceService;
import com.apsit.toll.R;
import com.apsit.toll.TollDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 16/03/17.
 */

public class PaymentFragment extends Fragment {

    @BindView(R.id.click)
    Button click;
    private TollDatabase db;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        db = new TollDatabase(getActivity());
        db.getReadableDatabase();
        init();
    }

    private void init() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DistanceService.class);
                getActivity().startService(intent);
            }
        });
    }
}
