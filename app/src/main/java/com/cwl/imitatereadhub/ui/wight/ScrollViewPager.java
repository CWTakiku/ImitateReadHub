package com.cwl.imitatereadhub.ui.wight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ScrollViewPager extends ViewPager {

    boolean canScroll=true;
    public ScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public ScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isCanScroll(){
        return canScroll;
    }
    public void setCanScroll(boolean canScroll){
       this.canScroll=canScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (canScroll) {
            return super.onTouchEvent(ev);
        }else {
            return false;//没有消费
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (canScroll) {
            return super.onInterceptTouchEvent(ev);//事件默认会被拦截，并将拦截到的事件交由当前View的onTouchEvent()进行处理。
        }else {
            return  false;//不拦截
        }
    }
}
