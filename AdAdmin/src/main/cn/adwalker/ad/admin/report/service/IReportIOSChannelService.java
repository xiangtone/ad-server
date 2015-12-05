package cn.adwalker.ad.admin.report.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.report.bean.AdsageActiviteLog;
import cn.adwalker.ad.admin.report.bean.IOSChannelBean;

public interface IReportIOSChannelService {

	/*****
	 * 条件获取所有的ios渠道统计数据
	 * 
	 * @param pageIndex
	 * @param pageRecord
	 * @param iocVo
	 * @return
	 * @throws Exception
	 */
	public List<AdsageActiviteLog> getadIOSLog(IOSChannelBean iocVo,
			IPageInfo pageInfo) throws Exception;

	/*****
	 * 汇总信息
	 */
	public int getSumActivete(IOSChannelBean iocVo);

	/***
	 * 获取渠道列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getChannelList();

	/**
	 * <p>
	 * Title: getAdList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @return List<Map<String,Object>>
	 * @throws
	 */
	public List<Map<String, Object>> getAdList();

	/**
	 * 
	 * <p>
	 * Title: getIOSExcel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2013-9-3
	 * @return List<AdsageActiviteLog>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<AdsageActiviteLog> getIOSExcel(IOSChannelBean bean)
			throws Exception;
	
	/**
	 * 当前时刻的前60-65分钟的数据copy到T_IOS_INCOME_NUMBER表里
	 */
	public void copyActionLog();
	
	/**
	 * <p>模糊查询，自动匹配 author jief</p>
	 * @param ad_name
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getAdListAutoCom(String ad_name)throws Exception; 
	
	/**
	 * <p>从jdbc 的ResultSet中获取每一条数据  author jief</p>
	 * @param bean
	 * @throws Exception
	 * 
	 */
	public File getIOSExcelFromResultSet(IOSChannelBean bean) throws Exception;
}
