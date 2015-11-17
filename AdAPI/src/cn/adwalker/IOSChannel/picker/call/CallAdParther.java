package cn.adwalker.IOSChannel.picker.call;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.bean.ParamConfig;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.HttpClientUtils;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.SpringUtil;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;
import cn.adwalker.IOSChannel.strategy.admaster.AdAreasStrategy;
import cn.adwalker.IOSChannel.strategy.admaster.AdBssidStrategy;
import cn.adwalker.IOSChannel.strategy.admaster.AdIPStrategy;
import cn.adwalker.IOSChannel.strategy.admaster.AdSendConfig;
import cn.adwalker.IOSChannel.strategy.admaster.IAdMasterSendStrategy;
import cn.adwalker.ad.util.ConfigUtil;

public class CallAdParther implements Runnable {
	private static final Logger logger = Logger.getLogger("callAdParther");
	private static final String CALL_BACK_URL=ConfigUtil.getString("check.callback");//http://api.adwalker.cn/common/ainfo.do
	private static final String AREA_MATCH="(龙岩|福州|泉州|漳州|三明|梅州|韶关|池州|赣州|郑州|宿迁|无锡)";
	private static final String  SOURCE="adwalker";
	private boolean success = false;
	private Integer times=0; 
	private CampaignConfig config;
	private IosClick iosClick;
	public CallAdParther(){
		
	}
	public CallAdParther(CampaignConfig config,IosClick iosClick){
		this.config=config;
		this.iosClick=iosClick;
	}
	@Override
	public void run() {
		cn.adwalker.IOSChannel.picker.vo.ParamConfig pc=this.createParamMap();
		String url=StringUtil.createUrl(StringUtil.adUrl(config.getUrl(),iosClick.appid),pc.getParams());
		if(isAdSendStrategy()){
			do{
				success = callAdParther(url,pc.getHeaders(),times);
				try {
					logger.logInfo("send times  :"+times);
					Thread.sleep(5000);
					times++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}while(!success && times<8);
			logger.logInfo(url+" done---  "+success);
		}else{
			logger.logInfo("refuseSendAdParther openudid:"+iosClick.getOPENUDID()+"  appId:"+iosClick.getAppid()+"  deviceId:"+iosClick.getDeviceid()+" rate:"+config.getPass_rate());
		}
	}
	
	
	/**
	 * 
	* <p>Title: isAdSendStrategy</p>
	* <p>Description:是否给广告主发送数据</p>
	* @return
	* @author luoyouhua
	* @date 2014年10月15日
	* @return boolean
	* @version 1.0
	 */
	public boolean isAdSendStrategy(){
		boolean b=true;
		StringBuffer sbuf = new StringBuffer("appid:"+iosClick.appid+"  openudid:"+iosClick.getOPENUDID());
		if(StringUtil.equals(SOURCE, iosClick.source)){
			if(!StringUtil.isEmpty(iosClick.getOPENUDID())){
				/**openudid*/
				SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
				//changed by jief select count(1) 2014-06-24
				Integer c = supporDao.getCountByOpenUdidAndAdId(iosClick.getAppid(), iosClick.getOPENUDID());
				b=c<2;
				if(!b){
					sbuf.append("激活数超过2了 :"+c);
				}
				IAdMasterSendStrategy sendStrategy=null;
				if(b){
					AdSendConfig sendConfig=ConfigCache.getAdSendConfig(iosClick.getAd_key());
					sendStrategy=AdIPStrategy.getInstance();
					 b=sendStrategy.isSend(supporDao, sendConfig, iosClick, sbuf);
					 if(b){
						 sendStrategy=AdAreasStrategy.getInstance();
						 b=sendStrategy.isSend(supporDao, sendConfig, iosClick, sbuf);
						 if(b){
							 sendStrategy=AdBssidStrategy.getInstance();
							 b=sendStrategy.isSend(supporDao, sendConfig, iosClick, sbuf);
//							 if(b){
//								 sendStrategy=AdLatLonStrategy.getInstance();
//								 b=sendStrategy.isSend(supporDao, sendConfig, iosClick, sbuf);
//							 }
						 }
					 }
				}
			}
		}
		if(!b){
			Logger.getLogger("unsend").logInfo(sbuf.toString());
		}
		return b;
	}
	
	
	public boolean isSend(){
		boolean b=true;
		StringBuffer sbuf = new StringBuffer("appid:"+iosClick.appid);
		if(StringUtil.equals(SOURCE, iosClick.source)){
			if(!StringUtil.isEmpty(iosClick.getOPENUDID())){
				/**openudid*/
				SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
				//changed by jief select count(1) 2014-06-24
				Integer c = supporDao.getCountByOpenUdidAndAdId(iosClick.getAppid(), iosClick.getOPENUDID());
				b=c<2;
				sbuf.append("OpenUdidAndAdId :"+c+"  "+b);
				
				/** ip 段判断*/
				if(b){
					if(!StringUtil.isEmpty(iosClick.client_ip)){
						//add by jief 2014-06-24
						Integer ipNum=supporDao.getActivateCountByIp(iosClick.client_ip, iosClick.appid);
						b=ipNum<=ConfigUtil.getInteger("send.ip.max", 3);
						if(b){//单个ip限制激活3次
							String ipduan = StringUtil.ipdo(iosClick.client_ip);
							//add by jief 2014-06-24
							Integer num=supporDao.getActivateCountByIpduan(ipduan, iosClick.appid);
							b = num<ConfigUtil.getInteger("send.ipduan.max",50);							
							sbuf.append("| ipduan count:"+num+" ipduan:"+ipduan+"  "+b);
						}else{
							sbuf.append("| ip count:"+ipNum+" ip:"+iosClick.client_ip+"  "+b);
						}
						
					}
				}
				
				/**地区随机发送
           		if(b){
           			//System.out.println(iosClick.areaCode);
           			if(!StringUtil.isEmpty(iosClick.areaCode) && !StringUtil.isEmpty(iosClick.ad_key)){
           				Integer adId = NumberUtil.getInteger(iosClick.ad_key, 0);
           				AdAreaRate rate=PickerCache.findAdAreaRate(adId, iosClick.areaCode);
           				RandomCollection<String> r = new RandomCollection<String>();
           				r.add(rate.getRate(), "1");
           				r.add(100-rate.getRate(), "0");
           				b = StringUtil.equals("1", r.next());
           				sbuf.append(" | random fail rate:"+rate.getRate()+"  "+b);
           			}
           		}
           		*/
           		/**地区屏蔽*/
           		if(b){
           			//1:appId不在白名单中 2:地区过滤
           			if(!StringUtil.dealNull(iosClick.app_key).matches(StringUtil.dealNull(ConfigUtil.getString("pass.appid"),"(-1)"))
           					&&"true".equalsIgnoreCase(config.getSend_type())&&!StringUtil.isEmpty(iosClick.areaCode)&&iosClick.areaCode.matches(AREA_MATCH)){
           				b=false;
           				sbuf.append(" | fail areaCode:"+iosClick.areaCode+" "+b);
           			}
           		}
			}
		}
		if(!b){
			Logger.getLogger("unsend").logInfo(sbuf.toString());
		}
		return b;
	}
	
	/**
	 * 
	* <p>Title: callAdParther</p>
	* <p>Description:TODO</p>
	* @param url
	* @param headers
	* @param times
	* @return
	* @author cuidd
	* @date 2014年10月25日
	* @return boolean
	* @version 1.0
	 */
	public boolean callAdParther(String url,Map<String, String> headers,Integer times){
		String res=this.requestCommon(url,headers);
		logger.logInfo(url+"  "+res+"   "+times);
		return  !"false".equals(res);
	}
	
	
	private String requestCommon(String url,Map<String, String> headers){
		String s=null;
		if (!StringUtil.isEmpty(url)) {
			if (headers!=null&&!headers.isEmpty()) {
				 s=HttpClientUtils.readFromURL(url, "utf-8",headers);
			}else {
				 s=HttpClientUtils.readFromURL(url, "utf-8");
			}
			
		}else {
			logger.logError("发送地址不存在");
		}
		return s;
	}
	
	
//	public Map<String, String> createMapbak(){
//		 Map<String, String> map = new HashMap<String, String>();
//		 map.put("appid", iosClick.appid);
//		 map.put("deviceid", iosClick.deviceid);
//		 map.put("eventtime", StringUtil.dealNull(iosClick.eventtime));
//		 map.put("OPENUDID", StringUtil.dealNull(iosClick.getOPENUDID()));
//		 map.put("IDFA", StringUtil.dealNull(iosClick.getIDFA()));
//		 map.put("IDFV", StringUtil.dealNull(iosClick.getIDFV()));
//		 map.put("DATA", WalkerBlur.getEncryption(StringUtil.dealNull(iosClick.getSource())));
//		 map.put("source", StringUtil.dealNull(config.getSource(),SOURCE));
//		 String callback=CALL_BACK_URL+"?appid="+iosClick.appid+"&deviceid="+StringUtil.dealNull(iosClick.deviceid)+"&OPENUDID="+StringUtil.dealNull(iosClick.OPENUDID)+"&IDFA="+StringUtil.dealNull(iosClick.IDFA)+"&IDFV="+StringUtil.dealNull(iosClick.IDFV);
//		 map.put("callback", StringUtil.encode(callback, "utf-8"));
//		 return map;
//	}
	
	private cn.adwalker.IOSChannel.picker.vo.ParamConfig createParamMap(){
		if(!StringUtil.isEmpty(config.getService_name())){
			return AdPartherMapper.createConfigMap(config, iosClick, config.getService_name());
		}else if(config.map.size()>0){
			return createMap();
		}else{
			return createConfigMap();
		}
	}
	private cn.adwalker.IOSChannel.picker.vo.ParamConfig createConfigMap(){
		 Map<String, String> map = new HashMap<String, String>();
		 //map.put(StringUtil.dealEmpty(config.getAdid_str(), "appid"), iosClick.appid);
		 map.put(StringUtil.dealEmpty(config.getAdid_str(), "appid"), config.getAd_key());
		 map.put(StringUtil.dealEmpty(config.getDeviceid_para(),"deviceid"), iosClick.deviceid);
		 map.put(StringUtil.dealEmpty(config.getUdid(), "mac"), StringUtil.dealNull(iosClick.mac));
		 map.put(StringUtil.dealEmpty(config.getOpenudid(),"OPENUDID"), StringUtil.dealNull(iosClick.getOPENUDID()));
		 map.put(StringUtil.dealEmpty(config.getIdfa(),"IDFA"), StringUtil.dealNull(iosClick.getIDFA()));
		 //map.put(StringUtil.dealEmpty(config.getIdfv(),"IDFV"), StringUtil.dealNull(iosClick.getIDFV()));		 
		 map.put(StringUtil.dealEmpty(config.getSourse_str(),"source"), StringUtil.dealEmpty(config.getSource(),SOURCE));
		 map.put(StringUtil.dealEmpty(config.getClient_ip(), "ip"), StringUtil.dealNull(iosClick.client_ip));
		 String callback=CALL_BACK_URL+"?appid="+iosClick.appid+"&deviceid="+iosClick.deviceid+"&OPENUDID="+iosClick.OPENUDID+"&IDFA="+iosClick.IDFA+"&IDFV="+iosClick.IDFV;
	     map.put(StringUtil.dealEmpty(config.getCallback(),"callback"), StringUtil.encode(callback, "utf-8"));
		 return new cn.adwalker.IOSChannel.picker.vo.ParamConfig(map);
	}
	
	public cn.adwalker.IOSChannel.picker.vo.ParamConfig createMap(){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put(pn("appid"), iosClick.appid);
		 map.put(pn("deviceid"), iosClick.deviceid);
		 map.put(pn("mac"), StringUtil.dealNull(iosClick.mac));
		 map.put(pn("OPENUDID"), StringUtil.dealNull(iosClick.getOPENUDID()));
		 map.put(pn("IDFA"), StringUtil.dealNull(iosClick.getIDFA()));	 
		 map.put(pn("source"), StringUtil.dealEmpty(config.getSource(),SOURCE));
		 map.put(pn("ip"), StringUtil.dealNull(iosClick.client_ip));
		 String callback=CALL_BACK_URL+"?appid="+iosClick.appid+"&deviceid="+iosClick.deviceid+"&OPENUDID="+iosClick.OPENUDID+"&IDFA="+iosClick.IDFA+"&IDFV="+iosClick.IDFV;
	     map.put(pn("callback"), StringUtil.encode(callback, "utf-8"));
	     if(config.map!=null){
		     for(Entry<String, ParamConfig> en:config.map.entrySet()){
			    	ParamConfig c = en.getValue();
			        map.put(c.getParam_name(), c.getParam_value());    	 
			     }
	     }
		 return new cn.adwalker.IOSChannel.picker.vo.ParamConfig(map);
	}
	
	
	private String pn(String name){
		if(config.map!=null){
			ParamConfig p = config.map.get(name);
			if(p!=null){
				String alis =p.getParam_alis();
				config.map.remove(name);
				return alis;
			}
		}
		return name;
	}
}
