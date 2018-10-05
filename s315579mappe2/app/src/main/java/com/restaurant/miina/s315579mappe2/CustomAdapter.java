package com.restaurant.miina.s315579mappe2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;
import com.restaurant.miina.s315579mappe2.Restaurants.RestaurantAdapter;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private List<Restaurant> restaurants;
    private List<Friend> friends;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, List<Restaurant> restaurants, List<Friend> friends) {
        this.restaurants = restaurants;
        this.friends = friends;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(restaurants != null) {
            return restaurants.size() ;
        }
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        if(restaurants != null) {
            return restaurants.get(position);
        }
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        if(restaurants != null) {
            return restaurants.get(position).get_ID();
        }
        return friends.get(position).get_ID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomAdapter.ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_row, null);
            holder = new CustomAdapter.ViewHolder();
            holder.header = (TextView)convertView.findViewById(R.id.header);
            holder.address = (TextView)convertView.findViewById(R.id.address);
            holder.phone = (TextView)convertView.findViewById(R.id.phone);

            if(restaurants != null) {
                holder.type = (TextView)convertView.findViewById(R.id.type);
            }

            convertView.setTag(holder);
        } else {
            holder = (CustomAdapter.ViewHolder) convertView.getTag();
        }


        if(restaurants != null ) {
            holder.header.setText(restaurants.get(position).getName());
            holder.address.setText(restaurants.get(position).getAddress());
            holder.phone.setText(restaurants.get(position).getPhone());
            holder.type.setText(restaurants.get(position).getType());
        } else {
            holder.header.setText(friends.get(position).getName());
            holder.address.setText(friends.get(position).getAddress());
            holder.phone.setText(friends.get(position).getPhone());
        }

        return convertView;
    }

    public void refreshRestaurant(List<Restaurant> updatedRestaurants) {
        restaurants.clear();
        restaurants.addAll(updatedRestaurants);
        this.notifyDataSetChanged();
    }

    public void refreshFriends(List<Friend> updatedFriends) {
        friends.clear();
        friends.addAll(updatedFriends);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder {
        public TextView header;
        public TextView address;
        public TextView phone;
        public TextView type;
    }
}
