package cn.adwalker.ad.admin.sales.service;

import java.util.List;

import cn.adwalker.ad.admin.sales.bean.DetailAndroidQueryBean;
import cn.adwalker.ad.admin.sales.bean.DetailIosQueryBean;
import cn.adwalker.ad.admin.sales.bean.SalesReportBean;
import cn.adwalker.ad.admin.sales.vo.SalesReportDetailAndroidVo;
import cn.adwalker.ad.admin.sales.vo.SalesReportDetailIosVo;
import cn.adwalker.ad.admin.sales.vo.SalesReportVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.domain.CampaignSalesman;

public interface ISalesReportService {

	/**
	 * 
	 * <p>
	 * Title: find
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2014-8-12
	 * @return List<SalesReportVo>
	 * @version 1.0
	 * @throws Exception
	 */
	public List<SalesReportVo> findByPage(SalesReportBean bean,
			IPageInfo pageInfor) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: getSalesmanList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adv_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014-8-13
	 * @return List<CampaignSalesman>
	 * @version 1.0
	 */
	public List<CampaignSalesman> getSalesmanList() throws Exception;

	/**
	 * 
	 * <p>
	 * Title: detail_android
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param start_date
	 * @param end_date
	 * @param campaign_id
	 * @return
	 * @author cuidd
	 * @date 2014-8-13
	 * @return SalesReportDetailAndroidVo
	 * @version 1.0
	 * @param pageInfo
	 * @throws Exception
	 */
	public List<SalesReportDetailAndroidVo> detail_android(
			DetailAndroidQueryBean bean, IPageInfo pageInfo) throws Exception;

	public List<SalesReportDetailIosVo> detail_ios(
			DetailIosQueryBean bean, IPageInfo pageInfo) throws Exception;
	
	public Long getSalesmanIdBySysUser(Long id) throws Exception;

}
