package cn.adwalker.model.common.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.common.dao.ISysCategoryDao;
import cn.adwalker.model.common.domain.SysCategory;
import cn.adwalker.model.common.domain.ProvinceCitySort;

/**
 * 功能概述：<br>
 * 分类接口实现
 * 
 * @author guoyongxiang
 */
@Repository("escoreCategoryDaoImpl")
public class SysCategoryDaoImpl implements ISysCategoryDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getType
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.model.common.dao.ISysCategoryDao#getType()
	 */
	@Override
	public List<SysCategory> getType() {
		String sql = "select distinct TYPE from ESCORE_CATEGORY";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysCategory>(SysCategory.class));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPlatform
	 * </p>
	 * 
	 * @param type
	 * @return
	 * @see cn.adwalker.model.common.dao.ISysCategoryDao#getPlatform(java.lang.String)
	 */
	@Override
	public List<SysCategory> getPlatform(String type) {
		String sql = "select distinct(TYPE) TYPE,PLATFORM,PARENT_ID from T_PLATFORM_CATEGORY where PARENT_ID=?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysCategory>(SysCategory.class), type);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getprovinceCitySort
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.model.common.dao.ISysCategoryDao#getprovinceCitySort(int)
	 */
	@Override
	public List<ProvinceCitySort> getprovinceCitySort(int id) {
		String sql = "select * from T_PLATFORM_PROVINCE_CITY where PARENT_ID=? ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getCategory
	 * </p>
	 * 
	 * @param type
	 * @param platform
	 * @param parentId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.ISysCategoryDao#getCategory(java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public List<SysCategory> getCategory(String type, String platform, Long parentId) throws Exception {
		// 父级分类传null默认为查顶级分类
		if (ObjectUtils.isEmpty(parentId)) {
			parentId = 0l;
		}
		String sql = "select ID,NAME,TYPE,PLATFORM,SORT from ESCORE_CATEGORY where TYPE=? and PLATFORM=? and PARENT_ID=? order by 'SORT' asc ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysCategory>(SysCategory.class), new Object[] { type, platform, parentId });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getCategoryNew
	 * </p>
	 * 
	 * @param type
	 * @param parentId
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.ISysCategoryDao#getCategoryNew(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<SysCategory> getCategoryNew(String type, Long parentId) throws Exception {
		String sql = "select ID,NAME,TYPE,PLATFORM,SORT from ESCORE_CATEGORY where PARENT_ID=? order by 'SORT' asc ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysCategory>(SysCategory.class), new Object[] { parentId });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getCityId
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.model.common.dao.ISysCategoryDao#getCityId(java.lang.Long)
	 */
	@Override
	public ProvinceCitySort getCityId(Long id) {
		String sql = "select * from T_PLATFORM_PROVINCE_CITY where ID = ? order by 'SORT' asc";
		return (ProvinceCitySort) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getCategoryById
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.model.common.dao.ISysCategoryDao#getCategoryById(java.lang.Long)
	 */
	@Override
	public SysCategory getCategoryById(Long id) {
		SysCategory entity = null;
		if (id != null) {
			String sql = "select * from T_PLATFORM_CATEGORY where ID = ? order by 'SORT' asc ";
			List<SysCategory> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysCategory>(SysCategory.class), id);
			if (list != null && list.size() > 0) {
				entity = list.get(0);
			}
		}
		return entity;
	}

}
