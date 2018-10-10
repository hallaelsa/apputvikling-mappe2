package com.restaurant.miina.s315579mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.restaurant.miina.s315579mappe2.Models.Friend;
import com.restaurant.miina.s315579mappe2.Models.Order;
import com.restaurant.miina.s315579mappe2.Models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    static String TABLE_RESTAURANTS = "Restaurants";
    static String TABLE_FRIENDS = "Friends";
    static String TABLE_ORDER = "Orders";
    static String TABLE_ORDER_FRIENDS = "Order_Friends";
    static String KEY_ID = "_ID";
    static String KEY_FRIEND_ID = "Friend_id";
    static String KEY_RES_ID = "Restaurant_id";
    static String KEY_ORDER_ID = "Order_id";
    static String KEY_NAME = "Name";
    static String KEY_PH_NO = "Phone";
    static String KEY_ADDRESS = "Address";
    static String KEY_RES_TYPE = "Type";
    static String KEY_DATE = "Date";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "Restaurant_app";

    private static String CREATE_TABLE_RESTAURANTS = "CREATE TABLE " + TABLE_RESTAURANTS + "(" + KEY_ID +
            " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO +
            " TEXT," + KEY_ADDRESS + " TEXT," + KEY_RES_TYPE + " TEXT" + ")";

    private static String CREATE_TABLE_FRIENDS = "CREATE TABLE " + TABLE_FRIENDS + "(" + KEY_ID +
            " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO +
            " TEXT," + KEY_ADDRESS + " TEXT" + ")";

    private static String CREATE_TABLE_ORDER = "CREATE TABLE " + TABLE_ORDER + "(" + KEY_ID +
            " INTEGER PRIMARY KEY," + KEY_RES_ID +
            " INTEGER," + KEY_DATE +
            " DATETIME" +")";

    // koblingstabell mellom Order og Add_Friend_Activity
    private static String CREATE_TABLE_ORDER_FRIENDS = "CREATE TABLE " + TABLE_ORDER_FRIENDS + "(" + KEY_ID +
            " INTEGER PRIMARY KEY," + KEY_ORDER_ID +
            " INTEGER," + KEY_FRIEND_ID + " INTEGER" +")";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESTAURANTS);
        db.execSQL(CREATE_TABLE_FRIENDS);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_ORDER_FRIENDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER_FRIENDS);
        onCreate(db);
    }

    //---------------------------------- FRIEND -----------------------------------

    // register friend
    public long regFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, friend.getName());
        values.put(KEY_ADDRESS, friend.getAddress());
        values.put(KEY_PH_NO, friend.getPhone());

        long friend_id = db.insert(TABLE_FRIENDS, null, values);
        db.close();

        return  friend_id;
    }

    // update friend
    public int updateFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, friend.getName());
        values.put(KEY_ADDRESS, friend.getAddress());
        values.put(KEY_PH_NO, friend.getPhone());

        // updating row
        int updated_rows = db.update(TABLE_FRIENDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(friend.get_ID()) });
        db.close();

        return updated_rows;
    }

    // delete friend
    public void deleteFriend(long friend_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FRIENDS, KEY_ID + " = ?",
                new String[] { String.valueOf(friend_id) });
        db.close();
    }

    // list all friends
    public List<Friend> getAllFriends() {
        List<Friend> friends = new ArrayList<Friend>();
        String selectQuery = "SELECT  * FROM " + TABLE_FRIENDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Friend f = new Friend();
                f.set_ID(c.getLong(c.getColumnIndex(KEY_ID)));
                f.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                f.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                f.setPhone(c.getString(c.getColumnIndex(KEY_PH_NO)));
                friends.add(f);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return friends;
    }

    // get one friend
    public Friend getFriend(long friend_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String orderQuery = "SELECT  * FROM " + TABLE_FRIENDS + " WHERE "
                + KEY_ID + " = " + friend_id;

        Cursor c = db.rawQuery(orderQuery, null);

        if (c != null)
            c.moveToFirst();

        Friend f = new Friend();
        f.set_ID(c.getLong(c.getColumnIndex(KEY_ID)));
        f.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        f.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
        f.setPhone(c.getString(c.getColumnIndex(KEY_PH_NO)));
        c.close();
        db.close();

        return f;
    }

    //---------------------------------- RESTAURANT -----------------------------------

    // register restaurant
    public long regRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getName());
        values.put(KEY_ADDRESS, restaurant.getAddress());
        values.put(KEY_PH_NO, restaurant.getPhone());
        values.put(KEY_RES_TYPE, restaurant.getType());

        long res_id = db.insert(TABLE_RESTAURANTS, null, values);
        db.close();

        return  res_id;
    }

    // update restaurant
    public int updateRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.getName());
        values.put(KEY_ADDRESS, restaurant.getAddress());
        values.put(KEY_PH_NO, restaurant.getPhone());
        values.put(KEY_RES_TYPE, restaurant.getType());

        // updating row
        int updated_rows = db.update(TABLE_RESTAURANTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(restaurant.get_ID()) });
        db.close();

        return updated_rows;
    }

    // delete restaurant
    public void deleteRestaurant(long res_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANTS, KEY_ID + " = ?",
                new String[] { String.valueOf(res_id) });
        db.close();
    }

    // list all restaurants
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Restaurant r = new Restaurant();
                r.set_ID(c.getLong(c.getColumnIndex(KEY_ID)));
                r.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                r.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                r.setPhone(c.getString(c.getColumnIndex(KEY_PH_NO)));
                r.setType(c.getString(c.getColumnIndex(KEY_RES_TYPE)));
                restaurants.add(r);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return restaurants;
    }

    // get one restaurant
    public Restaurant getRestaurant(long res_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String orderQuery = "SELECT  * FROM " + TABLE_RESTAURANTS + " WHERE "
                + KEY_ID + " = " + res_id;
        Cursor c = db.rawQuery(orderQuery, null);

        if (c != null)
            c.moveToFirst();

        Restaurant r = new Restaurant();
        r.set_ID(c.getLong(c.getColumnIndex(KEY_ID)));
        r.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        r.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
        r.setPhone(c.getString(c.getColumnIndex(KEY_PH_NO)));
        r.setType(c.getString(c.getColumnIndex(KEY_RES_TYPE)));
        c.close();
        db.close();

        return r;
    }

    //---------------------------------- ORDER -----------------------------------

    // create order
    public long createOrder(Order order) {
        long res_id = order.getRestaurant().get_ID();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RES_ID, res_id);
        values.put(KEY_DATE, order.getDate());

        long order_id = db.insert(TABLE_ORDER, null, values);
        db.close();

        if(order.getFriends() == null)
            return order_id;
        
        for (Friend friend : order.getFriends()) {
            createOrderFriend(order_id, friend.get_ID());
        }

        return order_id;

    }

    // create order
    public int updateOrder(Order order) {
        Log.d("DB", "order id "+order.get_ID());
        long res_id = order.getRestaurant().get_ID();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RES_ID, res_id);
        values.put(KEY_DATE, order.getDate());

        int updated_rows = db.update(TABLE_ORDER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(order.get_ID()) });

        // must drop Order_FRIEND first!!!
        dropOrderFriends(order.get_ID());
        for (Friend friend : order.getFriends()) {
            Log.d("Friends id: ", String.valueOf(friend.get_ID()));
            createOrderFriend(order.get_ID(), friend.get_ID());
        }

        db.close();

        return updated_rows;

    }

    // get one order
    public Order getOrder(long order_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String orderQuery = "SELECT  * FROM " + TABLE_ORDER + " WHERE "
                + KEY_ID + " = " + order_id;
        Cursor c = db.rawQuery(orderQuery, null);

        if (c != null)
            c.moveToFirst();

        Order o = new Order();
        o.set_ID(c.getLong(c.getColumnIndex(KEY_ID)));
        o.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
        o.setRestaurant(getRestaurant(c.getLong(c.getColumnIndex(KEY_RES_ID))));
        o.setFriends(getFriendsForOrder(c.getLong(c.getColumnIndex(KEY_ID))));
        c.close();
        db.close();

        Log.d("getOrder()", String.valueOf(o.getFriends().size())+" friends found");

        return o;
    }

    private List<Friend> getFriendsForOrder(long order_id) {
        List<Friend> friends = new ArrayList<Friend>();
        String friendQuery = "SELECT * FROM " + TABLE_FRIENDS + " INNER JOIN "
                + TABLE_ORDER_FRIENDS + " ON "+TABLE_FRIENDS +"."+ KEY_ID + " = "+TABLE_ORDER_FRIENDS+"."
                + KEY_FRIEND_ID + " WHERE "+TABLE_ORDER_FRIENDS+"." + KEY_ORDER_ID + " = " + order_id;

        Log.d("getFriOrdQuery", friendQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(friendQuery, null);

        if (c.moveToFirst()) {
            do {
                Friend f = new Friend();
                f.set_ID(c.getLong(c.getColumnIndex(KEY_FRIEND_ID)));
                f.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                f.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                f.setPhone(c.getString(c.getColumnIndex(KEY_PH_NO)));
                friends.add(f);
               // Log.d("FriendsForOrder", "friend added!");
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return friends;

    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        String selectQuery = "SELECT  * FROM " + TABLE_ORDER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Order o = new Order();
                o.set_ID(c.getLong(c.getColumnIndex(KEY_ID)));
                o.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
                o.setRestaurant(getRestaurant(c.getLong(c.getColumnIndex(KEY_RES_ID))));
                o.setFriends(getFriendsForOrder(c.getLong(c.getColumnIndex(KEY_ID))));
                orders.add(o);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return orders;
    }

    public int deleteOrder(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted_rows = db.delete(TABLE_ORDER, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });

        dropOrderFriends(id);

        db.close();

        return deleted_rows;
    }

    // create many-to-many connection for Order_Friend
    private long createOrderFriend(long order_id, long friend_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ORDER_ID, order_id);
        values.put(KEY_FRIEND_ID, friend_id);

        long order_friend_id = db.insert(TABLE_ORDER_FRIENDS, null, values);
        db.close();

        return order_friend_id;
    }

    private int dropOrderFriends(long order_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted_rows = db.delete(TABLE_ORDER_FRIENDS, KEY_ORDER_ID + " = ?",
                new String[] { String.valueOf(order_id) });

        Log.d("DELETE", String.valueOf(deleted_rows)+" rows deleted");
        return deleted_rows;
    }

}
