package com.example.peter.mystaffapplication.interfaces;

import android.media.Image;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * Created by peter on 2017/1/6.
 */

public interface IfactoryVolleyNet {
    void connectTo(String ip,String port);
    String getStringFormPost(String url, HashMap<String ,String> map);
    Image getImageSave(String url, ImageView imageView);
}
