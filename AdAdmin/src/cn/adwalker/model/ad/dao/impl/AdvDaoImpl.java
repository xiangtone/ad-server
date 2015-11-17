package cn.adwalker.model.ad.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.ad.dao.IAdvDao;
import cn.adwalker.model.ad.domain.Advertiser;

/**
 * 功能概述：<br>
 * 
 * @author jiaozhichao
 */
@Repository("advertiserDao")
public class AdvDaoImpl extends BaseDaoImpl<Advertiser> implements IAdvDao {

	public AdvDaoImpl() {
		setTableName("T_ADVERTISER");
	}


	/**
	 * @see cn.adwalker.model.ad.dao.IAdvDao#findById(int)
	 */
	@Override
	public Advertiser findById(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_ADVERTISER where ID = ? ");
		List<Advertiser> list = (List<Advertiser>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Advertiser>(Advertiser.class), id);
		Advertiser advertiser = new Advertiser();
		if (list != null && list.size() > 0) {
			advertiser = list.get(0);
		}
		return advertiser;
	}

	@Override
	public void updateStatus(Long advertiserId, Integer status) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update t_ADVERTISER set STATUS=?");
		sql.append(" where id=?");
		this.jdbcTemplate.update(sql.toString(), status, advertiserId);
	}

	@Override
	public int updatePassword(String password, Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_ADVERTISER set PASSWORD= ");
		sql.append("'");
		sql.append(password);
		sql.append("'");
		sql.append(" where id=");
		sql.append(id);
		return jdbcTemplate.update(sql.toString());
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAdvInformation
	 * </p>
	 * 
	 * @param advId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdvDao#findAdvInformation(java.lang.Long)
	 */
	public Advertiser findAdvInfo(Long advId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_ADVERTISER  where 1=1 and id= ?");
		List<Advertiser> list = (List<Advertiser>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Advertiser>(Advertiser.class), advId);
		Advertiser findAdv = new Advertiser();
		if (list != null && list.size() > 0) {
			findAdv = list.get(0);
		}
		return findAdv;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAdvService
	 * </p>
	 * 
	 * @param registadvvo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdvDao#updateAdvService(cn.adwalker.AdvForm.ad.vo.RegistAdvVo)
	 */
	@Override
	public long update(Advertiser entity) throws Exception {
		entity.setUpdate_time(new Date());
		StringBuilder sql = new StringBuilder();
		sql.append(" update T_ADVERTISER set ");
		if (ObjectUtils.isNotEmpty(entity.getCompany_name())) {
			sql.append("COMPANY_NAME = :company_name,");
		}
		if (ObjectUtils.isNotEmpty(entity.getCompany_address())) {
			sql.append("COMPANY_ADDRESS = :company_address,");
		}
		if (ObjectUtils.isNotEmpty(entity.getPostcode())) {
			sql.append("POSTCODE = :postcode,");
		}
		if (ObjectUtils.isNotEmpty(entity.getQq())) {
			sql.append("QQ = :qq,");
		}
		if (ObjectUtils.isNotEmpty(entity.getReal_name())) {
			sql.append("REAL_NAME = :real_name,");
		}
		if (entity.getMobile_phone() != null) {
			sql.append("MOBILE_PHONE = :mobile_phone,");
		}
		if (ObjectUtils.isNotEmpty(entity.getFixed_phone())) {
			sql.append("FIXED_PHONE = :fixed_phone,");
		}
		if (ObjectUtils.isNotEmpty(entity.getCredit_quota())) {
			sql.append("CREDIT_QUOTA = :credit_quota,");
		}
		if (entity.getArea_type()!=null) {
			sql.append("area_type = :area_type,");
		}
		
		sql.append("UPDATE_TIME = :update_time ");
		sql.append(" where ID=:id");
		return super.update(sql.toString(), entity);
	}

}
