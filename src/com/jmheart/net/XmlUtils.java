package com.jmheart.net;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class XmlUtils {
    /**
     * ����һ��bean��һ��list��xml�ļ��ṹ�ķ���
     * @param parser ������
     * @param listRoot �ڲ�ListBean��Ҫʵ���������һ����ʶ
     * @param listClazz ListBean.class
     * @param beanRoot ���Bean��Ҫʵ���������һ����ʶ
     * @param beanClazz Bean.class
     * @return һ��bean��һ��list�Ľ��
     * @throws Exception
     */
    public static <T,T1> ResultBeanAndList<T> getBeanByParseXml(String buffer , String listRoot , Class<T> listClazz ,String beanRoot , Class<T1> beanClazz) throws Exception{
    	 	XmlPullParser parser = Xml.newPullParser();//���XmlPullParser������     
	      
		    ByteArrayInputStream tInputStringStream = null;  
		    if (buffer != null && !buffer.trim().equals(""))  
		    {  
		       tInputStringStream = new ByteArrayInputStream(buffer.getBytes());  
		    }  
		    
		    parser.setInput(tInputStringStream, "utf-8");  
    	//�����
        ResultBeanAndList<T> result = null;
        //list  ���һ��item
        ArrayList<T> list = null;
        //�ڲ�ListBean
        T t = null;
        //���Bean
        T1 bean = null;
        //һ��������
        int count = 0 ;
        try {
            //��õ�ǰ��ǩ����
            int eventType = parser.getEventType();
            //�������xml�ļ�������ǩ����һ��һ�����½���
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType) {
                //�����xml�ļ���ʼ��ǩ�����ʼ��һЩ����
                case XmlPullParser.START_DOCUMENT:
                    //���Ľ��
                    result = new ResultBeanAndList<T>();
                    //list
                    list = new ArrayList<T>();
                    //��list���뵽result�У���ǰlist�ǿյģ��Ⱥ�����������ݺ󣬾Ͳ��ǿ���
                    result.setList(list);
                    break;
                //��ʼ��ǩ
                case XmlPullParser.START_TAG:
                    //��ñ�ǩ������
                    String tagName = parser.getName();
                    //����ڲ��ListBean�Ѿ�ʵ���������Ļ�
                    if (t != null) {
                        try {
                            //�жϵ�ǰ��ǩ��û��ListBean��������
                            Field field = listClazz.getField(tagName);
                            //���ListBean���е�ǰ��ǩ
                            if (field != null) {
                                //������+1
                                count++;
                                //��ȡ������ֵ����ListBean�ж�Ӧ������
                                field.set(t, parser.nextText());
                            }
                        } catch (Exception e) {
                            //���ListBean��û�е�ǰ��ǩ�����ֱ���������ʲô����ִ�У�Ȼ���ټ���������
                            
                        }
                    //�������Bean�Ѿ�ʵ���������Ļ�
                    }else if (bean != null) {
                        try {
                            //�жϵ�ǰ��ǩ��û��Bean��������
                            Field field = beanClazz.getField(tagName);
                            //���Bean���е�ǰ��ǩ
                            if (field != null) {
                                //������+1
                                count++;
                                //��ȡ������ֵ����Bean�ж�Ӧ������
                                field.set(bean, parser.nextText());
                            }
                        } catch (Exception e) {
                            //���Bean��û�е�ǰ��ǩ�����ֱ���������ʲô����ִ�У�Ȼ���ټ���������
                        }
                    }
                    //�����ǰ��ǩΪ���Ǵ�����ڲ����ǩ��˵��ListBean��Ҫʵ����������
                    if (tagName.equals(listRoot)) {
                        //��ListBeanʵ��������
                        t = listClazz.newInstance();
                    }
                    //�����ǰ��ǩΪ���Ǵ�����ڲ����ǩ��˵��Bean��Ҫʵ����������
                    if (tagName.equals(beanRoot)) {
                        //��Beanʵ��������
                        bean = beanClazz.newInstance();
                    }
                    break;
                //������ǩ
                case XmlPullParser.END_TAG:
                    //�����ǰ��ǩΪ</item>
                    if (listRoot.equalsIgnoreCase(parser.getName())) {
                        //���ListBean��Ϊ��
                        if (t != null) {
                            //���浽list�У�ͬʱҲ���浽��result�У���Ϊlist�Ѿ��Ǳ�����result���ˣ�
                            //ֻ�����ղ�û��ֵ��������ֵ��
                            list.add(t);
                            //���Ұ�ListBean�ÿգ���Ϊ�������кö��item
                            t = null;
                        }
                    //�����ǰ��ǩΪ</root>
                    }else if (beanRoot.equalsIgnoreCase(parser.getName())) {
                        //��Bean���浽result��
                        result.setBean(bean);
                    }
                    break;
                }
                //�ƶ�����һ����ǩ
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //���������Ϊ0˵��û�н������κ�����
        if (count == 0) {
            //��result�ÿվͿ�����
            result = null;
        }
        //��result����
        return result;
        
    }
}