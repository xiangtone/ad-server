/*
 * registService.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.ad.admin.mail.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import cn.adwalker.ad.admin.ad.form.CampaignForm;
import cn.adwalker.ad.admin.ad.vo.CampaignAuditMailVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.mail.service.ICampaignMailService;
import cn.adwalker.ad.admin.mail.vo.AdBudgetMail;
import cn.adwalker.ad.task.SendMailTask;
import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.spring.AppContext;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.ad.dao.IAdBudgetLogDao;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.ad.domain.AdBudgetLog;
import cn.adwalker.model.news.domain.Mail;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * <p>
 * Title: RegistAdvServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-19
 */
@Service
public class CampaignMailServiceImpl implements ICampaignMailService {

	@Resource(name = "campaignDao")
	private ICampaignDao campaignDao;

	@Resource
	private IAdDao adDao;

	@Resource
	private IAdBudgetLogDao adBudgetLogDao;

	/**
	 * 活动信息查询 (non-Javadoc)
	 * <p>
	 * Title: findActiInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param actId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.manager.ad.service.ICampaignService#findActiInfo(java.lang.Long)
	 */

	@SuppressWarnings("unchecked")
	private CampaignAuditMailVo getCampaignInfo(Long campaign_id) throws Exception {
		CampaignAuditMailVo vo = null;
		if (campaign_id != null) {
			List<CampaignAuditMailVo> list = (List<CampaignAuditMailVo>) campaignDao.findAll(
					"select v.*,sys_user.email sales_email,s.name sales_name from V_CAMPAIGN v left join t_campaign_salesman s  on v.salesman_id=s.id left join  t_sys_user sys_user on s.sys_user_id=sys_user.id where v.id=" + campaign_id,
					CampaignAuditMailVo.class);
			if (list != null && list.size() > 0) {
				vo = list.get(0);
			}
		}

		return vo;

	}

	/**
	 * 活动信息查询修改 (non-Javadoc)
	 * <p>
	 * Title: updateActivity
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param actId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.manager.ad.service.ICampaignService#updateActivity(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public CampaignForm getCampaign(Long actId) throws Exception {
		CampaignForm view = null;
		List<CampaignForm> list = (List<CampaignForm>) campaignDao.findAll("*",
				"V_CAMPAIGN where id=?", new Object[] { actId },
				CampaignForm.class);
		if (list != null && list.size() > 0) {
			view = list.get(0);
		}
		return view;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: sendMailAudit
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.mail.service.ICampaignMailService#sendMailAudit(java.lang.Long)
	 */
	@Override
	public void sendMailAudit(Long id,SysUserVo manageUser) throws Exception {
		CampaignAuditMailVo campaignInfoVo = this.getCampaignInfo(id);
		ApplicationContext ctx = AppContext.getApplicationContext();
		Configuration freadwalkerkerConfig = (Configuration) ctx.getBean("freemarkerConfigurer");
		Template temp =null;
		String email =ConfigUtil.getString("send_mail_for_aduit");;
		if (campaignInfoVo.getOs().equals("android")) {
			temp = freadwalkerkerConfig.getTemplate("/template/ad/campaign-aduit_android.ftl");
		}else if (campaignInfoVo.getOs().equals("ios")) {
			temp = freadwalkerkerConfig.getTemplate("/template/ad/campaign-aduit_ios.ftl");
		}
		campaignInfoVo.setManager_name(manageUser.getRealName());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vo", campaignInfoVo);
		String s = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
	
		if (!cn.adwalker.core.util.lang.StringUtil.isEmpty(email)) {
			if (!StringUtil.isEmpty(campaignInfoVo.getSales_email())) {
				email=email+","+campaignInfoVo.getSales_email();
			}
			String arr[] = email.split(",");
			ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
			for (int i = 0; i < arr.length; i++) {
				Mail vo = new Mail();
				vo.setTitle("系统消息：上线通知【"+campaignInfoVo.getCampaign_name()+"---"+ id+"】" );
				vo.setEmail(arr[i]);
				vo.setContent(s);
				threadPool.execute(new SendMailTask(vo, arr[i]));

			}

		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: sendMailAdBudget
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.mail.service.ICampaignMailService#sendMailAdBudget(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sendMailAdBudget(List<Long> ids) throws Exception {
		if (ids != null && ids.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Long id : ids) {
				sb.append(id).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			List<AdBudgetMail> list = (List<AdBudgetMail>) adDao
					.findAll(
							"select id,placement_name name,BUDGET_TYPE,BUDGET_DAY,COST_DAY from V_AD_CAMPAIGN where id in ("
									+ sb.toString() + ")", AdBudgetMail.class);
			this.adBudgetLog(list);
			ApplicationContext ctx = AppContext.getApplicationContext();
			Configuration freadwalkerkerConfig = (Configuration) ctx.getBean("freemarkerConfigurer");
			Template temp = freadwalkerkerConfig.getTemplate("/template/ad/ad-budget.ftl");// 修改bug，之前模版文件引用错误
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			String s = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
			String email = ConfigUtil.getString("send_mail_ad_info");
			if (!cn.adwalker.core.util.lang.StringUtil.isEmpty(email)) {
				String arr[] = email.split(",");
				ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
				for (int i = 0; i < arr.length; i++) {
					Mail vo = new Mail();
					vo.setTitle("【超量下线通知" + DateUtil.formatDate(new Date(), "yy-MM-ddHH") + "】");
					vo.setEmail(arr[i]);
					vo.setContent(s);
					threadPool.execute(new SendMailTask(vo, arr[i]));

				}

			}

		}

	}

	/**
	 * 
	 * <p>
	 * Title: adBudgetLog
	 * </p>
	 * <p>
	 * Description:记录日志
	 * </p>
	 * 
	 * @param list
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-9-4
	 * @return void
	 * @version 1.0
	 */
	private void adBudgetLog(List<AdBudgetMail> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (AdBudgetMail vo : list) {
				AdBudgetLog entity = new AdBudgetLog();
				entity.setAd_id(vo.getId());
				entity.setBudget_day(vo.getBudget_day());
				entity.setBudget_type(vo.getBudget_type());
				entity.setCost_day(vo.getCost_day());
				entity.setCreate_time(new Date());
				entity.setOffline_time(new Date());
				adBudgetLogDao.insert(entity);
			}
		}

	}

}
