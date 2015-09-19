package com.jmheart.net;

import android.annotation.SuppressLint;
import android.webkit.WebView;

/**
 * 	��Ŀ��BaseAndroidLibs
 * 		@author liujie
 *  ������ҳ
 *	���ڣ�2015-9-12����4:32:21
 */
public class LoadWebUrl {
	/**
	 * ��ʾ���ص�Html������
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
