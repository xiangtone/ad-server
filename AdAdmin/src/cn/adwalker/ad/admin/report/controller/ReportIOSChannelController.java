package cn.adwalker.ad.admin.report.controller;

/*
 * AdverReportStatController.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-20
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.report.bean.AdsageActiviteLog;
import cn.adwalker.ad.admin.report.bean.Autocomplete;
import cn.adwalker.ad.admin.report.bean.IOSChannelBean;
import cn.adwalker.ad.admin.report.service.IReportIOSChannelService;

/**
 * 功能概述： <br>
 * 
 * @author jiaozhichao
 */
@Controller
public class ReportIOSChannelController extends AbstractControllerParent {

	@Resource
	private IReportIOSChannelService reService;

	@RequestMapping("/manage!reportIosChannel.do")
	public String reportList(HttpServletRequest request, IOSChannelBean bean,
			HttpSession session) throws Exception {
		IPageInfo pageInfo = new SetPage(request);
		List<AdsageActiviteLog> rdadsr = new ArrayList<AdsageActiviteLog>();
		int summary = 0;// 汇总确认激活数
		if (bean == null) {
			bean = new IOSChannelBean();
		}
		if (StringUtils.isEmpty(bean.getStatDate_start())) {
			bean.setStatDate_start(DateUtil.formatDate(DateUtil
					.getFirstDayOfMonth(new Date())));
		}
		bean.setStatDate_start(bean.getStatDate_start() + " 00:00:00");
		if (StringUtils.isEmpty(bean.getStatDate_end())) {
			bean.setStatDate_end(DateUtil.formatDate(new Date()));
		}
		bean.setStatDate_end(bean.getStatDate_end() + " 23:59:59");
		List<Map<String, Object>> channelList = reService.getChannelList();
		List<Map<String, Object>> adList = reService.getAdList();
		//设置ad_id
		if(StringUtils.isNotBlank(bean.getQ())){
			String name_id[]=bean.getQ().split("@");
			if(name_id.length==2){
				bean.setAd_id(name_id[1]);
			}
		}
		try {
			rdadsr = reService.getadIOSLog(bean, pageInfo);
//			summary = this.reService.getSumActivete(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("channelList", channelList);
		request.setAttribute("adList", adList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("rdadsr", rdadsr);
		request.setAttribute("summary", summary);
		request.setAttribute("vo", bean);
		return "manage/report/reportIosChannel";
	}
	/**
	 * <p>add by jief 自动完成匹配功能</p>
	 * @param request
	 * @param adName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage!matchCampaingCofnig.do")
	public String getMatchCampaingCofnig(HttpServletRequest request,
			String q,HttpServletResponse response) throws Exception{
		    System.out.println("q is ="+q);
			List<Autocomplete> acList=new ArrayList<Autocomplete>();
			List<Map<String, Object>> adList = reService.getAdList();
			for(int i=0;i<adList.size();i++){
				if(StringUtils.isNotBlank(adList.get(i).get("AD_NAME").toString())){
					if(adList.get(i).get("AD_NAME").toString().contains(q)){
						Autocomplete am=new Autocomplete();
						am.setAdId(adList.get(i).get("AD_KEY").toString());
						am.setAdName(adList.get(i).get("AD_NAME").toString());
						acList.add(am);
					}
				}
			}
		String	json = JacksonMapper.objectToJsonString(acList);
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/manage!reportIosChannelExcel.do")
	public String impCallbackDataByExcel(HttpServletRequest request,
			IOSChannelBean bean, HttpServletResponse response) throws Exception {
		if (bean == null) {
			bean = new IOSChannelBean();
		}

		if (StringUtils.isEmpty(bean.getStatDate_start())) {
			bean.setStatDate_start(DateUtil.formatDate(DateUtil
					.getFirstDayOfMonth(new Date())));
		}
		bean.setStatDate_start(bean.getStatDate_start() + " 00:00:00");

		if (StringUtils.isEmpty(bean.getStatDate_end())) {
			bean.setStatDate_end(DateUtil.formatDate(new Date()));
		}
		bean.setStatDate_end(bean.getStatDate_end() + " 23:59:59");
		//设置ad_id
		if(StringUtils.isNotBlank(bean.getQ())){
				String name_id[]=bean.getQ().split("@");
				if(name_id.length==2){
					bean.setAd_id(name_id[1]);
				}
		}
		try{
			response.setContentType("application/zip;charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;  filename=ios.csv");
			File file = reService.getIOSExcelFromResultSet(bean);
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			in.close();
			OutputStream os = response.getOutputStream();
			// String aaa="真的很美好";
			os.write(buffer); // 参数里放byte[]
			os.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
