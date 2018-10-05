package com.restaurant.miina.s315579mappe2.Restaurants;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.restaurant.miina.s315579mappe2.R;

import java.util.ArrayList;
import java.util.List;

/// https://stackoverflow.com/questions/22512833/create-listview-in-fragment-android

public class RestaurantAdapter extends BaseAdapter {
    private List<Restaurant> restaurants;
    private LayoutInflater inflater;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return restaurants.get(position).get_ID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.restaurant_item, null);
            holder = new ViewHolder();
            holder.title = (TextView)convertView.findViewById(R.id.res_title);
            holder.res_address = (TextView)convertView.findViewById(R.id.res_address);
            holder.res_phone = (TextView)convertView.findViewById(R.id.res_phone);
            holder.res_type = (TextView)convertView.findViewById(R.id.res_type);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(restaurants.get(position).getName());
        holder.res_address.setText(restaurants.get(position).getAddress());
        holder.res_phone.setText(restaurants.get(position).getPhone());
        holder.res_type.setText(restaurants.get(position).getType());

        return convertView;
    }

    public void refresh(List<Restaurant> updatedRestaurants) {
        Log.d("Adapter", "refresh");
        restaurants.clear();
        restaurants.addAll(updatedRestaurants);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder {
        public TextView title;
        public TextView res_address;
        public TextView res_phone;
        public TextView res_type;
    }
}
