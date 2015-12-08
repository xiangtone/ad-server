package cn.adwalker.ad.picker.monitor;

import java.util.HashMap;
import java.util.Map;


public enum ATTActionType {
	  STATUS_CPD(0,"cpd"),
	  STATUS_CPA(1,"cpa"),
	  STATUS_CPC(2,"cpc"), 
	  STATUS_CPM(3,"cpm");
	  public static final Map<String, ATTActionType> statusMap = new HashMap<String, ATTActionType>();
		static {
			ATTActionType[] statu = ATTActionType.class.getEnumConstants();
		    for(ATTActionType s:statu ) {
		    	statusMap.put(s.type+"", s);
		}
	  }
	  public static ATTActionType fromValue(Integer type) {
		    return statusMap.get(""+type);
	  }
	  public static String valueToName(Integer status){
		  ATTActionType s = ATTActionType.fromValue(status);
		  return null==s?"":s.toName();
	  }
	  @Override
	  public String toString() {
			return this.type.toString();
	  }
	  public Integer toValue(){
		  return this.type;
	  }
	  public String toName(){
		  return this.name;
	  }
	  private Integer type;
	  private String name;
	  ATTActionType(Integer type,String name) {
	    this.type = type;
	    this.name = name;
	  }
}
