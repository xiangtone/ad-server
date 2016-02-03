package cn.adwalker.ad.model.log.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.log.domain.EScoreManagerLog;

@Repository("adwalkerActionLogDao")
public class AdwalkerActionLogDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	
	/**
	* <p>Description:保存记录</p>
	* @param log
	* @return
	 */
	public int save(EScoreManagerLog log) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_DEV_OPERATION_LOG ");
		sql.append(" (DEV_ID,ROLE,METHOD,PARAMS,MSG,FLAG,CREATE_TIME,LOG_LEVEL) ");
		sql.append(" values (:userId,:role,:method,:params,:msg,:flag,:createTime,:logLevel) ");
		return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(log));
	}

}
