/**
 * 
 */
package cn.adwalker.model.operation.dao;

import java.util.List;

import cn.adwalker.model.app.domain.ChaApplyMoney;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.ad.admin.operation.bean.ChaApplyMoneySearchBean;
import cn.adwalker.core.repository.IBaseDao;

/**
* <p>Title: IOperationChaApplyMoneyDao</p>
* <p>Description:渠道提现接口</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-2-14
 */
public interface IOperationChaApplyMoneyDao extends IBaseDao {

	/**
	 * 修改提现记录
	 * 
	 * @param DevApplyMoneyVo
	 * @return
	 * @throws Exception
	 */
	public Integer update(DevApplyMoney devApplyMoney) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:更新处理状态
	 * </p>
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2012-11-22
	 * @return Integer
	 * @version 1.0
	 * @throws
	 */
	public Integer updateStatus(Long id, Integer status) throws Exception;

	/**
	 * 根据用户ID查询待审核劲歌
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Double getApplyingByAppUserId(Long devUserId) throws Exception;

	/**
	 * 统计 tag:区分运营(0)和财务(1)和财务下载(2)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ChaApplyMoney> findReport(ChaApplyMoneySearchBean bean, int tag)
			throws Exception;

	/**
	 * 运用批量审核
	 * 
	 * @param devApplyMoneyList
	 * @throws Exception
	 */
	public void batchAuditInOper(List<ChaApplyMoney> devApplyMoneyList)
			throws Exception;

	/**
	 * 财务批量审核
	 * 
	 * @param devApplyMoneyList
	 * @throws Exception
	 */
	public void batchAuditInFinance(List<DevApplyMoney> devApplyMoneyList)
			throws Exception;

	/** 更新手续费和税率 */
	public void batchFinance_TaxAndDues(List<DevApplyMoney> updateList);

}