package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IOperationDevPresentDayDao;
import cn.adwalker.ad.admin.operation.bean.OperDevPresentbean;
import cn.adwalker.ad.admin.operation.service.IOperationDevPresentDayServer;
import cn.adwalker.ad.admin.operation.vo.OperationDevPresentDayVo;

/**
 * 
 * <p>
 * Title: OperationDevPresentDayServerImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-11
 */
@Service("OperationDevPresentDayServer")
public class OperationDevPresentDayServerImpl implements
		IOperationDevPresentDayServer {

	@Resource
	private IOperationDevPresentDayDao operationDevPresentDayDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByRewardList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationDevPresentDayServer#findByRewardList(cn.adwalker.ad.admin.operation.bean.OperDevPresentbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<OperationDevPresentDayVo> findByRewardList(
			OperDevPresentbean bean, IPageInfo pageInfo) throws Exception {

		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("V_FINANCE_DEV_AWARD_PUNISH where 1=1 and TYPE=0 ");
		if (ObjectUtils.isNotEmpty((bean.getDev_Id()))) {
			sql.append(" and DEV_ID ");
			sql.append(" = ");
			sql.append(bean.getDev_Id());
		}
		if (ObjectUtils.isNotEmpty((bean.getDev_Name()))) {
			sql.append(" and upper(DEV_EMAIL) ");
			sql.append(" like '%");
			sql.append(bean.getDev_Name().trim().toUpperCase());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStat_date_begin())) {
			sql.append(" and date_format(CREATE_TIME,'%Y-%m-%d') >=" + "'"
					+ bean.getStat_date_begin() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStat_date_end())) {
			sql.append(" and date_format(CREATE_TIME,'%Y-%m-%d') <=" + "'"
					+ bean.getStat_date_end() + "'");
		}
		return (List<OperationDevPresentDayVo>) operationDevPresentDayDao
				.findByPage("*", sql.toString(), list.toArray(),
						" order by create_time desc ",
						OperationDevPresentDayVo.class, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findSumReward
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationDevPresentDayServer#findSumReward(cn.adwalker.ad.admin.operation.bean.OperDevPresentbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public double findSumReward(OperDevPresentbean bean, IPageInfo pageInfo)
			throws Exception {

		double value = operationDevPresentDayDao.findSumReward(bean, pageInfo);

		return value;
	}

}
