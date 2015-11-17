package cn.adwalker.ad.model.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.common.domain.BankInfo;
import cn.adwalker.ad.model.common.domain.ProvinceCitySort;
import cn.adwalker.ad.util.ObjectUtils;

/**
 * 功能概述：<br>
 * 银行信息管理接口实现
 */
@Repository("bankInfoDao")
public class BankInfoDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<BankInfo> findByUser(Long userId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_BANK_INFO where USER_ID = ?");
		List<BankInfo> bankInfoList = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<BankInfo>(BankInfo.class), userId);
		if (bankInfoList != null && bankInfoList.size() > 0) {
			return bankInfoList;
		}
		return null;
	}
	
	public List<ProvinceCitySort> findprovinceCity( int parent_id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_PLATFORM_PROVINCE_CITY where PARENT_ID = ?");
		List<ProvinceCitySort> provinceCitySort = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), parent_id);
		if (provinceCitySort != null && provinceCitySort.size() > 0) {
			return provinceCitySort;
		}
		return null;
	}
	
	public List<ProvinceCitySort> findIdCity( int parent_id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_PLATFORM_PROVINCE_CITY where SORT = ?");
		List<ProvinceCitySort> provinceCitySort = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), parent_id);
		if (provinceCitySort != null && provinceCitySort.size() > 0) {
			return provinceCitySort;
		}
		return null;
	}

	public Integer insert(BankInfo bankInfo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_BANK_INFO(");
		sql.append("USER_ID,");
		sql.append("ROLE,");
		sql.append("ACCOUNT_HODER,");
		sql.append("BANK_NAME,");
		sql.append("BANK_CITY,");
		sql.append("CITY_ID,");
		sql.append("BANK_SUBBRANCH,");
		sql.append("BANK_ACCOUNT,");
		sql.append("CARD_TYPE,");
		sql.append("CARD_CODE,");
		sql.append("CARD_URL,");
		sql.append("CARD_URL_OPPOSITE,");
		sql.append("CREATE_TIME)");
		sql.append(" values(");
		sql.append(":userId,");
		sql.append(":role,");
		sql.append(":accountHoder,");
		sql.append(":bankName,");
		sql.append(":bankCity,");
		sql.append(":city_id,");
		sql.append(":bankSubbranch,");
		sql.append(":bankAccount,");
		sql.append(":cardType,");
		sql.append(":cardCode,");
		sql.append(":cardUrl,");
		sql.append(":card_url_opposite,");
		sql.append(":createTime)");
		return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(bankInfo));
	}

	public Integer update(BankInfo bankInfo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update T_BANK_INFO SET ");
		if (ObjectUtils.isNotEmpty(bankInfo.getAccountHoder())) {
			sql.append("ACCOUNT_HODER =:accountHoder,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getBankName())) {
			sql.append("BANK_NAME =:bankName,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getBankCity())) {
			sql.append("BANK_CITY =:bankCity,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCity_id())) {
			sql.append("CITY_ID =:city_id,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getBankSubbranch())) {
			sql.append("BANK_SUBBRANCH =:bankSubbranch,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getBankAccount())) {
			sql.append("BANK_ACCOUNT =:bankAccount,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCardType())) {
			sql.append("CARD_TYPE =:cardType,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCardCode())) {
			sql.append("CARD_CODE =:cardCode,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCardUrl())) {
			sql.append("CARD_URL =:cardUrl,");
		}
		if (ObjectUtils.isNotEmpty(bankInfo.getCard_url_opposite())) {
			sql.append("CARD_URL_OPPOSITE =:card_url_opposite,");
		}
		String sql1 = sql.substring(0, sql.length()-1);
		sql1 = sql1+" where ID =:id";
		return namedParameterJdbcTemplate.update(sql1, new BeanPropertySqlParameterSource(bankInfo));
	}

}
