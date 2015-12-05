/**
 * 
 */
package cn.adwalker.ad.admin.finance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.domain.Advertiser;
import cn.adwalker.model.finance.dao.IFinanceAdvRechargeDao;
import cn.adwalker.model.finance.domain.AdvRechargeLog;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.AdvRechargeBean;
import cn.adwalker.ad.admin.finance.service.IFinanceAdvRechargeService;

/**
 * @author wjp 广告主充值记录
 */
@Service(value = "financeAdvRechargeService")
@Transactional
public class FinanceAdvRechargeServiceImpl implements
		IFinanceAdvRechargeService {

	/** 广告主充值 */
	@Resource
	private IFinanceAdvRechargeDao financeAdvRechargeDao;

	@Override
	public int tranAddAndUpdateAdv(AdvRechargeLog advRechargeLog,
			Advertiser userAdver, SysUserVo currManageUser) throws Exception {
		this.financeAdvRechargeDao.insert(advRechargeLog);// 添加充值信息
		updateUserAdverInfo(advRechargeLog, userAdver, currManageUser);// 更新广告主累计投资、额度
		return 0;
	}

	/**
	 * @param advRechargeLog
	 * @param userAdver
	 * @throws Exception
	 */
	private void updateUserAdverInfo(AdvRechargeLog advRechargeLog,
			Advertiser userAdver, SysUserVo currManageUser) throws Exception {
		
	}

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
	 * @see cn.adwalker.ad.admin.finance.service.IFinanceAdvRechargeService#findByPage(cn.adwalker.ad.admin.finance.bean.AdvRechargeBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdvRechargeLog> findByPage(AdvRechargeBean bean,
			IPageInfo pageInfo) throws Exception {
		StringBuilder sb = new StringBuilder(
				" t_adv_recharge_log t left join  T_ADVERTISER ta on t.adv_id=ta.id where 1=1 ");
		if (!"".equals(bean.getValue()) && bean.getValue() != null
				&& !"".equals(bean.getKeyword()) && bean.getKeyword() != null) {

			if ("ADV_ID".equals(bean.getKeyword())) {
				sb.append(" and ");
				sb.append(bean.getKeyword());
				sb.append(" = ");
				sb.append(" " + bean.getValue() + " ");

			} else {
				sb.append(" and ");
				sb.append(bean.getKeyword());
				sb.append(" like ");
				sb.append(" '%" + bean.getValue() + "%' ");
			}
		}
		if (!"".equals(bean.getBeginTime()) && !"".equals(bean.getBeginTime())
				&& bean.getEndTime() != null && bean.getEndTime() != null) {
			sb.append(" and ");
			sb.append(" recharge_Date >= ");
			sb.append(" '" + bean.getBeginTime() + "' ");
			sb.append(" and ");
			sb.append(" recharge_Date <= ");
			sb.append(" '" + bean.getEndTime() + "' ");
		}
		
		return (List<AdvRechargeLog>) financeAdvRechargeDao.findByPage(
				"  t.*, ta.email as advEmail ,ta.real_name", sb.toString(),
				null, "order by t.create_time desc ", AdvRechargeLog.class,
				pageInfo);
	}

}
