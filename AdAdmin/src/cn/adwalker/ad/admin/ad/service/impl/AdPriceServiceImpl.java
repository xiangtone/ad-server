package cn.adwalker.ad.admin.ad.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.dao.IPriceUpdateDao;
import cn.adwalker.model.ad.domain.Price;
import cn.adwalker.ad.admin.ad.service.IAdPriceService;
import cn.adwalker.ad.admin.operation.bean.AdPriceBean;
import cn.adwalker.ad.admin.operation.vo.AdPriceVo;

@Service
public class AdPriceServiceImpl implements IAdPriceService {

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
	 * @see cn.adwalker.ad.admin.ad.service.IAdPriceService#findByPage(cn.adwalker.ad.admin.operation.bean.AdPriceBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<AdPriceVo> findByPage(AdPriceBean bean, IPageInfo pageInfor)
			throws Exception {
		List<AdPriceVo> list = null;
		List<Object> param = new ArrayList<Object>();
		String columns = "ID,CAMPAIGN_ID,CAMPAIGN_NAME,ADV_EMAIL,OS os,TYPE_ID adForm,media_id,media_name,blance_mode blanceMode,blance_price currentPrice,RELEASE_TIME effectTime";
		StringBuilder sb = new StringBuilder("V_AD_CAMPAIGN  where 1=1");
		if (bean != null) {
			if (bean.getAd_id() != null) {
				sb.append(" and id=? ");
				param.add(bean.getAd_id());
			}
			if (!StringUtils.isEmpty(bean.getCampaign())) {
				sb.append(" and (CAMPAIGN_ID=? or upper(CAMPAIGN_NAME) like ?)");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			if (!StringUtils.isEmpty(bean.getAdv())) {
				sb.append(" and (ADV_ID=? or upper(ADV_EMAIL) like ?)");
				param.add(bean.getAdv_id());
				param.add("%" + bean.getAdv().trim().toUpperCase() + "%");
			}

			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and OS=?");
				param.add(bean.getOs().trim());
			}

			if (!StringUtils.isEmpty(bean.getMedia())) {
				sb.append(" and (MEDIA_ID=? or upper(MEDIA_NAME) LIKE ?)");
				param.add(bean.getMedia_id());
				param.add("%" + bean.getMedia_name().trim().toUpperCase() + "%");

			}
			if (!StringUtils.isEmpty(bean.getBlanceMode())) {
				sb.append(" and BLANCE_MODE=?");
				param.add(bean.getBlanceMode().trim());
			}
			if (bean.getStatus() != null) {
				sb.append(" and status= ?");
				param.add(bean.getStatus());
			}

			if (bean.getType_id() != null) {
				sb.append(" and TYPE_ID= ?");
				param.add(bean.getType_id());
			}
			if (bean.getAd_type() != null) {
				sb.append(" and AD_TYPE=?");
				param.add(bean.getAd_type());
			}
		}
		list = (List<AdPriceVo>) adDao.findByPage(columns, sb.toString(),
				param.toArray(), AdPriceVo.class, pageInfor);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: modifyAdPrice
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @param price
	 * @see cn.adwalker.ad.admin.ad.service.IAdPriceService#modifyAdPrice(java.lang.Long,
	 *      java.lang.Double)
	 */
	@Override
	public void modifyAdPrice(Long adId, Double price,Double price_update,Long manager_id) throws Exception{
		adDao.modifyAdPrice(adId, price);
		//记录投放单价更改
		if(price.doubleValue()!=price_update.doubleValue()){
			Price pe=new Price();
				pe.setCam_ad_id(adId);
				pe.setCam_ad_type(2);
				pe.setOperater_id(manager_id);
				pe.setCreate_time(new Date());
				pe.setPrice(price_update);
				priceUpdateDao.insert(pe);
			
		}
	}

}
