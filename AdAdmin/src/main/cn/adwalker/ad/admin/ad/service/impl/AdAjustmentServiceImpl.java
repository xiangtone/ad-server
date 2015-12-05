package cn.adwalker.ad.admin.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.IAdScheduleDao;
import cn.adwalker.model.ad.dao.IAdSortRelDao;
import cn.adwalker.model.ad.dao.IPriceUpdateDao;
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.AdSchedule;
import cn.adwalker.model.ad.domain.Price;
import cn.adwalker.model.common.dao.ISysSortDao;
import cn.adwalker.model.common.domain.Sort;
import cn.adwalker.ad.admin.ad.bean.AdAjustmentBean;
import cn.adwalker.ad.admin.ad.form.AdAjustmentEditForm;
import cn.adwalker.ad.admin.ad.form.AdCategoryForm;
import cn.adwalker.ad.admin.ad.service.IAdAjustmentService;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentEditVo;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentVo;
import cn.adwalker.ad.admin.ad.vo.AdCategoryVo;
import cn.adwalker.ad.config.StatusConstant;

@Service
public class AdAjustmentServiceImpl implements IAdAjustmentService {

	@Resource
	private IAdDao adDao;

	@Resource
	private IAdScheduleDao adScheduleDao;
	
	@Resource
	private IPriceUpdateDao priceUpdateDao;
	
	@Resource
	private ISysSortDao sysSortDao;
	
