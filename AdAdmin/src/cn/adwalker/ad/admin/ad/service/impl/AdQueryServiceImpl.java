package cn.adwalker.ad.admin.ad.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.IAdScheduleDao;
import cn.adwalker.model.ad.domain.AdSchedule;
import cn.adwalker.ad.admin.ad.bean.AdOffLineLogQueryBean;
import cn.adwalker.ad.admin.ad.bean.AdQueryBean;
import cn.adwalker.ad.admin.ad.service.IAdQueryService;
import cn.adwalker.ad.admin.ad.vo.AdOffLineLogVo;
import cn.adwalker.ad.admin.ad.vo.AdQueryVo;

@Service
public class AdQueryServiceImpl implements IAdQueryService {

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
	public List<AdQueryVo> findByPage(AdQueryBean bean, IPageInfo pageInfor)
			throws Exception {
		List<AdQueryVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "a.ID adID,a.CAMPAIGN_ID campaign_id,a.CAMPAIGN_NAME campaign_name,a.placement_name,a.ADV_ID advertisersId,"
				+ "a.ADV_EMAIL advertisersMail,a.adv_name,a.OS os,a.TYPE_ID adForm,a.BLANCE_MODE blanceMode,a.CREATE_TIME submitTime,"
				+ "a.ONLINE_TIME onLineTime,a.OFFLINE_TIME offLineTime,a.STATUS status,a.media_name,l.file_name,l.code,a.ad_type";
		StringBuilder sb = new StringBuilder(
				"V_AD_CAMPAIGN a left join T_PLACEMENT_PACKAGE l on a.PACKAGE_ID=l.id where 1=1 and a.status!=-40 and (a.status is not null)");
		if (bean != null) {
			if (bean.getAd_id() != null) {
				sb.append(" and a.id=? ");
				param.add(bean.getAd_id());
			}
			if (!StringUtils.isEmpty(bean.getFile_name())) {
				sb.append(" and upper(l.file_name) like ? ");
				param.add("%" + bean.getFile_name().trim().toUpperCase() + "%");
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
			if (bean.getAd_type() != null) {
				sb.append(" and a.AD_TYPE=?");
				param.add(bean.getAd_type());
			}

		}
		list = (List<AdQueryVo>) adDao.findByPage(columns, sb.toString(),
				param.toArray(), "order by a.release_time desc",
				AdQueryVo.class, pageInfor);
		if (list != null && list.size() > 0) {
			for (AdQueryVo vo : list) {
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
	 * Title: findOffLineLogByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdQueryService#findOffLineLogByPage(cn.adwalker.ad.admin.ad.bean.AdOffLineLogQueryBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdOffLineLogVo> findOffLineLogByPage(
			AdOffLineLogQueryBean bean, IPageInfo pageInfo) throws Exception {
		List<AdOffLineLogVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "a.ID ad_id,a.CAMPAIGN_ID campaign_id,a.CAMPAIGN_NAME campaign_name,a.ADV_ID adv_id,"
				+ "a.adv_name,a.OS os,a.TYPE_ID type_id,"
				+ "log.ONLINE_TIME,log.OFFLINE_TIME offline_time,log.STATUS status,log.pv,log.click,log.download,log.activate,log.offline_type,log.BUDGET_TYPE,log.BUDGET_DAY";
		StringBuilder sb = new StringBuilder(
				"T_LOG_AD_BUDGET log left join V_AD_CAMPAIGN a on log.ad_id=a.id  where 1=1 and a.status!=-40 and (a.status is not null) and a.ad_type=0");
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
			if (bean.getOffline_type()!= null) {
				sb.append(" and log.offline_type= ?");
				param.add(bean.getOffline_type());
			}
			if (bean.getType_id() != null) {
				sb.append(" and a.TYPE_ID= ?");
				param.add(bean.getType_id());
			}
			if (!StringUtils.isEmpty(bean.getStatic_date())) {
				sb.append(" and log.static_date= ?");
				param.add(bean.getStatic_date());
			}

		}
		list = (List<AdOffLineLogVo>) adDao.findByPage(columns, sb.toString(),
				param.toArray(), "order by log.create_time desc",
				AdOffLineLogVo.class, pageInfo);
		return list;
	}
}
