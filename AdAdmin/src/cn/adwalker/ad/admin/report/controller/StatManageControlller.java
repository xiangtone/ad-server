package cn.adwalker.ad.admin.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.model.report.domain.TStatReportTable;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.report.bean.ReportItemBean;
import cn.adwalker.ad.admin.report.bean.ReportTableBean;
import cn.adwalker.ad.admin.report.service.IReportConfigService;
import cn.adwalker.core.util.DateUtil;

@Controller
public class StatManageControlller {

	/**
	 * 日志工具
	 */
	private Logger logger = Logger.getLogger(StatManageControlller.class);

	/** 服务接口 */
	@Resource
	private IReportConfigService iReportManageService;
	
	
	
	/** 管理员实体 */
	private SysUserVo manageUser;
	/** 
	* <p>Title: statManage</p>
	* <p>Description:主统计方法</p>
	* @param session
	* @param request
	* @return
	* @author lichuang
	* @date 2013-5-30
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!statManage.do")
	public String statManage(HttpSession session, HttpServletRequest request,Long id) {

		// try {
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// logger.error("request转码出错!" + e);
		// }
		// String requestURL = request.getRequestURL().toString();
		// id="1003";
		if (id==null) {
			// 返回出错，无法获取报表ID
			request.setAttribute("error_message", "非法统计要求！报表ID为空或非数字");
			return "stat/report/showList";
			// return mapping.findForward("error");
		}
		Long Id = new Long(id);
		// StatService s = new StatService();
		// 检验用户是否有访问权限
		// TUser user = this.getUser(request);
		// manageUser = (SysUserVo) session.getAttribute("manageUser");
		// TStatReportTable table = iReportManageService.getReportTable(Id);
		// if (!this.isAllow(user, table)) {
		// request.setAttribute("error_message", "您没有访问权限或其他错误！");
		// return "stat/report/showList";
		// return mapping.findForward("error");
		// }
		HashMap hash = this.getReplace(request, session);

		ReportTableBean bean = this
				.getReportTableBean(Id, request, hash, false);
		if (iReportManageService.getReportTable(Id) == null
				|| !iReportManageService.getReportTable(Id).getStatus()
						.equals("1")) {
			// 返回出错，无法获取报表ID
			request.setAttribute("error_message", "非法统计要求！请求不存在或已弃用的报表!");
			return "stat/report/showList";
			// return mapping.findForward("error");
		}

		request.setAttribute("bean", bean);
		// return mapping.findForward("showList");

		return "stat/report/showList";
	}

	private ReportTableBean getReportTableBean(Long id,
			HttpServletRequest request, HashMap hash2, boolean isExcel) {

		ReportTableBean bean = new ReportTableBean();
		ArrayList list = iReportManageService.getReportItems(id);
		TStatReportTable table = iReportManageService.getReportTable(id);
		if (list == null || table == null) {
			return null;
		}
		int index = 0;
		int flag = 0;
		for (int i = 0; i < list.size(); i++) {
			ReportItemBean rb = (ReportItemBean) list.get(i);
			if ("y".equals(rb.getItem().getIsSearch())) {
				index++;
				flag = i;
			}
		}

		if ((index + 2) % 3 > 0) {
			int r = (index + 2) % 3;
			int s = (3 - r) * 2 + 1;
			ReportItemBean rb = (ReportItemBean) list.get(flag);
			rb.setColspan(String.valueOf(s));
		}

		bean.setItems(list);
		bean.setTable(table);
		int pageSize = bean.getTable().getPageSize();
		int defaultStart = 0;
		int defaultEnd = 0;
		if (bean.getTable().getDefaultStart() != null) {
			defaultStart = bean.getTable().getDefaultStart().intValue();
		}
		if (bean.getTable().getDefaultEnd() != null) {
			defaultEnd = bean.getTable().getDefaultEnd().intValue();
		}
		// 存储下拉的附加数据
		HashMap select = new HashMap();
		// 暂存需要替换的sql部分
		HashMap replace = new HashMap();
		// 对公共的参数进行替换，如用户的ID
		// Map
		// params=request.getParameterMap();//this.getParameter(request.getQueryString());
		if (hash2 != null) {
			Object[] os = hash2.keySet().toArray();
			for (int i = 0; i < os.length; i++) {
				Object key = os[i];
				Object value = hash2.get(key);
				String v = " and " + key.toString() + " = " + value.toString();
				// logger.info("key:"+key.toString()+";value:"+value.toString());
				replace.put(key, v);
			}
		}
		HashMap input = new HashMap();
		// 获取统计起始时间
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String sid = request.getParameter("t.SID");
		String order = request.getParameter("order");
		if (StringUtils.isEmpty(beginTime)) {
			// 如果起始日期为空，则取当前日期
			// beginTime = AdDateUtil.getNowStr("yyyy-MM-dd");
			beginTime = DateUtil.getADay("yyyy-MM-dd", defaultStart);
		}
		if (StringUtils.isEmpty(endTime)) {
			// endTime = AdDateUtil.getNowStr("yyyy-MM-dd");
			endTime = DateUtil.getADay("yyyy-MM-dd", defaultEnd);
		}
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// 替换换行符
		replace.put("\r\n", " ");
		// 将源SQL语句中的日期进行替换
		replace.put("beginTime", beginTime);
		replace.put("endTime", endTime);

		// 保存用户输入的日期和排序字段
		input.put("beginTime", beginTime);
		input.put("endTime", endTime);
		input.put("order", order);

		// StatDao dao = new StatDao();
		if (list != null) {
			String sql = bean.getTable().getOriginSql();
			for (int i = 0; i < list.size(); i++) {
				ReportItemBean item = (ReportItemBean) list.get(i);
				// 获取原始的sql语句

				// 是否用于搜索项，具体搜索类型，s为select，i为input
				String isSearch = item.getItem().getIsSearch();
				String type = item.getItem().getJspType();
				String jspName = item.getItem().getJspName();
				if (isSearch != null && isSearch.equals("y")) {
					// 准备参数替换
					String param = request.getParameter(item.getItem()
							.getSqlName());
					String tt = request.getParameter("tt");
					if (param != null && !param.equals("")) {
						String code = "";
						try {
							code = URLDecoder.decode(param, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// logger.error("转码错误" + e);
						}
						item.setInput(code);
						item.setCodeInput(code);
						input.put(item.getItem().getSqlName(), param);
						String sqlType = item.getItem().getSqlType();
						if (sqlType != null && sqlType.equals("like")) {
							replace.put(item.getItem().getSqlName(), " and "
									+ item.getItem().getSqlName() + " like '%"
									+ code + "%' ");
						} else {
							replace.put(item.getItem().getSqlName(), " and "
									+ item.getItem().getSqlName() + " = '"
									+ code + "' ");
						}
					} else {
						replace.put(item.getItem().getSqlName(), "  ");
					}
					// 如果是页面是select，则需要读取显示的下拉数据
					if (type != null && type.equals("s")) {
						String s = item.getItem().getOriginSql();
						// logger.error("ss:"+s);
						List l = iReportManageService.executeQuery(s);
						item.setSelect(l);
						// select.put(item.getItem().getId(), l);
					}// if
				}// if
			}// for
				// 对SQL语句进行参数替换
			sql = this.replaceSQL(sql, replace);
			// 获取排序字段
			if (StringUtils.isBlank(order)) {
				sql += "order by static_date desc";
			} else {
				sql += "order by " + order.replace("|", " ");
			}
			// if(2==id && null != sid && !"".equals(sid)){
			// sql += " and t.sid='" + sid + "'";
			// }
			// countsql为计算分页的sql语句，totalsql为计算总和的SQL语句
			String countsql = bean.getTable().getCountSql();
			String totalsql = bean.getTable().getTotalSql();
			String count_sql = "select count(*) from ( " + sql + " )temp";
			String total_sql = "";
			if (countsql != null && !countsql.equals("")) {
				count_sql = this.replaceSQL(countsql, replace);
			}
			if (totalsql != null && !totalsql.equals("")) {
				total_sql = this.replaceSQL(totalsql, replace);
				List l = iReportManageService.executeQueryList(total_sql);
				if (l != null && l.size() >= 1) {
					Object o = l.get(0);
					select.put("total", o);
				}
			}
			// 用于页面显示的数据
			HashMap hash = new HashMap();
			if (!isExcel) {
				hash = iReportManageService.getPageResult_sql_List(count_sql,
						sql, currentPage, pageSize);
				select.put("data", hash);
			} else {
				// 用于导出EXCEL的数据
				select.put("data", iReportManageService.executeQueryList(sql));
			}
			select.put("input", input);
		}
		bean.setData(select);
		return bean;

	}

	/**
	 * 对SQL语句中的 遗留字进行替换，从而使SQL语句和数据动态联系起来
	 * 
	 * @param originSQL
	 * @param params
	 * @return
	 */
	private String replaceSQL(String originSQL, HashMap params) {
		if (params != null) {
			Object[] os = params.keySet().toArray();
			for (int i = 0; i < os.length; i++) {
				Object key = os[i];
				String value = params.get(key).toString();
				originSQL = originSQL.replaceAll(key.toString(), value);
			}
		}
		return originSQL;
	}

