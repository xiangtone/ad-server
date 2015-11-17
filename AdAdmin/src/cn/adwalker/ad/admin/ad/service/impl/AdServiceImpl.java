package cn.adwalker.ad.admin.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.ad.bean.AdByPlacementBean;
import cn.adwalker.ad.admin.ad.form.AdAddForm;
import cn.adwalker.ad.admin.ad.form.AdEditForm;
import cn.adwalker.ad.admin.ad.service.IAdService;
import cn.adwalker.ad.admin.ad.vo.AdByPlacementVo;
import cn.adwalker.ad.admin.ad.vo.AdChannelVo;
import cn.adwalker.ad.admin.ad.vo.AdVo;
import cn.adwalker.ad.admin.ad.vo.AdertiseAppVo;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.listener.StartupListener;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.IAdScheduleDao;
import cn.adwalker.model.ad.dao.ICampaignPlacementRelDao;
import cn.adwalker.model.ad.dao.IPlacementDao;
import cn.adwalker.model.ad.dao.IPlacementPackageDao;
import cn.adwalker.model.ad.dao.IPriceUpdateDao;
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.AdSchedule;
import cn.adwalker.model.ad.domain.CampaignPlacementRel;
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.model.ad.domain.PlacementPackage;
import cn.adwalker.model.ad.domain.Price;

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
public class AdServiceImpl implements IAdService {

	private static Log logger = LogFactory.getLog(StartupListener.class);

	@Resource
	private IAdDao adDao;

	@Resource
	private IAdScheduleDao adScheduleDao;

	@Resource
	private ICampaignPlacementRelDao campaignPlacementRelDao;

	@Resource
	private IPlacementDao placementDao;
	
	@Resource
	private IPriceUpdateDao priceUpdateDao;
	
