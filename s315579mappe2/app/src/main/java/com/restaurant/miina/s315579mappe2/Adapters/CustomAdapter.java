package com.restaurant.miina.s315579mappe2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.restaurant.miina.s315579mappe2.R;

import java.util.List;

public abstract class CustomAdapter extends BaseAdapter {
    private List list;
    public int flag;
    private LayoutInflater inflater;
    public abstract void refresh(List list);
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
            holder.arg1 = (TextView)convertView.findViewById(R.id.arg1);
            holder.arg2 = (TextView)convertView.findViewById(R.id.arg2);
            holder.arg3 = (TextView)convertView.findViewById(R.id.arg3);
            holder.checkBox = (CheckBox)convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (CustomAdapter.ViewHolder) convertView.getTag();
        }

        setText(holder, position);

        return convertView;
    }

    public static class ViewHolder {
        public TextView header;
        public TextView arg1;
        public TextView arg2;
        public TextView arg3;
        public CheckBox checkBox;
    }
}
