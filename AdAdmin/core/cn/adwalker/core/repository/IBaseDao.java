/**
 * 
 */
package cn.adwalker.core.repository;

import java.util.List;
import java.util.Map;

import cn.adwalker.core.page.IPageInfo;

/**
 * 功能概述：<br>
 * 复杂查询
 * 
 * @author zhaozengbin,wjp
 */
public abstract interface IBaseDao<E> {

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:根据条件查询所有
	 * </p>
	 * 
	 * @param columns
	 * @param tableAndWhere
	 * @param object
	 * @param orderBy
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-14
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findAll(String columns, String tableAndWhere, Object[] object, String orderBy, Class<?> clazz) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findTop
	 * </p>
	 * <p>
	 * Description: 查询前xx调
	 * </p>
	 * 
	 * @param columns
	 * @param tableAndWhere
	 * @param object
	 * @param orderBy
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-19
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findTop(String columns, String tableAndWhere, Object[] object, String orderBy, Class<?> clazz, int top) throws Exception;
	
	
	/**
	 * 
	* <p>Title: findTop</p>
	* <p>Description:TODO</p>
	* @param sql
	* @param object
	* @param orderBy
	* @param clazz
	* @param top
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-12-5
	* @return List<?>
	* @version 1.0
	 */
	public List<?> findTop(String sql, Object[] object,Class<?> clazz, int top)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:查询不带排序
	 * </p>
	 * 
	 * @param columns
	 * @param tableAndWhere
	 * @param object
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-17
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findAll(String columns, String tableAndWhere, Object[] object, Class<?> clazz) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:根据条件返回所有
	 * </p>
	 * 
	 * @param sql
	 * @param object
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-14
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findAll(String sql, Object[] object, Class<?> clazz) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:根据条件返回所有
	 * </p>
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-14
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findAll(String sql, Class<?> clazz) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @param columns
	 * @param table
	 * @param condition
	 * @param argsObject
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-1-16
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findByPage(String columns, String tableAndWhere, Object[] object, String orderBy, Class<?> clazz, IPageInfo pageInfor) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @param columns
	 * @param table
	 * @param condition
	 * @param argsObject
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-1-16
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findByPage(String columns, String tableAndWhere, Object[] object, Class<?> clazz, IPageInfo pageInfor) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @param columns
	 * @param table
	 * @param condition
	 * @param argsObject
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-1-16
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findByPage(String columns, String tableAndWhere, Class<?> clazz, IPageInfo pageInfor) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:分页查询
	 * </p>
	 * 
	 * @param columns
	 * @param table
	 * @param orderBy
	 * @param clazz
	 * @param pageInfor
	 * @return
	 * @author cuidd
	 * @date 2013-1-17
	 * @return List<?>
	 * @version 1.0
	 */
	public List<?> findByPage(String columns, String table, String orderBy, Class<?> clazz, IPageInfo pageInfor);

	/**
	 * 组装带函数的sql语句
	 * 
	 * @param functions
	 *            函数及要使用函数的参数 结构样例key:max,value:String[]{"DEV_ID|DEV_ID","CREATE_TIME|CREATE_TIME" }"|"前为字段 "|"后为别名，此语句会自动组装为max(DEV_ID) DEV_ID max(CREATE_TIME) CREATE_TIME
	 * @param fromCondition
	 *            数据库名
	 * @param whereCondition
	 *            查询条件
	 * @param groupCondition
	 *            分组字段
	 * @return
	 * @throws Exception
	 */
	public String findReportData(Map<String, String[]> functions, String fromCondition, List<String> whereCondition, List<String> groupCondition, List<String> orderCondition) throws Exception;

	/**
	 * 根据sql获取记录数
	 * 
	 * @param sql
	 * @return
	 */
	public int getCount(String sql);

	public int countQuery(String sql, Object[] objects);

	public List<?> findAlll(int pageIndex, int pageRecord, String sql, Object object) throws Exception;

	/**
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:插入
	 * </p>
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-12
	 * @return Long
	 * @version 1.0
	 */
	public Long insert(Object obj) throws Exception;

	/**
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:插入
	 * </p>
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-12
	 * @return Long
	 * @version 1.0
	 */
	public void update(String sql, Object[] objects) throws Exception;

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:更新
	 * </p>
	 * 
	 * @param obj
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-12
	 * @return void
	 * @version 1.0
	 */
	public void update(Object obj) throws Exception;

	/**
	 * <p>
	 * Title: updateBatch
	 * </p>
	 * 
	 * @param obj
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-14
	 * @return void
	 * @version 1.0
	 */
	public void updateBatch(List<?> obj, Class<?> clazz) throws Exception;

	/**
	 * @return
	 * 
	 *         <p>
	 *         Title: get
	 *         </p>
	 * @param id
	 * @param clazz
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-16
	 * @return void
	 * @version 1.0
	 */
	public <T extends Entity> T get(Long id, Class<?> clazz) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:删除
	 * </p>
	 * 
	 * @param obj
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-18
	 * @return void
	 * @version 1.0
	 */
	public void delete(Long id) throws Exception;

	/**
	 * <p>
	 * Title: batchUpdate
	 * </p>
	 * 
	 * @param string
	 * @param ads
	 * @author cuidd
	 * @date 2013-6-28
	 * @return void
	 * @version 1.0
	 */

	void batchUpdate(String str, List<Object[]> objects);

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param string
	 * @param beanPropertySqlParameterSource
	 * @author cuidd
	 * @date 2013-10-25
	 * @return void
	 * @version 1.0
	 */
	int update(String string, Object obj);

	long insert(String string, Object obj);
	
	
	void batchInsert(String str, List<Object[]> objects);
}
