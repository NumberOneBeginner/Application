package com.example.peter.mystaffapplication.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.peter.mystaffapplication.R;

public class LogInActivity extends Activity {
    Button btnLogOn;
    EditText et_number,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        full(false);
//        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_in);
        init();
    }

    private void init(){
        btnLogOn = (Button) findViewById(R.id.btn_logon);
        et_number = (EditText) findViewById(R.id.et_number);
        et_password = (EditText) findViewById(R.id.et_password);
//        btnLogOn.setEnabled(false);
//        btnLogOn.setBackgroundColor(0xf324324);
//        if (!et_number.getText().toString().equals("")&&!et_password.getText().toString().equals("")){
//            btnLogOn.setEnabled(true);
//        }
        btnLogOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
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
