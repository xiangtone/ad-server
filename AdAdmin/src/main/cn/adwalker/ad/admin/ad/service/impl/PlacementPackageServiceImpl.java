package cn.adwalker.ad.admin.ad.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.ad.dao.IPlacementPackageDao;
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.Campaign;
import cn.adwalker.model.ad.domain.PlacementPackage;
import cn.adwalker.ad.admin.ad.form.IosPackageForm;
import cn.adwalker.ad.admin.ad.form.PackageForm;
import cn.adwalker.ad.admin.ad.service.IPlacementPackageService;
import cn.adwalker.ad.admin.ad.vo.AdvVo;
import cn.adwalker.ad.admin.ad.vo.PlacementPackageEditVo;
import cn.adwalker.ad.admin.ad.vo.PlacementPackageVo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.PinYinUtil;

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
public class PlacementPackageServiceImpl implements IPlacementPackageService {

	@Resource
	private IPlacementPackageDao placementPackageDao;

	@Resource
	private ICampaignDao campaignDao;
	
	@Resource
	private IAdDao adDao;
	
	
	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: uploadPath
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param advertisement
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementPackageService#uploadPath(cn.adwalker.model.ad.domain.Advertisement)
	 */
	@Override
	public PlacementPackageVo uploadPath(PackageForm form) throws Exception {
		PlacementPackageVo result = null;
		if (form != null) {
			PlacementPackage entity = new PlacementPackage();
			entity.setFile_name(form.getFileName());
			entity.setFile_size(form.getResourceSize());
			entity.setPackage_name(form.getPackageName());
			entity.setPlacement_id(form.getPlacement_id());
			entity.setRes_url(form.getResourceUrl());
			entity.setVersion_code(form.getVersionCode());
			entity.setVersion_name(form.getVersionName());
			entity.setCreate_time(new Date());
			entity.setCode(getPackageCode(form.getPlacement_id()));
			entity.setStatus(0);
			Long id = placementPackageDao.insert(entity);
			result = new PlacementPackageVo();
			result.setCode(entity.getCode());
			result.setFile_name(entity.getFile_name());
			result.setFile_size(entity.getFile_size());
			result.setPackage_name(entity.getPackage_name());
			result.setPlacement_id(entity.getPlacement_id());
			result.setRes_url(entity.getRes_url());
			result.setVersion_code(entity.getVersion_code());
			result.setVersion_name(entity.getVersion_name());
			result.setCreate_time(DateUtil.formatDateTime(entity
					.getCreate_time()));
			result.setCode(entity.getCode());
			result.setId(id);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private String getPackageCode(Long placement_id) throws Exception {
		String code = null;
		List<Campaign> list = (List<Campaign>) campaignDao
				.findAll(
						"select c.id,c.campaign_name,c.max_package_code from T_CAMPAIGN_PLACEMENT_REL rel left join T_CAMPAIGN c on c.id=rel.campaign_id where rel.placement_id = "
								+ placement_id, Campaign.class);
		if (list != null && list.size() > 0) {
			Campaign campaign = list.get(0);
			code = PinYinUtil.toPinYin(campaign.getCampaign_name());
			Integer max_package_code = campaign.getMax_package_code();
			if (max_package_code == null) {
				max_package_code = 0;
			}
			max_package_code = max_package_code + 1;
			code = code + "_000" + max_package_code;
			campaignDao.update(
					"update T_CAMPAIGN set max_package_code=? where id=?",
					new Object[] { max_package_code, campaign.getId() });

		}
		return code;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAdvByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#findAdvByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AdvVo findAdvByPlacement(Long placement_id) throws Exception {
		AdvVo vo = null;
		if (placement_id != null) {
			List<AdvVo> list = (List<AdvVo>) placementPackageDao.findAll(
					"select adv_id,adv_email from V_CAMPAIGN where placement_id="
							+ placement_id, AdvVo.class);
			if (list != null && list.size() > 0) {
				vo = list.get(0);
			}
		}
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementService#findByPPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PlacementPackage> findByPlacement(Long placement_id)
			throws Exception {
		return (List<PlacementPackage>) placementPackageDao
				.findAll(
						"select * from T_PLACEMENT_PACKAGE where status>=0 and (code is not null) and  placement_id="
								+ placement_id + " order by create_time",
						PlacementPackage.class);
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
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementPackageService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		placementPackageDao.deletePackage(id);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param code
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementPackageService#update(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void update(Long id, String code) throws Exception {
		if (id != null && !StringUtils.isEmpty(code)) {
			placementPackageDao.update(
					"update T_PLACEMENT_PACKAGE set code=? where id=? ",
					new Object[] { code, id });
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: query
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementPackageService#query(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean query(Long id, String code) throws Exception {
		boolean b = true;
		if (!StringUtils.isEmpty(code)) {
			List<PlacementPackage> list = (List<PlacementPackage>) placementPackageDao
					.findAll("select * from T_PLACEMENT_PACKAGE where code='"
							+ code + "' and id!= " + id, PlacementPackage.class);
			if (list != null && list.size() > 0) {
				b = false;
			}
		}

		return b;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findIosPackageByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementPackageService#findIosPackageByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PlacementPackageEditVo getIosPackageByPlacement(Long placement_id)
			throws Exception {
		PlacementPackageEditVo vo = null;
		if (placement_id != null) {
			List<PlacementPackage> list = (List<PlacementPackage>) placementPackageDao
					.findAll(
							"select * from T_PLACEMENT_PACKAGE where status>=0 and (code is not null) and  placement_id="
									+ placement_id + " order by create_time",
							PlacementPackage.class);
			if (list != null && list.size() > 0) {
				PlacementPackage entity = list.get(0);
				if (entity != null) {
					vo = new PlacementPackageEditVo();
					vo.setCode(entity.getCode());
					if (entity.getFile_size()!=null) {
						vo.setFile_size(entity.getFile_size()/1048576);
					}
					vo.setFile_name(entity.getFile_name());
					vo.setRes_url(entity.getRes_url());
					vo.setAppstore_status(entity.getAppstore_status());
					vo.setAppstore_id(entity.getAppstore_id());
					vo.setVersion_code(entity.getVersion_code());
					vo.setVersion_name(entity.getVersion_name());
					vo.setCreate_time(entity.getCreate_time());
					vo.setPackage_name(entity.getPackage_name());
				}
			}
		}
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: addIosPackage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.ad.service.IPlacementPackageService#addIosPackage(java.lang.Long,
	 *      cn.adwalker.ad.admin.ad.form.IosPackageForm)
	 */
	@Override
	public void addIosPackage(Long placement_id, IosPackageForm form)
			throws Exception {
		PlacementPackage p = placementPackageDao.getByPlacement(placement_id);
		if (p != null && p.getId() != null) {
			p.setCode(form.getCode());
			p.setCreate_time(form.getCreate_time());
			p.setFile_name(form.getFile_name());
			p.setFile_size(form.getFile_size()*1048576);
			p.setPackage_name(form.getPackage_name());
			p.setRes_url(form.getRes_url());
			p.setVersion_code(form.getVersion_code());
			p.setVersion_name(form.getVersion_name());
			p.setAppstore_id(form.getAppstore_id());
			p.setAppstore_status(form.getAppstore_status());
			placementPackageDao.update(p);
			/**
			 * 1、以上更新包的基本信息；
			 * 2、更新广告表中的数据，ad_mark；多个广告。
			 * 3、清空广告缓存。
			 */
			adDao.update("update t_ad set ad_mark=? where package_id=?",new Object[]{p.getPackage_name(),form.getId()});
			List<Ad> ads=adDao.getAdsByByPlacement(placement_id);
			if (ads!=null&&ads.size()>0) {
				for (Ad ad:ads) {
					CacheUtils.updateAdCache(ad.getId());//清理缓存
				}
			}
		} else {
			p = new PlacementPackage();
			p.setOs("ios");
			p.setCode(form.getCode());
			p.setCreate_time(form.getCreate_time());
			p.setFile_name(form.getFile_name());
			p.setFile_size(form.getFile_size()*1048576);
			p.setPackage_name(form.getPackage_name());
			p.setRes_url(form.getRes_url());
			p.setVersion_code(form.getVersion_code());
			p.setVersion_name(form.getVersion_name());
			p.setPlacement_id(placement_id);
			p.setStatus(1);
			p.setAppstore_id(form.getAppstore_id());
			p.setAppstore_status(form.getAppstore_status());
			placementPackageDao.insert(p);
		}

	}

}
