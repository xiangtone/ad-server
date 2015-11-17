/**
 * 
 */
package cn.adwalker.ad.admin.app.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.model.app.dao.IDevApplyMoneyDao;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.ad.admin.app.service.IDevApplyMoneyService;
import cn.adwalker.ad.config.AppConstant;

/**
 * 功能概述：<br>
 * 开发者提现服务
 * 
 * @author zhaozengbin,guoyongxiang
 * 
 */
@Service(value = "devApplyMoneyLogService")
@Transactional
public class DevApplyMoneyServiceImpl implements IDevApplyMoneyService {
	@Resource
	private IDevApplyMoneyDao devApplyMoneyDao;

	 

	/**
	 * @see cn.adwalker.ad.admin.app.service.manager.dev.service.IDevApplyMoneyService#insert(cn.adwalker.model.app.domain.DevApplyMoney)
	 */
	public void insert(DevApplyMoney DevApplyMoney) throws Exception {
		DevApplyMoney.setCreateTime(new Date());
		DevApplyMoney.setStatus(String.valueOf(AppConstant.DEV_APPLY_MONEY));// 设置审批状态:未审批
		devApplyMoneyDao.insert(DevApplyMoney);
	}

	/**
	 * 
	 * @see cn.adwalker.ad.admin.app.service.manager.dev.service.IDevApplyMoneyService#findByDevUserId(java.lang.String)
	 */
	@Override
	public Double getApplyingByDevId(Long devUserId) throws Exception {
		Double applying = devApplyMoneyDao.getApplyingByAppUserId(devUserId);
		return applying;
	}
}