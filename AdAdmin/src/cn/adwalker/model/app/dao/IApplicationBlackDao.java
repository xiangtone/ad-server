package cn.adwalker.model.app.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.ApplicationBlack;

/**
 * <p>
 * Title: IApiLogDao
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
public interface IApplicationBlackDao extends IBaseDao<ApplicationBlack> {
	
	/**
	 * <p>
	 * Title: appBlackAdInsert
	 * </p>
	 * <p>
	 * Description:添加黑名单
	 * </p>
	 * 
	 * @param paramsList
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-16
	 * @return void
	 * @version 1.0
	 */
	public void createApplicationBlack(List<Object[]> paramsList) throws Exception;

	/**
	 * <p>
	 * Title: deleteApplicationBlackByApplicationid
	 * </p>
	 * <p>
	 * Description:删除黑名单
	 * </p>
	 * 
	 * @param applicationId
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-23
	 * @return void
	 * @version 1.0
	 */
	public Integer deleteApplicationBlackByApplicationid(Long applicationId) throws Exception;
}
