package com.example.peter.mystaffapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peter.mystaffapplication.R;

/**
 * Created by peter on 2017/2/21.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyTwoHolder>{
    Context context;
    @Override
    public MyTwoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyTwoHolder holder = new MyTwoHolder(LayoutInflater.from(context).inflate(R.layout.activity_log_in,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyTwoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class  MyTwoHolder extends RecyclerView.ViewHolder{

        public MyTwoHolder(View itemView) {
            super(itemView);

        }
    }
}
