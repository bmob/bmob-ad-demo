package cn.bmob.ad.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created on 17/12/8 14:18
 * @author zhangchaozhou
 */

public class FeedsViewpager extends ViewPager {
    public FeedsViewpager(Context context) {
        super(context);
    }

    public FeedsViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof FeedsWebView) {
            return v.canScrollHorizontally(-dx);
        } else {
            return super.canScroll(v, checkV, dx, x, y);
        }
    }
}
