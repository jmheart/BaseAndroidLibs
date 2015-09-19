package com.jmheart.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.jmheart.R;

/**
 * 	项目：BaseAndroidLibs
 * 		@author liujie
 *	日期：2015-8-21上午11:15:37
 *   动画
 */
public class AppAnimation {

	Context context;
	public AppAnimation(Context mcontext)
	{
		context=mcontext;
	}
	/**
	  * 抖动
	 * @param view
	 */
	public void shark(View view)
	 {
		 Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);  
		 view.startAnimation(shake);  
	 }
	/**
	 * 淡入
	 */
	public void Jbview(View v) {

		AnimationSet animationSet = new AnimationSet(true);
		AlphaAnimation alphaAnimation = new AlphaAnimation((float) 0.5, 1);
		alphaAnimation.setDuration(1000);
		animationSet.addAnimation(alphaAnimation);
		v.startAnimation(animationSet);
	}
	/**
	 * 底部弹出
	 * @param v
	 */
	public void FrView(View v,float h)
	{
		Animation tranAnim=new TranslateAnimation( 0, 0, 0, h);
		tranAnim.setDuration(1000);  
        v.setAnimation(tranAnim);
        tranAnim.startNow(); 
	}
}
