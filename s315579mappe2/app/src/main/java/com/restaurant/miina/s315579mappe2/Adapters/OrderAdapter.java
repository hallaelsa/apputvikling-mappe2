package com.restaurant.miina.s315579mappe2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;

import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.Models.Order;
import com.restaurant.miina.s315579mappe2.R;

import java.util.List;

public class OrderAdapter extends CustomAdapter {
    List<Order> orders;

    public OrderAdapter(Context context, List<Order> orders, int flag) {
        super(context, orders, flag);
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orders.get(position).get_ID();
    }

    @Override
    public void refresh(List updatedOrders) {
        orders.clear();
        orders.addAll(updatedOrders);
        this.notifyDataSetChanged();
    }

    @Override
    void setText(CustomAdapter.ViewHolder holder, int position) {
        holder.header.setText(orders.get(position).getRestaurant().getName());
        holder.arg1.setText(orders.get(position).getRestaurant().getAddress());
        holder.arg2.setText(getNumOfFruends(position)+" friends joining");
        holder.arg3.setText(orders.get(position).getDate()+" "+orders.get(position).getTime());
    }

    @Override
    int getLayout() {
        return R.layout.item_row;
    }

    private String getNumOfFruends(int position) {
        List<Friend> friends = orders.get(position).getFriends();
        return String.valueOf(friends.size());
    }
}
