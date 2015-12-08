package cn.adwalker.ad.picker.constants;

import java.util.HashMap;
import java.util.Map;


public enum ActionCode {
	  STATUS_0("0","下载完成"),
	  STATUS_1("1","打开"),
	  STATUS_2("2","表单注册"), 
	  STATUS_3("3",""), 
	  STATUS_4("4","CPC出下载按钮"), 
	  STATUS_5("5","点击下载"),
	  STATUS_6("6","结束"),  
	  STATUS_7("7","系统暂停");
	  public static final Map<String, ActionCode> statusMap = new HashMap<String, ActionCode>();
		static {
			ActionCode[] statu = ActionCode.class.getEnumConstants();
		    for(ActionCode s:statu ) {
		    	statusMap.put(s.status, s);
		}
	  }
	  public static ActionCode fromValue(String status) {
		    return statusMap.get(status);
	  }
	  public static String valueToName(String status){
		  ActionCode s = ActionCode.fromValue(status);
		  return null==s?"":s.toName();
	  }
	  @Override
	  public String toString() {
			return this.status;
	  }
	  public String toName(){
		  return this.name;
	  }
	  private String status;
	  private String name;
	  ActionCode(String status,String name) {
	    this.status = status;
	    this.name = name;
	  }
}
