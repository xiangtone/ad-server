package cn.adwalker.ad.admin.ad.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.ad.bean.AdOffLineLogQueryBean;
import cn.adwalker.ad.admin.ad.bean.AdQueryBean;
import cn.adwalker.ad.admin.ad.vo.AdOffLineLogVo;
import cn.adwalker.ad.admin.ad.vo.AdQueryVo;

/**
 * 
 * @author zouguibao
 * @version 1.0
 * @since 2013-5-14
 */
public interface IAdQueryService {
	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-15
	 * @return List<MediaRatingVo>
	 * @version 1.0
	 * @throws
	 */

	public List<AdQueryVo> findByPage(AdQueryBean bean,
			IPageInfo pageInfor) throws Exception;

	/**
	* <p>Title: findOffLineLogByPage</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param pageInfo
	* @return
	* @author cuidd
	* @date 2013-10-30
	* @return List<AdOffLineLogVo>
	* @version 1.0
	 * @throws Exception 
	*/
	
	public List<AdOffLineLogVo> findOffLineLogByPage(
			AdOffLineLogQueryBean bean, IPageInfo pageInfo) throws Exception;
}
