package cn.adwalker.IOSChannel.picker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.IOSChannel.picker.bean.AdAreaRate;
import cn.adwalker.IOSChannel.picker.bean.ApplicateAreaNum;
import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.bean.IosActionLog;
import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.bean.ParamConfig;
import cn.adwalker.IOSChannel.picker.bean.UserInfo;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.statement.Statement;
import cn.adwalker.IOSChannel.picker.util.DateUtil;
import cn.adwalker.IOSChannel.picker.util.NumberUtil;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.strategy.AreaScheme;
import cn.adwalker.IOSChannel.strategy.AreaSchemeInfo;
import cn.adwalker.IOSChannel.strategy.admaster.AdSendConfig;
import cn.adwalker.IOSChannel.strategy.media.MediaCallbackConfig;
import cn.adwalker.ad.bean.Advertise;
import cn.adwalker.ad.bean.DevApp;
import cn.adwalker.ad.spring.JdbcTemplateUtil;

@Repository("supportDao")
public class SupportDao extends CommonDao {
	
	public void saveIosActivateLog(IosActivateLog a){
		Statement stms = stmsFactory.createNativeStatement("insert into t_ios_activite_log (ad_id,mac,activite_date,channel, status, create_time, activite_status,application_key,");
		          stms.append("page_type, ad_key,sdkversion, client_ip,openudid, idfa, idfv,os_version, action_id,area_code,price,ssid,bssid,phoneName,latitude,longitude,in_price ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	              stms.addParam(a.getAd_id(),a.getMac(),a.getActivite_date(),a.getChannel(),a.getStatus(),a.getCreate_time(),a.getActivite_status(),a.getApplication_key());
	              stms.addParam(a.getPage_type(),a.getAd_key(),a.getSdkversion(),a.getClient_ip(),a.getOpenudid(),a.getIdfa(),a.getIdfv(),a.getOs_version(),a.getAction_id(),a.getArea_code(),a.getPrice(),a.getSsid(),a.getBssid(),a.getPhoneName(),a.getLatitude(),a.getLongitude(),a.getIn_price());
		simpleJdbcTemplate.update(stms);
	}
	
	public Integer findCountByOpenUdidAndAdId(String adId,String openudid){
		Statement stms = stmsFactory.createNativeStatement("SELECT t.* FROM t_ios_activite_log t WHERE t.ad_id=? AND t.openudid=?");
		          stms.addParam(adId);
		          stms.addParam(openudid);
		return simpleJdbcTemplate.queryList(stms, IosActivateLog.class).size();
	}
	/**
	 * 根据openudid和adid的激活数量 add by jief
	 * @param adId
	 * @param openudid
	 * @return
	 */
	public Integer getCountByOpenUdidAndAdId(String adId,String openudid){
		String sql = "SELECT count(1) FROM t_ios_activite_log t WHERE t.ad_id=? AND t.openudid=?" ;
        return simpleJdbcTemplate.queryForInt(sql, new Object[]{adId,openudid});
	}
	
	/**
	 * 
	* <p>Title: getAdvertise</p>
	* <p>Description:查询单个广告</p>
	* @param adId
	* @return
	* @author cuidd
	* @date 2014年10月22日
	* @return Advertise
	* @version 1.0
	 */
	public Advertise getAdvertise(String adId){
		Statement stms = stmsFactory.createNativeStatement("SELECT 	id,blance_price,confirm_type from t_ad t where t.id=?  limit 1 ");
        stms.addParam(adId);    	          
        return simpleJdbcTemplate.findObject(stms, Advertise.class);
	}
	
	
	public CampaignConfig findCampaignConfigBybundleid(String bundleid){
		Statement stms = stmsFactory.createNativeStatement("SELECT 	* from t_campaign_config t where t.bundleid=?  limit 1 ");
        stms.addParam(bundleid);    	          
        return simpleJdbcTemplate.findObject(stms, CampaignConfig.class);
		
	}
	
	
	
	
	
	public void battchAddClick(List<IosClick> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_ios_action_log (AD_ID, MAC, STAT_DATE, ACTIVITE_DATE, CHANNEL, STATUS, CREATE_TIME, ACTIVITE_STATUS, "
		        +" page_type,ad_key,APPLICATION_KEY,OPENUDID,IDFA,IDFV,os_version,sdkversion,callback_url,client_ip,ssid,bssid,phoneName,latitude,longitude,in_price) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE click_time=click_time+1");
		List<Object[]> parameters = new ArrayList<Object[]>();
		for(IosClick c:list){
			parameters.add(new Object[]{c.appid,c.deviceid,NumberUtil.getInt(c.eventtime, 0),null,c.source,BaseAttribute.IOS_ACTION_LOG_STATUS_0,new Date(),
					BaseAttribute.IOS_ACTION_LOG_STATUS_0,c.page_type,c.ad_key,c.app_key,c.OPENUDID,c.IDFA,c.IDFV,c.os,c.sdkVersion,c.callback,c.client_ip,c.ssid,c.bssid,c.phoneName,c.latitude,c.longitude,c.getIn_price()});
		}
	    this.simpleJdbcTemplate.batchUpdate(sql.toString(), parameters);
	}
	
	public List<ParamConfig> queryParamConfig(String configType,String configId){
	   	Statement stms = stmsFactory.createNativeStatement("SELECT * FROM t_param_config t WHERE t.config_type=? AND t.config_id=?");
	   	          stms.addParam(configType);
	   	          stms.addParam(configId);
	   	return simpleJdbcTemplate.queryList(stms, ParamConfig.class);
	}
	
	
	public CampaignConfig findCampaignConfig(String key){
		Statement stms = stmsFactory.createNativeStatement("select t.id,t.ad_key,t.ad_name,t.adv_price,t.create_time,t.url,t.send_type,t.udid,t.openudid,t.idfa,t.idfv,t.source,t.eventtime_para  ");
		          stms.append(",t.adid_str,t.deviceid_para,t.client_ip,t.callback,t.sourse_str,t.service_name,t.pass_rate");
		          stms.append("from T_CAMPAIGN_CONFIG t where t.ad_key=?");   
		       stms.addParam(key);
		       
	    return simpleJdbcTemplate.findObject(stms, CampaignConfig.class);
	}
	public Integer findActionCount(Date date){
		Statement stms = stmsFactory.createNativeStatement("SELECT COUNT(1) FROM t_ios_action_log t WHERE t.create_time>=?");
		          stms.addParam(date);
		return simpleJdbcTemplate.findCount(stms);
	}
	
	
	
	public ChannelConfig findChannelConfig(String channel){
		Statement stms =stmsFactory.createNativeStatement("SELECT * FROM t_channel_config t WHERE t.channel=?");
		          stms.addParam(channel);
		return simpleJdbcTemplate.findObject(stms, ChannelConfig.class);
	}
	
	
	
    public void saveIosActionLog(IosActionLog action){
    	 long rowID = JdbcTemplateUtil.getId("ios_action_log", simpleJdbcTemplate);
    	 Statement stms = stmsFactory.createNativeStatement(" insert into t_ios_action_log (ID, AD_ID, MAC, STAT_DATE, CHANNEL, STATUS, CREATE_TIME, ACTIVITE_STATUS,OPENUDID,IDFA,IDFV) " +
			"  values(?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE");
           stms.addParam(rowID);
           stms.addParam(action.getAdId());
           stms.addParam(action.getMac());
           stms.addParam(action.getStat_date());
           stms.addParam(action.getChannel());
           stms.addParam(action.getStatus());
           stms.addParam(action.getCreateTime());
           stms.addParam(action.getStatus());
           stms.addParam(action.getOpenudid());
           stms.addParam(action.getIdfa());
           stms.addParam(action.getIdfv());     
           System.out.println(action.getAdId());
           simpleJdbcTemplate.update(stms);
	}
    
    public IosActionLog findIosActionLog(String mac,String appid){
    	Statement stms = stmsFactory.createNativeStatement("SELECT 	id, ad_id AS adId,mac, stat_date, activite_date, channel, STATUS, create_time createTime, activite_status, application_key, page_type, ad_key, client_ip, openudid, idfa, idfv, os_version, callback_url,ssid,bssid,phoneName,latitude,longitude,in_price from t_ios_action_log t where t.ad_id=? and t.mac=? limit 1 ");
    	          stms.addParam(StringUtil.dealNull(appid).toUpperCase());
    	          stms.addParam(mac);    	          
    	return simpleJdbcTemplate.findObject(stms, IosActionLog.class);
    }
    public IosActionLog findIosActionLogIdfA(String idfa,String appid){
    	Statement stms = stmsFactory.createNativeStatement("select * from t_ios_action_log t where t.ad_id=? and upper(t.idfa)=? and rownum=1");
    	          if(!StringUtil.isEmpty(idfa)){
    	        	  idfa = idfa.toUpperCase();
    	          }
    	          stms.addParam(appid);
    	          stms.addParam(idfa);
    	return simpleJdbcTemplate.findObject(stms, IosActionLog.class);
    }
    public boolean existActivateLog(String appId,String mac){
    	Statement stms = stmsFactory.createNativeStatement("select id from t_ios_activite_log t where t.ad_id=? and t.mac=?");
    	          stms.addParam(appId,mac);
    	          return simpleJdbcTemplate.queryList(stms, IosActivateLog.class).iterator().hasNext();
    }
    
    
    public Integer updateActiviceLog(Integer iosActionId){
    	Statement stms = stmsFactory.createNativeStatement("update t_ios_action_log t set t.activite_status=?,t.activite_date=? where t.id=?");
    	          stms.addParam(BaseAttribute.IOS_ACTION_LOG_STATUS_1);
    	          stms.addParam(new Date().getTime());
    	          stms.addParam(iosActionId);
    	return simpleJdbcTemplate.update(stms);
    }
    public Integer updateActionLogIdfaToMac(Integer id){
    	Statement stms = stmsFactory.createNativeStatement("update t_ios_action_log t set t.mac=t.openudid where t.id=?");
    	          stms.addParam(id);
    	return simpleJdbcTemplate.update(stms);
    }
    
    public ApplicateAreaNum findApplicateAreaNum(String areaCode){
    	Statement stms = stmsFactory.createNativeStatement("SELECT t.* FROM t_application_area_num t WHERE t.area_code=?");
    	          stms.addParam(areaCode);
    	List<ApplicateAreaNum>  list =  simpleJdbcTemplate.queryList(stms, ApplicateAreaNum.class);
    	return list.iterator().hasNext()?list.iterator().next():null;
    }
    public AdAreaRate findAdAreaRate(Integer adId,String areaCode){
    	Statement stms = stmsFactory.createNativeStatement("SELECT * FROM t_ad_area_rate t WHERE t.ad_id=? AND t.area_code=?");
    	          stms.addParam(adId,areaCode);
    	          List<AdAreaRate> list = simpleJdbcTemplate.queryList(stms, AdAreaRate.class);    	          
    	return list.iterator().hasNext()?list.iterator().next():null;
    }
    
    
    public Integer findActivateCountByAreaCode(String areaCode,String appId){
    	Statement stms = stmsFactory.createNativeStatement("SELECT t.* FROM t_ios_activite_log t WHERE t.area_code=? AND t.application_key=? AND DATE_FORMAT(t.create_time,'%Y-%m-%d')=? and send=1");
    	          stms.addParam(areaCode,StringUtil.dealNull(appId),DateUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
    	return simpleJdbcTemplate.queryList(stms, IosActivateLog.class).size();
    }
    /**
     * 根据条件获取激活数 add by jief
     * @param areaCode
     * @param appId
     * @return
     */
    public Integer getActivateCountByAreaCode(String areaCode,String appId){
    	String sql = "SELECT count(1) FROM t_ios_activite_log t WHERE t.area_code=? AND t.application_key=? AND DATE_FORMAT(t.create_time,'%Y-%m-%d')=? and send=1" ;
    	          Object[] parms=new Object[]{areaCode,StringUtil.dealNull(appId),DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")};
    	return simpleJdbcTemplate.queryForInt(sql, parms);
    }
    
    public Integer findActivateCountByIpduan(String ipduan,String adId){
    	Statement stms = stmsFactory.createNativeStatement("SELECT t.ad_id FROM t_ios_activite_log t WHERE t.create_time>=? AND t.ad_id=?");
           stms.addParam(DateUtil.get00HourDate(new Date()),adId);
           stms.appendLike("^", "and", "t.client_ip", ipduan);
           return simpleJdbcTemplate.queryList(stms, IosActivateLog.class).size();
    }
    /**
     * add by jief2014-06-24
     * @param ipduan
     * @param adId
     * @return
     */
    public Integer getActivateCountByIpduan(String ipduan,String adId){
    	Statement stms = stmsFactory.createNativeStatement("SELECT count(1) FROM t_ios_activite_log t WHERE t.create_time>=? AND t.ad_id=?");
    	stms.addParam(DateUtil.get00HourDate(new Date()),adId);
    	stms.appendLike("^", "and", "t.client_ip", ipduan);
    	return simpleJdbcTemplate.queryForInt(stms.toString(),stms.getArrayParams());
    }
    public Integer findActivateCountByIp(String ip,String adId){
    	Statement stms = stmsFactory.createNativeStatement("SELECT t.ad_id FROM t_ios_activite_log t WHERE t.create_time>=? AND t.ad_id=?");
           stms.addParam(DateUtil.get00HourDate(new Date()),adId);
           stms.append("and","t.client_ip","=",ip);       
           return simpleJdbcTemplate.queryList(stms, IosActivateLog.class).size();
    }
    /**
     * changed by jief 2014-06-24 
     * @param ip
     * @param adId
     * @return
     */
    public Integer getActivateCountByIp(String ip,String adId){
    	Statement stms = stmsFactory.createNativeStatement("SELECT count(1) FROM t_ios_activite_log t WHERE t.create_time>=? AND t.ad_id=?");
    	stms.addParam(DateUtil.get00HourDate(new Date()),adId);
    	stms.append("and","t.client_ip","=",ip);       
    	return simpleJdbcTemplate.queryForInt(stms.toString(), stms.getArrayParams());
    }
    
    public Integer updateActivateSend(String adId,String mac,Integer send){
    	Statement stms = stmsFactory.createNativeStatement("UPDATE t_ios_activite_log t SET t.send=? WHERE t.ad_id=? AND t.mac=?");
    	          stms.addParam(send,adId,mac);
    	return simpleJdbcTemplate.update(stms);
    }
    
    
    public UserInfo loadUserInfo(String uuid){
    	Statement stms = stmsFactory.createNativeStatement("select id,uuid,imei,tel_num,tel_model,net_env,area_code as areaCode,operator,os,brand,screen_width,screen_heigh,pda_type,score,create_time,imsi,OPENUDID,IDFA,JAILBROKEN,IDFV from "+tableName(uuid)+" t where t.uuid = ?");
        stms.addParam(uuid);
        List<UserInfo> list = simpleJdbcTemplate.queryList(stms, UserInfo.class);
        return list.iterator().hasNext()?list.iterator().next():null;
    }
    //查询一个小时以前的点击数.
    @SuppressWarnings("unchecked")
	public List<String[]> queryActionLogByHourDate(Date hourAgo){
    	Statement stms = stmsFactory.createNativeStatement("SELECT t.ad_id, COUNT(1) FROM t_ios_action_log t WHERE t.create_time>=? GROUP BY t.ad_id");
    	          stms.addParam(hourAgo);
    	          RowMapper<String[]> rm = new RowMapper<String[]>() {
  					@Override
  					public String[] mapRow(ResultSet rs, int arg1) throws SQLException {
  					    String[] strs = new String[2];
  					    strs[0] = rs.getString(1);
  					    strs[1] = rs.getString(2);
  						return strs; 
  					}
  				};
  				System.out.println(stms.toString()+"  ==================");
    	return (List<String[]>)simpleJdbcTemplate.queryListForObject(stms, rm);
    }
    //查询一个小时以前的激活数据.
    public Integer queryActivateLogByHourDate(Date hourAgo,String adId){
    	Statement stms = stmsFactory.createNativeStatement("SELECT COUNT(1) FROM t_ios_activite_log t WHERE t.create_time>=? and t.ad_id=?");
    	          stms.addParam(hourAgo);
    	          stms.addParam(adId);
    	          Object obj = simpleJdbcTemplate.findForObject(stms, Object.class);
    	return NumberUtil.getInteger(obj, 0);
    }
    
    private  String tableName(String uuid){
		if(null!=uuid){
			String lastOne = String.valueOf(uuid.charAt(uuid.length()-1)).toLowerCase();
			if(lastOne.matches("(?i)[0|1|2|3|4|5|6|7|8|9|a|b|c|d|e|f]")){
				return "t_user_"+lastOne;
			}else{
				return "t_user__0";
			}
		}
		return null;
	}
    /**
     * 根据appid获取媒体防作弊配置
     * @param appid
     * @return
     */
    public MediaCallbackConfig getMediaConfigById(String appid){
    	Statement stms = stmsFactory.createNativeStatement("SELECT 	t.appid,t.detain_rate,t.scheme_id,t.ipsegment_times,t.ip_times,t.ca_time_ratio  from t_mediacallback_config t where t.appid=? limit 1 ");
        stms.addParam(appid);    	      
        MediaCallbackConfig config=simpleJdbcTemplate.findObject(stms, MediaCallbackConfig.class);
        if(null!=config){
        	 stms = stmsFactory.createNativeStatement("SELECT t.id,t.scheme_id,t.area,t.config from t_prevent_cheat_scheme_info t where t.scheme_id=?");
        	 stms.addParam(config.getScheme_id());
        	 List<AreaSchemeInfo> schemeInfo=simpleJdbcTemplate.queryList(stms, AreaSchemeInfo.class);
        	 config.setSchemeInfo(schemeInfo);
        }
        return config;
    }
    /**
     * 
     * @param adid
     * @return
     */
    public AdSendConfig getAdSendConfigById(String adid){
    	 Statement stms = stmsFactory.createNativeStatement("SELECT t.ad_id,t.scheme_id,t.ad_ipsegment_num,t.ad_ip_num,t.ad_bssid_num,t.ad_latlon_num from t_ad_send_config t where t.ad_id=?");
    	 stms.addParam(adid);
    	 AdSendConfig config=simpleJdbcTemplate.findObject(stms, AdSendConfig.class);
    	 if(null!=config && config.getScheme_id()!=null){
    		 stms = stmsFactory.createNativeStatement("SELECT t.id,t.scheme_id,t.area,t.config from t_prevent_cheat_scheme_info t where t.scheme_id=?");
        	 stms.addParam(config.getScheme_id());
        	 List<AreaSchemeInfo> schemeInfo=simpleJdbcTemplate.queryList(stms, AreaSchemeInfo.class);
        	 config.setSchemeInfo(schemeInfo);
    	 }
    	return config;
    }
    /**
     * 根据类型获取区域配置方案
     * @param schemetype
     * @return
     */
    public AreaScheme getAreaSchemeByType(String schemetype){
    	 Statement stms = stmsFactory.createNativeStatement("SELECT t.id,t.name,t.is_default,t.adormedia from t_prevent_cheat_scheme t where t.is_default=? and t.adormedia=?");
    	 stms.addParam(1,schemetype);
    	 AreaScheme config=simpleJdbcTemplate.findObject(stms, AreaScheme.class);
    	 if(null!=config ){
    		 stms = stmsFactory.createNativeStatement("SELECT t.id,t.scheme_id,t.area,t.config from t_prevent_cheat_scheme_info t where t.scheme_id=?");
        	 stms.addParam(config.getId());
        	 List<AreaSchemeInfo> schemeInfo=simpleJdbcTemplate.queryList(stms, AreaSchemeInfo.class);
        	 config.setSchemeInfo(schemeInfo);
    	 }
    	return config;
    	
    }
    
    /**
     * 获取当前在线的ios积分墙广告数目
     * @return
     */
    public Integer getiosAdNum(){
    	Statement stms = stmsFactory.createNativeStatement("SELECT count(1) FROM  t_ad ad,t_placement pt  WHERE ad.placement_id=pt.id AND pt.os='ios' AND ad.status=1 AND ad.type_id=0 AND ad.media_id=0");
    	return simpleJdbcTemplate.queryForInt(stms.toString(), stms.getArrayParams());
    }
    /**
     * 获取某个区域某个广告的激活数目
     * @return
     */
    public Integer getAdAreaActNum(String area,String adkey){
    	Statement stms = stmsFactory.createNativeStatement("SELECT count(1) FROM t_ios_activite_log t WHERE t.create_time >=? AND t.ad_id=? ");
        stms.addParam(DateUtil.get00HourDate(new Date()),adkey);
        stms.append("and","t.area_code","=",area);
        return simpleJdbcTemplate.queryForInt(stms.toString(), stms.getArrayParams());
    }
    /**
     * 获取同一个bssid下某广告的激活数
     * @param bssid
     * @param adkey
     * @return
     */
    public Integer getAdBssidActNum(String bssid,String adkey){
    	Statement stms = stmsFactory.createNativeStatement("SELECT count(1) FROM t_ios_activite_log t WHERE t.create_time >=? AND t.ad_id=? ");
        stms.addParam(DateUtil.get00HourDate(new Date()),adkey);
        stms.append("and","t.bssid","=",bssid);
        return simpleJdbcTemplate.queryForInt(stms.toString(), stms.getArrayParams());
    }
    /**
     * 获取同一经纬度下
     * @param lat
     * @param lon
     * @param adkey
     * @return
     */
    public Integer getAdlatlonNum(String lat,String lon,String adkey){
    	Statement stms = stmsFactory.createNativeStatement("SELECT count(1) FROM t_ios_activite_log t WHERE t.create_time >=? AND t.ad_id=? AND t.latitude=? AND t.longitude=?");
        stms.addParam(DateUtil.get00HourDate(new Date()),adkey,lat,lon);
        return simpleJdbcTemplate.queryForInt(stms.toString(), stms.getArrayParams());
    }
    /**
     * 获取app
     * @param appId
     * @return
     */
	public DevApp getApplication(Long appId) {
		Statement stms = stmsFactory.createNativeStatement("SELECT t.*,a.response_url FROM t_application t LEFT JOIN t_cooperation_app_rel a ON t.id=a.app_id WHERE t.id=?");
		          stms.addParam(appId);
		          return simpleJdbcTemplate.findObject(stms, DevApp.class);
	}
	
    /**
     * 根据appid获取某ip当前内激活的广告数
     * changed by jief 2014-07-09 
     * @param ip
     * @param appkey
     * @return
     */
    public Integer getAppActivateCountByIp(String ip,String appkey){
    	Statement stms = stmsFactory.createNativeStatement("SELECT count(1) FROM t_ios_activite_log t WHERE t.create_time>=? AND t.application_key=?");
    	stms.addParam(DateUtil.get00HourDate(new Date()),appkey);
    	stms.append("and","t.client_ip","=",ip);       
    	return simpleJdbcTemplate.queryForInt(stms.toString(), stms.getArrayParams());
    }
    /**
     * 根据appid获取某ip段内当前内激活的广告数
     * changed by jief 2014-07-09 
     * @param ipduan
     * @param appkey
     * @return
     */
    public Integer getAppActivateCountByIpduan(String ipduan,String appkey){
    	Statement stms = stmsFactory.createNativeStatement("SELECT count(1) FROM t_ios_activite_log t WHERE t.create_time>=? AND t.application_key=?");
    	stms.addParam(DateUtil.get00HourDate(new Date()),appkey);
    	stms.appendLike("^", "and", "t.client_ip", ipduan);  
    	return simpleJdbcTemplate.queryForInt(stms.toString(), stms.getArrayParams());
    }
    
}
