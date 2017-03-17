package com.example.peter.mystaffapplication.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.peter.mystaffapplication.R;
import com.example.peter.mystaffapplication.adapter.MyRecyclerAdapter;
import com.example.peter.mystaffapplication.test.MyServer;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private String path= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        init();
//       startService(new Intent(this, MyServer.class));
    }
    private void init(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_test);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setAdapter(new MyRecyclerAdapter());
    }

}
