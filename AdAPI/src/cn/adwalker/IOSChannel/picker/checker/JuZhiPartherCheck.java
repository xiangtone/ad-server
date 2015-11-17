package cn.adwalker.IOSChannel.picker.checker;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.adwalker.IOSChannel.picker.bean.AdBean;
import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.bean.ChannelBean;
import cn.adwalker.IOSChannel.picker.bean.IosActionLog;
import cn.adwalker.IOSChannel.picker.bean.YjfBean;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.util.Base64;
import cn.adwalker.IOSChannel.picker.util.HttpHelp;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.MD5Util;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.ad.util.AppConstant;

/**
 * @author admin
 * 桔子渠道对接类
 */
public class JuZhiPartherCheck extends PartherCheck {
    private static final Logger logger = Logger.getLogger(JuZhiPartherCheck.class);
    private CampaignConfig config=null;
    private IosActionLog action=null;
    private static final String skey="3279e91207c231adfeb62d4c56061150"; //"c86be9aca16829a2467f7472f9b89c79";
    private static final String SUCCESS_CODE="000000";
    private static final String FAIL_CODE="000005";
    
	@Override
	public String createCheckKey(CampaignConfig config,ChannelBean b){
		String checkKey = "";
		if(b.isIos6()){//ios6 采用mac地址为确认的唯一标识
			checkKey=StringUtil.dealNull(b.getDeviceid()).replaceAll(":", "");	
		}else if(b.isIos7()){//ios 7则采用openudid idfa idfv中的一种或者多种作为激活的标识
			checkKey+=StringUtil.dealNull(b.getIDFA()).toUpperCase();
		}
		return checkKey;
	}
    
	public JuZhiPartherCheck getInstance(){
		return new JuZhiPartherCheck();
	}
	@Override
	void saveIosActionLog(YjfBean bean) {
		ChannelBean b = (ChannelBean)bean;
		
	
		
		config = supportService.findTCampaignConfig(b.getAppid());
		if(config!=null){
			String checkKey = createCheckKey(config, b);
			IosActionLog action = new IosActionLog();
			action.setAdId(b.getAppid());
			action.setStat_date(StringUtil.isEmpty(b.getEventtime())?Long.valueOf(new Date().getTime()).intValue():StringUtil.getInt(b.getEventtime(), 0));
			action.setChannel(b.getSource());
			action.setCreateTime(new Date());
			action.setIdfa(StringUtil.dealNull(b.getIDFA()).toUpperCase());
			action.setIdfv(b.getIDFV());
			action.setMac(checkKey);
			action.setOpenudid(b.getOPENUDID());
			action.setStatus(BaseAttribute.IOS_ACTION_LOG_STATUS_0);
			//LoggerManager.actionLogger(action);
			/**
			 * 非自由渠道则保存action_log
			 * */
			if(!SELF_CHANNEL.equals(action.getChannel())){
				supportService.saveIosActionLog(action);
			}
		}else{
			logger.logError(" can not find campgin_config. key:"+b.getAppid());
		}
	}
	
