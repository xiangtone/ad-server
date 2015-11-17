/**
 * <p>Title: HttpClientUtil.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-30
 * @version 1.0
 */
package cn.adwalker.IOSChannel.picker.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import cn.adwalker.ad.util.Des3;



/**
 * <p>
 * Title: HttpClientUtil
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @date 2013-5-30
 */
public class HttpClientUtils {

    private static Logger logger = Logger.getLogger(HttpClientUtils.class);
    
    public static String readFromURL(String path,Map<String,String> map, String encoding) {
    	boolean b = true;
    	StringBuffer sbuf = new StringBuffer("");
        for(Entry<String, String> en:map.entrySet()){
        	String param = en.getKey();
        	String value = en.getValue();
        	sbuf.append(b?("?"+param+"="+value):("&"+param+"="+value));
        	if(b){b=false;}
        }
    	String url = path+sbuf.toString();
		return readFromURL(url, encoding, 1024, 5000,null);
	}
    public static String sendGet(String path){
    	return readFromURL(path, null);
    }
    
    public static String readFromURL(String path, String encoding,Map<String, String> headers) {
		return readFromURL(path, encoding, 1024, 5000,headers);
	}
    public static String readFromURL(String path, String encoding) {
		return readFromURL(path, encoding, 1024, 5000,null);
	}

    public static String readFromURL(String path, String encoding, int init, int timeout,Map<String, String> headers)  {
		return readFromURL(path, encoding, init, timeout, null,headers);
	}
    
	public static String readFromURL(String path, String encoding, int init, int timeout, Proxy proxy,Map<String, String> headers) {
		logger.logInfo("send get url  :"+path);
		long a = System.currentTimeMillis();
		StringBuffer bf = new StringBuffer();
		URL url = null;
		URLConnection con = null;
		BufferedReader in = null;
		if (encoding == null) {
			encoding = "UTF-8";
		}
		try {
			url = new URL(path);
			if (proxy != null) {
				con = url.openConnection(proxy);
			} else {
				con = url.openConnection();
			}
			con.setReadTimeout(timeout);
			CharBuffer chars = CharBuffer.allocate(init);
			con.setRequestProperty("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_2 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Version/7.0 Mobile/11D257 Safari/9537.53");
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					con.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), encoding));
			while (in.read(chars) > 0) {
				chars.flip();
				bf.append(chars);
				chars.clear();
			}
		} catch (Exception e) {
			logger.logInfo("Get url["+path+"] error! "+e.getMessage());
			e.printStackTrace();
			return "false";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			con = null;
			url = null;
		}
		logger.logInfo("send response "+(System.currentTimeMillis()-a)+": ms  "+bf.toString());
		return bf.toString();
	}
	
	public static String postFromUrl(String url,Map<String, String> map){
		if(map!=null){
			NameValuePair[] ps =new NameValuePair[map.size()];
			int i=0;
			StringBuffer sbuf =new StringBuffer();
			boolean b = true;
			for(Entry<String, String> e:map.entrySet()){
				NameValuePair p = new NameValuePair();
				p.setName(e.getKey());
				//p.setValue(StringUtil.encode(e.getValue(), "utf-8"));
				p.setValue(e.getValue());
				sbuf.append(b?(e.getKey()+"="+StringUtil.encode(e.getValue(), "utf-8")):("&"+e.getKey()+"="+StringUtil.encode(e.getValue(), "utf-8")));
				if(b){b=false;}
				ps[i]=p;
				i++;
			}
			logger.logInfo("send url:"+url+"?"+sbuf.toString());
			return postFromURL(url, ps);
		}else{
			logger.logInfo("Array of parameters may not be null");
		}
		return null;
	}
	
	
	
	
	public static String postFromURL(String url, NameValuePair[] msg) {
		logger.logInfo("send post url  :"+url);
		
		String strURL = url;
		String result = null;
		PostMethod post = null;
		try {
			post = new PostMethod(strURL);
			post.setRequestBody(msg);
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(post);
			if (post.getStatusCode() == HttpStatus.SC_OK) {
				result = post.getResponseBodyAsString();
			} else {
				result = post.getStatusLine().toString();
			}
		} catch (Exception e) {
			logger.logInfo("postFromURL Error!" + e.getMessage());
		} finally {
			post.releaseConnection();
		}
		logger.logInfo("send response  :"+result);
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("adId=23");
		sbuf.append("&appkey=AWAAB718ZDIG1173B3TO3JUVL80MCXIEHB");
		sbuf.append("&ac=1");
		sbuf.append("&adIds=");			
		String param = Des3.encode(sbuf.toString()); 
	    System.out.println(param);
	    Map<String,String> m = new HashMap<String, String>();
	    m.put("m",param);
	    System.out.println(HttpClientUtils.postFromUrl("http://localhost/AdService/android/motion.do", m));
	}

	
}
