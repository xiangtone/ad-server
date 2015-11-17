/**
 * 
 */
package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.ad.admin.app.bean.AppQuery;
import cn.adwalker.ad.admin.app.form.AppAddForm;
import cn.adwalker.ad.admin.app.form.AppEditForm;
import cn.adwalker.ad.admin.app.vo.AppEditVo;
import cn.adwalker.ad.admin.app.vo.AppListVo;
import cn.adwalker.ad.admin.app.vo.ApplicationVo;
import cn.adwalker.ad.admin.app.vo.VApplication;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.domain.AppCurrency;
import cn.adwalker.model.app.domain.AppMedia;
import cn.adwalker.model.app.domain.Application;

/**
 * 功能概述：<br>
 * 开发者SDK应用服务接口
 * 
 * @author guoyongxiang
 */
public interface IApplicationService {

	/**
	 * 
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:修改APP信息
	 * </p>
	 * 
	 * @param developedApp
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-1-21
	 * @return void
	 * @version 1.0
	 * @param ad_res 
	 * @param types
	 */
	public void update(AppEditForm form, String[] pageType, String[] ad_res, SysUserVo user)
			throws Exception;

	/**
	 * @throws Exception
	 * @param bean
	 * 
	 *            <p>
	 *            Title: findByPage
	 *            </p>
	 *            <p>
	 *            Description:后台应用列表分页查询
	 *            </p>
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-1-17
	 * @return List<VApplication>
	 * @version 1.0
	 * @throws
	 */
	public List<AppListVo> findByPage(IPageInfo pageInfo, AppQuery bean)
			throws Exception;

	/**
	 * @throws Exception
	 * @param bean
	 * 
	 *            <p>
	 *            Title: findByPage
	 *            </p>
	 *            <p>
	 *            Description:后台应用扣了配置
	 *            </p>
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-1-17
	 * @return List<VApplication>
	 * @version 1.0
	 * @throws
	 */
	// public List<AppDeductionUp> findByDeduction(IPageInfo pageInfo,
	// AppDeduction bean)
	// throws Exception;

	/**
	 * 根据开发者ID查询APP列表
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Application> findByUser(Long id) throws Exception;

	/**
	 * 根据ID查询单个APP
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Application get(Long id) throws Exception;

	/**
	 * 根据ID查询单个APP
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApplicationVo getById(Long id) throws Exception;
	
	
	public AppEditVo getByAppForEdit(Long id) throws Exception;
	

	/**
	 * 添加APP信息
	 * 
	 * @param theme
	 * 
	 * @param developedApp
	 * @throws Exception
	 */
	public Long insert(AppAddForm form, String types[], SysUserVo user)
			throws Exception;

	/**
	 * 修改APP信息-上传截图
	 * 
	 * @param developedApp
	 * @param appScreenshotPaths
	 * @throws Exception
	 */
	public void update(Application developedApp) throws Exception;

	/**
	 * 根据开发者ID批量修改
	 * 
	 * @param developedApp
	 * @return
	 * @throws Exception
	 */
	public Long updateByDevId(Application developedApp) throws Exception;

	/**
	 * @throws Exception
	 *             <p>
	 *             Title: findByPage
	 *             </p>
	 *             <p>
	 *             Description:TODO
	 *             </p>
	 * @param developerId
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-1-26
	 * @return int
	 * @version 1.0
	 * @throws
	 */

	public List<VApplication> findByPage(Long developerId, IPageInfo pageInfo)
			throws Exception;

	/**
	 * <p>
	 * Title: updateAppApkInf
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param developedApp
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-1-29
	 * @return void
	 * @version 1.0
	 * @throws
	 */

	void updateAppApkInf(Application developedApp) throws Exception;

	/**
	 * 修改 扣量比例
	 * <p>
	 * Title: updatePrice
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param rate
	 * @author lichuang
	 * @date 2013-3-20
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public void updateAppRate(Long id, Double rate);

	/**
	 * <p>
	 * Title: audit
	 * </p>
	 * <p>
	 * Description:应用审核
	 * </p>
	 * 
	 * @param ispass
	 * @param reason
	 * @author cuidd
	 * @date 2013-7-2
	 * @return void
	 * @version 1.0
	 * @param appId
	 * @throws Exception
	 */
	public Application audit(Long appId, boolean ispass, String reason)
			throws Exception;

	/**
	 * <p>
	 * Title: updateAppStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @param status
	 * @author cuidd
	 * @date 2013-7-2
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void updateAppStatus(Long appId, Integer status) throws Exception;

	/**
	 * <p>
	 * Title: getTypeList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param app_id
	 * @return
	 * @author cuidd
	 * @date 2013-7-3
	 * @return List<String>
	 * @version 1.0
	 */

	List<String> getTypeList(Long app_id);
	
	
	/**
	 * 
	* <p>Title: findCurrencyByAppId</p>
	* <p>Description:查询虚拟货币</p>
	* @param app_id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014-1-21
	* @return AppCurrency
	* @version 1.0
	 */
	public AppCurrency findCurrencyByAppId(Long app_id) throws Exception;

	
	
	
	List<AppMedia> getAppMediaList() throws Exception;
	
	
	
	
}