	@Resource
	private IPlacementPackageDao placementPackageDao;

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
	 * @see cn.adwalker.ad.admin.ad.service.IAdvService.ad.service.IRegistAdvService#findActivityAll(cn.adwalker.manager.adv.bean.ActivityFind,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<AdertiseAppVo> findAll(String os, Long type_id)
			throws Exception {
		List<AdertiseAppVo> list = null;
		if (!StringUtils.isEmpty(os) && type_id != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("((select app.id,app.name from  T_APPLICATION app  LEFT join  T_PAGE p ON p.app_id=app.id  where app.os='"
					+ os
					+ "' and p.status=1 and app.PLACEMENT=app.id  and p.type_id="
					+ type_id
					+ ")union all(select 0 as id,'其他' as name from dual)) t1 ");
			list = (List<AdertiseAppVo>) adDao.findAll("*", sb.toString(),
					null, " order by id desc ", AdertiseAppVo.class);

		}

		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @param appids
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#insert(java.lang.Long,
	 *      java.lang.String[])
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Long insert(AdAddForm form, Integer adType) throws Exception {
		Long entity_id = null;
		if (form != null && form.getPlacement_id() != null) {
			Boolean flag = null;
			Ad entity = new Ad();
			entity.setAd_type(adType);
			entity.setBlance_mode(form.getBlance_mode());
			entity.setBlance_price(form.getBlance_price());
			entity.setCreate_time(new Date());
			entity.setBudget_day(form.getBudget_day());
			entity.setBudget_type(form.getBudget_type());
			entity.setMedia_id(form.getAppid());
			entity.setPackage_id(form.getPackage_id());
			entity.setPlacement_id(form.getPlacement_id());
			entity.setRes_type(0);
			CampaignPlacementRel rel = campaignPlacementRelDao
					.getByPlacementId(form.getPlacement_id());
			Placement p=placementDao.get(form.getPlacement_id(), Placement.class);
			entity.setAd_name(p.getName());
			entity.setOs(p.getOs());
			PlacementPackage placementpackage=placementPackageDao.get(form.getPackage_id(), PlacementPackage.class);
			if (placementpackage!=null) {
				entity.setAd_mark(placementpackage.getPackage_name());
			}
			if (rel != null
					&& rel.getStatus().equals(
							StatusConstant.CAMPAIGN_STATUS_PLACEMENT_ON_LINE)) {
				if (form.getStart_time() != null
						&& DateUtil.compare(form.getStart_time(), new Date())) {
					flag = false;
					entity.setStatus(StatusConstant.AD_STATUS_INIT);
				} else {
					flag = true;
					entity.setStatus(StatusConstant.AD_STATUS_ONLINE);
					entity.setOnline_time(new Date());
				}
			} else {
				entity.setStatus(StatusConstant.AD_STATUS_OVER_DRAFT);
			}
			entity.setType_id(form.getPage_type());
			entity_id = adDao.insert(entity);
			Price pe=new Price();
			pe.setCam_ad_id(entity_id);
			pe.setCam_ad_type(2);
			pe.setCreate_time(new Date());
			pe.setPrice(form.getBlance_price());
			priceUpdateDao.insert(pe);
			if (flag != null) {
				if (flag) {
					CacheUtils.updateAdCache(entity_id);
				}
			}
		}
		return entity_id;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#updateAd(cn.adwalker.ad.admin.ad.form.AdEditForm)
	 */
	@Override
	public void updateAd(AdEditForm form) throws Exception {
		if (form != null && form.getId() != null) {
			Ad ad = (Ad) adDao.get(form.getId(), Ad.class);
			if (ad != null && ad.getPlacement_id() != null) {
				CampaignPlacementRel rel = (CampaignPlacementRel) campaignPlacementRelDao
						.getByPlacementId(ad.getPlacement_id());
				if (rel != null && rel.getStatus() == 1 && ad.getStatus() == 0) {
					ad.setStatus(1);
				}
				ad.setPackage_id(form.getPackage_id());
				ad.setBudget_day(form.getBudget_day());
				ad.setBudget_type(form.getBudget_type());
				ad.setBlance_price(form.getBlance_price());
				ad.setBlance_mode(form.getBlance_mode());
				adDao.update(ad);
				CacheUtils.updateAdCache(form.getId());
			}
		}

	}
	
	@Override
	public void updateAdCha(AdEditForm form) throws Exception {
		if (form != null && form.getId() != null) {
			Ad ad = (Ad) adDao.get(form.getId(), Ad.class);
			if (ad != null && ad.getPlacement_id() != null) {
				CampaignPlacementRel rel = (CampaignPlacementRel) campaignPlacementRelDao
						.getByPlacementId(ad.getPlacement_id());
				if (rel != null && rel.getStatus() == 1 && ad.getStatus() == 0) {
					ad.setStatus(1);
				}
				ad.setPackage_id(form.getPackage_id());
				ad.setBudget_day(form.getBudget_day());
				ad.setBudget_type(form.getBudget_type());
				ad.setBlance_price(form.getBlance_price());
				ad.setBlance_mode(form.getBlance_mode());
				ad.setStart_time(form.getStart_time());
				ad.setEnd_time(form.getEnd_time());
				adDao.update(ad);
				CacheUtils.updateAdCache(form.getId());
			}
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws Exception {
		adDao.delete(id);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#findByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdVo> findAndroidByPlacement(Long placement_id, Integer ad_type)
			throws Exception {
		return (List<AdVo>) adDao
				.findAll(
						"select ad.id,ad.placement_id,t.id app_id,t.name app_name,"
								+ "ad.budget_day,ad.budget_type,ad.blance_price,ad.blance_mode,ad.type_id,"
								+ "ad.create_user,ad.create_time,ad.package_id,ad.status,ad.start_time,ad.end_time "
								+ "from t_ad ad left join t_application t on ad.media_id=t.id  where ad.placement_id="
								+ placement_id + " and ad_type=" + ad_type
								+ " order by create_time", AdVo.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdChannelVo> findChannelByPlacement(Long placement_id)
			throws Exception {
		List<AdChannelVo> list = null;
		if (placement_id != null) {
			list = (List<AdChannelVo>) adDao
					.findAll(
							"select ad.id,ad.placement_id,t.id channel_id,t.name channel_name,"
									+ "ad.budget_day,ad.budget_type,ad.blance_price,ad.blance_mode,ad.type_id,"
									+ "ad.create_user,ad.create_time,ad.package_id,ad.status,ad.start_time,ad.end_time "
									+ "from t_ad ad left join T_CHANNEL t on ad.media_id=t.id  where ad.placement_id="
									+ placement_id + " and ad_type=" + 1
									+ " order by create_time",
							AdChannelVo.class);
		}

		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAllChannel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param os
	 * @param type_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#findAllChannel(java.lang.String,
	 *      java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<AdertiseAppVo> findAllChannel(Long placement_id, String channel)
			throws Exception {
		List<AdertiseAppVo> list = null;
		if (placement_id != null) {
			Placement placement = (Placement) placementDao.get(placement_id,
					Placement.class);
			if (placement != null && !StringUtils.isEmpty(placement.getOs())) {
				StringBuffer sb = new StringBuffer(" T_CHANNEL where os='"
						+ placement.getOs() + "' and status="
						+ StatusConstant.CHANNEL_STATUS_F);

				List<Object> param = new ArrayList<Object>();
				if (!StringUtils.isEmpty(channel)) {
					long id = 0l;
					if (NumberUtils.isNumber(channel)) {
						id = Long.valueOf(channel);
					}
					sb.append(" and (id=? or upper(name) like ?)");
					param.add(id);
					param.add("%" + channel.toUpperCase() + "%");
				}
				list = (List<AdertiseAppVo>) adDao.findAll("name,id",
						sb.toString(), param.toArray(),
						" order by CREATE_TIME desc ", AdertiseAppVo.class);
			}

		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getStatusByPlacementId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#getStatusByPlacementId(java.lang.Long)
	 */
	@Override
	public Integer getStatusByPlacementId(Long placement_id) throws Exception {
		Integer status = null;
		if (placement_id != null) {
			CampaignPlacementRel rel = campaignPlacementRelDao
					.getByPlacementId(placement_id);
			if (rel != null) {
				status = rel.getStatus();
			}
		}

		return status;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#findByPlacement(cn.adwalker.admin.operation.bean.AdAjustmentBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdByPlacementVo> findByPlacement(AdByPlacementBean bean,
			IPageInfo pageInfo) {
		List<AdByPlacementVo> list = null;
		String columns = "a.ID adID,a.placement_name,a.TYPE_ID adForm,a.BLANCE_MODE blanceMode,a.CREATE_TIME submitTime,"
				+ "a.ONLINE_TIME onLineTime,a.OFFLINE_TIME offLineTime,a.STATUS status,a.media_name,a.ad_type";
		StringBuilder sb = new StringBuilder(
				"V_AD_CAMPAIGN a where 1=1 and a.placement_id="
						+ bean.getPlacement_id() + " and (status is not null)");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getMedia())) {
				sb.append(" and (a.MEDIA_ID=" + bean.getMedia_id()
						+ " or upper(a.MEDIA_NAME) LIKE '%"
						+ bean.getMedia_name().toUpperCase() + "%')");
			}
			if (!StringUtils.isEmpty(bean.getBlanceMode())) {
				sb.append(" and a.BLANCE_MODE='" + bean.getBlanceMode().trim()
						+ "'");
			}
			if (bean.getStatus() != null) {
				sb.append(" and a.status= " + bean.getStatus());
			}

			if (bean.getType_id() != null) {
				sb.append(" and a.TYPE_ID= " + bean.getType_id());
			}
			if (bean.getAd_type() != null) {
				sb.append(" and a.AD_TYPE= " + bean.getAd_type());
			}

		}
		list = (List<AdByPlacementVo>) adDao.findByPage(columns, sb.toString(),
				"order by release_time desc", AdByPlacementVo.class, pageInfo);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#findByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdVo> findByPlacement(Long placement_id) throws Exception {
		return (List<AdVo>) adDao
				.findAll(
						"select ad.id,ad.placement_id,t.id app_id,t.name app_name,"
								+ "ad.budget_day,ad.budget_type,ad.blance_price,ad.blance_mode,ad.type_id,"
								+ "ad.create_user,ad.create_time,ad.package_id,ad.status,ad.start_time,ad.end_time "
								+ "from t_ad ad left join t_application t on ad.media_id=t.id  where ad.placement_id="
								+ placement_id + " order by create_time",
						AdVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adBudget
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#adBudget()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Long> adBudget() throws Exception {
		Date date1 = DateUtil.getDayStart(new Date());
		Date date2 = DateUtil.addMinutes(date1, 90);
		if (DateUtil.compare(new Date(), date1)
				&& DateUtil.compare(date2, new Date())) {
			logger.error("invalid request---"
					+ DateUtil.formatDateTime(new Date()));
			return null;
		}

		List<Ad> list = (List<Ad>) adDao
				.findAll(
						"select * from t_ad where status=1 and budget_day!=0 and (budget_day-cost_day)<=0 and (budget_day is not null)",
						Ad.class);
		List<Long> ids = null;
		if (list != null && list.size() > 0) {
			ids = new ArrayList<Long>();
			for (Ad ad : list) {
				// 否则，写入定时任务
				ids.add(ad.getId());
				AdSchedule adSchedule_on = new AdSchedule();
				adSchedule_on.setAd_id(ad.getId());
				adSchedule_on.setTask_time(DateUtil.getDayStart(DateUtil
						.getDateAddDay(1)));
				adSchedule_on.setType(1);// 上线
				adSchedule_on.setTask_type(3);
				adSchedule_on.setStatus(0);
				adScheduleDao.insert(adSchedule_on);
				CacheUtils.updateAdCache(ad.getId());
			}
			adDao.updateStatus(ids, StatusConstant.AD_STATUS_OVER_AMOUNT);
		}
		return ids;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAdChannel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#updateAdChannel(cn.adwalker.ad.admin.ad.form.AdEditForm)
	 */
	@Override
	public void updateAdChannel(AdEditForm form) throws Exception {
		if (form != null && form.getId() != null) {
			Ad ad = (Ad) adDao.get(form.getId(), Ad.class);
			if (ad != null && ad.getPlacement_id() != null) {
				ad.setStatus(1);
				ad.setPackage_id(form.getPackage_id());
				ad.setBudget_day(form.getBudget_day());
				ad.setBudget_type(form.getBudget_type());
				ad.setBlance_price(form.getBlance_price());
				ad.setBlance_mode(form.getBlance_mode());
				adDao.update(ad);
				// 渠道广告不更新缓存
			}
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insertChannelAD
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @param i
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#insertChannelAD(cn.adwalker.ad.admin.ad.form.AdAddForm,
	 *      int)
	 */
	@Override
	public Long insertChannelAD(AdAddForm form) throws Exception {
		Long entity_id = null;
		if (form != null && form.getPlacement_id() != null) {
			Ad entity = new Ad();
			Placement p=placementDao.get(form.getPlacement_id(), Placement.class);
			entity.setAd_name(p.getName());
			entity.setOs(p.getOs());
			entity.setAd_type(1);
			entity.setBlance_mode(form.getBlance_mode());
			entity.setBlance_price(form.getBlance_price());
			entity.setCreate_time(new Date());
			entity.setBudget_day(form.getBudget_day());
			entity.setBudget_type(form.getBudget_type());
			entity.setMedia_id(form.getAppid());
			entity.setPackage_id(form.getPackage_id());
			entity.setPlacement_id(form.getPlacement_id());
			entity.setStart_time(form.getStart_time());
			entity.setEnd_time(form.getEnd_time());
			entity.setStatus(StatusConstant.AD_STATUS_ONLINE);
			entity.setType_id(form.getPage_type());
			entity_id = adDao.insert(entity);
		}
		return entity_id;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adBudgetonLine
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#adBudgetonLine()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void adBudgetonLine() throws Exception {
		Date date1 = DateUtil.getDayStart(new Date());
		Date date2 = DateUtil.addMinutes(date1, 90);
		if (DateUtil.compare(new Date(), date1)
				&& DateUtil.compare(date2, new Date())) {
			return;
		}

		List<Ad> list = (List<Ad>) adDao
				.findAll(
						"select * from t_ad where status="
								+ StatusConstant.AD_STATUS_OVER_AMOUNT
								+ " and budget_day!=0 and (budget_day-cost_day)>=0 and (budget_day is not null)",
						Ad.class);
		List<Long> ids = null;
		if (list != null && list.size() > 0) {
			ids = new ArrayList<Long>();
			for (Ad ad : list) {
				ids.add(ad.getId());
				CacheUtils.updateAdCache(ad.getId());
			}
			adDao.updateStatus(ids, StatusConstant.AD_STATUS_ONLINE);
		}

	}
}
