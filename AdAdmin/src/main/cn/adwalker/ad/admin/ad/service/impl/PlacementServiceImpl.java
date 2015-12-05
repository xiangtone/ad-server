package cn.adwalker.ad.admin.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.ad.bean.PlacementQueryBean;
import cn.adwalker.ad.admin.ad.form.PlacementEditForm;
import cn.adwalker.ad.admin.ad.form.ResForm;
import cn.adwalker.ad.admin.ad.service.IPlacementService;
import cn.adwalker.ad.admin.ad.vo.PlacementEditVo;
import cn.adwalker.ad.admin.ad.vo.PlacementInfoVo;
import cn.adwalker.ad.admin.ad.vo.PlacementVo;
import cn.adwalker.ad.admin.ad.vo.ResAddVo;
import cn.adwalker.ad.admin.ad.vo.ResVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.ad.dao.IAdAuditLogDao;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.ad.dao.ICampaignPlacementRelDao;
import cn.adwalker.model.ad.dao.IPlacementDao;
import cn.adwalker.model.ad.dao.IPlacementResBannerDao;
import cn.adwalker.model.ad.dao.IPlacementResChartboostDao;
import cn.adwalker.model.ad.dao.IPlacementResWallListDao;
import cn.adwalker.model.ad.dao.IPlacementResWallScoreDao;
import cn.adwalker.model.ad.dao.IPlacementScreenshotDao;
import cn.adwalker.model.ad.dao.IPlacementTypeRelDao;
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.AdAuditLog;
import cn.adwalker.model.ad.domain.Campaign;
import cn.adwalker.model.ad.domain.CampaignPlacementRel;
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.model.ad.domain.PlacementResBanner;
import cn.adwalker.model.ad.domain.PlacementResChartboost;
import cn.adwalker.model.ad.domain.PlacementResWallList;
import cn.adwalker.model.ad.domain.PlacementResWallScore;
import cn.adwalker.model.ad.domain.PlacementScreenshot;
import cn.adwalker.model.ad.domain.PlacementTypeRel;
import cn.adwalker.model.ad.domain.Type;

/**
 * 
 * <p>
 * Title: RegistAdvServiceImpl
 * </p>
 * <p>
 * Description:投放服务组件实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-19
 */
@Service("placementService")
public class PlacementServiceImpl implements IPlacementService {

	@Resource
	private IPlacementDao placementDao;

	@Resource
	private ICampaignPlacementRelDao campaignPlacementRelDao;

	@Resource
	private IPlacementResWallScoreDao placementResWallScoreDao;

	@Resource
	private IPlacementResWallListDao placementResWallListDao;

	@Resource
	private IPlacementResBannerDao placementResBannerDao;

	@Resource
	private IPlacementResChartboostDao placementResChartboostDao;

	@Resource
	private IPlacementScreenshotDao placementScreenshotDao;

	@Resource
	private IAdAuditLogDao adAuditLogDao;

	@Resource
	private IAdDao adDao;

	@Resource
	private IPlacementTypeRelDao placementTypeRelDao;
	
