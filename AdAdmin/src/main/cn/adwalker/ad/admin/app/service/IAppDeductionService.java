package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.AppDeductionBean;
import cn.adwalker.ad.admin.operation.vo.AppDeductionVo;
/**
 * 
 * @author zouguibao
 * @version 1.0
 * @since 2013-5-14
 */
public interface IAppDeductionService {
	/**
	 * @param user 
	* <p>Title: findByPage</p>
	* <p>Description:媒体数量调控设置List</p>
	* @param bean
	* @param pageInfor
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-5-14
	* @return List<MediaRatingVo>
	* @version 1.0
	* @throws
	*/
	public List<AppDeductionVo> findByPage(AppDeductionBean bean,
			SysUserVo user, IPageInfo pageInfor) throws Exception;
	/**
	* <p>Title: updateAppDeductionRate</p>
	* <p>Description:修改 媒体数量比例</p>
	* @param adId
	* @param rate
	* @author lichuang
	* @date 2013-7-9
	* @return void
	* @version 1.0
	 */
	public void updateAppDeductionRate(Long adId,Double rate);
}
