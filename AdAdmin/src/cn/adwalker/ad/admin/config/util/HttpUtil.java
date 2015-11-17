package cn.adwalker.ad.admin.config.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/**
 * <p>http工具类</p>
 * @author jief
 *
 */
public class HttpUtil {

     public static String sendGet(String url) {   
		        String result = "";// 返回的结果   
		        BufferedReader in = null;// 读取响应输入流   
		        PrintWriter out = null;   
		        String params ="";
		        try {   
		            // 创建URL对象   
		            java.net.URL connURL = new java.net.URL(url);   
		            // 打开URL连接   
		            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL   
		                    .openConnection();   
		            // 设置通用属性   
		            httpConn.setRequestMethod("GET");    //默认是post 大小写敏感
		            httpConn.setReadTimeout(2000);		 //读超时时间设置
		            httpConn.setConnectTimeout(3000);	 //连接超时时间设置
//		            httpConn.setRequestProperty("Accept", "application/json");   
//		            httpConn.setRequestProperty("Connection", "Keep-Alive");   
//		            httpConn.setRequestProperty("User-Agent",   
//		                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");   
//		            httpConn.setRequestProperty("Accept-Charset", "utf-8");  
//		            httpConn.setRequestProperty("Content-Type", "application/json");  
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
//		            System.out.println("httpcon.getInputStream="+httpConn.getInputStream());
		            in = new BufferedReader(new InputStreamReader(httpConn   
		                    .getInputStream(), "UTF-8"));   
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
