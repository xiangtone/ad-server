package cn.adwalker.ad.admin.ad.service;

import java.util.List;

import cn.adwalker.ad.admin.ad.bean.AdvSerach;
import cn.adwalker.ad.admin.ad.form.AdvAddForm;
import cn.adwalker.ad.admin.ad.form.AdvBankInfoform;
import cn.adwalker.ad.admin.ad.form.AdvForm;
import cn.adwalker.ad.admin.ad.vo.AdvInfoVo;
import cn.adwalker.ad.admin.ad.vo.AdvertiserVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.common.domain.SysCategory;

/**
 * 
 * <p>
 * Title: IRegistAdvService
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
public interface IAdvService {

	/**
	 * 用户注册
	 * @param manageUser 
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Long save(AdvAddForm registadvvo, SysUserVo manageUser) throws Exception;

	/**
	 * 检查用户是否存在
	 * 
	 * @param email
	 * @throws Exception
	 */
	public boolean exists(String checkParament) throws Exception;

	/**
	 * 查看信息
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
	 * @throws
	 */
	public AdvInfoVo getAdv(Long advId) throws Exception;

	/**
	 * 更新广告主信息
	 * <p>
	 * Title: updateAdvService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param registadvvo
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-2
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public void updateAdvService(AdvForm advForm, AdvBankInfoform advBankInfoVo) throws Exception;
	
	
	/**
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:修改账户状态
	 * </p>
	 * 
	 * @param advertiserId
	 * @param status
	 * @author lichuang
	 * @date 2013-4-15
	 * @return void
	 * @version 1.0
	 */

	void updateStatus(Long advertiserId, Integer status);

	
	/**
	 * <p>
	 * Title: getCountByCondition
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-1-7
	 * @return int
	 * @version 1.0
	 * @throws
	 */

	int getCountByCondition(String condition) throws Exception;

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
	 * @date 2013-4-12
	 * @return List<EscoreCategory>
	 * @version 1.0
	 * @throws
	 */

	List<SysCategory> findECList();
	/**
	* <p>Title: findBylist</p>
	* <p>Description:广告主列表查询</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-4-15
	* @return List<Advertiser>
	* @version 1.0
	 */
	public List<AdvertiserVo> findBylist(AdvSerach bean, IPageInfo pageInfo,SysUserVo manageUser)
			throws Exception;

}
