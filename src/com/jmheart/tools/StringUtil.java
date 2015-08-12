package com.jmheart.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liujie
 * @version 2015-04-01 09:02:25
 * 
 *          �ַ���������
 */
public class StringUtil {

	 public static String replace(String from, String to, String source) 
     {   
        if (source == null || from == null || to == null)   
            return null;   
        StringBuffer bf = new StringBuffer("");   
        int index = -1;   
        while ((index = source.indexOf(from)) != -1) 
        {   
            bf.append(source.substring(0, index) + to);   
            source = source.substring(index + from.length());   
            index = source.indexOf(from);   
        }   
        bf.append(source);   
        return bf.toString();   
    }
	/**
	 * ȥ���ַ��������пո�
	 */
	public static String remove(String resource) {
		StringBuffer buffer = new StringBuffer();
		int position = 0;
		char currentChar;

		while (position < resource.length()) {
			currentChar = resource.charAt(position++);
			if (currentChar != ' ')
				buffer.append(currentChar);
		}
		return buffer.toString();
	}

	public static String deNull(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	public static String Object2String(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	/**
	 * �����������Ƿ�Ϊ�հ�,����null��""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str);
	}

	public static boolean isBlank(Object str) {
		return str == null || str.toString().trim().length() == 0
				|| "null".equals(str);
	}
	  /**
	 * @param time
	 * @return
	 * ʱ���תʱ��
	 * 
	 */
	public static String getStrTime(String time) {
	        String re_StrTime = null;
	        SimpleDateFormat sdf = null;
	        if (time.equals("")||time==null) {
	                return "";
	        }
	        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        long loc_time = Long.valueOf(time);
	        re_StrTime = sdf.format(new Date(loc_time * 1000L));
	        return re_StrTime;
	}
	
	  /**
	 * @param time
	 * @return
	 * ʱ���תʱ��
	 * 
	 */
	public static String getaStrTime(String time) {
	        String re_StrTime = null;
	        SimpleDateFormat sdf = null;
	        if (time.equals("")||time==null) {
	                return "";
	        }
	        sdf = new SimpleDateFormat("yyyy��MM��dd��");
	        long loc_time = Long.valueOf(time);
	        re_StrTime = sdf.format(new Date(loc_time * 1000L));
	        return re_StrTime;
	}
}
