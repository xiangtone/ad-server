/*
 * registService.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.ad.admin.ad.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import cn.adwalker.ad.admin.ad.bean.CampaignOnlineSerach;
import cn.adwalker.ad.admin.ad.bean.CampaignQueryBean;
import cn.adwalker.ad.admin.ad.form.CampaignAddForm;
import cn.adwalker.ad.admin.ad.form.CampaignForm;
import cn.adwalker.ad.admin.ad.form.CampaignPriceForm;
import cn.adwalker.ad.admin.ad.service.ICampaignService;
import cn.adwalker.ad.admin.ad.vo.CampaignEditVo;
import cn.adwalker.ad.admin.ad.vo.CampaignInfoVo;
import cn.adwalker.ad.admin.ad.vo.CampaignVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.vo.CampaignOnlineVo;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.ad.task.SendMailTask;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.spring.AppContext;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.ad.dao.IAdAuditLogDao;
import cn.adwalker.model.ad.dao.IAdvDao;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.ad.dao.ICampaignPlacementRelDao;
import cn.adwalker.model.ad.dao.ICampaignSalesmanDao;
import cn.adwalker.model.ad.dao.IAdSortRelDao;
import cn.adwalker.model.ad.dao.IPlacementDao;
import cn.adwalker.model.ad.dao.IPriceUpdateDao;
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.AdAuditLog;
import cn.adwalker.model.ad.domain.Advertiser;
import cn.adwalker.model.ad.domain.Campaign;
import cn.adwalker.model.ad.domain.CampaignPlacementRel;
import cn.adwalker.model.ad.domain.CampaignSalesman;
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.model.ad.domain.Price;
import cn.adwalker.model.config.dao.ICpIosConfigDao;
import cn.adwalker.model.news.domain.Mail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * <p>
 * Title: RegistAdvServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-19
 */
@Service
public class CampaignServiceImpl implements ICampaignService {

	@Resource(name = "campaignDao")
	private ICampaignDao campaignDao;

	@Resource
	private IAdAuditLogDao adAuditLogDao;

	@Resource(name = "campaignPlacementRelDao")
	private ICampaignPlacementRelDao campaignPlacementRelDao;

	@Resource(name = "placementDao")
	private IPlacementDao placementDao;

	@Resource
	private IAdSortRelDao campaignSortRelDao;

	@Resource
	private ICampaignSalesmanDao campaignSalesmanDao;

	@Resource
	private IAdvDao advDao;

	@Resource
	private IPriceUpdateDao priceUpdateDao;

