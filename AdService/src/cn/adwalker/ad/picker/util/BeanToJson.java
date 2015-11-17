package cn.adwalker.ad.picker.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.adwalker.ad.picker.json.JSONArray;
import cn.adwalker.ad.picker.json.JSONObject;



public class BeanToJson {
	private static final Map<Class<?>, Object> primitiveDefaults = new HashMap<Class<?>, Object>();
    
    
	static {
		primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
		primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
		primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
		primitiveDefaults.put(Float.TYPE, Float.valueOf(0f));
		primitiveDefaults.put(Double.TYPE, Double.valueOf(0d));
		primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
		primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
		primitiveDefaults.put(Character.TYPE, Character.valueOf((char) 0));
	}
	public static Object objectToJson(Object obj,String...names){
		if(obj==null){
			return "";
		}else if(obj instanceof String ||obj instanceof Integer || obj instanceof Float ||obj instanceof Boolean || obj instanceof Short || obj instanceof Double || obj instanceof Long ||obj instanceof BigDecimal || obj instanceof BigInteger ||obj instanceof Byte){
			return StringUtil.dealNull(obj);
		} else if (obj instanceof Object[]) {
		    return arrayToJson((Object[])obj, names);
	    } else if (obj instanceof List<?>) {
	        return listTojson((List<?>)obj,names);
	    } else if (obj instanceof Map<?,?>) {
	    
	    } else if (obj instanceof Set<?>) {
	      
	    } else if (obj instanceof Date){
	        return DateUtil.getFormatDate((Date)obj, "yyyy-MM-dd HH:mm:ss");
	     }else{
	    	 //System.out.println(obj.getClass().getSimpleName());
	       return beanToJson(obj,names);
	     }
		return null;
	}

	public static JSONObject beanToJson(Object o,String...names) {
		JSONObject json = new JSONObject();
		try {
			PropertyDescriptor[] ps = propertyDescriptors(o.getClass());
			for(PropertyDescriptor p:ps){
				String name=p.getName();
				boolean b=false;
				for(String n:names){
				   	if(StringUtil.equals(n, name)){
				   		b=true;
				   	}
				}
				if(!b){
					Object obj = p.getReadMethod().invoke(o);
					json.put(name, objectToJson(obj,names));
				}
			}
			
		}catch(Exception e){
	        //logger.logError("beanToJson error :"+e.getMessage());
		}
		return json;
	}
	public static JSONArray listTojson(List<?> list,String...names) {
		JSONArray r = new JSONArray();
		if(list!=null){
			for(Object obj : list){
				r.put(objectToJson(obj,names));
				//r.add();
			}
		}
		return r;
	}
	public static JSONArray arrayToJson(Object[] objs,String...names){
		JSONArray r = new JSONArray();
		if(objs!=null){
			for(Object obj : objs){
				r.put(objectToJson(obj,names));
			}
		}
		return r;
	}
	private static PropertyDescriptor[] propertyDescriptors(Class<?> c) {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(c,Object.class);
		} catch (IntrospectionException e) {
		}
		return beanInfo.getPropertyDescriptors();
	}
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
	}
}
