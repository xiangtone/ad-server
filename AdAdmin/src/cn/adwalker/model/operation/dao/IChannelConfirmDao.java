package cn.adwalker.model.operation.dao;

import java.util.List;

import cn.adwalker.ad.admin.finance.vo.ChannelConfirmVo;
import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.finance.domain.CostMonthChannel;
import cn.adwalker.model.operation.domain.CampaignConfirm;
import cn.adwalker.model.operation.domain.CampaignIncomeCost;

/**
 * <p>
 * Title: ICampaignConfirmDao
 * </p>
 * <p>
 * Description:确认数录入dao接口
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-8
 */
public interface IChannelConfirmDao extends IBaseDao<CampaignConfirm> {
	/**
	* <p>Title: updateinvoice</p>
	* <p>Description:收票</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2013-10-28
	* @return void
	* @version 1.0
	 */
	public int[] updateInvoiceChannel(List<Object[]> objList)throws Exception;
	/**
	* <p>Title: updateinvoice</p>
	* <p>Description:付款</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2013-10-28
	* @return void
	* @version 1.0
	 */
	public int[] updatePayment(List<Object[]> objList)throws Exception;
	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-31
	 * @return CampaignIncome
	 * @version 1.0
	 */
	public CampaignIncomeCost findById(Long id) throws Exception;
	
	/**
	* <p>Title: getCampaignConfirm</p>
	* <p>Description:查找要被修改的数据</p>
	* @param id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-10-24
	* @return CampaignConfirm
	* @version 1.0
	 */
	public CampaignConfirm getCampaignConfirm(Long id)throws Exception;
	/**
	* <p>Title: updateinvoice</p>
	* <p>Description:更新所有</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2013-10-28
	* @return void
	* @version 1.0
	 */
	public void updateInvoice(Long confirm_id,Long id)throws Exception;
	/**
	* <p>Title: deleteInvoice</p>
	* <p>Description:删除所有</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2013-10-28
	* @return void
	* @version 1.0
	 */
	public void deleteInvoice(long id)throws Exception;
	/**
	* <p>Title: updatereduceAchievement</p>
	* <p>Description:业绩报表还原</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2014-2-17
	* @return void
	* @version 1.0
	 */
	public void updatereduceAchievement(Long id)throws Exception;
	/**
	* <p>Title: updatepassThrough</p>
	* <p>Description:业绩报表通过不通过</p>
	* @param id
	* @param status
	* @throws Exception
	* @author lichuang
	* @date 2014-2-26
	* @return void
	* @version 1.0
	 */
	public void updatepassThrough(Long id, Integer status)throws Exception;
	
	/**
	* <p>Title: findByIosId</p>
	* <p>Description:ios预计收入</p>
	* @param id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2014-4-8
	* @return CampaignIncomeCost
	* @version 1.0
	 */
	public CampaignIncomeCost findByIosId(Long id)throws Exception;
	/**
	* <p>Title: getgetById</p>
	* <p>Description:显示数据申请的渠道成本结算数据</p>
	* @param income_id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return ChannelConfirmVo
	* @version 1.0
	 */
	public ChannelConfirmVo getgetById(Long income_id)throws Exception;
	/**
	* <p>Title: updateStatus</p>
	* <p>Description:修改数据状态</p>
	* @param id
	* @throws Exception
	* @author cuidd
	* @date 2014年11月15日
	* @return void
	* @version 1.0
	 */
	public void updateStatus(CostMonthChannel plist)throws Exception;
	
	/**
	* <p>Title: updateInCome</p>
	* <p>Description:TODO</p>
	* @param objList
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月15日
	* @return int[]
	* @version 1.0
	 */
	public int[] updateInCome(List<Object[]> objList)throws Exception;
	/**
	* <p>Title: deleteStatus</p>
	* <p>Description:删除申请产生的数据</p>
	* @param id
	* @throws Exception
	* @author cuidd
	* @date 2014年12月10日
	* @return void
	* @version 1.0
	 */
	public void deleteStatus(Long id)throws Exception;

}
