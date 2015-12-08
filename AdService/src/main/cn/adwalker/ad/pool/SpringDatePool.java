/**
* <p>Title: SpringDatePool.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-15
* @version 1.0
*/
package cn.adwalker.ad.pool;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * <p>Title: SpringDatePool</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-15
 */
@Service("springDatePool")
public class SpringDatePool extends Thread implements InitializingBean{
	
	
	
	

	/**  
	* <p>Title: afterPropertiesSet</p>
	* <p>Description:TODO</p>
	* @throws Exception
	* @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	*/
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

}
