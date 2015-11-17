package cn.adwalker.IOSChannel.picker.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.adwalker.IOSChannel.picker.vo.ParamVo;



/**
 * @author admin
 *
 */
public class ParamHandler {
	@SuppressWarnings("unused")
	private  HttpServletResponse response=null;
	private  HttpServletRequest request=null;
	private  boolean error=false;
    
    public ParamHandler(){}
    public ParamHandler(HttpServletResponse response,HttpServletRequest request){
    	this.response=response;
    	this.request=request;
    }
    
    public static ParamHandler createParamHandler(HttpServletResponse response,HttpServletRequest request){
    	return new ParamHandler(response,request);
    }
	
	@SuppressWarnings("unchecked")
	public <T> T paramConvent(Class<T> type,String paramMap){
		Map<String,String> map=(Map<String, String>) request.getAttribute(paramMap);
		if(map!=null){
			BeanRequestPicker brp = new BeanRequestPicker(map);
			ParamVo vo = (ParamVo)brp.handleMap(type);
			vo.setUserAgent(request.getHeader("User-Agent"));
	        return (T)vo;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T paramConventAlis(Class<T> type,String source){
		BeanRequestPicker brp = new BeanRequestPicker(request);
		ParamVo vo = (ParamVo)brp.handleAlis(type, source);//handle(type);
		vo.setUserAgent(request.getHeader("User-Agent"));
		return (T)vo;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T paramConvent(Class<T> type){
		BeanRequestPicker brp = new BeanRequestPicker(request);
		ParamVo vo = (ParamVo)brp.handle(type);
		vo.setUserAgent(request.getHeader("User-Agent"));
		return (T)vo;
	}
	
	
	
	public <T> T paramConvent(Class<T> type,HashMap<String, String> map){
		BeanRequestPicker brp = new BeanRequestPicker(request);
		brp.handleAlis(type);
		return null;
	}
	
	public boolean hasError(){
		return error;
	}
	
}
