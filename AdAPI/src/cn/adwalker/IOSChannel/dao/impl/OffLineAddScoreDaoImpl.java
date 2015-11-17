package cn.adwalker.IOSChannel.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.dao.IOffLineAddScoreDao;
import cn.adwalker.ad.bean.OffLineData;
/**
 * <p></p>
 * @author jief
 */
@Service("offLineAddScoreDao")
public class OffLineAddScoreDaoImpl implements IOffLineAddScoreDao{
	@Resource
	private SimpleJdbcTemplate simpleJdbcTemplate;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<OffLineData> getDatasByStatus(int status) {
		StringBuffer sql = new StringBuffer();
		sql.append("select tiin.id,tiin.ad_id as ad_id,tcc.ad_key as ad_key,tiin.income_mac,tiin.idfa,tiin.openudid,tiin.status from " 
    +" t_ios_income_number tiin left join t_campaign_config tcc on tcc.id=tiin.ad_id where tiin.status=?");
		System.out.println(sql);
		List<OffLineData> list = this.simpleJdbcTemplate
				.query(sql.toString(), 
				new BeanPropertyRowMapper(OffLineData.class),status);
		return list;
	}

	@Override
	public void update(long id, int status) {
		StringBuffer sql = new StringBuffer();
		sql.append("update t_ios_income_number  set status=?,ACTIVE_DATE=? where id=?");
		Date date=new Date();
		this.simpleJdbcTemplate.update(sql.toString(),status,date,id);
		
	}

}
