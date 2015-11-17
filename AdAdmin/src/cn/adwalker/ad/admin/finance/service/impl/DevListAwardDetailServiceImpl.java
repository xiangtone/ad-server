package cn.adwalker.ad.admin.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.model.finance.dao.IDevListAwardDetailDao;
import cn.adwalker.model.finance.domain.DevListAwardDetail;
import cn.adwalker.model.finance.domain.FinanceAward;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevListAwardDetailbean;
import cn.adwalker.ad.admin.finance.form.FinanceAwardDetail;
import cn.adwalker.ad.admin.finance.service.IDevListAwardDetailService;
import cn.adwalker.ad.admin.finance.vo.DevListAwardDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;

@Service(value = "devListAwardDetailService")
@Transactional
public class DevListAwardDetailServiceImpl implements
		IDevListAwardDetailService {

	@Resource
	private IDevListAwardDetailDao devListAwardDetailDao;

	@Resource
	private IDeveloperDao developerDao;//add by cdd查询开发者相关信息直接从开发者dao中获取，不要重复创建.

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByCondition
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevListAwardDetailService#findByCondition(cn.adwalker.ad.admin.finance.bean.DevListAwardDetailbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<DevListAwardDetailVo> findByCondition(
			DevListAwardDetailbean bean, IPageInfo pageInfo) throws Exception {
		List<DevListAwardDetailVo> voList = new ArrayList<DevListAwardDetailVo>();
		StringBuilder sql = new StringBuilder();
		sql.append("  V_FINANCE_DEV_AWARD_PUNISH where 1=1 and TYPE=0 ");
		if (ObjectUtils.isNotEmpty((bean.getDev_id()))) {
			sql.append(" and DEV_ID ");
			sql.append(" = ");
			sql.append(bean.getDev_id());
		}
		if (ObjectUtils.isNotEmpty((bean.getDev_account()))) {
			sql.append(" and DEV_EMAIL ");
			sql.append(" like '%");
			sql.append(bean.getDev_account());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty((bean.getActivity_name()))) {
			sql.append(" and NOTE ");
			sql.append(" like '%");
			sql.append(bean.getActivity_name());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getActivity_begin())) {
			sql.append(" and ACTIVITY_BEGIN >= '");
			sql.append(bean.getActivity_begin());
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getActivity_end())) {
			sql.append(" and ACTIVITY_END <= '");
			sql.append(bean.getActivity_end());
			sql.append("'");
		}
		List<DevListAwardDetail> list = (List<DevListAwardDetail>) devListAwardDetailDao
				.findByPage("*", sql.toString(), null,
						" order by CREATE_TIME DESC", DevListAwardDetail.class,
						pageInfo);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				DevListAwardDetail dev = list.get(i);
				DevListAwardDetailVo devVo = new DevListAwardDetailVo();
				devVo.setDev_id(dev.getDev_id());
				devVo.setDev_account(dev.getDev_email());
				devVo.setActivity_name(dev.getNote());
				devVo.setAward_money(dev.getMoney());
				devVo.setActivity_begin(dev.getActivity_begin());
				devVo.setActivity_end(dev.getActivity_end());
				if (dev.getFinance_id() == 0) {
					devVo.setFinance_name("平台平台");
				} else {
					devVo.setFinance_name(dev.getFinance_name());
				}
				if (dev.getFinance_type() == 1) {
					devVo.setFinance_type("未结算");
				} else {
					devVo.setFinance_type("已结算");
				}
				devVo.setStat_date(dev.getCreate_time());
				voList.add(devVo);
			}
		}
		return voList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByConditionSum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevListAwardDetailService#findByConditionSum(cn.adwalker.ad.admin.finance.bean.DevListAwardDetailbean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public FinanceIncomeSumVo findByConditionSum(DevListAwardDetailbean bean)
			throws Exception {
		List<FinanceIncomeSumVo> dDB = devListAwardDetailDao
				.findSummaryByCondition(bean);
		double sum_sumMoney = 0d;// 总金额 统计
		if (dDB != null && dDB.size() > 0) {
			sum_sumMoney = dDB.get(0).getSum_sumMoney() != null ? dDB.get(0)
					.getSum_sumMoney() : 0;
		}
		return new FinanceIncomeSumVo(sum_sumMoney);
	}

	/**
	 * <p>
	 * Title: insertAward
	 * </p>
	 * <p>
	 * Description:插入奖励流水记录
	 * </p>
	 * 
	 * @param form
	 * @param currenUser
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-10
	 * @return void
	 * @version 1.0
	 */
	private void insertAward(FinanceAwardDetail form, SysUserVo currenUser)
			throws Exception {
		FinanceAward domin = new FinanceAward();
		domin.setFinance_id(currenUser.getId());
		domin.setFinance_type(0);
		domin.setCreate_time(new Date());
		domin.setDev_id(form.getEntryDevId());
		domin.setNote(form.getEntryActName());
		domin.setMoney(form.getEntryActMoney());
		domin.setType(0);
		domin.setActivity_begin(form.getEntryActCycleBegin());
		domin.setActivity_end(form.getEntryActCycleEnd());
		this.devListAwardDetailDao.insert(domin);
	}


	@Override
	public String findByEmail(Long id) throws Exception {
		return this.developerDao.findByEmail(id);
	}

	public void setDevListAwardDetailDao(
			IDevListAwardDetailDao devListAwardDetailDao) {
		this.devListAwardDetailDao = devListAwardDetailDao;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateDev
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevListAwardDetailService#updateDev(cn.adwalker.ad.admin.finance.form.FinanceAwardDetail)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public int updateDev(FinanceAwardDetail form, SysUserVo currenUser)
			throws Exception {
		this.insertAward(form, currenUser);
		return this.developerDao.updateDev(form);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevListAwardDetailService#findByAll(cn.adwalker.ad.admin.finance.bean.DevListAwardDetailbean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<DevListAwardDetailVo> findByAll(DevListAwardDetailbean bean)
			throws Exception {
		List<DevListAwardDetailVo> voList = new ArrayList<DevListAwardDetailVo>();
		StringBuilder sql = new StringBuilder();
		sql.append("V_FINANCE_DEV_AWARD_PUNISH where 1=1 and TYPE=0 ");
		if (ObjectUtils.isNotEmpty((bean.getDev_id()))) {
			sql.append(" and DEV_ID ");
			sql.append(" = ");
			sql.append(bean.getDev_id());
		}
		if (ObjectUtils.isNotEmpty((bean.getDev_account()))) {
			sql.append(" and DEV_EMAIL ");
			sql.append(" like '%");
			sql.append(bean.getDev_account());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty((bean.getActivity_name()))) {
			sql.append(" and NOTE ");
			sql.append(" like '%");
			sql.append(bean.getActivity_name());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getActivity_begin())) {
			sql.append(" and ACTIVITY_BEGIN >= '");
			sql.append(bean.getActivity_begin());
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getActivity_end())) {
			sql.append(" and ACTIVITY_END <= '");
			sql.append(bean.getActivity_end());
			sql.append("'");
		}
		List<DevListAwardDetail> list = (List<DevListAwardDetail>) devListAwardDetailDao
				.findAll("*", sql.toString(), null,
						" order by CREATE_TIME DESC", DevListAwardDetail.class);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				DevListAwardDetail dev = list.get(i);
				DevListAwardDetailVo devVo = new DevListAwardDetailVo();
				devVo.setDev_id(dev.getDev_id());
				devVo.setDev_account(dev.getDev_email());
				devVo.setActivity_name(dev.getNote());
				devVo.setAward_money(dev.getMoney());
				devVo.setActivity_begin(dev.getActivity_begin());
				devVo.setActivity_end(dev.getActivity_end());
				if (dev.getFinance_id() == 0) {
					devVo.setFinance_name("平台平台");
				} else {
					devVo.setFinance_name(dev.getFinance_name());
				}
				if (dev.getFinance_type() == 1) {
					devVo.setFinance_type("未结算");
				} else {
					devVo.setFinance_type("已结算");
				}
				devVo.setStat_date(dev.getCreate_time());
				voList.add(devVo);
			}
		}
		return voList;
	}
}
