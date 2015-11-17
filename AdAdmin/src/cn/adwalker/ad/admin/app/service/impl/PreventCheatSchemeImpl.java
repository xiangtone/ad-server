package cn.adwalker.ad.admin.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.app.bean.PreventBean;
import cn.adwalker.ad.admin.app.service.IPreventCheatSchemeService;
import cn.adwalker.ad.admin.app.vo.PreventCheatSchemeVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.dao.IPreventCheatSchemeDao;
import cn.adwalker.model.app.domain.PreventCheatScheme;


@Service("preventCheatSchemeService")
public class PreventCheatSchemeImpl implements IPreventCheatSchemeService {

	@Resource
	private IApplicationDao appDao;
	@Resource
	private IPreventCheatSchemeDao preventCheatSchemeDao;
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<PreventCheatSchemeVo> findByPage(PreventBean bean,IPageInfo pageInfor) throws Exception {
		List<PreventCheatSchemeVo> list = null;
		String columns = "id,name,is_default,adormedia";
		StringBuilder sb = new StringBuilder("t_prevent_cheat_scheme where 1=1");
		if (bean != null) {			
			if (!StringUtils.isEmpty(bean.getName())) {
				sb.append(" and name like '%"
						+ bean.getName().trim() + "%'");
			}
			if (bean.getIsDefault() != null) {
				sb.append(" and is_default=" + bean.getIsDefault());
			}	
			if (bean.getAdormedia() != null) {
				sb.append(" and adormedia=" + bean.getAdormedia());
			}
		}
		list = (List<PreventCheatSchemeVo>) appDao.findByPage(columns, sb.toString(),PreventCheatSchemeVo.class, pageInfor);
		return list;
	}


	@Override
	public int saveOrUpdate(PreventBean entity,PreventBean entity2) throws Exception {
		if (entity2!=null) {
			preventCheatSchemeDao.saveOrUpdate(entity2);
		}
		return preventCheatSchemeDao.saveOrUpdate(entity);
		
	}


	@Override
	public List<PreventCheatScheme> getPreventCheatSchemeList(Integer type)
			throws Exception {
		return preventCheatSchemeDao.getPreventCheatSchemeList(type);
	}


	@Override
	public PreventCheatScheme getIsDefaultPreventCheatScheme(Integer isDefault,
			Integer adormedia) {
		return preventCheatSchemeDao.getIsDefaultPreventCheatScheme(isDefault, adormedia);
	}


	@Override
	public PreventCheatScheme getPreventCheatSchemeById(Long id) {
		return preventCheatSchemeDao.getPreventCheatSchemeById(id);
	}

	
	
}
