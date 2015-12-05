package cn.adwalker.ad.admin.mail.service;

import java.util.List;

import cn.adwalker.ad.admin.common.vo.SysUserVo;

/**
 * 
 * <p>
 * Title: ICampaignService
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
public interface ICampaignMailService {

	/**
	 * <p>
	 * Title: sendMailAudit
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2013-5-29
	 * @return void
	 * @version 1.0
	 * @param manageUser 
	 * @throws Exception
	 */

	public void sendMailAudit(Long id, SysUserVo manageUser) throws Exception;

	/**
	 * <p>
	 * Title: sendMailAdBudget
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @author cuidd
	 * @date 2013-9-2
	 * @return void
	 * @version 1.0
	 * @throws Exception 
	 */
	public void sendMailAdBudget(List<Long> ids) throws Exception;
}
