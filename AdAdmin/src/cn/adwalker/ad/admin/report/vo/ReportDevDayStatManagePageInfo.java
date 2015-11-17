package cn.adwalker.ad.admin.report.vo;

import javax.servlet.http.HttpServletRequest;

import cn.adwalker.core.repository.bean.PageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 功能概述：<br>
 * 
 * @author guoyongxiang
 */
public class ReportDevDayStatManagePageInfo {
	public static PageInfo getPageInfo(int pageIndex, int pageRecord,
			HttpServletRequest request, int recordCount) throws Exception {

		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);// 当前第几页
		page.setPageCount(5);// 页面显示条数
		page.setPageSize(pageRecord);// 每页显示条数
		page.setPagingParam("pageIndex");// 页码参数
		page.setRecordCount(recordCount);// 总数量

		// 组装翻页参数
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String statType = request.getParameter("statType");
		String devType = request.getParameter("devType");
		String appType = request.getParameter("appType");
		String devText = request.getParameter("devText");
		String appText = request.getParameter("appText");
		String orderColumn = request.getParameter("orderColumn");
		String orderCondition = request.getParameter("orderCondition");
		// url前缀
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI() + "");

		if (ObjectUtils.isNotEmpty(statType)) {
			url.append("?statType=");
			url.append(statType);
		} else {
			url.append("?statType=");
			url.append("1");
		}

		if (ObjectUtils.isNotEmpty(startTime)) {
			url.append("&startTime=");
			url.append(startTime);
		}
		if (ObjectUtils.isNotEmpty(endTime)) {
			url.append("&endTime=");
			url.append(endTime);
		}
		if (ObjectUtils.isNotEmpty(devType)) {
			url.append("&devType=");
			url.append(devType);
		}
		if (ObjectUtils.isNotEmpty(appType)) {
			url.append("&appType=");
			url.append(appType);
		}
		if (ObjectUtils.isNotEmpty(devText)) {
			url.append("&devText=");
			url.append(devText);
		}
		if (ObjectUtils.isNotEmpty(appText)) {
			url.append("&appText=");
			url.append(appText);
		}
		if (ObjectUtils.isNotEmpty(orderColumn)) {
			url.append("&orderColumn=");
			url.append(orderColumn);
		}
		if (ObjectUtils.isNotEmpty(orderCondition)) {
			url.append("&orderCondition=");
			url.append(orderCondition);
		}
		page.setPrefixUrl(url.toString());// url前缀
		return page;
	}
	public static PageInfo getPageInfoIos(int pageIndex, int pageRecord,
			HttpServletRequest request, int recordCount) throws Exception {

		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);// 当前第几页
		page.setPageCount(5);// 页面显示条数
		page.setPageSize(pageRecord);// 每页显示条数
		page.setPagingParam("pageIndex");// 页码参数
		page.setRecordCount(recordCount);// 总数量

		// 组装翻页参数
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String statType = request.getParameter("statType");
		String devType = request.getParameter("devType");
		String appType = request.getParameter("appType");
		String devText = request.getParameter("devText");
		String appText = request.getParameter("appText");
		String orderColumn = request.getParameter("orderColumn");
		String orderCondition = request.getParameter("orderCondition");
		// url前缀
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI() + "");

		if (ObjectUtils.isNotEmpty(statType)) {
			url.append("?statType=");
			url.append(statType);
		} else {
			url.append("?statType=");
			url.append("1");
		}

		if (ObjectUtils.isNotEmpty(startTime)) {
			url.append("&startTime=");
			url.append(startTime);
		}
		if (ObjectUtils.isNotEmpty(endTime)) {
			url.append("&endTime=");
			url.append(endTime);
		}
		if (ObjectUtils.isNotEmpty(devType)) {
			url.append("&devType=");
			url.append(devType);
		}
		if (ObjectUtils.isNotEmpty(appType)) {
			url.append("&appType=");
			url.append(appType);
		}
		if (ObjectUtils.isNotEmpty(devText)) {
			url.append("&devText=");
			url.append(devText);
		}
		if (ObjectUtils.isNotEmpty(appText)) {
			url.append("&appText=");
			url.append(appText);
		}
		if (ObjectUtils.isNotEmpty(orderColumn)) {
			url.append("&orderColumn=");
			url.append(orderColumn);
		}
		if (ObjectUtils.isNotEmpty(orderCondition)) {
			url.append("&orderCondition=");
			url.append(orderCondition);
		}
		page.setPrefixUrl(url.toString());// url前缀
		return page;
	}
	public static PageInfo getPageInfoDev(int pageIndex, int pageRecord,
			HttpServletRequest request, int recordCount) throws Exception {

		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);// 当前第几页
		page.setPageCount(5);// 页面显示条数
		page.setPageSize(pageRecord);// 每页显示条数
		page.setPagingParam("pageIndex");// 页码参数
		page.setRecordCount(recordCount);// 总数量

		// 组装翻页参数
		String startTime = request.getParameter("startTime");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		
		String statType = request.getParameter("statType");
		
		String AD_ID = request.getParameter("AD_ID");
		String AD_NAME = request.getParameter("AD_NAME");
		String APP_ID = request.getParameter("APP_ID");
		String APP_NAME = request.getParameter("APP_NAME");
		
