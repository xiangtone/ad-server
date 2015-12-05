package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.app.bean.DevTaxSettting;
import cn.adwalker.ad.admin.operation.vo.DevTaxSettingVo;

/**
 * <p>
 * Title: IDevTaxSettingService
 * </p>
 * <p>
 * Description:开发者免税审核service接口
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-9
 */
public interface IDevTaxSettingService {
	/**
	 * <p>
	 * Title: findBypage
	 * </p>
	 * <p>
	 * Description:开发者免税审核List
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-9
	 * @return List<DevTaxSettingVo>
	 * @version 1.0
	 */
	public List<DevTaxSettingVo> findBypage(DevTaxSettting bean,
			IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: updateDevTaxSetting
	 * </p>
	 * <p>
	 * Description:开发者免税审核
	 * </p>
	 * 
	 * @param dev_id
	 * @param tax_status
	 * @author lichuang
	 * @date 2013-7-9
	 * @return void
	 * @version 1.0
	 */
	public void updateDevTaxSetting(Long dev_id, Integer tax_status);
}
