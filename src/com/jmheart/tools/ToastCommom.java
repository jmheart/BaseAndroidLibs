package com.jmheart.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jmheart.R;

@SuppressLint("ResourceAsColor")
public class ToastCommom {
	
	private static ToastCommom toastCommom;
	
	private Toast toast;
	
	private static Context mcontext;
	
	private static ViewGroup mroot;
	private ToastCommom(){
	}
	public static ToastCommom createToastConfig(Context context,ViewGroup root){
		if (toastCommom==null) {
			toastCommom = new ToastCommom();
		}
		mcontext=context;
		mroot=root;
		return toastCommom;
	}
	
	/**
	 * œ‘ æToast
	 * @param context
	 * @param root
	 * @param tvString
	 */
	
	public void ToastShow(String tvString){
		View layout = LayoutInflater.from(mcontext).inflate(R.layout.toast,mroot);
		TextView text = (TextView) layout.findViewById(R.id.text);
		ImageView mImageView = (ImageView) layout.findViewById(R.id.iv);
		
		text.setText(tvString);
		toast = new Toast(mcontext);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}

}