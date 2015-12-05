package cn.adwalker.model.report.dao;

import java.util.List;
import java.util.Map;

import cn.adwalker.ad.admin.report.bean.AdsageActiviteLog;
import cn.adwalker.ad.admin.report.bean.IOSChannelBean;
import cn.adwalker.ad.admin.report.util.ResultSetItor;
import cn.adwalker.core.repository.IBaseDao;



public interface IReportIOSChannelDao extends IBaseDao{
	
	/*****
	 * 汇总信息
	 */
	public int getSumActivete(IOSChannelBean iocVo);

	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getChannelList();

	/**
	* <p>Title: getAdList</p>
	* <p>Description:TODO</p>
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> getAdList();
	
	/**
	 * 把两个时间段内的数据copy到T_IOS_INCOME_NUMBER表里
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public void copyActionLog(String beginDate, String endDate);
	/**
	 * <p>获取自定义迭代器 author jief</p>
	 * @param bean
	 */
	public ResultSetItor<AdsageActiviteLog> getResultItor(IOSChannelBean bean) throws Exception;
}
