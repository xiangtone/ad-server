package cn.adwalker.IOSChannel.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.dao.IOffLineAddScoreDao;
import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.bean.IosActionLog;
import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.bean.UserInfo;
import cn.adwalker.IOSChannel.picker.call.CallChannelParther;
import cn.adwalker.IOSChannel.picker.checker.LoggerManager;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.service.SupportService;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;
import cn.adwalker.IOSChannel.picker.xmemcached.PickerCache;
import cn.adwalker.IOSChannel.service.IAdvertisementService;
import cn.adwalker.IOSChannel.service.IMemcacheChannelService;
import cn.adwalker.IOSChannel.service.IOffLineAddScoreService;
import cn.adwalker.ad.bean.Advertise;
import cn.adwalker.ad.bean.OffLineData;
@Service("offLineAddScoreService")
public class OffLineAddScoreServiceImpl implements IOffLineAddScoreService{
	private final static Logger logger = Logger.getLogger(SupportService.class);
	@Resource
	private IOffLineAddScoreDao offLineAddScoreDao;
	@Resource
	private IAdvertisementService advertisementService;

	@Resource
	private IMemcacheChannelService memcacheChannelService;
	@Resource
	private SupportDao supportDao;
	@Override
	public void retScore(Integer status) {
	   List<OffLineData> dataList=offLineAddScoreDao.getDatasByStatus(status);
	   if(dataList!=null && dataList.size()>0){
		   for(OffLineData od:dataList){
			   if(StringUtils.isBlank(od.getAd_id()) ||
					   (StringUtils.isBlank(od.getIncome_mac()) && StringUtils.isBlank(od.getIdfa()))){
				   logger.logError("线下返积分数据有误!");
				   offLineAddScoreDao.update(od.getId(), 5);
				   continue;
			   }
				new AddScoreThread(od,offLineAddScoreDao,supportDao).run();
				try{
					Thread.sleep(20);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
		   }
	   }
	}
  //changed by jief 取消多线程
  public static class AddScoreThread {
	 private IOffLineAddScoreDao  idd;
	 private OffLineData od;
	 private SupportDao supportDao;
     public AddScoreThread(OffLineData od,IOffLineAddScoreDao idd,SupportDao supportDao){
    	this.idd=idd;
    	this.od=od;
    	this.supportDao=supportDao;
     }
	public void run() {
	      cc(od.getAd_id(), od.getIncome_mac());
	    
	  }
	
	
	
	public void cc(String appid, String deviceid){
		  IosActionLog click = PickerCache.getIosActionLog(appid, deviceid);
		  if(click==null){
			  logger.logInfo("clickinfo no exist! key:"+(click==null?"null":click.getMac()));
			  idd.update(od.getId(), 5);
			  return;
		  }else if(supportDao.existActivateLog(appid, deviceid)){
			  logger.logInfo("clickinfo has activete! key:"+click.getMac());
			  idd.update(od.getId(),4);
			  return;
		  }else{
				IosActivateLog activateLog = new IosActivateLog();
				String areaCode=click.getArea_code();
				if(!StringUtil.isEmpty(click.getOpenudid()) && StringUtil.equals(BaseAttribute.ADWALKER, click.getChannel()) ){
					UserInfo u = PickerCache.loadUserInfo(click.getOpenudid());
					areaCode = u==null || StringUtil.isEmpty(u.getAreaCode())?PickerCache.getAreaCodeByIp(click.getClient_ip()):u.getAreaCode();
				}
				activateLog.setAd_id(appid);
				activateLog.setMac(appid);
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
			    //add by jief 2014-06-24 start
			    CampaignConfig config = ConfigCache.findCampaignCongfig(deviceid);
			    activateLog.setIn_price(null==config?0d:config.getAdv_price());
			    //end
			    activateLog.setSsid(click.getSsid());
			    activateLog.setBssid(click.getBssid());
			    activateLog.setPhoneName(click.getPhoneName());
			    activateLog.setLatitude(click.getLatitude());
			    activateLog.setLongitude(click.getLongitude());
				//保存激活
				supportDao.saveIosActivateLog(activateLog);
				//记录激活
				LoggerManager.activateLogger(activateLog);
				callChannelParther(activateLog, click);
				PickerCache.removeIosActionLog(appid, deviceid);
		    	//激活成功
				idd.update(od.getId(), 3);
			}
	}
	
	
	
	
	public void callChannelParther(IosActivateLog activate,IosActionLog click){
		if(click!=null){
			ChannelConfig config = ConfigCache.findChannelConfig(click.getChannel());
			String callback = !StringUtil.isEmpty(click)?click.getCallback_url():null;
		   	if(!StringUtil.isEmpty(config) || !StringUtil.isEmpty(callback)){
		   		new Thread(new CallChannelParther(config, activate, callback)).start();
		   	}else{
		   		logger.logError("config no exist channelKey:"+click.getChannel());
		   	}
		}else{
			logger.logError("click not exist:");
		}
	}
  }
  
  public static void sendURL(String url)throws Exception{
	  String result="";
	  URL getUrl = new URL(url);
      // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
      // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
      HttpURLConnection connection = (HttpURLConnection) getUrl
              .openConnection();
      // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
      // 服务器
      connection.connect();
      // 取得输入流，并使用Reader读取
      BufferedReader reader = new BufferedReader(new InputStreamReader(
              connection.getInputStream()));
      String lines;
      while ((lines = reader.readLine()) != null) {
   	   result += lines+"\r\n";   
      }
      logger.logInfo(result);
      reader.close();
      // 断开连接
      connection.disconnect();
   }
  
}
