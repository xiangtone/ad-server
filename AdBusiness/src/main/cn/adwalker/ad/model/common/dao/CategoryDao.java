package cn.adwalker.ad.model.common.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.common.domain.ApplicationCategory;
import cn.adwalker.ad.model.common.domain.EscoreCategory;
import cn.adwalker.ad.model.common.domain.ProvinceCitySort;

/**
 * 功能概述：<br>
 * 分类接口实现
 */
@Repository("categoryDao")
public class CategoryDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<EscoreCategory> findECList(Integer type) {
		String sql = "select t.id,t.name,(select e.name from T_ESCORE_SORT e where e.id=t.parent_id) as fname from T_ESCORE_SORT t where t.parent_id!=0 and t.type=? order by t.parent_id";
		return (List<EscoreCategory>) namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper<EscoreCategory>(EscoreCategory.class), type);
	}

	public String findECLById(Long id) {
		String s = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t1.NAME,t2.NAME AS fname FROM T_ESCORE_SORT t1 LEFT JOIN T_ESCORE_SORT t2 ON t1.parent_id=t2.id WHERE t1.TYPE=4 AND t1.id=?");
		List<Map<String, Object>> list = namedParameterJdbcTemplate.getJdbcOperations().queryForList(sql.toString(), new Object[] { id });
		Map<String, Object> map = null;
		if (list != null && list.size() > 0) {
			map = list.get(0);
			s = map.get("fname").toString() + "--" + map.get("name").toString();
		}
		return s;
	}

	public ApplicationCategory findApplicationCategoryByAppId(Long appId) throws Exception {
		String sql = "SELECT * FROM t_application_category WHERE app_id=?";
		List<ApplicationCategory> applicationCategoryList = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<ApplicationCategory>(ApplicationCategory.class), appId);
		ApplicationCategory applicationCategory = null;
		if (applicationCategoryList != null && applicationCategoryList.size() > 0) {
			applicationCategory = (ApplicationCategory) applicationCategoryList.get(0);
			return applicationCategory;
		}
		return null;
	}

	public Long saveApplicationCategory(ApplicationCategory applicationCategory) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into t_application_category(app_id,category_ids) values (:appId,:categoryIds)";
		namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(applicationCategory), keyHolder);
		return keyHolder.getKey().longValue();
	}

	public Long updateApplicationCategory(ApplicationCategory applicationCategory) throws Exception {
		String sql = "update t_application_category set app_id=:appId,category_ids=:categoryIds where ID=:id";
		namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(applicationCategory));
		return applicationCategory.getId();
	}

	public Integer deleteApplicationCategory(Long id) throws Exception {
		String sql = "DELETE FROM t_application_category WHERE app_id=?";
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), id);
	}
	
	public List<ProvinceCitySort> getprovinceCitySort(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_PLATFORM_PROVINCE_CITY where PARENT_ID=? ");
		return namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), id);
	}
	
	public List<EscoreCategory> getCategoryNew(String type, Long parentId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ID,NAME,TYPE,PLATFORM,SORT from t_escore_category where PARENT_ID =? ");
		sql.append(" order by 'SORT' asc ");
		return namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<EscoreCategory>(EscoreCategory.class), new Object[] { parentId });
	}
	
	public EscoreCategory getCategoryById(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_escore_category where ID = ? ");
		sql.append(" order by 'SORT' asc ");
		return (EscoreCategory) namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql.toString(), new BeanPropertyRowMapper<EscoreCategory>(EscoreCategory.class), id);
	}
	
	public ProvinceCitySort getCityId(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_PLATFORM_PROVINCE_CITY where ID = ? ");
		sql.append(" order by 'SORT' asc ");
		return (ProvinceCitySort) namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql.toString(), new BeanPropertyRowMapper<ProvinceCitySort>(ProvinceCitySort.class), id);
	}

}
