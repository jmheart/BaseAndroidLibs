package com.jmheart.view.dialog;

import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Dialog;
import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jmheart.R;

public class LoadingDialog extends Dialog{

	Dialog loadingDialog;	// ����dialog
	Context context;
	int flag=0;
	public LoadingDialog(Context context) {
		super(context);
		this.context = context;
	}
	android.os.Handler handler=new android.os.Handler()
	{
		public void handleMessage(android.os.Message msg) {
			updateTitle();
		};
	};
	TextView tipTextView;
	String strText;
	public void SetMessage(String msg) {
		strText=msg;
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.net_dialog_loaing, null);// �õ�����view
		 tipTextView = (TextView) v.findViewById(R.id.text);// ��ʾ����
		// ���ü�����Ϣ
		tipTextView.setText(msg.substring(0, msg.length()-3));
		loadingDialog = new Dialog(context,R.style.Dialog_NoTitle);
		loadingDialog.setContentView(v);
		 Timer timer = new Timer();
	     timer.scheduleAtFixedRate(new MyTask(), 1, 1000);
	//	loadingDialog.setCancelable(false);//�������á����ؼ���ȡ��
	}
	private class MyTask extends TimerTask{

        @Override

         public void run() {

                Message message = new Message();
                handler.sendMessage(message);

        }

     }

   public void updateTitle()
    {
	 
	   flag++;
	   if (flag>3) {
		   flag=0;
		   tipTextView.setText(strText.substring(0, strText.length()-3));
	    }
	   else {
		   tipTextView.setText(strText.substring(0, strText.length()-3+flag));
	}
	   
    }
	/**
	 * ��dialog
	 */
	public void showDialog() {
		
			loadingDialog.show();
		
		
	}

	/**
	 * �ر�dialog
	 */
	public void dismissDialog() {
		
			loadingDialog.dismiss();
	}

}
