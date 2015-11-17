package cn.adwalker.ad.admin.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.app.bean.PreventInfoBean;
import cn.adwalker.ad.admin.app.service.IPreventCheatSchemeInfoService;
import cn.adwalker.ad.admin.app.vo.PreventCheatSchemeInfoVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.dao.IPreventCheatSchemeInfoDao;


@Service("preventCheatSchemeInfoService")
public class PreventCheatSchemeInfoImpl implements IPreventCheatSchemeInfoService {

	@Resource
	private IApplicationDao appDao;
	@Resource
	private IPreventCheatSchemeInfoDao preventCheatSchemeInfoDao;
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<PreventCheatSchemeInfoVo> findByPage(PreventInfoBean bean,IPageInfo pageInfor) throws Exception {
		List<PreventCheatSchemeInfoVo> list = null;
		String columns = "id,scheme_id,area,config";
		StringBuilder sb = new StringBuilder("t_prevent_cheat_scheme_info where 1=1");
		if (bean != null) {			
			if (!StringUtils.isEmpty(bean.getArea())) {
				sb.append(" and area like '%"
						+ bean.getArea().trim() + "%'");
			}
			if (bean.getSchemeId() != null) {
				sb.append(" and scheme_id=" + bean.getSchemeId());
			}
		}
		list = (List<PreventCheatSchemeInfoVo>) appDao.findByPage(columns, sb.toString(),PreventCheatSchemeInfoVo.class, pageInfor);
		return list;
	}


	@Override
	public int saveOrUpdate(PreventInfoBean entity) throws Exception {
		return preventCheatSchemeInfoDao.saveOrUpdate(entity);
		
	}

}
