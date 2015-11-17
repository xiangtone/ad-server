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
import cn.adwalker.ad.admin.finance.bean.PlatformCostQueryBean;
import cn.adwalker.ad.admin.finance.service.IPlatformCostQueryService;
import cn.adwalker.ad.admin.finance.vo.DevIncomeAuditSumVo;
import cn.adwalker.ad.admin.finance.vo.PlatformCostVo;

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
public class PlatformCostQueryServiceImpl implements IPlatformCostQueryService {

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
	public List<PlatformCostVo> findList(PlatformCostQueryBean bean,
			IPageInfo pageInfo) throws Exception {
		List<PlatformCostVo> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"v_finace_dev_incom_confirm t " +
//				"left join T_BALANCE_PLATFORM_REL rel on rel.DETAIL_ID=t.id" +
				" left join T_APPLICATION app on t.app_id=app.id  where  t.status=2");
		
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(t.CAMPAIGN_NAME) like ? )");
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

			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and t.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and t.confirmMoney <='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}

			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and app.os ='");
				sb.append(bean.getOs());
				sb.append("'");
			}

			if (bean.getBalance_status() != null) {
				if (bean.getBalance_status() == 1) {
					sb.append(" and  (rel.balance_id is not null) ");
				} else if (bean.getBalance_status() == 0) {
					sb.append(" and  (rel.balance_id is null) ");
				}
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and t.EFFECT_TIME>='"
						+ bean.getStatStartTime() + " 00:00:00'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and t.EFFECT_TIME<='"
						+ bean.getStatEndTime() + " 23:59:59'");
			}
			sb.append(" ");
		}
		list = (List<PlatformCostVo>) devIncomeAuditDao.findByPage(
				"t.* ", sb.toString(), param.toArray(),
				" order by t.id desc ", PlatformCostVo.class, pageInfo);
		return list;
	}

	@SuppressWarnings("unchecked")
	private List<DevIncomeAuditSumVo> findSummary(PlatformCostQueryBean bean)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("select  sum(confirmMoney) as sum_sumMoney  from v_finace_dev_incom_confirm t");
		sb.append(" where status=2");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(t.campaign_name) like ? )");
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

			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and t.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and t.confirmMoney <='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}

			if (bean.getBalance_status() != null) {
				if (bean.getBalance_status() == 1) {
					sb.append(" and  (rel.balance_id is not null) ");
				} else if (bean.getBalance_status() == 0) {
					sb.append(" and  (rel.balance_id is null) ");
				}
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and t.EFFECT_TIME>='"
						+ bean.getStatStartTime() + " 00:00:00'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and t.EFFECT_TIME<='"
						+ bean.getStatEndTime() + " 23:59:59'");
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
	public DevIncomeAuditSumVo findSummaryByCondition(PlatformCostQueryBean bean)
			throws Exception {
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
	public List<PlatformCostVo> findAll(PlatformCostQueryBean bean)
			throws Exception {
		List<PlatformCostVo> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"v_finace_dev_incom_confirm t where status=2");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(t.campaign_name) like ? )");
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

			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and t.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and t.confirmMoney <='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and t.EFFECT_TIME>='"
						+ bean.getStatStartTime() + " 00:00:00'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and t.EFFECT_TIME<='"
						+ bean.getStatEndTime() + " 23:59:59'");
			}
			sb.append(" ");
		}
		list = (List<PlatformCostVo>) devIncomeAuditDao.findAll("*",
				sb.toString(), param.toArray(),
				" order by t.CREATE_TIME desc ", PlatformCostVo.class);
		return list;
	}
}
