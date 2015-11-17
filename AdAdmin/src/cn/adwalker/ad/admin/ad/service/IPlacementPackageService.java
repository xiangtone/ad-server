package cn.adwalker.ad.admin.ad.service;

import java.util.List;

import cn.adwalker.model.ad.domain.PlacementPackage;
import cn.adwalker.ad.admin.ad.form.IosPackageForm;
import cn.adwalker.ad.admin.ad.form.PackageForm;
import cn.adwalker.ad.admin.ad.vo.AdvVo;
import cn.adwalker.ad.admin.ad.vo.PlacementPackageEditVo;
import cn.adwalker.ad.admin.ad.vo.PlacementPackageVo;

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
public interface IPlacementPackageService {

	/**
	 * 更新广告
	 * 
	 * @param advertisement
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PlacementPackageVo uploadPath(PackageForm advertisement)
			throws Exception;

	/**
	 * <p>
	 * Title: findAdvByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @author cuidd
	 * @date 2013-5-9
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	AdvVo findAdvByPlacement(Long placement_id) throws Exception;

	/**
	 * <p>
	 * Title: findByPPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @author cuidd
	 * @date 2013-5-9
	 * @return List<PlacementPackageVo>
	 * @version 1.0
	 * @throws Exception
	 */

	List<PlacementPackage> findByPlacement(Long placement_id) throws Exception;

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
	 * @date 2013-5-9
	 * @return void
	 * @version 1.0
	 */

	public void delete(Long id);

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param code
	 * @author cuidd
	 * @date 2013-5-9
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public void update(Long id, String code) throws Exception;

	/**
	 * <p>
	 * Title: query
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param code
	 * @return
	 * @author cuidd
	 * @date 2013-5-18
	 * @return boolean
	 * @version 1.0
	 * @param id
	 * @throws Exception
	 */

	public boolean query(Long id, String code) throws Exception;

	/**
	 * <p>
	 * Title: findIosPackageByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @author cuidd
	 * @date 2013-5-21
	 * @return IosPackageForm
	 * @version 1.0
	 * @throws Exception
	 */
	public PlacementPackageEditVo getIosPackageByPlacement(Long placement_id)
			throws Exception;

	/**
	 * <p>
	 * Title: addIosPackage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @param form
	 * @author cuidd
	 * @date 2013-5-21
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */

	public void addIosPackage(Long placement_id, IosPackageForm form)
			throws Exception;

}
