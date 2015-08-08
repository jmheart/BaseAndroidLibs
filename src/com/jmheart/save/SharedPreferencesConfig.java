package com.jmheart.save;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author liujie
 * 
 *         �����ļ� ��SharedPreferences SD�� SQLite Database��
 */
public class SharedPreferencesConfig {

	// ��ֵ�Դ洢�ļ�����
	public final static String CONFIG_NAME = "Maternal_CONFIG";

	public synchronized Class<?> getDataConfig(Context context, Class<?> cls,
			String key) {
		return cls;
	}

	public static void clear(Context context)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				CONFIG_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}
	/**
	 * �����ַ�������
	 * 
	 * @param context
	 * @param keyName
	 * @param keyValue
	 */
	public synchronized static void saveStringConfig(Context context,
			String keyName, String keyValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				CONFIG_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(keyName, keyValue);
		editor.commit();
	}

	/**
	 * ������������
	 * 
	 * @param context
	 * @param keyName
	 * @param keyValue
	 */
	public synchronized static void saveIntConfig(Context context,
			String keyName, Integer keyValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				CONFIG_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(keyName, keyValue);
		editor.commit();
	}

	/**
	 * ���沼��������
	 * 
	 * @param context
	 * @param keyName
	 * @param keyValue
	 */
	public synchronized static void saveBoolConfig(Context context,
			String keyName, boolean keyValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				CONFIG_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(keyName, keyValue);
		editor.commit();
	}

	/**
	 * ��ȡ�ַ�������
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public synchronized static String getStringConfig(Context context,
			String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				CONFIG_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, "");
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public synchronized static int getIntConfig(Context context, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				CONFIG_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getInt(key, 0);
	}

	/**
	 * ��ȡ����������
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public synchronized static boolean getBoolConfig(Context context, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				CONFIG_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, false);
	}

	/**
	 * ��ȡ����������
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public synchronized static boolean getBoolConfig(Context context,
			String key, Boolean default_bool) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				CONFIG_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, default_bool);
	}
}