	@Override
	void callAddScore(YjfBean bean) {
		if(null!=action){
			//自由渠道的积分墙则返回积分
			if(StringUtil.dealNull(action.getPage_type()).equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)&&action.getChannel().equals(SELF_CHANNEL)){
				//mac为空则把idfa重置为mac 以便返还积分
				if(StringUtil.isEmpty(action.getMac())){
					logger.logInfo("updateIdfaToMacJiangShen==============================="+action.getId());
				   	supportService.updateActionLogIdfaToMac(action.getId());
				}
				addScore(action);
			}else{//非自由渠道则通知渠道确认激活
				callChannelConfirm(action.getChannel(), action);
			}
		}
	}

	@Override
	void callComfirmCheck(YjfBean bean) {
		if(config!=null){
			String url = config.getUrl();
			String method = config.getSend_type();
			if(!StringUtil.isEmpty(url) && url.indexOf("http")!=-1){
				Map<String, String>  map = getParamter((ChannelBean)bean);
				if("POST".equals(method)){
					HttpHelp.postFromUrl(url, map);
				}else{
					HttpHelp.readFromURL(url, map, null);
				}
				o.accumulate("code", "success");
			}else{
				o.accumulate("code", "Illegal URL");
				logger.logInfo("Illegal URL:"+url);
			}
		}else{
			o.accumulate("code", "can not find campgin_config");
			logger.logError(" can not find campgin_config");
		}
	}
	private Map<String, String> getParamter(ChannelBean data){
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", data.getAppid());
		//map.put("channel", data.getSource());
		map.put("channel", "2000036");
		//根据桔子要求 mac 需要改成小写 去掉:号
		map.put("imei", data.isIos6()?StringUtil.dealNull(data.getDeviceid()).toLowerCase().replaceAll(":", ""):StringUtil.dealNull(data.getIDFA()).toUpperCase());
		map.put("pridata", data.getSource());//保存渠道信息
		//String mac = data.isIos6()?data.getDeviceid():data.getIDFA();
	    //mac = StringUtil.dealNull(mac).replaceAll("-","").replaceAll(":", "");
		map.put("target", "0");
		return map;
	}

	@SuppressWarnings("unused")
	@Override
	boolean updateComriamActive(YjfBean bean) {
		AdBean ab = (AdBean)bean;
		String data = ab.getData();
	    JSONObject obj = parseObject(data);
        if(obj!=null){
           String appid = obj.containsKey("appid")?obj.getString("appid"):null;//{"pridata":"","appid":"","imei":"","mac":"","tradeno":""}	
           String imei = obj.containsKey("imei")?obj.getString("imei"):null;//激活标识
           String mac = obj.containsKey("imei")?obj.getString("mac"):null;
           String tradeno = obj.getString("tradeno");
           String pridata=obj.getString("pridata");//保存渠道信息
           action = supportService.findIosActionLog(imei, appid);
           //自有媒体激活，idfa存在idfa字段中
           if(SELF_CHANNEL.equals(pridata) && action==null){
        	   logger.logInfo(" SELF_CHANNEL CHECK ");
        	   action = supportService.findIosActionLogIdfA(imei, appid);
           }
           if(null!=action){
        	   if(!StringUtil.equals(BaseAttribute.IOS_ACTION_LOG_STATUS_1, action.getActivite_status())){
        		   supportService.updateActiviceLog(action.getId());
        		   //LoggerManager.activiteLogger(action);
        		   o.accumulate("Code",SUCCESS_CODE);
                   o.accumulate("Content", "success");
        		   return true;
        	   }else{
            	   o.accumulate("Code",FAIL_CODE);
               	   o.accumulate("Content", "不允许重复激活");
        	   }
           }else{
        	   o.accumulate("Code",FAIL_CODE);
           	   o.accumulate("Content", "未找到相应的记录");
           }
           
        }else{
        	o.accumulate("Code",FAIL_CODE);
        	o.accumulate("Content", "error");
        }
        return false;
	}
	private JSONObject parseObject(String data){
		if(null!=data){
			String str="";
			try {
				str = new String(Base64.decryptBASE64(data));
				logger.logInfo(str);
				return JSONObject.fromObject(str);
			} catch (Exception e) {
				logger.logError(" json error: " +str+"  by "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	boolean validateChannelBean(YjfBean bean) {
		ChannelBean b = (ChannelBean)bean;
		if(b.isIos7()){//ios7 openudid idfa idfv必须有一个不为空
			if(StringUtil.isEmpty(b.getOPENUDID()) && StringUtil.isEmpty(b.getIDFA()) && StringUtil.isEmpty(b.getIDFV())){
				return false;
			}
		}else if(StringUtil.isEmpty(b.getDeviceid())){//ie6 deviceid不能为空
			return false;
		}
		return true;
	}
	
	@Override
	boolean validateAdBean(YjfBean bean) {
		AdBean ab = (AdBean)bean;
		String data = ab.getData();
	    String sign = ab.getSign();
	    String vali_sign=new MD5Util().getMD5ofStr(data+skey);
	    if(StringUtil.equalsIgnoreCase(sign, vali_sign)){
	    	return true;
	    }else{
	    	o.accumulate("Code",FAIL_CODE);
	    	o.accumulate("Content", "validate sign error");
        	return false;
	    }
	}
	
	

}