	@Resource
	private IAdSortRelDao adSortRelDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#findByPage(cn.adwalker.ad.admin.ad.bean.AdAjustmentBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdAjustmentVo> findByPage(AdAjustmentBean bean,
			IPageInfo pageInfor) throws Exception {
		List<AdAjustmentVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "a.ID adID,a.CAMPAIGN_ID campaign_id,a.CAMPAIGN_NAME campaign_name,a.placement_name,a.ADV_ID advertisersId,"
				+ "a.ADV_EMAIL advertisersMail,a.adv_name,a.OS os,a.TYPE_ID adForm,a.BLANCE_MODE blanceMode,a.CREATE_TIME submitTime,"
				+ "a.ONLINE_TIME onLineTime,a.OFFLINE_TIME offLineTime,a.STATUS status,a.media_name,a.ad_type";
		StringBuilder sb = new StringBuilder(
				"V_AD_CAMPAIGN a where 1=1 and status!=-40 and ad_type=0 and  (status is not null)");
		if (bean != null) {
			if (bean.getAd_id() != null) {
				sb.append(" and a.id=? ");
				param.add(bean.getAd_id());
			}
			if (!StringUtils.isEmpty(bean.getCampaign())) {
				sb.append(" and (a.CAMPAIGN_ID=? or upper(a.CAMPAIGN_NAME) like ?)");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			if (!StringUtils.isEmpty(bean.getAdv())) {
				sb.append(" and (a.ADV_ID=? or upper(a.ADV_NAME) like ?)");
				param.add(bean.getAdv_id());
				param.add("%" + bean.getAdv().trim().toUpperCase() + "%");
			}

			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and a.OS=?");
				param.add(bean.getOs().trim());
			}

			if (!StringUtils.isEmpty(bean.getMedia())) {
				sb.append(" and (a.MEDIA_ID=? or upper(a.MEDIA_NAME) LIKE ?)");
				param.add(bean.getMedia_id());
				param.add("%" + bean.getMedia_name().trim().toUpperCase() + "%");

			}
			if (!StringUtils.isEmpty(bean.getBlanceMode())) {
				sb.append(" and a.BLANCE_MODE=?");
				param.add(bean.getBlanceMode().trim());
			}
			if (bean.getStatus() != null) {
				sb.append(" and a.status= ?");
				param.add(bean.getStatus());
			}

			if (bean.getType_id() != null) {
				sb.append(" and a.TYPE_ID= ?");
				param.add(bean.getType_id());
			}

		}
		list = (List<AdAjustmentVo>) adDao.findByPage(columns, sb.toString(),
				param.toArray(), "order by release_time desc",
				AdAjustmentVo.class, pageInfor);
		if (list != null && list.size() > 0) {
			for (AdAjustmentVo vo : list) {
				if (vo != null) {
					AdSchedule schedule_s = adScheduleDao.getAdScheduleStart(vo
							.getAdId());
					AdSchedule schedule_e = adScheduleDao.getAdScheduleEnd(vo
							.getAdId());
					if (schedule_s == null || schedule_s.getStatus() != 0) {
						vo.setSchedule_start(false);
					} else {
						vo.setSchedule_start(true);
						vo.setSchedule_start_time(schedule_s.getTask_time());
					}

					if (schedule_e == null || schedule_e.getStatus() != 0) {
						vo.setSchedule_end(false);
					} else {
						vo.setSchedule_end(true);
						vo.setSchedule_end_time(schedule_e.getTask_time());
					}

				}

			}

		}

		return list;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#findById(java.lang.Long)
	 */
	@Override
	public AdAjustmentEditVo findById(Long adId) throws Exception {
		AdAjustmentEditVo vo = null;
		if (adId != null) {
			vo = adDao.findByAdId(adId);
			if (vo != null) {
				AdSchedule schedule_s = adScheduleDao.getAdScheduleStart(adId);
				AdSchedule schedule_e = adScheduleDao.getAdScheduleEnd(adId);
				if (schedule_s == null || schedule_s.getStatus() != 0) {
					vo.setSchedule_start(false);
				} else {
					vo.setSchedule_start(true);
					vo.setSchedule_start_time(schedule_s.getTask_time());
				}

				if (schedule_e == null || schedule_e.getStatus() != 0) {
					vo.setSchedule_end(false);
				} else {
					vo.setSchedule_end(true);
					vo.setSchedule_end_time(schedule_e.getTask_time());
				}

			}
		}
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAdAjustment
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#updateAdAjustment(cn.adwalker.ad.admin.ad.form.AdAjustmentEditForm)
	 */

	@Override
	@Transactional(rollbackFor = {Exception.class })
	public void updateAdAjustment(AdAjustmentEditForm form,Double price_update,Long manger_id) throws Exception {
		Ad ad = (Ad) adDao.get(form.getId(), Ad.class);
		if (ad != null) {
			// ---广告基本信息
			ad.setBlance_mode(form.getCharge_type());
			ad.setBudget_day(form.getBudget_day());
			ad.setPackage_id(form.getPackageId());
			ad.setBlance_price(form.getPrice());
			ad.setBudget_type(form.getBudget_type());
			ad.setId(form.getId());
			ad.setFast_task(form.getFast_task());
			ad.setConfirm_type(form.getConfirm_type());
			// --------------以上更新广告基本信息
			//记录投放单价更改
			if(form.getPrice().doubleValue()!=price_update.doubleValue()){
				Price pe=new Price();
					pe.setCam_ad_id(form.getId());
					pe.setCam_ad_type(2);
					pe.setOperater_id(manger_id);
					pe.setCreate_time(new Date());
					pe.setPrice(price_update);
					priceUpdateDao.insert(pe);
				
			}
			
			// 上线时间
			if (form.getPlan_start() != null
					&& form.getSchedule_start() != null
					&& form.getSchedule_start() == 1) {
				AdSchedule adSchedule_on = new AdSchedule();
				if (ad.getStatus() != StatusConstant.AD_STATUS_ONLINE) {
					ad.setStatus(StatusConstant.AD_STATUS_INIT);
					adSchedule_on.setStatus(0);
				} else {
					adSchedule_on.setStatus(-2);
				}

				adSchedule_on.setAd_id(form.getId());
				adSchedule_on.setTask_time(form.getPlan_start());
				adSchedule_on.setType(1);
				adSchedule_on.setTask_type(1);
				adScheduleDao.saveOrUpdate(adSchedule_on);
			} else {
				// 否则的话取消更新定时任务的上线状态为无效的
				adScheduleDao.updateStatus(ad.getId(), 1, 0, -3);// 状态为0（未执行）的改成-3失效的
				if (ad.getStatus() == StatusConstant.AD_STATUS_OVER_AMOUNT) {
					ad.setStatus(StatusConstant.AD_STATUS_OFFLINE);// 如果状态是超量下线的广告，取消定时，广告状态变为下线

				}
			}

			// 判断有没有选择开启定时
			if (form.getPlan_end() != null && form.getSchedule_end() != null
					&& form.getSchedule_end() == 1) {
				AdSchedule adSchedule_off = new AdSchedule();
				adSchedule_off.setAd_id(form.getId());
				adSchedule_off.setTask_time(DateUtil.addMilliseconds(
						form.getPlan_end(), 2));
				adSchedule_off.setStatus(0);
				adSchedule_off.setType(-1);
				adSchedule_off.setTask_type(2);
				adScheduleDao.saveOrUpdate(adSchedule_off);
			} else {
				adScheduleDao.updateStatus(ad.getId(), -1, 0, -3);
			}

			adDao.updateAdAjustment(ad);
			CacheUtils.updateAdCache(form.getId());

		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adOnline
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#adOnline(java.lang.Long)
	 */
	@Override
	public void adOnline(Long adId) {
		adDao.adOnline(adId);
		CacheUtils.updateAdCache(adId);
		//媒体和广告防止作弊，缓存接口
		CacheUtils.updateAdMediaDeleteCache("alliosadnum");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adOffline
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#adOffline(java.lang.Long)
	 */
	@Override
	public void adbatchOffline(String ids) {
		if (!StringUtils.isEmpty(ids)) {
			String arr[] = ids.split(",");
			if (arr != null && arr.length > 0) {
				for (int i = 0; i < arr.length; i++) {
					if (NumberUtils.isNumber(arr[i])) {
						Long ad_id = Long.valueOf(arr[i]);
						adDao.adOffline(ad_id);
						CacheUtils.updateAdCache(ad_id);						
					}
				}
			}
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adSuspend
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#adSuspend(java.lang.Long)
	 */
	@Override
	public void adOffline(Long adId) throws Exception {
		adDao.adOffline(adId);// 更新广告状态
		// 取消该广告的定时上线操作
		adScheduleDao.updateStatus(adId, 1, 0, -3);
		CacheUtils.updateAdCache(adId);
		//媒体和广告防止作弊，缓存接口
		CacheUtils.updateAdMediaDeleteCache("alliosadnum");

	}
	
	/**
	 * //活动信息查看修改定向属性 (non-Javadoc)
	 * <p>
	 * Title: updatecategory
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
	@Override
	public List<AdCategoryVo> getCategory(Long campaign_id)
			throws Exception {
		// 活动信息查看定向属性
		List<AdCategoryVo> list = null;
		if (campaign_id != null) {
			list = (List<AdCategoryVo>) adDao.findAll(
					"select * from V_CAMPAIGN_CATEGORY t  where 1=1 and campaign_id= "
							+ campaign_id, AdCategoryVo.class);

		}
		return list;

	}
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: queryAllCategory
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public Map<String, List<Sort>> queryAllCategory() throws Exception {
		Map<String, List<Sort>> map = new HashMap<String, List<Sort>>();
		List<Sort> list = sysSortDao.queryAll();
		if (list != null && list.size() > 0) {
			for (Sort c : list) {

				List<Sort> temp = map.get(String.valueOf(c.getType()));
				if (temp != null) {
					temp.add(c);
				} else {
					temp = new ArrayList<Sort>();
					temp.add(c);
					map.put(String.valueOf(c.getType()), temp);
				}
			}
		}
		return map;
	}
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: queryAllCity
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, List<Sort>> queryAllCity() throws Exception {

		Map<String, List<Sort>> map = new HashMap<String, List<Sort>>();
		List<Sort> list = sysSortDao.queryAllCity();
		if (list != null && list.size() > 0) {
			for (Sort c : list) {

				List<Sort> temp = map.get(String.valueOf(c.getType()));
				if (temp != null) {
					temp.add(c);
				} else {
					temp = new ArrayList<Sort>();
					temp.add(c);
					map.put(String.valueOf(c.getType()), temp);
				}
			}
		}
		return map;
	}
	
	@Override
	public void updateAdCategory(AdCategoryForm form, Long[] category) throws Exception {
		/*
		 * 1、更新活动相关信息。 2、更新活动分类。 3、投放下的广告都改成暂停 ----简化广告上线流程，活动重新提交审核不会影响广告状态
		 * 4、更新广告状态
		 */
		if (form != null && form.getId() != null) {
			// 更新活动分类
			if (category != null && category.length > 0) {
				adSortRelDao.updateCategory(form.getId(), category);
			} else {
				adSortRelDao.delCategory(form.getId());
			}
			this.update1(form);
		}
	}
	
	private void update1(AdCategoryForm form)
			throws Exception {
		// 更新投放信息
		Ad entity = (Ad) adDao.get(
				form.getId(), Ad.class);
		// area_directional
		List<Sort> area_directional = adSortRelDao.findCampaignCity(
				form.getId(), 6);
		if (area_directional != null && area_directional.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Sort c : area_directional) {
				List<Sort> area_name = sysSortDao.findSortCity(c.getSort());
				for (Sort d : area_name) {
					sb.append(d.getName()).append(",");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			entity.setArea_directional_str(sb.toString());
		} else {
			entity.setArea_directional_str(null);
		}

		// sdk_version
		
		List<Sort> sdk_version = adSortRelDao.findCampaignCity(
				form.getId(), 1);
		if (sdk_version != null && sdk_version.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Sort c : sdk_version) {
				List<Sort> sdk_name = sysSortDao.findSortCity(c.getSort());
				for (Sort d : sdk_name) {
					sb.append(d.getName()).append(",");
				}
			}
			// sb.deleteCharAt(sb.length() - 1);
			entity.setSdk_version_str(sb.toString());
		} else {
			entity.setSdk_version_str(null);
		}
		
		// app_type
		List<Sort> app_type = adSortRelDao.findCampaignCategory(
				form.getId(), 4);
		if (app_type != null && app_type.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Sort c : app_type) {
				sb.append(c.getContent_value()).append(",");
			}
			entity.setApp_type_str(sb.toString());
		}else {
			entity.setApp_type_str(null);
		}
		// terminal_type_str
		entity.setTerminal_type_str(null);
		List<Sort> terminal_type = adSortRelDao.findCampaignCategory(
				form.getId(), 0);
		if (terminal_type != null && terminal_type.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Sort c : terminal_type) {
				sb.append(c.getContent_value()).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			entity.setTerminal_type_str(sb.toString());
		}
		// time_directional_str
		entity.setTime_directional_str(null);
		List<Sort> time_directional = adSortRelDao
				.findCampaignCategory(form.getId(), 5);
		if (time_directional != null && time_directional.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Sort c : time_directional) {
				sb.append(c.getContent_value()).append(",");
			}
			entity.setTime_directional_str(sb.toString());
		}
		entity.setPhone_brand_str(null);
		List<Sort> phone_brand = adSortRelDao.findCampaignCategory(
				form.getId(), 3);
		if (phone_brand != null && phone_brand.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Sort c : phone_brand) {
				sb.append(c.getContent_value()).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			entity.setPhone_brand_str(sb.toString());
		}
		entity.setOperat_agencies_str(null);
		
			List<Sort> operat_agencies = adSortRelDao
					.findCampaignCategory(form.getId(), 2);
			if (operat_agencies != null && operat_agencies.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (Sort c : operat_agencies) {
					sb.append(c.getContent_value()).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				entity.setOperat_agencies_str(sb.toString());
			}
			adDao.update(entity);
		// 投放下的广告都改成暂停-----暂时去掉广告暂停操作,
//		this.adSuspend(placement.getId());
	}
	

}
