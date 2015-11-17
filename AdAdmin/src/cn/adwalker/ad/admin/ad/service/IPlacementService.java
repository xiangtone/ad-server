package cn.adwalker.ad.admin.ad.service;

import java.util.List;

import cn.adwalker.ad.admin.ad.bean.PlacementQueryBean;
import cn.adwalker.ad.admin.ad.form.PlacementEditForm;
import cn.adwalker.ad.admin.ad.form.ResForm;
import cn.adwalker.ad.admin.ad.vo.PlacementEditVo;
import cn.adwalker.ad.admin.ad.vo.PlacementInfoVo;
import cn.adwalker.ad.admin.ad.vo.PlacementVo;
import cn.adwalker.ad.admin.ad.vo.ResAddVo;
import cn.adwalker.ad.admin.ad.vo.ResVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.model.ad.domain.PlacementResChartboost;
import cn.adwalker.model.ad.domain.PlacementTypeRel;

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
public interface IPlacementService {

	/**
	 * <p>
	 * Title: getAdvEmailByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @author cuidd
	 * @date 2013-4-18
	 * @return void
	 * @version 1.0
	 * @throws
	 */

	String getAdvEmailByPlacement(Long placement_id);

	/**
	 * <p>
	 * Title: saveMaterialChartboost
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param vo
	 * @author cuidd
	 * @date 2013-4-14
	 * @return void
	 * @version 1.0
	 * @throws
	 */

	public void saveMaterialChartboost(Long id, PlacementResChartboost vo);

	/**
	 * @throws Exception
	 *             <p>
	 *             Title: updatePlacement
	 *             </p>
	 *             <p>
	 *             Description:TODO
	 *             </p>
	 * @param form
	 * @author cuidd
	 * @date 2013-4-16
	 * @return void
	 * @version 1.0
	 * @throws
	 */

	public void updatePlacement(PlacementEditForm form) throws Exception;

	/**
	 * <p>
	 * Title: getRes
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-5-9
	 * @return ResVo
	 * @version 1.0
	 * @throws Exception
	 */

	ResVo getRes(Long id) throws Exception;

	/**
	 * <p>
	 * Title: getRes
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-5-9
	 * @return ResVo
	 * @version 1.0
	 * @throws Exception
	 */

	ResAddVo getResAdd(Long id) throws Exception;

	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param manageUser
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-5-13
	 * @return List<CampaignVo>
	 * @version 1.0
	 * @throws Exception
	 */
	List<PlacementVo> findByPage(PlacementQueryBean bean, SysUserVo manageUser,
			IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: getPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-5-14
	 * @return Placement
	 * @version 1.0
	 * @throws Exception
	 */

	Placement getPlacement(Long id) throws Exception;

	/**
	 * <p>
	 * Title: auditingStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param ispass
	 * @param note
	 * @param manageUser
	 * @author cuidd
	 * @date 2013-5-15
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	void audit(Long id, Integer ispass, String note,
			SysUserVo manageUser) throws Exception;

	/**
	 * <p>
	 * Title: getPlacementEditVo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-5-15
	 * @return PlacementEditVo
	 * @version 1.0
	 * @param id
	 * @throws Exception
	 */

	PlacementEditVo getPlacementEditVo(Long id) throws Exception;

	/**
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:更新投放状态
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @param status
	 * @author cuidd
	 * @date 2013-5-16
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	void updateStatus(Long id, SysUserVo manageUser, Integer status)
			throws Exception;

	/**
	 * <p>
	 * Title: getPlacementInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-6-1
	 * @return PlacementEditVo
	 * @version 1.0
	 * @throws Exception
	 */
	PlacementInfoVo getPlacementInfo(Long id) throws Exception;

	/**
	 * <p>
	 * Title: findTypesByplacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-6-2
	 * @return List<PlacementTypeRel>
	 * @version 1.0
	 */

	List<PlacementTypeRel> findTypesByplacement(Long id);

	/**
	 * <p>
	 * Title: getTypeStr
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-6-2
	 * @return String
	 * @version 1.0
	 */

	String getTypeStr(Long id);

	/**
	 * <p>
	 * Title: updateMaterial
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param form
	 * @author cuidd
	 * @date 2013-6-2
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	void updateMaterial(Long id, ResForm form) throws Exception;

	/**
	 * <p>
	 * Title: submitPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2013-6-20
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	void submitPlacement(Long id) throws Exception;

	/**
	 * <p>
	 * Title: Offline
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @author cuidd
	 * @date 2013-7-18
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	void Offline(String ids) throws Exception;
}
