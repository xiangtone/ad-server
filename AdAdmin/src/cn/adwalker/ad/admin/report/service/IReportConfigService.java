package cn.adwalker.ad.admin.report.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.report.domain.TStatReportItem;
import cn.adwalker.model.report.domain.TStatReportTable;

public interface IReportConfigService {
	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:查询报表配置列表
	 * </p>
	 * 
	 * @param pageIndex
	 * @param pageRecord
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return List<TStatReportTable>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<TStatReportTable> findAll(IPageInfo pageInfo) throws Exception;


	/**
	 * 
	 * <p>
	 * Title: insertTable
	 * </p>
	 * <p>
	 * Description:添加一个报表
	 * </p>
	 * 
	 * @param data
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return void
	 * @version 1.0
	 */
	public void insertTable(TStatReportTable data);

	/**
	 * 
	 * <p>
	 * Title: getReportTableForEdit
	 * </p>
	 * <p>
	 * Description:得到一个报表
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return TStatReportTable
	 * @version 1.0
	 */
	public TStatReportTable getReportTableForEdit(Long id);

	/**
	 * 
	 * <p>
	 * Title: updateTable
	 * </p>
	 * <p>
	 * Description:更新一个报表
	 * </p>
	 * 
	 * @param table
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return void
	 * @version 1.0
	 */
	public void updateTable(TStatReportTable table);

	/**
	 * 
	 * <p>
	 * Title: getReportItemsForList
	 * </p>
	 * <p>
	 * Description:得到单个报表的item集合
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return List
	 * @version 1.0
	 */
	public List getReportItemsForList(Long id);

	/***
	 * 
	 * <p>
	 * Title: insertItem
	 * </p>
	 * <p>
	 * Description:插入一个item
	 * </p>
	 * 
	 * @param table
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return void
	 * @version 1.0
	 */
	public void insertItem(TStatReportItem table);

	/**
	 * 
	 * <p>
	 * Title: getReportItemForEdit
	 * </p>
	 * <p>
	 * Description:得到一个item
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return TStatReportItem
	 * @version 1.0
	 */
	public TStatReportItem getReportItemForEdit(Long id);

	/**
	 * 
	 * <p>
	 * Title: updateItem
	 * </p>
	 * <p>
	 * Description:得到一个item
	 * </p>
	 * 
	 * @param data
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return void
	 * @version 1.0
	 */
	public void updateItem(TStatReportItem data);

	/**
	 * 
	 * <p>
	 * Title: addSysPurview
	 * </p>
	 * <p>
	 * Description:添加权限
	 * </p>
	 * 
	 * @param table
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return void
	 * @version 1.0
	 */
	public void addSysPurview();

	/**
	 * 
	 * <p>
	 * Title: updateSysPurview
	 * </p>
	 * <p>
	 * Description:修改权限
	 * </p>
	 * 
	 * @param table
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return void
	 * @version 1.0
	 */
	public void updateSysPurview(TStatReportTable table);

	public TStatReportTable getReportTable(Long id);

	/**
	 * <p>
	 * Title: getReportItems
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-5-29
	 * @return ArrayList
	 * @version 1.0
	 */

	ArrayList getReportItems(Long id);

	/**
	 * <p>
	 * Title: executeQuery
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s
	 * @return
	 * @author cuidd
	 * @date 2013-5-29
	 * @return ArrayList
	 * @version 1.0
	 */

	public List executeQuery(String s);

	/**
	 * <p>
	 * Title: executeQueryList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param total_sql
	 * @return
	 * @author cuidd
	 * @date 2013-5-29
	 * @return List
	 * @version 1.0
	 */

	public List executeQueryList(String total_sql);

	/**
	 * <p>
	 * Title: getPageResult_sql_List
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param count_sql
	 * @param sql
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @author cuidd
	 * @date 2013-5-29
	 * @return HashMap
	 * @version 1.0
	 */

	public HashMap getPageResult_sql_List(String count_sql, String sql,
			int currentPage, int pageSize);
}
