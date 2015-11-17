package cn.adwalker.model.common.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.common.domain.Sort;
import cn.adwalker.model.common.domain.SysCategory;

/**
 * 
 * <p>
 * Title: IEscoreSortDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-6-4
 */
public interface ISysSortDao extends IBaseDao<Sort> {

	/**
	 * 
	 * <p>
	 * Title: getAdCategory
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-6-16
	 * @return List<Sort>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<Sort> getAdCategory() throws Exception;

	/**
	 * 
	 * <p>
	 * Title: queryAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-6-16
	 * @return List<Sort>
	 * @version 1.0
	 */
	public List<Sort> queryAll() throws Exception;

	/**
	 * <p>
	 * Title: queryAllCity
	 * </p>
	 * <p>
	 * Description:省
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-3
	 * @return List<Sort>
	 * @version 1.0
	 */
	public List<Sort> queryAllCity() throws Exception;

	/**
	 * <p>
	 * Title: findSortCity
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param sort
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-3
	 * @return List<Sort>
	 * @version 1.0
	 */
	public List<Sort> findSortCity(Integer sort) throws Exception;

	/**
	 * <p>
	 * Title: findECList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-1-23
	 * @return List<EscoreCategory>
	 * @version 1.0
	 * @throws
	 */

	public List<SysCategory> findECList();
}
