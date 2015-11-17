package cn.adwalker.ad.picker.util;

import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.cache.element.UserScore;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.vo.NoticeInfo;

public class PickerUtil {
	private static final String USER="t_user";
	private static final Integer activateNum=NumberUtil.getInteger(ConfigUtil.getString("ac_limit"), 6) ;
	public static String tableName(String uuid){
		if(null!=uuid){
			String lastOne = String.valueOf(uuid.charAt(uuid.length()-1)).toLowerCase();
			if(lastOne.matches("(?i)[0|1|2|3|4|5|6|7|8|9|a|b|c|d|e|f]")){
				return USER+"_"+lastOne;
			}else{
				return USER+"_0";
			}
		}
		return null;
	}
	public static NoticeInfo getNoticle(UserInfo u,Long appId){
		UserScore us = u.getUserScore(appId);
		if(us!=null){
			Integer score = us.getScore();
			Integer today = us.getTodayInteger();
			String rate = us.getDownLoadTime()>=activateNum?"100%":(us.getDownLoadTime()*100/activateNum)+"%";
			return new NoticeInfo(NumberUtil.getInteger(score, 0),NumberUtil.getInteger(today, 0) ,rate);
		}
        return new NoticeInfo(0,0 ,"0%");
	}
}
