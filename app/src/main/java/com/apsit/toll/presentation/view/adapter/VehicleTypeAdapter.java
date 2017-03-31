package com.apsit.toll.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.apsit.toll.R;
import com.apsit.toll.presentation.view.viewmodel.VehicleType;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adityathanekar on 09/01/17.
 */

public class VehicleTypeAdapter extends ArrayAdapter<VehicleType> {

    LayoutInflater inflater;

    public VehicleTypeAdapter(Context context, List<VehicleType> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView,position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView,position);
    }

    private View rowview(View convertView , int position){

        ViewHolder holder ;
        View rowview = convertView;
        if (rowview==null) {

            holder = new ViewHolder();
            rowview = inflater.inflate(R.layout.vehicle_type_item, null, false);

            holder.name = (TextView) rowview.findViewById(R.id.title);
            holder.icon = (ImageView) rowview.findViewById(R.id.icon);
            rowview.setTag(holder);
        }else{
            holder = (ViewHolder) rowview.getTag();
        }
        VehicleType type = getItem(position);
        Glide.with(getContext()).load(type.getIcon()).into(holder.icon);
        holder.name.setText(type.getText());

        return rowview;
    }

    private class ViewHolder{
        ImageView icon;
        TextView name;
    }

}
