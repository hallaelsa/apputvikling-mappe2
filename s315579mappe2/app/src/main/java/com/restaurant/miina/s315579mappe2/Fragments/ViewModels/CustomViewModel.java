package com.restaurant.miina.s315579mappe2.Fragments.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

public abstract class CustomViewModel extends AndroidViewModel {
    public abstract List getList();
    abstract void loadList();
    public abstract List getUpdatedList();

    public CustomViewModel(@NonNull Application application) {
        super(application);
    }

}
