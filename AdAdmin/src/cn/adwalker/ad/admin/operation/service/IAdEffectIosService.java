package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.operation.bean.AdEffectIosbean;
import cn.adwalker.ad.admin.operation.form.AdIosEffect;
import cn.adwalker.ad.admin.operation.vo.AdEffectIosVo;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmIos;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmIosEditVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * <p>
 * Title: IConfirmationNumberService
 * </p>
 * <p>
 * Description:TODO   
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */
public interface IAdEffectIosService {

	/**
	 * <p>
	 * Title: findList
	 * </p>
	 * <p>
	 * Description:广告主确认数录入List
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-23
	 * @return List<ConfirmationNumberVo>
	 * @version 1.0
	 */

	public List<AdEffectIosVo> findList(AdEffectIosbean bean, IPageInfo pageInfo)
			throws Exception;

	/**
	 * <p>
	 * Title: getAdByCampaign
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @author cuidd
	 * @date 2013-9-12
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public List<CampaignConfirmIos> initData(AdIosEffect form) throws Exception;


	/**
	 * <p>
	 * Title: addConfirmNum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @param priceList
	 * @param confirm_numList
	 * @return
	 * @author cuidd
	 * @date 2013-9-12
	 * @return List<CampaignConfirmIos>
	 * @version 1.0
	 * @param effect_id
	 * @throws Exception
	 */

	public void addConfirmNum(Long effect_id, Long[] ids, Double[] priceList,
			Integer[] confirm_numList) throws Exception;

	/**
	 * <p>
	 * Title: getEffectDetail
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2013-9-13
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public List<CampaignConfirmIosEditVo> getEffectDetail(Long id) throws Exception;

	/**
	* <p>Title: submitEffect</p>
	* <p>Description:TODO</p>
	* @param effect_id
	* @author cuidd
	* @date 2013-9-13
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	
	public void submitEffect(Long effect_id) throws Exception;

	/**
	* <p>Title: deleteEffect</p>
	* <p>Description:TODO</p>
	* @param effect_id
	* @author cuidd
	* @date 2013-9-13
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	
	public void deleteEffect(Long effect_id) throws Exception;
}
