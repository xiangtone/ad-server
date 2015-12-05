package cn.adwalker.ad.admin.operation.service.impl;

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
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.DevIncomeAuditbean;
import cn.adwalker.ad.admin.operation.service.IOperationDevIncomeAuditService;
import cn.adwalker.ad.admin.operation.vo.DevIncomeAuditSumVo;
import cn.adwalker.ad.admin.operation.vo.IncomeAuditVo;

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
@Service("operationDevIncomeAuditService")
public class OperationDevIncomeAuditServiceImpl implements
		IOperationDevIncomeAuditService {
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
	 * @see cn.adwalker.ad.admin.finance.service.IDevIncomeAuditService.finance.service.IDevIncomeEffectConfirmService#findByCondition(cn.adwalker.ad.admin.finance.bean.DevIncomeAuditbean.DevIncomeEffectSearchbean,
	 *      int, int)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<IncomeAuditVo> findList(DevIncomeAuditbean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"t_finace_dev_incom_confirm f INNER JOIN v_app_dev app ON f.app_id=app.id INNER JOIN v_ad_campaign c ON f.ad_id=c.id where 1=1");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (c.id=? or upper(c.campaign_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			// 开发者id
			if (StringUtils.isNotEmpty(bean.getDev())) {
				sb.append(" and (app.dev_id=? or upper(app.dev_Email) like ? )");
				param.add(bean.getDeve_id());
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");
			}
			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and f.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 开发者状态
			if (ObjectUtils.isNotEmpty(bean.getDev_status())) {
				sb.append(" and app.dev_status ='");
				sb.append(bean.getDev_status());
				sb.append("'");
			}
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and f.dev_cost<='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and f.effect_time>='" + bean.getStatStartTime()
						+ " 00:00:00'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and f.effect_time<='" + bean.getStatEndTime()
						+ " 23:59:59'");
			}
			sb.append(" ");
		}
		String columns = "app.NAME app_name,app.dev_email,app.dev_id dev_id,app.dev_status,c.campaign_name,c.id campaign_id,f.manager_name,f.manager_time,finance_name,f.id,f.app_id,f.manager_id,f.STATUS AS STATUS,f.create_time,f.finance_id,f.finance_time,f.finance_money,f.dev_cost AS confirm_money,f.effect_num,f.effect_time";
		return (List<IncomeAuditVo>) devIncomeAuditDao.findByPage(columns,
				sb.toString(), param.toArray(), " order by f.id desc ",
				IncomeAuditVo.class, pageInfo);
	}

	@SuppressWarnings("unchecked")
	private List<DevIncomeAuditSumVo> findSummary(DevIncomeAuditbean bean)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("select sum(ROUND(dev_cost,2)) as sum_sumMoney FROM t_finace_dev_incom_confirm f INNER JOIN v_app_dev app ON f.app_id=app.id INNER JOIN v_ad_campaign c ON f.ad_id=c.id");
		sb.append(" where 1=1 ");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (c.id=? or upper(c.campaign_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			// 开发者id
			if (StringUtils.isNotEmpty(bean.getDev())) {
				sb.append(" and (app.dev_id=? or upper(app.dev_Email) like ? )");
				param.add(bean.getDeve_id());
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");
			}
			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and f.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 开发者状态
			if (ObjectUtils.isNotEmpty(bean.getDev_status())) {
				sb.append(" and app.dev_status ='");
				sb.append(bean.getDev_status());
				sb.append("'");
			}
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and f.dev_cost<='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and f.effect_time>='" + bean.getStatStartTime()
						+ " 00:00;00'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and f.effect_time<='" + bean.getStatEndTime()
						+ " 23:59:59'");
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
	 * @see cn.adwalker.ad.admin.operation.service.IOperationDevIncomeAuditService#findSummaryByCondition(cn.adwalker.ad.admin.operation.bean.DevIncomeAuditbean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public DevIncomeAuditSumVo findSummaryByCondition(DevIncomeAuditbean bean)
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
	 * Title: tranAudit
	 * </p>
	 * <p>
	 * Description:开发者收入审核操作
	 * </p>
	 * 
	 * @param ids
	 * @param types
	 * @param manageUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationDevIncomeAuditService#tranAudit(java.lang.Long[],
	 *      java.lang.Integer[], cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void tranAudit(Long[] ids, SysUserVo manageUser) throws Exception {
		// 设置审核状态
		List<Long> mapAd = new ArrayList<Long>();
		for (int i = 0; i < ids.length; i++) {
			mapAd.add(ids[i]);
		}
		// 批量修改状态
		Long manageId = 0L; // 当前登录管理员id
		String manageName = "";
		if (manageUser != null) {
			manageId = manageUser.getId();
			manageName = manageUser.getRealName();
		}
		try {
			this.devIncomeAuditDao.batchDealAdStatus(mapAd, 1, manageId,
					manageName);// 2：批量更改开发者审核状态（给开发者结钱）
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * @see cn.adwalker.ad.admin.finance.service.IDevIncomeAuditService.finance.service.IDevIncomeEffectConfirmService#findAll(cn.adwalker.ad.admin.finance.bean.DevIncomeAuditbean.DevIncomeEffectSearchbean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<IncomeAuditVo> findAll(DevIncomeAuditbean bean)
			throws Exception {
		List<IncomeAuditVo> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"t_finace_dev_incom_confirm f INNER JOIN v_app_dev app ON f.app_id=app.id INNER JOIN v_ad_campaign c ON f.ad_id=c.id where 1=1");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (c.id=? or upper(c.campaign_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			// 开发者id
			if (StringUtils.isNotEmpty(bean.getDev())) {
				sb.append(" and (app.dev_id=? or upper(app.dev_Email) like ? )");
				param.add(bean.getDeve_id());
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");
			}
			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and f.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 开发者状态
			if (ObjectUtils.isNotEmpty(bean.getDev_status())) {
				sb.append(" and app.dev_status ='");
				sb.append(bean.getDev_status());
				sb.append("'");
			}
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and f.dev_cost<='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and f.effect_time>='" + bean.getStatStartTime()
						+ " 00:00:00'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and f.effect_time<='" + bean.getStatEndTime()
						+ " 23:59:59'");
			}
			sb.append(" ");
		}
		String columns = "app.NAME AS app_name,app.dev_email,app.dev_id AS dev_id,app.dev_status,c.campaign_name,c.id campaign_id,f.manager_name,f.manager_time,finance_name,f.id,f.app_id,f.manager_id,f.STATUS AS STATUS,f.create_time,f.finance_id,f.finance_time,f.finance_money,f.dev_cost AS confirm_money,f.effect_num,f.effect_time";
		list = (List<IncomeAuditVo>) devIncomeAuditDao.findAll(columns,
				sb.toString(), param.toArray(),
				" order by f.CREATE_TIME desc ", IncomeAuditVo.class);
		return list;
	}

	@Override
	public void tranAudit_no(Long[] ids, SysUserVo manageUser) {
		// 设置审核状态
		List<Long> mapAd = new ArrayList<Long>();
		for (int i = 0; i < ids.length; i++) {
			mapAd.add(ids[i]);
		}
		// 批量修改状态
		Long manageId = 0L; // 当前登录管理员id
		String manageName = "";
		if (manageUser != null) {
			manageId = manageUser.getId();
			manageName = manageUser.getRealName();
		}
		try {
			this.devIncomeAuditDao.batchDealAdStatus(mapAd, -2, manageId,
					manageName);// 2：批量更改开发者审核状态（给开发者结钱）
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
