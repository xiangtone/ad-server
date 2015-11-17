package cn.adwalker.model.app.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.app.dao.IApplicationBlackDao;
import cn.adwalker.model.app.domain.ApplicationBlack;

/**
 * <p>
 * Title: ApiLogDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-16
 */
@Repository("applicationBlackDao")
public class ApplicationBlackDaoImpl extends BaseDaoImpl<ApplicationBlack> implements IApplicationBlackDao {
	public ApplicationBlackDaoImpl() {
		setTableName("t_application_config");
	}


	@Override
	public void createApplicationBlack(List<Object[]> paramsList) throws Exception {
		String sql = "insert into t_application_config (application_id,placement_id) SELECT ?,placement_id FROM t_campaign_placement_rel WHERE campaign_id=?";
		super.batchUpdate(sql, paramsList);
	}

	@Override
	public Integer deleteApplicationBlackByApplicationid(Long applicationId) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (applicationId != null) {
			sb.append("delete FROM t_application_config where application_id=");
			sb.append(applicationId);
		}
		return jdbcTemplate.update(sb.toString());
	}
}
