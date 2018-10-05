package com.restaurant.miina.s315579mappe2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public abstract class CustomAdapter extends BaseAdapter {
    private List list;
    private LayoutInflater inflater;
    abstract void refresh(List list);
    abstract void setText(ViewHolder holder, int position);

    public CustomAdapter(Context context, List list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
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
            holder.type = (TextView)convertView.findViewById(R.id.type);
            convertView.setTag(holder);
        } else {
            holder = (CustomAdapter.ViewHolder) convertView.getTag();
        }

        setText(holder, position);
//        if(restaurants != null ) {
//            holder.header.setText(list.get(position).getName());
//            holder.address.setText(list.get(position).getAddress());
//            holder.phone.setText(list.get(position).getPhone());
//            holder.type.setText(list.get(position).getType());
//        } else {
//            holder.header.setText(friends.get(position).getName());
//            holder.address.setText(friends.get(position).getAddress());
//            holder.phone.setText(friends.get(position).getPhone());
//        }

        return convertView;
    }

    public static class ViewHolder {
        public TextView header;
        public TextView address;
        public TextView phone;
        public TextView type;
    }
}
