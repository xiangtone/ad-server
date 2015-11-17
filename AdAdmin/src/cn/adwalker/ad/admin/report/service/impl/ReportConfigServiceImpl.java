package cn.adwalker.ad.admin.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.report.dao.IReportConfigDao;
import cn.adwalker.model.report.domain.TStatReportItem;
import cn.adwalker.model.report.domain.TStatReportTable;
import cn.adwalker.model.sys.dao.ISysResourceDao;
import cn.adwalker.ad.admin.report.bean.ReportItemBean;
import cn.adwalker.ad.admin.report.service.IReportConfigService;

@Service(value = "reportManageService")
public class ReportConfigServiceImpl implements IReportConfigService {

	@Resource
	IReportConfigDao reportConfigDao;

	@Resource
	ISysResourceDao sysResourceDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<TStatReportTable> findAll(IPageInfo pageInfo) throws Exception {
		return (List<TStatReportTable>) reportConfigDao.findByPage("*", "t_stat_report_table","order by createtime desc", TStatReportTable.class, pageInfo);
	}

	

	@Override
	public void insertTable(TStatReportTable data) {
		reportConfigDao.insertTable(data);
	}

	@Override
	public TStatReportTable getReportTableForEdit(Long id) {
		return reportConfigDao.getReportTableForEdit(id);
	}

	@Override
	public void updateTable(TStatReportTable table) {

		reportConfigDao.updateTable(table);
	}

	@Override
	public List getReportItemsForList(Long id) {
		return reportConfigDao.getReportItemsForList(id);
	}

	@Override
	public void insertItem(TStatReportItem table) {
		reportConfigDao.insertItem(table);
	}

	@Override
	public TStatReportItem getReportItemForEdit(Long id) {
		return reportConfigDao.getReportItemForEdit(id);
	}

	@Override
	public void updateItem(TStatReportItem data) {
		reportConfigDao.updateItem(data);
	}

	@Override
	public void addSysPurview() {
		TStatReportTable table = reportConfigDao.findTStatReportTable();
		sysResourceDao.addSysPurview(table);
		Long sysPurvieId = sysResourceDao.findByTableId(table.getId());
		reportConfigDao.insertRolePurview(sysPurvieId);
	}

	@Override
	public void updateSysPurview(TStatReportTable table) {
		sysResourceDao.updateSysPurview(table);
	}

	@Override
	public TStatReportTable getReportTable(Long id) {
		return reportConfigDao.getReportTable(id);
	}

	/**
	 * 根据报表id获取报表下面的items
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ArrayList getReportItems(Long id) {
		List l = reportConfigDao.getReportItems(id);
		ArrayList result = new ArrayList();
		if (l != null && l.size() >= 1) {
			for (int i = 0; i < l.size(); i++) {
				TStatReportItem item = (TStatReportItem) l.get(i);
				ReportItemBean bean = new ReportItemBean();
				bean.setItem(item);
				bean.setInput("");
				result.add(bean);
			}
		}
		return result;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: executeQuery
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s
	 * @return
	 * @see cn.adwalker.ad.admin.report.service.IReportConfigService#executeQuery(java.lang.String)
	 */
	@Override
	public List executeQuery(String s) {

		return reportConfigDao.executeQuery(s);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: executeQueryList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param total_sql
	 * @return
	 * @see cn.adwalker.ad.admin.report.service.IReportConfigService#executeQueryList(java.lang.String)
	 */
	@Override
	public List executeQueryList(String total_sql) {
		// TODO Auto-generated method stub
		return reportConfigDao.executeQueryList(total_sql);
	}

	/**
	 * (non-Javadoc)
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
	 * @see cn.adwalker.ad.admin.report.service.IReportConfigService#getPageResult_sql_List(java.lang.String,
	 *      java.lang.String, int, int)
	 */
	@Override
	public HashMap getPageResult_sql_List(String count_sql, String sql,
			int currentPage, int pageSize) {
		return reportConfigDao.getPageResult_sql_List(count_sql, sql,
				currentPage, pageSize);
	}

}
