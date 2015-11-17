package cn.adwalker.IOSChannel.picker.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;

import net.sf.json.JSONObject;

public class BeanRequestPicker {
	
	
	private HttpServletRequest request;
	private JSONObject json;
	private String actionParam;
	private BeanConvent convent = new BeanConvent();
	private BeanJsonProcessor jsonConvent = new BeanJsonProcessor();
	private BeanMapConvent mapConvent = new BeanMapConvent();
	private Map<String, String> map = new HashMap<String, String>();

	public BeanRequestPicker(HttpServletRequest request){
		this.request=request;
	}
	public BeanRequestPicker(HttpServletRequest request,String actionParam){
		this.request=request;
		this.actionParam=actionParam;
	}
	public BeanRequestPicker(JSONObject obj){
		this.json=obj;
	}
	public BeanRequestPicker(Map<String, String> map ){
		this.map=map;
	}
	
	public <T> T handle(Class<T> type){
		return convent.toBean(request, type);
	}
	public <T> T handleAlis(Class<T> type){	
		String source = request.getParameter(null==actionParam?"appid":actionParam);
		Map<String, String> map = new HashMap<String, String>();
		if(!StringUtil.isEmpty(source)){
			CmdBean bean = Config.getBeanCmd(source);
			if(null!=bean){
				map = bean.getMap();
			}
		}
		return convent.toBean(request, type,map);
	}
	
	public <T> T handleAlis(Class<T> type,String source){
		ChannelConfig config = StringUtil.isEmpty(source)?null:ConfigCache.findChannelConfig(source);
		if(config!=null && !StringUtil.isEmpty(config.alis)){
			Map<String, String> aliMap = new HashMap<String, String>();
			String[] strs = config.alis.split("&");
			for(String str:strs){
				String[] s = str.split("=");
			    if(s.length>1){
			    	aliMap.put(s[0], s[1]);
			    }
			}
			return convent.toBean(request, type,aliMap);
		}else{
			return convent.toBean(request, type);
		}
	}
	public <T> T handleJson(Class<T> type){
		return jsonConvent.toBean(json, type);
	}
	public <T> T handleMap(Class<T> type){
		return mapConvent.toBean(map, type);
	}
}
