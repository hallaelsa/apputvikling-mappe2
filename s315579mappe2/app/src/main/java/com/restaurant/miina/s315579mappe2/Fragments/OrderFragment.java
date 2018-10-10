package com.restaurant.miina.s315579mappe2.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.restaurant.miina.s315579mappe2.Activities.CreateOrderActivity;
import com.restaurant.miina.s315579mappe2.Adapters.CustomAdapter;
import com.restaurant.miina.s315579mappe2.Adapters.OrderAdapter;
import com.restaurant.miina.s315579mappe2.Fragments.ViewModels.CustomViewModel;
import com.restaurant.miina.s315579mappe2.Fragments.ViewModels.OrderViewModel;
import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.Models.Order;
import com.restaurant.miina.s315579mappe2.R;

import java.util.List;

public class OrderFragment extends  CustomFragment {
    private final int REQUEST_CODE = 30003;


    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public CustomAdapter setAdapter() {
        List<Order> listOrders = model.getList();
        CustomAdapter adapter = new OrderAdapter(getActivity(), listOrders, flag);
        return adapter;
    }

    @Override
    public CustomViewModel getViewModel() {
        return ViewModelProviders.of(this).get(OrderViewModel.class);
    }

    @Override
    public Intent getCustomIntent() {
        return new Intent(getActivity(), CreateOrderActivity.class);
    }

    @Override
    public int getRequestCode() {
        return REQUEST_CODE;
    }

    @Override
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public int getLayout() {
        return R.layout.custom_fragment;
    }


}
