package com.example.peter.mystaffapplication.interfaces.impl;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.peter.mystaffapplication.interfaces.IfactoryVolleyNet;

import java.util.HashMap;
import java.util.Map;


import static com.android.volley.Request.*;

/**
 * Created by peter on 2017/1/6.
 */

public class NetConnectImpl implements IfactoryVolleyNet {
    RequestQueue queue;
    Context context;
     Handler handler;
    public NetConnectImpl(Context context,Handler handler) {
        this.context = context;
        this.handler = handler;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void connectTo(String ip, String port) {
        queue = Volley.newRequestQueue(context);

    }

    @Override
    public String getStringFormPost(String url, final HashMap<String, String> map) {
        StringRequest request = new StringRequest(Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Message message = handler.obtainMessage();
//                message.obj = s;
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("string",s);
                message.setData(bundle);
                message.sendToTarget();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("S",volleyError.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;
            }
        };
        queue.add(request);
        return null;
    }

    @Override
    public Image getImageSave(String url, ImageView imageView) {
        return null;
    }
}
