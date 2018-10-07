package com.restaurant.miina.s315579mappe2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public abstract class CustomAdapter extends BaseAdapter {
    private List list;
    public int flag;
    private LayoutInflater inflater;
    abstract void refresh(List list);
    abstract void setText(ViewHolder holder, int position);
    abstract int getLayout();

    public CustomAdapter(Context context, List list, int flag) {
        this.flag = flag;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomAdapter.ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(getLayout(), null);
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

        return convertView;
    }

    public static class ViewHolder {
        public TextView header;
        public TextView address;
        public TextView phone;
        public TextView type;
    }
}
