package cn.adwalker.ad.admin.config.service;

import java.util.List;

import cn.adwalker.ad.admin.config.bean.ICpCampaignBean;
import cn.adwalker.ad.admin.config.form.CpCampaignConfigEditForm;
import cn.adwalker.ad.admin.config.form.CpCampaignConfigForm;
import cn.adwalker.ad.admin.config.form.SendClickInfoForm;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.config.domain.CampaignConfig;
/**
 * <p>广告主接口配置服务端</p>
 * @author jief
 *
 */
public interface ICpIosConfigService {
/**
 * <p>广告主配置</p>
 * @param bean
 * @param pageInfo
 * @return
 */
public List<CampaignConfig> getList(ICpCampaignBean bean,IPageInfo pageInfo) throws Exception;
/**
 * <p>添加广告主接口配置</p>
 * @param form
 */
public void saveCampaignConfig(CpCampaignConfigForm form);

/**
 * <p>添加广告主接口配置</p>
 * @param form
 * @throws Exception 
 */
public void updateCampaignConfig(CpCampaignConfigEditForm form) throws Exception;

/**
 * <p>发送点击信息</p>
 * @param form
 * @return
 */
public String sendClickInfo(SendClickInfoForm form); 
/**
 * <p>根据id获取广告主配置</p>
 * @param id
 * @return
 */
public CampaignConfig getConfigById(Long id);
/**
 * <p>刷新广告配置缓存</p>
 * @param adid
 * @return
 */
public String reflushCache(String adid);

}
