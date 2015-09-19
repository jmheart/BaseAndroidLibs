package com.jmheart.net;

import android.annotation.SuppressLint;
import android.webkit.WebView;

/**
 * 	项目：BaseAndroidLibs
 * 		@author liujie
 *  加载网页
 *	日期：2015-9-12下午4:32:21
 */
public class LoadWebUrl {
	/**
	 * 显示本地的Html的内容
	 * @param htmlName
	 * @param web
	 */
	@SuppressLint("SetJavaScriptEnabled")
	public static void showHtml(String htmlName,WebView web){
		try {
			web.getSettings().setJavaScriptEnabled(true);
			web.loadUrl("file:///android_asset/"+htmlName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
	public static void showWebHtml(String urlhtmlName,WebView web){
		try {
			web.getSettings().setJavaScriptEnabled(true);
			web.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Version/7.0 Mobile/11D5145e Safari/9537.53");  
			web.loadUrl(urlhtmlName);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
