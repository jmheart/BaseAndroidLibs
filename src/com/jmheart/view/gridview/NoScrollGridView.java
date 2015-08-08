package com.jmheart.view.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 	项目：FuBaoHealth
 * 		@author liujie
 *	日期：2015-5-28上午10:21:52
 * 不滑动的gridview
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