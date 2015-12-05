package cn.adwalker.model.report.dao;

import java.util.HashMap;
import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.report.domain.TStatReportItem;
import cn.adwalker.model.report.domain.TStatReportTable;

public interface IReportConfigDao extends IBaseDao {

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
	void insertTable(TStatReportTable data);

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
	TStatReportTable getReportTableForEdit(Long id);

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
	void updateTable(TStatReportTable table);

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
	List getReportItemsForList(Long id);

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
	void insertItem(TStatReportItem table);

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
	TStatReportItem getReportItemForEdit(Long id);

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
	void updateItem(TStatReportItem data);

	void insertRolePurview(Long sysPurvieId);

	TStatReportTable findTStatReportTable();

	TStatReportTable getReportTable();

	TStatReportTable getReportTable(Long id);

	List executeQuery(String s);

	List executeQueryList(String total_sql);

	List getReportItems(Long id);

	HashMap getPageResult_sql_List(String count_sql, String sql,
			int currentPage, int pageSize);
}
