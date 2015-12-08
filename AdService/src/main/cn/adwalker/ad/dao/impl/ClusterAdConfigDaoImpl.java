/**
* <p>Title: AdDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-4
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IClusterAdConfigDao;
import cn.adwalker.ad.picker.statement.Statement;
import cn.adwalker.ad.picker.util.StringUtil;

/**
 * <p>Title: AdDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-4
 */
@Repository()
public class ClusterAdConfigDaoImpl extends CommonDao implements IClusterAdConfigDao {
	public String getTypeIdList(Long appId){
		Statement stms = stmsFactory.createNativeStatement("SELECT  GROUP_CONCAT(t.res_code SEPARATOR  '|') FROM t_config_cluster_ad t WHERE status >? AND t.app_id=?");
		          stms.addParam(0,appId);
		          String obj =  simpleJdbcTemplate.findForObject(stms, String.class);
		          String typeId=StringUtil.dealNull(obj,"-1");
		          return "("+typeId+")";
	}
}
