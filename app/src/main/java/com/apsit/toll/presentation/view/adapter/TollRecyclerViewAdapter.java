package com.apsit.toll.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.presentation.utility.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 26/03/17.
 */

public class TollRecyclerViewAdapter extends RecyclerView.Adapter<TollRecyclerViewAdapter.TollRecyclerViewHolder>{

    private List<Toll> tollList;
    private LayoutInflater inflater;
    private Callback callback;

    public TollRecyclerViewAdapter(List<Toll> tollList, Context context) {
        this.tollList = tollList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return tollList.get(position).getId();
    }

    @Override
    public TollRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TollRecyclerViewHolder(inflater.inflate(R.layout.toll_recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TollRecyclerViewHolder holder, int position) {
        Toll toll = tollList.get(position);
        holder.toll = toll;
        holder.name.setText(toll.getName());
        holder.price.setText(Utility.formatFloat(toll.getPrice()));
        if(toll.isPaid()) {
            holder.checkBox.setVisibility(View.GONE);
            holder.showqr.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.showqr.setVisibility(View.GONE);
        }
    }

//    private double getPrice(Toll toll) {
//        switch (Toll.selectType) {
//            case Toll.SELECT_TYPE_TWO_AXLE:
//                Log.d("Aditya", toll.getTwo_axle() + " hello");
//                return toll.getTwo_axle();
//            case Toll.SELECT_TYPE_TWO_AXLE_HEAVY:
//                return toll.getTwo_axle_heavy();
//            case Toll.SELECT_TYPE_LCV:
//                return toll.getLCV();
//            case Toll.SELECT_TYPE_UPTO_THREE_AXLE:
//                return toll.getUpto_three_axle();
//            case Toll.SELECT_TYPE_FOUR_AXLE_MORE:
//                return toll.getFour_axle_more();
//            default:
//                return 0;
//        }
//    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return tollList.size();
    }

    public class TollRecyclerViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

        Toll toll;

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.check)
        AppCompatCheckBox checkBox;
        @BindView(R.id.showqr)
        Button showqr;

        private Unbinder unbinder;

        public TollRecyclerViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            init();
        }

        private void init() {
            checkBox.setOnCheckedChangeListener(this);
            showqr.setOnClickListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(callback != null) {
                toll.setSelected(isChecked);
                callback.checkChanged(toll);
            }
        }

        @Override
        public void onClick(View v) {
            callback.itemClicked(toll);
        }
    }

    public interface Callback {
        void checkChanged(Toll toll);
        void itemClicked(Toll toll);
    }
}
