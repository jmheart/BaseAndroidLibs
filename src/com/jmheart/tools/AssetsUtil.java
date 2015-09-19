package com.jmheart.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

import org.apache.http.protocol.HTTP;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.Toast;
/**
 * AssetsĿ¼�����࣬���ڷ��ʸ���AssetsĿ¼�µ��ļ�
 * @author lee
 *
 */
public class AssetsUtil {
	private Context context;
	private AssetManager assets;

	public AssetsUtil(Context context) {
		this.context = context;
		assets = context.getAssets();
	}
	/**
	 * ��װassetsĿ¼�µ��ļ�
	 * @param fileName �ļ�����
	 */
	public void installapk(String fileName) {
		try {
			InputStream stream = assets.open(fileName);
			if (stream == null) {
				Toast.makeText(context, "�ļ�������", Toast.LENGTH_SHORT).show();
				return;
			}

			String folder = "/mnt/sdcard/sm/";
			File f = new File(folder);
			if (!f.exists()) {
				f.mkdir();
			}
			String apkPath = "/mnt/sdcard/sm/test.apk";
			File file = new File(apkPath);
			if(!file.exists())
				file.createNewFile();
			writeStreamToFile(stream, file);
			installApk(apkPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * ��ʾ���ص�Html������
	 * @param htmlName
	 * @param web
	 */
	@SuppressLint("SetJavaScriptEnabled")
	public void showHtml(String htmlName,WebView web){
		try {
			web.getSettings().setJavaScriptEnabled(true);
			web.loadUrl("file:///android_asset/"+htmlName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ִ��JS�ļ��еĴ���
	 * @param web  ����������
	 * @param handler  ���̵߳ı�־
	 * @param jsName  JS�ļ�����
	 */
	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	public void excuteJs(final WebView web,final Handler handler,final String jsName){
		try {
			final InputStreamReader in = new InputStreamReader(assets.open(jsName));
			final StringWriter write = new StringWriter();
			char[] ch = new char[1024];
			int len = 0;
			while((len = in.read(ch)) != -1){
				write.write(ch, 0, len);
			}
			web.getSettings().setJavaScriptEnabled(true);
			web.addJavascriptInterface(new Object(){
				@SuppressWarnings("unused")
				public void clickOnAndroid() {  
					handler.post(new Runnable() {
						@Override
						public void run() {
							try {
								web.loadUrl("javascript:"+write.toString());
								write.close();
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
				}  
			}, "demo");
			web.loadData("<html><head></head><body onload=\"javascript:window.demo.clickOnAndroid()\"></body></html>", "text/html", HTTP.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeStreamToFile(InputStream stream, File file) {
		try {
			OutputStream output = null;
			try {
				output = new FileOutputStream(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				try {
					final byte[] buffer = new byte[1024];
					int read;

					while ((read = stream.read(buffer)) != -1)
						output.write(buffer, 0, read);
					output.flush();
				} finally {
					output.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void installApk(String apkPath) {
		AppUtils app_util = new AppUtils(context);
		app_util.installApk(apkPath);
	}
}
