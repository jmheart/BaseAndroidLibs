package com.jmheart.tools;

import java.lang.reflect.Method;
import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
/**
 * ���ù�����
 * @author lee
 *
 */
public class ControlsUtil {
	private Context context;
	public ControlsUtil(Context context) {
		this.context = context;
	}
	/**
	 * ��һ��UI�ؼ����û�ý���
	 * @param view  
	 */
	public void getFouces(View view){
		view.setFocusable(true);
		view.requestFocus();
		view.setFocusableInTouchMode(true);
	}
	/**
	 * ����ϵͳ����
	 * @param view
	 */
	public void hideSoftInputMethod(TextView view){
		if (android.os.Build.VERSION.SDK_INT <= 10) {  
			view.setInputType(InputType.TYPE_NULL);  
        } else {  
        	((Activity)context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
            try {  
                Class<TextView> cls = TextView.class;   
                Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",  
                        boolean.class);  
                setShowSoftInputOnFocus.setAccessible(true);  
                setShowSoftInputOnFocus.invoke(view, false);  
            } catch (Exception e) {  
                e.printStackTrace();
            }   
        }  
	}
	/**
	 * ��EditText�е�Ӣ����ĸ�Զ���д   ֻ�ǿ�������д�ˣ���ʵ�ʻ�ȡ���ַ���ҪtoUppCase()ת��һ�´�д
	 * @param et
	 */
	public void autoToUppCase(EditText et){
		et.setTransformationMethod(new AllCapTransformationMethod());
	}
	private static class AllCapTransformationMethod extends ReplacementTransformationMethod {
		@Override
		protected char[] getOriginal() {
			char[] aa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
			return aa;
		}
		@Override
		protected char[] getReplacement() {
			char[] cc = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
			return cc;
		}
	}
	/**
	 * 	��ֹ��Ļ�ƹ�䰵
	 * @param flag  true���ÿ���ȫ��ģʽ
	 */
	public void setKeepScreenOn(boolean flag) {
		if (flag) {
			((Activity) context).getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
					WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}
	}
}
