package cn.adwalker.ad.admin.report.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.report.domain.TStatReportItem;
import cn.adwalker.model.report.domain.TStatReportTable;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.report.service.IReportConfigService;
import cn.adwalker.ad.admin.report.util.CheakConvertData;

@Controller
public class ReportManageControlller extends AbstractControllerParent {
	/**
	 * 日志工具
	 */
	private Logger logger = Logger.getLogger(ReportManageControlller.class);

	/** 服务接口 */
	@Resource
	private IReportConfigService iReportManageService;

	/** 管理员实体 */
	private SysUserVo manageUser;

	/**
	 * 
	 * <p>
	 * Title: reportManage
	 * </p>
	 * <p>
	 * Description:报表配置界面
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!reportManage.do")
	public String reportManage(HttpSession session, HttpServletRequest request) {
		logger.info("/manage!reportManage.do");

		List<TStatReportTable> data = new ArrayList<TStatReportTable>();
		IPageInfo pageInfo = new SetPage(request);
		try {
			data = iReportManageService.findAll(pageInfo);
			request.setAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("data", data);
		return "stat/table/showTableList";
	}

	/**
	 * 
	 * <p>
	 * Title: showAddReportStat
	 * </p>
	 * <p>
	 * Description:跳转添加报表配置
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!showAddTable.do")
	public String showAddReportStat(HttpSession session,
			HttpServletRequest request) {
		return "stat/table/showAddTable";
	}

	/**
	 * 
	 * <p>
	 * Title: showEditTable
	 * </p>
	 * <p>
	 * Description:跳转编辑报表配置
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!showEditTable.do")
	public String showEditTable(HttpSession session,
			HttpServletRequest request, Long tableid) {

		if (tableid == null) {
			// 返回出错，无法获取报表ID
			request.setAttribute("error_message", "请求编辑一个不存在的报表");
			return "stat/table/showTableList";
		}
		TStatReportTable data = iReportManageService
				.getReportTableForEdit(tableid);
		request.setAttribute("data", data);
		return "stat/table/showEditTable";
	}

	/**
	 * 
	 * <p>
	 * Title: addReportStat
	 * </p>
	 * <p>
	 * Description:添加保存报表配置
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param table
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 * @throws IOException
	 */
	@RequestMapping("/manage!addTable.do")
	public String addReportStat(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			TStatReportTable table) throws Exception {
		ResultVo vo = null;
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		if (!checkTable(request, table)) {
			vo = new ResultErrorVo("统计报表提交的数据是否合法");
		}
		try {
			table.setCOperator(manageUser.getRealName());
			Date date = new Date();
			table.setCreatetime(date);
			iReportManageService.insertTable(table);
			vo = new ResultSuccessVo();

		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: editTable
	 * </p>
	 * <p>
	 * Description:编辑报表配置
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param table
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!editTable.do")
	public String editTable(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, TStatReportTable table)
			throws Exception {
		ResultVo vo = null;
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		if (!checkTable(request, table)) {
			vo = new ResultErrorVo("统计报表提交的数据是否合法");
		}
		try {
			table.setCOperator(manageUser.getRealName());
			Date date = new Date();
			table.setCreatetime(date);
			iReportManageService.updateTable(table);
			// iReportManageService.updateSysPurview(table);
			vo = new ResultSuccessVo();

		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: showItemList
	 * </p>
	 * <p>
	 * Description:查看ItemList
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param table
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!showItemList.do")
	public String showItemList(HttpSession session, HttpServletRequest request,
			TStatReportTable table, Long tableid) {
		if (tableid != null) {
			Long Id = new Long(tableid);

			List data = iReportManageService.getReportItemsForList(Id);
			request.setAttribute("data", data);
			request.setAttribute("tableid", tableid);
		} else {
			// 返回出错，无法获取报表ID
			request.setAttribute("error_message", "请求显示一个不存在的统计报表的items");
		}

		return "stat/item/showItemList";
	}

	/**
	 * 
	 * <p>
	 * Title: showAddItem
	 * </p>
	 * <p>
	 * Description:显示添加item
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param table
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!showAddItem.do")
	public String showAddItem(HttpSession session, HttpServletRequest request,
			TStatReportTable table) {
		String tableid = request.getParameter("tableid");
		request.setAttribute("tableid", tableid);
		return "stat/item/showAddItem";

	}

	/**
	 * 
	 * <p>
	 * Title: addItem
	 * </p>
	 * <p>
	 * Description:添加item
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param table
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 * @throws IOException
	 */
	@RequestMapping("/manage!addItem.do")
	public String addItem(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, TStatReportItem table)
			throws IOException {
		ResultVo vo = null;

		// 如果填入数据有误
		if (!checkItem(request, table)) {
			// return mapping.findForward("showAddItem");
		}
		// 添加用户和添加时间
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		table.setCOperator(manageUser.getRealName());
		Date date = new Date();
		table.setCreatetime(date);

		iReportManageService.insertItem(table);

		List result = iReportManageService.getReportItemsForList(table
				.getTableId());
		request.setAttribute("data", result);
		request.setAttribute("tableid", table.getTableId());
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;

	}

	/**
	 * 
	 * <p>
	 * Title: showEditItem
	 * </p>
	 * <p>
	 * Description:显示编辑item
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param table
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!showEditItem.do")
	public String showEditItem(HttpSession session, HttpServletRequest request,
			TStatReportItem table, Long itemid) {
		String tableid = request.getParameter("tableid");
		if (itemid == null) {
			// 返回出错，无法获取报表ID
			request.setAttribute("error_message", "请求编辑一个不存在的统计报表item");
			// return mapping.findForward("error");
			return "stat/item/showItemList";
		}
		Long Id = new Long(itemid);
		TStatReportItem data = iReportManageService.getReportItemForEdit(Id);
		request.setAttribute("data", data);
		request.setAttribute("itemid", itemid);
		request.setAttribute("tableid", tableid);
		// return mapping.findForward("showEditItem");

		// return super.jumpPage(request, "操作成功！", "/manage!reportManage.do",
		// EJumpType.SUCCESS.getValue());
		return "stat/item/showEditItem";

	}

	/**
	 * 
	 * <p>
	 * Title: editItem
	 * </p>
	 * <p>
	 * Description:编辑item
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param table
	 * @return
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return String
	 * @version 1.0
	 * @throws Exception
	 */
	@RequestMapping("/manage!editItem.do")
	public String editItem(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, TStatReportItem table, Long itemid)
			throws Exception {
		ResultVo vo = null;
		if (itemid == null) {
			// 返回出错，无法获取报表ID
			request.setAttribute("error_message", "请求提交编辑一个不存在的报表item");
			return "stat/item/showItemList";
		}
		Long Id = new Long(itemid);
		TStatReportItem data = iReportManageService.getReportItemForEdit(Id);
		// 如果填入数据有误
		if (!checkItem(request, data)) {
			vo = new ResultErrorVo("填入数据有误");
		}
		iReportManageService.updateItem(data);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 判断统计报表提交的数据是否合法，并填写入bean
	 * 
	 * @param request
	 * @param table
	 * @return
	 */
	private boolean checkTable(HttpServletRequest request,
			TStatReportTable table) {
		// 修改用户和修改时间
		// table.setUOperator(tuser.getLoginName());
		Date date = new Date();
		table.setUpdatetime(date);

		boolean is = true;
		HashMap wrong_message = new HashMap();
		String jspName = request.getParameter("jspName");
		String menuName = request.getParameter("menuName");
		String sql = request.getParameter("sql");
		String pageSize = request.getParameter("pageSize");
		String status = request.getParameter("status");
		String role = request.getParameter("role");
		String note = request.getParameter("note");
		String countsql = request.getParameter("countsql");
		String totalsql = request.getParameter("totalsql");
		String defaultStart = request.getParameter("defaultStart");
		String defaultEnd = request.getParameter("defaultEnd");
		// 页面显示名称不能为空
		if (StringUtils.isEmpty(jspName)) {
			wrong_message.put("jspName_error", "页面显示名称不能为空!");
			is = false;
		} else {
			table.setJspName(jspName);
		}
		// 菜单名称可以为空，此字段未用
		if (!StringUtils.isEmpty(menuName)) {
			table.setMenuName(menuName);
		} else {
			table.setMenuName("");
		}
		// 数据源SQL不能为空
		if (StringUtils.isEmpty(sql)) {
			wrong_message.put("sql_error", "SQL语句不能为空!");
			is = false;
		} else {
			table.setOriginSql(sql);
		}
		// 页面大小不能为空，且必须为数字
		if (!NumberUtils.isNumber(pageSize)) {
			wrong_message.put("pageSize_error", "分页大小不能为空，且必须为数字!");
			is = false;
		} else {

			Integer i = new Integer(pageSize);
			// logger.error("pagesize1:"+i);
			table.setPageSize(i);
		}
		// 状态不能为空，且必须为数字
		if (!NumberUtils.isNumber(status)) {
			wrong_message.put("status_error", "状态只能为1或者9!");
			is = false;
		} else {
			table.setStatus(status);
		}
		// 查看角色不能为空
		if (StringUtils.isEmpty(role)) {
			wrong_message.put("role_error", "必须填入表的查看角色，形如1,5,7,8");
			is = false;
		} else {
			table.setRole(role);
		}
		// 注释可以为空
		if (!StringUtils.isEmpty(note)) {
			table.setNote(note);
		} else {
			table.setNote("");
		}
		// 总和SQL可以为空
		if (!StringUtils.isEmpty(totalsql)) {
			table.setTotalSql(totalsql);
		} else {
			table.setTotalSql("");
		}
		// 分页SQL可以为空
		if (!StringUtils.isEmpty(countsql)) {
			table.setCountSql(countsql);
		} else {
			table.setCountSql("");
		}
		// 默认起始日期可以为空，否则必须为数字
		if (StringUtils.isEmpty(defaultStart)) {
			table.setDefaultStart(0);
		} else if (CheakConvertData.isIntegerString(defaultStart)) {
			table.setDefaultStart(new Integer(defaultStart));
		} else {
			is = false;
			wrong_message.put("defaultStart_error", "起始日期可以为空，否则必须为数字!");
		}
		// 默认结束日期可以为空，否则必须为数字
		if (StringUtils.isEmpty(defaultEnd)) {
			table.setDefaultEnd(0);
		} else if (CheakConvertData.isIntegerString(defaultEnd)) {
			table.setDefaultEnd(new Integer(defaultEnd));
		} else {
			is = false;
			wrong_message.put("defaultEnd_error", "结束日期可以为空，否则必须为数字!");
		}

		request.setAttribute("data", table);
		request.setAttribute("wrong_message", wrong_message);
		return is;
	}

	/**
	 * 判断统计报表提交的数据是否合法，并填写入bean
	 * 
	 * @param request
	 * @param table
	 * @return
	 */
	private boolean checkItem(HttpServletRequest request, TStatReportItem table) {
		// 修改用户和修改时间
		HttpSession session = request.getSession();
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		table.setUOpeartor(manageUser.getRealName());
		Date date = new Date();
		table.setUpdatetime(date);

		boolean is = true;
		HashMap wrong_message = new HashMap();
		String tableId = request.getParameter("tableid");
		String itemid = request.getParameter("itemid");
		String sqlName = request.getParameter("sqlName");
		String format = request.getParameter("format");
		String jspName = request.getParameter("jspName");
		String status = request.getParameter("status");
		String sequence = request.getParameter("sequence");
		String isSearch = request.getParameter("isSearch");
		String jspType = request.getParameter("jspType");
		String sqlType = request.getParameter("sqlType");
		String originSql = request.getParameter("originSql");
		String isLink = request.getParameter("isLink");
		String link = request.getParameter("link");
		// 如果tableid不为空，则填入tableid
		if (!StringUtils.isEmpty(tableId)) {
			Long id = new Long(tableId);
			table.setTableId(id);
		} else {
			wrong_message.put("error", "错误的请求，tableid为空!");
		}
		// SQL名称不能为空
		if (StringUtils.isEmpty(sqlName)) {
			wrong_message.put("sqlName_error", "SQL名称不能为空!");
			is = false;
		} else {
			table.setSqlName(sqlName);
		}
		// 页面显示名称不能为空
		if (StringUtils.isEmpty(jspName)) {
			wrong_message.put("jspName_error", "页面显示名称不能为空!");
			is = false;
		} else {
			table.setJspName(jspName);
		}
		// 状态不能为空
		if (!NumberUtils.isNumber(status)) {
			wrong_message.put("origin_error", "状态不能为空!");
			is = false;
		} else {
			table.setStatus(status);
		}

		// 排序值不能为空，且必须为数字
		if (!NumberUtils.isNumber(sequence)) {
			wrong_message.put("sequence_error", "排序不能为空，且必须为数字!");
			is = false;
		} else {
			Integer i = new Integer(sequence);
			table.setSequence(i);
		}
		// 是否搜索不能为空
		if (StringUtils.isEmpty(isSearch)) {
			wrong_message.put("isSearch_error", "是否搜索不能为空!");
			is = false;
		} else {
			table.setIsSearch(isSearch);
		}
		// JSP类型
		if (!StringUtils.isEmpty(jspType)) {
			table.setJspType(jspType);
		} else {
			table.setJspType("");
		}
		// SQL类型
		if (!StringUtils.isEmpty(sqlType)) {
			table.setSqlType(sqlType);
		} else {
			table.setSqlType("");
		}
		// 数据源SQL
		if (!StringUtils.isEmpty(originSql)) {
			table.setOriginSql(originSql);
		} else {
			table.setOriginSql("");
		}
		// 是否链接不能为空
		if (StringUtils.isEmpty(isLink)) {
			wrong_message.put("isLink_error", "是否链接不能为空!");
			is = false;
		} else {
			table.setIsLink(isLink);
		}
		// 链接地址
		if (!StringUtils.isEmpty(link)) {
			table.setLink(link);
		} else {
			table.setLink("");
		}
		// 样式不能为空
		if (StringUtils.isEmpty(format)) {
			wrong_message.put("format_error", "样式不能为空!");
			is = false;
		} else {
			table.setFormat(format);
		}
		// 如果itemid存在，则填入
		if (!StringUtils.isEmpty(itemid)) {
			Long iid = new Long(itemid);
			table.setId(iid);
		}
		request.setAttribute("tableid", tableId);
		request.setAttribute("itemid", itemid);
		request.setAttribute("data", table);
		request.setAttribute("wrong_message", wrong_message);
		return is;
	}
}
