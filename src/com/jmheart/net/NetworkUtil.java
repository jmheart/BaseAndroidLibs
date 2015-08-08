package com.jmheart.net;



import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.jmheart.R;

/**
 * @version 2014��1��8�� 
 * ���������
 *  
 */
public class NetworkUtil {

	private static final int CMNET = 0; // ֱ��������
	private static final int CMWAP = 1; // ��Ҫ����
	private static final int WIFI = 2; // ����

	/**
	 * �ж������Ƿ����ӳɹ�
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isOnline(Context context) {
		boolean isOnline = false;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo != null) {
			if (networkInfo.isConnected()) {
				isOnline = true;
			}
		}
		return isOnline;
	}

	/**
	 * ���ص�ǰ���������״̬ CMNET 0 CMWAP 1 WIFI 2
	 * 
	 * @param context
	 * @return
	 */
	public static int getAPNType(Context context) {
		int netType = -1;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			if ("cmnet".equals(networkInfo.getExtraInfo().toLowerCase())) {
				netType = CMNET;
			} else {
				netType = CMWAP;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = WIFI;
		}
		return netType;
	}

	/**
	 * �õ��豸UUID
	 */
	public static String getUUID(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	/**
	 * �õ��豸�ͺ�
	 */
	public static String getDeviceModel(Context context) {
		return Build.MODEL.contains(Build.MANUFACTURER) ? Build.MODEL
				: Build.MANUFACTURER + " " + Build.MODEL;
	}

	/**
	 * û��������ʾ ����
	 * 
	 * @param context
	 */
	public static void showNoNetDialog(final Context context) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("��ʾ:");
		builder.setMessage("����ǰû�����磬�Ƿ��������磿");
		builder.setTitle(R.string.app_name);
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				  Intent intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);      
				context.startActivity(intent);
			}
		});

		builder.setNegativeButton("ȡ��", null);
		builder.show();
	}
}
