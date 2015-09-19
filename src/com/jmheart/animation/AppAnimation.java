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
 * 	��Ŀ��BaseAndroidLibs
 * 		@author liujie
 *	���ڣ�2015-8-21����11:15:37
 *   ����
 */
public class AppAnimation {

	Context context;
	public AppAnimation(Context mcontext)
	{
		context=mcontext;
	}
	/**
	  * ����
	 * @param view
	 */
	public void shark(View view)
	 {
		 Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);  
		 view.startAnimation(shake);  
	 }
	/**
	 * ����
	 */
	public void Jbview(View v) {

		AnimationSet animationSet = new AnimationSet(true);
		AlphaAnimation alphaAnimation = new AlphaAnimation((float) 0.5, 1);
		alphaAnimation.setDuration(1000);
		animationSet.addAnimation(alphaAnimation);
		v.startAnimation(animationSet);
	}
	/**
	 * �ײ�����
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
