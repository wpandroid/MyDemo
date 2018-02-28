package com.example.stopgap.mydemo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wp on 2017/12/19.
 */

public class CompatViewPager extends ViewPager {
    public CompatViewPager(Context context) {
        super(context);
    }

    public CompatViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 决定我们是否想要拦截这个手势，如果返回true,
     * onMotionEvent就会接受到事件，并且在其中发生滑动的操作.
     * 所以这段代码的原理是：当ViewPager和SwipeRefreshLayout滑动冲突的时候直接返回true,
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            return true;
        }
    }
}
