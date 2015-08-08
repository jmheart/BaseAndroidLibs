package com.jmheart.view.viewpage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author liujie
 * @时间：2015-3-23下午11:59:30
 * @项目：MechanicsBao
 * @说明：重新viewpager 去掉滑动
 */
public class NoScollViewPager extends ViewPager{
	//滑动为false
	private boolean isCanScroll = false;
    //建立上下文
	public NoScollViewPager(Context context) {
		super(context);
	}
	public NoScollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public void setScanScroll(boolean isCanScroll) {
		this.isCanScroll = isCanScroll;
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
	}
    
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		//触摸监听
		if (isCanScroll) {
			return super.onTouchEvent(arg0);
		} else {
			return false;
		}

	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		//viewpager页面
		super.setCurrentItem(item, smoothScroll);
	}

	@Override
	public void setCurrentItem(int item) {
		//viewpager页面
		super.setCurrentItem(item);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		//滑动事件监听
		if (isCanScroll) {
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}

	}

}
