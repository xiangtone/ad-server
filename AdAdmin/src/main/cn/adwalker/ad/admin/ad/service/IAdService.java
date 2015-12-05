package cn.adwalker.ad.admin.ad.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.ad.bean.AdByPlacementBean;
import cn.adwalker.ad.admin.ad.form.AdAddForm;
import cn.adwalker.ad.admin.ad.form.AdEditForm;
import cn.adwalker.ad.admin.ad.vo.AdByPlacementVo;
import cn.adwalker.ad.admin.ad.vo.AdChannelVo;
import cn.adwalker.ad.admin.ad.vo.AdVo;
import cn.adwalker.ad.admin.ad.vo.AdertiseAppVo;

/**
 * 
 * <p>
 * Title: IApiService
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
public interface IAdService {

	/**
	 * <p>
	 * Title: findActivityAll
	 * </p>
	 * <p>
	 * Description:投放查询媒体接口
	 * </p>
	 * 
	 * @param actId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-9
	 * @return List<AdOld>
	 * @version 1.0
	 * @param type_id
	 */

	public List<AdertiseAppVo> findAll(String os, Long type_id)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param type_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-21
	 * @return List<AdertiseAppVo>
	 * @version 1.0
	 * @param channel
	 */
	public List<AdertiseAppVo> findAllChannel(Long type_id, String channel)
			throws Exception;

	/**
	 * @throws Exception
	 * 
	 *             <p>
	 *             Title: updateAd
	 *             </p>
	 *             <p>
	 *             Description:更新单个表单
	 *             </p>
	 * @param form
	 * @author cuidd
	 * @date 2013-4-17
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public void updateAd(AdEditForm form) throws Exception;
	/**
	 * @throws Exception
	 * 
	 *             <p>
	 *             Title: updateAd
	 *             </p>
	 *             <p>
	 *             Description:更新单个表单渠道
	 *             </p>
	 * @param form
	 * @author cuidd
	 * @date 2013-4-17
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public void updateAdCha(AdEditForm form) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: updateAdChannel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-10
	 * @return void
	 * @version 1.0
	 */
	public void updateAdChannel(AdEditForm form) throws Exception;

	/**
	 * @throws Exception
	 * @param page_type
	 *            <p>
	 *            Title: insert
	 *            </p>
	 *            <p>
	 *            Description:TODO
	 *            </p>
	 * @param placement_id
	 * @param appids
	 * @author cuidd
	 * @date 2013-4-17
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public Long insert(AdAddForm form, Integer adType) throws Exception;

	/**
	 * @throws Exception
	 *             <p>
	 *             Title: delete
	 *             </p>
	 *             <p>
	 *             Description:TODO
	 *             </p>
	 * @param id
	 * @author cuidd
	 * @date 2013-4-18
	 * @return void
	 * @version 1.0
	 * @throws
	 */

	public void delete(Long id) throws Exception;

	/**
	 * <p>
	 * Title: findByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @author cuidd
	 * @date 2013-5-10
	 * @return List<AdVo>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<AdVo> findAndroidByPlacement(Long placement_id, Integer ad_type)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findChannelByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @param ad_type
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-8
	 * @return List<AdVo>
	 * @version 1.0
	 */
	public List<AdChannelVo> findChannelByPlacement(Long placement_id)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-6-26
	 * @return List<AdVo>
	 * @version 1.0
	 */
	public List<AdVo> findByPlacement(Long placement_id) throws Exception;

	/**
	 * <p>
	 * Title: getStatusByPlacementId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @author cuidd
	 * @date 2013-6-13
	 * @return Integer
	 * @version 1.0
	 * @throws Exception
	 */

	public Integer getStatusByPlacementId(Long placement_id) throws Exception;

	/**
	 * <p>
	 * Title: findByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-6-25
	 * @return List<AdByPlacementVo>
	 * @version 1.0
	 */

	public List<AdByPlacementVo> findByPlacement(AdByPlacementBean bean,
			IPageInfo pageInfo);

	/**
	 * <p>
	 * Title: adBudget
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @author cuidd
	 * @date 2013-6-26
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public List<Long> adBudget() throws Exception;

	/**
	 * <p>
	 * Title: insertChannelAD
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @param i
	 * @return
	 * @author cuidd
	 * @date 2013-7-10
	 * @return Long
	 * @version 1.0
	 * @throws Exception
	 */

	public Long insertChannelAD(AdAddForm form) throws Exception;

	/**
	 * <p>
	 * Title: adBudgetonLine
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @author cuidd
	 * @date 2013-7-15
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public void adBudgetonLine() throws Exception;
	
	
	
	
	
	
}
