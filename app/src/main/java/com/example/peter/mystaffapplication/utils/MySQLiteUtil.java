package com.example.peter.mystaffapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * Created by peter on 2017/2/10.
 */

public class MySQLiteUtil extends SQLiteOpenHelper {
    public MySQLiteUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
       RequestQueue queue= Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, "url", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table test"+"(id Integer primary key autoincrement, name 1, age 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
