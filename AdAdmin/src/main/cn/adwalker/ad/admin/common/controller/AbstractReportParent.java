/*
 * AbstractReportParent.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 26-Dec-2011
 */
package cn.adwalker.ad.admin.common.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 * 功能概述：<br>
 * 报表父类
 * 
 * @author zhaozengbin
 */
public class AbstractReportParent extends AbstractControllerParent {

	/**
	 * 获取昨天日期yyyy-MM-dd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getYesterday() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		return yesterday;
	}

	/**
	 * 获取昨天日期yyyy-MM-dd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBeforeDay(Integer day) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Integer.parseInt("-" + day));
		String beforeDay = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		return beforeDay;
	}
}
