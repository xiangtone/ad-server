package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.finance.bean.FinanceConsumebean;
import cn.adwalker.ad.admin.finance.vo.AdvConsumeDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceConsumeVo;
/**
 * 
* <p>Title: IAdvConsumeDetailService</p>
* <p>Description:广告主消费明细service接口</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-15
 */
public interface IFinanceAdConsumeDetailService {
	/**
	* <p>Title: getAdvConsumeDetailListForReport</p>
	* <p>Description:条件获取 广告主消费明细  为数据导出准备数据</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-5-14
	* @return AdvConsumeDetailVo
	* @version 1.0
	 */
	public AdvConsumeDetailVo getAdvConsumeDetailListForReport(FinanceConsumebean bean);
	/**
	* <p>Title: findConsumeList</p>
	* <p>Description:财务：收入明细列表</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-8
	* @return List<FinanceConsume>
	* @version 1.0
	 */
	public List<FinanceConsumeVo> findConsumeList(FinanceConsumebean bean,
			IPageInfo pageInfo)throws Exception;
	/**
	* <p>Title: findConsumeReportList</p>
	* <p>Description:导出广告消费明细</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-14
	* @return List<FinanceConsume>
	* @version 1.0
	 */
	public List<FinanceConsumeVo> findConsumeReportList(FinanceConsumebean bean)throws Exception;
}
