//package com.restaurant.miina.s315579mappe2.Friends;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//import com.restaurant.miina.s315579mappe2.R;
//
//import java.util.List;
//
//public class FriendAdapter extends BaseAdapter {
//    private List<Friend> friends;
//    private LayoutInflater inflater;
//
//    public FriendAdapter(Context context, List<Friend> restaurants) {
//        this.friends = restaurants;
//        inflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount() {
//        return friends.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return friends.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return friends.get(position).get_ID();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        FriendAdapter.ViewHolder holder;
//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.friends_item, null);
//            holder = new FriendAdapter.ViewHolder();
//            holder.name = (TextView)convertView.findViewById(R.id.friend_name);
//            holder.address = (TextView)convertView.findViewById(R.id.friend_address);
//            holder.phone = (TextView)convertView.findViewById(R.id.friend_phone);
//
//            convertView.setTag(holder);
//        } else {
//            holder = (FriendAdapter.ViewHolder) convertView.getTag();
//        }
//
//        holder.name.setText(friends.get(position).getName());
//        holder.address.setText(friends.get(position).getAddress());
//        holder.phone.setText(friends.get(position).getPhone());
//
//        return convertView;
//    }
//
//    public void refresh(List<Friend> updatedFriends) {
//        Log.d("Adapter", "refresh");
//        friends.clear();
//        friends.addAll(updatedFriends);
//        this.notifyDataSetChanged();
//    }
//
//    public static class ViewHolder {
//        public TextView name;
//        public TextView address;
//        public TextView phone;
//        public TextView type;
//    }
//}
