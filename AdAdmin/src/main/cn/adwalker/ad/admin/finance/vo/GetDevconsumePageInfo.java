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
public class GetDevconsumePageInfo {
	
	public static PageInfo getPageInfo(int pageIndex , int pageRecord , HttpServletRequest request,int recordCount){
		
		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);//当前第几页
		page.setPageCount(5);//页面显示条数
		page.setPageSize(pageRecord);//每页显示条数
		page.setPagingParam("pageIndex");//页码参数
		page.setRecordCount(recordCount);//总数量
		
		//url前缀
		String adv_Id = request.getParameter("adv_Id");//广告ID
		String adv_Name = request.getParameter("adv_Name");//广告名称
		String yunYingStartTime = request.getParameter("yunYingStartTime");//网站主ID
		String yunYingEndTime = request.getParameter("yunYingEndTime");//网站主名称
		String ad_Name = request.getParameter("ad_Name");//应用ID
		String statDateStartTime = request.getParameter("statDateStartTime");//应用名称
		String statDateEndTime = request.getParameter("statDateEndTime");//结算时间 开始时间
	
	
		
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI()+"");
		url.append("?1=1");
		
		if(!"".equals(adv_Id) && adv_Id != null ){
			url.append("&adv_Id=");
			url.append(adv_Id);
		}
		if(!"".equals(adv_Name)&& adv_Name != null){
			url.append("&adv_Name=");
			url.append(adv_Name);
		}
		if(!"".equals(yunYingStartTime)&& yunYingStartTime != null){
			url.append("&yunYingStartTime=");
			url.append(yunYingStartTime);
		}
		if(!"".equals(yunYingEndTime)&& yunYingEndTime != null){
			url.append("&yunYingEndTime=");
			url.append(yunYingEndTime);
		}
		if(!"".equals(ad_Name)&& ad_Name != null){
			url.append("&ad_Name=");
			url.append(ad_Name);
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
