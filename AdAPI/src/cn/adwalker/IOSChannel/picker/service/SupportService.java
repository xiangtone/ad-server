package cn.adwalker.IOSChannel.picker.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.bean.IosActionLog;
import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.bean.UserInfo;
import cn.adwalker.IOSChannel.picker.call.CallAdParther;
import cn.adwalker.IOSChannel.picker.call.CallChannelParther;
import cn.adwalker.IOSChannel.picker.checker.LoggerManager;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.BeanHelper;
import cn.adwalker.IOSChannel.picker.util.DateUtil;
import cn.adwalker.IOSChannel.picker.util.HttpClientUtils;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.NumberUtil;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;
import cn.adwalker.IOSChannel.picker.xmemcached.PickerCache;
import cn.adwalker.ad.bean.Advertise;
import cn.adwalker.ad.pool.ChannelClickPool;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.ConfigUtil;

@Service("supportService")
public class SupportService {

	private final static Logger logger = Logger.getLogger(SupportService.class);
	private final static String MOTION="http://i.adwalker.cn/AdService/android/motion.do";
	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil.getString("cache_time"));
	@Resource
	private MemCached memCached;
	@Resource
	private SupportDao supportDao;
	
	public void iosClick(IosClick iosClick){
		UserInfo u=null;
		if(!StringUtil.isEmpty(iosClick.getOPENUDID())){
			u = PickerCache.loadUserInfo(iosClick.getOPENUDID());
		}
		String areaCode = u==null || StringUtil.isEmpty(u.getAreaCode())?PickerCache.getAreaCodeByIp(iosClick.client_ip):u.getAreaCode();
		iosClick.setAreaCode(areaCode);
		CampaignConfig config = ConfigCache.findCampaignCongfig(iosClick.getAppid());
		iosClick.setIn_price(config==null?0d:config.getAdv_price());
		//iosClick.set
		IosActionLog action = BeanHelper.iosClickConverToIosActionLog(iosClick);
		PickerCache.addIosActionLog(action);//点击放入缓存
		ChannelClickPool.getInstance().add(iosClick);
		LoggerManager.clickLogger(iosClick);//写点击日志
		callAdParther(iosClick);
	}
	/** call ad parther*/
	private void callAdParther(IosClick iosClick){
		CampaignConfig config = ConfigCache.findCampaignCongfig(iosClick.getAppid());
		if(config!=null && !StringUtil.isEmpty(config.getUrl())){
			new Thread(new CallAdParther(config,iosClick)).start();
			//ThreadPool.getInstance().execute(new CallAdParther(config,iosClick));
		}else{
			logger.logError("config no exist adKey:"+iosClick.getAppid());
		}
	}
	
	/**
	 * 
	* <p>Title: saveIosActivate</p>
	* <p>Description:TODO</p>
	* @param ad_key
	* @param deviceid
	* @author cuidd
	* @date 2014年10月25日
	* @return void
	* @version 1.0
	 */
	public void saveIosActivate(String ad_key,String deviceid){
		//IosActionLog click = supportDao.findIosActionLog(param.deviceid,param.appid);
		IosActionLog click = PickerCache.getIosActionLog(ad_key, deviceid);
		//if(click!=null && StringUtil.equals(BaseAttribute.IOS_ACTION_LOG_STATUS_0, click.getActivite_status())){
		if(click!=null && !supportDao.existActivateLog(ad_key,deviceid)){
			IosActivateLog activateLog = new IosActivateLog();
			String areaCode=click.getArea_code();
			if(!StringUtil.isEmpty(click.getOpenudid()) && StringUtil.equals(BaseAttribute.ADWALKER, click.getChannel()) ){
				UserInfo u = PickerCache.loadUserInfo(click.getOpenudid());
				areaCode = u==null || StringUtil.isEmpty(u.getAreaCode())?PickerCache.getAreaCodeByIp(click.getClient_ip()):u.getAreaCode();
			}
			activateLog.setAd_id(ad_key);
			activateLog.setMac(deviceid);
			activateLog.setActivite_status(BaseAttribute.IOS_ACTION_LOG_STATUS_1);
			activateLog.setStatus(BaseAttribute.IOS_ACTION_LOG_STATUS_1);
	        activateLog.setIdfa(click.getIdfa());
	        activateLog.setIdfv(click.getIdfv());
	        activateLog.setOpenudid(click.getOpenudid());
	        activateLog.setCreate_time(new Date());
	        activateLog.setClick_time(click.getCreateTime());
		    activateLog.setOs_version(click.getOs_version());
		    activateLog.setChannel(click.getChannel());
		    activateLog.setAction_id(click.getId());
		    activateLog.setPage_type(click.getPage_type());
		    activateLog.setAd_key(activateLog.getAd_key());
		    activateLog.setClient_ip(click.getClient_ip());
		    activateLog.setAd_key(click.getAd_key());
		    activateLog.setApplication_key(click.getApplication_key());
		    activateLog.setArea_code(areaCode);
		    Advertise ad = PickerCache.getAdvertise(click.getAd_key());
		    activateLog.setPrice(null==ad?0d:ad.getBlance_price());
		    activateLog.setIn_price(click.getIn_price());
		    //end
		    activateLog.setSsid(click.getSsid());
		    activateLog.setBssid(click.getBssid());
		    activateLog.setPhoneName(click.getPhoneName());
		    activateLog.setLatitude(click.getLatitude());
		    activateLog.setLongitude(click.getLongitude());
		    //更改点击状态
		    //supportDao.updateActiviceLog(click.getId());
			//保存激活
			supportDao.saveIosActivateLog(activateLog);
			//记录激活
			LoggerManager.activateLogger(activateLog);
			callChannelParther(activateLog, deviceid, click);
			PickerCache.removeIosActionLog(ad_key, deviceid);
		    
			
		}else{
			logger.logInfo("clickinfo no exist! key:"+(click==null?"null":click.getMac()));
		}
		
	}
	
	/**
	 * 
	* <p>Title: callChannelParther</p>
	* <p>Description:给渠道发送数据</p>
	* @param activate
	* @param param
	* @param click
	* @author cuidd
	* @date 2014年10月22日
	* @return void
	* @version 1.0
	 */
	public void callChannelParther(IosActivateLog activate,String  deviceid,IosActionLog click){
		if(click!=null){
			ChannelConfig config = ConfigCache.findChannelConfig(click.getChannel());
			String callback = !StringUtil.isEmpty(click)?click.getCallback_url():null;
		   	if(!StringUtil.isEmpty(config) || !StringUtil.isEmpty(callback)){
		   		boolean flag=true;
		   		if (config.getChannel().equals("adwalker")&&!StringUtil.isEmpty(click.getAd_key())) {
		   			Advertise ad = PickerCache.getAdvertise(click.getAd_key());
		   			if (ad.getConfirm_type()!=null&&ad.getConfirm_type()==1) {
						flag=false;
					}
		   		 
				}
		   		if (flag) {
		   			new Thread(new CallChannelParther(config, activate, callback)).start();
				}
		   		
		   		//ThreadPool.getInstance().execute((new CallChannelParther(config, activate, callback)));
		   		
		   	}else{
		   		logger.logError("config no exist channelKey:"+click.getChannel());
		   	}
		}else{
			logger.logError("click not exist:"+deviceid);
		}
	}
	/**
	 * @param appId
	 * key为广告主提供标识
	 */
	public CampaignConfig findTCampaignConfig(String key){
		CampaignConfig config = (CampaignConfig)memCached.get(BaseAttribute.CAMPAIGN_CONFIG_KEY+ key);
		if(null==config){
			config=supportDao.findCampaignConfig(key);
			if(config!=null){
				memCached.replace(BaseAttribute.CAMPAIGN_CONFIG_KEY+ key,config,new Date(CACHE_TIME));
			}
		}
		return config;
	}
	public void callService(String param){
	   String url = MOTION+"?m="+param;
	   
	   Map<String, String> m = new HashMap<String, String>();
	   m.put("m", param);
	   
	   logger.logInfo(url);
	   HttpClientUtils.postFromUrl(MOTION, m);
	}
	
	public String hourCheck(String dateHour){
		boolean b= true;
		StringBuffer sbuf = new StringBuffer("");
		//判断上一小时是否有点击.
		Date date = DateUtil.getDate(dateHour,"yyyy-MM-dd-HH");
		Date hourAgo =date==null?DateUtil.getAddHourByDate(new Date(), -1):date;
		String hour = DateUtil.getFormatDate(hourAgo, "yyyy-MM-dd-HH");
		
		List<String[]> list = supportDao.queryActionLogByHourDate(hourAgo);
		if(list.size()==0){
			 b=false;
		     sbuf.append("false| datetime: ["+hour+"] no click info!");
		}else{
			 for(String[] str:list){
				 String adId = StringUtil.dealNull(str[0]);
				 Integer count = NumberUtil.getInt(StringUtil.dealNull(str[1]), 0) ;
				 if(count>=100){
					 Integer activateCount = supportDao.queryActivateLogByHourDate(hourAgo,adId);				 
					 if(activateCount==null || activateCount<=0){
						 if(b){
							 sbuf.append("false|");
							 b=false;
						 }
						 sbuf.append("appId:["+adId+"] no activate info. click count: "+count+"   ");
					 }
				 }
				 
			 }
		}
	    return b?"true|success":sbuf.toString();	
	}
	
	public String hourInfo(String dateHour){
		StringBuffer sbuf = new StringBuffer("");
		//判断上一小时是否有点击.
		Date date = DateUtil.getDate(dateHour,"yyyy-MM-dd-HH");
		System.out.println(date);
		
		Date hourAgo =date==null?DateUtil.getAddHourByDate(new Date(), -1):date;
		System.out.println(hourAgo);
		List<String[]> list = supportDao.queryActionLogByHourDate(hourAgo);
		sbuf.append("size:"+list.size()+"    |");
		 for(String[] str:list){
			 String adId = StringUtil.dealNull(str[0]);
			 String count = StringUtil.dealNull(str[1]);
			 Integer activateCount = supportDao.queryActivateLogByHourDate(hourAgo,adId);
			 sbuf.append(adId+ " click count:"+count+",  activate count"+activateCount+"  |"); 
		 }
	    return sbuf.toString();	
	}
	
	
	public void saveIosActionLog(IosActionLog action){
	   	supportDao.saveIosActionLog(action);
	}
	public IosActionLog findIosActionLog(String mac,String appid){
		return supportDao.findIosActionLog(mac, appid);
	}
	public IosActionLog findIosActionLogIdfA(String idfa,String appid){
		return supportDao.findIosActionLogIdfA(idfa, appid);
	}
	
	public Integer updateActiviceLog(Integer id){
		return supportDao.updateActiviceLog(id);
	}
	public Integer updateActionLogIdfaToMac(Integer id){
		return supportDao.updateActionLogIdfaToMac(id);
	}
	
	
	public CampaignConfig findCampaignConfigBybundleid(String bundleid){
		return supportDao.findCampaignConfigBybundleid(bundleid);
	}
	

}
