package cn.adwalker.ad.picker.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * */
public enum AppStatus {
	  STATUS_END (-3,"终止",false),
	  STATUS_OFFLINE  (-2,"下线",false),
	  STATUS_NOTPASS (-1,"未通过",false),
	  STATUS_STOP (0,"暂停",false),
	  STATUS_CAOGAO(1,"草稿",true),
	  STATUS_PASS(2,"通过",true),
	  STATUS_ONLINE  (3,"上线",true);
	  public static final Map<String, AppStatus> statusMap = new HashMap<String, AppStatus>();
		static {
			AppStatus[] statu = AppStatus.class.getEnumConstants();
		    for(AppStatus s:statu ) {
		    	statusMap.put(s.status.toString(), s);
		}
	  }
	  public static AppStatus fromValue(Integer status) {
		    return statusMap.get(status.toString());
	  }
	  public static String valueToName(Integer status){
		  AppStatus s = AppStatus.fromValue(status);
		  return null==s?"":s.toName();
	  }
	  @Override
	  public String toString() {
			return this.status.toString();
	  }
	  public Integer toInteger(){
		  return this.status;
	  }
	  
	  public String toName(){
		  return this.name;
	  }
	  private Integer status;
	  private String name;
	  public boolean adPass;
	  AppStatus(Integer status,String name,boolean adPass) {
	    this.status = status;
	    this.name = name;
	    this.adPass=adPass;
	  }
}
