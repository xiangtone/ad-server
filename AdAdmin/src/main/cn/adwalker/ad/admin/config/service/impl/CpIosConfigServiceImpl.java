package cn.adwalker.ad.admin.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.config.bean.ICpCampaignBean;
import cn.adwalker.ad.admin.config.form.CpCampaignConfigEditForm;
import cn.adwalker.ad.admin.config.form.CpCampaignConfigForm;
import cn.adwalker.ad.admin.config.form.SendClickInfoForm;
import cn.adwalker.ad.admin.config.service.ICpIosConfigService;
import cn.adwalker.ad.admin.config.util.HttpUtil;
import cn.adwalker.ad.admin.report.service.impl.ReportIOSChannelServiceImpl;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.config.dao.ICpIosConfigDao;
import cn.adwalker.model.config.domain.CampaignConfig;
@Service("cpIosConfigService")
public class CpIosConfigServiceImpl implements ICpIosConfigService{
	private static final String url="http://api.adwalker.com/AdwalkerAPI/common/channelCheckGet.do";
	private static final String reflush_cache_url="http://api.adwalker.cn/AdAPI/cache/deletecache.do?key=campaignConfigne_n_1";
	@Resource
    private ICpIosConfigDao cpIosConfigDao;
	/**
	 * <p>分页查询</p>
	 * @author jief
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignConfig> getList(ICpCampaignBean bean, IPageInfo pageInfo) throws Exception{
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" t_campaign_config where 1=1 ");
		if (ObjectUtils.isNotEmpty(bean.getAd_key())) {
			sb.append(" and ad_key='");
			sb.append(bean.getAd_key());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getAd_name())) {
			sb.append(" and ad_name like '%");
			sb.append(bean.getAd_name());
			sb.append("%'");
		}
		return (List<CampaignConfig>) cpIosConfigDao.findByPage("*",
				sb.toString(), list.toArray(), " order by create_time desc ",
				CampaignConfig.class, pageInfo);
	}
	/**
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly=false,isolation=Isolation.DEFAULT)
	@Override
	public void saveCampaignConfig(CpCampaignConfigForm form) {
		CampaignConfig config=new CampaignConfig();
		config.setId(form.getId());
		config.setAd_key(form.getAd_key());
		config.setAd_name(form.getAd_name());
		config.setUrl(form.getUrl());
		config.setAdid_str(form.getAdid_str()) ;
		config.setDeviceid_para(form.getDeviceid_para());
		config.setSourse_str(form.getSourse_str());
		config.setEventtime_para(form.getEventtime_para());
		config.setSend_type(form.getSend_type());
		config.setSource(form.getSource());
		config.setClient_ip(form.getClient_ip()); 
		config.setOpenudid(form.getOpenudid());  
		config.setIdfa(form.getIdfa());  
		config.setIdfv(form.getIdfv()); 
		config.setService_name(form.getService_name());  
		config.setCreate_time(form.getCreate_time());   
		config.setCallback(form.getCallback());
		config.setAdv_price(form.getAdv_price());
		config.setPlacement_id(form.getPlacement_id());
		config.setPass_rate(form.getPass_rate());
		config.setUdid(form.getUdid());
		this.cpIosConfigDao.saveOrUpdate(config);
		//清空缓存
		ReportIOSChannelServiceImpl.MA_CACHE_ELEMENT.clearElement();
		if(StringUtil.isNotBlank(form.getAd_key())){
			//清空 memecached 缓存
			this.reflushCache(form.getAd_key());
		}
	}
	/**
	* <p>模拟给广告主发送点击</p>
	* @author jiefs
	*/
	@Override
	public String sendClickInfo(SendClickInfoForm form) {
		StringBuffer sburl=new StringBuffer(url);
		sburl.append("?deviceid=").append(form.getMac())
		  	 .append("&appid=").append(form.getId())
		  	 .append("&source=").append(form.getChannel())
		  	 .append("&IDFA=").append(form.getIdfa())
		  	 .append("&OPENUDID=").append(form.getOpenudid())
		  	 .append("&IDFV=").append(form.getIdfv())
		  	 .append("&client_ip=").append(form.getClient_ip());
		 String result=HttpUtil.sendGet(sburl.toString());
		return result;
	}
	/**
	 * <p>根据id获取广告配置对象</p>
	 */
	@Override
	public CampaignConfig getConfigById(Long id) {
		return this.cpIosConfigDao.getConfigById(id);
	}
	
	@Override
	public String reflushCache(String adid) {
		StringBuffer sburl=new StringBuffer(reflush_cache_url);
		if(StringUtil.isNotBlank(adid)){
			sburl.append(adid);
			String result=HttpUtil.sendGet(sburl.toString());
			return result;
		}
		return "error!adid is empty!";
	}
	@Override
	public void updateCampaignConfig(CpCampaignConfigEditForm form) throws Exception {
		if (form!=null&&form.getId()!=null) {
			String sql="update T_CAMPAIGN_CONFIG set ad_key=?,ad_name=?,url=?,adid_str=?,deviceid_para=?,sourse_str=?,eventtime_para=?,send_type=?," +
					" source=?,client_ip=?,openudid=?,idfa=?,idfv=?,service_name=?,callback=?,pass_rate=?,udid=?,debug_mode=?  "
					+ "where id=?";
			cpIosConfigDao.update(sql, new Object[]{
					form.getAd_key(),
					form.getAd_name(),
					form.getUrl(),
					form.getAdid_str(),
					form.getDeviceid_para(),
					form.getSourse_str(),
					form.getEventtime_para(),
					form.getSend_type(),
					form.getSource(),
					form.getClient_ip(),
					form.getOpenudid(),
					form.getIdfa(),
					form.getIdfv(),
					form.getService_name(),
					form.getCallback(),
					form.getPass_rate(),
					form.getUdid(),
					form.getDebug_mode(),
					form.getId()});
			//清空缓存
			ReportIOSChannelServiceImpl.MA_CACHE_ELEMENT.clearElement();
			if(StringUtil.isNotBlank(form.getAd_key())){
				//清空 memecached 缓存
				this.reflushCache(form.getAd_key());
			}
		}
		
	}
}
