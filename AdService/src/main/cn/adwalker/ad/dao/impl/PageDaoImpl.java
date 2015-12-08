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
import cn.adwalker.ad.dao.IPageDao;
import cn.adwalker.ad.picker.statement.Statement;
import cn.adwalker.ad.picker.util.StringUtil;

/**
 * <p>Title: AdDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-4
 */
@Repository("pageDao")
public class PageDaoImpl extends CommonDao implements IPageDao {
	public String getTypeIdList(Long appId){
		Statement stms = stmsFactory.createNativeStatement("SELECT  GROUP_CONCAT(t.type_id SEPARATOR  '|') FROM t_page t WHERE status >? AND t.app_id=?");
		          stms.addParam(0,appId);
		          //simpleJdbcTemplate.queryfor
		          String obj =  simpleJdbcTemplate.findForObject(stms, String.class);
		          String typeId=StringUtil.dealNull(obj,"-1");
		          return "("+typeId+")";
	}
}