	@Resource(name = "campaignDao")
	private ICampaignDao campaignDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdvEmailByPlacement
	 * </p>
	 * <p>
	 * Description:通过投放id获取广告主名称
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#getAdvEmailByPlacement(java.lang.Long)
	 */
	@Override
	public String getAdvEmailByPlacement(Long placement_id) {
		return placementDao.getAdvEmailByPlacement(placement_id);
	}


	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveMaterialChartboost
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param vo
	 * @see cn.adwalker.ad.admin.ad.service.ICampaignService#saveMaterialChartboost(java.lang.Long,
	 *      cn.adwalker.model.ad.domain.PlacementResChartboost)
	 */
	@Override
	public void saveMaterialChartboost(Long id, PlacementResChartboost vo) {
		try {
			placementResChartboostDao.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getRes
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#getRes(java.lang.Long)
	 */
	@Override
	public ResVo getRes(Long id) throws Exception {
		ResVo vo = null;
		if (id != null) {
			vo = new ResVo();
			Placement placement = (Placement) placementDao.get(id,
					Placement.class);
			if (placement != null) {
				vo.setIconimg_url(placement.getIcon_url());
				vo.setOs(placement.getOs());
			}
			List<PlacementScreenshot> list = placementScreenshotDao
					.getByPlacement(id);
			if (list != null && list.size() > 0) {
				for (PlacementScreenshot screenshot : list) {
					if (screenshot.getSort() == 0) {
						vo.setAppimg_url_01(screenshot.getImg_url());
					} else if (screenshot.getSort() == 1) {
						vo.setAppimg_url_02(screenshot.getImg_url());
					} else if (screenshot.getSort() == 2) {
						vo.setAppimg_url_03(screenshot.getImg_url());
					}
				}
			}

			List<Long> typeIds = this.getTypes(id);
			if (typeIds != null) {
				if (typeIds.contains(0L)) {
					PlacementResWallScore wall = placementResWallScoreDao
							.getByPlacement(id);
					if (wall != null) {
						vo.setWall_score_banner_url(wall.getBanner_url());
						vo.setWall_score_desc(wall.getScore_desc());
						vo.setWall_score_long_desc(wall.getScore_long_desc());
						vo.setWall_score_redirect_url(wall.getRedirect_url());
						vo.setWall_score_response_category(wall
								.getResponse_category());
						vo.setWall_score_response_type(wall.getResponse_type());
						vo.setWall_score_weight(wall.getWeight());
						vo.setScore_delay(wall.getScore_delay());
						vo.setWall_score_limit_time(wall.getLimit_time());
						vo.setWall_score_weixin_desc(wall.getWeixin_desc());
					}
					int wall_score_max_weight = placementResWallScoreDao
							.getMaxWeight();
					vo.setWall_score_max_weight(wall_score_max_weight);
				}
				if (typeIds.contains(1L)) {
					PlacementResWallList wallList = placementResWallListDao
							.getByPlacement(id);
					if (wallList != null) {
						vo.setWall_list_banner_url(wallList.getBanner_url());
						vo.setWall_list_redirect_url(wallList.getRedirect_url());
						vo.setWall_list_response_category(wallList
								.getResponse_category());
						vo.setWall_list_response_type(wallList
								.getResponse_type());
						vo.setWall_list_weight(wallList.getWeight());
					}
					int wall_liist_max_weight = placementResWallListDao
							.getMaxWeight();
					vo.setWall_list_max_weight(wall_liist_max_weight);
				}
				if (typeIds.contains(4L)) {
					PlacementResBanner banner = placementResBannerDao
							.getByPlacement(id);
					if (banner != null) {
						vo.setBanner_response_type(banner.getResponse_type());
						vo.setBanner_response_category(banner
								.getResponse_category());
						vo.setBanner_redirect_url(banner.getRedirect_url());
						vo.setBeanner_img_url_first(banner.getImg_url_first());
						vo.setBeanner_img_url_second(banner.getImg_url_second());
						vo.setBeanner_img_url_third(banner.getImg_url_third());
						vo.setBanner_weight(banner.getWeight());
					}
					int banner_max_weight = placementResBannerDao
							.getMaxWeight();
					vo.setBanner_max_weight(banner_max_weight);
				}
				if (typeIds.contains(5L)) {
					PlacementResChartboost chartboost = placementResChartboostDao
							.getByPlacement(id);
					if (chartboost != null) {
						vo.setChartboost_redirect_url(chartboost
								.getRedirect_url());
						vo.setChartboost_response_category(chartboost
								.getResponse_category());
						vo.setChartboost_response_type(chartboost
								.getResponse_type());
						vo.setImg_vertical(chartboost.getImg_vertical());
						vo.setImg_horizontal(chartboost.getImg_horizontal());
						vo.setChartboost_weight(chartboost.getWeight());
					}
					int chartboost_max_weight = placementResChartboostDao
							.getMaxWeight();
					vo.setChartboost_max_weight(chartboost_max_weight);
				}

			}

		}

		return vo;
	}

	/**
	 * 
	 * <p>
	 * Title: getTypes
	 * </p>
	 * <p>
	 * Description:获取类型列表
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-6-26
	 * @return List<Long>
	 * @version 1.0
	 */
	private List<Long> getTypes(Long placement_id) throws Exception {
		List<Long> list = null;
		if (placement_id != null) {
			List<Type> types = placementTypeRelDao
					.getTypeListByPlacement(placement_id);
			if (types != null && types.size() > 0) {
				list = new ArrayList<Long>();
				for (Type type : types) {
					list.add(type.getId());
				}
			}

		}
		return list;

	}

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
	 * @param manageUser
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#findByPage(cn.adwalker.admin.ad.bean.CampaignQueryBean,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PlacementVo> findByPage(PlacementQueryBean bean,
			SysUserVo manageUser, IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"V_CAMPAIGN v left join (select placement_id,GROUP_CONCAT(type_id)type_ids from T_PLACEMENT_TYPE_REL where status=1  group by PLACEMENT_ID)page_type on page_type.PLACEMENT_ID=v.PLACEMENT_ID where STATUS>=-30  ");
		if (StringUtil.isNotEmpty(bean.getCampaign())) {
			sb.append(" and (upper(v.CAMPAIGN_NAME) like ? or v.id= ? )");
			list.add("%" + bean.getCampaign_name().toUpperCase() + "%");
			list.add(bean.getCampaign_id());
		}
		if (StringUtil.isNotEmpty(bean.getAdv())) {
			sb.append(" and (upper(v.company_name)  like ?  or v.ADV_ID = ? )");
			list.add("%" + bean.getCompany_name().toUpperCase() + "%");
			list.add(bean.getAdv_id());

		}
		if (StringUtil.isNotEmpty(bean.getPlacement_name())) {
			sb.append(" and upper(v.placement_name) like ? ");
			list.add("%" + bean.getPlacement_name().toUpperCase() + "%");
		}
		if (!StringUtil.isEmpty(bean.getOs())) {
			sb.append(" and v.os= ? ");
			list.add(bean.getOs());
		}
		if (bean.getType_id() != null && bean.getType_id().length > 0) {
			sb.append(" and (");
			for (int i = 0; i < bean.getType_id().length; i++) {
				if (i == 0) {
					sb.append(" instr(page_type.type_ids,?)!=0");
				} else {
					sb.append("and instr(page_type.type_ids,?)!=0");
				}
				list.add(bean.getType_id()[i]);
			}
			sb.append(")");
		}

		if (ObjectUtils.isNotEmpty(bean.getStatus())) {
			sb.append(" and v.status= '");
			sb.append(bean.getStatus());
			sb.append("'");
		}
		List<PlacementVo> resultList = (List<PlacementVo>) placementDao
				.findByPage("v.*", sb.toString(), list.toArray(),
						" order by v.create_time desc ", PlacementVo.class,
						pageInfo);
		if (resultList != null && resultList.size() > 0) {
			for (PlacementVo vo : resultList) {
				List<Type> typeList = this.getTypeListByPlacement(vo
						.getPlacement_id());
				vo.setTypeList(typeList);

			}

		}
		return resultList;
	}

	/**
	 * 
	 * <p>
	 * Title: getTypeListByPlacement
	 * </p>
	 * <p>
	 * Description:获取墙类型
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-6-1
	 * @return List<Type>
	 * @version 1.0
	 */
	private List<Type> getTypeListByPlacement(Long placement_id)
			throws Exception {
		List<Type> typeList = null;
		if (placement_id != null) {
			typeList = placementTypeRelDao.getTypeListByPlacement(placement_id);
		}
		return typeList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#getPlacement(java.lang.Long)
	 */
	@Override
	public Placement getPlacement(Long id) throws Exception {
		return (Placement) placementDao.get(id, Placement.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: auditingStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param ispass
	 * @param note
	 * @param manageUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#auditingStatus(java.lang.Long,
	 *      java.lang.Integer, java.lang.String,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void audit(Long id, Integer ispass, String note, SysUserVo manageUser)
			throws Exception {
		if (id != null) {
			if (ispass == 1) {
				campaignPlacementRelDao.updateStatusByPlacement(id, 1);
				List<Ad> list = adDao.getAdsByByPlacement(id);
				if (list != null && list.size() > 0) {
					List<Long> onLine = new ArrayList<Long>();
					// 草稿或者暂停的广告才进行操作
					for (Ad ad : list) {
						if (ad.getStatus() != null
								&& (ad.getStatus().equals(
										StatusConstant.AD_STATUS_OVER_DRAFT) || ad
										.getStatus().equals(
												StatusConstant.AD_STATUS_PAUSE))) {
							onLine.add(ad.getId());
						}
					}
					// 如果是当天的，直接上线就行了
					adDao.onlineAd(onLine);

					for (long ad_id : onLine) {
						CacheUtils.updateAdCache(ad_id);
					}

				}
			} else {
				campaignPlacementRelDao.updateStatusByPlacement(id, -20);
			}
			AdAuditLog entity = new AdAuditLog();
			entity.setCreate_time(new Date());
			entity.setObject_id(id);
			entity.setIspass(ispass);
			entity.setNote(note);
			entity.setType(0);
			if (manageUser != null && manageUser.getId() != null) {
				entity.setCreate_user(manageUser.getId());
			}
			adAuditLogDao.insert(entity);
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPlacementEditVo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#getPlacementEditVo(java.lang.Long)
	 */
	@Override
	public PlacementEditVo getPlacementEditVo(Long id) throws Exception {
		PlacementEditVo vo = null;
		if (id != null) {
			Placement entity = (Placement) placementDao
					.get(id, Placement.class);
			if (entity != null) {
				vo = new PlacementEditVo();
				vo.setCampaign_desc(entity.getCampaign_desc());
				vo.setCategory_id(entity.getCategory_id());
				vo.setKeyword(entity.getKeyword());
				vo.setName(entity.getName());
				vo.setSlogan(entity.getSlogan());
				vo.setPriority(entity.getPriority());
				vo.setStar_level(entity.getStar_level());
				vo.setNew_app(entity.getNew_app());
				vo.setPopular_app(entity.getPopular_app());
				vo.setIs_url_params(entity.getIs_url_params());
			}
		}
		return vo;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @param status
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#updateStatus(java.lang.Long,
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo, java.lang.Integer)
	 */
	@Override
	public void updateStatus(Long id, SysUserVo manageUser, Integer status)
			throws Exception {
		campaignPlacementRelDao.updateStatusByPlacement(id, -10);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatePlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IAdService#updatePlacement(cn.adwalker.ad.admin.ad.form.PlacementEditForm)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void updatePlacement(PlacementEditForm form) throws Exception {
		Placement entity = (Placement) placementDao.get(form.getId(),
				Placement.class);
		if (entity != null) {
			entity.setId(form.getId());
			entity.setName(form.getName());
			entity.setStar_level(form.getStar_level());
			entity.setSlogan(form.getSlogan());
			entity.setKeyword(form.getKeyword());
			entity.setCampaign_desc(form.getCampaign_desc());
			entity.setCategory_id(form.getCategory_id());
			entity.setPriority(form.getPriority());
			entity.setNew_app(form.getNew_app());
			//投放的时候不会设置是否是链接会清空数据库字段为null
			if(form.getIs_url_params()!=null){
				entity.setIs_url_params(form.getIs_url_params());
			}else{
				entity.setIs_url_params(0);
			}
			entity.setPopular_app(form.getPopular_app());
			placementDao.update(entity);
			campaignPlacementRelDao.updateStatusByPlacement(entity.getId(),
					form.getStatus());
			adDao.update("update t_ad set ad_name=? where placement_id=?",new Object[]{form.getName(),entity.getId()});

			placementTypeRelDao.saveOrUpdate(form.getId(), form.getTypes());
			// 更新缓存
			List<Ad> ads = adDao.getAdsByByPlacement(form.getId());
			this.updateCahce(ads);
		}
	}

	private void updateCahce(List<Ad> list) {
		for (Ad ad : list) {
			try {
				CacheUtils.updateAdCache(ad.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPlacementInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#getPlacementInfo(java.lang.Long)
	 */
	@Override
	public PlacementInfoVo getPlacementInfo(Long id) throws Exception {
		PlacementInfoVo vo = null;
		if (id != null) {
			Placement entity = (Placement) placementDao
					.get(id, Placement.class);
			if (entity != null) {
				vo = new PlacementInfoVo();
				Campaign cam = campaignDao
						.getConfirmMode(id);
				//获得活动确认方式
				if(ObjectUtils.isNotEmpty(cam.getConfirm_mode())){
					vo.setConfirm_mode(cam.getConfirm_mode());
				}
				vo.setCampaign_desc(entity.getCampaign_desc());
				vo.setCategory_id(entity.getCategory_id());
				vo.setKeyword(entity.getKeyword());
				vo.setName(entity.getName());
				vo.setSlogan(entity.getSlogan());
				vo.setTypeList(this.getTypeListByPlacement(id));
				vo.setOs(entity.getOs());
				vo.setPlan_end(entity.getPlan_end());
				vo.setPlan_start(entity.getPlan_start());
				vo.setIconimg_url(entity.getIcon_url());
				vo.setPriority(entity.getPriority());
				vo.setStar_level(entity.getStar_level());
				vo.setConfig_id(entity.getConfig_id());
				vo.setNew_app(entity.getNew_app());
				vo.setIs_url_params(entity.getIs_url_params());
				vo.setPopular_app(entity.getPopular_app());
				vo.setId(id);

				List<PlacementScreenshot> list = placementScreenshotDao
						.getByPlacement(id);
				if (list != null && list.size() > 0) {
					for (PlacementScreenshot screenshot : list) {
						if (screenshot.getSort() == 0) {
							vo.setAppimg_url_01(screenshot.getImg_url());
						} else if (screenshot.getSort() == 1) {
							vo.setAppimg_url_02(screenshot.getImg_url());
						} else if (screenshot.getSort() == 2) {
							vo.setAppimg_url_03(screenshot.getImg_url());
						}
					}
				}
				if (vo.getTypeList() != null && vo.getTypeList().size() > 0) {
					for (Type type : vo.getTypeList()) {
						if (type != null) {
							if (type.getId() == 0) {
								PlacementResWallScore wall = placementResWallScoreDao
										.getByPlacement(id);
								if (wall != null) {
									vo.setWall_score_banner_url(wall
											.getBanner_url());
									vo.setWall_score_desc(wall.getScore_desc());
									vo.setWall_score_long_desc(wall
											.getScore_long_desc());
									vo.setWall_score_redirect_url(wall
											.getRedirect_url());
									vo.setWall_score_response_category(wall
											.getResponse_category());
									vo.setWall_score_response_type(wall
											.getResponse_type());
									vo.setWall_score_weight(wall.getWeight());
									vo.setScore_delay(wall.getScore_delay());
									int wall_score_max_weight = placementResWallScoreDao
											.getMaxWeight();
									vo.setWall_score_max_weight(wall_score_max_weight);
								}
							} else if (type.getId() == 1) {
								PlacementResWallList wallList = placementResWallListDao
										.getByPlacement(id);
								if (wallList != null) {
									vo.setWall_list_banner_url(wallList
											.getBanner_url());
									vo.setWall_list_redirect_url(wallList
											.getRedirect_url());
									vo.setWall_list_response_category(wallList
											.getResponse_category());
									vo.setWall_list_response_type(wallList
											.getResponse_type());
									vo.setWall_list_weight(wallList.getWeight());
								}
							} else if (type.getId() == 4) {
								PlacementResBanner banner = placementResBannerDao
										.getByPlacement(id);
								if (banner != null) {
									vo.setBanner_response_type(banner
											.getResponse_type());
									vo.setBanner_response_category(banner
											.getResponse_category());
									vo.setBanner_redirect_url(banner
											.getRedirect_url());
									vo.setBeanner_img_url_first(banner
											.getImg_url_first());
									vo.setBeanner_img_url_second(banner
											.getImg_url_second());
									vo.setBeanner_img_url_third(banner
											.getImg_url_third());
									vo.setBanner_weight(banner.getWeight());
								}

							} else if (type.getId() == 5) {
								PlacementResChartboost chartboost = placementResChartboostDao
										.getByPlacement(id);
								if (chartboost != null) {
									vo.setChartboost_redirect_url(chartboost
											.getRedirect_url());
									vo.setChartboost_response_category(chartboost
											.getResponse_category());
									vo.setChartboost_response_type(chartboost
											.getResponse_type());
									vo.setImg_vertical(chartboost
											.getImg_vertical());
									vo.setImg_horizontal(chartboost
											.getImg_horizontal());
									vo.setChartboost_weight(chartboost
											.getWeight());
									int chartboost_max_weight = placementResChartboostDao
											.getMaxWeight();
									vo.setChartboost_max_weight(chartboost_max_weight);
								}

							}
						}
					}
				}

			}
		}
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findTypesByplacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#findTypesByplacement(java.lang.Long)
	 */
	@Override
	public List<PlacementTypeRel> findTypesByplacement(Long id) {
		return placementTypeRelDao.findByPlacement(id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getTypeStr
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#getTypeStr(java.lang.Long)
	 */
	@Override
	public String getTypeStr(Long id) {
		String string = null;
		if (id != null) {
			List<PlacementTypeRel> pageTypeVos = placementTypeRelDao
					.findByPlacement(id);
			StringBuilder sb = new StringBuilder();
			if (pageTypeVos != null && pageTypeVos.size() > 0) {
				for (PlacementTypeRel pageType : pageTypeVos) {
					sb.append(pageType.getType_id() + ",");
				}
				sb = sb.deleteCharAt(sb.length() - 1);
			}
			string = sb.toString();
		}
		return string;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getResAdd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#getResAdd(java.lang.Long)
	 */
	@Override
	public ResAddVo getResAdd(Long id) throws Exception {
		ResAddVo vo = null;
		if (id != null) {
			vo = new ResAddVo();
			Placement placement = (Placement) placementDao.get(id,
					Placement.class);
			if (placement != null) {
				vo.setIconimg_url(placement.getIcon_url());
			}
			List<PlacementScreenshot> list = placementScreenshotDao
					.getByPlacement(id);
			if (list != null && list.size() > 0) {
				for (PlacementScreenshot screenshot : list) {
					if (screenshot.getSort() == 0) {
						vo.setAppimg_url_01(screenshot.getImg_url());
					} else if (screenshot.getSort() == 1) {
						vo.setAppimg_url_02(screenshot.getImg_url());
					} else if (screenshot.getSort() == 2) {
						vo.setAppimg_url_03(screenshot.getImg_url());
					}
				}
			}
			PlacementResWallScore wall = placementResWallScoreDao
					.getByPlacement(id);
			if (wall != null) {
				vo.setWall_score_banner_url(wall.getBanner_url());
				vo.setWall_score_desc(wall.getScore_desc());
				vo.setWall_score_long_desc(wall.getScore_long_desc());
			}

			PlacementResWallList wallList = placementResWallListDao
					.getByPlacement(id);
			if (wallList != null) {
				vo.setWall_list_banner_url(wallList.getBanner_url());
			}

			PlacementResBanner banner = placementResBannerDao
					.getByPlacement(id);
			if (banner != null) {
				vo.setBeanner_img_url_first(banner.getImg_url_first());
				vo.setBeanner_img_url_second(banner.getImg_url_second());
				vo.setBeanner_img_url_third(banner.getImg_url_third());
			}

			PlacementResChartboost chartboost = placementResChartboostDao
					.getByPlacement(id);
			if (chartboost != null) {
				vo.setImg_vertical(chartboost.getImg_vertical());
				vo.setImg_horizontal(chartboost.getImg_horizontal());
			}

		}

		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateMaterial
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#updateMaterial(java.lang.Long,
	 *      cn.adwalker.ad.admin.ad.form.ResForm)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void updateMaterial(Long id, ResForm form) throws Exception {
		if (id != null && form != null) {
			// 存储应用截图
			if (form.getAppimg_url() != null && form.getAppimg_url().length > 0) {
				placementScreenshotDao.saveOrUpdate(id, form.getAppimg_url());
			}
			// iocUrl
			if (!StringUtil.isEmpty(form.getIconimg_url())) {
				placementDao.update(id, form.getIconimg_url());
			}

			List<Long> typeIds = this.getTypes(id);
			if (typeIds != null) {
				if (typeIds.contains(0L)) {
					PlacementResWallScore resWall = new PlacementResWallScore();
					resWall.setBanner_url(form.getWall_score_banner_url());
					resWall.setPlacement_id(id);
					resWall.setScore_desc(form.getWall_score_desc());
					resWall.setScore_long_desc(form.getWall_score_long_desc());
					resWall.setRedirect_url(form.getWall_score_redirect_url());
					resWall.setResponse_type(form.getWall_score_response_type());
					resWall.setResponse_category(form
							.getWall_score_response_category());
					resWall.setWeight(form.getWall_score_weight());
					resWall.setScore_delay(form.getScore_delay());
					resWall.setWeixin_desc(form.getWall_score_weixin_desc());
					resWall.setLimit_time(form.getWall_score_limit_time());
					placementResWallScoreDao.saveOrUpdate(resWall);
				}
				if (typeIds.contains(1L)) {
					// 插入墙
					PlacementResWallList resWallList = new PlacementResWallList();
					resWallList.setBanner_url(form.getWall_list_banner_url());
					resWallList.setPlacement_id(id);
					resWallList.setRedirect_url(form
							.getWall_list_redirect_url());
					resWallList.setResponse_type(form
							.getWall_list_response_type());
					resWallList.setResponse_category(form
							.getWall_list_response_category());
					resWallList.setWeight(form.getWall_list_weight());
					placementResWallListDao.saveOrUpdate(resWallList);
				}
				if (typeIds.contains(4L)) {
					// banner
					PlacementResBanner resBanner = new PlacementResBanner();
					resBanner.setImg_url_first(form.getBanner01_url());
					resBanner.setImg_url_second(form.getBanner02_url());
					resBanner.setImg_url_third(form.getBanner03_url());
					resBanner.setResponse_type(form.getBanner_response_type());
					resBanner.setResponse_category(form
							.getBanner_response_category());
					resBanner.setPlacement_id(id);
					resBanner.setRedirect_url(form.getBanner_redirect_url());
					resBanner.setWeight(form.getBanner_weight());
					placementResBannerDao.saveOrUpdate(resBanner);
				}
				if (typeIds.contains(5L)) {
					// 插屏
					PlacementResChartboost chartboost = new PlacementResChartboost();
					chartboost.setImg_horizontal(form.getChartboost_x_url());
					chartboost.setImg_vertical(form.getChartboost_y_url());
					chartboost.setRedirect_url(form
							.getChartboost_redirect_url());
					chartboost.setResponse_category(form
							.getChartboost_response_catelog());
					chartboost.setResponse_type(form
							.getChartboost_response_type());
					chartboost.setPlacement_id(id);
					chartboost.setWeight(form.getChartboost_weight());
					placementResChartboostDao.saveOrUpdate(chartboost);
				}
			}
			campaignPlacementRelDao.updateStatusByPlacement(id,
					StatusConstant.CAMPAIGN_STATUS_PLACEMENT_CAOGAO_STEP_TOW);

			// 更新缓存
			List<Ad> ads = adDao.getAdsByByPlacement(id);
			for (Ad ad : ads) {
				CacheUtils.updateAdCache(ad.getId());
			}

		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: submitPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#submitPlacement(java.lang.Long)
	 */
	@Override
	public void submitPlacement(Long id) throws Exception {
		if (id != null) {
			campaignPlacementRelDao.updateStatusByPlacement(id,
					StatusConstant.CAMPAIGN_STATUS_PLACEMENT_AUDIT);
			// 更新缓存
			List<Ad> ads = adDao.getAdsByByPlacement(id);
			for (Ad ad : ads) {
				CacheUtils.updateAdCache(ad.getId());
			}

		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: Offline
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#Offline(java.lang.String)
	 */
	@Override
	public void Offline(String ids) throws Exception {
		if (!StringUtils.isEmpty(ids)) {
			String arr[] = ids.split(",");
			if (arr != null && arr.length > 0) {
				for (int i = 0; i < arr.length; i++) {
					if (NumberUtils.isNumber(arr[i])) {
						Long placement_id = Long.valueOf(arr[i]);
						this.placementOffline(placement_id);
					}
				}
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Title: placementOffline
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @author cuidd
	 * @date 2013-7-18
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	private void placementOffline(Long placement_id) throws Exception {
		CampaignPlacementRel rel = campaignPlacementRelDao
				.getByCampaignId(placement_id);
		placementDao.offline(rel.getId());
		List<Ad> list = adDao.getAdsByByPlacement(rel.getPlacement_id());
		List<Long> adIdList = new ArrayList<Long>();
		for (Ad ad : list) {
			adIdList.add(ad.getId());
		}
		adDao.updateStatus(adIdList, StatusConstant.AD_STATUS_OVER);
		if (adIdList != null) {
			for (Long ad_id : adIdList) {
				CacheUtils.updateAdCache(ad_id);

			}

		}

	}

}
