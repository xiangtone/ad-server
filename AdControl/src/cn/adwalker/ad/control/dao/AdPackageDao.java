package cn.adwalker.ad.control.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.config.StatusConstant;


@Repository("adPackageDao")
public class AdPackageDao {
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int update(String date) {
		StringBuilder sb = new StringBuilder("INSERT INTO T_LOG_AD_PLACEMENT (CREATE_TIME, PACKAGE_ID, AD_ID,MEDIA_ID,TYPE_ID,STATIC_DATE,ad_type,AD_PRICE,BLANCE_MODE,STATUS,OS)");
		sb.append("SELECT ?,ad.PACKAGE_ID,ad.ID,ad.MEDIA_ID,ad.TYPE_ID,? AS STATIC_DATE,ad.ad_type,ad.BLANCE_PRICE,ad.BLANCE_MODE,ad.STATUS,p.os AS os ");
		sb.append("FROM T_AD ad LEFT JOIN T_PLACEMENT p ON ad.PLACEMENT_ID=p.id  WHERE ad.STATUS!=? AND ad.STATUS!=? AND ad.PACKAGE_ID IS NOT NULL");
		return namedParameterJdbcTemplate.getJdbcOperations().update(sb.toString(), new Object[] { new Date(),date,StatusConstant.AD_STATUS_OVER_DRAFT, StatusConstant.AD_STATUS_OVER});		
	}
	

}
