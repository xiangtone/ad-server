package cn.adwalker.ad.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.core.vo.ResponseResult;

/**
 * 
* <p>Title: StringUtil</p>
* <p>Description:字符串工具类</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2013-3-21
 */
public class StringUtil extends org.apache.commons.lang.StringUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(StringUtil.class);
	
	public  StringUtil(){
		//重写构造函数避免被实例化。
	}

	private static final String default_char_encode = "utf-8";
/**
 * <p>编码</p>
 * @param s
 * @return
 */
	public static String encode(String s) {
		String str = null;
		if (!StringUtil.isEmpty(s)) {
			try {
				str = URLEncoder.encode(s, default_char_encode);
			} catch (UnsupportedEncodingException e) {
				logger.error(e.toString());
			}
		}
		return str;

	}
	/**
	 * <p>解码</p>
	 * @param s
	 * @return
	 */
	public static String decode(String s){
		String str=null;
		if (!StringUtil.isEmpty(s)) {
			try {
				str = URLDecoder.decode(s, default_char_encode);
			} catch (UnsupportedEncodingException e) {
				logger.error(e.toString());
			}
		}
		return str;
	}
	/**
	 * <p>例如020000000000  转化成 02:00:00:00:00:00</p>
	 * @param mac
	 * @return
	 */
    public static String formatMac(String mac){
    	String macNew="";
    	if(StringUtils.isBlank(mac)){
    		return macNew;
    	}
    	String[] strs = new String[mac.length() - 1];
		for (int k = 0; k < mac.length() - 1; k += 2) {
		strs[k] = mac.substring(k, k + 2);
		macNew += strs[k] + ":";
       }
		macNew = macNew.substring(0, macNew.length()-1);
		return macNew;
    }
    /**
     * <p>格式化idfa 9E9BAAE4EB334C889044E10C47042A67 改为
     *   9E9BAAE4-EB33-4C88-9044-E10C47042A67
     * </p>
     * @param idfa
     * @return
     */
    public static String formatIDFA(String idfa){
      if(StringUtils.isNotBlank(idfa) && idfa.length()==32){
    		String idfaNew = "";
    		char []ifas=idfa.toCharArray();
    		for(int i=0;i<ifas.length;i++){
    			idfaNew+=ifas[i];
    			if(i==7 || i==11 || i==15 || i==19){
    				idfaNew+="-";
    			}
    		}
		return idfaNew;
       }
     return "";
    }
    /*****
	 * 将对象转换成json
	 * @param resposeResult
	 * @return
	 */
	public static String toJson(ResponseResult resposeResult){
		 // 将对象转换为json
	    JsonGenerator gen = null;
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        StringWriter sw = new StringWriter();
	        gen = new JsonFactory().createJsonGenerator(sw);
	        
	        mapper.writeValue(gen, resposeResult);

	        return sw.toString();
	    } catch (Exception e) {
	    } finally {
	        try {
	            if (gen != null) {
	                gen.close();
	            }
	        } catch (IOException e) {
	        }
	    }
	    return null;
	}
	/**
	 * <p>将固定param添加到键值对中</p>
	 * @param param
	 * @param sourceStr
	 */
	public static void addParam(Map<String,String> param,String sourceStr){
		if(param==null || StringUtils.isBlank(sourceStr)){
			return ;
		 }
		String params[]=sourceStr.split("&");
		for(String pare:params){
			
			String kv[]=pare.split("=");
			if(kv.length==2){
				param.put(kv[0],kv[1]);
			}
		}
	}
}