	@Resource
	private ICpIosConfigDao cpIosConfigDao;
	
	
	
	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: addPubAct
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param campaign
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Long saveCampaign(CampaignAddForm form, SysUserVo sysUserVo,boolean isSubmit)
			throws Exception {
		Long campaign_id = null;
		if (form != null) {
			Campaign campaign = new Campaign();
			campaign.setAdv_id(form.getAdv_id());
			campaign.setCampaign_name(form.getCampaign_name());
			campaign.setCampaign_type(form.getCampaign_type());
			campaign.setCategory_id(form.getCategory_id());
			campaign.setCharge_type(form.getCharge_type());
			campaign.setPrice(form.getPrice());
			campaign.setBudget(form.getBudget());
			campaign.setCampaign_required(form.getCampaign_required());
			campaign.setCreate_time(new Date());
			campaign.setCreate_user(sysUserVo.getId());
			campaign.setBalance_cycle(form.getBalance_cycle());
			campaign.setConfirm_mode(form.getConfirm_mode());
			campaign.setSalesman_id(form.getSalesman_id());
			campaign.setRes_type(form.getRes_type());
			campaign_id = campaignDao.insert(campaign);

			Placement placement = new Placement();
			placement.setOs(form.getOs());
			placement.setPlan_start(form.getPlan_start());
			placement.setPlan_end(form.getPlan_end());
			placement.setPopular_app(0);
			placement.setIs_url_params(0);
			placement.setIs_dsp(form.getIs_dsp());
			if (form.getIs_dsp() == 1) {
				placement.setDsp_id(form.getDsp_id());
			}
			Long placement_id = placementDao.insert(placement);
			CampaignPlacementRel rel = new CampaignPlacementRel();
			rel.setCampaign_id(campaign_id);
			rel.setPlacement_id(placement_id);
			if (isSubmit) {
				rel.setStatus(-40);
			}else {
				rel.setStatus(-50);
			}
			campaignPlacementRelDao.insert(rel);
			Price pe = new Price();
			pe.setCam_ad_id(campaign_id);
			pe.setCam_ad_type(1);
			pe.setCreate_time(new Date());
			pe.setOperater_id(form.getCreate_user());
			pe.setPrice(form.getPrice());
			priceUpdateDao.insert(pe);
		}
		return campaign_id;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: addPubAct
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param activityPublishVo
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void auditingStatus(Long id, Integer ispass, String note,
			SysUserVo manageUser) throws Exception {
		if (id != null) {
			Integer integer = null;
			if (ispass == 1) {
				integer = -30;
			} else {
				integer = -50;
			}
			campaignDao.update(
					"update t_campaign  set online_time=? where id=?",
					new Object[] { new Date(), id });
			campaignPlacementRelDao.updateStatus(id, integer);
			AdAuditLog entity = new AdAuditLog();
			entity.setCreate_time(new Date());
			entity.setIspass(ispass);
			entity.setNote(note);
			entity.setType(0);
			entity.setCreate_user(manageUser.getId());
			adAuditLogDao.insert(entity);

		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findActivityAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignVo> findByPage(CampaignQueryBean bean,
			SysUserVo manageUser, IPageInfo pageInfor) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"V_CAMPAIGN v left join t_campaign_salesman t on v.salesman_id=t.id where 1=1");
		if (StringUtil.isNotEmpty(bean.getCampaign_name())) {
			sb.append(" and (upper(v.campaign_name)  like ? or v.ID = ? )");
			list.add("%" + bean.getCampaign_name().toUpperCase() + "%");
			list.add(bean.getCampaign_id());
		}
		if (StringUtil.isNotEmpty(bean.getAdv())) {
			sb.append(" and (upper(v.company_name) like ? or v.ADV_ID= ? )");
			list.add("%" + bean.getCompany_name() + "%");
			list.add(bean.getAdv_id());
		}
		if (StringUtil.isNotEmpty(bean.getCreate_user())) {
			sb.append(" and v.create_user_name ");
			sb.append(" like '%");
			sb.append(bean.getCreate_user().trim());
			sb.append("%'");
		}

		if (ObjectUtils.isNotEmpty(bean.getStatus())) {
			sb.append(" and v.STATUS= '");
			sb.append(bean.getStatus());
			sb.append("'");
		}
		// if (ObjectUtils.isNotEmpty(manageUser.getId())) {
		// sb.append(" and not(create_user!= '");
		// sb.append(manageUser.getId());
		// sb.append("'");
		// sb.append("and status=0)");
		// }
		if (!StringUtil.isEmpty(bean.getOs())) {
			sb.append(" and v.os= ?");
			list.add(bean.getOs());
		}
		if (!StringUtil.isEmpty(bean.getCharge_type())) {
			sb.append(" and  v.charge_type=?");
			list.add(bean.getCharge_type());
		}
		return (List<CampaignVo>) campaignDao.findByPage(
				"v.*,t.name as salesman_name", sb.toString(), list.toArray(),
				" order by v.create_time desc ", CampaignVo.class, pageInfor);
	}

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
	 */
	@Override
	public CampaignInfoVo getCampaignInfo(Long campaign_id) throws Exception {
		CampaignInfoVo vo = null;
		if (campaign_id != null) {
			vo = this.getCampaignInfoVo(campaign_id);
		}
		// 活动信息查看定向属性

		// 复制

		return vo;

	}

	@SuppressWarnings("unchecked")
	private CampaignInfoVo getCampaignInfoVo(Long campaign_id) throws Exception {
		CampaignInfoVo vo = null;
		List<CampaignInfoVo> list = (List<CampaignInfoVo>) campaignDao.findAll(
				"select * from V_CAMPAIGN where id=" + campaign_id,
				CampaignInfoVo.class);
		if (list != null && list.size() > 0) {
			vo = list.get(0);
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
	 */
	@SuppressWarnings("unchecked")
	public CampaignEditVo getCampaign(Long actId) throws Exception {
		CampaignEditVo view = null;
		List<CampaignEditVo> list = (List<CampaignEditVo>) campaignDao.findAll(
				"*", "V_CAMPAIGN where id=?", new Object[] { actId },
				CampaignEditVo.class);
		if (list != null && list.size() > 0) {
			view = list.get(0);
		}
		return view;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateCampaign
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void updateCampaign(CampaignForm form) throws Exception {

		if (form != null && form.getId() != null
				&& form.getPlacement_id() != null) {

			Campaign campaign = (Campaign) campaignDao.get(form.getId(),
					Campaign.class);
			if (campaign != null) {
				campaign.setBudget(form.getBudget());
				campaign.setCampaign_name(form.getCampaign_name());
				campaign.setCampaign_type(form.getCampaign_type());
				campaign.setCategory_id(form.getCategory_id());
				campaign.setPrice(form.getPrice());
				campaign.setCampaign_required(form.getCampaign_required());
				campaign.setCharge_type(form.getCharge_type());
				campaign.setBalance_cycle(form.getBalance_cycle());
				campaignDao.update(campaign);
				campaignPlacementRelDao.updateStatus(campaign.getId(),
						form.getStatus());// 修改活动状态改为草稿
			}
			Placement placement = (Placement) placementDao.get(
					form.getPlacement_id(), Placement.class);
			placement.setOs(form.getOs());
			placement.setPlan_end(form.getPlan_end());
			placement.setPlan_start(form.getPlan_start());
			placementDao.update(placement);

		}

	}

	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateCampaign
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @param category
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void updateCampaign(CampaignForm form, Long[] category,
			Double price_update) throws Exception {
		/*
		 * 1、更新活动相关信息。 2、更新活动分类。 3、投放下的广告都改成暂停 ----简化广告上线流程，活动重新提交审核不会影响广告状态
		 * 4、更新广告状态
		 */
		if (form != null && form.getId() != null
				&& form.getPlacement_id() != null) {

			// 更新活动相关信息
			Campaign campaign = (Campaign) campaignDao.get(form.getId(),
					Campaign.class);
			if (campaign != null) {
				campaign.setBudget(form.getBudget());
				campaign.setCampaign_name(form.getCampaign_name());
				campaign.setCampaign_type(form.getCampaign_type());
				campaign.setCategory_id(form.getCategory_id());
				campaign.setPrice(form.getPrice());
				campaign.setCampaign_required(form.getCampaign_required());
				campaign.setCharge_type(form.getCharge_type());
				campaign.setBalance_cycle(form.getBalance_cycle());
				campaign.setConfirm_mode(form.getConfirm_mode());
				campaign.setSalesman_id(form.getSalesman_id());
				campaign.setRes_type(form.getRes_type());
				campaignDao.update(campaign);
				campaignPlacementRelDao.updateStatus(campaign.getId(),
						form.getStatus());// 修改活动状态改为草稿
				this.updatePrice(form, price_update);
			}

			// 更新活动分类
			if (category != null && category.length > 0) {
				campaignSortRelDao.updateCategory(form.getId(), category);
			} else {
				campaignSortRelDao.delCategory(form.getId());
			}
			this.updatePlacementByCampaginInfo(form);
		}

	}

	private void updatePrice(CampaignForm form, Double price_update)
			throws Exception {
		Price pe = new Price();
		if (form.getPrice().doubleValue() != price_update.doubleValue()) {
			pe.setCam_ad_id(form.getId());
			pe.setCam_ad_type(1);
			pe.setCreate_time(new Date());
			pe.setOperater_id(form.getCreate_user());
			pe.setPrice(price_update);
			priceUpdateDao.insert(pe);
		}
		// 接入单价的修改
		CampaignPlacementRel rel = campaignPlacementRelDao.getByCampaignId(form
				.getId());
		if (ObjectUtils.isNotEmpty(rel.getPlacement_id())) {
			String config_id = placementDao.getConfigId(rel.getPlacement_id());
			if (ObjectUtils.isEmpty(config_id)) {
				config_id = new String();
			}
			cpIosConfigDao.updatePrice(config_id, form.getPrice());
			CacheUtils.updateConfigAdvPrice(config_id);
		}

	}

	private void updatePlacementByCampaginInfo(CampaignForm form)
			throws Exception {
		// 更新投放信息
		Placement placement = (Placement) placementDao.get(
				form.getPlacement_id(), Placement.class);
		placement.setOs(form.getOs());
		placement.setPlan_end(form.getPlan_end());
		placement.setPlan_start(form.getPlan_start());
		placement.setIs_dsp(form.getIs_dsp());
		placement.setDsp_id(form.getDsp_id());
		placementDao.update(placement);
		// 投放下的广告都改成暂停-----暂时去掉广告暂停操作,
		this.adSuspend(placement.getId());
	}
	
	
	
	
	
	
	
	

	/**
	 * 
	 * <p>
	 * Title: adSuspend
	 * </p>
	 * <p>
	 * Description:活动修改了改为暂停
	 * </p>
	 * 
	 * @param placement_id
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-18
	 * @return void
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private void adSuspend(Long placement_id) throws Exception {
		// 去掉广告暂停操作，刷新所有在线广告状态
		List<Ad> list = (List<Ad>) placementDao.findAll(
				" select * from  t_ad where placement_id= ? and status= ?",
				new Object[] { placement_id, StatusConstant.AD_STATUS_ONLINE },
				Ad.class);
		if (list != null && list.size() > 0) {
			List<Long> adIdList = new ArrayList<Long>();
			for (Ad ad : list) {
				adIdList.add(ad.getId());
			}
			if (adIdList != null) {
				for (Long ad_id : adIdList) {
					CacheUtils.updateAdCache(ad_id);

				}

			}

		}

	}

	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getCampaignInfoByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public CampaignInfoVo getCampaignInfoByPlacement(Long id) throws Exception {
		CampaignInfoVo vo = null;
		if (id != null) {
			CampaignPlacementRel rel = campaignPlacementRelDao
					.getByPlacementId(id);
			if (rel != null) {
				vo = this.getCampaignInfoVo(rel.getCampaign_id());

			}
		}

		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getSalesmanList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CampaignSalesman> getSalesmanList(Long adv_id) throws Exception {
		List<CampaignSalesman> list = null;
		Advertiser adv = advDao.get(adv_id, Advertiser.class);
		if (adv != null) {
			list = campaignSalesmanDao.all();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignOnlineVo> findCampaignOnlineByPage(
			CampaignOnlineSerach bean, IPageInfo pageInfor) throws Exception {
		List<CampaignOnlineVo> list = null;
		String columns = "t.CHARGE_TYPE AS blance_mode ,t.company_name AS adv_name,t.os,t.CAMPAIGN_NAME,t.online_time ";
		StringBuilder sb = new StringBuilder("v_campaign t where 1=1 ");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getAdName())) {
				sb.append(" and t.company_name like '%" + bean.getAdName()
						+ "%'");
			}

			if (!StringUtils.isEmpty(bean.getCampaignName())) {
				sb.append(" and t.campaign_name like '%"
						+ bean.getCampaignName() + "%'");
			}
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and t.os like '%" + bean.getOs() + "%'");
			}
			if (!StringUtils.isEmpty(bean.getBlance_mode())) {
				sb.append(" and t.CHARGE_TYPE like '%" + bean.getBlance_mode()
						+ "%'");
			}
			if (!StringUtils.isEmpty(bean.getOnline_time())) {
				sb.append(" and t.online_time like '%" + bean.getOnline_time()
						+ "%'");
			}

		}
		list = (List<CampaignOnlineVo>) campaignSalesmanDao.findByPage(columns,
				sb.toString(), null, "order by t.create_time desc",
				CampaignOnlineVo.class, pageInfor);

		return list;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateCampaignPrice
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.ICampaignService#updateCampaignPrice(cn.adwalker.ad.admin.ad.form.CampaignForm)
	 */
	@Override
	public void updateCampaignPrice(CampaignPriceForm form,
			Double price_update, SysUserVo sysUser) throws Exception {
		/*
		 * 1、修改活动接入的单价 2、记录单价修改 3、配置单价更改刷新缓存
		 */
		if (form != null && form.getId() != null
				&& form.getPlacement_id() != null) {

			// 更新活动相关信息
			Campaign campaign = (Campaign) campaignDao.get(form.getId(),
					Campaign.class);
			if (campaign != null) {
				campaign.setPrice(form.getPrice());
				campaignDao.update(campaign);

				Price pe = new Price();
				if (form.getPrice().doubleValue() != price_update.doubleValue()) {
					pe.setCam_ad_id(form.getId());
					pe.setCam_ad_type(1);
					pe.setCreate_time(new Date());
					pe.setOperater_id(sysUser.getId());
					pe.setPrice(price_update);
					priceUpdateDao.insert(pe);
					if (form.getOs().equals("ios")) {
						sendMain(campaign.getId(), campaign.getCampaign_name(),
								campaign.getOnline_time(),
								campaign.getCharge_type(), price_update,
								form.getPrice(), sysUser);
					}

				}
				// 接入单价的修改
				CampaignPlacementRel rel = campaignPlacementRelDao
						.getByCampaignId(form.getId());
				if (ObjectUtils.isNotEmpty(rel.getPlacement_id())) {
					String config_id = placementDao.getConfigId(rel
							.getPlacement_id());
					if (ObjectUtils.isEmpty(config_id)) {
						config_id = new String();
					}
					cpIosConfigDao.updatePrice(config_id, form.getPrice());
					CacheUtils.updateConfigAdvPrice(config_id);
				}
			}

		}

	}

	private void sendMain(Long campaign_id, String campaign_name,
			Date online_time, String charge_model, Double price_update,
			Double price_new, SysUserVo sysUser) throws IOException,
			TemplateException, ParseException {
		ApplicationContext ctx = AppContext.getApplicationContext();
		Configuration freadwalkerkerConfig = (Configuration) ctx
				.getBean("freemarkerConfigurer");
		Template temp = freadwalkerkerConfig
				.getTemplate("/template/email/campaign_change.ftl");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", DateUtil.formatDateTime(new Date()));
		map.put("sys_user_name", sysUser.getRealName());
		map.put("campaign_name", campaign_name);
		map.put("online_time", DateUtil.formatDate(online_time, "yyyy-MM-dd"));
		map.put("charge_model", charge_model);
		map.put("price_update", price_update);
		map.put("price_new", price_new);
		map.put("campaign_id", String.valueOf(campaign_id));
		String s = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
		String str = ConfigUtil.getString(ConfigUtil.SEND_MAIL_FOR_CHNAGEMSG);
		if (str != null) {
			String arr[] = str.split(",");
			for (int i = 0; i < arr.length; i++) {
				Mail vo = new Mail();
				vo.setEmail(arr[i]);
				vo.setContent(s);
				vo.setTitle("系统消息:单价变更【" + campaign_name + "】--"
						+ DateUtil.formatDate(new Date()));
				sendMail(vo);
			}
		}

	}

	private void sendMail(Mail vo) {
		if (vo != null) {
			ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
			threadPool.execute(new SendMailTask(vo, vo.getEmail()));
		}

	}

	@Override
	public Campaign getCampaignBaseInfoByPlacement(Long id) throws Exception {
		Campaign vo = null;
		if (id != null) {
			CampaignPlacementRel rel = campaignPlacementRelDao
					.getByPlacementId(id);
			if (rel != null) {
				vo = campaignDao.get(rel.getCampaign_id(), Campaign.class);
			}
		}

		return vo;
	}
}
