package cn.adwalker.ad.admin.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.IPriceUpdateDao;
import cn.adwalker.model.ad.domain.Ad;
import cn.adwalker.model.ad.domain.Price;
import cn.adwalker.ad.admin.ad.bean.AdChannelBean;
import cn.adwalker.ad.admin.ad.form.AdChannelEditForm;
import cn.adwalker.ad.admin.ad.service.IAdChannelService;
import cn.adwalker.ad.admin.ad.vo.AdChannel;
import cn.adwalker.ad.admin.ad.vo.AdChannelEditVo;

@Service
public class AdChannelServiceImpl implements IAdChannelService {

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
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#findByPage(cn.adwalker.admin.ad.bean.AdAjustmentBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdChannel> findByPage(AdChannelBean bean, IPageInfo pageInfor)
			throws Exception {
		List<AdChannel> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "a.ID adID,a.CAMPAIGN_ID campaign_id,a.CAMPAIGN_NAME campaign_name,a.placement_name,a.ADV_ID advertisersId,"
				+ "a.ADV_EMAIL advertisersMail,a.adv_name,a.OS os,a.TYPE_ID adForm,a.BLANCE_MODE blanceMode,a.CREATE_TIME submitTime,"
				+ "a.ONLINE_TIME onLineTime,a.OFFLINE_TIME offLineTime,a.STATUS status,a.media_name,a.ad_type,b.code cha_page";
		StringBuilder sb = new StringBuilder(
				"V_AD_CAMPAIGN a LEFT JOIN T_PLACEMENT_PACKAGE b ON a.package_id=b.id where 1=1 and a.status!=-40 and a.ad_type=1 and (a.status is not null)");
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
			if (bean.getAd_type() != null) {
				sb.append(" and a.AD_TYPE=?");
				param.add(bean.getAd_type());
			}

		}
		list = (List<AdChannel>) adDao.findByPage(columns, sb.toString(),
				param.toArray(), "order by release_time desc", AdChannel.class,
				pageInfor);
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
	public AdChannelEditVo findById(Long adId) throws Exception {
		AdChannelEditVo vo = null;
		if (adId != null) {
			vo = adDao.findChannelAdById(adId);
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
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#updateAdAjustment(cn.adwalker.admin.operation.form.AdAjustmentEditForm)
	 */

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void updateAdAjustment(AdChannelEditForm form,Double price_update,Long manage_id) throws Exception {
		Ad ad = (Ad) adDao.get(form.getId(), Ad.class);
		if (ad != null) {
			ad.setBlance_mode(form.getCharge_type());
			ad.setBudget_day(form.getBudget_day());
			ad.setPackage_id(form.getPackageId());
			ad.setBlance_price(form.getPrice());
			ad.setBudget_type(form.getBudget_type());
			ad.setId(form.getId());
			adDao.updateAdAjustment(ad);
			CacheUtils.updateAdCache(form.getId());
			//记录投放单价更改
			if(form.getPrice().doubleValue()!=price_update.doubleValue()){
				Price pe=new Price();
					pe.setCam_ad_id(form.getId());
					pe.setCam_ad_type(2);
					pe.setOperater_id(manage_id);
					pe.setCreate_time(new Date());
					pe.setPrice(price_update);
					priceUpdateDao.insert(pe);
				
			}
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
	 * @see cn.adwalker.ad.admin.ad.service.IAdAjustmentService#adSuspend(java.lang.Long)
	 */
	@Override
	public void adOffline(Long adId) {
		adDao.adOffline(adId);

	}

	/**  (non-Javadoc)
	* <p>Title: adOver</p>
	* <p>Description:TODO</p>
	* @param adId
	* @see cn.adwalker.ad.admin.ad.service.IAdChannelService#adOver(java.lang.Long)
	*/
	@Override
	public void adOver(Long adId) {
		adDao.adOver(adId);
	}

}
