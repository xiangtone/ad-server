package cn.adwalker.ad.web.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.model.application.dao.ApplicationCurrencyDao;
import cn.adwalker.ad.model.application.domain.AppCurrency;
import cn.adwalker.ad.model.common.dao.CategoryDao;
import cn.adwalker.ad.model.common.domain.EscoreCategory;
import cn.adwalker.ad.model.common.domain.ProvinceCitySort;
import cn.adwalker.ad.util.JacksonMapper;

/**
 * 功能描述：<br>
 * 分类控制层
 */
@Controller
public class CategoryController {
	
	@Resource
	private CategoryDao categoryDao;

	@Resource
	private ApplicationCurrencyDao applicationCurrencyDao;

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
	 * 共用方法，ajax打印对象类型的json串
	 * 
	 * @param object
	 * @param reponse
	 */
	public void writerJSON(Object object, HttpServletResponse reponse) {
		try {
			String json = JacksonMapper.objectToJsonString(object);
			Writer writer = reponse.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据应用名称获取虚拟货币信息
	 * 
	 * @param id 分类类型
	 * @param response
	 */
	@RequestMapping("/getCurrency.action")
	public void getCurrency(String id, HttpServletResponse response) {
		AppCurrency currencyF = applicationCurrencyDao.findCurrencyById(id);
		try {
			String json = JacksonMapper.objectToJsonString(currencyF);
			print(json, response);
		} catch (Exception e) {

		}
	}

	public void setEscoreCategoryDao(CategoryDao escoreCategoryDao) {
		this.categoryDao = escoreCategoryDao;
	}

	/**
	 * 根据分类类型获取平台
	 * 
	 * @param type
	 *            分类类型
	 * @param response
	 */
	@RequestMapping("/provinceCitySort.action")
	public void geProvinceCitySort(int id, HttpServletResponse response) {
		List<ProvinceCitySort> provinceCitySort = categoryDao.getprovinceCitySort(id);
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
	 * 根据分类类型和分类平台获取分类
	 * 
	 * @param type 分类类型
	 * @param platform 分类平台
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getCategory.action")
	public void getCategory(String PARENT_ID, String platform, HttpServletResponse response) throws Exception {
		List<EscoreCategory> escoreCategory = categoryDao.getCategoryNew(PARENT_ID, Long.parseLong(platform));
		StringBuffer buffer = new StringBuffer();
		for (EscoreCategory bean : escoreCategory) {
			buffer.append("<option value=\"");
			buffer.append(bean.getId());
			buffer.append("\">");
			buffer.append(bean.getName());
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
	@RequestMapping("/getCategoryById.action")
	public void getCategoryById(Long categoryId, HttpServletResponse response) throws Exception {
		EscoreCategory escoreCategory = categoryDao.getCategoryById(categoryId);
		writerJSON(escoreCategory, response);
	}

	/**
	 * 根据分类id获取分类信息
	 * 
	 * @param categoryId
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCityId.action")
	public void getCityId(Long categoryId, HttpServletResponse response) throws Exception {
		ProvinceCitySort provinceCitySort = categoryDao.getCityId(categoryId);
		writerJSON(provinceCitySort, response);
	}

}
