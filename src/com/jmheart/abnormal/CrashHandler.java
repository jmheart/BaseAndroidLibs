package com.jmheart.abnormal;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author liujie
 * @version 2015��04��01�� ����09:17:58
 * 
 *          �����쳣 ���洢���ļ���
 */
public class CrashHandler implements UncaughtExceptionHandler {

	public static final String TAG = "CrashHandler";

	/**
	 * �Ƿ�����־���, ��Debug״̬�¿���, ��Release״̬�¹ر���������������
	 */
	public static final boolean DEBUG = true;

	/**
	 * �����Context����
	 */ 
	private Context mContext;

	/**
	 * CrashHandlerʵ��
	 */
	private static CrashHandler INSTANCE;

	/**
	 * ��ȡCrashHandlerʵ�� ����ģʽ
	 */
	public static CrashHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CrashHandler();
		return INSTANCE;
	}

	/**
	 * ϵͳĬ�ϵ�UncaughtException������
	 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	/**
	 * �����洢�豸��Ϣ���쳣��Ϣ
	 */
	private Map<String, String> infos = new HashMap<String, String>();


	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");// ���ڸ�ʽ������,��Ϊ��־�ļ�����һ����

	/**
	 * ��ʼ�� ע��Context���� ��ȡϵͳĬ�ϵ�UncaughtException������ ���ø�CrashHandlerΪ�����Ĭ�ϴ�����
	 * 
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;
		/**
		 * ��ȡϵͳĬ�ϵ�UncaughtException������
		 */
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		/**
		 * ���ø�CrashHandlerΪ����Ĭ�ϵĴ�����
		 */
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * ��UncaughtException����ʱ��ת��ú���������
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// ����û�û�д�������ϵͳĬ�ϵ��쳣������������
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			// Sleepһ����������
			// �����߳�ֹͣһ����Ϊ����ʾToast��Ϣ���û���Ȼ��Kill����
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Log.e(TAG, "Error : ", e);
			}
			// �˳�����
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(10);
		}
	}

	/**
	 * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����. �����߿��Ը����Լ���������Զ����쳣�����߼�
	 * 
	 * @param ex
	 * @return true:��������˸��쳣��Ϣ;���򷵻�false
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return true;
		}
		// ʹ��Toast����ʾ�쳣��Ϣ
		new Thread() {
			@Override
			public void run() {
				// Toast ��ʾ��Ҫ������һ���̵߳���Ϣ������
				Looper.prepare();
				Looper.loop();
			}
		}.start();

		// �ռ��豸������Ϣ
		collectDeviceInfo(mContext);
		// ���������־�ļ�
		saveCrashInfo2File(ex);

		return true;
	}

	
	private void collectDeviceInfo(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (info != null) {
				String versionName = info.versionName == null ? "null"
						: info.versionName;
				String versionCode = info.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (Exception e) {
			Log.i(TAG, "an error occured when collect package info", e);
		}
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
				Log.d(TAG, field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				Log.e(TAG, "an error occured when collect crash info", e);
			}
		}
	}

	private String saveCrashInfo2File(Throwable ex) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\r\n");
		}
		Log.e(TAG, infos.toString());
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		ex.printStackTrace(pw);
		Throwable cause = ex.getCause();
		// ѭ���Ű����е��쳣��Ϣд��writer��
		while (cause != null) {
			cause.printStackTrace(pw);
			cause = cause.getCause();
		}
		pw.close();// �ǵùر�
		String result = writer.toString();
		Log.e(TAG, result.toString());
		sb.append(result);
		try {
			uploadFile(sb.toString()+"");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*// �����ļ�
		try {
			long timetamp = System.currentTimeMillis();
			String time = format.format(new Date());
			String fileName = "acrash-" + time + "-" + timetamp + ".log";
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				File dir = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + File.separator + "����������־");
				if (!dir.exists())
					dir.mkdir();
				FileOutputStream fos = new FileOutputStream(new File(dir,
						fileName));
				fos.write(sb.toString().getBytes());
				fos.close();
			}
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}
    /**
 * @param path
 *            Ҫ�ϴ����ļ�·��
 * @param url
 *            ����˽���URL
 * @throws Exception
 */
 public static void uploadFile(String logfile) throws Exception {
	
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("uploadfile", logfile);
		// �ϴ��ļ�
		client.post("http://shandi.zgcainiao.com/index.php/Api/AndroidLog/witelog", params, new AsyncHttpResponseHandler() {
		
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				
			}

		});
	} 

}
