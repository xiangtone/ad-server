package cn.adwalker.model.common.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.common.dao.ISysSortDao;
import cn.adwalker.model.common.domain.SysCategory;
import cn.adwalker.model.common.domain.Sort;

/**
 * 
 * <p>
 * Title: CampaignDaoImpl
 * </p>
 * <p>
 * Company: adwalker 
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
@Repository("escoreSortDao")
public class SysSortDaoImpl extends BaseDaoImpl<Sort> implements ISysSortDao {

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: queryAll
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.ISysSortDao#queryAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sort> queryAll() throws Exception {
		return (List<Sort>) super.findAll("select id,type,content_value,name  from T_ESCORE_SORT", Sort.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: queryAllCity
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.ISysSortDao#queryAllCity()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sort> queryAllCity() throws Exception {
		return (List<Sort>) super.findAll("select id,type,content_value,name  from T_ESCORE_SORT  where parent_id=0 ", Sort.class);
	}

	@Override
	public List<Sort> findSortCity(Integer sort) throws Exception {
		String sql = "select * from t_escore_sort t  where 1=1 and parent_id=?";
		List<Sort> list = (List<Sort>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Sort>(Sort.class), sort);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findECList
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.model.common.dao.ISysCategoryDao#findECList()
	 */
	@Override
	public List<SysCategory> findECList() {
		String sql = "select t.id,t.name,(select e.name from T_ESCORE_SORT e where e.id=t.parent_id) as fname from T_ESCORE_SORT t where t.parent_id!=0 and t.type=4 order by t.parent_id";
		return (List<SysCategory>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysCategory>(SysCategory.class));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdCategory
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.ISysSortDao#getAdCategory()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sort> getAdCategory() throws Exception {
		return (List<Sort>) findAll("select * from T_ESCORE_SORT  where parent_id=0 and type=4", Sort.class);
	}

}
