package cn.adwalker.model.common.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.common.dao.IProvinceCitySortDao;
import cn.adwalker.model.common.domain.ProvinceCitySort;

/**
 * 功能概述：<br>
 * 银行信息管理接口实现
 * 
 * @author zhaozengbin,guoyongxiang
 */
@Repository("provinceCitySortDao")
public class ProvinceCitySortDaoImpl implements IProvinceCitySortDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findprovinceCity
	 * </p>
	 * 
	 * @param parent_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IProvinceCitySortDao#findprovinceCity(int)
	 */
	@Override
	public List<ProvinceCitySort> findprovinceCity(int parent_id) throws Exception {
		String sql = "select * from T_PLATFORM_PROVINCE_CITY where PARENT_ID = ?";
		List<ProvinceCitySort> provinceCitySort = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), parent_id);
		if (provinceCitySort != null && provinceCitySort.size() > 0) {
			return provinceCitySort;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findIdCity
	 * </p>
	 * 
	 * @param parent_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.IProvinceCitySortDao#findIdCity(int)
	 */
	@Override
	public List<ProvinceCitySort> findIdCity(int parent_id) throws Exception {
		String sql = "select * from T_PLATFORM_PROVINCE_CITY where SORT=?";
		List<ProvinceCitySort> provinceCitySort = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), parent_id);
		if (provinceCitySort != null && provinceCitySort.size() > 0) {
			return provinceCitySort;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findprovinceCityBean
	 * </p>
	 * 
	 * @param category_Id
	 * @return
	 * @see cn.adwalker.model.common.dao.IProvinceCitySortDao#findprovinceCityBean(java.lang.Integer)
	 */
	@Override
	public ProvinceCitySort findprovinceCityBean(Integer category_Id) {
		String sql = "select * from t_platform_province_city where SORT=?";
		List<ProvinceCitySort> provinceCitySort = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), category_Id);
		if (provinceCitySort != null && provinceCitySort.size() > 0) {
			return provinceCitySort.get(0);
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findprovinceCityList
	 * </p>
	 * 
	 * @param category_Id
	 * @return
	 * @see cn.adwalker.model.common.dao.IProvinceCitySortDao#findprovinceCityList(java.lang.Integer)
	 */
	@Override
	public List<ProvinceCitySort> findprovinceCityList(Integer category_Id) {
		String sql = "select * from t_platform_province_city p where p.parent_id in (select t.parent_id from t_platform_province_city t where t.sort=?)";
		List<ProvinceCitySort> provinceCitySort = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), category_Id);
		if (provinceCitySort != null && provinceCitySort.size() > 0) {
			return provinceCitySort;
		}
		return null;
	}
}
