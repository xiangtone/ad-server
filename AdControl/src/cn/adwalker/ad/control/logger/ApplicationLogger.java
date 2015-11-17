package cn.adwalker.ad.control.logger;

import java.util.List;

import cn.adwalker.ad.control.domain.Application;


public class ApplicationLogger {
	
	public static String getApplicationInfo(List<Application> applicationList) {
		if (applicationList != null && !applicationList.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(Application app : applicationList) {
				sb.append(app.getId() == null ? "" : app.getId());
				sb.append("\t").append(app.getAppkey() == null ? "" : app.getAppkey());
				sb.append("\t").append(app.getOs() == null ? "" : app.getOs());
				sb.append("\t").append(app.getName() == null ? "" : app.getName());
				sb.append("\t").append(app.getCreateTime() == null ? "" : app.getCreateTime());
				sb.append("\r\n");
			}
			return sb.substring(0, sb.length() - 2); 
		} else {
			return null; 
		}
	}
}
