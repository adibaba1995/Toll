package com.apsit.toll.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.vehicle.Vehicle;
import com.apsit.toll.presentation.application.TollApplication;
import com.apsit.toll.presentation.presenter.AddVehiclePresenter;
import com.apsit.toll.presentation.view.AddVehicleView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class AddVehicleActivity extends AppCompatActivity implements AddVehicleView,  View.OnClickListener {

    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.vehicle_number)
    EditText vehicleNumber;
    @BindView(R.id.color)
    TextView color;
    @BindView(R.id.model_name)
    TextView modelName;
    @BindView(R.id.company)
    TextView company;
    @BindView(R.id.container)
    View container;
    @BindView(R.id.add)
    Button add;

    @Inject
    AddVehiclePresenter presenter;

    private Unbinder unbinder;
    private Vehicle vehicle;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        unbinder = ButterKnife.bind(this);
        ((TollApplication)getApplication()).createAddVehicleComponent().inject(this);
        init();
    }

    private void init() {
        submit.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                presenter.getVehicleDetails(vehicleNumber.getText().toString());
                break;
            case R.id.add:
                presenter.addVehicle(vehicle);
                break;
        }
    }

    @Override
    public void showVehicleDetails(Vehicle vehicle) {
        this.vehicle = vehicle;
        container.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);
        modelName.setText(vehicle.getModel_name());
        company.setText(vehicle.getCompany());
        color.setText(vehicle.getColor());
    }

    @Override
    public void showAddVehicleSuccess() {
        finish();
    }
}
