package cn.adwalker.ad.model.platform.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.web.common.vo.RegistVo;

@Repository("mailActivationDao")
public class MailActivationDao {

	private final static int MAIL_NUM = 2;

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Mail_activation getMailCount(RegistVo registVo, String strDate) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_PLATFORM_MAIL_ACTIVATION t where t.mail='" + registVo.getEmail() + "' and t.user_type='" + registVo.getUserType() + "' and t.stat_date ='" + strDate + "'");
		System.out.println(sql);

		Mail_activation vos = null;
		try {
			vos = (Mail_activation) namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql.toString(), new BeanPropertyRowMapper(Mail_activation.class));
		} catch (Exception e) {
			System.out.println("未查询到合适数据");
		}
		return vos;
	}

	public void insertDate(Integer userType, String strDate, String email, Mail_activation vo) {
		StringBuilder sql = new StringBuilder();
		if (vo != null) {
			sql.append(" update T_PLATFORM_MAIL_ACTIVATION t  set t.NUM=?  where t.id=? ");
			int result = namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), vo.getNum() + 1, vo.getId());
		} else {
			sql.append(" insert into T_PLATFORM_MAIL_ACTIVATION(USER_TYPE,STAT_DATE,NUM,MAIL) values (?,?,?,?)");
			int result = namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), userType, strDate, MAIL_NUM, email);
		}
	}

	public Integer getUserStatus(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t.status as STATUS from VIEW_USER t where t.email=?");
		return namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql.toString(), email);
	}

}
