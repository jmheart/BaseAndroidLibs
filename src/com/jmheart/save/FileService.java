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
 *	日期：2015-4-26下午2:22:06
 * 文件图片的读存
 */
public class FileService {
	 /**  
     * 保存文件  
     * @param fileName 文件名称  
     * @param content  文件内容  
     * @throws IOException  
     */  
    public void saveToSDCard(String fileName, String content) throws IOException {  
 
        //考虑不同版本的sdCard目录不同，采用系统提供的API获取SD卡的目录  
        File file = new File(Environment.getExternalStorageDirectory(),fileName);  
        if(!file.isDirectory()){
        	file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);  
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();  
    } 
    /**  
     * 读取文件内容  
     * @param fileName 文件名称  
     * @return 文件内容  
     * @throws IOException  
     */  
    public String read(String fileName) throws IOException {  
    	File file = new File(Environment.getExternalStorageDirectory(),fileName);
    	if(file.exists()){
    		FileInputStream fileInputStream=new FileInputStream(file); 
            //把每次读取的内容写入到内存中，然后从内存中获取  
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
            byte[] buffer = new byte[1024];  
            int len =0;  
            //只要没读完，不断的读取  
            while((len=fileInputStream.read(buffer))!=-1){  
                outputStream.write(buffer, 0, len);  
            }  
            //得到内存中写入的所有数据  
            byte[] data = outputStream.toByteArray();  
            fileInputStream.close();
            return new String(data);  
    	}
    	else
    		return "";
    	
    }  
    /**  
     * 保存图片  
     * @param b 图片资源  
     * @param strFileName  图片名称
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
     * 读取图片  
     * @param strFileName 图片名称  
     * @return 图片内容  
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
