package cn.adwalker.model.ad.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.ad.dao.IAdvBankInfoDao;
import cn.adwalker.model.ad.domain.AdvBankInfo;
import cn.adwalker.ad.admin.ad.form.AdvBankInfoform;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 功能概述：<br>
 * 
 * @author jiaozhichao
 */
@Repository("advBankInfoDao")
public class AdvBankInfoDaoImpl extends BaseDaoImpl<AdvBankInfo> implements IAdvBankInfoDao {

	public AdvBankInfoDaoImpl() {
		setTableName("T_BANK_INFO");
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
	public AdvBankInfo findAdvBankInfo(Long advId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_BANK_INFO  where 1=1 and role=1 and user_id= ?");
		List<AdvBankInfo> list = (List<AdvBankInfo>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<AdvBankInfo>(AdvBankInfo.class), advId);
		AdvBankInfo advBankInfo = new AdvBankInfo();
		if (list != null && list.size() > 0) {
			advBankInfo = list.get(0);
		}
		return advBankInfo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insertbankInfo
	 * </p>
	 * 
	 * @param advBankInfoVo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdvDao#insertbankInfo(cn.adwalker.admin.ad.form.AdvBankInfoform.ad.vo.AdvBankInfoVo)
	 */
	@Override
	public void insertbankInfo(AdvBankInfoform advBankInfoVo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into T_BANK_INFO (USER_ID,ROLE,ACCOUNT_HODER,BANK_ACCOUNT,BANK_SUBBRANCH,COMPANY_REGIST_ADDRESS,TAXPAYER_NUMBER,INVOICE_REQUIRE,INVOICE_REQUIRE_OTHERS,BUSINESS_LICENSE,TAX_REG_CER,ACCOUNT_PERMIT,TAXPAYER_CERTIFICATE)");
		sql.append(" values (:adv_id,:userType,:account_hoder,:bank_account,:bank_subbranch,:company_regist_address,:taxpayer_number,:invoice_require,:invoice_require_others,:business_license,:tax_reg_cer,:account_permit,:taxpayer_certificate)");
		super.update(sql.toString(), advBankInfoVo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatesBankInfoService
	 * </p>
	 * 
	 * @param advBankInfoVo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdvDao#updatesBankInfoService(cn.adwalker.admin.ad.form.AdvBankInfoform.ad.vo.AdvBankInfoVo)
	 */

	@Override
	public void updatesBankInfoService(AdvBankInfoform advBankInfoVo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update T_BANK_INFO set ");
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getBank_account())) {
			sql.append("BANK_ACCOUNT = :bank_account,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getBank_subbranch())) {
			sql.append("BANK_SUBBRANCH = :bank_subbranch,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getAccount_hoder())) {
			sql.append("ACCOUNT_HODER = :account_hoder,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getCompany_regist_address())) {
			sql.append("COMPANY_REGIST_ADDRESS = :company_regist_address,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getInvoice_require())) {
			sql.append("INVOICE_REQUIRE = :invoice_require,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getInvoice_require_others())) {
			sql.append("INVOICE_REQUIRE_OTHERS = :invoice_require_others,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getBusiness_license())) {
			sql.append("BUSINESS_LICENSE = :business_license,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getTax_reg_cer())) {
			sql.append("TAX_REG_CER = :tax_reg_cer,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getAccount_permit())) {
			sql.append("ACCOUNT_PERMIT = :account_permit,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getTaxpayer_certificate())) {
			sql.append("TAXPAYER_CERTIFICATE = :taxpayer_certificate,");
		}
		if (ObjectUtils.isNotEmpty(advBankInfoVo.getTaxpayer_number())) {
			sql.append("TAXPAYER_NUMBER = :taxpayer_number,");
		}
		sql.append("ROLE = 1");
		sql.append(" where USER_ID=:adv_id");
		super.update(sql.toString(), advBankInfoVo);
	}

}
