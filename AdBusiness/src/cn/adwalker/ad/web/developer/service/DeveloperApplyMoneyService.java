package cn.adwalker.ad.web.developer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.config.DevelopConstants;
import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.model.application.dao.ApplicationDao;
import cn.adwalker.ad.model.common.dao.BankInfoDao;
import cn.adwalker.ad.model.common.domain.BankInfo;
import cn.adwalker.ad.model.common.domain.ProvinceCitySort;
import cn.adwalker.ad.model.developer.dao.DeveloperApplyMoneyDao;
import cn.adwalker.ad.model.developer.domain.DevApplyMoney;
import cn.adwalker.ad.web.common.bean.DevIncomeBean;
import cn.adwalker.ad.web.developer.bean.DevApplyMoneySearch;
import cn.adwalker.ad.web.developer.form.DevBankInfoFrom;
import cn.adwalker.ad.web.developer.vo.DevAccountInfoVo;
import cn.adwalker.ad.web.developer.vo.DevApplyMoneyVo;

/**
 * 功能概述：<br>
 * 开发者提现服务
 */
@Service("developerApplyMoneyService")
public class DeveloperApplyMoneyService {

	@Resource
	private DeveloperApplyMoneyDao devApplyMoneyDao;

	@Resource
	private ApplicationDao developedAppDao;

	/** 开发者操作服务 */
	@Resource
	private DeveloperService developerService;
	/** 财务信息 */
	@Resource
	private BankInfoDao bankInfoDao;

	public Long insert(DevApplyMoney DevApplyMoney) throws Exception {
		DevApplyMoney.setCreateTime(new Date());
		DevApplyMoney.setStatus(String.valueOf(AppConstant.DEV_APPLY_MONEY_FIRST));// 设置审批状态:初始化
		return devApplyMoneyDao.insert(DevApplyMoney);
	}

	/**
	 * 撤销提款申请处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Integer cancelApply(Long id) throws Exception {
		if (id != null) {
			DevApplyMoney entity = devApplyMoneyDao.getById(id);
			if (entity.getStatus().equals(String.valueOf(DevelopConstants.DEV_APPLY_MONEY_STATUS_APPLY))) {
				return devApplyMoneyDao.updateStatus(id, DevelopConstants.DEV_APPLY_MONEY_STATUS_CANCEL);
			}
		}
		return null;
	}

	public List<DevApplyMoneyVo> findByDevUserId(DevApplyMoneySearch bean, IPageInfo pageInfo) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" T_DEV_APPLY_MONEY t  LEFT JOIN t_developer Y ON t.dev_id=y.id where 1=1 and t.STATUS != -3 and t.STATUS != -5 and t.DEV_ID = " + bean.getDev_id());
		sb.append(" order by t.CREATE_TIME desc");
		return (List<DevApplyMoneyVo>) devApplyMoneyDao.findByPage("t.*,y.finance_income", sb.toString(), DevApplyMoneyVo.class, pageInfo);
	}

	/**
	 * 获取已申请和待审核的钱
	 * 
	 * @param devUserId
	 * @return
	 * @throws Exception
	 */
	public Double getApplyingByDevUserId(Long devUserId) throws Exception {
		return devApplyMoneyDao.getApplyingByAppUserId(devUserId);
	}

	@Transactional
	public void updateFnancialInfoMoney(DevBankInfoFrom bankInfo) throws Exception {
		BankInfo devBankInfo = new BankInfo();
		devBankInfo.setBankAccount(bankInfo.getBankAccount());
		devBankInfo.setBankCity(bankInfo.getBankCity());
		devBankInfo.setCity_id(bankInfo.getCity_id());
		devBankInfo.setCardUrl(bankInfo.getCardUrl());
		devBankInfo.setCardCode(bankInfo.getCardCode());
		devBankInfo.setCardType(bankInfo.getCardType());
		devBankInfo.setBankSubbranch(bankInfo.getBankSubbranch());
		devBankInfo.setBankName(bankInfo.getBankName());
		devBankInfo.setAccountHoder(bankInfo.getAccountHoder());
		devBankInfo.setUserId(bankInfo.getUserId());
		devBankInfo.setCard_url_opposite(bankInfo.getCard_url_opposite());
		devBankInfo.setId(bankInfo.getId());
		bankInfoDao.update(devBankInfo);
	}

	@Transactional
	public void insertFnancialInfoMoney(DevBankInfoFrom bankInfo) throws Exception {
		BankInfo devBankInfo = new BankInfo();
		devBankInfo.setRole(2);
		devBankInfo.setCreateTime(new Date());
		devBankInfo.setBankAccount(bankInfo.getBankAccount());
		devBankInfo.setBankCity(bankInfo.getBankCity());
		devBankInfo.setCity_id(bankInfo.getCity_id());
		devBankInfo.setCardUrl(bankInfo.getCardUrl());
		devBankInfo.setCardCode(bankInfo.getCardCode());
		devBankInfo.setCardType(bankInfo.getCardType());
		devBankInfo.setBankSubbranch(bankInfo.getBankSubbranch());
		devBankInfo.setBankName(bankInfo.getBankName());
		devBankInfo.setAccountHoder(bankInfo.getAccountHoder());
		devBankInfo.setUserId(bankInfo.getUserId());
		devBankInfo.setCard_url_opposite(bankInfo.getCard_url_opposite());
		devBankInfo.setId(bankInfo.getId());
		bankInfoDao.insert(devBankInfo);
	}

	public List<BankInfo> findByUser(Long id) throws Exception {
		List<BankInfo> bankInfoList = bankInfoDao.findByUser(id);
		return bankInfoList;
	}

	public List<ProvinceCitySort> findprovinceCity(int parent_id) throws Exception {
		return bankInfoDao.findprovinceCity(parent_id);
	}

	public DevAccountInfoVo findByIdInfo(Long dev_id) throws Exception {
		return this.devApplyMoneyDao.findByIdInfo(dev_id);
	}

	@Transactional
	public void updateApplyAccount(DevApplyMoney devApplyMoney) throws Exception {
		if (devApplyMoney != null) {
			devApplyMoney.setStatus(String.valueOf(AppConstant.DEV_APPLY_MONEY));
			devApplyMoneyDao.updateApplyAccount(devApplyMoney);
		}
	}

	public List<DevIncomeBean> queryIncomeByDate(Date beginDate, Long userId) throws Exception {
		return devApplyMoneyDao.queryIncomeByDate(beginDate, userId);
	}

	public Map<String, Integer> queryAppCount(Long userId) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("status_3", devApplyMoneyDao.queryAppCount(userId, 3));
		map.put("status_0", devApplyMoneyDao.queryAppCount(userId, 0));
		map.put("status_2", devApplyMoneyDao.queryAppCount(userId, 2));
		map.put("status_-1", devApplyMoneyDao.queryAppCount(userId, -1));
		map.put("status_1", devApplyMoneyDao.queryAppCount(userId, 1));
		return map;
	}

}