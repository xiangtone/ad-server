package cn.adwalker.model.operation.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IPackageActivateDetailDao;
import cn.adwalker.model.operation.domain.PackageActivateDetail;

/**
 * <p>
 * Title: AdEffectFirstConfirmDaoImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-16
 */
@Repository("confirmationNumberDao")
public class PackageActivateDetailDaoImpl extends BaseDaoImpl<PackageActivateDetail> implements
		IPackageActivateDetailDao {
	
	public PackageActivateDetailDaoImpl() {
		super();
		this.setTableName("T_PACKAGE_ACTIVATE_DETAIL");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveConfirm
	 * </p>
	 * <p>
	 * Description:分完数的值更新到数据库中
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#saveConfirm(java.lang.Long[],
	 *      java.lang.Integer[])
	 */
	@Override
	public int saveConfirm(Long[] id, Integer[] confirm_num) throws Exception {
		// 分完数的值更新到数据库中。
		if (id != null && id.length > 0) {
			String sql = "update T_PACKAGE_ACTIVATE_DETAIL set CONFIRM_NUM=? where ID=?";
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (int i = 0; i < id.length; i++) {
				if (confirm_num[i] == null) {
					confirm_num[i] = 0;
				}
				parameters.add(new Object[] { confirm_num[i], id[i] });
			}
			this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
		}
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationAmount
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#confirmationAmount(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	public void confirmationAmount(Long id, Integer amount, Double out_price)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("update T_PACKAGE_ACTIVATE_DETAIL set CONFIRM_AMOUNT=");
			if (ObjectUtils.isNotEmpty(amount)) {
				sb.append(amount);
			}
			sb.append(",SYS_COST=");
			if (ObjectUtils.isNotEmpty(out_price)) {
				sb.append(amount * out_price);
			}
			sb.append(",status=1,submit_time=? where ID=?");
			jdbcTemplate.update(sb.toString(), new Object[] { new Date(),
					id });
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPrice
	 * </p>
	 * <p>
	 * Description:获取投放单价
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#getPrice(java.lang.Long)
	 */
	@Override
	public Double getPrice(Long id) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select out_price from T_PACKAGE_ACTIVATE_DETAIL t where t.id="
					+ id);
			return jdbcTemplate.queryForObject(sql.toString(),
					Double.class);
		} catch (Exception e) {
			return 0d;
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationAmount
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#confirmationAmount(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	public void confirmationAmount(Long id, Integer amount) throws Exception {
		if (id != null && amount != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("update T_PACKAGE_ACTIVATE_DETAIL set CONFIRM_AMOUNT=?,status=?,SUBMIT_TIME=? where ID=?");
			jdbcTemplate.update(sb.toString(), new Object[] { amount, 1,
					new Date(), id });
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveConfirm
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#saveConfirm(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	public int saveConfirm(Long id, Integer confirm_num) throws Exception {
		if (id != null && confirm_num != null) {
			String sql = "update T_PACKAGE_ACTIVATE_DETAIL set CONFIRM_NUM=? where ID=?";
			this.jdbcTemplate.update(sql,
					new Object[] { confirm_num, id });
		}
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationCpd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#confirmationCpd(java.lang.Long,
	 *      java.lang.Double)
	 */
	@Override
	public void updateChannelCost(Long id, Double sys_cost, Integer num)
			throws Exception {
		if (id != null && sys_cost != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("update T_PACKAGE_ACTIVATE_DETAIL set SYS_COST=?,confirm_amount=? where ID=?");
			jdbcTemplate.update(sb.toString(), new Object[] { sys_cost,
					num, id });
		}

	}


	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#updateStatus(java.lang.Long)
	 */
	@Override
	public void submitData(Long id, Integer status) throws Exception {
		if (id != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("update T_PACKAGE_ACTIVATE_DETAIL set STATUS=?,SUBMIT_TIME=? where ID=?");
			jdbcTemplate.update(sb.toString(), new Object[] { status,
					new Date(), id });
		}

	}

}
