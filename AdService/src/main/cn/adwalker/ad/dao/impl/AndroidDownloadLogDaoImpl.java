/**
* <p>Title: CampaginDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-11
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IAndroidDownloadLogDao;
import cn.adwalker.ad.dao.domain.AndroidDownloadLog;
import cn.adwalker.ad.dao.domain.Placement;
import cn.adwalker.ad.picker.statement.Statement;

/**
 * <p>Title: CampaginDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-11
 */
@Repository
public class AndroidDownloadLogDaoImpl extends CommonDao implements IAndroidDownloadLogDao {
	
	
	@Override
	public int insert(AndroidDownloadLog entity) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into t_log_d_android");
		sql.append(" (mac_address,android_id,adid,bundleid,isroot,imei,userid,imsi,phonenum,create_time) ");
		sql.append(" values (:mac_address,:android_id,:adid,:bundleid,:isroot,:imei,:userid,:imsi,:phonenum,:create_time) ");
		return simpleJdbcTemplate.update(sql.toString(),new BeanPropertySqlParameterSource(entity));
	}

	
	/**  
	* <p>Title: getCampagin</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @see com.adwalker.escore.dao.ICampaginDao#getCampagin(java.lang.Long)
	*/
	public Placement getPlacement(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select "
				+ "t.id,"
				+ "t.os,"
				+ "t.campaign_desc,"
				+ "t.slogan,"
				+ "t.icon_url,"
				+ "t.priority,"
				+ "t.category_id,"
				+ "t.terminal_type_str,"
				+ "t.operat_agencies_str,"
				+ "t.phone_brand_str,"
				+ "t.app_type_str,"
				+ "t.time_directional_str,"
				+ "t.area_directional_str,"
				+ "t.sdk_version_str,"
				+ "t.star_level,"
				+ "t.config_id,t.keyword from T_PLACEMENT t where t.ID = ?");
		List<Placement> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<Placement>(Placement.class),new Object[]{id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	public String getUrlParamByConfigId(String confiId){
		Statement stms = stmsFactory.createNativeStatement("SELECT t.sourse_str FROM t_campaign_config t WHERE t.id=?");
		stms.addParam(confiId);
		return simpleJdbcTemplate.findForObject(stms, String.class);
	}
    	
	

}
