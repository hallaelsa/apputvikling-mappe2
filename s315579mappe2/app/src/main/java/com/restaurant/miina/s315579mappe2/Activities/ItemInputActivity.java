package com.restaurant.miina.s315579mappe2.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.DBHandler;
import com.restaurant.miina.s315579mappe2.R;

public abstract class ItemInputActivity extends AppCompatActivity {
    EditText name;
    EditText address;
    EditText phone;
    EditText type;
    TextView typeLabel;
    Button addBtn;
    Button updateBtn;
    Button deleteBtn;
    ActionBar actionbar;
    DBHandler db;
    long id;
    boolean isUpdate;
    abstract void setTitle(boolean update);
    abstract void setValues(long id);
    abstract void addItem();
    abstract void updateItem(long id);
    abstract void deleteItem(long id);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_input);

        Toolbar toolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.nameInput);
        address = findViewById(R.id.addressInput);
        phone = findViewById(R.id.phoneInput);
        type = findViewById(R.id.typeInput);
        typeLabel = findViewById(R.id.typeLabel);
        addBtn = findViewById(R.id.addBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        db = new DBHandler(this);
        isUpdate = getIntent().getStringExtra("OPTIONS").equals("UPDATE");

        if(isUpdate) {
            Log.d("OPTIONS","UPDATE");
            setTitle(true);
            id = getIntent().getLongExtra("ID", 999999999);
            setValues(id);
        } else {
            Log.d("OPTIONS","ADD");
            setTitle(false);
            deleteBtn.setVisibility(View.GONE);
        }

    }

    public void addNewItem(View view) {
        boolean ok = checkValues();

        if(!ok)
            return;

        if(isUpdate) {
            if(id == 999999999) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                return;
            }
            updateItem(id);
        } else {
            addItem();
        }

        setResult(RESULT_OK);
        finish();
    }


    private boolean checkValues() {
        String nameText = name.getText().toString();
        String addressText = address.getText().toString();
        String phoneText = phone.getText().toString();

        if(nameText.isEmpty() || addressText.isEmpty() || phoneText.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void deleteItem(View view) {
        if(id == 999999999) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        builder.setTitle(this.getResources().getString(R.string.deleteDialogTitle))
                .setMessage(this.getResources().getString(R.string.deleteDialogText))
                .setPositiveButton(this.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(id);
                        setResult(RESULT_OK);
                        finish();
                    }
                })
                .setNegativeButton(this.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                })
                .setCancelable(false)
                .show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                if(isUpdate) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    finish();
                }
                    break;
        }
        return true;
    }
}
