/**
* <p>Title: ChannelDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.IOSChannel.dao.IChannelDao;
import cn.adwalker.IOSChannel.vo.AdvertisementChannel;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
import cn.adwalker.IOSChannel.vo.Constant;
import cn.adwalker.ad.spring.JdbcTemplateUtil;

/**
 * <p>Title: ChannelDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
@Repository("channelDao")
public class ChannelDaoImpl implements IChannelDao{
 
	@Resource
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	/**
	*保存数据
	*/
	@Override
	public void saveDate(ChannelRequestResult vo, String[] macAdress) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_ios_action_log (AD_ID, MAC, STAT_DATE, ACTIVITE_DATE, CHANNEL, STATUS, CREATE_TIME, ACTIVITE_STATUS, "
		        +" page_type,ad_key,APPLICATION_KEY,OPENUDID,IDFA,IDFV,os_version,sdkversion,callback_url,client_ip) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		List<Object[]> parameters = new ArrayList<Object[]>();
		Date date =new Date();
		for (String mac : macAdress) {
			 parameters.add(new Object[] {vo.getAdId(),mac,vo.getStatDate(),vo.getActiviteDate(),vo.getSource(),vo.getStatus(),date,vo.getActiviteStatus(),
				vo.getPage_type(),vo.getAd_key(),vo.getApplication_key(),vo.getOpenUDID(),vo.getIdfa(),vo.getIdfv(),vo.getOs_version(),vo.getSdkversion(),vo.getCallback_url(),vo.getClient_ip()
			 });
		}
		this.simpleJdbcTemplate.batchUpdate(sql.toString(), parameters);
	}


	/**
	* <p>Title: getChannel</p>
	* <p>Description:TODO</p>
	* @param source
	* @return
	* @return AdvertisementChannel
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public AdvertisementChannel getChannel(String source) {
		StringBuffer sqlnew = new StringBuffer();
		sqlnew.append("select * from T_CHANNEL_CONFIG t where CHANNEL='"+source+"'");
		//System.out.println(sqlnew);
		List<AdvertisementChannel> list = this.simpleJdbcTemplate.query(sqlnew.toString(), 
				new BeanPropertyRowMapper(AdvertisementChannel.class) );
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	
	/**
	* <p>Title: getIOS</p>
	* <p>Description:TODO</p>
	* @param appkey
	* @return
	* @return cn.adwalker.ios.service.Advertisement_IOS
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Advertisement_IOS getIOS(String adkey) {
		StringBuffer sqlnew = new StringBuffer();
		sqlnew.append("select * from T_CAMPAIGN_CONFIG t where AD_KEY='"+adkey+"'");
		List<Advertisement_IOS> list = this.simpleJdbcTemplate.query(sqlnew.toString(), 
				new BeanPropertyRowMapper(Advertisement_IOS.class));
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}


	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<AdvertisementChannel> getChannelList() {
		StringBuffer sqlnew = new StringBuffer();
		sqlnew.append("select * from T_CHANNEL_CONFIG t ");
		List<AdvertisementChannel> list = this.simpleJdbcTemplate.query(sqlnew.toString(), 
				new BeanPropertyRowMapper(AdvertisementChannel.class) );
		return list;
	}


	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Advertisement_IOS> getISOList() {
		StringBuffer sqlnew = new StringBuffer();
		sqlnew.append("select * from T_CAMPAIGN_CONFIG t ");
		List<Advertisement_IOS> list = this.simpleJdbcTemplate.query(sqlnew.toString(), 
				new BeanPropertyRowMapper(Advertisement_IOS.class) );
		return list;
	}
	
	/**  (non-Javadoc)
	* <p>Title: saveDate</p>
	* <p>Description:TODO</p>
	* @param list
	* @see cn.adwalker.IOSChannel.dao.IChannelDao#saveDate(java.util.List)
	*/
	@Override
	public void saveDate(List<ChannelRequestResult> list) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_ios_action_log (ID, AD_ID, MAC, STAT_DATE, ACTIVITE_DATE, CHANNEL, STATUS, CREATE_TIME, ACTIVITE_STATUS,APPLICATION_KEY,AD_KEY,PAGE_TYPE) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?)");
		List<Object[]> parameters = new ArrayList<Object[]>();
		Date date =new Date();
		for (ChannelRequestResult vo:list) {
			long rowID = JdbcTemplateUtil.getId("ios_action_log", simpleJdbcTemplate);
			 parameters.add(new Object[] {rowID,vo.getAdId(),vo.getDeviceId(),vo.getStatDate(),vo.getActiviteDate(),vo.getSource(),0,date,0,vo.getApplication_key(),vo.getAd_key(),vo.getPage_type()
			 });

		}
		this.simpleJdbcTemplate.batchUpdate(sql.toString(), parameters);
	}

