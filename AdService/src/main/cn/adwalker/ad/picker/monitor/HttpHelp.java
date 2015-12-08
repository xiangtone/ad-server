package cn.adwalker.ad.picker.monitor;

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
import org.apache.log4j.Logger;


public class HttpHelp {
    private static Logger logger = Logger.getLogger(HttpHelp.class);
	
    public static String readFromURL(String path,Map<String,String> map, String encoding) {
    	boolean b = true;
    	StringBuffer sbuf = new StringBuffer("");
        for(Entry<String, String> en:map.entrySet()){
        	sbuf.append(b?("?"+en.getKey()+"="+en.getValue()):("&"+en.getKey()+"="+en.getValue()));
        	if(b){b=false;}
        }
    	String url = path+sbuf.toString();
		return readFromURL(url, encoding, 1024, 5000);
	}
    
    public static String readFromURL(String path, String encoding) {
		return readFromURL(path, encoding, 1024, 5000);
	}

    public static String readFromURL(String path, String encoding, int init, int timeout)  {
		return readFromURL(path, encoding, init, timeout, null);
	}
    
	public static String readFromURL(String path, String encoding, int init, int timeout, Proxy proxy) {
		logger.info("send get url  :"+path);
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
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), encoding));
			while (in.read(chars) > 0) {
				chars.flip();
				bf.append(chars);
				chars.clear();
			}
		} catch (Exception e) {
			logger.info("Get url["+path+"] error! "+e.getMessage());
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
		logger.info("send response "+(System.currentTimeMillis()-a)+": ms  "+bf.toString());
		return bf.toString();
	}
	
	public static String postFromUrl(String url,Map<String, String> map){
		if(map!=null){
			NameValuePair[] ps =new NameValuePair[map.size()];
			int i=0;
			for(Entry<String, String> e:map.entrySet()){
				NameValuePair p = new NameValuePair();
				p.setName(e.getKey());
				p.setValue(e.getValue());
				ps[i]=p;
				i++;
			}
			return postFromURL(url, ps);
		}else{
			logger.info("Array of parameters may not be null");
		}
		return null;
	}
	
	public static String postFromURL(String url, NameValuePair[] msg) {
		logger.info("send post url  :"+url);
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
			logger.info("postFromURL Error!" + e.getMessage());
		} finally {
			post.releaseConnection();
		}
		logger.info("send response  :"+result);
		return result;
	}
	
	public static void main(String[] args){
		Map<String, String> map = new HashMap<String, String>();
		map.put("luoyouhua", "pad");
		
		System.out.println(HttpHelp.postFromUrl("http://www.baidu.com", map));
	}
	
}
