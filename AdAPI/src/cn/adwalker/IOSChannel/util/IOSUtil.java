/**
* <p>Title: IOSUtil.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-23
* @version 1.0
*/
package cn.adwalker.IOSChannel.util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.retry.RetryPolicies;
import cn.adwalker.retry.RetryPolicy;
import cn.adwalker.ad.util.ConfigUtil;



/**
 * <p>Title: IOSUtil</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-23
 */
public class IOSUtil {
	 private static final Logger log = LoggerFactory.getLogger(IOSUtil.class);
	
	/***
	 * 向广告主发送post数据
	* <p>Title: sendPost</p>
	* <p>Description:TODO</p>
	* @param url
	* @param params
	* @return
	* @return String
	* @throws
	 */
	public static String sendPost(String url, String params) {
		
		log.info(url);
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		// 编码之后的参数
		try {
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			httpConn.setRequestProperty("Accept-Charset", "utf-8");
			httpConn.setRequestProperty("Content-Type", "application/json");
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
			in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), "utf-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line + "\r\n";
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
	
	 public static String sendGet(String url) throws Exception{  
		 HttpURLConnection connection =null;
		 int  curRetries = 1;
		 while (true) {
			String result = "";// 返回的结果 
		    try{
		       URL getUrl = new URL(url);
		       // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
		       // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
		       connection= (HttpURLConnection) getUrl.openConnection();
		       // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
		       // add by jief 2013-12-05 start 
				connection.setConnectTimeout(Integer.parseInt(ConfigUtil
						.getString("connectionTimeOut")));
				connection.setReadTimeout(Integer.parseInt(ConfigUtil
						.getString("readTimeOut")));
				//add by jief 2013-12-05 end
		       connection.connect();
		       // 取得输入流，并使用Reader读取
		       BufferedReader reader = new BufferedReader(new InputStreamReader(
		               connection.getInputStream()));
		       String lines="";
		       while ((lines = reader.readLine()) != null) {
		    	   result += lines+"\r\n";   
		       }
		       reader.close();
		       // 断开连接
		       connection.disconnect();
		       return result;   
		   } catch (IOException e) {
			   handleConnectionFailure(curRetries++,e,connection,url);
	       }  
		  }
	 } 
	//add by jief for http retry start 2013-11-06 
//    private final  RetryLimited connectionRetryPolicy = 
     private static RetryPolicy retryPolicy= RetryPolicies.exponentialBackoffRetry(5,1l,TimeUnit.SECONDS);
	 private static void handleConnectionFailure(int curRetries, IOException ioe,
				HttpURLConnection connection,String url) throws IOException {
			System.out.println("io Exception :Thread" + Thread.currentThread().getName()
					+ "重发次数" + curRetries);
			connection.disconnect();
			final boolean retry;
			try {
				retry = retryPolicy.shouldRetry(ioe, curRetries);
			} catch (Exception e) {
				log.error(url+"is error");
				throw e instanceof IOException ? (IOException) e : new IOException(
						e);
			}
			if (!retry) {
				throw ioe;
			}
		}
	 
	/*****
	 * 将 inputStream 转化为String
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream in)throws IOException   { 
        StringBuffer out = new StringBuffer(); 
        byte[] b = new byte[4096]; 
        for (int n;(n = in.read(b)) != -1;){ 
                out.append(new String(b, 0, n)); 
        } 
        return out.toString(); 
	} 
	
	
	
	 /******
	  * 将流写入 文件
	  * @param fileName
	  * @param in
	  * @throws IOException
	  */
	 /*****
	 * 把json格式的字符串写到文件
	 * @param filePath
	 * @param sets
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String jsonString) throws IOException {
		
	    FileWriter fw = new FileWriter(filePath);
	    PrintWriter out = new PrintWriter(fw);
	    try{
		    out.write(jsonString);
		    out.println();
		    fw.close();
		    out.close();
	    }catch (Exception e) {
			// TODO: handle exception
		}finally{
			 fw.close();
			 out.close();
		}
	} 
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static Map  md5s(String plainText) {
		Map map =new HashMap();
		  try {
		   MessageDigest md = MessageDigest.getInstance("MD5");
		   md.update(plainText.getBytes());
		   byte b[] = md.digest();

		   int i;

		   StringBuffer buf = new StringBuffer("");
		   for (int offset = 0; offset < b.length; offset++) {
		    i = b[offset];
		    if (i < 0)
		     i += 256;
		    if (i < 16)
		     buf.append("0");
		    buf.append(Integer.toHexString(i));
		   }
		   String str = buf.toString();
		   //System.out.println("result: " + buf.toString());// 32位的加密
		   map.put("32", buf.toString());
		   //System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
		   map.put("16", buf.toString().substring(8, 24));
		  } catch (NoSuchAlgorithmException e) {		
		   e.printStackTrace();
		  }
		  
		  return map;
	}
	/**
	 * <p>https请求ssl协议</p>
	 * @param url
	 */
	public static String sendGetHttpS(String url)throws Exception{
		
	 	HttpsURLConnection connection =null;
		 int  curRetries = 1;
		 while (true) {
			String result = "";// 返回的结果 
		    try{
		       URL getUrl = new URL(url);
		       // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
		       // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
		       connection= (HttpsURLConnection) getUrl.openConnection();
		       // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
		       // add by jief 2013-12-05 start 
				connection.setConnectTimeout(Integer.parseInt(ConfigUtil
						.getString("connectionTimeOut")));
				connection.setReadTimeout(Integer.parseInt(ConfigUtil
						.getString("readTimeOut")));
				//add by jief 2013-12-05 end
		       connection.connect();
		       // 取得输入流，并使用Reader读取
		       BufferedReader reader = new BufferedReader(new InputStreamReader(
		               connection.getInputStream()));
		       String lines="";
		       while ((lines = reader.readLine()) != null) {
		    	   result += lines+"\r\n";   
		       }
		       reader.close();
		       // 断开连接
		       connection.disconnect();
		       return result;   
		   } catch (IOException e) {
			   handleConnectionFailure(curRetries++,e,connection,url);
	       }  
		  }
	}
	
	public static void main(String args[])throws Exception{
		String result=IOSUtil.sendGetHttpS("https://ws.tapjoyads.com/log_device_app?app_id=153e3d0f-8e81-4452-a3e2-39db54f55845&mac_address=10:9A:DD:25:1A:DF&library_version=server&device_ip=38.104.224.69&sdk_type=connect&&advertising_id=9E9BAAE5-EB33-4C88-9044-E10C47042A68");
		System.out.println(result+"ok");
	}
}
