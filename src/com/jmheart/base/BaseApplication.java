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
	 * 调试模式
	 */
	public static boolean islog=true;
	/**
	 * 主机域名
	 */
	public static String HOST="http://wx.zgcainiao.com";
	/**
	 * 接口地址
	 */
	public static String API_URL="http://wx.zgcainiao.com/index.php/shop/";
	/*
	 * 图片缓存
	 */
	public static DisplayImageOptions options ;
	public static ImageLoader imageLoader;
	/**
	 * FuBaoApplication实例 单例模式
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
	 * 退出所有的Activity
	 */
	public void exitSystem() {
		for (Activity activity : activities) {
			activity.finish();
		}
		// 把应用全清了，在程序列表启动程序会执行applicatin.onCreate()
		System.exit(0);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		setInstance(this);
		//初始化百度地图
		SDKInitializer.initialize(getApplicationContext());
		appinitImageLoader(getApplicationContext());
		CrashHandler handler = new CrashHandler();
		handler.init(getApplicationContext());
		Thread.setDefaultUncaughtExceptionHandler(handler);
		// 初始化网络请求
        AsyncHttpClient client = new AsyncHttpClient();
        ApiHttpClient.setHttpClient(client);
	}

	/**
	 * 获取当前应用的版本号
	 * 
	 * @param context
	 * @return
	 */
	public String getVersionCode(Context context) {
		try {
			PackageManager packageManager = getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
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
	 * 图片缓存
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
	    .showImageForEmptyUri(R.drawable.ic_error)  // empty URI时显示的图片  
	    .showImageOnFail(R.drawable.ic_error)       // 不是图片文件 显示图片  
	    .resetViewBeforeLoading(false)  // default  
	    .delayBeforeLoading(1000)  
	    .cacheInMemory(false)           // default 不缓存至内存  
	    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// default  
	    .bitmapConfig(Bitmap.Config.ARGB_8888)    
	    .displayer(new SimpleBitmapDisplayer()) // default 可以设置动画，比如圆角或者渐变  
	    .handler(new Handler())                             // default  
	    .build();  
	}
	
}
