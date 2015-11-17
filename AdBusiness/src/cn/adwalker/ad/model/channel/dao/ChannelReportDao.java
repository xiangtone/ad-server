package cn.adwalker.ad.model.channel.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.channel.domain.UserChannels;
import cn.adwalker.ad.model.page.dao.BaseDao;
import cn.adwalker.ad.util.ObjectUtils;

@Repository("channelReportDao")
public class ChannelReportDao extends BaseDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public UserChannels exists(String email) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_channel where LOWER(EMAIL)=?");
		UserChannels userChannels = null;
		List<UserChannels> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<UserChannels>(UserChannels.class), email.trim().toLowerCase());
		if (objects != null && objects.size() > 0) {
			userChannels = (UserChannels) objects.get(0);
		}
		return userChannels;
	}

	public UserChannels getById(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_channel where ID = ?");
		List<UserChannels> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<UserChannels>(UserChannels.class), id);
		UserChannels userChannels = null;
		if (objects != null && objects.size() > 0) {
			userChannels = (UserChannels) objects.get(0);
			return userChannels;
		}
		return null;
	}

	public Integer updateUserPassword(String password, Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update t_channel set ");
		if (ObjectUtils.isNotEmpty(password)) {
			sql.append("PASSWORD=?,");
		}
		sql.append("UPDATE_TIME=?");
		sql.append(" where ID=?");
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), new Object[] { password, new Date(), id });
	}

}