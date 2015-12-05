package cn.adwalker.ad.admin.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.app.bean.AdSendBean;
import cn.adwalker.ad.admin.app.service.IAdSendConfigService;
import cn.adwalker.ad.admin.app.vo.AdSendConfigVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IAdSendConfigDao;
import cn.adwalker.model.app.dao.IApplicationDao;


@Service("adSendConfigService")
public class AdSendConfigImpl implements IAdSendConfigService {

	@Resource
	private IApplicationDao appDao;
	@Resource
	private IAdSendConfigDao adSendConfigDao;
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<AdSendConfigVo> findByPage(AdSendBean bean,IPageInfo pageInfor) throws Exception {
		List<AdSendConfigVo> list = null;
		String columns = "a.id,a.status,r.status AS pc_status,p.os,c.campaign_name,con.scheme_id,con.ad_ipsegment_num,con.ad_ip_num,con.ad_bssid_num,con.ad_latlon_num";
		StringBuilder sb = new StringBuilder(" t_ad a LEFT JOIN t_campaign_placement_rel r ON  a.placement_id=r.placement_id LEFT JOIN t_campaign c ON r.campaign_id=c.id LEFT JOIN t_placement p ON r.placement_id=p.id LEFT JOIN t_ad_send_config con ON a.id=con.ad_id  where 1=1");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and p.os='" + bean.getOs().trim() + "'");
			}
			if (!StringUtils.isEmpty(bean.getCampaignName())) {
				sb.append(" and c.campaign_name like '%"
						+ bean.getCampaignName().trim() + "%'");
			}
			if (bean.getId() != null) {
				sb.append(" and a.id=" + bean.getId());
			}	
			
			if (bean.getStatus() != null) {
				sb.append(" and a.status=" + bean.getStatus());
			}	
			
			if (bean.getPcStatus() != null) {
				sb.append(" and r.status=" + bean.getPcStatus());
			}
			if (bean.getSchemeId() != null) {
				sb.append(" and con.scheme_id=" + bean.getSchemeId());
			}
		}
		list = (List<AdSendConfigVo>) appDao.findByPage(columns, sb.toString(),
				AdSendConfigVo.class, pageInfor);
		return list;
	}


	public int saveOrUpdate(AdSendBean app) throws Exception {
		return adSendConfigDao.saveOrUpdate(app);
	}


	public int delAdSendConfig(Long id) throws Exception {		
		return adSendConfigDao.delAdSendConfig(id);
	}

}
