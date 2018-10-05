package com.restaurant.miina.s315579mappe2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.Friends.Friend;
import com.restaurant.miina.s315579mappe2.Restaurants.Restaurant;

public class ItemInputActivity extends AppCompatActivity {
    EditText name;
    EditText address;
    EditText phone;
    EditText type;
    Button addBtn;
    Button updateBtn;
    Button deleteBtn;
    TextView title;
    DBHandler db;
    long id;
    boolean isRestaurant;
    Restaurant restaurant;
    Friend friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_input);
        name = findViewById(R.id.nameInput);
        address = findViewById(R.id.addressInput);
        phone = findViewById(R.id.phoneInput);
        type = findViewById(R.id.typeInput);
        isRestaurant = getIntent().getStringExtra("TARGET").equals("RESTAURANT");

        if(!isRestaurant) {
            TextView typeLabel = findViewById(R.id.typeLabel);
            typeLabel.setVisibility(View.GONE);
            type.setVisibility(View.GONE);

        }
        addBtn = findViewById(R.id.addBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        title = findViewById(R.id.header);
        db = new DBHandler(this);

        if(getIntent().getStringExtra("OPTIONS").equals("UPDATE")) {
            Log.d("OPTIONS","UPDATE");
            addBtn.setVisibility(View.GONE);
            setTitle(true);
            id = getIntent().getLongExtra("ID", 999999999);
            setValues(id);
        } else {
            Log.d("OPTIONS","ADD");
            updateBtn.setVisibility(View.GONE);
            deleteBtn.setVisibility(View.GONE);
        }

    }

    private void setTitle(boolean update) {
        if(update) {
            if(isRestaurant) {
                title.setText("Update Restaurant");
            } else {
                title.setText("Update Friend");
            }
        } else {
            if(isRestaurant) {
                title.setText("Add Restaurant");
            } else {
                title.setText("Add Friend");
            }
        }

    }

    private void setValues(long id) {
        if(isRestaurant) {
            Restaurant restaurant = db.getRestaurant(id);
            name.setText(restaurant.getName());
            address.setText(restaurant.getAddress());
            phone.setText(restaurant.getPhone());
            type.setText(restaurant.getType());
        } else {
            Friend friend = db.getFriend(id);
            name.setText(friend.getName());
            address.setText(friend.getAddress());
            phone.setText(friend.getPhone());
        }

    }

    public void addNewItem(View view) {
        boolean ok = checkValues();

        if(!ok)
            return;

        if(isRestaurant) {
            restaurant = new Restaurant(name.getText().toString(),phone.getText().toString(),address.getText().toString(),type.getText().toString());
            db.regRestaurant(restaurant);
        } else {
            friend = new Friend(name.getText().toString(),phone.getText().toString(),address.getText().toString());
            db.regFriend(friend);
        }

        setResult(RESULT_OK);
        finish();
    }

    private boolean checkValues() {
        String nameText = name.getText().toString();
        String addressText = address.getText().toString();
        String phoneText = phone.getText().toString();
        String typeText = type.getText().toString();

        if(nameText.isEmpty() || addressText.isEmpty() || phoneText.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void updateItem(View view) {
        boolean ok = checkValues();

        if(!ok)
            return;

        if(id == 999999999) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        if(isRestaurant) {
            restaurant = new Restaurant(id, name.getText().toString(), phone.getText().toString(), address.getText().toString(), type.getText().toString());
            db.updateRestaurant(restaurant);
        } else {
            friend = new Friend(id, name.getText().toString(), phone.getText().toString(), address.getText().toString());
            db.updateFriend(friend);
        }
        setResult(RESULT_OK);
        finish();
    }

    public void deleteItem(View view) {

        if(id == 999999999) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        if(isRestaurant) {
            db.deleteRestaurant(id);
        } else {
            db.deleteFriend(id);
        }
        setResult(RESULT_OK);
        finish();
    }
}
