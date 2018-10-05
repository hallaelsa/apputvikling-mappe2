package com.restaurant.miina.s315579mappe2.Restaurants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.DBHandler;
import com.restaurant.miina.s315579mappe2.R;

public class Add_Restaurant_Activity extends AppCompatActivity {
    EditText name;
    EditText address;
    EditText phone;
    EditText type;
    Button addBtn;
    Button updateBtn;
    Button deleteBtn;
    DBHandler db;
    long id;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        name = (EditText)findViewById(R.id.nameInput);
        address = (EditText)findViewById(R.id.addressInput);
        phone = (EditText)findViewById(R.id.phoneInput);
        type = (EditText)findViewById(R.id.typeInput);
        addBtn = findViewById(R.id.addBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        db = new DBHandler(this);

        if(getIntent().getStringExtra("OPTIONS").equals("UPDATE")) {
            Log.d("OPTIONS","UPDATE");
            addBtn.setVisibility(View.GONE);
            TextView title = findViewById(R.id.addResHeader);
            title.setText("Update Restaurant");
            id = getIntent().getLongExtra("ID", 999999999);
            setValues(id);
        } else {
            Log.d("OPTIONS","ADD");
            updateBtn.setVisibility(View.GONE);
            deleteBtn.setVisibility(View.GONE);
        }

    }

    private void setValues(long id) {
        Restaurant restaurant = db.getRestaurant(id);
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        phone.setText(restaurant.getPhone());
        type.setText(restaurant.getType());
    }

    public void addNewRestaurant(View view) {
        boolean ok = checkValues();

        if(!ok)
            return;

        restaurant = new Restaurant(name.getText().toString(),phone.getText().toString(),address.getText().toString(),type.getText().toString());
        db.regRestaurant(restaurant);

        setResult(RESULT_OK);
        finish();
    }

    private boolean checkValues() {
        String nameText = name.getText().toString();
        String addressText = address.getText().toString();
        String phoneText = phone.getText().toString();
        String typeText = type.getText().toString();

        if(nameText.isEmpty() || addressText.isEmpty() || phoneText.isEmpty() || typeText.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void updateRestaurant(View view) {
        boolean ok = checkValues();

        if(!ok)
            return;

        if(id == 999999999) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        restaurant = new Restaurant(id, name.getText().toString(),phone.getText().toString(),address.getText().toString(),type.getText().toString());
        db.updateRestaurant(restaurant);

        setResult(RESULT_OK);
        finish();
        // must redirect somewhere... and update the fragment
    }

    public void deleteRestaurant(View view) {

        if(id == 999999999) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        db.deleteRestaurant(id);
        setResult(RESULT_OK);
        finish();
    }
}
