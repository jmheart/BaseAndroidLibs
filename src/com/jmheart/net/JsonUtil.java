package com.jmheart.net;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Json���ݽ��������֧࣬�ֵ�Json��ʽ��
 * 	 [{"":"","":""},{"":"","":""},{"":"","":""}]�� {"":"","":""}]  ����Ƕ��
 * 	{"":"","":{}}  ֻ֧�ֶ���Json����Ƕ��    ��������Ƕ�ף����Ƕ���ݲ���֧��
 * @author lee
 *
 */
public class JsonUtil {
	/**
	 * json���ݽ���     [{"":"","":""},{"":"","":""},{"":"","":""}]  ����Ƕ��ģʽ
	 * �ݲ�֧��[,,,,](������Ƕ�׵Ĳ���Json���������ͨ����)
	 * @param clazz
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> parserJsonToList(Class<T> clazz,String json) throws Exception{
		Field[] names =  clazz.getDeclaredFields();
		JSONArray array = new JSONArray(json);
		List<T> list = new ArrayList<T>();
		int length = array.length();
		for(int i = 0 ; i < length ; i++){
			T object = clazz.newInstance();
			JSONObject jsonobj = array.getJSONObject(i);
			for(int j = 0 ; j < names.length ; j++){
				names[j].set(object, jsonobj.get(names[j].getName()).toString());
			}
			list.add(object);
		}
		return list;
	}
	/**
	 * json���ݽ���    [{"":"","":""},{"":"","":""},{"":"","":""}]
	 * @param clazz  
	 * @param array
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> parserJsonToList(Class<T> clazz,JSONArray array) throws Exception{
		Field[] names =  clazz.getDeclaredFields();
		List<T> list = new ArrayList<T>();
		int length = array.length();
		for(int i = 0 ; i < length ; i++){
			T object = clazz.newInstance();
			JSONObject jsonobj = array.getJSONObject(i);
			for(int j = 0 ; j < names.length ; j++){
				names[j].set(object, jsonobj.get(names[j].getName()).toString());
			}
			list.add(object);
		}
		return list;
	}
	/**
	 * json���ݽ���  {}  ��ģʽ
	 * @param clazz
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static <T> T parserJsonToObject(Class<T> clazz,String json) throws Exception{
		Field[] names = clazz.getDeclaredFields();
		JSONObject jsonobj = new JSONObject(json);
		T object = clazz.newInstance();
		for(int i = 0 ; i < names.length ; i++){
			Object hah = jsonobj.get(names[i].getName());
			if(hah instanceof JSONObject){
				hah = parserJsonToObject(names[i].getType(),(JSONObject)hah);
			}
			names[i].set(object, hah);
		}
		return object;
	}
	/**
	 * json���ݽ���  {}  ��ģʽ
	 * @param clazz
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static <T> T parserJsonToObject(Class<T> clazz,JSONObject jsonobj) throws Exception{
		Field[] names = clazz.getDeclaredFields();
		T object = clazz.newInstance();
		for(int i = 0 ; i < names.length ; i++){
			Object hah = jsonobj.get(names[i].getName());
			if(hah instanceof JSONObject){
				hah = parserJsonToObject(names[i].getType(),(JSONObject)hah);
			}
			names[i].set(object, hah);
		}
		return object;
	}
}
