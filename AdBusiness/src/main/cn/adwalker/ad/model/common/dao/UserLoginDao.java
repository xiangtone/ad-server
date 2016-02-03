package cn.adwalker.ad.model.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.web.common.vo.ViewUserVo;

@Repository("userLoginDao")
public class UserLoginDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ViewUserVo login(String email, String password) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from view_user where LOWER(EMAIL)='");
		sql.append(email.trim().toLowerCase());
		sql.append("' and PASSWORD ='");
		sql.append(password);
		sql.append("'");
		ViewUserVo vo = null;
		List<ViewUserVo> list = namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ViewUserVo>(ViewUserVo.class));
		if (list != null && list.size() > 0) {
			vo = list.get(0);
		}
		return vo;
	}

	public int existEmail(String email) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) from view_user where LOWER(EMAIL)=?");
		return namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql.toString(), email.trim().toLowerCase());
	}

	public int byEmail(String email) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select type from view_user where LOWER(EMAIL)=?");
		return namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql.toString(), email.trim().toLowerCase());
	}

	public int resetPassword(String email, String password, int type) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (type == AppConstant.USER_ADVERTISER) {
			sql.append("update T_ADVERTISER set PASSWORD=? where LOWER(EMAIL)=?");
		} else if (type == AppConstant.USER_DEVELOPER) {
			sql.append("update T_DEVELOPER set PASSWORD=? where LOWER(EMAIL)=?");
		} else {
			return 0;
		}
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), password, email.trim().toLowerCase());
	}

}
