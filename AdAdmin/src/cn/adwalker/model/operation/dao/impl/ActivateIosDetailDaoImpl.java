package cn.adwalker.model.operation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.operation.dao.IActivateIosDetailDao;
import cn.adwalker.model.operation.domain.ActivateNumDetailIos;

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
@Repository("confirmationIosNumberDao")
public class ActivateIosDetailDaoImpl extends BaseDaoImpl<ActivateNumDetailIos>
		implements IActivateIosDetailDao {

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 */
	public ActivateIosDetailDaoImpl() {
		setTableName("T_IOS_ACTIVATE_NUM_DETAIL");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveConfIos
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IActivateIosDetailDao#saveConfIos(java.lang.Long[],
	 *      java.lang.Integer[])
	 */
	@Override
	public int saveConfIos(Long[] id, Integer[] confirm_num,
			Integer[] comfirm_cost) throws Exception {
		// 分完数的值更新到数据库中。
		if (id != null && id.length > 0) {
			String sql = "update T_IOS_ACTIVATE_NUM_DETAIL set CONFIRM_NUM=?,comfirm_cost=? where ID=?";
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (int i = 0; i < id.length; i++) {
				if (confirm_num[i] == null) {
					confirm_num[i] = 0;
				}
				parameters.add(new Object[] { confirm_num[i],confirm_num[i], id[i] });
			}
			this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
		}
		return 0;
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
			String sql = "update t_ios_activate_num_detail set CONFIRM_NUM=? where ID=?";
			this.jdbcTemplate.update(sql, new Object[] { confirm_num, id });
		}
		return 0;
	}
}
