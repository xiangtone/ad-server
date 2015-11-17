/**
 * <p>Title: BalancePlatformRelDaoImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-6
 * @version 1.0
 */
package cn.adwalker.model.finance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.IBalancePlatformRelDao;
import cn.adwalker.model.finance.domain.BalancePlatformRel;

/**
 * <p>
 * Title: BalancePlatformRelDaoImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-12-6
 */
@Repository
public class BalancePlatformRelDaoImpl extends BaseDaoImpl<BalancePlatformRel>
		implements IBalancePlatformRelDao {
	public BalancePlatformRelDaoImpl() {
		setTableName("T_BALANCE_PLATFORM_REL");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchInsert
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @see cn.adwalker.model.finance.dao.IBalancePlatformRelDao#batchInsert()
	 */
	@Override
	public void batchInsert(List<BalancePlatformRel> list) {
		List<Object[]> objects = new ArrayList<Object[]>();
		if (list != null && list.size() > 0) {
			for (BalancePlatformRel entity : list) {
				objects.add(new Object[] { entity.getBalance_id(),
						entity.getCreate_time(), entity.getDetail_id() });
			}
			jdbcTemplate
					.batchUpdate(
							"insert into T_BALANCE_PLATFORM_REL (BALANCE_ID,CREATE_TIME,DETAIL_ID)values(?,?,?)",
							objects);

		}

	}

}
