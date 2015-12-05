package cn.adwalker.model.app.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.AppMedia;

/**
 * 
 * <p>
 * Title: IAdDao
 * </p>
 * <p>
 * Description:TODOfff
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
public interface IAppMediaDao extends IBaseDao<AppMedia> {

	/**
	 * 
	 * <p>
	 * Title: all
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param area_type
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014-8-13
	 * @return List<AppMedia>
	 * @version 1.0
	 */
	public List<AppMedia> all() throws Exception;

	/**
	 * 更新销售信息
	 * <p>
	 * Title: updateSalesmanServic
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param advSalesmanVo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-2
	 * @return int
	 * @version 1.0
	 */
	public long updateSalesman(AppMedia advSalesman) throws Exception;

	/**
	 * 查看销售信息
	 * <p>
	 * Title: findAdvInformation
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param advId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-1
	 * @return AdvInfoVo
	 * @version 1.0
	 */
	public AppMedia findSalesmanInfo(Long advId) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: getSalesmanIdBySysUser
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014-8-14
	 * @return Long
	 * @version 1.0
	 */
	public Long getSalesmanIdBySysUser(Long id) throws Exception;
	
	public AppMedia getEntityBySysUser(Long id) throws Exception;

}
