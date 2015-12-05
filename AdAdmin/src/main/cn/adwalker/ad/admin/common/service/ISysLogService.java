/**
 * 
 */
package cn.adwalker.ad.admin.common.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.log.domain.SysLog;
import cn.adwalker.ad.admin.common.bean.LogQueryBean;
import cn.adwalker.ad.admin.common.vo.SysManagerLogVo;

/**
 * @author wjp
 *
 */
public interface ISysLogService {

	
	/**
	 * 转换Vo 
	 * @param eScoreManagerLog
	 * @return
	 * @throws Exception
	 */
	public  List<SysManagerLogVo> eScoreManagerLogToVo(List<SysLog> eScoreManagerLog) throws Exception;

	/**
	* <p>Title: findByPage</p>
	* <p>Description:TODO</p>
	* @param bean
	* @param pageInfo
	* @return
	* @author cuidd
	* @date 2013-4-15
	* @return List<EScoreManagerLog>
	* @version 1.0
	* @throws
	*/
	
	public List<SysLog> findByPage(LogQueryBean bean,
			IPageInfo pageInfo);
}
