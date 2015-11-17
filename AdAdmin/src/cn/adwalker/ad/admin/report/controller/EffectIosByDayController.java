package cn.adwalker.ad.admin.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.report.bean.EffectIosByDayQuery;
import cn.adwalker.ad.admin.report.service.IEffectIosByDayService;
import cn.adwalker.ad.admin.report.service.IReportIOSChannelService;
import cn.adwalker.ad.admin.report.vo.EffectIosByDay;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.BeanUtils;
import cn.adwalker.core.util.DateUtil;

/**
 * 
 * <p>
 * Title: AdByHourController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-19
 */
@Controller
public class EffectIosByDayController extends AbstractControllerParent {
	private static final Logger logger = LoggerFactory
			.getLogger(EffectIosByDayController.class);

	@Resource
	private IEffectIosByDayService service;

	@Resource
	private IReportIOSChannelService reService;

	/**
	 * 
	 * <p>
	 * Title: list
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param bean
	 * @param model
	 * @return
	 * @author cuidd
	 * @date 2013-9-17
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("report/effectIosByDay.do")
	public String list(HttpSession session, HttpServletRequest request,
			EffectIosByDayQuery bean, Map<String, Object> model) {
		try {
			if (bean == null) {
				bean = new EffectIosByDayQuery();
			}
			if (StringUtils.isEmpty(bean.getBeginTime())) {
				bean.setBeginTime(DateUtil.formatDate(DateUtil.addDays(
						new Date(), -1)));
			}
			if (StringUtils.isEmpty(bean.getEndTime())) {
				bean.setEndTime(DateUtil.formatDate(DateUtil.addDays(
						new Date(), -1)));
			}
			IPageInfo pageInfo = new SetPage(request, 20);
			List<EffectIosByDay> list = this.service.findByPage(bean, pageInfo);
			EffectIosByDay total = this.service.findTotal(bean);
			List<Map<String, Object>> channelList = reService.getChannelList();
			List<Map<String, Object>> adList = reService.getAdList();
			
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			Iterator it = adList.iterator(); 
			while(it.hasNext()) {   
			    Map userMap = (Map) it.next();   
			    sb.append("\""+userMap.get("id")+"\":\""+userMap.get("ad_name")+"\",");

			} 
			sb.append("}");
			String adId = request.getParameter("ad_id");
			String countryName = request.getParameter("country");
			
			model.put("pageInfo", pageInfo);
			model.put("bean", bean);
			model.put("list", list);
			model.put("total", total);
			model.put("adId", adId);
			model.put("countryName", countryName);
			request.setAttribute("channelList", channelList);
			request.setAttribute("adList", adList);
			request.setAttribute("data", sb.toString());
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return "report/effectIosByDay";

	}

	/**
	 * 
	 * <p>
	 * Title: exportCSV
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param session
	 * @param response
	 * @param bean
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-9-17
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("report/exportAdByDayCSV.do")
	public void exportCSV(HttpSession session, HttpServletResponse response,
			EffectIosByDayQuery bean) throws Exception {
		if (StringUtils.isEmpty(bean.getBeginTime())) {
			bean.setBeginTime(DateUtil.formatDate(DateUtil.addDays(new Date(),
					-1)));
		}
		if (StringUtils.isEmpty(bean.getEndTime())) {
			bean.setEndTime(DateUtil.formatDate(DateUtil
					.addDays(new Date(), -1)));
		}
		String fileName = "按天统计----广告统计报表.xls";
		String workbookName = "广告按天统计";
		String title[] = { "日期", "广告主ID", "广告ID", "广告名称", "广告样式", "平台类型",
				"广告展示", "广告点击", "广告下载", "广告激活", "	费用支出(元)", "点击转化率", "下载转化率",
				"激活转化率" };
		List<Object> list = service.findAll(bean);
		String clumes[] = { "static_date", "adv_id", "id", "placement_name",
				"fname", "os", "adpv", "click", "download", "activate", "cost",
				"ctrc", "ctrd", "ctra" };
		this.exportXlsFile(response, title, null, list, clumes, fileName,
				workbookName);
	}

	/**
	 * <p>
	 * Title: exportXlsFile
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
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
	 * @throws UnsupportedEncodingException
	 */

	private void exportXlsFile(HttpServletResponse response, String[] titles,
			String[] total, List<Object> content, String clumes[],
			String fileName, String workbookName)
			throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(fileName)) {
			fileName = "统计报表";
		}
		if (StringUtils.isEmpty(workbookName)) {
			workbookName = "报表";
		}
		fileName = new String((fileName).getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
		try {
			ServletOutputStream out = response.getOutputStream();

			writeExcel(out, titles, total, content, clumes, workbookName);

			out.close();
		} catch (IOException e) {
			logger.error("导出Excel文件时出错", e);
		}

	}

	/**
	 * @param total
	 * @param startDate
	 * @param endDate
	 * @return List<List<Object>>这种形式的结果集
	 */
	private String writeExcel(OutputStream os, String[] title, String[] total,
			List<Object> list, String clums[], String workbookName) {
		WritableWorkbook wbook = null;
		// 设置头部字体格式
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false,
				jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
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
			int i = 0;
			for (Object o : list) {
				int tag = 0;
				for (String key : clums) {
					Label label = null;
					label = new Label(tag, i + dataBegin, ""
							+ BeanUtils.getProperty(o, key));
					WritableCellFormat cellFormat = new WritableCellFormat();
					cellFormat.setAlignment(jxl.format.Alignment.LEFT);
					cellFormat
							.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
					cellFormat.setWrap(true);
					label.setCellFormat(cellFormat);
					wsheet.addCell(label);
					tag = tag + 1;
				}
				i = i + 1;
			}

			wbook.write();
			wbook.close();

			os.close();
		} catch (Exception e) {
			logger.error("创建Excel时出错", e);
		}

		return null;
	}

}
