package com.jmheart.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BaseActivity extends Activity implements OnClickListener{

	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @param msg
	 * ¥Ú”°log
	 */
	public void showToast(String msg)
	{
		Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
	}
	/**
	 * @param msg
	 * ¥Ú”°log
	 */
	public void showLog(String msg)
	{
		Log.i("log¥Ú”°£∫", ""+msg);
	}

}
