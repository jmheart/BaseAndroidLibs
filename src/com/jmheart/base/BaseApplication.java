package com.jmheart.base;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;

import com.baidu.mapapi.SDKInitializer;
import com.jmheart.R;
import com.jmheart.abnormal.CrashHandler;
import com.jmheart.net.ApiHttpClient;
import com.loopj.android.http.AsyncHttpClient;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class BaseApplication extends Application {
	public static final String TAG = "BaseApplication";
	/**
	 * ����ģʽ
	 */
	public static boolean islog=true;
	/**
	 * ��������
	 */
	public static String HOST="http://wx.zgcainiao.com";
	/**
	 * �ӿڵ�ַ
	 */
	public static String API_URL="http://wx.zgcainiao.com/index.php/shop/";
	/*
	 * ͼƬ����
	 */
	public static DisplayImageOptions options ;
	public static ImageLoader imageLoader;
	/**
	 * FuBaoApplicationʵ�� ����ģʽ
	 */
	private static BaseApplication INSTANCE;

	public static void setInstance(BaseApplication i) {
		INSTANCE = i;
	}

	public static BaseApplication getInstance() {
		return INSTANCE;
	}

	public ArrayList<Activity> activities = new ArrayList<Activity>();

	/**
	 * �˳����е�Activity
	 */
	public void exitSystem() {
		for (Activity activity : activities) {
			activity.finish();
		}
		// ��Ӧ��ȫ���ˣ��ڳ����б����������ִ��applicatin.onCreate()
		System.exit(0);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		setInstance(this);
		//��ʼ���ٶȵ�ͼ
		SDKInitializer.initialize(getApplicationContext());
		appinitImageLoader(getApplicationContext());
		CrashHandler handler = new CrashHandler();
		handler.init(getApplicationContext());
		Thread.setDefaultUncaughtExceptionHandler(handler);
		// ��ʼ����������
        AsyncHttpClient client = new AsyncHttpClient();
        ApiHttpClient.setHttpClient(client);
	}

	/**
	 * ��ȡ��ǰӦ�õİ汾��
	 * 
	 * @param context
	 * @return
	 */
	public String getVersionCode(Context context) {
		try {
			PackageManager packageManager = getPackageManager();
			// getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
			PackageInfo packInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			String version = packInfo.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param context
	 * ͼƬ����
	 */
	public static void appinitImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app
		// Initialize ImageLoader with configuration.
		imageLoader=ImageLoader.getInstance();imageLoader.init(config.build());
		initImage();
	}
	public static void initImage()
	{
		 options = new DisplayImageOptions.Builder()  
	    .showImageForEmptyUri(R.drawable.ic_error)  // empty URIʱ��ʾ��ͼƬ  
	    .showImageOnFail(R.drawable.ic_error)       // ����ͼƬ�ļ� ��ʾͼƬ  
	    .resetViewBeforeLoading(false)  // default  
	    .delayBeforeLoading(1000)  
	    .cacheInMemory(false)           // default ���������ڴ�  
	    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// default  
	    .bitmapConfig(Bitmap.Config.ARGB_8888)    
	    .displayer(new SimpleBitmapDisplayer()) // default �������ö���������Բ�ǻ��߽���  
	    .handler(new Handler())                             // default  
	    .build();  
	}
	
}
