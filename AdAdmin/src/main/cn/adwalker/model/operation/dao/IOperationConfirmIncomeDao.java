package cn.adwalker.model.operation.dao;

import java.util.List;

import cn.adwalker.ad.admin.operation.form.AdIosEntering;
import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.operation.domain.IncomeConfirm;

/**
 * @author wjp 广告效果一次确认
 */
@SuppressWarnings("rawtypes")
public interface IOperationConfirmIncomeDao extends IBaseDao {

	/**
	 * <p>
	 * Title: batchDealStatus
	 * </p>
	 * <p>
	 * Description:批量状态处理
	 * </p>
	 * 
	 * @param map
	 * @param manageId
	 * @param description
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-17
	 * @return int
	 * @version 1.0
	 */
	public int batchDealStatus(String[] s_date, Long[] ad_id, Long manageId, Integer[] ch_plf_type, String description) throws Exception;

	/**
	 * 根据广告主id及时间段获取一次确认金额
	 * 
	 * @param advId
	 *            广告主id
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 * @throws Exception
	 */
	public double countMoneyByDate(Long advId, String startDate, String endDate) throws Exception;

	/**
	 * <p>
	 * Title: findPage_id
	 * </p>
	 * <p>
	 * Description:判断渠道包是否存在
	 * </p>
	 * 
	 * @param page_id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-9
	 * @return Integer
	 * @version 1.0
	 */
	public Integer findPage_id(String page_id) throws Exception;

	/**
	 * <p>
	 * Title: findPageCreateTime
	 * </p>
	 * <p>
	 * Description:判断数据是否发布过
	 * </p>
	 * 
	 * @param income_mac
	 * @param campaign_id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-2
	 * @return Integer
	 * @version 1.0
	 */
	public Integer findPageCreateTime(String income_mac, Long campaign_id) throws Exception;

	/**
	 * <p>
	 * Title: insertAdEffectIosData
	 * </p>
	 * <p>
	 * Description:插入基础数据
	 * </p>
	 * 
	 * @param list
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-1
	 * @return void
	 * @version 1.0
	 */
	public int insertAdEffectIosData(List<AdIosEntering> list) throws Exception;

	/**
	 * <p>
	 * Title: insertIosDate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s_date
	 * @param cam_id
	 * @param manageId
	 * @param ch_plf_type
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-24
	 * @return void
	 * @version 1.0
	 */
	public int insertIosDate(List<IncomeConfirm> list) throws Exception;

	/**
	 * <p>
	 * Title: findIosDate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s_date
	 * @param cam_id
	 * @param manageId
	 * @param ch_plf_type
	 * @param string
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-24
	 * @return List<IncomeConfirm>
	 * @version 1.0
	 */
	public List<IncomeConfirm> findIosDate(String[] s_date, Long[] ad_id, Long manageId, Integer[] ch_plf_type) throws Exception;

	/**
	 * <p>
	 * Title: batchDealAdStatus
	 * </p>
	 * <p>
	 * Description:ios线下给积分
	 * </p>
	 * 
	 * @param mapAd
	 * @param i
	 * @param manageId
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-11-21
	 * @return void
	 * @version 1.0
	 */
	public int batchDealAdStatus(List<Long> mapAd, int i, Long manageId) throws Exception;

}
