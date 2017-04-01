package com.apsit.toll.presentation.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.vehicle.Vehicle;
import com.apsit.toll.presentation.view.viewmodel.VehicleType;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adityathanekar on 09/01/17.
 */

public class VehicleAdapter extends ArrayAdapter<Vehicle> {

    LayoutInflater inflater;

    public VehicleAdapter(Context context, List<Vehicle> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    private View rowview(View convertView, int position) {
        View view = null;
        if (position == getCount() - 1) {
            view = inflater.inflate(android.R.layout.simple_list_item_1, null, false);
            ((TextView) view.findViewById(android.R.id.text1)).setText("Add Vehicle");
        } else {
            view = inflater.inflate(R.layout.vehicle_type_item, null, false);
        }

        return view;
    }

    @Override
    public int getCount() {
        if (super.getCount() == 0)
            return 1;
        else
            return super.getCount();
    }

    private class ViewHolder {
        ImageView icon;
        TextView name;
    }

}
