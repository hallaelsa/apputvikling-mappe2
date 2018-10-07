package com.restaurant.miina.s315579mappe2;

import android.content.Context;

import com.restaurant.miina.s315579mappe2.CustomAdapter;
import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

import java.util.List;

public class FriendAdapter extends CustomAdapter {
    List<Friend> friends;

    public FriendAdapter(Context context, List<Friend> friends, int flag) {
        super(context, friends, flag);
        this.friends = friends;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return friends.get(position).get_ID();
    }

    @Override
    void refresh(List updatedFriends) {
        friends.clear();
        friends.addAll(updatedFriends);
        this.notifyDataSetChanged();
    }

    @Override
    void setText(ViewHolder holder, int position) {
        if(flag == FriendFragment.SIMPLIFIED_CHECK_LIST_FLAG) {
            holder.header.setText(friends.get(position).getName());
        } else {
            holder.header.setText(friends.get(position).getName());
            holder.address.setText(friends.get(position).getAddress());
            holder.phone.setText(friends.get(position).getPhone());
        }

    }

    @Override
    int getLayout() {
        if (flag == FriendFragment.SIMPLIFIED_CHECK_LIST_FLAG) {
            return R.layout.item_checkbox_row;
        }

        return R.layout.item_row;
    }
}
