package cn.adwalker.ad.admin.sales.service;

import java.util.List;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.bean.CampaignSalesmanBean;
import cn.adwalker.ad.admin.sales.form.SalesmanAddForm;
import cn.adwalker.ad.admin.sales.form.SalesmanEditForm;
import cn.adwalker.ad.admin.sales.vo.CampaignSalesmanVo;
import cn.adwalker.ad.admin.sales.vo.SalesmanEditVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * 
 * @author zouguibao
 * @version 1.0
 * @since 2013-5-14
 */
public interface ICampaignSalesmanService {
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

	public List<CampaignSalesmanVo> findByPage(CampaignSalesmanBean bean,
			IPageInfo pageInfor) throws Exception;

	/**
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:方案详细内容查询
	 * </p>
	 * 
	 * @param adId
	 * @return
	 * @author lichuang
	 * @date 2013-6-4
	 * @return AdAjustmentEditVo
	 * @version 1.0
	 * @throws Exception
	 */
	public SalesmanEditVo findById(Long adId) throws Exception;


	/**
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2014-2-19
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public void delete(Long id) throws Exception;

	/**
	 * <p>
	 * Title: saveSalesman
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @author cuidd
	 * @date 2014-2-26
	 * @return void
	 * @version 1.0
	 * @param manageUser
	 * @throws Exception
	 */
	public void saveSalesman(SalesmanAddForm form, SysUserVo manageUser)
			throws Exception;

	/**
	 * <p>
	 * Title: updateSalesman
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @author cuidd
	 * @date 2014-2-27
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void updateSalesman(SalesmanEditForm form) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: querySysUser
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2014-2-27
	 * @return List<SysUserVo>
	 * @version 1.0
	 * @throws Exception 
	 */
	public List<SysUserVo> querySysUser(Long id) throws Exception;
	
	
	public Long getSalesmanIdBySysUser(Long id) throws Exception;
}
