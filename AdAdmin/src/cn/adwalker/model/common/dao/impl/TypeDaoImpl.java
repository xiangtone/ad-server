package cn.adwalker.model.common.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.ad.domain.Type;
import cn.adwalker.model.common.dao.ITypeDao;

/**
 * 
 * <p>
 * Title: TypeDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-27
 */
@Repository("typeDao")
public class TypeDaoImpl implements ITypeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAppWallType
	 * </p>
	 * 
	 * @param appID
	 * @return
	 * @see cn.adwalker.model.common.dao.ITypeDao#getAppWallType(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getAppWallType(Long appID) {
		String sql = "select * from PROVINCE_CITY_SORT where ID=? order by 'SORT' asc";
		return (List<Type>) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Type>(Type.class), appID);
	}
}
