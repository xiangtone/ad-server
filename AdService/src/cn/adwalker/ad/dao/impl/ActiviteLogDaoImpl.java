/**
* <p>Title: AdvertiseDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IActiviteLogDao;
import cn.adwalker.ad.dao.domain.AdActivateNum;
import cn.adwalker.ad.picker.statement.Statement;
import cn.adwalker.ad.util.DateUtil;

/**
 * <p>Title: AdvertiseDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
@Repository
public class ActiviteLogDaoImpl extends CommonDao implements IActiviteLogDao {

	
	/**  
	* <p>Title: getAdvertiseList</p>
	* <p>Description:TODO</p>
	* @param typeid
	* @return
	* @see cn.adwalker.ad.dao.IAdvertiseDao#getAdvertiseList(long)
	*/
	@Override
	public List<AdActivateNum> getAdActiviteNum() {
		Statement stms = stmsFactory.createNativeStatement("SELECT t.`ad_key` as ad_id,COUNT(1) activate_num FROM `t_ios_activite_log` t WHERE t.`create_time`>=? and t.`channel`=? GROUP BY t.`ad_id`");
		          stms.addParam(DateUtil.format(new Date()),"adwalker");
		          return simpleJdbcTemplate.queryList(stms, AdActivateNum.class);
	}

	@Override
	public AdActivateNum getAdActiviteNumById(Long ad_id) {
		AdActivateNum num=null;
		Statement stms = stmsFactory.createNativeStatement("SELECT t.`ad_key` as ad_id,COUNT(1) activate_num FROM `t_ios_activite_log` t WHERE t.`create_time`>=? and t.ad_key=? and t.`channel`=? GROUP BY t.`ad_id`");
        stms.addParam(DateUtil.format(new Date()),ad_id,"adwalker");
        List<AdActivateNum> list=simpleJdbcTemplate.queryList(stms, AdActivateNum.class);
        if (list!=null&&list.size()>0) {
			num=list.get(0);
		}
		return num;
	}
}
