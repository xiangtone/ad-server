package cn.adwalker.ad.admin.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;

import cn.adwalker.ad.admin.app.bean.AppMediaBean;
import cn.adwalker.ad.admin.app.service.IMediacallbackConfigService;
import cn.adwalker.ad.admin.app.vo.MediacallbackConfigVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.dao.IMediacallbackConfigDao;


@Service("mediacallbackConfigService")
public class MediacallbackConfigImpl implements IMediacallbackConfigService {

	@Resource
	private IApplicationDao appDao;
	@Resource
	private IMediacallbackConfigDao mediacallbackConfigDao;
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<MediacallbackConfigVo> findByPage(AppMediaBean bean,IPageInfo pageInfor) throws Exception {
		List<MediacallbackConfigVo> list = null;
		String columns = "id,os,status,name,detain_rate,scheme_id,ipsegment_times,ip_times,ca_time_ratio";
		StringBuilder sb = new StringBuilder("t_application a LEFT JOIN t_mediacallback_config m ON a.id=m.appid where 1=1");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and os='" + bean.getOs().trim() + "'");
			}
			if (!StringUtils.isEmpty(bean.getName())) {
				sb.append(" and name like '%"
						+ bean.getName().trim() + "%'");
			}
			if (bean.getId() != null) {
				sb.append(" and id=" + bean.getId());
			}	
			if (bean.getStatus() != null) {
				sb.append(" and status=" + bean.getStatus());
			}
			if (bean.getSchemeId() != null) {
				sb.append(" and scheme_id=" + bean.getSchemeId());
			}
		}
		list = (List<MediacallbackConfigVo>) appDao.findByPage(columns, sb.toString(),
				MediacallbackConfigVo.class, pageInfor);
		return list;
	}


	public int saveOrUpdate(AppMediaBean app) throws Exception {
		return mediacallbackConfigDao.saveOrUpdate(app);
	}


	@Override
	public int delMediacallbackConfig(Long id) throws Exception {
		return mediacallbackConfigDao.delMediacallbackConfig(id);
		
	}

}
