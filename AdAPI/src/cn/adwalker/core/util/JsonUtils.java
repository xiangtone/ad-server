package cn.adwalker.core.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.ad.util.StringUtil;
import cn.adwalker.core.vo.ResponseResult;

/**
 * 
* <p>Title: JsonUtil</p>
* <p>Description:JOSN 工具类，代码从提取出来的类,先集中写在一个方法里以后再分拣</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年9月30日
 */
public abstract class JsonUtils {
	
	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
	
	/*****
	 * 将对象转换成json
	 * 
	 * @param resposeResult
	 * @return
	 */
	public static String toJson(ResponseResult resposeResult) {
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
	
	/*****
	 * 将对象转换成json
	 * @param resposeResult
	 * @return
	 */
	public static String toJson(Object resposeResult){
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
	 * 
	 * @param response
	 * @param responseJson
	 */
	public static void sendJson(HttpServletResponse response,String responseJson){
		response.setContentType("application/json;charset=UTF-8");
		try {
			OutputStream out = response.getOutputStream();
			out.write(responseJson.getBytes("UTF-8"));
		} catch (Exception e1) {
			log.error("出错了",e1);
		}
	}
	
	
	/**
	 * <p>发送信息</p>
	 * @param resposeResult
	 * @param response
	 */
	public static void sendResponse(ResponseResult resposeResult, HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		String responseJson = StringUtil.toJson(resposeResult);
		OutputStream out=null;
		try {
			out = response.getOutputStream();
			out.write(responseJson.getBytes("UTF-8"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			if (out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