@SuppressWarnings({ "unchecked", "rawtypes" })
@Override
public ChannelRequestResult getClickLog(ChannelRequestResult callback,
		Advertisement_IOS adios) {
	StringBuffer sqlnew = new StringBuffer();
	if(StringUtils.isNotBlank(callback.getDeviceId())){
	sqlnew.append("select id,ad_id,mac,stat_date,activite_date,channel,status,").append(
			" create_time,activite_status,stat_enddate,application_key,page_type,ad_key,").append(
			" sdkversion,udid,client_ip,openudid,idfa,idfv,os_version,callback_url  ").append(
			" from t_ios_action_log t where mac= ?  and AD_ID =? order by id desc");
	List<ChannelRequestResult> list = this.simpleJdbcTemplate.query(sqlnew.toString(), 
			new BeanPropertyRowMapper(ChannelRequestResult.class),new Object[]{callback.getDeviceId(),adios.getId()});
	 if(list.size() > 0){
		return list.get(0);
	 }
	}else{
		List<Object> olist=new ArrayList<Object>();
		sqlnew.append("select  id,ad_id,mac,stat_date,activite_date,channel,status,")
		.append(" create_time,activite_status,stat_enddate,application_key,page_type,ad_key,")
		.append(" sdkversion,udid,client_ip,openudid,idfa,idfv,os_version,callback_url ")
		.append(" from t_ios_action_log t where 1=1 ");
		if(StringUtils.isNotBlank(adios.getOpenudid())){
			sqlnew.append(" and OPENUDID= ? ");
			olist.add(callback.getOpenUDID());
		}
		if(StringUtils.isNotBlank(adios.getIdfa())){
			sqlnew.append(" and IDFA= ? ");
			olist.add(callback.getIdfa());
		}
		if(StringUtils.isNotBlank(adios.getIdfv())){
			sqlnew.append(" and IDFV= ? ");
			olist.add(callback.getIdfv());
		}
		sqlnew.append(" and AD_ID =? order by id desc");
		if(olist.size()>0){
			Object[]objs=new Object[olist.size()+1];
			for(int i=0;i<olist.size();i++){
				objs[i]=olist.get(i);
			}
			objs[olist.size()]=adios.getId();
			List<ChannelRequestResult> list = this.simpleJdbcTemplate.query(sqlnew.toString(), 
					new BeanPropertyRowMapper(ChannelRequestResult.class),objs );
		   if(null!=list && list.size() > 0){
			  for(ChannelRequestResult crr :list){
				  //优先易积分的确认
				  if("zijiren".equals(crr.getChannel())){
					return crr;
				  }
			   }
			 return list.get(0);
			}
		}
	}
	return null;
}


@Override
public void saveActiveLogIfNotExist(ChannelRequestResult actionLog) {
	StringBuilder sql=new StringBuilder();
	long acDate=System.currentTimeMillis()/1000;
	sql.append(" insert  into t_ios_activite_log (ad_id,mac,activite_date,channel, status, create_time, activite_status,application_key,")
	.append(" page_type, ad_key,sdkversion, client_ip,openudid, idfa, idfv,os_version, action_id )")
	.append(" select ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? from dual where NOT EXISTS ("
             + "SELECT action_id FROM t_ios_activite_log WHERE action_id = ?"
             + ") LIMIT 1");
	//修改了一个bug 2014-01-16 13:55 mac 值插入为空 jief
	this.simpleJdbcTemplate.update(sql.toString(), new Object[]{actionLog.getAdId(),actionLog.getMac(),acDate
		,actionLog.getChannel(),actionLog.getStatus(),actionLog.getCreateTime(),Constant.IOS_ACTION_LOG_ACTIVITE_STATUS_CONFIRMED,
		actionLog.getApplication_key(),actionLog.getPage_type(),actionLog.getAd_key(),actionLog.getSdkversion(),actionLog.getClient_ip(),
		actionLog.getOpenUDID(),actionLog.getIdfa(),actionLog.getIdfv(),actionLog.getOs_version(),actionLog.getId(),actionLog.getId()
	});
}


@Override
public List<Long> getAdsSchemeBySchemeId(String schemeId) {
	StringBuffer sqlnew = new StringBuffer();
	sqlnew.append(" select t.ad_id from t_ad_send_config t where t.scheme_id=? ");
	List<Map<String,Object>> adids= this.simpleJdbcTemplate.queryForList(sqlnew.toString(), schemeId);
	List<Long> ids=new ArrayList<Long>();
	if(adids!=null && adids.size()>0){
		for(Map<String,Object> m:adids){
			ids.add((Long)m.get("ad_id"));
		}
	}
	return ids;
}


@Override
public List<Long> getmiediasSchemeBySchemeId(String schemeId) {
	StringBuffer sqlnew = new StringBuffer();
	sqlnew.append(" select t.appid from t_mediacallback_config t where t.scheme_id=? ");
	List<Map<String,Object>> appids= this.simpleJdbcTemplate.queryForList(sqlnew.toString(), schemeId);
	List<Long> ids=new ArrayList<Long>();
	if(appids!=null && appids.size()>0){
		for(Map<String,Object> m:appids){
			ids.add((Long)m.get("appid"));
		}
	}
	return ids;
}
  
}
