package com.apsit.toll.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.toll.Toll;

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
        holder.name.setText(toll.getName());
        holder.price.setText(toll.getTwo_axle());
    }

    @Override
    public int getItemCount() {
        return tollList.size();
    }

    public class TollRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.check)
        AppCompatCheckBox checkBox;

        private Unbinder unbinder;

        public TollRecyclerViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
