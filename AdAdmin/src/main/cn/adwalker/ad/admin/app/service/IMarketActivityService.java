package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.domain.MarketActivity;
import cn.adwalker.ad.admin.app.bean.MarketAvtivityBean;
import cn.adwalker.ad.admin.app.form.MarketAvtivityForm;
/**
* <p>Title: IMarketActivityService</p>
* <p>Description:市场活动接口</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-18
 */
public interface IMarketActivityService {
	/**
	* <p>Title: findByPage</p>
	* <p>Description:市场活动List</p>
	* @param bean
	* @param pageInfor
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-18
	* @return List<MarketActivity>
	* @version 1.0
	 */
	public List<MarketActivity> findByPage(MarketAvtivityBean bean,IPageInfo pageInfor) throws Exception;
	/**
	* <p>Title: findById</p>
	* <p>Description:通过ID查找某一个市场活动的详细信息</p>
	* @param id
	* @return
	* @author lichuang
	* @date 2013-6-18
	* @return MarketActivity
	* @version 1.0
	 */
	public MarketActivity findById(Long id);
	/**
	* <p>Title: updatadwalkerketActivity</p>
	* <p>Description:修改市场活动</p>
	* @param form
	* @author lichuang
	* @date 2013-6-18
	* @return void
	* @version 1.0
	 */
	public void updatadwalkerketActivity(MarketAvtivityForm form);
	/**
	* <p>Title: addMarketActivity</p>
	* <p>Description:添加市场活动</p>
	* @param form
	* @author lichuang
	* @date 2013-6-18
	* @return void
	* @version 1.0
	 */
	public void addMarketActivity(MarketAvtivityForm form);
}
