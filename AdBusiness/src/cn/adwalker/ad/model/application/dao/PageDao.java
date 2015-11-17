package cn.adwalker.ad.model.application.dao;import java.util.ArrayList;import java.util.Date;import java.util.List;import javax.annotation.Resource;import org.apache.commons.lang.ArrayUtils;import org.springframework.jdbc.core.BeanPropertyRowMapper;import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;import org.springframework.jdbc.core.simple.ParameterizedSingleColumnRowMapper;import org.springframework.stereotype.Repository;import cn.adwalker.ad.model.application.domain.Page;@Repository("pageDao")public class PageDao {	@Resource	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;	private List<Page> findByApp(Long app_id, Integer type) {		List<Page> list = null;		if (app_id != null) {			String sql = " select * from T_PAGE  where app_id = " + app_id;			if (type != null) {				sql = sql + " and status>0";			}			list = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<Page>(Page.class));		}		return list;	}	public int saveAndUpdate(Long app_id, String arr[]) {		if (app_id != null && arr != null && arr.length > 0) {			List<Page> pageList = this.findByApp(app_id, null);			StringBuffer sql = new StringBuffer();			List<Object[]> parameters = new ArrayList<Object[]>();			if (pageList != null && pageList.size() > 0) {				for (Page page : pageList) {					if (ArrayUtils.contains(arr, String.valueOf(page.getType_id()))) {						if (page.getStatus() != 1) {							parameters.add(new Object[] { 1, page.getId() });						}					} else {						parameters.add(new Object[] { -1, page.getId() });					}				}				sql.append("update T_PAGE set STATUS=? where id=?");			} else {				String typeArr[] = new String[] { "0", "1", "2", "4", "5" };				sql.append("insert into T_PAGE (APP_ID,TYPE_ID,STATUS,CREATE_TIME,rate) values (?,?,?,?,?)");				for (String s : typeArr) {					Integer status = null;					if (ArrayUtils.contains(arr, s)) {						status = 1;					} else {						status = -1;					}					parameters.add(new Object[] { app_id, Integer.valueOf(s), status, new Date(), 1 });				}			}			namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql.toString(), parameters);		}		return 0;	}	public List<Page> findByApplication(Long applicationId) {		String sql = " select page.*,type.name type_name from T_PAGE page left join t_type type on page.type_id=type.id where page.app_id = " + applicationId + " and page.status>0 ";		List<Page> list = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<Page>(Page.class));		return list;	}	public List<Integer> findByApplicationId(Long applicationId) {		String sql = "select type_id from T_PAGE where status=1 and type_id!=3 and app_id= " + applicationId + " order by app_id";		List<Integer> list = namedParameterJdbcTemplate.query(sql, ParameterizedSingleColumnRowMapper.newInstance(Integer.class));		return (List<Integer>) list;	}	}