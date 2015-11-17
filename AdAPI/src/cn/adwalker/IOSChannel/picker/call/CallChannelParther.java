package cn.adwalker.IOSChannel.picker.call;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import cn.adwalker.IOSChannel.picker.bean.ApplicateAreaNum;
import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.bean.ParamConfig;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.DateUtil;
import cn.adwalker.IOSChannel.picker.util.HttpClientUtils;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.NumberUtil;
import cn.adwalker.IOSChannel.picker.util.SpringUtil;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;
import cn.adwalker.IOSChannel.picker.xmemcached.PickerCache;
import cn.adwalker.IOSChannel.strategy.channel.ChannelDetainStrategy;
import cn.adwalker.IOSChannel.strategy.channel.IChannelCallbackStrategy;
import cn.adwalker.IOSChannel.strategy.media.IMediaCallbackStrategy;
import cn.adwalker.IOSChannel.strategy.media.MediaCAStrategy;
import cn.adwalker.IOSChannel.strategy.media.MediaCallbackConfig;
import cn.adwalker.IOSChannel.strategy.media.MediaDetainStrategy;
import cn.adwalker.IOSChannel.strategy.media.MediaIPStrategy;
import cn.adwalker.IOSChannel.strategy.media.MediaareasStrategy;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;

public class CallChannelParther implements Runnable {
    private static final Logger logger = Logger.getLogger("callChannelParther");
	private ChannelConfig config;
	private String callback;
	private IosActivateLog activate;
	private boolean success;
	private Integer times = 0;
	public CallChannelParther(ChannelConfig config,IosActivateLog activate,String callback){
		this.config=config;
		this.activate=activate;
		this.callback=callback;
	}
	@Override
	public void run() {
        String url = null;
		if(!StringUtil.isEmpty(callback)){
			url = callback;
		}else{
			//change by jief 2014-07-30
			url = createUrl(config,createMap());
		}
		if(isSendStrategy()){	
			do{
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				success = callChannelParther(url,times);
				times++;
			}while(!success && times<8);
			logger.logInfo(url+"  done--- "+success);
		}else{
		    logger.logInfo("OpenudiError: adId"+activate.getAd_id()+"  openudid:"+activate.getOpenudid()+"  areaCode:"+activate.getArea_code()+"  appKey:"+activate.getApplication_key());
		}
	}
	public boolean callChannelParther(String url,Integer times){
		String res = HttpClientUtils.readFromURL(url, "utf-8");
		logger.logInfo(url+"   "+res+"  "+times);
		return  !"false".equals(res);
	}
	private boolean isSendStrategy(){
		long timestamp=System.currentTimeMillis();
		boolean b=true;
		StringBuffer sbuf = new StringBuffer("adId:"+activate.getAd_id()+"  openudid:"+activate.getOpenudid()+"  areaCode:"+activate.getArea_code()+"  appKey:"+activate.getApplication_key());
		int status=0;
		IMediaCallbackStrategy callbackStrategy=null;
		SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
		if(BaseAttribute.ADWALKER.equals(config.getChannel())){
			MediaCallbackConfig callbackConfig = ConfigCache.getMediaCallbackConfig(activate.getApplication_key());
			callbackStrategy=MediaDetainStrategy.getInstance();
			b=callbackStrategy.isCallback(supporDao, callbackConfig, activate, sbuf);
			if(b){
				callbackStrategy=MediaareasStrategy.getInstance();
				b=callbackStrategy.isCallback(supporDao, callbackConfig, activate, sbuf);
				if(b){
					callbackStrategy= MediaCAStrategy.getInstance();
					b=callbackStrategy.isCallback(supporDao, callbackConfig, activate, sbuf);
					if(b){
						callbackStrategy=MediaIPStrategy.getInstance();
						b=callbackStrategy.isCallback(supporDao, callbackConfig, activate, sbuf);
					}
				}
			}else{
				status=2;
			}
		}else{
			IChannelCallbackStrategy ccbs= ChannelDetainStrategy.getInstance();
			b=ccbs.isCallback(config, activate, sbuf);
			if(!b){
				status=2;
			}
		}
		if(!b){
			supporDao.updateActivateSend(activate.getAd_id(), activate.getMac(), status);
			Logger.getLogger("unback").logInfo(sbuf.toString());
		}	
		System.out.println("验证是否回调媒体或渠道耗时"+(System.currentTimeMillis()-timestamp)+"毫秒");
		return b;
	}
	@SuppressWarnings("unused")
	private boolean isSend(){
		boolean b = true;
		StringBuffer sbuf = new StringBuffer("adId "+activate.getAd_id()+"  openudid:"+activate.getOpenudid()+"  areaCode:"+activate.getArea_code()+"  appKey:"+activate.getApplication_key());
		SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
		int status=0;
		if(BaseAttribute.ADWALKER.equals(config.getChannel())){
			//String s="OpenudiError: adId"+activate.getAd_id()+"  openudid:"+activate.getOpenudid()+"  areaCode:"+activate.getArea_code()+"  appKey:"+activate.getApplication_key();
			//判断openudid是否激活过两次
			//changed by jief select count(1)
			Integer c = supporDao.getCountByOpenUdidAndAdId(activate.getAd_id(), activate.getOpenudid());
			b = c<=1;	
			//判断地区最大激活数
			if(b){
				if(!StringUtil.isEmpty(activate.getArea_code()) && !StringUtil.isEmpty(activate.getApplication_key())){
				   	 Integer appId = NumberUtil.getInteger(activate.getApplication_key(),0);
				   	 ApplicateAreaNum aan = PickerCache.findApplicateAreaNum(appId, activate.getArea_code());
				     if(aan!=null){
				    	 //changed by jief 2014-06-24
				    	 Integer num = supporDao.getActivateCountByAreaCode(activate.getArea_code(),activate.getApplication_key());
				    	 b = num<aan.getNum();
				    	 if(!b){
				    		 sbuf.append(" area max:"+num);
				    	 }
					   	 logger.logInfo("ApplicateAreaNum appId:"+aan.getApp_id()+"  areacode:"+aan.getArea_code()+" num:"+aan.getNum());
				     }else{
				    	 logger.logInfo("aannull areacode:"+activate.getArea_code()+" appid:"+activate.getApplication_key()+"");
				     }
				}
			//单个ip段判断	
			if(b){
				if(!StringUtil.isEmpty(activate.getClient_ip())){
				    String ipduan = ipdo(activate.getClient_ip());
				    //add by jierfei
				    Integer ipnum = supporDao.getActivateCountByIpduan(ipduan, activate.getAd_id());
				    b = ipnum<ConfigUtil.getInteger("ipduan.max",30);
				    if(!b){
				    	sbuf.append(" ipnum max:"+ipnum +" ipduan:"+ipduan);
				    }
				}
			}
			//激活时间差
			if(b){
				//System.out.println(activate.getCreate_time()+"   "+activate.getClick_time());
				if(!StringUtil.isEmpty(activate.getCreate_time()) && !StringUtil.isEmpty(activate.getClick_time())){
					Long second = DateUtil.getSecondByTwoDate(activate.getClick_time(), activate.getCreate_time());
					Integer minSecond = ConfigUtil.getInteger("activate.min.second",20);
					b = second.intValue()>minSecond;
					logger.logInfo("activate_seconds  "+second+"   "+activate.getClient_ip()+"   "+activate.getArea_code());
					if(!b){
				    	sbuf.append(" activate second less then:"+minSecond+"");
				    }
				}
			}
		
			
			
			}else{
				sbuf.append(" openudid max:"+c);
			}
		}
		//返回渠道比率
		if(b){
			CampaignConfig capfig = ConfigCache.findCampaignCongfig(activate.getAd_id());
			if(capfig!=null){
				float rdf=StringUtil.floatRandom();
				Float adpassrate=capfig.getPass_rate();
				Float chpassrate=config.getPass_rate();
				if(null!=adpassrate && null!=chpassrate){
					float pr=adpassrate*chpassrate;
					b= (rdf <= pr) ;
					if(!b){
						status=2;
				    	sbuf.append(" pass rate is more then:"+pr+"");
				    }
				}
			}
		}
		if(!b){
			supporDao.updateActivateSend(activate.getAd_id(), activate.getMac(), status);
			Logger.getLogger("unback").logInfo(sbuf.toString());
		}		
		return b;
	}
	
