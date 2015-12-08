package cn.adwalker.ad.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.logger.LoggerManager;
import cn.adwalker.ad.param.AcParam;
import cn.adwalker.ad.picker.constants.ActionCode;
import cn.adwalker.ad.picker.thread.CallApi;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IPageActionService;
import cn.adwalker.ad.service.IScoreService;
import cn.adwalker.ad.util.AppConstant;

@Service("pageActionService")
public class PageActionServiceImpl implements IPageActionService {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(PageActionServiceImpl.class);


	@Resource
	private IUserInfoCache userInfoCache;
	@Resource
	private IAdCache advCache;
	@Resource
	private IScoreService scoreService; 	//add by jierfei 2013-08-26
	
	
	/**
	 * (non-Javadoc)
	* <p>Title: adMotion</p>
	* <p>Description:广告日志服务接口</p>
	* @param vo
	* @param os
	 */
	@Override
	public void adMotion(AcParam vo,String os){
		
		// 终端用户信息实体
		UserInfo userInfo = userInfoCache.getUserInfo(vo.getUuid());
		String area_code = "全国";
		if (userInfo != null && userInfo.getAreaCode() != null && !userInfo.getAreaCode().equals("null")) {
			area_code = userInfo.getAreaCode();
		}
		ActionCode ac = ActionCode.fromValue(vo.getAc());
		//单个广告日志
		Advertise ad = advCache.getAdv(NumberUtil.getLong(vo.getId(),0));
		if(ac!=null && ad!=null){
			if(ac==ActionCode.STATUS_0){
				loggerForStatus0(vo,ad,area_code);
			}else if(ActionCode.STATUS_1==ac){
				loggerForStatus1(vo,ad,area_code);
			}else if(ActionCode.STATUS_2==ac){
				loggerForStatus2(vo, ad, area_code);
				//ios系统且是线上对接方式则调用api激活确认接口 
				if(StringUtil.equals(os, AppConstant.OS_IOS) && !StringUtil.isEmpty(ad.getConfig_id())){
					Map<String,String> paramMap=new HashMap<String, String>();
					paramMap.put("ip",vo.ip);
					paramMap.put("app_id", String.valueOf(vo.getAppId()));
					paramMap.put("idfa",  StringUtil.dealNull(vo.getIdfa(),StringUtil.dealNull(vo.getYjf_idfa())));
					paramMap.put("idfv", StringUtil.dealNull(vo.getIdfv()));//idfv
					paramMap.put("openudid", StringUtil.dealNull(vo.getOpenudid()));//udid
					paramMap.put("os",StringUtil.dealNull(vo.getOs()));//os
					paramMap.put("sdkVersion", StringUtil.dealNull(vo.getVersion()));
					paramMap.put("devUserId",vo.getDevUserId());
					paramMap.put("page_type", StringUtil.dealNull(vo.getPage_type()));//类型
					paramMap.put("ssid",  StringUtil.dealNull(vo.getSsid()));
					paramMap.put("bssid",  StringUtil.dealNull(vo.getBssid()));
					paramMap.put("phoneName",  StringUtil.dealNull(vo.getPhoneName()));
					paramMap.put("latitude", StringUtil.dealNull(vo.getLatitude()));
					paramMap.put("longitude", StringUtil.dealNull(vo.getLongitude()));
					paramMap.put("uuid", vo.getUuid());
					paramMap.put("version", vo.version);
					paramMap.put("devuserid", vo.getDevUserId());
					paramMap.put("mac",vo.getMac());
					new Thread(new CallApi(ad.getId(),ad.getConfig_id(),paramMap) ).start();
				}
			}else if(ActionCode.STATUS_3==ac){

			}else if(ActionCode.STATUS_4==ac){
				loggerForStatus4(vo, ad, area_code);
			}else if(ActionCode.STATUS_5==ac){
				loggerForStatus5(vo, ad, area_code);
			}else if(ActionCode.STATUS_7==ac){
				loggerForStatus7(vo, ad, area_code);
			}
		}
		//墙展示成功
		if(ActionCode.STATUS_6==ac && !StringUtil.isEmpty(vo.getIds())){
			LoggerManager.loggerPageShow(StringUtil.dealNull(vo.getAppId()),vo.version, vo.getChannel(), vo.getUuid(), area_code, vo.ip, vo.getPage_type(), vo.getBannerTag());
			String ids[] = StringUtil.dealNull(vo.getIds()).split(",");
			for (String adIdStr : ids) {				
				Advertise adv = advCache.getAdv(NumberUtil.getLong(adIdStr, 0));
		        LoggerManager.logAdShowInfo(adv,vo.getAppId(), vo.getPage_type(), vo.getChannel(), vo.getUuid(), area_code, vo.ip, vo.version, vo.getBannerTag(), vo.getImsi(),vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa()); 
			}
		}
		
		/**
		 * 积分墙回调接口 add by jierfei 2013-09-02 
		 */
		if (!StringUtils.isEmpty(vo.getDevUserId()) && !"null".equals(vo.getDevUserId())) {
			// 3.业务逻辑处理
			try {
				scoreService.updateConfig(vo.getUuid(), vo.getDevUserId(), StringUtil.dealNull(vo.getAppId()),os);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//下载完成日志
    private void loggerForStatus0(AcParam vo, Advertise ad,String area_code){
    	if(ad!=null){
    		//androidV3.0.0版本业务处理  并且是渠道投放 写 adDetail日志 否则写addownload日志    原因：待确认  (0:媒体投放广告 1:渠道投放广告)
    		if ("androidV3.0.0".equals(vo.version) && ad.getResponse_type().equals(1)) {
				String priceStr = "0";
				if (StringUtil.equals(ad.getBlance_mode(), AppConstant.AD_CPD)) {
					priceStr = String.valueOf(ad.getBlance_price());
				}
				LoggerManager.logAdDetailInfo(StringUtil.dealNull(vo.getAppId()), vo.getPage_type(), vo.getChannel(), vo.getId(), StringUtil.dealNull(ad.getCategory_id()), ad.getBlance_mode(), priceStr, vo.getUuid(), area_code, vo.ip, vo.version, vo.getTerminalType(), vo.getImsi());
        	}else{
        		String priceStr = "0";
				if (StringUtil.equals(ad.getBlance_mode(), AppConstant.AD_CPD)) {
					priceStr = String.valueOf(ad.getBlance_price());
				}
				LoggerManager.logAdDownLoad(StringUtil.dealNull(vo.getAppId()),vo.getPage_type(), vo.getChannel(), vo.getId(), StringUtil.dealNull(ad.getCategory_id()), ad.getBlance_mode(), priceStr, vo.getUuid(), area_code, vo.ip, vo.version, StringUtil.dealNull(vo.getBannerTag()), vo.getImsi());
        	}
    		
    		
    	}
    }
	//
    private void loggerForStatus1(AcParam vo,Advertise ad,String area_code){
    	if (StringUtil.equals("androidV3.0.0", vo.version)) {        
    		if(ad!=null){
    			LoggerManager.logAdDetailInfo(StringUtil.dealNull(vo.getAppId()), vo.getPage_type(), vo.getChannel(), vo.getId(), StringUtil.dealNull(ad.getCategory_id()), ad.getBlance_mode(), StringUtil.dealNull(ad.getBlance_price(),"0"), vo.getUuid(), area_code, vo.ip, vo.version, vo.getTerminalType(), vo.getImsi());
    		}
		}
    }
    private void loggerForStatus2(AcParam vo,Advertise ad,String area_code){
    	String priceStr="0";
		 if(StringUtil.equals(ad.getBlance_mode(), AppConstant.AD_CPC)){
	        	priceStr = StringUtil.dealNull(ad.getBlance_price());// log.getPrice().toString();
	        }
		LoggerManager.logAdDetailInfo(StringUtil.dealNull(vo.getAppId()), vo.getPage_type(), vo.getChannel(), vo.getId(), StringUtil.dealNull(ad.getCategory_id()), ad.getBlance_mode(), priceStr, vo.getUuid(), area_code, vo.ip, vo.version, vo.getTerminalType(), vo.getImsi());
        LoggerManager.logAdShowInfo(ad,vo.getAppId(), vo.getPage_type(), vo.getChannel(), vo.getUuid(), area_code, vo.ip, vo.version, vo.getBannerTag(), vo.getImsi(),vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa());
    }
    private void loggerForStatus4(AcParam vo,Advertise ad,String area_code){
		LoggerManager.logAdDetailInfo(StringUtil.dealNull(vo.getAppId()), vo.getPage_type(), vo.getChannel(), vo.getId(), StringUtil.dealNull(ad.getCategory_id()), ad.getBlance_mode(), "0", vo.getUuid(), area_code, vo.ip, vo.version, vo.getTerminalType(), vo.getImsi());
    }
    private void loggerForStatus5(AcParam vo,Advertise ad,String area_code){
    	String priceStr = "0";
    	if((StringUtil.equals(ad.getResponse_type(), AppConstant.AD_TYPE_DOWNLOAD) || StringUtil.equals(ad.getResponse_type(), AppConstant.AD_TYPE_DRIECT_DOWNLOAD)) && StringUtil.equals(ad.getBlance_mode(), AppConstant.AD_CPC)){
    		priceStr = StringUtil.dealNull(ad.getBlance_price(),"0");
    	}
		LoggerManager.logAdbannerAc4(StringUtil.dealNull(vo.getAppId()), vo.getPage_type(), vo.getChannel(), vo.getId(), StringUtil.dealNull(ad.getCategory_id()), ad.getBlance_mode(), priceStr, vo.getUuid(), area_code, vo.ip, vo.version, StringUtil.dealNull(vo.getBannerTag()), vo.getImsi(),vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa());
    }
    private void loggerForStatus7(AcParam vo,Advertise ad,String area_code){
    	LoggerManager.logAdShowInfo(ad,vo.getAppId(), vo.getPage_type(), vo.getChannel(), vo.getUuid(), area_code, vo.ip, vo.version, vo.getBannerTag(), vo.getImsi(),vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa());
    }
}
