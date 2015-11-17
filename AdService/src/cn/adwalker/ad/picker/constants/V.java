package cn.adwalker.ad.picker.constants;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.ad.util.AppConstant;

public enum V {
	  Android210("android2.1.0",AppConstant.OS_ANDROID,"android2.1.0"),
	  IOS210("IOS2.1.0",AppConstant.OS_IOS,"IOS2.1.0"),
	  IOS220("IOS2.2.0",AppConstant.OS_IOS,"IOS2.2.0");
	  public static final Map<String, V> statusMap = new HashMap<String, V>();
		static {
			V[] statu = V.class.getEnumConstants();
		    for(V s:statu ) {
		    	statusMap.put(s.status, s);
		}
	  }
	  public static V fromValue(String status) {
		    return statusMap.get(status);
	  }
	  public static String valueToName(String status){
		  V s = V.fromValue(status);
		  return null==s?"":s.toName();
	  }
	  @Override
	  public String toString() {
		  return this.status;
	  }
	  public Integer toInteger(){
		  return Integer.valueOf(this.status);
	  }
	 
	  public String os(){
		  return this.os;
	  }
	  
	  public String toName(){
		  return this.name;
	  }
	  private String status;
	  private String name;
	  private String os;
	  V(String status,String os,String name) {
	    this.status = status;
	    this.name = name;
	    this.os=os;
	  }
}
