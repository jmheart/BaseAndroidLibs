package com.jmheart.view.viewpage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author liujie
 * @ʱ�䣺2015-3-23����11:59:30
 * @��Ŀ��MechanicsBao
 * @˵��������viewpager ȥ������
 */
public class NoScollViewPager extends ViewPager{
	//����Ϊfalse
	private boolean isCanScroll = false;
    //����������
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
		//��������
		if (isCanScroll) {
			return super.onTouchEvent(arg0);
		} else {
			return false;
		}

	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		//viewpagerҳ��
		super.setCurrentItem(item, smoothScroll);
	}

	@Override
	public void setCurrentItem(int item) {
		//viewpagerҳ��
		super.setCurrentItem(item);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		//�����¼�����
		if (isCanScroll) {
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}

	}

}
