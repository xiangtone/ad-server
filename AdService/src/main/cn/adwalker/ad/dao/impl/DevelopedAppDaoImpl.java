package cn.adwalker.ad.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IDevelopedAppDao;
import cn.adwalker.ad.picker.statement.Statement;


@Repository("developedAppDao")
public class DevelopedAppDaoImpl extends CommonDao implements IDevelopedAppDao {
	
	/**  (non-Javadoc)
	* <p>Title: getApplication</p>
	* <p>Description:TODO</p>
	* @param category_id
	* @return
	* @see cn.adwalker.ad.dao.IDevelopedAppDao#getApplication(java.lang.Long)
	*/
	@Override
	public DevApp getApplication(Long appId) {
		String str="SELECT  "
				+ "t.id,"
				+ "t.category_id,"
				+ "t.os,"
				+ "t.name,"
				+ "t.package_name,t."
				+ "version_code,"
				+ "t.placement,"
				+ "t.is_coordinate,"
				+ "t.scale,t.appkey,"
				+ "t.dev_id,"
				+ "t.status,"
				+ "t.del,"
				+ "a.response_url FROM t_application t LEFT JOIN t_cooperation_app_rel a ON t.id=a.app_id WHERE t.id=?";
		Statement stms = stmsFactory.createNativeStatement(str);
		          stms.addParam(appId);
		          return simpleJdbcTemplate.findObject(stms, DevApp.class);
	}


	@Deprecated
	public DevApp findOne(Long id) {
		Statement stms = stmsFactory.createNativeStatement("select id as appId,appkey,DEV_ID as developerId,package_name as packageName,name as appName, status,del,OS");
		          stms.append("from t_application where id = ?");
		          stms.addParam(id);
		          return simpleJdbcTemplate.findObject(stms, DevApp.class);
	}

	/**  (non-Javadoc)
	* <p>Title: appList</p>
	* <p>Description:TODO</p>
	* @return
	* @see cn.adwalker.ad.dao.IDevelopedAppDao#appList()
	*/
	@Override
	public List<DevApp> appList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_application t where t.STATUS=4");
		List<DevApp> list = simpleJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DevApp>(DevApp.class));
		return list;
	}

	
	
	/**  (non-Javadoc)
	* <p>Title: appPlacementList</p>
	* <p>Description:TODO</p>
	* @return
	* @see cn.adwalker.ad.dao.IDevelopedAppDao#appPlacementList()
	*/
	@Override
	public List<Map<String, Object>> appPlacementList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct PLACEMENT as PLACEMENT  from t_application t ");
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql.toString());
		return list;
	}

	/**  (non-Javadoc)
	* <p>Title: findByAppKey</p>
	* <p>Description:TODO</p>
	* @param key
	* @return
	* @see cn.adwalker.ad.dao.IDevelopedAppDao#findByAppKey(java.lang.String)
	*/
	@Override
	public DevApp findByAppKey(String key) {
		String str="SELECT  "
				+ "t.id,"
				+ "t.category_id,"
				+ "t.os,"
				+ "t.name,"
				+ "t.package_name,"
				+ "t.version_code,"
				+ "t.placement,"
				+ "t.is_coordinate,"
				+ "t.scale,t.appkey,"
				+ "t.dev_id,"
				+ "t.status,"
				+ "t.del,"
				+ "a.response_url FROM t_application t LEFT JOIN t_cooperation_app_rel a ON t.id=a.app_id WHERE t.appkey=?";
		Statement stms = stmsFactory.createNativeStatement(str);
		          stms.addParam(key);
		          return simpleJdbcTemplate.findObject(stms, DevApp.class);
	}
	
	

}
