package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.ICampaignPlacementRelDao;
import cn.adwalker.model.ad.dao.IPlacementPackageDao;
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.CampaignPlacementRel;
import cn.adwalker.model.ad.domain.PlacementPackage;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.domain.ApplicationEntity;
import cn.adwalker.model.app.domain.Page;
import cn.adwalker.model.common.dao.IPageDao;
import cn.adwalker.ad.admin.ad.form.AdAddForm;
import cn.adwalker.ad.admin.ad.form.AdEditForm;
import cn.adwalker.ad.admin.app.bean.HistoryAdQuery;
import cn.adwalker.ad.admin.app.bean.MediaBean;
import cn.adwalker.ad.admin.app.service.IMediaService;
import cn.adwalker.ad.admin.app.vo.AdByAppVo;
import cn.adwalker.ad.admin.app.vo.BigAppVo;
import cn.adwalker.ad.admin.app.vo.PlacementVo;
import cn.adwalker.ad.config.StatusConstant;

/**
 * <p>
 * Title: MediaServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-9-6
 */
@Service
public class MediaServiceImpl implements IMediaService {

	@Resource
	private IApplicationDao applicationDao;

	@Resource
	private IPageDao pageDao;

	@Resource
	private IAdDao adDao;

	@Resource
	private ICampaignPlacementRelDao campaignPlacementRelDao;

