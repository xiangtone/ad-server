package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.ad.admin.app.bean.DevTaxSettting;
import cn.adwalker.ad.admin.app.service.IDevTaxSettingService;
import cn.adwalker.ad.admin.operation.vo.DevTaxSettingVo;

@Service(value = "devTaxSettingService")
public class DevTaxSettingServiceImpl implements IDevTaxSettingService {

	@Resource
	private IDeveloperDao developerDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<DevTaxSettingVo> findBypage(DevTaxSettting bean,
			IPageInfo pageInfo) throws Exception {

		StringBuilder sb = new StringBuilder("where 1=1");
		ArrayList<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (bean.getDev_id() != null) {
				sb.append(" and id=?");
				param.add(bean.getDev_id());
			}
			if (!StringUtils.isEmpty(bean.getDev_name())) {
				sb.append(" and upper(real_name) like ?");
				param.add("%" + bean.getDev_name().trim().toUpperCase() + "%");
			}
			if (!StringUtils.isEmpty(bean.getDev_email())) {
				sb.append(" and upper(email) like ?");
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");
			}
			if (bean.getTax_status() != null) {
				sb.append(" and tax_status=?");
				param.add(bean.getTax_status());
			}

		}

		return (List<DevTaxSettingVo>) developerDao
				.findByPage(
						"id dev_id,email dev_email,real_name dev_name,tax_status tax_status",
						"T_DEVELOPER " + sb.toString(), param.toArray(),
						"order by CREATE_TIME desc", DevTaxSettingVo.class,
						pageInfo);

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateDevTaxSetting
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param dev_id
	 * @param tax_status
	 * @see cn.adwalker.ad.admin.app.service.IDevTaxSettingService#updateDevTaxSetting(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	public void updateDevTaxSetting(Long dev_id, Integer tax_status) {
		developerDao.updateDevTaxSetting(dev_id, tax_status);
	}
}
