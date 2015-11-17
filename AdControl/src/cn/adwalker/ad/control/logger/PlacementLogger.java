package cn.adwalker.ad.control.logger;

import java.util.List;

import cn.adwalker.ad.control.domain.Placement;


public class PlacementLogger {
	
	public static String getPlacementInfo(List<Placement> placementList) {
		if (placementList != null && !placementList.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(Placement pla : placementList) {
				sb.append(pla.getId() == null ? "" : pla.getId());
				sb.append("\t").append(pla.getName() == null ? "" : pla.getName());
				//sb.append("\t").append(pla.getCreateTime() == null ? "" : pla.getCreateTime());
				sb.append("\r\n");
			}		
			return sb.substring(0, sb.length() - 2); 
		} else {
			return null;
		}
	}
}
