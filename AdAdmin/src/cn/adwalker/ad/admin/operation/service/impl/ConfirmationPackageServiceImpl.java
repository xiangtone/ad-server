package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.dao.IPlacementPackageDao;
import cn.adwalker.ad.admin.operation.bean.ConfirmPackagebean;
import cn.adwalker.ad.admin.operation.service.IConfirmationPackageService;
import cn.adwalker.ad.admin.operation.vo.ConfirmationPackageVo;

/**
 * <p>
 * Title: ConfirmationPackageServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-16
 */
@Service("confirmationPackageService")
public class ConfirmationPackageServiceImpl implements
		IConfirmationPackageService {

	@Resource
	private IPlacementPackageDao confirmationPackageDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findList(cn.adwalker.admin.operation.bean.AdPriceBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ConfirmationPackageVo> findList(ConfirmPackagebean bean,
			IPageInfo pageInfo) throws Exception {

		List<ConfirmationPackageVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" V_PLACEMENT_PACKAGE t where 1=1 and os='android'");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and t.ID ='");
				sb.append(bean.getPackage_id().trim());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}

		}

		resultList = (List<ConfirmationPackageVo>) confirmationPackageDao
				.findByPage("t.*", sb.toString(), list.toArray(),
						" order by t.create_time desc ",
						ConfirmationPackageVo.class, pageInfo);
		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: packageremarks
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param packageremarks
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationPackageService#packageremarks(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void packageremarks(Long id, String packageremarks) throws Exception {
		confirmationPackageDao.packageremarks(id, packageremarks);
	}
}
