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
import cn.adwalker.model.finance.dao.IFinancePunishDetailDao;
import cn.adwalker.model.finance.domain.DevListAwardDetail;
import cn.adwalker.model.finance.domain.FinanceAward;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean;
import cn.adwalker.ad.admin.finance.form.FinancePunishDetail;
import cn.adwalker.ad.admin.finance.service.IFinancePunishDetailService;
import cn.adwalker.ad.admin.finance.vo.DevListAwardDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;

@Service(value = "financePunishDetailService")
@Transactional
public class FinancePunishDetailServiceImpl implements
		IFinancePunishDetailService {

	@Resource
	private IFinancePunishDetailDao financePunishDetailDao;

	@Resource
	private IDeveloperDao developerDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinancePunishDetailService#findByList(cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<DevListAwardDetailVo> findByList(DevPunishDetailbean bean,
			IPageInfo pageInfo) throws Exception {
		List<DevListAwardDetailVo> voList = new ArrayList<DevListAwardDetailVo>();
		StringBuilder sql = new StringBuilder();
		sql.append("  V_FINANCE_DEV_AWARD_PUNISH where 1=1 and TYPE=-1 ");
		if (ObjectUtils.isNotEmpty((bean.getDev_id()))) {
			sql.append(" and DEV_ID ");
			sql.append(" = ");
			sql.append(bean.getDev_id());
		}
		if (ObjectUtils.isNotEmpty((bean.getDev_account()))) {
			sql.append(" and upper(DEV_EMAIL) ");
			sql.append(" like '%");
			sql.append(bean.getDev_account().trim().toUpperCase());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty((bean.getFinance_name()))) {
			sql.append(" and FINANCE_NAME ");
			sql.append(" like '%");
			sql.append(bean.getFinance_name());
			sql.append("%'");
		}

		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStat_date_begin())) {
			sql.append(" and date_format(CREATE_TIME,'%Y-%m-%d') >='"
					+ bean.getStat_date_begin() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStat_date_end())) {
			sql.append(" and date_format(CREATE_TIME,'%Y-%m-%d') <='"
					+ bean.getStat_date_end() + "'");
		}
		List<DevListAwardDetail> list = (List<DevListAwardDetail>) financePunishDetailDao
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
				devVo.setStat_date(dev.getCreate_time());
				voList.add(devVo);
			}
		}
		return voList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findBySum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @see cn.adwalker.ad.admin.finance.service.IFinancePunishDetailService#findBySum(cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public FinanceIncomeSumVo findBySum(DevPunishDetailbean bean)
			throws Exception {
		List<FinanceIncomeSumVo> dDB = financePunishDetailDao
				.findBySummary(bean);
		double sum_sumMoney = 0d;// 总金额 统计
		if (dDB != null && dDB.size() > 0) {
			sum_sumMoney = dDB.get(0).getSum_sumMoney() != null ? dDB.get(0)
					.getSum_sumMoney() : 0;
		}
		return new FinanceIncomeSumVo(sum_sumMoney);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateDevPunish
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param from
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinancePunishDetailService#updateDevPunish(cn.adwalker.ad.admin.finance.form.FinancePunishDetail)
	 */
	@Override
	public void updateDevPunish(FinancePunishDetail from, SysUserVo currenUser)
			throws Exception {
		developerDao.updateDevPunish(from);
		this.insertPunishDetail(from, currenUser);

	}

	/**
	 * <p>
	 * Title: insertPunishDetail
	 * </p>
	 * <p>
	 * Description录入数据扣款记录
	 * </p>
	 * 
	 * @param from
	 * @param currenUser
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-10
	 * @return void
	 * @version 1.0
	 */
	private void insertPunishDetail(FinancePunishDetail from,
			SysUserVo currenUser) throws Exception {
		FinanceAward domin = new FinanceAward();
		domin.setFinance_id(currenUser.getId());
		domin.setFinance_type(0);
		domin.setCreate_time(new Date());
		domin.setDev_id(from.getEntryDevId());
		domin.setNote(from.getEntryActName());
		domin.setMoney(from.getEntryActMoney());
		domin.setType(-1);
		this.financePunishDetailDao.insert(domin);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByall
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IFinancePunishDetailService#findByall(cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<DevListAwardDetailVo> findByall(DevPunishDetailbean bean)
			throws Exception {
		List<DevListAwardDetailVo> voList = new ArrayList<DevListAwardDetailVo>();
		StringBuilder sql = new StringBuilder();
		sql.append("  V_FINANCE_DEV_AWARD_PUNISH where 1=1 and TYPE=-1 ");
		if (ObjectUtils.isNotEmpty((bean.getDev_id()))) {
			sql.append(" and DEV_ID ");
			sql.append(" = ");
			sql.append(bean.getDev_id());
		}
		if (ObjectUtils.isNotEmpty((bean.getDev_account()))) {
			sql.append(" and upper(DEV_EMAIL) ");
			sql.append(" like '%");
			sql.append(bean.getDev_account());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty((bean.getFinance_name()))) {
			sql.append(" and FINANCE_NAME ");
			sql.append(" like '%");
			sql.append(bean.getFinance_name());
			sql.append("%'");
		}

		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStat_date_begin())) {
			sql.append(" and date_format(CREATE_TIME,'%Y-%m-%d') >='"
					+ bean.getStat_date_begin() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStat_date_end())) {
			sql.append(" and date_format(CREATE_TIME,'%Y-%m-%d') <='"
					+ bean.getStat_date_end() + "'");
		}
		List<DevListAwardDetail> list = (List<DevListAwardDetail>) financePunishDetailDao
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
				devVo.setStat_date(dev.getCreate_time());
				voList.add(devVo);
			}
		}
		return voList;
	}

}
