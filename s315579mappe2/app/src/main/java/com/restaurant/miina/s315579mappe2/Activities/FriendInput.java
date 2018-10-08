package com.restaurant.miina.s315579mappe2.Activities;

import android.os.Bundle;
import android.view.View;

import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.R;

public class FriendInput extends ItemInputActivity {
    Friend friend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeLabel.setVisibility(View.GONE);
        type.setVisibility(View.GONE);
    }

    @Override
    void setTitle(boolean update) {
        if(update) {
            actionbar.setTitle(R.string.FriUpdateHeader);
        } else {
            actionbar.setTitle(R.string.FriHeader);
        }
    }

    @Override
    void setValues(long id) {
        Friend friend = db.getFriend(id);
        name.setText(friend.getName());
        address.setText(friend.getAddress());
        phone.setText(friend.getPhone());
    }

    @Override
    void addItem() {
        friend = new Friend(name.getText().toString(),phone.getText().toString(),address.getText().toString());
        db.regFriend(friend);
    }

    @Override
    void updateItem(long id) {
        friend = new Friend(id, name.getText().toString(), phone.getText().toString(), address.getText().toString());
        db.updateFriend(friend);
    }

    @Override
    void deleteItem(long id) {
        db.deleteFriend(id);
    }

}
