package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.AdSchedule;
import cn.adwalker.ad.admin.ad.form.AdAjustmentEditForm;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentEditVo;
import cn.adwalker.ad.admin.app.bean.MediaAdBean;
import cn.adwalker.ad.admin.app.service.IMediaAdService;
import cn.adwalker.ad.admin.app.vo.MediaAdVo;
import cn.adwalker.ad.config.StatusConstant;

@Service
public class MediaAdServiceImpl implements IMediaAdService {

	@Resource
	private IAdDao adDao;

	@Resource
	private IAdScheduleDao adScheduleDao;

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
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#findByPage(cn.adwalker.admin.ad.bean.AdAjustmentBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MediaAdVo> findByPage(MediaAdBean bean, IPageInfo pageInfor)
			throws Exception {
		List<MediaAdVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "a.ID adID,a.CAMPAIGN_ID campaign_id,a.CAMPAIGN_NAME campaign_name,a.placement_name,a.ADV_ID advertisersId,"
				+ "a.ADV_EMAIL advertisersMail,a.adv_name,a.OS os,a.TYPE_ID adForm,a.BLANCE_MODE blanceMode,a.CREATE_TIME submitTime,"
				+ "a.ONLINE_TIME onLineTime,a.OFFLINE_TIME offLineTime,a.STATUS status,a.media_name,a.ad_type";
		StringBuilder sb = new StringBuilder(
				"V_AD_CAMPAIGN a where 1=1 and status!=-40 and ad_type=0 and media_id!=0 and  (status is not null)");
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
		list = (List<MediaAdVo>) adDao.findByPage(columns, sb.toString(),
				param.toArray(), "order by release_time desc", MediaAdVo.class,
				pageInfor);
		if (list != null && list.size() > 0) {
			for (MediaAdVo vo : list) {
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
	@Transactional(rollbackFor = { Exception.class })
	public void updateAdAjustment(AdAjustmentEditForm form) throws Exception {
		Ad ad = (Ad) adDao.get(form.getId(), Ad.class);
		if (ad != null) {
			// ---广告基本信息
			ad.setBlance_mode(form.getCharge_type());
			ad.setBudget_day(form.getBudget_day());
			ad.setPackage_id(form.getPackageId());
			ad.setBlance_price(form.getPrice());
			ad.setBudget_type(form.getBudget_type());
			ad.setId(form.getId());
			// --------------以上更新广告基本信息

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

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: auditAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ad_id
	 * @see cn.adwalker.ad.admin.app.service.IMediaAdService#auditAd(java.lang.Long)
	 */
	@Override
	public void auditAd(Long ad_id) {
		adDao.adAudit(ad_id);

	}

}
