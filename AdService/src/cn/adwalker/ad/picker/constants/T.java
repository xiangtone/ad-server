package cn.adwalker.ad.picker.constants;

import java.util.HashMap;
import java.util.Map;

public enum T {
	  jifen("0","积分墙"),
	  tueijian("1","推荐墙"),
	  grid("2","九宫格"), 
	  push("3","推送"), 
	  banner("4","banner"), 
	  chapin("5","点击下载"),
	  hot("6","热门");
	  public static final Map<String, T> statusMap = new HashMap<String, T>();
		static {
			T[] statu = T.class.getEnumConstants();
		    for(T s:statu ) {
		    	statusMap.put(s.status, s);
		}
	  }
	  public static T fromValue(String status) {
		    return statusMap.get(status);
	  }
	  public static String valueToName(String status){
		  T s = T.fromValue(status);
		  return null==s?"":s.toName();
	  }
	  @Override
	  public String toString() {
			return this.status;
	  }
	  public Integer toInteger(){
		  return Integer.valueOf(this.status);
	  }
	  public String toName(){
		  return this.name;
	  }
	  private String status;
	  private String name;
	  T(String status,String name) {
	    this.status = status;
	    this.name = name;
	  }
}
