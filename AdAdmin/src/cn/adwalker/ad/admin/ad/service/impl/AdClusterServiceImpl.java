package cn.adwalker.ad.admin.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.ad.bean.AdClusterBean;
import cn.adwalker.ad.admin.ad.form.AdClusterEditForm;
import cn.adwalker.ad.admin.ad.service.IAdClusterService;
import cn.adwalker.ad.admin.ad.vo.AdClusterEditVo;
import cn.adwalker.ad.admin.ad.vo.AdClusterVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.IPriceUpdateDao;
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.Price;

@Service
public class AdClusterServiceImpl implements IAdClusterService {

	@Resource
	private IAdDao adDao;
	
	@Resource
	private IPriceUpdateDao priceUpdateDao;

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
	public List<AdClusterVo> findByPage(AdClusterBean bean,
			IPageInfo pageInfor) throws Exception {
		List<AdClusterVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "a.id adID,a.type_id,a.blance_mode,a.CREATE_TIME submitTime,a.ad_name,a.os,"
				+ "a.create_time,a.status,a.ad_type,c.res_code,a.blance_price,c.blance_price as income_price,c.media_status";
		StringBuilder sb = new StringBuilder(
				"t_AD a left join t_ad_cluster c on a.cluster_id=c.id where 1=1 and status!=-40 and ad_type=3 and  (status is not null)");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getAd())) {
				sb.append(" and (a.ID=? or upper(a.ad_NAME) like ?)");
				param.add(bean.getAd_id());
				param.add("%" + bean.getAd_name() .trim().toUpperCase()
						+ "%");
			}

			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and a.OS=?");
				param.add(bean.getOs().trim());
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
			if (!StringUtils.isEmpty(bean.getRes_code())) {
				sb.append(" and c.res_code= ?");
				param.add(bean.getRes_code());
			}
			if (bean.getRes_status()!=null) {
				sb.append(" and c.media_status= ?");
				param.add(bean.getRes_status());
			}

		}
		list = (List<AdClusterVo>) adDao.findByPage(columns, sb.toString(),
				param.toArray(), "order by a.id desc",
				AdClusterVo.class, pageInfor);

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
	public AdClusterEditVo findById(Long adId) throws Exception {
		AdClusterEditVo vo = null;
		if (adId != null) {
			Ad ad= adDao.get(adId, Ad.class);
			vo=new AdClusterEditVo();
			vo.setCharge_type(ad.getBlance_mode());
			vo.setPrice(ad.getBlance_price());
			vo.setBudget_day(ad.getBudget_day());
			vo.setBudget_type(ad.getBudget_type());
			vo.setId(adId);
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
	public void updateAdAjustment(AdClusterEditForm form,Double price_update,Long manger_id) throws Exception {
		Ad ad = (Ad) adDao.get(form.getId(), Ad.class);
		if (ad != null) {
			// ---广告基本信息
			ad.setBlance_mode(form.getCharge_type());
			ad.setBlance_price(form.getPrice());
			ad.setId(form.getId());
			ad.setBudget_day(form.getBudget_day());
			ad.setBudget_type(form.getBudget_type());
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
		CacheUtils.updateAdCache(adId);
		//媒体和广告防止作弊，缓存接口
		CacheUtils.updateAdMediaDeleteCache("alliosadnum");

	}

}
