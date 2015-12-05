package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.model.finance.domain.UserDeveloper;
import cn.adwalker.model.log.dao.IDevAddMoneyDao;
import cn.adwalker.model.log.dao.IDevIncomeAuditLogDao;
import cn.adwalker.model.log.domain.DevAddMoneyLog;
import cn.adwalker.model.log.domain.DevIncomeAuditLog;
import cn.adwalker.model.operation.dao.IOperationDevIncomeAuditDao;
import cn.adwalker.model.operation.domain.IncomeAudit;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevIncomeAuditbean;
import cn.adwalker.ad.admin.finance.service.IDevIncomeAuditService;
import cn.adwalker.ad.admin.finance.vo.DevIncomeAuditSumVo;
import cn.adwalker.ad.admin.finance.vo.IncomeAuditVo;

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
@Service("finance_devIncomeAuditService")
public class DevIncomeAuditServiceImpl implements IDevIncomeAuditService {

	private static final LRUMap incomeMap = new LRUMap(1000);

	@Resource
	private IOperationDevIncomeAuditDao devIncomeAuditDao;

	@Resource
	private IDeveloperDao developerDao;

	@Resource
	private IDevIncomeAuditLogDao devIncomeAuditLogDao;

	@Resource
	private IDevAddMoneyDao devAddMoneyDao;

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
	public List<IncomeAuditVo> findList(DevIncomeAuditbean bean, IPageInfo pageInfo) throws Exception {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("t_finace_dev_incom_confirm f INNER JOIN v_app_dev app ON f.app_id=app.id INNER JOIN v_ad_campaign c ON f.ad_id=c.id where app.dev_status=? and f.status!=0 and  f.status!=-2 ");
		param.add(1);// 开发者状态正常
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (c.id=? or upper(c.campaign_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase() + "%");
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
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and f.dev_cost<='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and f.effect_time>='" + bean.getStatStartTime() + " 00:00:00'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and f.effect_time<='" + bean.getStatEndTime() + " 23:59:59'");
			}
			sb.append(" ");
		}
		String columns = "app.NAME AS app_name,app.dev_email,app.dev_id AS dev_id,app.dev_status,c.campaign_name,c.id campaign_id,f.manager_name,f.manager_time,finance_name,f.id,f.app_id,f.manager_id,f.STATUS AS STATUS,f.create_time,f.finance_id,f.finance_time,f.finance_money,f.dev_cost AS confirm_money,f.effect_num,f.effect_time";
		return (List<IncomeAuditVo>) devIncomeAuditDao.findByPage(columns, sb.toString(), param.toArray(), " order by f.id desc ", IncomeAuditVo.class, pageInfo);
	}

	@SuppressWarnings("unchecked")
	private List<DevIncomeAuditSumVo> findSummary(DevIncomeAuditbean bean) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("select sum(ROUND(f.dev_cost,2)) as sum_sumMoney FROM t_finace_dev_incom_confirm f INNER JOIN v_app_dev app ON f.app_id=app.id INNER JOIN v_ad_campaign c ON f.ad_id=c.id where app.dev_status=? and f.status!=0 and f.status!=-2");
		param.add(1);// 开发者账号正常
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (c.id=? or upper(c.campaign_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase() + "%");
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
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and f.dev_cost<='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and f.effect_time>='" + bean.getStatStartTime() + " 00:00:00'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and f.effect_time<='" + bean.getStatEndTime() + " 23:59:59'");
			}
			sb.append(" ");
		}
		return (List<DevIncomeAuditSumVo>) this.devIncomeAuditDao.findAll(sb.toString(), param.toArray(), DevIncomeAuditSumVo.class);
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
	public DevIncomeAuditSumVo findSummaryByCondition(DevIncomeAuditbean bean) throws Exception {
		List<DevIncomeAuditSumVo> dDB = this.findSummary(bean);
		double sum_valid_Amount = 0d;// 广告主确认数统计
		double sum_sumMoney = 0d;// 总金额 统计
		if (dDB != null && dDB.size() > 0) {
			sum_sumMoney = dDB.get(0).getSum_sumMoney() != null ? dDB.get(0).getSum_sumMoney() : 0;
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
		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < ids.length; i++) {
			if (!incomeMap.containsKey(ids[i])) {
				list.add(ids[i]);
				incomeMap.put(ids[i], new Date());
			}
		}
		this.nextWork(list, manageUser);// 1：处理流水数据
		// 批量修改状态
		Long manageId = 0L; // 当前登录管理员id
		if (manageUser != null) {
			manageId = manageUser.getId();
		}
		try {
			this.devIncomeAuditDao.batchDealAudit(list, 2, manageId, manageUser.getRealName());// 2：批量更改开发者审核状态（给开发者结钱）
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 审核通过引发工作步骤： a:更新当前确认激活数 b:更新当前确认收入 c:更新开发者的当前积分、预计佣金、已确认佣金
	 * d:更新报表：REPORT_DEV_AD_DAY_STAT 的确认激活收入（CONFIRM_ACTIVATION_INCOME） =
	 * CONFIRM_ACTIVATION_INCOME + 管理员确认的钱（DEV_AD_EFFECT_CONFIRM.CONFIRM_MONEY）
	 * 
	 * @param ids 被审核数据id
	 * @param types
	 * @param confirmAmounts 确认数
	 */
	private void nextWork(List<Long> ids, SysUserVo curManageUser) throws Exception {
		DevAddMoneyLog log = null;
		if (ids != null && ids.size() > 0) {
			// 临时存放开发者信息更新列表，key：开发者id，value：开发者用户,累加开发者当前积分、预计佣金、已确认佣金
			Map<Long, UserDeveloper> tempMap = new HashMap<Long, UserDeveloper>();
			List<DevAddMoneyLog> logList = new ArrayList<DevAddMoneyLog>();
			// 日志的集合
			for (Long id : ids) {
				log = new DevAddMoneyLog();
				IncomeAudit bean = this.devIncomeAuditDao.findById(id);
				UserDeveloper tempUser = tempMap.get(bean.getDev_id());// 在map中是否存在
				if (tempUser == null) {
					tempUser = new UserDeveloper();
					tempUser.setConfirmedMoney(0.0);// 已确认佣金
				}
				if (bean.getConfirmMoney() == null) {
					bean.setConfirmMoney(0.0);
				}
				tempUser.setConfirmedMoney(tempUser.getConfirmedMoney() + bean.getConfirmMoney());// 已确认佣金
				tempMap.put(bean.getDev_id(), tempUser);
				// 增加日志
				log.setOld_money(tempUser.getConfirmedMoney());
				log.setMoney(bean.getConfirmMoney());
				log.setCreate_time(new Date());
				log.setDev_id(bean.getDev_id());
				log.setIncome_id(Long.valueOf(bean.getId()));
				logList.add(log);
			}
			// 批量修改开发者的信息，当前积分、未确认佣金、已确认佣金
			if (tempMap != null && tempMap.size() != 0) {
				// 批量修改开发者的信息，已确认佣金
				this.developerDao.batchUpdateMoney(tempMap);
			}
			if (logList != null && logList.size() > 0) {
				devAddMoneyDao.batchInsert(logList);
			}

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
	public List<IncomeAuditVo> findAll(DevIncomeAuditbean bean) throws Exception {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("t_finace_dev_incom_confirm f INNER JOIN v_app_dev app ON f.app_id=app.id INNER JOIN v_ad_campaign c ON f.ad_id=c.id where app.dev_status=? and f.status!=0 and  f.status!=-2");
		param.add(1);// 开发者账号状态
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (c.id=? or upper(c.campaign_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase() + "%");
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
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and f.dev_cost<='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and date_format(f.effect_time,'%Y-%m-%d') >='" + bean.getStatStartTime() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and date_format(f.effect_time,'%Y-%m-%d') <='" + bean.getStatEndTime() + "'");
			}
			sb.append(" ");
		}
		String columns = "app.NAME AS app_name,app.dev_email,app.dev_id AS dev_id,app.dev_status,c.campaign_name,c.id campaign_id,f.manager_name,f.manager_time,finance_name,f.id,f.app_id,f.manager_id,f.STATUS AS STATUS,f.create_time,f.finance_id,f.finance_time,f.finance_money,f.dev_cost AS confirm_money,f.effect_num,f.effect_time";
		return (List<IncomeAuditVo>) devIncomeAuditDao.findAll(columns, sb.toString(), param.toArray(), " order by f.CREATE_TIME desc ", IncomeAuditVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: tranAuditNotPass
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @param manageUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevIncomeAuditService#tranAuditNotPass(java.lang.Long[],
	 *      cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void tranAuditNotPass(List<Long> ids, String reason, SysUserVo manageUser) throws Exception {
		// 批量修改状态
		Long manageId = 0L; // 当前登录管理员id
		String manageName = "";
		if (manageUser != null) {
			manageId = manageUser.getId();
			manageName = manageUser.getRealName();
		}
		try {
			this.devIncomeAuditDao.batchDealAudit(ids, -1, manageId, manageName);// 2：批量更改开发者审核状态（给开发者结钱）
			DevIncomeAuditLog log = null;
			for (Long id : ids) {
				log = new DevIncomeAuditLog();
				log.setCreate_time(new Date());
				log.setIncome_id(id);
				log.setNote(reason);
				log.setStatus(0);
				log.setUser_id(manageId);
				devIncomeAuditLogDao.insert(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
