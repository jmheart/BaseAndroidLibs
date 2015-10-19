package com.jmheart.base;

import java.util.List;

import com.jmheart.view.dialog.LoadingDialog;
import com.jmheart.view.listview.RefreshListView.OnLoadListener;
import com.jmheart.view.listview.RefreshListView.OnRefreshListener;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BaseFragment extends Fragment implements ValidationListener,OnClickListener,OnRefreshListener,OnLoadListener {
	LoadingDialog dialog;
	/**
	 * @param msg
	 * 打印log
	 */
	public void showToast(String msg)
	{
		Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
	}
	 public void showDialog()
	 {
		 dialog=new LoadingDialog(getActivity());
		 dialog.SetMessage("加载中...");
		 dialog.showDialog();
		 
	 }
	 public void showDialog(String msg)
	 {
		 dialog=new LoadingDialog(getActivity());
		 dialog.SetMessage(msg);
		 dialog.showDialog();
		 
	 }
	 public void dissDialog()
	 {
		 if (dialog!=null) {
			 dialog.dismissDialog();
		}
		 
	 }
	/**
	 * @param msg
	 * 打印log
	 */
	public void showLog(String msg)
	{
		if (BaseApplication.islog) {
			
			Log.i("log打印：", ""+msg);
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onValidationSucceeded() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onValidationFailed(List<ValidationError> errors) {
		// TODO Auto-generated method stub
		
	}
}
