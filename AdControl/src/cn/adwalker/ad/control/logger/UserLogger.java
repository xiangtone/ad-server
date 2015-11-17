package cn.adwalker.ad.control.logger;

import java.util.List;

import cn.adwalker.ad.control.domain.User;


public class UserLogger {	
	public static String getUserInfo(List<User> userList) {
		if (userList != null && !userList.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(User user : userList) {
				sb.append(user.getUuid() == null ? "" : user.getUuid());
				sb.append("\t").append(user.getTelModel() == null ? "" : user.getTelModel());
				sb.append("\t").append(user.getOperator() == null ? "" : user.getOperator());
				sb.append("\t").append(user.getOs() == null ? "" : user.getOs());
				sb.append("\t").append(user.getAppId() == null ? "" : user.getAppId());
				sb.append("\t").append(user.getOpenudid() == null ? "" : user.getOpenudid());
				sb.append("\t").append(user.getIdfv() == null ? "" : user.getIdfv());				
				sb.append("\t").append(user.getImei() == null ? "" : user.getImei());
				sb.append("\t").append(user.getNetEnv() == null ? "" : user.getNetEnv());
				sb.append("\t").append(user.getAreaCode() == null ? "" : user.getAreaCode());
				sb.append("\t").append(user.getBrand() == null ? "" : user.getBrand());
				sb.append("\t").append(user.getImsi() == null ? "" : user.getImsi());
				sb.append("\t").append(user.getAreaProvince() == null ? "" : user.getAreaProvince());
				sb.append("\t").append(user.getCreateTime() == null ? "" : user.getCreateTime());
				sb.append("\r\n");
			}
			return sb.substring(0, sb.length() - 2);
		} else {
			return null;
		}
		
	}
}