	/**
	 * <p>
	 * Title: exportCSV
	 * </p>
	 * <p>
	 * Description:导出CSV
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @author lichuang
	 * @date 2013-5-30
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("/manage!exportCSV.do")
	public void exportCSV(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		// ExcelExportServiceImpl oles = new ExcelExportServiceImpl();
		// try {
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// logger.error("request转码出错!" + e);
		// }

		String id = request.getParameter("id");
		// id="1003";
		Long Id = new Long(id);
		// StatService s = new StatService();
		// 检验用户是否有访问权限
		// TUser user = this.getUser(request);
		// TStatReportTable table = s.getReportTable(Id);
		// if (!this.isAllow(user, table)) {
		// request.setAttribute("error_message", "您没有访问权限或其他错误！");
		// return;
		// }
		HashMap hash = this.getReplace(request, session);
		ReportTableBean bean = this.getReportTableBean(Id, request, hash, true);
		List list = bean.getItems();
		// List totalList = (List) bean.getData().get("total");
		int SIZE = list.size();
		String[] titles = new String[SIZE];
		String[] total = new String[SIZE];
		// 填充表头数据
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ReportItemBean item = (ReportItemBean) list.get(i);
				titles[i] = item.getItem().getJspName();
			}
		}
		// 填充总和数据
		// if (totalList != null && totalList.size() >= 1) {
		// for (int i = 0; i < totalList.size(); i++) {
		// total[i] = (String) totalList.get(i);
		// }
		// }
		List content = (List) bean.getData().get("data");
		String fileName = bean.getTable().getMenuName() + "统计报表.xls";
		String workbookName = bean.getTable().getJspName();
		this.exportXlsFile(response, titles, total, content,
				fileName, workbookName);

		// return "";
	}
	
	
	 

	/**
	* <p>Title: exportXlsFile</p>
	* <p>Description:TODO</p>
	* @param response
	* @param titles
	* @param total
	* @param content
	* @param fileName
	* @param workbookName
	* @author cuidd
	* @date 2013-6-27
	* @return void
	* @version 1.0
	*/
	
