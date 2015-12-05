/**
 * 
 */
package cn.adwalker.ad.admin.app.service;

import cn.adwalker.model.app.domain.DevApplyMoney;

/**
 * 功能概述：<br>
 * 开发者提现服务接口
 * 
 * @author guoyongxiang
 */
public interface IDevApplyMoneyService {

	/**
	 * 添加提现记录
	 * 
	 * @param DevApplyMoney
	 * @throws Exception
	 */
	public void insert(DevApplyMoney DevApplyMoney) throws Exception;

	/**
	 * 根据用户获取申请中的提现金额
	 * 
	 * @param devUserId
	 * @return
	 * @throws Exception
	 */
	public Double getApplyingByDevId(Long devUserId) throws Exception;
}