/**
 * 
 */
package cn.adwalker.ad.admin.common.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.model.app.dao.IAppCurrencyDao;
import cn.adwalker.model.app.domain.AppCurrency;
import cn.adwalker.model.common.dao.ISysCategoryDao;
import cn.adwalker.model.common.domain.SysCategory;
import cn.adwalker.model.common.domain.ProvinceCitySort;

/**
 * 功能描述：<br>
 * 分类控制层 
 * 
 * @author guoyongxiang
 */
@Controller(value = "escoreCategoryController")
public class SysCategoryController {
	@Resource
	private ISysCategoryDao sysCategoryDao;

	@Resource
	private IAppCurrencyDao appCurrencyDao;

	/**
	 * 根据分类类型获取平台
	 * 
	 * @param type
	 *            分类类型
	 * @param response
	 */
	@RequestMapping("/getPlatform.do")
	public void getPlatform(String parent_id, HttpServletResponse response) {
		List<SysCategory> escoreCategory = sysCategoryDao
				.getPlatform(parent_id);
		StringBuffer buffer = new StringBuffer();
		for (SysCategory bean : escoreCategory) {
			buffer.append("<option value=\"");
			buffer.append(bean.getParentId());
			buffer.append("\">");
			buffer.append(bean.getPlatform());
			buffer.append("</option>");
		}
		print(buffer.toString(), response);
	}

	/**
	 * 根据分类类型和分类平台获取分类
	 * 
	 * @param type
	 *            分类类型
	 * @param platform
	 *            分类平台
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getCategory.do")
	public void getCategory(String PARENT_ID, String platform,
			HttpServletResponse response) throws Exception {
		List<SysCategory> escoreCategory = sysCategoryDao.getCategoryNew(
				PARENT_ID, Long.parseLong(platform));
		StringBuffer buffer = new StringBuffer();
		for (SysCategory bean : escoreCategory) {
			buffer.append("<option value=\"");
			buffer.append(bean.getId());
			buffer.append("\">");
			buffer.append(bean.getName());
			buffer.append("</option>");
		}
		print(buffer.toString(), response);

	}

	/**
	 * 公用方法，ajax给后台打印数据
	 * 
	 * @param msg
	 */
	private void print(String msg, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(msg.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据应用名称获取虚拟货币信息
	 * 
	 * @param id
	 *            分类类型
	 * @param response
	 */
	@RequestMapping("/getCurrency.do")
	public void getCurrency(String id, HttpServletResponse response) {
		AppCurrency currencyF = appCurrencyDao.findCurrencyById(id);
		try {
			String json = JacksonMapper.objectToJsonString(currencyF);
			print(json, response);
		} catch (Exception e) {

		}

	}

	/**
	 * 根据分类类型获取平台
	 * 
	 * @param type
	 *            分类类型
	 * @param response
	 */
	@RequestMapping("/provinceCitySort.do")
	public void geProvinceCitySort(int id, HttpServletResponse response) {
		List<ProvinceCitySort> provinceCitySort = sysCategoryDao
				.getprovinceCitySort(id);
		StringBuffer buffer = new StringBuffer();
		for (ProvinceCitySort bean : provinceCitySort) {
			buffer.append("<option value=\"");
			buffer.append(bean.getId());
			buffer.append("\">");
			buffer.append(bean.getProvince_City());
			buffer.append("</option>");
		}
		print(buffer.toString(), response);
	}

	/**
	 * 根据分类id获取分类信息
	 * 
	 * @param categoryId
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCityId.do")
	public void getCityId(Long categoryId, HttpServletResponse response)
			throws Exception {
		ProvinceCitySort provinceCitySort = sysCategoryDao
				.getCityId(categoryId);
		OutputHelper.writerJSON(provinceCitySort, response);
	}
}