	private void exportXlsFile(HttpServletResponse response, String[] titles,
			String[] total, List content, String fileName, String workbookName) {
		  if (fileName == null || fileName.equals("")) {
	            fileName = "统计报表";
	        }
	        if (workbookName == null || workbookName.equals("")) {
	            workbookName = "报表";
	        }
	        try {
	            fileName = new String((fileName).getBytes(), "ISO-8859-1");

	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            logger.error("转码出错!" + e);
	        }
	        response.setHeader("Content-Disposition", "attachment;   filename="
	                + fileName);
	        response.setContentType("APPLICATION/OCTET-STREAM;   charset=UTF-8");
	        try {
	            ServletOutputStream out = response.getOutputStream();

	            writeExcel(out, titles, total, content, workbookName);

	            out.close();
	        } catch (IOException e) {
	            logger.error("导出Excel文件时出错", e);
	        }
		
		
		// TODO Auto-generated method stub
		
	}
	
	 /**
     * @param total
     * @param startDate
     * @param endDate
     * @return List<List<Object>>这种形式的结果集
     */
    private String writeExcel(OutputStream os, String[] title, String[] total,
            List dataList, String workbookName) {

        WritableWorkbook wbook = null;

        // 设置头部字体格式
        WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,

        WritableFont.BOLD, false,

        jxl.format.UnderlineStyle.NO_UNDERLINE,

        jxl.format.Colour.BLACK);