//		String keyword = request.getParameter("keyword");
//		String value = request.getParameter("value");
		
		String status = request.getParameter("status");
		String orderColumn = request.getParameter("orderColumn");
		String orderCondition = request.getParameter("orderCondition");
		// url前缀
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI() + "?1=1");
		
//		if (ObjectUtils.isNotEmpty(statType)) {
//			url.append("?statType=");
//			url.append(statType);
//		} else {
//			url.append("?statType=");
//			url.append("1");
//		}

		if (ObjectUtils.isNotEmpty(startTime)) {
			url.append("&startTime=");
			url.append(startTime);
		}
		if (ObjectUtils.isNotEmpty(beginTime)) {
			url.append("&beginTime=");
			url.append(beginTime);
		}
		if (ObjectUtils.isNotEmpty(endTime)) {
			url.append("&endTime=");
			url.append(endTime);
		}
		if (ObjectUtils.isNotEmpty(AD_ID)) {
			url.append("&AD_ID=");
			url.append(AD_ID);
		}
		if (ObjectUtils.isNotEmpty(AD_NAME)) {
			url.append("&AD_NAME=");
			url.append(AD_NAME);
		}
		if (ObjectUtils.isNotEmpty(APP_ID)) {
			url.append("&APP_ID=");
			url.append(APP_ID);
		}
		if (ObjectUtils.isNotEmpty(APP_NAME)) {
			url.append("&APP_NAME=");
			url.append(APP_NAME);
		}
		///
