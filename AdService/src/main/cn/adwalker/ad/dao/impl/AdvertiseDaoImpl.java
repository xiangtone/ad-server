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

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IAdvertiseDao;
import cn.adwalker.ad.dao.domain.Ad;
import cn.adwalker.ad.dao.domain.AdList;
import cn.adwalker.ad.picker.statement.Statement;

/**
 * <p>Title: AdvertiseDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
@Repository("advertiseDao")
public class AdvertiseDaoImpl extends CommonDao implements IAdvertiseDao {

	
	/**  
	* <p>Title: getAdvertiseList</p>
	* <p>Description:TODO </p>
	* @param typeid
	* @return
	* @see cn.adwalker.ad.dao.IAdvertiseDao#getAdvertiseList(long)
	*/
	@Override
	public List<AdList> getAdvertiseList(String os,long typeId,long media_id) {
		//TODO 去掉*
		Statement stms = stmsFactory.createNativeStatement("select t.*,c.is_dsp,c.dsp_id,c.popular_app,c.category_id,c.is_url_params from T_AD t,T_PLACEMENT c  where c.id = t.placement_id and c.os= ? and t.type_id = ? and t.media_id= ? and t.status=1 and t.AD_TYPE=0");
		          stms.addParam(os,typeId,media_id);
		          return simpleJdbcTemplate.queryList(stms, AdList.class);
	}

	/**  (non-Javadoc)
	* <p>Title: getAdvertise</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @see cn.adwalker.ad.dao.IAdvertiseDao#getAdvertise(long)
	*/
	@Override
	public Ad getAdvertise(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from T_AD t where t.id=?");
		List<Ad> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<Ad>(Ad.class),new Object[]{id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