        WritableCellFormat titleFormat = new WritableCellFormat(wfont);

        // 创建一个excel文件
        try {
            wbook = Workbook.createWorkbook(os);

            WritableSheet wsheet = wbook.createSheet(workbookName, 0); // 工作表名称

            // 添加头部名称
            for (int i = 0; i < title.length; i++) {
                Label excelTitle = new Label(i, 0, title[i], titleFormat);

                wsheet.addCell(excelTitle);
            }
            // 填充总和数据
            int dataBegin = 1;
            if (total != null && total.length >= 1) {
                dataBegin = 2;
                for (int i = 0; i < total.length; i++) {
                    Label excelTitle = new Label(i, 1, total[i]);

                    wsheet.addCell(excelTitle);
                }
            }
            

            // 创建内容
            int len = dataList.size();
            for (int i = 0; i < len; i++) {
            	Map<String, Object> tmpList = (Map<String, Object>) dataList.get(i);
            	
            	Set<String> set = tmpList.keySet();
            	int tag =0;
            	for (String key : set) {
            	String temp = "";	
            	temp =  ""+tmpList.get(key);
//               System.out.println("键:"+key+"  值:"+tmpList.get(key)+"tag"+tag);
               Label label = null;
               label = new Label(tag, i + dataBegin, ""+tmpList.get(key));
//             key,i + dataBegin,tmpList.get(key)
               WritableCellFormat cellFormat=new WritableCellFormat();
               cellFormat.setAlignment(jxl.format.Alignment.LEFT);
               cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
               cellFormat.setWrap(true);
               label.setCellFormat(cellFormat);
               wsheet.addCell(label);
            	tag= tag+1;
            	}
            	
//                for (int j = 0; j < tmpList.size(); j++) {
//                    Object o = tmpList.get(j);
//                    String temp = "";
//                    if (o != null) {
//                        temp = o.toString();
//                    }
//                    Label label = null;
//                    label = new Label(j, i + dataBegin, temp);
//                    WritableCellFormat cellFormat=new WritableCellFormat();
//                    cellFormat.setAlignment(jxl.format.Alignment.LEFT);
//                    cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//                    cellFormat.setWrap(true);
//                    label.setCellFormat(cellFormat);
//                    
//                    wsheet.addCell(label);
//
//                }
            }

            wbook.write();
            wbook.close();

            os.close();
        } catch (Exception e) {
            logger.error("创建Excel时出错", e);
        }

        return null;
    }

	/**
	 * 所有需要对SQL语句中单独替换的数据，从此函数中提取进入HashMap 如用户的id，或者request中的其他参数，使用大写
	 * 
	 * @param request
	 * @return
	 */
	private HashMap getReplace(HttpServletRequest request, HttpSession session) {

		HashMap hash = new HashMap();
		/*
		 * Map map = request.getParameterMap(); Object[] os =
		 * map.keySet().toArray(); for(Object o : os){
		 * hash.put(o.toString().toUpperCase(),
		 * request.getParameter(o.toString())); }
		 */
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		// TUser user = this.getUser(request);
		if (manageUser != null) {
			hash.put("USERID", manageUser.getRoleId());
			// logger.info("userid:"+user.getId());
		}

		return hash;

	}
}
