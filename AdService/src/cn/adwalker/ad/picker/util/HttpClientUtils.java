/**
 * <p>Title: HttpClientUtil.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-30
 * @version 1.0
 */
package cn.adwalker.ad.picker.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import cn.adwalker.ad.util.ConfigUtil;


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
 * @author cuidd
 * @date 2013-5-30
 */
public class HttpClientUtils {

    private static Logger logger = Logger.getLogger(HttpClientUtils.class);
    public static Integer TIME_OUT=NumberUtil.getInteger(ConfigUtil.getString("dsp.timeout"), 500);
	public static String encoding="utf-8";
	
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
    public static String sendGet(String path){
    	return readFromURL(path, null);
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
			StringBuffer sbuf =new StringBuffer();
			boolean b = true;
			for(Entry<String, String> e:map.entrySet()){
				NameValuePair p = new NameValuePair();
				p.setName(e.getKey());
				p.setValue(StringUtil.encode(e.getValue(), "utf-8"));
				sbuf.append(b?(e.getKey()+"="+StringUtil.encode(e.getValue(), "utf-8")):("&"+e.getKey()+"="+StringUtil.encode(e.getValue(), "utf-8")));
				if(b){b=false;}
				ps[i]=p;
				i++;
			}
			logger.info("send url:"+url+"?"+sbuf.toString());
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
	
	public static String post(String url, RequestEntity requestEntity) throws Exception {
        PostMethod method = new PostMethod(url);
        method.addRequestHeader("Connection", "Keep-Alive");
        method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        //method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIME_OUT);
        method.setRequestEntity(requestEntity);
        method.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
        
        try {
        	HttpClient client = new HttpClient();
        	client.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT);
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            byte[] bs = method.getResponseBody();
            return new String(bs, encoding);

        } catch (SocketTimeoutException e) {
        	logger.error("SocketTimeoutException error:"+e.getMessage());
        	return null;
        } catch (Exception e) {
        	logger.error("SocketTimeoutException error:"+e.getMessage());
        	return null;
        } finally {
            method.releaseConnection();
        }
    }
	

	public static void main(String[] args){
		System.out.println(HttpClientUtils.readFromURL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=124.205.200.130", "gbk"));
		HttpClientUtils.readFromURL("http://www.ddbaiddu.com.cn",null);
	}

}
