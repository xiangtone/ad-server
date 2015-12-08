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

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.bean.SimpleBean;
import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IAdClusterDao;
import cn.adwalker.ad.dao.domain.Ad;
import cn.adwalker.ad.dao.domain.AdCluster;
import cn.adwalker.ad.picker.statement.Statement;

/**
 * <p>Title: AdvertiseDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
@Repository
public class AdClusterDaoImpl extends CommonDao implements IAdClusterDao {

	
	/**  
	* <p>Title: getAdvertiseList</p>
	* <p>Description:TODO</p>
	* @param typeid
	* @return
	* @see cn.adwalker.ad.dao.IAdvertiseDao#getAdvertiseList(long)
	*/
	@Override
	public List<Ad> getAdClusterList() {
		Statement stms = stmsFactory.createNativeStatement("select ad.* from T_AD ad,t_ad_cluster c  where ad.cluster_id =c.id  and c.media_status=? and ad.ad_type=? and ad.status=? and c.blance_price>?");
		          stms.addParam(0,3,1,0d);
		          return simpleJdbcTemplate.queryList(stms, Ad.class);
	}


	@Override
	public AdCluster getAdClusterListId(Long id) {
		AdCluster entity=null;
		if (id!=null) {
			Statement stms = stmsFactory.createNativeStatement("select t.* from t_ad_cluster t  where t.id=?");
	        stms.addParam(id);
	        List<AdCluster> list=simpleJdbcTemplate.queryList(stms, AdCluster.class);
	        if (list!=null&&list.size()!=0) {
				entity=list.get(0);
			}
		}
		return entity;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Long getClusterIdByAdId(Long ad_id) {
		Long l=null;
		if (ad_id!=null) {
			Statement stms = stmsFactory.createNativeStatement("select cluster_id as obj from t_ad t  where t.id=?");
	        stms.addParam(ad_id);
	        List<SimpleBean<Long>> list=(List<SimpleBean<Long>>)simpleJdbcTemplate.findAll(stms, SimpleBean.class);
	        if (list!=null&&list.size()!=0) {
				l=list.get(0).getObj();
			}
		}
		return l;
	}
}
