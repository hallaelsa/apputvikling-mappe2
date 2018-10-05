//package com.restaurant.miina.s315579mappe2.Friends;
//
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import com.restaurant.miina.s315579mappe2.R;
//
//import java.util.List;
//
//import static android.app.Activity.RESULT_OK;
//
//public class FriendFragment extends Fragment {
//    ListView lv;
//    FriendAdapter adapter;
//    FriendViewModel model;
//
//    private FriendViewModel mViewModel;
//
//    public static FriendFragment newInstance() {
//        return new FriendFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        model = ViewModelProviders.of(this).get(FriendViewModel.class);
//        List<Friend> listFriends = model.getFriends();
//
//        View rootView = inflater.inflate(R.layout.friend_fragment, container, false);
//        adapter = new FriendAdapter(getActivity(), listFriends);
//        lv = (ListView)rootView.findViewById(R.id.list_view);
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                Intent i = new Intent(getActivity(), Add_Friend_Activity.class);
//                i.putExtra("OPTIONS","UPDATE");
//                i.putExtra("ID", id);
//                startActivityForResult(i, 201);
//
//            }
//        });
//
//        return rootView;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == 201 && resultCode == RESULT_OK) {
//            updateView();
//        }
//    }
//
//    public void updateView() {
//        adapter.refresh(model.getUpdatedFriends());
//    }
//
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(FriendViewModel.class);
//        // TODO: Use the ViewModel
//    }
//
//}
