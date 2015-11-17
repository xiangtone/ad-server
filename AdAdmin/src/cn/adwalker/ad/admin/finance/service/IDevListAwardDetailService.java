package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevListAwardDetailbean;
import cn.adwalker.ad.admin.finance.form.FinanceAwardDetail;
import cn.adwalker.ad.admin.finance.vo.DevListAwardDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;

public interface IDevListAwardDetailService {
	/**
	* <p>Title: findByCondition</p>
	* <p>Description:根据条件查询列表</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-9
	* @return List<DevListAwardDetailVo>
	* @version 1.0
	 */
	public List<DevListAwardDetailVo> findByCondition(
			DevListAwardDetailbean bean, IPageInfo pageInfo) throws Exception;

	/**
	* <p>Title: findByConditionSum</p>
	* <p>Description:根据条件查询sum</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-9
	* @return FinanceIncomeSumVo
	* @version 1.0
	 */
	public FinanceIncomeSumVo findByConditionSum(DevListAwardDetailbean bean)
			throws Exception;

	/**
	* <p>Title: findByEmail</p>
	* <p>Description: 查找网站主Email</p>
	* @param id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-9
	* @return String
	* @version 1.0
	 */
	public String findByEmail(Long id) throws Exception;

	/**
	* <p>Title: updateDev</p>
	* <p>Description:修改开发者可提款收入</p>
	* @param form
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-2
	* @return int
	* @version 1.0
	 */
	public int updateDev(FinanceAwardDetail form,SysUserVo currenUser) throws Exception;

	/**
	 * <p>
	 * Title: findByAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2013-5-21
	 * @return List<DevListAwardDetailVo>
	 * @version 1.0
	 * @throws Exception
	 */

	public List<DevListAwardDetailVo> findByAll(DevListAwardDetailbean bean)
			throws Exception;
}
