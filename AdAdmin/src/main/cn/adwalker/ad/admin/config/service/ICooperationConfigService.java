package cn.adwalker.ad.admin.config.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.config.domain.CooperationConfig;
import cn.adwalker.ad.admin.config.form.CooperationConfigForm;
/**
 * 
 * @author jief
 *
 */
public interface ICooperationConfigService {
	/**
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<CooperationConfig> getList(CooperationConfigForm bean,IPageInfo pageInfo) throws Exception;
	/**
	 * 根据id获取配置
	 * @param app_id
	 * @return
	 */
	public CooperationConfig getConfigByAppId(Long app_id);
	/**
	 * 保存或更新接口配置
	 * @param form
	 * @return
	 */
	public boolean saveOrUpdateConfig(CooperationConfigForm form) throws Exception; 
}
