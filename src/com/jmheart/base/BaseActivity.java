package com.jmheart.base;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;

public class BaseActivity extends Activity implements OnClickListener,ValidationListener{
 
	public Validator validator;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		inintValida();
	}
	 private void inintValida()
	 {
		    validator = new Validator(this);
		    validator.setValidationListener(this);
	 }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @param msg
	 * 打印log
	 */
	public void showToast(String msg)
	{
		Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
	}
	/**
	 * @param msg
	 * 打印log
	 */
	public void showLog(String msg)
	{
		Log.i("log打印：", ""+msg);
	}
	/**
	 * @param v
	 * @return
	 * 判断是否为手机号码
	 */
	public static boolean CheckPhone(EditText v)
	{
		if (v.getText().toString().matches("[1][3578]\\d{9}")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * findViewById(int id)书写简化,无须强制转换、
	 * 
	 * @param id
	 *            控件的id
	 * @return 返回指定View
	 */
	@SuppressWarnings("unchecked")
	public final <E extends View> E getView(int id) {
		try {
			return (E) findViewById(id);
		} catch (ClassCastException e) {
			Log.e("Base", "Can't cast the View.", e);
			throw e;
		}

	}
	/*  表单验证框架
	 * (non-Javadoc)
	 * @see com.mobsandgeeks.saripaar.Validator.ValidationListener#onValidationSucceeded()
	 */
	@Override
	public void onValidationSucceeded() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onValidationFailed(List<ValidationError> errors) {
		// TODO Auto-generated method stub
		for (int i = 0; i < errors.size(); i++) {
			ValidationError validationError=errors.get(i);
			String message = validationError.getCollatedErrorMessage(this);
	        if (validationError.getView() instanceof EditText) {
	        	validationError.getView().requestFocus();
	            ((EditText) validationError.getView()).setError(message);
	        } else {
	            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	        }
	        
		}
		
	}
	

}
