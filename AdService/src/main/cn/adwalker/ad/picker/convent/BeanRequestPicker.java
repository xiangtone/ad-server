package cn.adwalker.ad.picker.convent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.adwalker.ad.picker.json.JSONObject;

public class BeanRequestPicker {
	private HttpServletRequest request;
	private BeanConvent convent = new BeanConvent();
	private BeanMapConvent mapConvent = new BeanMapConvent();
	private BeanJsonConvent jsonConvent = new BeanJsonConvent();
	private Map<String, String> map = new HashMap<String, String>();
	private JSONObject json = new JSONObject();
	public BeanRequestPicker(HttpServletRequest request){
		this.request=request;
	}

	public BeanRequestPicker(Map<String, String> map ){
		this.map=map;
	}
	public BeanRequestPicker(JSONObject json ){
		this.json=json;
	}
	public <T> T handle(Class<T> type){		
		return convent.toBean(request, type);
	}
	public <T> T handleMap(Class<T> type){
		return mapConvent.toBean(map, type);
	}
	public <T> T handleJson(Class<T> type){
		return jsonConvent.toBean(json, type);
	}
	
}
