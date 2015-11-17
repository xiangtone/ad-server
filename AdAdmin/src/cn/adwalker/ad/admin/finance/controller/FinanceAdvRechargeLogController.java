/*
 * FinanceAdvResultController.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-26
 */
package cn.adwalker.ad.admin.finance.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.common.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.finance.bean.AdvRechargeBean;
import cn.adwalker.ad.admin.finance.service.IFinanceAdvRechargeService;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.model.finance.domain.AdvRechargeLog;

/**
 * 功能概述：<br>
 * 广告主充值记录
 * 
 * @author wjp
 */
@Controller
public class FinanceAdvRechargeLogController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(FinanceAdvRechargeLogController.class);


	/** 广告主支付录入服务 */
	@Resource
	private IFinanceAdvRechargeService financeAdvRechargeService;

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/manage!listFinanceAdvRecharge.do")
	public String listAdvRecharge(HttpServletRequest request,
			HttpServletResponse response ,AdvRechargeBean bean) {

		try {
			List<AdvRechargeLog> advRechargeLogList = null;
			
			IPageInfo pageInfo = new SetPage(request);
			try {
				advRechargeLogList = (List<AdvRechargeLog>) this.financeAdvRechargeService.findByPage(bean,pageInfo);
			} catch (Exception e) {
			}
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("advRechargeLogList", advRechargeLogList);
			request.setAttribute("bean", bean);
			return "manage/finance/ope_cw_advgck";
		} catch (Exception e) {
			logger.debug("获取广告主冲值记录列表异常！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加充值记录。引发的动作： a: 添加广告主充值信息 b: 更新广告主账户累计投资，公式（开发者账户累计投资 + 充值金额） c:
	 * 更新广告主信用额度余额、充值额度余额。 d: 该广告主下的广告因为欠费而下线的广告做上线处理。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/manage!addFinanceAdvRecharge.do")
	public String addAdvRecharge(HttpServletRequest request,
			HttpServletResponse response, AdvRechargeLog advRechargeLog,String advEmail) {

		try {
		} catch (Exception e) {
			logger.debug("广告主支付录入异常！");
			e.printStackTrace();
			try {
			} catch (Exception e2) {
			}
		}

		return null;
	}
}
