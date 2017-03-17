package com.example.peter.mystaffapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.mystaffapplication.R;

/**
 * Created by peter on 2017/2/8.
 */

public class SlideMenuAdapter extends RecyclerView.Adapter<SlideMenuAdapter.MyHolder> {
    private Context context;
    public SlideMenuAdapter(Context context) {
        this.context  = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.slide_menu_item_layout,null,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv.setText("nihao");
        holder.iv.setImageResource(R.mipmap.aaaaa);
    }

//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
          iv = (ImageView) itemView.findViewById(R.id.iv_slide_show);
            tv = (TextView) itemView.findViewById(R.id.tv_slide_name);
        }
    }
}
