package com.example.peter.mystaffapplication.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.peter.mystaffapplication.R;

/**
 * Created by xalo on 16/7/23.
 */
public class aaaa extends HorizontalScrollView {
    boolean once = false;
    LinearLayout linearLayout;
    int scrollWidth;
//在父布局准备加载子布局时调用
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            //初始化侧滑布局
            linearLayout = (LinearLayout) findViewById(R.id.ll);
            once = true;
        }

    }
//在修改子布局的过程中调用
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            //获得侧滑菜单宽度
            scrollWidth = linearLayout.getWidth();
            //隐藏侧滑菜单－－侧滑菜单在主界面的左侧（若侧滑菜单在主界面的右侧，为默认隐藏）
            this.scrollTo(scrollWidth, 0);
        }
    }
//捕捉手势动作
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                checkScoll();
                return true;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(ev);
    }
//检查滑动的距离
    private void checkScoll() {
        if (getScrollX() > scrollWidth / 2) {
            smoothScrollTo(scrollWidth, 0);
        } else {
            smoothScrollTo(0, 0);
        }
    }


    public aaaa(Context context) {
        super(context);
    }

    public aaaa(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public aaaa(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public aaaa(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
