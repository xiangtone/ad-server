package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.finance.dao.IFinanceConsumeDao;
import cn.adwalker.model.finance.domain.AdvConsumeDetail;
import cn.adwalker.ad.admin.finance.bean.FinanceConsumebean;
import cn.adwalker.ad.admin.finance.service.IFinanceAdConsumeDetailService;
import cn.adwalker.ad.admin.finance.vo.AdvConsumeDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceConsumeVo;

/**
 * <p>
 * Title: FinanceAdConsumeDetailServiceImpl
 * </p>
 * <p>
 * Description:广告主消费明细service实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-8
 */

@Service("financeAdConsumeDetailService")
public class FinanceAdConsumeDetailServiceImpl implements
		IFinanceAdConsumeDetailService {
	@Resource
	private IFinanceConsumeDao financeConsumeDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdvConsumeDetailListForReport
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceAdConsumeDetailService#getAdvConsumeDetailListForReport(cn.adwalker.ad.admin.finance.bean.FinanceConsumebean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public AdvConsumeDetailVo getAdvConsumeDetailListForReport(
			FinanceConsumebean bean) {
		List<AdvConsumeDetail> list;
		list = this.getAdvConsumeDetailList(bean);
		Integer sum_platform_amount = 0;// 统计 总确认数
		Double sum_income_money = 0d;// 确认 总金额
		Double sum_forecast_money = 0d;// 预计收入
		if (list != null && list.size() > 0) {
			sum_platform_amount = list.get(0).getSum_platform_amount();
			sum_income_money = list.get(0).getSum_income_money();
			sum_forecast_money=list.get(0).getSum_forecast_money();
		}
		return new AdvConsumeDetailVo(sum_platform_amount, sum_income_money,sum_forecast_money);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdvConsumeDetailList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.finance.dao.IFinanceConsumeDao#getAdvConsumeDetailList(cn.adwalker.ad.admin.finance.bean.FinanceConsumebean)
	 */

	@SuppressWarnings("unchecked")
	private List<AdvConsumeDetail> getAdvConsumeDetailList(
			FinanceConsumebean bean) {
		List<AdvConsumeDetail> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"select sum(v.IN_PRICE*v.CONFIRM_NUM) as sum_income_money ,sum(v.CONFIRM_NUM) as sum_platform_amount,sum(v.SYS_COST*(1+v.profit_rate)) as sum_forecast_money from V_INCOME_OUTCOME v   LEFT JOIN  T_ADVERTISER  adv ON v.adv_id=adv.id LEFT JOIN  T_TYPE tp  on v.type_id=tp.id where 1=1");
		// 广告主ID
		if (!StringUtils.isEmpty(bean.getAdv())) {
			sb.append(" and (v.adv_id =? or upper(adv.email) like ?)");
			param.add(bean.getAdv_Id());
			param.add("%" + bean.getAdv_email().trim().toUpperCase() + "%");
		}
		// 活动名称
		if (!ObjectUtils.isEmpty(bean.getCampaign_name())) {
			sb.append(" and (upper(v.campaign_name) like ? or v.campaign_id=?)");
			param.add("%" + bean.getCampaign_name().trim().toUpperCase() + "%");
			param.add(bean.getCampaign_id());
		}

		// 平台类型
		if (!ObjectUtils.isEmpty(bean.getOs())) {
			sb.append(" and v.os ='");
			sb.append(bean.getOs());
			sb.append("'");
		}
		// 媒体类型
		if (!ObjectUtils.isEmpty(bean.getMedia_type())) {
			sb.append(" and v.type like ?");
			param.add("%" + bean.getMedia_type() + "%");
		}
		// 计费方式
		if (!ObjectUtils.isEmpty(bean.getType_id())) {
			sb.append(" and v.type_type =?");
			param.add(bean.getType_id());
		}

		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStatDateStartTime())) {
			sb.append(" and v.static_date >='" + bean.getStatDateStartTime()
					+ "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStatDateEndTime())) {
			sb.append(" and v.static_date <='" + bean.getStatDateEndTime()
					+ "'");
		}
		try {
			list = this.financeConsumeDao.findAll(sb.toString(),param.toArray(),
					AdvConsumeDetail.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findConsumeList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceAdConsumeDetailService#findConsumeList(cn.adwalker.ad.admin.finance.bean.FinanceConsumebean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceConsumeVo> findConsumeList(FinanceConsumebean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"V_INCOME_OUTCOME v   LEFT JOIN  T_ADVERTISER  adv ON v.adv_id=adv.id LEFT JOIN  T_TYPE tp  on v.type_id=tp.id where 1=1");
		// 广告主ID
		if (!StringUtils.isEmpty(bean.getAdv())) {
			sb.append(" and (v.adv_id =? or upper(adv.email) like ?)");
			param.add(bean.getAdv_Id());
			param.add("%" + bean.getAdv_email().trim().toUpperCase() + "%");
		}
		// 活动名称
		if (!ObjectUtils.isEmpty(bean.getCampaign_name())) {
			sb.append(" and (upper(v.campaign_name) like ? or v.campaign_id=?)");
			param.add("%" + bean.getCampaign_name().toString().toUpperCase() + "%");
			param.add(bean.getCampaign_id());
		}

		// 平台类型
		if (!ObjectUtils.isEmpty(bean.getOs())) {
			sb.append(" and v.os ='");
			sb.append(bean.getOs());
			sb.append("'");
		}
		// 媒体类型
		if (!ObjectUtils.isEmpty(bean.getMedia_type())) {
			sb.append(" and v.type like ?");
			param.add("%" + bean.getMedia_type() + "%");
		}
		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStatDateStartTime())) {
			sb.append(" and v.static_date >='" + bean.getStatDateStartTime()
					+ "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStatDateEndTime())) {
			sb.append(" and v.static_date <='" + bean.getStatDateEndTime()
					+ "'");
		}
		return (List<FinanceConsumeVo>) financeConsumeDao.findByPage(
				"v.*,adv.email adv_email,tp.name type_name", sb.toString(),
				param.toArray(), "order by v.static_date DESC", FinanceConsumeVo.class, pageInfo);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findConsumeReportList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceAdConsumeDetailService#findConsumeReportList(cn.adwalker.ad.admin.finance.bean.FinanceConsumebean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<FinanceConsumeVo> findConsumeReportList(FinanceConsumebean bean)
			throws Exception {

		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"V_INCOME_OUTCOME v   LEFT JOIN  T_ADVERTISER  adv ON v.adv_id=adv.id LEFT JOIN  T_TYPE tp  on v.type_id=tp.id where 1=1");
		// 广告主ID
		if (!StringUtils.isEmpty(bean.getAdv())) {
			sb.append(" and (v.adv_id =? or upper(adv.email) like ?)");
			param.add(bean.getAdv_Id());
			param.add("%" + bean.getAdv_email().trim().toUpperCase() + "%");
		}
		// 活动名称
		if (!ObjectUtils.isEmpty(bean.getCampaign_name())) {
			sb.append(" and (upper(v.campaign_name) like ? or v.campaign_id=?)");
			param.add("%" + bean.getCampaign_name().trim().toUpperCase() + "%");
			param.add(bean.getCampaign_id());
		}

		// 平台类型
		if (!ObjectUtils.isEmpty(bean.getOs())) {
			sb.append(" and v.os ='");
			sb.append(bean.getOs());
			sb.append("'");
		}
		// 媒体类型
		if (!ObjectUtils.isEmpty(bean.getMedia_type())) {
			sb.append(" and v.type like ?");
			param.add("%" + bean.getMedia_type() + "%");
		}
		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStatDateStartTime())) {
			sb.append(" and v.static_date >='" + bean.getStatDateStartTime()
					+ "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStatDateEndTime())) {
			sb.append(" and v.static_date <='" + bean.getStatDateEndTime()
					+ "'");
		}
		return (List<FinanceConsumeVo>) financeConsumeDao
				.findAll(
						"v.*,adv.email adv_email,tp.name type_name",
						sb.toString(), param.toArray(),
						" ", FinanceConsumeVo.class);

	}

}
