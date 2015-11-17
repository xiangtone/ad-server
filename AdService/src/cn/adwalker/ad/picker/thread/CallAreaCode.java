package cn.adwalker.ad.picker.thread;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.IUserInfoDao;
import cn.adwalker.ad.picker.util.HttpClientUtils;
import cn.adwalker.core.spring.AppContext;

public class CallAreaCode implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(CallAreaCode.class);
	private String uuid;
	private String ip;
	private UserInfo userInfo;

	public CallAreaCode(String uuid, String ip, UserInfo userInfo) {
		this.uuid = uuid;
		this.ip = ip;
		this.userInfo = userInfo;
	}
	
	
	@Override
	public void run() {
		ApplicationContext  c=AppContext.getApplicationContext();
		String aa = HttpClientUtils.readFromURL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip="+ip, "gbk");
		String[] bb = aa.split("\\s+");		
		IUserInfoDao userInfoDao=c.getBean(IUserInfoDao.class);
		IUserInfoCache userInfoCache=c.getBean(IUserInfoCache.class);
		if (bb.length >= 6) {				
			if (!StringUtils.isEmpty(bb[5]) && !bb[5].equals("null")) {				
				userInfoDao.updateUserArea_Code(uuid, bb[5],bb[4]);
				userInfo.setAreaCode(bb[5]);
				userInfo.setArea_province(bb[4]);
				log.info("init.AreaCode" + bb[5]);
				userInfoCache.replaceUserInfo(uuid);
			}
		}
	}

}