	@Resource
	private IPlacementPackageDao placementPackageDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#findList(cn.adwalker.admin.operation.bean.Blackbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<BigAppVo> findList(MediaBean bean, IPageInfo pageInfo)
			throws Exception {
		List<BigAppVo> list = null;
		List<Object> param = new ArrayList<Object>();
		//大媒体投放(平台大媒体)
		StringBuilder sb = new StringBuilder(" v_media t where type=? ");
		param.add(0);
		if (bean != null) {
			if (bean.getApp_id() != null) {
				sb.append(" and t.id ='");
				sb.append(bean.getApp_id());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getApp_name())) {
				sb.append(" and upper(t.NAME) like '%");
				sb.append(bean.getApp_name().toUpperCase().trim());
				sb.append("%'");
			}
		}
		list = (List<BigAppVo>) applicationDao.findByPage("*", sb.toString(),
				param.toArray(), "order by id desc", BigAppVo.class, pageInfo);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param os
	 * @param l
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#findAll(java.lang.String,
	 *      long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PlacementVo> findAll(Long app_id, Long type_id,
			String placement_id_name) throws Exception {
		List<PlacementVo> list = null;
		if (app_id != null && type_id != null) {
			ApplicationEntity application = applicationDao.get(app_id,
					ApplicationEntity.class);
			if (application != null) {
				// 根据广告形式查询,并且过滤掉已经投放过的活动
				StringBuilder sb = new StringBuilder();
				sb.append("select v.placement_id as id,v.placement_name as name from  V_CAMPAIGN v where v.status!=? and v.os=?  and exists(select 1 from  T_PLACEMENT_TYPE_REL where PLACEMENT_id=v.placement_id and type_id=? and status=?) and not exists(SELECT  1 from  T_AD where placement_id=v.placement_id and media_id=?) ");
				List<Object> param = new ArrayList<Object>();
				param.add(-1);
				param.add(application.getOs());
				param.add(type_id);
				param.add(1);
				param.add(app_id);
				if (!StringUtils.isEmpty(placement_id_name)) {
					Long pid = 0L;
					if (NumberUtils.isNumber(placement_id_name)) {
						pid = Long.valueOf(placement_id_name);
					}
					sb.append(" and (v.placement_id= ? or v.placement_name like ?)");
					param.add(pid);
					param.add("%" + placement_id_name.trim() + "%");
				}
				list = (List<PlacementVo>) applicationDao.findAll(
						sb.toString(), param.toArray(), PlacementVo.class);
			}
		}

		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findPackage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @param l
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#findPackage(java.lang.Long,
	 *      long)
	 */
	@Override
	public List<PlacementPackage> findPackage(Long placement_id)
			throws Exception {
		return placementPackageDao.findPackage(placement_id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPageStrByApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param app_id
	 * @return
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#getPageStrByApp(java.lang.Long)
	 */
	@Override
	public String getPageStrByApp(Long app_id) {
		String s = null;
		if (app_id != null) {
			StringBuilder sb = new StringBuilder();
			List<Page> list = pageDao.findByApp(app_id);
			if (list != null && list.size() > 0) {
				for (Page page : list) {
					sb.append(page.getType_id() + ",");
				}
				if (sb.length() > 0) {
					sb.deleteCharAt(sb.length() - 1);
					s = sb.toString();
				}

			}
		}
		return s;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insertAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @param i
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#insertAd(cn.adwalker.ad.admin.ad.form.AdAddForm,
	 *      int)
	 */
	@Override
	public Long insertAd(AdAddForm form, int adType) throws Exception {
		Long entity_id = null;
		if (form != null && form.getPlacement_id() != null) {
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
			entity.setStatus(StatusConstant.AD_STATUS_OVER_DRAFT);
			entity.setType_id(form.getPage_type());
			entity_id = adDao.insert(entity);
		}
		return entity_id;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deleteAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#deleteAd(java.lang.Long)
	 */
	@Override
	public void deleteAd(Long id) throws Exception {
		adDao.delete(id);
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

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAndroidByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param app_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#findAndroidByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdByAppVo> findAndroidByPlacement(Long app_id) throws Exception {
		List<AdByAppVo> list = null;
		if (app_id != null) {
			list = (List<AdByAppVo>) adDao
					.findAll(
							"select ad.id,ad.placement_id,t.id app_id,t.name placement_name,"
									+ "ad.budget_day,ad.budget_type,ad.blance_price,ad.blance_mode,ad.type_id,"
									+ "ad.create_user,ad.create_time,ad.package_id,ad.status,ad.start_time,ad.end_time "
									+ "from t_ad ad left join t_placement t on ad.placement_id=t.id  where ad.media_id="
									+ app_id + " and ad_type=" + 0
									+ " and ad.status="
									+ StatusConstant.AD_STATUS_OVER_DRAFT
									+ " and res_type=" + 0
									+ " order by create_time", AdByAppVo.class);
			// 查询这个应用下所有的投放可以使用的包
			if (list != null && list.size() > 0) {
				for (AdByAppVo vo : list) {
					List<PlacementPackage> packages = placementPackageDao
							.findPackage(vo.getPlacement_id());
					vo.setPackages(packages);
				}

			}

		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: submitAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#submitAd(cn.adwalker.ad.admin.ad.form.AdEditForm)
	 */
	@Override
	public void submitAd(Long ad_id) throws Exception {
		adDao.updateStatus(ad_id, StatusConstant.AD_STATUS_FOR_ADUIT);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findHistoryAdByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMediaService#findHistoryAdByPage(cn.adwalker.ad.admin.app.bean.MediaBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdByAppVo> findHistoryAdByPage(HistoryAdQuery bean,
			IPageInfo pageInfo) throws Exception {
		List<AdByAppVo> list = null;
		if (bean.getApp_id() != null) {
			list = (List<AdByAppVo>) adDao
					.findByPage(
							"ad.id,ad.placement_id,t.id app_id,t.name placement_name,"
									+ "ad.budget_day,ad.budget_type,ad.blance_price,ad.blance_mode,ad.type_id,"
									+ "ad.create_user,ad.create_time,ad.package_id,ad.status,ad.start_time,ad.end_time ",
							" t_ad ad left join t_placement t on ad.placement_id=t.id  where ad.media_id="
									+ bean.getApp_id() + " and ad.ad_type=" + 0,
							" order by ad.create_time", AdByAppVo.class,
							pageInfo);
			// 查询这个应用下所有的投放可以使用的包
			if (list != null && list.size() > 0) {
				for (AdByAppVo vo : list) {
					List<PlacementPackage> packages = placementPackageDao
							.findPackage(vo.getPlacement_id());
					vo.setPackages(packages);
				}

			}

		}
		return list;
	}
}
