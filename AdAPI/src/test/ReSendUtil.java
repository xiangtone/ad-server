package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import cn.adwalker.IOSChannel.picker.util.HttpClientUtils;
import cn.adwalker.IOSChannel.picker.util.StringUtil;

public class ReSendUtil {

	public static void eachLine(String filePath){

		try {			 
			 BufferedInputStream fis = new BufferedInputStream(new FileInputStream(new File(filePath)));
			 BufferedReader reader = new BufferedReader(new InputStreamReader(fis,"utf-8"),10*1024*1024); 
			 String line = "";  
			 Integer count=0;
	         while((line = reader.readLine()) != null){
	        	 String[] strs = line.split(" ");
	        	 if(strs.length>=7){
	        		 String param = strs[6];
	        		 if(StringUtil.trim(param).length()>0){
	        			 count++;
	        			 String url = "http://api.adwalker.cn/AdAPI"+param;
	        			 HttpClientUtils.readFromURL(url, null);
	        			 Thread.sleep(2000);
	        		 }
	        	 }
	        	
	         }
    		 System.out.println(count);
	         reader.close();  
	         fis.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}  
	}
	
	public static void main(String[] args){
		ReSendUtil.eachLine("E:/activate/activate231.txt");
	}
	
}
