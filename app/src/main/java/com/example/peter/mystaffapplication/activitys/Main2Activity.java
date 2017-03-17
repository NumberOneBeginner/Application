package com.example.peter.mystaffapplication.activitys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.peter.mystaffapplication.R;
import com.example.peter.mystaffapplication.adapter.SlideMenuAdapter;

public class Main2Activity extends Activity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //初始化ViewGroup
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.rv_a);
        //设置ViewGroup占满屏幕宽度
        viewGroup.getLayoutParams().width = getWindowWidth();
        init();
        setA();
    }
    //
    private void init(){
        rv = (RecyclerView) findViewById(R.id.rv_show);
    }
    //
    private void setA(){
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new SlideMenuAdapter(this));
    }
    //获得屏幕的宽度
    private int getWindowWidth() {
        WindowManager windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

}
