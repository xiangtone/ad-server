package cn.adwalker.ad.model.developer.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.common.domain.ConfigPushDelay;
import cn.adwalker.ad.model.common.domain.RegDev;
import cn.adwalker.ad.model.developer.domain.DevInfo;
import cn.adwalker.ad.model.developer.domain.Developer;
import cn.adwalker.ad.util.ObjectUtils;

/**
 * 开发者实现类
 */
@Repository("developerDao")
public class DeveloperDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Developer exists(String email) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_DEVELOPER where LOWER(EMAIL)=?");
		Developer userDeveloper = null;
		List<Developer> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<Developer>(Developer.class), email.trim().toLowerCase());
		if (objects != null && objects.size() > 0) {
			userDeveloper = (Developer) objects.get(0);
		}
		return userDeveloper;
	}

	public Developer getById(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_DEVELOPER where ID = ?");
		List<Developer> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<Developer>(Developer.class), id);
		Developer userDeveloper = null;
		if (objects != null && objects.size() > 0) {
			userDeveloper = (Developer) objects.get(0);
			return userDeveloper;
		}
		return null;
	}

	public Integer insert(RegDev regDev) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_DEVELOPER(");
		sql.append("EMAIL,");
		sql.append("PASSWORD,");
		sql.append("STATUS,");
		sql.append("resource_code,");
		sql.append("CREATE_TIME");
		sql.append(")");
		sql.append(" values (");
		sql.append(":email,");
		sql.append(":password,");
		sql.append(":status,");
		sql.append("'ADWALKER',");
		sql.append(":createTime");
		sql.append(")");
		return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(regDev));
	}

	public Integer update(Developer userDeveloper) throws Exception {
		userDeveloper.setUpdateTime(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("update T_DEVELOPER set ");
		if (ObjectUtils.isNotEmpty(userDeveloper.getEmail())) {
			sql.append("EMAIL=:email,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getPassword())) {
			sql.append("PASSWORD=:password,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getCompanyName())) {
			sql.append("COMPANY_NAME=:companyName,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getCompanyAddress())) {
			sql.append("COMPANY_ADDRESS=:companyAddress,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getMailingAddress())) {
			sql.append("MAILING_ADDRESS=:mailingAddress,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getPostCode())) {
			sql.append("POST_CODE=:postCode,");
		}
		if (userDeveloper.getQq() != null) {
			sql.append("QQ=:qq,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getMsn())) {
			sql.append("MSN=:msn,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getRealName())) {
			sql.append("REAL_NAME=:realName,");
		}
		if (userDeveloper.getMobilePhone() != null) {
			sql.append("MOBILE_PHONE=:mobilePhone,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getFixedPhone())) {
			sql.append("FIXED_PHONE=:fixedPhone,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getWebsiteUrl())) {
			sql.append("WEBSITE_URL=:websiteUrl,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getDescription())) {
			sql.append("DESCRIPTION=:description,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getCardType())) {
			sql.append("CARD_TYPE=:cardType,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getCardCode())) {
			sql.append("CARD_CODE=:cardCode,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getCardUrl())) {
			sql.append("CARD_URL=:cardUrl,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getTotalMoney())) {
			sql.append("TOTAL_MONEY=:totalMoney,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getStatus())) {
			sql.append("STATUS=:status,");
		}
		if (ObjectUtils.isNotEmpty(userDeveloper.getLastUpdateMan())) {
			sql.append("LAST_UPDATE_MAN=:lastUpdateMan,");
		}
		sql.append("UPDATE_TIME= :updateTime");
		sql.append(" where ID=:id");
		return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(userDeveloper));
	}

	public Integer updateUserInfo(DevInfo userInfo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update T_DEVELOPER set ");
		if (ObjectUtils.isNotEmpty(userInfo.getCompanyName())) {
			sql.append("COMPANY_NAME=:companyName,");
		}
		if (ObjectUtils.isNotEmpty(userInfo.getCompanyAddress())) {
			sql.append("COMPANY_ADDRESS=:companyAddress,");
		}
		if (userInfo.getQq() != null) {
			sql.append("QQ=:qq,");
		}
		if (ObjectUtils.isNotEmpty(userInfo.getRealName())) {
			sql.append("REAL_NAME=:realName,");
		}
		if (ObjectUtils.isNotEmpty(userInfo.getStatus())) {
			sql.append("STATUS=:status,");
		}
		if (userInfo.getMobilePhone() != null) {
			sql.append("MOBILE_PHONE=:mobilePhone,");
		}
		if (userInfo.getType() != null) {
			sql.append("TYPE=:type,");
		}
		sql.append("UPDATE_TIME= :updateTime");
		sql.append(" where ID=:id");
		return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(userInfo));
	}

	public Integer updatePassword(String password, Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update T_DEVELOPER set ");
		if (ObjectUtils.isNotEmpty(password)) {
			sql.append("PASSWORD=?,");
		}
		sql.append("UPDATE_TIME=?");
		sql.append(" where ID=?");
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), new Object[] { password, new Date(), id });
	}

	public Double getTax(Double preTax) {
		Double tax = 0d;
		Double taxRate = 1d;
		String sql = ("select ID as id,CONFIG_TYPE as configType,CONFIG_VALUE as configValue from T_SYS_CONFIG  where CONFIG_TYPE=" + "'FINANCETAX'");
		List<ConfigPushDelay> list = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<ConfigPushDelay>(ConfigPushDelay.class));
			taxRate = Double.parseDouble(list.get(0).getConfigValue());
 			taxRate = taxRate / 100;
			if (preTax <= 800) {
				tax = 0d;
			} else if (preTax > 800 ) {
				tax = (preTax - 800) *taxRate;
			} 
		
		return tax;
	}

	public Double getDues() {
		Double dues = 0d;
		String sql = ("select ID as id,CONFIG_TYPE as configType,CONFIG_VALUE as configValue from T_SYS_CONFIG  where CONFIG_TYPE=" + "'FINANCEDUES'");
		List<ConfigPushDelay> list = this.namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<ConfigPushDelay>(ConfigPushDelay.class));
		if (list != null && list.size() > 0) {
			dues = Double.parseDouble(list.get(0).getConfigValue());
		}
		return dues;
	}

	public Integer deleteById(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_interior_mail_dev set ");
		sql.append(" STATUS = 2");
		sql.append(" where id= ");
		sql.append(id);
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString());
	}

	public Integer updateById(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_interior_mail_dev set ");
		sql.append(" STATUS = 1");
		sql.append(" where id= ");
		sql.append(id);
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString());
	}
	
}