package cn.adwalker.ad.admin.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.sys.dao.IDictionaryDao;
import cn.adwalker.ad.admin.sys.bean.SysDictionaryBean;
import cn.adwalker.ad.admin.sys.service.ISysDictionarySettingService;
import cn.adwalker.ad.admin.sys.vo.SysDictionaryVo;

@Service("sysDictionarySettingService")
public class SysDictionarySettingServiceImpl implements
		ISysDictionarySettingService {

	@Resource
	private IDictionaryDao dictionaryDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<SysDictionaryVo> findByPage(SysDictionaryBean bean,
			IPageInfo pageInfor) throws Exception {
		List<SysDictionaryVo> list = null;
		String columns = "a.ID id,a.FIELD_NAME columnName,a.FIELD_TYPE columnType,a.FROM_TABLE tableName,a.FROM_VIEW viewName";
		StringBuffer sb = new StringBuffer();
		sb.append("T_FIELD_INFO a where 1=1");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getColumnName())) {
				sb.append(" and a.FIELD_NAME like '%"
						+ bean.getColumnName().trim() + "%'");
			}

			if (!StringUtils.isEmpty(bean.getColumnType())) {
				sb.append(" and a.FIELD_TYPE like '%"
						+ bean.getColumnType().trim() + "%'");
			}

			if (!StringUtils.isEmpty(bean.getTableName())) {
				sb.append(" and a.FROM_TABLE like '%"
						+ bean.getTableName().trim() + "%'");
			}

			if (!StringUtils.isEmpty(bean.getViewName())) {
				sb.append(" and a.FROM_VIEW like '%"
						+ bean.getViewName().trim() + "%'");
			}
		}
		list = (List<SysDictionaryVo>) dictionaryDao.findByPage(columns,
				sb.toString(), SysDictionaryVo.class, pageInfor);
		return list;
	}

	@Override
	public SysDictionaryVo findById(Long id) {
		return dictionaryDao.findByDictionaryId(id);
	}

	public void updateDictionary(SysDictionaryBean form) {
		dictionaryDao.updateDictionary(form);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: addDictionary
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @see cn.adwalker.ad.admin.sys.service.ISysDictionarySettingService#addDictionary(cn.adwalker.ad.admin.sys.bean.SysDictionaryBean)
	 */
	@Override
	public void addDictionary(SysDictionaryBean form) {
		dictionaryDao.addDictionary(form);
	}

}
