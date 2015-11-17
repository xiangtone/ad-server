package cn.adwalker.ad.util;

import cn.adwalker.ad.picker.util.HttpClientUtils;
import cn.adwalker.ad.vo.AppQueryResult;

public abstract class PListUrl {

	public static String getPlist() {
		String s = "";
		String string = HttpClientUtils
				.sendGet("http://syxf.bb800.com/app/getApp");
		System.out.println(string);
		AppQueryResult result = JsonMapper.buildNonDefaultMapper().fromJson(
				string, AppQueryResult.class);
		if (result!=null&&result.getIsok() == 1) {
			s = result.getData().getPlistUrl();
		}
		return s;
	}

}