	//把ip形如 192.168.1.20转换为192.168.1
	public String ipdo(String ip){
		Pattern pattern = Pattern.compile("\\.\\d+$"); 
        Matcher matcher = pattern.matcher(ip); 
        //String a=matcher.replaceAll(""); 
        return matcher.replaceAll(""); 
	}
	
	
	public boolean parseJson(String res){
		try{
		   JSONObject o = JSONObject.fromObject(res);
		   return  o.getBoolean("success");
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	    //return false;
	}
	/**
	 * add by jief 2014-07-30
	 * @param config
	 * @param map
	 * @return
	 */
	public String createUrl(ChannelConfig config,Map<String, String> map){
		if(!StringUtil.isEmpty(config.getService_name())){
			if(config.getService_name().equals(AppConstant.CH_DOMOB)){
				return CallChannelPartherUtils.createUrl4domob(activate, config);
			}else{
				return createUrl(config.getUrl(), map);
			}
		}else{
			return createUrl(config.getUrl(), map);
		}
	}
	
	public String createUrl(String url,Map<String, String> map){
		return StringUtil.createUrl(url, map);
	}
	
	public String loggerStr(String url,Map<String,String> map){
		boolean b = true;
    	StringBuffer sbuf = new StringBuffer(url);
        for(Entry<String, String> en:map.entrySet()){
        	String param = en.getKey();
        	String value = en.getValue();
        	sbuf.append(b?("?"+param+"="+value):("&"+param+"="+value));
        	if(b){b=false;}
        }
        return sbuf.toString();
	}
	
	public Map<String, String> createMap(){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put(pn("appid"), StringUtil.dealNull(activate.getAd_id()));
		 map.put(pn("deviceid"), StringUtil.dealNull(activate.getMac()));
		 map.put(pn("OPENUDID"), StringUtil.dealNull(activate.getOpenudid()));
		 map.put(pn("IDFA"), StringUtil.dealNull(activate.getIdfa()));
		 map.put(pn("IDFV"), StringUtil.dealNull(activate.getIdfv()));
		 for(Entry<String, ParamConfig> en:config.map.entrySet()){
		    	ParamConfig c = en.getValue();
		        map.put(c.getParam_name(), c.getParam_value());
		 }
		 return map;
	}
	
	private String pn(String name){
		ParamConfig p = config.map.get(name);
		if(p!=null){
			String alis =p.getParam_alis();
			config.map.remove(name);
			return alis;
		}
		return name;
	}
}
