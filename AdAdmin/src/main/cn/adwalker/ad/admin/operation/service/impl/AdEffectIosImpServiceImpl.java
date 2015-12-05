package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.Function;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.ICampaignEffectIosDao;
import cn.adwalker.model.operation.dao.ICampaignEffectIosDetailDao;
import cn.adwalker.model.operation.domain.CampaignEffectIos;
import cn.adwalker.model.operation.domain.CampaignEffectIosDetail;
import cn.adwalker.ad.admin.operation.bean.AdEffectIosImpbean;
import cn.adwalker.ad.admin.operation.form.AdIosEffect;
import cn.adwalker.ad.admin.operation.service.IAdEffectIosImpService;
import cn.adwalker.ad.admin.operation.vo.AdEffectIosImpVo;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmIos;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmIosEditVo;

/**
 * <p>
 * Title: ChannelEffectQueryServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */
@Service
public class AdEffectIosImpServiceImpl implements IAdEffectIosImpService {

	@Resource
	private ICampaignEffectIosDao campaignEffectIosDao;

	@Resource
	private ICampaignEffectIosDetailDao campaignEffectIosDetailDao;

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
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IAdEffectIosImpService#findByPage(cn.adwalker.ad.admin.operation.bean.AdEffectIosImpbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<AdEffectIosImpVo> findByPage(AdEffectIosImpbean bean,
			IPageInfo pageInfo) throws Exception {
		List<AdEffectIosImpVo> list = null;
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" t_ios_forecast_income_cost a where 1=1 and (a.status=0 or a.status=1) ");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStart_time())) {
				sb.append(" and a.static_date>=?");
				paramList.add(bean.getStart_time());
			}

			if (ObjectUtils.isNotEmpty(bean.getEnd_time())) {
				sb.append(" and a.static_date<=?");
				paramList.add(bean.getEnd_time());
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}

			if (bean.getType_id() != null) {
				sb.append(" and a.type_id=? ");
				paramList.add(bean.getType_id());

			}
			if (bean.getStatus() != null) {
				sb.append(" and a.status=? ");
				paramList.add(bean.getStatus());
			}
		}
		list = (List<AdEffectIosImpVo>) campaignEffectIosDao.findByPage("*",
				sb.toString(), paramList.toArray(), AdEffectIosImpVo.class,
				pageInfo);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: initData
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IAdEffectIosImpService#initData(cn.adwalker.ad.admin.operation.form.AdIosEffect)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignConfirmIos> initData(AdIosEffect form) throws Exception {
		List<CampaignConfirmIos> list = null;
		if (form != null && form.getCampaign_id() != null) {
			CampaignEffectIos entity = new CampaignEffectIos();
			entity.setCampaign_id(form.getCampaign_id());
			entity.setStart_time(form.getStart_time());
			entity.setEnd_time(form.getEnd_time());
			entity.setStatus(-1);
			entity.setCreate_time(new Date());
			Long id = campaignEffectIosDao.insert(entity);
			list = (List<CampaignConfirmIos>) campaignEffectIosDao
					.findAll(
							"SELECT id as ad_id,media_id,type_id,media_name,status,price from  V_AD_CAMPAIGN where campaign_id=?",
							new Object[] { form.getCampaign_id() },
							CampaignConfirmIos.class);
			if (list != null && list.size() > 0) {
				for (CampaignConfirmIos vo : list) {
					CampaignEffectIosDetail detailEntity = new CampaignEffectIosDetail();
					detailEntity.setParent_id(id);
					detailEntity.setPrice(vo.getPrice());
					detailEntity.setAd_id(vo.getAd_id());
					detailEntity.setStart_time(form.getStart_time());
					detailEntity.setEnd_time(form.getEnd_time());
					detailEntity.setStatus(-1);
					Long temp_id = campaignEffectIosDetailDao
							.insert(detailEntity);
					vo.setId(temp_id);
					vo.setParent_id(id);

				}

			}
		}
		return list;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: addConfirmNum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param effect_id
	 * @param ids
	 * @param priceList
	 * @param confirm_numList
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IAdEffectIosImpService#addConfirmNum(java.lang.Long,
	 *      java.lang.Long[], java.lang.Double[], java.lang.Integer[])
	 */
	@Override
	public void addConfirmNum(Long effect_id, Long[] ids, Double[] priceList,
			Integer[] confirm_numList) throws Exception {
		List<Object[]> param = new ArrayList<Object[]>();
		if (ids != null && priceList != null && confirm_numList != null) {
			Double confirm_total = 0d;
			for (int i = 0; i < ids.length; i++) {
				param.add(new Object[] { priceList[i], confirm_numList[i],
						ids[i] });
				confirm_total = Function.add(confirm_total, priceList[i]
						* confirm_numList[i]);
			}
			campaignEffectIosDetailDao
					.batchUpdate(
							"update T_IOS_EFFECT_DETAIL set price=?,confirm_num=? where id=?",
							param);
			campaignEffectIosDao
					.update("update T_IOS_EFFECT set status=?,confirm_total=? where id=?",
							new Object[] { 0, confirm_total, effect_id });

		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getEffectDetail
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IAdEffectIosImpService#getEffectDetail(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignConfirmIosEditVo> getEffectDetail(Long id)
			throws Exception {
		List<CampaignConfirmIosEditVo> list = (List<CampaignConfirmIosEditVo>) campaignEffectIosDetailDao
				.findAll(
						"select t.*,v.media_name,v.media_id,v.type_id from T_IOS_EFFECT_DETAIL t left join V_AD_CAMPAIGN v on t.ad_id=v.id where parent_id=?",
						new Object[] { id }, CampaignConfirmIosEditVo.class);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deleteEffect
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param effect_id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IAdEffectIosImpService#deleteEffect(java.lang.Long)
	 */
	@Override
	public void deleteEffect(Long effect_id) throws Exception {
		campaignEffectIosDao.update(
				"update T_IOS_EFFECT set status=? where id=?", new Object[] {
						-4, effect_id });

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: submit
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IAdEffectIosImpService#submit(java.lang.Long)
	 */
	@Override
	public void submit(Long id) throws Exception {
		campaignEffectIosDao.update(
				"update t_ios_forecast_income_cost set status=? where  id=?",
				new Object[] { 1, id });

	}
}
