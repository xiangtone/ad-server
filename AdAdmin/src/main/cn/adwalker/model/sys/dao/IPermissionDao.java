package cn.adwalker.model.sys.dao;

import java.util.List;
import java.util.Set;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.report.domain.TStatReportTable;
import cn.adwalker.model.sys.domain.Permission;
import cn.adwalker.model.sys.domain.SysPurview;

/**
 * 功能概述： <br>
 * 开发者管理接口
 * 
 * @author zhaozengbin
 */
public interface IPermissionDao extends IBaseDao<Permission> {

	/**
	 * 
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-15
	 * @return Integer
	 * @version 1.0
	 * @throws
	 */
	public Integer updateStatus(Long id, Integer status) throws Exception;

	/**
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-5-29
	 * @return List<SysResource>
	 * @version 1.0
	 * @throws Exception
	 */

	public List<Permission> findAll() throws Exception;

	public void updateCode(Long id, String code) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: addSysPurview
	 * </p>
	 * <p>
	 * Description:添加权限
	 * </p>
	 * 
	 * @param table
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return void
	 * @version 1.0
	 */
	void addSysPurview(TStatReportTable table);

	/**
	 * 
	 * <p>
	 * Title: updateSysPurview
	 * </p>
	 * <p>
	 * Description:修改权限
	 * </p>
	 * 
	 * @param table
	 * @author caiqiang
	 * @date 2013-1-22
	 * @return void
	 * @version 1.0
	 */
	void updateSysPurview(TStatReportTable table);

	Long findByTableId(Long id);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(Long id) throws Exception;

	/**
	 * 修改
	 * 
	 * @param sysPurview
	 * @return
	 * @throws Exception
	 */
	public int updateById(SysPurview sysPurview) throws Exception;

	/**
	 * 根据用id获取权限顶级节点
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SysPurview> findRootPurviewListByUserId(Long userId)
			throws Exception;

	/**
	 * 根据权限父id查询子菜单
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<SysPurview> findSubPurviewListByParentId(Long parentId,
			Long userId) throws Exception;

	/**
	 * 查询子菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysPurview> findSub() throws Exception;

	/**
	 * <p>
	 * Title: getButtonPermission
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-8-7
	 * @return List<String>
	 * @version 1.0
	 * @throws Exception
	 */
	public Set<String> getButtonPermission(Long id) throws Exception;

}
