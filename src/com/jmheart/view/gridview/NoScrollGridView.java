package com.jmheart.view.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 	��Ŀ��FuBaoHealth
 * 		@author liujie
 *	���ڣ�2015-5-28����10:21:52
 * ��������gridview
 */
public class NoScrollGridView extends GridView{

     public NoScrollGridView(Context context, AttributeSet attrs){
          super(context, attrs);
     }

     public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
          int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
          super.onMeasure(widthMeasureSpec, mExpandSpec);
     }


}