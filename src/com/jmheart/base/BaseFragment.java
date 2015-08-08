package com.jmheart.base;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BaseFragment extends Fragment implements OnClickListener{

	/**
	 * @param msg
	 * ¥Ú”°log
	 */
	public void showToast(String msg)
	{
		Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
	}
	/**
	 * @param msg
	 * ¥Ú”°log
	 */
	public void showLog(String msg)
	{
		Log.i("log¥Ú”°£∫", ""+msg);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
