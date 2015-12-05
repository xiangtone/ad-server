/*
 * GetAdvEffectSecondPageInfo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-28
 */
package cn.adwalker.ad.admin.finance.vo;

import javax.servlet.http.HttpServletRequest;

import cn.adwalker.core.repository.bean.PageInfo;

/**
 * 功能概述：<br>
 *
 *		广告主结算确认分页
 *
 * @author jiaozhichao
 */
public class GetDevmonPageInfo {
	
	public static PageInfo getPageInfo(int pageIndex , int pageRecord , HttpServletRequest request,int recordCount){
		
		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);//当前第几页
		page.setPageCount(5);//页面显示条数
		page.setPageSize(pageRecord);//每页显示条数
		page.setPagingParam("pageIndex");//页码参数
		page.setRecordCount(recordCount);//总数量
		
		//url前缀
		String ad_Id = request.getParameter("ad_Id");//广告ID
		String ad_Name = request.getParameter("ad_Name");//广告名称
		String dev_Id = request.getParameter("dev_Id");//网站主ID
		String dev_Name = request.getParameter("dev_Name");//网站主名称
		String app_Id = request.getParameter("app_Id");//应用ID
		String app_Name = request.getParameter("app_Name");//应用名称
		String settleStartTime = request.getParameter("settleStartTime");//结算时间 开始时间
		String settleEndTime = request.getParameter("settleEndTime");//结算时间  结束时间
		String status = request.getParameter("status");//结算类型
		String statDateStartTime = request.getParameter("statDateStartTime");//效果发生日期 开始时间
		String statDateEndTime = request.getParameter("statDateEndTime");//效果发生日期 结束时间
	
		
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI()+"");
		url.append("?1=1");
		
		if(!"".equals(ad_Id) && ad_Id != null ){
			url.append("&ad_Id=");
			url.append(ad_Id);
		}
		if(!"".equals(ad_Name)&& ad_Name != null){
			url.append("&ad_Name=");
			url.append(ad_Name);
		}
		if(!"".equals(dev_Id)&& dev_Id != null){
			url.append("&dev_Id=");
			url.append(dev_Id);
		}
		if(!"".equals(dev_Name)&& dev_Name != null){
			url.append("&dev_Name=");
			url.append(dev_Name);
		}
		if(!"".equals(app_Id)&& app_Id != null){
			url.append("&app_Id=");
			url.append(app_Id);
		}
		if(!"".equals(app_Name)&& app_Name != null){
			url.append("&app_Name=");
			url.append(app_Name);
		}
		if(!"".equals(settleStartTime)&& settleStartTime != null){
			url.append("&settleStartTime=");
			url.append(settleStartTime);
		}
		if(!"".equals(settleEndTime)&& settleEndTime != null){
			url.append("&settleEndTime=");
			url.append(settleEndTime);
		}
		if(!"".equals(status)&& status != null){
			url.append("&status=");
			url.append(status);
		}
		if(!"".equals(statDateStartTime)&& statDateStartTime != null){
			url.append("&statDateStartTime=");
			url.append(statDateStartTime);
		}
		if(!"".equals(statDateEndTime)&& statDateEndTime != null){
			url.append("&statDateEndTime=");
			url.append(statDateEndTime);
		}
		page.setPrefixUrl(url.toString());//url前缀
		return page;
	}
}
