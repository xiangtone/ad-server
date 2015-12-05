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
public class GetAdvEffectSecondPageInfo {
	
	public static PageInfo getPageInfo(int pageIndex , int pageRecord , HttpServletRequest request,int recordCount){
		
		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);//当前第几页
		page.setPageCount(5);//页面显示条数
		page.setPageSize(pageRecord);//每页显示条数
		page.setPagingParam("pageIndex");//页码参数
		page.setRecordCount(recordCount);//总数量
		
		//url前缀
		String searchText = request.getParameter("searchText");//搜索关键字
		String searchType = request.getParameter("searchType");//搜索字段
		String startTime = request.getParameter("startTime");//开始时间
		String endTime = request.getParameter("endTime");//结束时间
		
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI()+"");
		url.append("?searchType=");
		url.append(searchType);
		if(!"".equals(searchText) && searchText != null ){
			url.append("&searchText=");
			url.append(searchText);
		}
		if(!"".equals(startTime)&& startTime != null){
			url.append("&startTime=");
			url.append(startTime);
		}
		if(!"".equals(endTime)&& endTime != null){
			url.append("&endTime=");
			url.append(endTime);
		}
		page.setPrefixUrl(url.toString());//url前缀
		return page;
	}
public static PageInfo getPageInfoCollect(int pageIndex , int pageRecord , HttpServletRequest request,int recordCount){
		
		PageInfo page = new PageInfo();
		page.setCurrentPage(pageIndex);//当前第几页
		page.setPageCount(5);//页面显示条数
		page.setPageSize(pageRecord);//每页显示条数
		page.setPagingParam("pageIndex");//页码参数
		page.setRecordCount(recordCount);//总数量
		
		//url前缀
		String searchText = request.getParameter("searchText");//搜索关键字
		String searchType = request.getParameter("searchType");//搜索字段
		String startTime = request.getParameter("startTime");//开始时间
		String endTime = request.getParameter("endTime");//结束时间
		String status = request.getParameter("status");//结束时间
		
		StringBuffer url = new StringBuffer();
		url.append(request.getRequestURI()+"");
		url.append("?searchType=");
		url.append(searchType);
		if(!"".equals(searchText) && searchText != null ){
			url.append("&searchText=");
			url.append(searchText);
		}
		if(!"".equals(startTime)&& startTime != null){
			url.append("&startTime=");
			url.append(startTime);
		}
		if(!"".equals(endTime)&& endTime != null){
			url.append("&endTime=");
			url.append(endTime);
		}
		if(!"".equals(status)&& status != null){
			url.append("&status=");
			url.append(status);
		}
		page.setPrefixUrl(url.toString());//url前缀
		return page;
	}
}
