package cn.adwalker.IOSChannel.picker.checker;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.IOSChannel.logger.AdSendLogger;
import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.bean.ChannelBean;
import cn.adwalker.IOSChannel.picker.bean.IosActionLog;
import cn.adwalker.IOSChannel.picker.bean.YjfBean;
import cn.adwalker.IOSChannel.picker.service.SupportService;
import cn.adwalker.IOSChannel.picker.util.HttpHelp;
import cn.adwalker.IOSChannel.picker.util.SpringUtil;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.service.impl.MemcacheChannelServiceImpl;
import cn.adwalker.IOSChannel.vo.AdvertisementChannel;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;



public abstract class PartherCheck {

	public static SupportService supportService=SpringUtil.getBean("supportService",SupportService.class);
	public static MemcacheChannelServiceImpl memcacheChannelService = SpringUtil.getBean("memcacheChannelService", MemcacheChannelServiceImpl.class);
	public static String SELF_CHANNEL = "zijiren";
	
	public JSONObject o = new JSONObject();
	
	
	public JSONObject getJsonObject(){
		return o;
	}
	public CampaignConfig findTCampaignConfig(String key){
		return null;
	}
	/**
	 * @param bean
	 * 保存T_IOS_ACTION_LOG 
	 * 对于IOS7的情况,需要把mac值重置为空串或者null。并保存openudid idfa idfv;
	 */
	abstract void saveIosActionLog(YjfBean bean);
	
	/**
	 * @param bean
	 * 通知渠道激活确认 调用渠道激活接口
	 */
	abstract void callComfirmCheck(YjfBean bean);
	
	/**
	 * @param bean
	 * 通知设备终端添加积分 调用service端保存终端积分信息。
	 */
	abstract void callAddScore(YjfBean bean);
	
	/**
	 * @param bean
	 * 激活确认状态修改 修改T_IOS_ACTION_LOG表状态
	 */
	abstract boolean updateComriamActive(YjfBean bean);
	
	/**
	 * @param bean
	 * 参数验证 channel
	 */
	abstract boolean validateChannelBean(YjfBean bean);
	
	/**
	 * @param bean
	 * @return
	 * 参数验证 ad
	 */
	abstract boolean validateAdBean(YjfBean bean);
	
	
	/**
	 * 根据配置信息生成确认激活的key标识。通过此标识进行A的数据验证。对于
	 * @param config
	 * @param b
	 * @return
	 */
	abstract String createCheckKey(CampaignConfig config,ChannelBean b);
	
	
	/**
	 * 通知渠道确认激活
	 * channel渠道标识     ,    action 激活记录  copy二飞代码
	 */
	public void callChannelConfirm(String channelStr,IosActionLog action){
		AdvertisementChannel channel = memcacheChannelService.getChannel(channelStr);
		if(channel!=null && action!=null){
			if(!StringUtil.isEmpty(channel.getUrl()) && channel.getUrl().indexOf("http")!=-1){
				//changed by jief 2013-09-10 
				if(channel.getChannel().equals("adSage") && StringUtils.isNotBlank(action.getMac())){
					String macOne = action.getMac();
					String macNew = "";
					String[] strs = new String[macOne.length() - 1];
					for (int k = 0; k < macOne.length() - 1; k += 2) {
						strs[k] = macOne.substring(k, k + 2);
						macNew += strs[k] + ":";
					}
					action.setMac(macNew);
				}
			}
			
			StringBuffer  params = new StringBuffer();
			params.append(channel.getUrl()).append("")
			              .append(channel.getAdid_para()).append("=").append(action.getAdId())
			              .append("&").append(channel.getDeviceid_para()).append("=").append(StringUtils.isBlank(action.getMac())?"":action.getMac())
			              .append("&").append(channel.getTime_para()).append("=").append(System.currentTimeMillis());
			if(StringUtils.isNotBlank(channel.getOpenUdid()) && StringUtils.isNotBlank(action.getOpenudid())){
				params.append("&").append(channel.getOpenUdid()).append("=").append(action.getOpenudid());
			}
			if(StringUtils.isNotBlank(channel.getIdfa()) && StringUtils.isNotBlank(action.getIdfa())){
				params.append("&").append(channel.getIdfa()).append("=").append(action.getIdfa());
			}
			if(StringUtils.isNotBlank(channel.getIdfv()) && StringUtils.isNotBlank(action.getIdfv())){
				params.append("&").append(channel.getIdfv()).append("=").append(action.getIdfv());
			}
			if(channel.getChannel().equals(AppConstant.CHANNEL_RUANLIE)){
				if(action.getAdId().equals("9c69842b389a48b38ab8c5a3a9cfeaa7")){
					params.append("&").append("appId=").append("1122");
				}
			}
			//通知渠道确认激活
			String k = HttpHelp.readFromURL(params.toString(), null);
			AdSendLogger logger =new AdSendLogger();
			logger.logInfo(params.toString(), k);
		}
	}
	/**
	 *  
	 */
	public void addScore(IosActionLog action){
		//积分墙
		if(action.getPage_type()!=null&&action.getChannel()!=null){
			if(action.getPage_type().toString().equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)&&action.getChannel().equals(AppConstant.ZIJIREN)){
				String url =AppConstant.CONFIG_ESCORE_SERVICE;
				StringBuffer  params = new StringBuffer();
				params.append(url).append(ConfigUtil.getString("iosAddScore"))
				      .append("?adId=").append(action.getAdId()).append("&deviceId=").append(StringUtils.isBlank(action.getMac())?action.getOpenudid():action.getMac());
				HttpHelp.readFromURL(params.toString(), null);   //.sendGet(params.toString());
			}
		}
		
	}
	
	
}
