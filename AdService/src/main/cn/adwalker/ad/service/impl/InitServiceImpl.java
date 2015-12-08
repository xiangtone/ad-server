package cn.adwalker.ad.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.IUserInfoDao;
import cn.adwalker.ad.picker.thread.CallAreaCode;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.vo.InitVo;
import cn.adwalker.ad.service.InitService;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.vo.InitDataJson;

/**
 * 
 * <p>
 * Title: SystemServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author caiqiang
 * @date 2013-1-8
 */
@Service("systemService")
public class InitServiceImpl implements InitService {
	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private IUserInfoCache userInfoCache;

	@Resource
	private IDevAppCache devAppCache;
	@Override
	public InitDataJson collectUuidMsg(InitVo iv, String osName) {
		InitDataJson data = new InitDataJson();
		if(StringUtil.isEmpty(iv.getUuid())){
			iv.setUuid(PublicUtil.installUuid(iv.getImei(), iv.getTelModel(), iv.getSw(), iv.getSh(), iv.getBrand()));
		}
		UserInfo info = userInfoCache.getUserInfo(iv.getUuid(), iv.getImei(), iv.getTelNum(), iv.getTelModel(),iv.getNetEnv(), null,iv.getOperator(),iv.getSw(),iv.getSh(),iv.getOs_version(),iv.getBrand(),iv.getImsi(),iv.getAppId(),iv.getTerminalType(),iv.getOpenudid(),iv.getIdfa(),iv.getJailbroken(),iv.getIdfv(),osName);
		if (!StringUtils.isEmpty(iv.getIp())&& (info == null || StringUtils.isEmpty(info.getAreaCode()))) {
			new Thread(new CallAreaCode(iv.getUuid(), iv.getIp(), info)).start();
		}
		DevApp app = devAppCache.getDevApp(iv.getAppId());
		String isCoordinate="1";
		if(null!=app && !StringUtil.isEmpty(app.getIs_coordinate())){
			isCoordinate=StringUtil.equals(app.getIs_coordinate(), 1)?"1":"0";
		}
		data.setConfig_value("0");
		data.setPushStatus("0");
		data.setSid(iv.getUuid());
		data.setUuid(iv.getUuid());
		data.setIs_coordinate(isCoordinate);
		data.setRes_url(ConfigUtil.getString("images.url.prefix"));
		return data;
	}
}
