package com.jmheart.view.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * �����testview
 * 	��Ŀ��BaseAndroidLibs
 * 		@author liujie
 *	���ڣ�2015-10-13����11:37:38
 */
public class AlwaysMarqueeTextView extends TextView {

	public AlwaysMarqueeTextView(Context context) {
	super(context);
	}
	
	public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
	super(context, attrs);
	}
	
	public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
	}
	
	@Override
	public boolean isFocused() {
	return true;
	}
}