/**
* <p>Title: AdvertiseDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.IOSChannel.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.IOSChannel.dao.IAdClusterDao;
import cn.adwalker.IOSChannel.picker.dao.CommonDao;
import cn.adwalker.IOSChannel.picker.statement.Statement;
import cn.adwalker.ad.cache.element.AdCluster;

/**
 * <p>Title: AdvertiseDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
@Repository
public class AdClusterDaoImpl extends CommonDao implements IAdClusterDao {
	@Override
	public AdCluster getAdClusterByAdId(Long id) {
		AdCluster entity=null;
		if (id!=null) {
			Statement stms = stmsFactory.createNativeStatement("select t.* from t_ad ad,t_ad_cluster t  where ad.cluster_id=t.id and ad.id=?");
	        stms.addParam(id);
	        List<AdCluster> list=simpleJdbcTemplate.queryList(stms, AdCluster.class);
	        if (list!=null&&list.size()!=0) {
				entity=list.get(0);
			}
		}
		return entity;
	}
}
