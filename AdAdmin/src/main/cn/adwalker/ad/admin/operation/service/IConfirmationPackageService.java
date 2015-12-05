package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.operation.bean.ConfirmPackagebean;
import cn.adwalker.ad.admin.operation.vo.ConfirmationPackageVo;
import cn.adwalker.core.page.IPageInfo;

/**
* <p>Title: IConfirmationPackageService</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-7-16
 */
public interface IConfirmationPackageService {

	/**
	* <p>Title: findList</p>
	* <p>Description:渠道包备注添加</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-16
	* @return List<ConfirmationNumberVo>
	* @version 1.0
	 */

	public List<ConfirmationPackageVo> findList(ConfirmPackagebean bean,
			IPageInfo pageInfo) throws Exception;
	/**
	* <p>Title: Packageremarks</p>
	* <p>Description:添加备注</p>
	* @param id
	* @param packageremarks
	* @author lichuang
	* @date 2013-7-16
	* @return void
	* @version 1.0
	 */
	public void packageremarks(Long id, String packageremarks) throws Exception ;

	
}
