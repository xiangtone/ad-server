package cn.adwalker.model.common.dao.impl;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.common.dao.ISysLogDao;
import cn.adwalker.model.log.domain.SysLog;

/**
 * 
* <p>Title: EScoreManagerLogDaoImpl</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014-3-19
 */
@Repository("eScoreManagerLogDao")
public class SysLogDaoImpl extends BaseDaoImpl<SysLog> implements ISysLogDao {

	public SysLogDaoImpl() {
		setTableName("T_LOG_SYS_MANAGER");
	}

	public int save(SysLog log) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_LOG_SYS_MANAGER ");
		sql.append(" (USER_ID,ROLE_ID,METHOD,PARAMS,LOG_LEVEL,MSG,FLAG,CREATE_TIME,RES_ID) ");
		sql.append(" values (:userId,:roleId,:method,:params,:logLevel,:msg,:flag,:createTime,:res_id ) ");
		return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(log));
	}
}
