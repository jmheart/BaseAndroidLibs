package com.jmheart.tools;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

public class CallPhone {

	/**
	 * ��绰
	 * @param c
	 * @param number
	 */
	public static void callphone(Context c,String number)
	{
		   //��intent��������绰  
        Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));  
        c.startActivity(intent);  
	}
	/**
	 * ������
	 * @param c
	 * @param number
	 * @param message
	 */
	public static void send2(Context c,String number, String message){
	    SmsManager smsm = SmsManager.getDefault();
	    short port = 1000;
	    PendingIntent pi = PendingIntent.getBroadcast(c, 0, new Intent(), 0);
	    smsm.sendDataMessage(number, null, port, message.getBytes(), pi, null);
	}
}
