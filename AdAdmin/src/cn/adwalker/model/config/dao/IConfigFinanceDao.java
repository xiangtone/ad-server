package cn.adwalker.model.config.dao;


/**
 * 功能概述： <br>
 * 开发者SDK应用管理接口
 * 
 * @author guoyongxiang
 */
public interface IConfigFinanceDao {
	/**
	 * 查询积分与货币的汇率
	 * @return
	 * @throws Exception
	 */
	public Integer findMoneyScoreRate();

}