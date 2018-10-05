//package com.restaurant.miina.s315579mappe2.Friends;
//
//import android.app.Application;
//import android.arch.lifecycle.AndroidViewModel;
//import android.support.annotation.NonNull;
//
//import com.restaurant.miina.s315579mappe2.DBHandler;
//
//import java.util.List;
//
//public class FriendViewModel extends AndroidViewModel {
//    private List<Friend> friends;
//    private Application application;
//
//    public FriendViewModel(@NonNull Application application) {
//        super(application);
//        this.application = application;
//    }
//
//    public List<Friend> getFriends() {
//        if (friends == null) {
//            loadFriends();
//        }
//        return friends;
//    }
//
//    private void loadFriends() {
//        DBHandler db = new DBHandler(application.getApplicationContext());
//        friends = db.getAllFriends();
//    }
//
//    public List<Friend> getUpdatedFriends() {
//        loadFriends();
//        return friends;
//    }
//}
