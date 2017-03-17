package com.example.peter.mystaffapplication.activitys;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.mystaffapplication.R;
import com.example.peter.mystaffapplication.fragments.AssistantFragment;
import com.example.peter.mystaffapplication.fragments.MeFragment;
import com.example.peter.mystaffapplication.fragments.NewsroomFragment;
import com.example.peter.mystaffapplication.utils.MyHomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    private String titles[] = {"News room","Assistant", "Me"};
    private int[]imageView={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    android.support.v4.app.FragmentManager manager ;
    List<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        full(false);
        setContentView(R.layout.activity_home);

        init();
    }
    //初始化
    private void init(){
        addFragment();
        viewPager = (ViewPager) findViewById(R.id.vp_home);
        tabLayout = (TabLayout) findViewById(R.id.tab_home);
        manager = getSupportFragmentManager();
        MyHomeAdapter adapter = new MyHomeAdapter(manager,fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //先和viewpager关联在获取位置设置text。不关联设置会空指针
//        tabLayout.getTabAt(0).setText("News room");
//        tabLayout.getTabAt(1).setText("Assistant");
//        tabLayout.getTabAt(2).setText("Me");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }

    }
    public View getTabView(int position){
        //首先为子tab布置一个布局
        View v = LayoutInflater.from(this).inflate(R.layout.tablayout_item,null);
        TextView tv = (TextView) v.findViewById(R.id.tv_tab);
        tv.setText(titles[position]);
        ImageView iv = (ImageView) v.findViewById(R.id.img_tab);
        iv.setImageResource(imageView[position]);
        return v;
    }
    //
    private void addFragment(){
        fragments.add(new NewsroomFragment());
        fragments.add(new AssistantFragment());
        fragments.add(new MeFragment());
    }
    /**
     * @param enable   false 显示，true 隐藏
     */
    private void full(boolean enable) {
        // TODO Auto-generated method stub
        WindowManager.LayoutParams p =this.getWindow().getAttributes();
        if(enable){

            p.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;//|=：或等于，取其一

        }else{
            p.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);//&=：与等于，取其二同时满足，     ~ ： 取反

        }
        getWindow().setAttributes(p);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);}
}
