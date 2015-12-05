package cn.adwalker.model.app.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.app.vo.DeveloperVo;
import cn.adwalker.ad.admin.finance.form.FinanceAwardDetail;
import cn.adwalker.ad.admin.finance.form.FinancePunishDetail;
import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.config.Constant;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.model.app.domain.Developer;
import cn.adwalker.model.app.domain.DeveloperEntity;
import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.model.finance.domain.UserDeveloper;

/**
 * 功能概述：<br>
 * 开发者实现类
 * 
 * @author zhaozengbin
 */
@Repository("userDeveloperDao")
public class DeveloperDaoImpl extends BaseDaoImpl<DeveloperEntity> implements IDeveloperDao {

	public DeveloperDaoImpl() {
		setTableName("T_DEVELOPER");
	}

	/**
	 * 
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#exists(java.lang.String)
	 */
	@Override
	public Developer exists(String email) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_DEVELOPER where EMAIL = ?");
		Developer userDeveloper = null;
		List<Developer> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Developer>(Developer.class), email);
		if (objects != null && objects.size() > 0) {
			userDeveloper = (Developer) objects.get(0);
		}
		return userDeveloper;
	}

	/**
	 * 
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#findAll()
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Developer> findAll() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_DEVELOPER");
		// sql.append(" select * from USER_DEVELOPER where STATUS<>"
		// + AppConstant.BLOCK_NOTUSED);
		sql.append(" order by CREATE_TIME desc");
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(Developer.class));
	}

	/**
	 * 
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#getById(java.lang.Integer)
	 */
	@Override
	public Developer getById(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_DEVELOPER where ID = ?");
		List<Developer> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Developer>(Developer.class), id);
		Developer userDeveloper = null;
		if (objects != null && objects.size() > 0) {
			userDeveloper = (Developer) objects.get(0);
			return userDeveloper;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insert
	 * </p>
	 * 
	 * @param dev
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#insert(cn.adwalker.model.app.domain.Developer)
	 */
	@Override
	public long insert(Developer dev) throws Exception {
		dev.setCreateTime(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_DEVELOPER(");
		sql.append("EMAIL,");
		sql.append("PASSWORD,");
		sql.append("COMPANY_NAME,");
		sql.append("COMPANY_ADDRESS,");
		sql.append("MAILING_ADDRESS,");
		sql.append("POST_CODE,");
		sql.append("QQ,");
		sql.append("MSN,");
		sql.append("REAL_NAME,");
		sql.append("MOBILE_PHONE,");
		sql.append("FIXED_PHONE,");
		sql.append("WEBSITE_URL,");
		sql.append("TYPE,");
		sql.append("DESCRIPTION,");
		sql.append("CARD_TYPE,");
		sql.append("CARD_CODE,");
		sql.append("CARD_URL,");
		sql.append("TOTAL_MONEY,");
		sql.append("CONFIRMED_MONEY,");
		sql.append("STATUS,");
		sql.append("CREATE_TIME");
		sql.append(")");
		sql.append(" values (");
		sql.append(":email,");
		sql.append(":password,");
		sql.append(":companyName,");
		sql.append(":companyAddress,");
		sql.append(":mailingAddress,");
		sql.append(":postCode,");
		sql.append(":qq,");
		sql.append(":msn,");
		sql.append(":realName,");
		sql.append(":mobilePhone,");
		sql.append(":fixedPhone,");
		sql.append(":websiteUrl,");
		sql.append(":type,");
		sql.append(":description,");
		sql.append(":cardType,");
		sql.append(":cardCode,");
		sql.append(":cardUrl,");
		sql.append(":totalMoney,");
		sql.append(":confirmedMoney,");
		sql.append(Constant.BLOCK_NOTUSED + ",");
		sql.append(":createTime");
		sql.append(")");
		return insert(sql.toString(), dev);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#update(cn.adwalker.model.app.domain.Developer)
	 */
	@Override
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
		if (ObjectUtils.isNotEmpty(userDeveloper.getCost())) {
			sql.append("COST=:cost,");
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
		return super.update(sql.toString(), userDeveloper);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatePass
	 * </p>
	 * 
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#updatePass(cn.adwalker.model.app.domain.Developer)
	 */
	@Override
	public Integer updatePass(Developer userDeveloper) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update USER_DEVELOPER set ");
		sql.append("PASSWORD=:password,");
		sql.append("UPDATE_TIME=:updateTime");
		sql.append(" where ID=:id");
		return super.update(sql.toString(), userDeveloper);
	}

	/**
	 * 获取用户下未删除的应用数量
	 */
	@Override
	public List<DeveloperVo> getAppCount() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select DEV_ID,count(*) as APP_COUNT from T_APPLICATION group by DEV_ID ");
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DeveloperVo>(DeveloperVo.class));
	}

	/**
	 * CUR_SCORE,UNCONFIRMED_MONEY,CONFIRMED_MONEY
	 * 
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#batchUpdateConsumeByConfirm(java.lang.Double, java.lang.Double)
	 */
	@Override
	public void updateoperateConfirmedMoney(Double manageMoney, Long id, Long managerId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update T_DEVELOPER set ");
		sql.append(" OPERATE_CONFIRMED_MONEY =  IFNULL(OPERATE_CONFIRMED_MONEY,0.0) + ");
		sql.append(manageMoney);
		sql.append(",LAST_UPDATE_MAN = ");
		sql.append(managerId);
		sql.append(" where id= ");
		sql.append(id);
		jdbcTemplate.update(sql.toString());
	}

	@Override
	public Double getTax(Double preTax) {
		Double tax = 0d;
		Double taxRate = 1d;
		String sql = ("select ID as id,CONFIG_TYPE as configType,CONFIG_VALUE as configValue from manage_config  where CONFIG_TYPE='FINANCETAX'");
		List<ConfigPushDelay> list = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<ConfigPushDelay>(ConfigPushDelay.class));
		if (list == null || list.size() == 0) {
			taxRate = -1d;
		} else {
			taxRate = Double.parseDouble(list.get(0).getConfigValue());
		}
		if (taxRate == -1) {
			if (preTax <= 800) {
				tax = 0d;
			} else if (preTax > 800 && preTax < 4000) {
				tax = (preTax - 800) * 0.2;
			} else if (preTax >= 4000 && preTax < 20000) {
				tax = preTax * (1 - 0.2) * 0.2;
			} else if (preTax >= 20000 && preTax < 50000) {
				if (preTax * (1 - 0.2) < 20000) {
					tax = preTax * (1 - 0.2) * 0.2;
				} else {
					tax = preTax * (1 - 0.2) * 0.3 - 2000;
				}
			} else if (preTax >= 50000) {
				if (preTax * (1 - 0.2) < 50000) {
					tax = preTax * (1 - 0.2) * 0.3 - 2000;
				} else {
					tax = preTax * (1 - 0.2) * 0.4 - 7000;
				}
			}
		} else {
			taxRate = taxRate / 100;
			tax = preTax * taxRate;
		}
		return tax;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByIdAndName
	 * </p>
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#getByIdAndName(java.lang.Long, java.lang.String)
	 */
	@Override
	public Developer getByIdAndName(Long id, String name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_DEVELOPER where ID = ? and email=?");
		List<Developer> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Developer>(Developer.class), new Object[] { id, name.trim() });
		Developer userDeveloper = null;
		if (objects != null && objects.size() > 0) {
			userDeveloper = (Developer) objects.get(0);
			return userDeveloper;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByName
	 * </p>
	 * 
	 * @param name
	 * @return
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#getByName(java.lang.String)
	 */
	@Override
	public List<Developer> getByEmail(String name) {
		List<Developer> list = null;
		if (!StringUtils.isEmpty(name)) {
			String sql = "select * from T_DEVELOPER where email like ? order by CREATE_TIME";
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Developer>(Developer.class), new Object[] { name.trim() + "%" });
		}
		return list;

	}
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#updateStatus(cn.adwalker.model.app.domain.Developer)
	 */
	@Override
	public Integer updateStatus(Long dev_id, Integer status, Long long1) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update T_DEVELOPER set ");
		sql.append("STATUS=?,");
		sql.append("LAST_UPDATE_MAN=?,");
		sql.append("UPDATE_TIME=?");
		sql.append(" where ID=?");
		return jdbcTemplate.update(sql.toString(), new Object[] { status, long1, new Date(), dev_id });

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateFreeze
	 * </p>
	 * 
	 * @param dev_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.developer.dao.IDeveloperDao#updateFreeze(java.lang.Long)
	 */
	@Override
	public Integer updateFreeze(Long dev_id) throws Exception {
		Developer entity = getById(dev_id);
		StringBuffer sql = new StringBuffer();
		sql.append("update T_DEVELOPER set ");
		sql.append("FINANCE_INCOME=?,");
		sql.append("FREEZE_MONEY=?,");
		sql.append("STATUS=?,");
		sql.append("UPDATE_TIME=?");
		sql.append(" where ID=?");
		return jdbcTemplate.update(sql.toString(), new Object[] { 0, entity.getFinance_income(),Constant.BLOCK_USE, new Date(), dev_id });
	}

	@Override
	public void updateDevTaxSetting(Long dev_id, Integer tax_status) {
		String sql = "update T_DEVELOPER  set tax_status=? where id=?";
		jdbcTemplate.update(sql, new Object[] { tax_status, dev_id });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchUpdateScoreAndMoney
	 * </p>
	 * 
	 * @param user
	 * @throws Exception
	 * @see cn.adwalker.model.IOperationDevIncomeAuditDao.dao.IDevIncomeEffectConfirmDao#batchUpdateScoreAndMoney(java.util.Map)
	 */
	@Override
	public void batchUpdateMoney(Map<Long, UserDeveloper> user) throws Exception {
		String sql = " update T_DEVELOPER set MONTH_INCOME=IFNULL(MONTH_INCOME,0.0)+?,CONFIRMED_TOTAL=IFNULL(CONFIRMED_TOTAL,0.0)+? where ID = ?";
		List<Object[]> parameters = new ArrayList<Object[]>();
		Set<Long> set = user.keySet();
		for (Long key : set) {
			UserDeveloper bean = user.get(key);
			bean.setId(key);
			parameters.add(new Object[] { bean.getConfirmedMoney(), bean.getConfirmedMoney(), bean.getId() });
		}
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * 
	 * @param userDeveloper
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IDeveloperDao#saveOrUpdate(cn.adwalker.model.app.domain.Developer)
	 */
	@Override
	public Integer saveOrUpdate(DeveloperEntity dev) throws Exception {
		DeveloperEntity entity = (DeveloperEntity) this.get(dev.getId(), DeveloperEntity.class);
		boolean flag = false;
		if (entity != null) {
			flag = true;
		} else {
			entity = new DeveloperEntity();
			entity.setId(dev.getId());
			entity.setEmail(dev.getEmail());
		}
		entity.setPassword(dev.getPassword());
		entity.setCompany_name(dev.getCompany_name());
		entity.setCompany_address(dev.getCompany_address());
		entity.setMailing_address(dev.getMailing_address());
		entity.setType(dev.getType());
		entity.setPost_code(dev.getPost_code());
		entity.setQq(dev.getQq());
		entity.setReal_name(dev.getReal_name());
		entity.setMobile_phone(dev.getMobile_phone());
		entity.setCard_type(dev.getCard_type());
		entity.setCard_code(dev.getCard_code());
		entity.setUpdate_time(dev.getUpdate_time());
		entity.setCreate_time(dev.getCreate_time());
		entity.setResource_code(dev.getResource_code());
		if (flag) {
			this.update(entity);
		} else {
			this.insertDev(entity);
		}

		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: insertDev
	 * </p>
	 * <p>
	 * Description:新增开发者
	 * </p>
	 * 
	 * @param dev
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-8-1
	 * @return Integer
	 * @version 1.0
	 */
	private Integer insertDev(DeveloperEntity entity) throws Exception {
		entity.setCreate_time(new Date());
		Field[] fields = entity.getClass().getDeclaredFields();
		List<String> arr = new ArrayList<String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!fields[i].getName().toUpperCase().equals("SERIALVERSIONUID")) {
				if (!fields[i].getName().equals(super.ID)) {
					arr.add(fields[i].getName());
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into " + tableName + "(");
		for (String s : arr) {
			sb.append(s + ",");
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		sb.append(" values (");
		for (String s : arr) {
			if (!s.equals(super.ID)) {
				sb.append(":" + s + ",");
			}
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return super.update(sb.toString(), entity);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String findByEmail(Long id) throws Exception {
		String sql = "select EMAIL from T_DEVELOPER where ID = " + id;
		List<Developer> list = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(Developer.class));
		return list.get(0).getEmail();
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateDev
	 * </p>
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.finance.dao.IDevListAwardDetailDao#updateDev(cn.adwalker.ad.admin.finance.form.FinanceAwardDetail)
	 */
	@Override
	public int updateDev(FinanceAwardDetail form) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_DEVELOPER set ");
		sql.append(" FINANCE_INCOME = IFNULL(FINANCE_INCOME,0) + :entryActMoney ");
		sql.append(", AWARD_INCOME = IFNULL(AWARD_INCOME,0) + :entryActMoney ");
		sql.append(" where id = :entryDevId ");
		return super.update(sql.toString(), form);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchUpdateTotalMAndConfirmM
	 * </p>
	 * 
	 * @param user
	 * @throws Exception
	 * @see cn.adwalker.model.finace.dao.IFinanceDevApplyMoneyDao#batchUpdateTotalMAndConfirmM(java.util.Map)
	 */
	@Override
	public void batchUpdateTotalMAndConfirmM(Map<Long, Developer> user) throws Exception {
		String sql = " update T_DEVELOPER set CONFIRMED_INCOME = IFNULL(CONFIRMED_INCOME,0.0) - ? , FINANCE_INCOME = IFNULL(FINANCE_INCOME,0.0) - ? , TOTAL_MONEY = IFNULL(TOTAL_MONEY,0.0) + ? where ID = ? ";
		List<Object[]> parameters = new ArrayList<Object[]>();
		Set<Long> set = user.keySet();
		for (Long key : set) {
			Developer bean = user.get(key);
			bean.setId(key);
			parameters.add(new Object[] { bean.getConfirmedMoney(), bean.getConfirmedMoney(), bean.getTotalMoney(), bean.getId() });
		}
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateDevPunish
	 * </p>
	 * 
	 * @param from
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.finance.dao.IFinancePunishDetailDao#updateDevPunish(cn.adwalker.ad.admin.finance.form.FinancePunishDetail)
	 */
	@Override
	public int updateDevPunish(FinancePunishDetail from) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_DEVELOPER set ");
		sql.append(" FINANCE_INCOME = IFNULL(FINANCE_INCOME,0) - :entryActMoney ");
		sql.append(" where id = :entryDevId ");
		return super.update(sql.toString(), from);
	}

	/**
	 * 
	 * @see cn.adwalker.model.sys.dao.ISysMailDao#resetPassword(java.lang.String, java.lang.String, int)
	 */
	@Override
	public int resetPassword(String email, String password, int type) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (type == AppConstant.USER_ADVERTISER) {
			sql.append("update T_DEVELOPER set PASSWORD=? where EMAIL=?");
		} else if (type == AppConstant.USER_DEVELOPER) {
			sql.append("update T_DEVELOPER set PASSWORD=? where EMAIL=?");
		} else {
			return 0;
		}
		jdbcTemplate.update(sql.toString(), password, email);
		return 1;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateMonthFinance</p>
	* <p>Description:TODO</p>
	* @return
	* @throws Exception
	* @see cn.adwalker.model.app.dao.IDeveloperDao#updateMonthFinance()
	 */
	@Override
	public int updateMonthFinance() throws Exception {
		
		StringBuffer sql = new StringBuffer();
			sql.append("update T_DEVELOPER set FINANCE_INCOME=IFNULL(FINANCE_INCOME,0.0)+IFNULL(MONTH_INCOME,0.0)");
			return  jdbcTemplate.update(sql.toString());
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateMonth</p>
	* <p>Description:TODO</p>
	* @return
	* @throws Exception
	* @see cn.adwalker.model.app.dao.IDeveloperDao#updateMonth()
	 */
	@Override
	public int updateMonth() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update T_DEVELOPER set MONTH_INCOME=0");
		return  jdbcTemplate.update(sql.toString());
	}
}