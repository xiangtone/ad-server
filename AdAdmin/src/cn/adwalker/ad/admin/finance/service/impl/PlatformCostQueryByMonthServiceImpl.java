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
import cn.adwalker.model.operation.dao.IOperationDevIncomeAuditDao;
import cn.adwalker.ad.admin.finance.bean.PlatformCostByMonthQueryBean;
import cn.adwalker.ad.admin.finance.service.IPlatformCostQueryByMonthService;
import cn.adwalker.ad.admin.finance.vo.DevIncomeAuditSumVo;
import cn.adwalker.ad.admin.finance.vo.PlatformCostByMonthVo;

/**
 * <p>
 * Title: DevIncomeAuditServiceImpl
 * </p>
 * <p>
 * Description:运营确认收入serice实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-17
 */
@Service
public class PlatformCostQueryByMonthServiceImpl implements
		IPlatformCostQueryByMonthService {

	@Resource
	private IOperationDevIncomeAuditDao devIncomeAuditDao;

	/**
	 * 
	 * (non-Javadoc)
	 * <p>
	 * Title: findByCondition
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageIndex
	 * @param pageRecord
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevIncomeAuditService.finance.service.IDevIncomeEffectConfirmService#findByCondition(cn.adwalker.model.finance.DevIncomeAuditbean.DevIncomeEffectSearchbean,
	 *      int, int)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<PlatformCostByMonthVo> findList(
			PlatformCostByMonthQueryBean bean, IPageInfo pageInfo)
			throws Exception {
		List<PlatformCostByMonthVo> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"t_finace_dev_incom_confirm_bymonth t where  1=1");

		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(t.ad_NAME) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}

			// 开发者id
			if (StringUtils.isNotEmpty(bean.getDev())) {
				sb.append(" and (t.dev_id=? or  upper(t.dev_Email) like ? )");
				param.add(bean.getDeve_id());
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");
			}

			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os ='");
				sb.append(bean.getOs());
				sb.append("'");
			}

			if (ObjectUtils.isNotEmpty(bean.getYear())) {
				sb.append(" and t.year =? ");
				param.add(bean.getYear());
			}

			if (StringUtils.isNotEmpty(bean.getMonth())) {
				sb.append(" and t.month=? ");
				param.add(bean.getMonth());
			}

			sb.append(" ");
		}
		list = (List<PlatformCostByMonthVo>) devIncomeAuditDao.findByPage(
				"t.* ", sb.toString(), param.toArray(), " order by t.id desc ",
				PlatformCostByMonthVo.class, pageInfo);
		return list;
	}

	@SuppressWarnings("unchecked")
	private List<DevIncomeAuditSumVo> findSummary(
			PlatformCostByMonthQueryBean bean) throws Exception {

		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("select  sum(dev_cost) as sum_sumMoney  from t_finace_dev_incom_confirm_bymonth t");
		sb.append(" where 1=1");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(t.ad_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			// 开发者id
			if (StringUtils.isNotEmpty(bean.getDev())) {
				sb.append(" and (t.dev_id=? or  upper(t.dev_Email) like ? )");
				param.add(bean.getDeve_id());
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");
			}

			if (ObjectUtils.isNotEmpty(bean.getYear())) {
				sb.append(" and t.year =? ");
				param.add(bean.getYear());
			}

			if (StringUtils.isNotEmpty(bean.getMonth())) {
				sb.append(" and t.month=? ");
				param.add(bean.getMonth());
			}
			sb.append(" ");
		}
		return (List<DevIncomeAuditSumVo>) this.devIncomeAuditDao.findAll(
				sb.toString(), param.toArray(), DevIncomeAuditSumVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findSummaryByCondition
	 * </p>
	 * <p>
	 * Description:求和
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationDevIncomeAuditService#findSummaryByCondition(cn.adwalker.admin.operation.bean.DevIncomeAuditbean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public DevIncomeAuditSumVo findSummaryByCondition(
			PlatformCostByMonthQueryBean bean) throws Exception {
		List<DevIncomeAuditSumVo> dDB = this.findSummary(bean);
		double sum_valid_Amount = 0d;// 广告主确认数统计
		double sum_sumMoney = 0d;// 总金额 统计
		if (dDB != null && dDB.size() > 0) {
			sum_sumMoney = dDB.get(0).getSum_sumMoney() != null ? dDB.get(0)
					.getSum_sumMoney() : 0;
		}
		return new DevIncomeAuditSumVo(sum_valid_Amount, sum_sumMoney);
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
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevIncomeAuditService.finance.service.IDevIncomeEffectConfirmService#findAll(cn.adwalker.model.finance.DevIncomeAuditbean.DevIncomeEffectSearchbean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<PlatformCostByMonthVo> findAll(PlatformCostByMonthQueryBean bean)
			throws Exception {
		List<PlatformCostByMonthVo> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"t_finace_dev_incom_confirm_bymonth t where 1=1");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(t.ad_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			// 开发者id
			if (StringUtils.isNotEmpty(bean.getDev())) {
				sb.append(" and (t.dev_id=? or  upper(t.dev_Email) like ? )");
				param.add(bean.getDeve_id());
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");
			}

			if (ObjectUtils.isNotEmpty(bean.getYear())) {
				sb.append(" and t.year =? ");
				param.add(bean.getYear());
			}

			if (StringUtils.isNotEmpty(bean.getMonth())) {
				sb.append(" and t.month=? ");
				param.add(bean.getMonth());
			}
			sb.append(" ");
		}
		list = (List<PlatformCostByMonthVo>) devIncomeAuditDao.findAll("*",
				sb.toString(), param.toArray(),
				" order by t.CREATE_TIME desc ", PlatformCostByMonthVo.class);
		return list;
	}
}
