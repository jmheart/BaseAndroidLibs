package com.jmheart.save;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * 	@author liujie
 *	���ڣ�2015-4-26����2:22:06
 * �ļ�ͼƬ�Ķ���
 */
public class FileService {
	 /**  
     * �����ļ�  
     * @param fileName �ļ�����  
     * @param content  �ļ�����  
     * @throws IOException  
     */  
    public void saveToSDCard(String fileName, String content) throws IOException {  
 
        //���ǲ�ͬ�汾��sdCardĿ¼��ͬ������ϵͳ�ṩ��API��ȡSD����Ŀ¼  
        File file = new File(Environment.getExternalStorageDirectory(),fileName);  
        if(!file.isDirectory()){
        	file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);  
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();  
    } 
    /**  
     * ��ȡ�ļ�����  
     * @param fileName �ļ�����  
     * @return �ļ�����  
     * @throws IOException  
     */  
    public String read(String fileName) throws IOException {  
    	File file = new File(Environment.getExternalStorageDirectory(),fileName);
    	if(file.exists()){
    		FileInputStream fileInputStream=new FileInputStream(file); 
            //��ÿ�ζ�ȡ������д�뵽�ڴ��У�Ȼ����ڴ��л�ȡ  
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
            byte[] buffer = new byte[1024];  
            int len =0;  
            //ֻҪû���꣬���ϵĶ�ȡ  
            while((len=fileInputStream.read(buffer))!=-1){  
                outputStream.write(buffer, 0, len);  
            }  
            //�õ��ڴ���д�����������  
            byte[] data = outputStream.toByteArray();  
            fileInputStream.close();
            return new String(data);  
    	}
    	else
    		return "";
    	
    }  
    /**  
     * ����ͼƬ  
     * @param b ͼƬ��Դ  
     * @param strFileName  ͼƬ����
     * @throws IOException  
     */  
  	public  void savePhoto(Bitmap b,String strFileName){
  		try {
  			 File file = new File(Environment.getExternalStorageDirectory(),strFileName);  
  		        if(!file.isDirectory()){
  		        	file.createNewFile();
  		        }
  			FileOutputStream fos=new FileOutputStream(file);
  			if(fos!=null){
  				b.compress(Bitmap.CompressFormat.PNG, 80, fos);
  				fos.flush();
  				fos.close();
  			}
  		} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();	
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  	 /**  
     * ��ȡͼƬ  
     * @param strFileName ͼƬ����  
     * @return ͼƬ����  
     * @throws IOException  
     */  
  	@SuppressWarnings("unused")
	public Bitmap readPhoto(String strFileName){		
  		String path=Environment.getExternalStorageDirectory()+"/"+strFileName;
  		if(path!=null){
  		Bitmap bitmap=BitmapFactory.decodeFile(path);
  		return bitmap;
  		}
  		else
  			return null;
  		
  	}
}
