package cn.adwalker.ad.admin.sys.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.sys.bean.DataMonitorBean;
import cn.adwalker.ad.admin.sys.vo.DataMonitorVo;

/**
* <p>Title: IDataMonitorService</p>
* <p>Description:定时数据监控</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-8-22
 */
public interface IDataMonitorService {
	
	/**
	* <p>Title: findByPage</p>
	* <p>Description:定时数据监控查询</p>
	* @param bean
	* @param pageInfor
	* @return
	* @throws Exception   
	* @author lichuang
	* @date 2013-8-22
	* @return List<DataMonitorVo>
	* @version 1.0
	 */
	public List<DataMonitorVo> findByPage(DataMonitorBean bean,
			IPageInfo pageInfor) throws Exception;
	/**
	* <p>Title: update</p>
	* <p>Description:发送邮件更改状态</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2013-8-22
	* @return void
	* @version 1.0
	 */
	public void update(Long id)throws Exception ;
}
