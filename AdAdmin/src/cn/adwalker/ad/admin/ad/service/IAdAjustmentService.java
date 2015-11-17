package cn.adwalker.ad.admin.ad.service;

import java.util.List;
import java.util.Map;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.common.domain.Sort;
import cn.adwalker.ad.admin.ad.bean.AdAjustmentBean;
import cn.adwalker.ad.admin.ad.form.AdAjustmentEditForm;
import cn.adwalker.ad.admin.ad.form.AdCategoryForm;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentEditVo;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentVo;
import cn.adwalker.ad.admin.ad.vo.AdCategoryVo;

/**
 * 
 * @author zouguibao  
 * @version 1.0
 * @since 2013-5-14
 */
public interface IAdAjustmentService {
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

	public List<AdAjustmentVo> findByPage(AdAjustmentBean bean,
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
	public AdAjustmentEditVo findById(Long adId) throws Exception;

	/**
	 * <p>
	 * Title: updateAdAjustment
	 * </p>
	 * <p>
	 * Description:修改广告投放方案
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void updateAdAjustment(AdAjustmentEditForm form,Double price_update,Long manger_id) throws Exception;

	/**
	 * <p>
	 * Title: adOnline
	 * </p>
	 * <p>
	 * Description:修改广告上线
	 * </p>
	 * 
	 * @param adId
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void adOnline(Long adId);

	/**
	 * <p>
	 * Title: adOffline
	 * </p>
	 * <p>
	 * Description:修改广告下线
	 * </p>
	 * 
	 * @param ids
	 * @author lichuang
	 * @date 2013-6-4
	 * @return void
	 * @version 1.0
	 */
	public void adbatchOffline(String ids);

	/**
	 * <p>
	 * Title: adSuspend
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @author cuidd
	 * @date 2013-7-10
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void adOffline(Long adId) throws Exception;
	
	
	/**
	 * 定向分类的更改
	 * <p>
	 * Title: updatecategory
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param actId
	 * @return
	 * @author lichuang
	 * @date 2013-4-8
	 * @return List<CampaignCategoryVo>
	 * @version 1.0
	 * @throws
	 */
	public List<AdCategoryVo> getCategory(Long actId) throws Exception;
	
	
	/**
	 * @throws Exception
	 *             <p>
	 *             Title: queryAllCategory
	 *             </p>
	 *             <p>
	 *             Description:TODO
	 *             </p>
	 * @author cuidd
	 * @date 2013-4-18
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public Map<String, List<Sort>> queryAllCategory() throws Exception;
	
	
	/**
	 * <p>
	 * Title: queryAllCity
	 * </p>
	 * <p>
	 * Description:城市
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-3
	 * @return Map<String,List<Sort>>
	 * @version 1.0
	 */
	public Map<String, List<Sort>> queryAllCity() throws Exception;
	
	public void updateAdCategory(AdCategoryForm form, Long[] category) throws Exception;
}
