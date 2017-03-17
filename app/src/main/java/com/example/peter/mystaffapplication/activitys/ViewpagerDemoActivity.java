package com.example.peter.mystaffapplication.activitys;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.peter.mystaffapplication.R;

public class ViewpagerDemoActivity extends Activity {
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_demo);
        //自定义ActionBar
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_splash);//自定义ActionBar布局
        actionBar.getCustomView().setOnClickListener(new View.OnClickListener() {//监听事件
            @Override
            public void onClick(View arg0) {
                switch (arg0.getId()) {
//                    case R.id.back:
////                        showToast(R.string.finish);
//                        finish();
//                        break;
                    default:
                        break;
                }
            }
        });


        init();
    }
    private void init(){
        vp = (ViewPager) findViewById(R.id.vp_test);
//        vp.setAdapter();
    }

}
