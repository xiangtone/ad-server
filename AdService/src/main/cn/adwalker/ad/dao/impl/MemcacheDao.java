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
import org.springframework.transaction.annotation.Transactional;
import cn.adwalker.ad.dao.CommonDao;


@Repository("memcacheDao")
@Transactional(readOnly=true)
public class MemcacheDao extends CommonDao {

	
}
