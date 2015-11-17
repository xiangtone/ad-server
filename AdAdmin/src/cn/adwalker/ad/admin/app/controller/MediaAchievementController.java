package cn.adwalker.ad.admin.app.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.MediaAchievementBean;
import cn.adwalker.ad.admin.app.service.IMediaAchievementService;
import cn.adwalker.ad.admin.app.vo.MediaAchievementVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.BeanUtils;
import cn.adwalker.core.util.DateUtil;

/**
 * <p>
 * Title: MediaAchievementController
 * </p>
 * <p>
 * Description:媒介BD业绩
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-9-6
 */

@Controller
public class MediaAchievementController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(MediaAchievementController.class);

	/** 媒介BD业务 */
	@Resource
	private IMediaAchievementService mediaAchievementService;

	/**
	 * <p>
	 * Title: List
	 * </p>
	 * <p>
	 * Description:媒介BD业绩List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-9-6
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!mediaAchievementList.do")
	public String list(HttpSession session, HttpServletRequest request,
			MediaAchievementBean bean, Map<String, Object> model) {
		SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		try {
			if (bean == null) {
				bean = new MediaAchievementBean();
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
			List<MediaAchievementVo> list = mediaAchievementService.findByPage(
					pageInfo, bean, user);

			MediaAchievementVo total = mediaAchievementService.findTotal(bean, user);
			model.put("pageInfo", pageInfo);
			model.put("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("total", total);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return "media/app/media_achievement_list";

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
	 * @throws Exception
	 */
	@RequestMapping("/manage!mediaAchievementSDown.do")
	public void exportCSV(HttpSession session, HttpServletResponse response,
			MediaAchievementBean bean) throws Exception {
		SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		if (StringUtils.isEmpty(bean.getBeginTime())) {
			bean.setBeginTime(DateUtil.formatDate(DateUtil.addDays(new Date(),
					-1)));
		}
		if (StringUtils.isEmpty(bean.getEndTime())) {
			bean.setEndTime(DateUtil.formatDate(DateUtil
					.addDays(new Date(), -1)));
		}
		String fileName = "媒介BD业绩报表.xls";
		String workbookName = "媒介BD业绩";

		String title[] = { "日期", "负责人", "开发者ID", "应用id", "应用名称", "广告样式",
				"SDK类型", "请求展示", "广告展示", "广告点击", "开始下载", "下载完成", "激活数", "成本（元）" };
		List<Object> list = mediaAchievementService.findAll(bean, user);
		String clumes[] = { "static_date", "app_manager_name", "dev_id", "app_id",
				"name", "fname", "os", "pospv", "apppv", "click", "clickd",
				"download", "activate", "cost" };
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
