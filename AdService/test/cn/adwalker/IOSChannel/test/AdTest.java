/**
* <p>Title: AdTest.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date Dec 23, 2012
* @version 1.0
*/
package cn.adwalker.IOSChannel.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

/**
 * <p>Title: AdTest</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       Dec 23, 2012
 */
public class AdTest {
	 public static void main(String[] args) throws Exception {
		 String k = sendGet("http://221.122.127.84:8085/EScore_IOS_Service/common/comfirmActivateCheck.do?appid=xiao-iphone&deviceid=00:21:E9:71:BB:33&data1=速度发");		
		 System.out.println("result:"+k);			
		 System.out.println(new Date().getTime());
	}
		 
	 
	 public static String sendGet(String url) {   
	        String result = "";// 返回的结果   
	        BufferedReader in = null;// 读取响应输入流   
	        PrintWriter out = null;   
//	        StringBuffer sb = new StringBuffer();// 处理请求参数   
//	        String params = "{'Content':'00:26:08:AD:5F:23','AppleID':'1','Type':1,'StartDate':'2012-06-01 00','EndDate':'2012-06-01 23'}";// 编码之后的参数   
	        String params ="";
	        try {   
	            
	            // 创建URL对象   
	            java.net.URL connURL = new java.net.URL(url);   
	            // 打开URL连接   
	            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL   
	                    .openConnection();   
	            // 设置通用属性   
	            httpConn.setRequestMethod("GET");    //默认是post 大小写敏感
//	            httpConn.setRequestProperty("Accept", "application/json");   
//	            httpConn.setRequestProperty("Connection", "Keep-Alive");   
//	            httpConn.setRequestProperty("User-Agent",   
//	                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");   
//	            httpConn.setRequestProperty("Accept-Charset", "utf-8");  
//	            httpConn.setRequestProperty("Content-Type", "application/json");  
	            // 设置POST方式   
	            httpConn.setDoInput(true);   
	            httpConn.setDoOutput(true);   
	            // 获取HttpURLConnection对象对应的输出流   
	            out = new PrintWriter(httpConn.getOutputStream());   
	            // 发送请求参数   
	            out.write(params);   
	            // flush输出流的缓冲   
	            out.flush(); 
	            
	            // 定义BufferedReader输入流来读取URL的响应，设置编码方式   
	            in = new BufferedReader(new InputStreamReader(httpConn   
	                    .getInputStream(), "GBK"));   
	            String line;   
	            // 读取返回的内容   
	            while ((line = in.readLine()) != null) {   
	                result += line+"\r\n";   
	            }   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        } finally {   
	            try {   
	                if (out != null) {   
	                    out.close();   
	                }   
	                if (in != null) {   
	                    in.close();   
	                }   
	            } catch (IOException ex) {   
	                ex.printStackTrace();   
	            }   
	        }   
	        return result;   
	    } 
}
