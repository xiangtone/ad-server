package cn.adwalker.ad.admin.config.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.config.form.CooperationConfigForm;
import cn.adwalker.ad.admin.config.service.ICooperationConfigService;
import cn.adwalker.ad.admin.config.util.HttpUtil;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.config.dao.ICooperationConfigDao;
import cn.adwalker.model.config.domain.CooperationConfig;
@Service("cooperationConfigService")
public class CooperationConfigServiceImpl implements ICooperationConfigService{
	static final String cooper_reflush_cache_url="http://i.adwalker.cn/AdService/cache/updateAppCache.do?appId=";
	@Resource
	ICooperationConfigDao cooperationConfigDao;
	/**
	 * 分页
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CooperationConfig> getList(CooperationConfigForm bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" t_cooperation_app_rel where 1=1 ");
		if (ObjectUtils.isNotEmpty(bean.getApp_id())) {
			sb.append(" and app_id='");
			sb.append(bean.getApp_id());
			sb.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getResponse_url())) {
			sb.append(" and response_url like '%");
			sb.append(bean.getResponse_url());
			sb.append("%'");
		}
		return (List<CooperationConfig>) cooperationConfigDao.findByPage("*",
				sb.toString(), list.toArray(), " order by create_time desc ",
				CooperationConfig.class, pageInfo);
	}

	@Override
	public CooperationConfig getConfigByAppId(Long app_id) {
		return this.cooperationConfigDao.getConfigByAppId(app_id);
	}
/**
 * 保存
 */
	@Transactional(propagation = Propagation.REQUIRED,readOnly=false,isolation=Isolation.DEFAULT)
	@Override
	public boolean saveOrUpdateConfig(CooperationConfigForm form) throws Exception {
		if(form.getApp_id()!=null){
			CooperationConfig cf=this.cooperationConfigDao.getConfigByAppId(form.getApp_id());
			if(cf==null){
				CooperationConfig config=new CooperationConfig();
				config.setApp_id(form.getApp_id());
				config.setResponse_url(form.getResponse_url());
				config.setCreate_time(DateUtil.formatDate(new Date()));
				this.cooperationConfigDao.saveConfig(config);
			}else{
				cf.setResponse_url(form.getResponse_url());
				this.cooperationConfigDao.updateConfig(cf);
			}
			//add by jief 2014-07-01 reflush add score config cache
			StringBuffer sburl=new StringBuffer(cooper_reflush_cache_url);
			sburl.append(form.getApp_id());
			String result=HttpUtil.sendGet(sburl.toString());
			System.out.println("reflush cooperConfig cache result="+result);
			return true;
		}else{
			return false;
		}
		
	}
}
