package com.example.peter.mystaffapplication.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import android.widget.ImageView;

import com.example.peter.mystaffapplication.R;



public class SplashActivity extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        full(false);
        //隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //隐藏状态栏
//        //定义全屏参数
//        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        //获得当前窗体对象
//        Window window=this.getWindow();
//        //设置当前窗体为全屏显示
//        window.setFlags(flag, flag);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        init();
        CountDownTimer timer  = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(SplashActivity.this,LogInActivity.class);
                startActivity(i);
                finish();
            }
        };
        timer.start();
    }
    //初始化控件
    private void init(){
        imageView = (ImageView) findViewById(R.id.image_splash);
        animationStart();
    }

    //动画
    private void animationStart(){
        AnimationDrawable a = (AnimationDrawable) imageView.getBackground();
        a.start();
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