//		if (ObjectUtils.isNotEmpty(keyword)) {
//			url.append("&keyword=");
//			url.append(keyword);
//		}
//		if (ObjectUtils.isNotEmpty(value)) {
//			url.append("&value=");
//			url.append(value);
//		}
		if (ObjectUtils.isNotEmpty(status)) {
			url.append("&status=");
			url.append(status);
		}
		//
		
		if (ObjectUtils.isNotEmpty(orderColumn)) {
			url.append("&orderColumn=");
			url.append(orderColumn);
		}
		if (ObjectUtils.isNotEmpty(orderCondition)) {
			url.append("&orderCondition=");
			url.append(orderCondition);
		}
		page.setPrefixUrl(url.toString());// url前缀
		return page;
	}
	
	public static PageInfo getPageInfoIOS(int pageIndex, int pageRecord,
			HttpServletRequest request, int recordCount) throws Exception {

		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);// 当前第几页
		page.setPageCount(5);// 页面显示条数
		page.setPageSize(pageRecord);// 每页显示条数
		page.setPagingParam("pageIndex");// 页码参数
		page.setRecordCount(recordCount);// 总数量

		// 组装翻页参数
		String ad_id = request.getParameter("ad_id")!=null?request.getParameter("ad_id"):"";
		String channel = request.getParameter("channel")!=null?request.getParameter("channel"):"";
		String ad_name = request.getParameter("ad_name")!=null?request.getParameter("ad_name"):"";
		String mac = request.getParameter("mac")!=null?request.getParameter("mac"):"";
		String searchStatus = request.getParameter("searchStatus")!=null?request.getParameter("searchStatus"):"";
		String statDate_start = request.getParameter("statDate_start")!=null?request.getParameter("statDate_start"):"";
		String statDate_end = request.getParameter("statDate_end")!=null?request.getParameter("statDate_end"):"";
		String activeDate_start = request.getParameter("activeDate_start")!=null?request.getParameter("activeDate_start"):"";
		String activeDate_end = request.getParameter("activeDate_end")!=null?request.getParameter("activeDate_end"):"";
		// url前缀
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI() + "");
		if (ObjectUtils.isNotEmpty(searchStatus)) {
			url.append("?searchStatus=");
			url.append(searchStatus);
		}
		if (ObjectUtils.isNotEmpty(ad_id)) {
			url.append("&ad_id=");
			url.append(ad_id);
		}

		if (ObjectUtils.isNotEmpty(channel)) {
			url.append("&channel=");
			url.append(channel);
		}
		if (ObjectUtils.isNotEmpty(ad_name)) {
			url.append("&ad_name=");
			url.append(ad_name);
		}
		if (ObjectUtils.isNotEmpty(mac)) {
			url.append("&mac=");
			url.append(mac);
		}
		
		if (ObjectUtils.isNotEmpty(statDate_start)) {
			url.append("&statDate_start=");
			url.append(statDate_start);
		}
		if (ObjectUtils.isNotEmpty(statDate_end)) {
			url.append("&statDate_end=");
			url.append(statDate_end);
		}
		if (ObjectUtils.isNotEmpty(activeDate_start)) {
			url.append("&activeDate_start=");
			url.append(activeDate_start);
		}
		if (ObjectUtils.isNotEmpty(activeDate_end)) {
			url.append("&activeDate_end=");
			url.append(activeDate_end);
		}
		page.setPrefixUrl(url.toString());// url前缀
		return page;
	}
	
	
	public static PageInfo getAppPageInfo(int pageIndex, int pageRecord,
			HttpServletRequest request, int recordCount) throws Exception {

		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);// 当前第几页
		page.setPageCount(5);// 页面显示条数
		page.setPageSize(pageRecord);// 每页显示条数
		page.setPagingParam("pageIndex");// 页码参数
		page.setRecordCount(recordCount);// 总数量

		// 组装翻页参数
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String statType = request.getParameter("statType");
		String statAdType = request.getParameter("statAdType");
		String devType = request.getParameter("devType");
		String appType = request.getParameter("appType");
		String devText = request.getParameter("devText");
		String appText = request.getParameter("appText");
		String orderColumn = request.getParameter("orderColumn");
		String orderCondition = request.getParameter("orderCondition");
		String rateStatus = request.getParameter("rateStatus");
		// url前缀
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI() + "");

		if (ObjectUtils.isNotEmpty(statType)) {
			url.append("?statType=");
			url.append(statType);
		} else {
			url.append("?statType=");
			url.append("1");
		}
		if (ObjectUtils.isNotEmpty(statAdType)) {
			url.append("&statAdType=");
			url.append(statAdType);
		} else {
			url.append("&statAdType=");
			url.append("0");
		}

		if (ObjectUtils.isNotEmpty(startTime)) {
			url.append("&startTime=");
			url.append(startTime);
		}
		if (ObjectUtils.isNotEmpty(endTime)) {
			url.append("&endTime=");
			url.append(endTime);
		}
		if (ObjectUtils.isNotEmpty(devType)) {
			url.append("&devType=");
			url.append(devType);
		}
		if (ObjectUtils.isNotEmpty(appType)) {
			url.append("&appType=");
			url.append(appType);
		}
		if (ObjectUtils.isNotEmpty(devText)) {
			url.append("&devText=");
			url.append(devText);
		}
		if (ObjectUtils.isNotEmpty(appText)) {
			url.append("&appText=");
			url.append(appText);
		}
		if (ObjectUtils.isNotEmpty(orderColumn)) {
			url.append("&orderColumn=");
			url.append(orderColumn);
		}
		if (ObjectUtils.isNotEmpty(orderCondition)) {
			url.append("&orderCondition=");
			url.append(orderCondition);
		}
		if (ObjectUtils.isNotEmpty(rateStatus)) {
			url.append("&rateStatus=");
			url.append(rateStatus);
		}
		page.setPrefixUrl(url.toString());// url前缀
		return page;
	}
}
