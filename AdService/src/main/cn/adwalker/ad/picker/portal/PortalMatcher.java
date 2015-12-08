package cn.adwalker.ad.picker.portal;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.picker.util.StringUtil;

public class PortalMatcher {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PortalMatcher.class);

	public static List<Advertise> matcherAdvertise(List<Advertise> list,
			UserInfo u, DevApp app) {
		List<Advertise> removeList = new ArrayList<Advertise>();
		List<UserAdRel> reAdRels = null;
		if (u.getSignAdRels() != null && u.getSignAdRels().size() > 0) {
			reAdRels = u.getSignAdRels();
		}
		for (Advertise a : list) {
			System.out.println(a.getId()+"----"+a.getAd_name()+"---"+a.getAd_res_code()+"----"+a.getAd_mark());
			if (a.getAd_res_code()!=null&&(!a.getAd_res_code().equals("ADWALKER")&&!a.getAd_res_code().equals("YOUMI"))) {
				removeList.add(a);
			}
			if (!checkLimit(a)) {
				removeList.add(a);
			}
			if (!checkBundleid(a)) {
				removeList.add(a);
			}

			if (reAdRels != null) {
				for (UserAdRel rel : reAdRels) {
					if (a.getId() == rel.getAd_id()) {
						removeList.add(a);
					}
				}
			}

		}

		list.removeAll(removeList);
		return list;
	}
	
	
	public static List<Advertise> matcherAdvertise(List<Advertise> list,DevApp app) {
		List<Advertise> removeList = new ArrayList<Advertise>();
		list.removeAll(removeList);
		return list;
	}

	private static boolean checkLimit(Advertise a) {
		boolean b = true;
		if (a != null) {
			MaterielScore wall = (MaterielScore) a.getWall();
			if (wall.getLimit_time() == null) {
				b = false;
			}
		}
		return b;

	}

	private static boolean checkBundleid(Advertise a) {
		boolean b = true;
		if (a != null && a.getType_id() == 0) {
			if (StringUtil.isEmpty(a.getAd_mark())) {
				b = false;
			}
		}
		return b;

	}
}
