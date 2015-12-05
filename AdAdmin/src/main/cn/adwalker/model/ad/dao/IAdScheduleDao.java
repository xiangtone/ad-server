package cn.adwalker.model.ad.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.AdSchedule;

/**
 * 
 * <p>
 * Title: IAdDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
public interface IAdScheduleDao extends IBaseDao<AdSchedule> {

	/**
	 * 
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * <p>
	 * Description:保存或更新
	 * </p>
	 * 
	 * @param adSchedule
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-6-13
	 * @return void
	 * @version 1.0
	 */
	public void saveOrUpdate(AdSchedule adSchedule) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: getAdScheduleStart
	 * </p>
	 * <p>
	 * Description:根据广告id获取定时开始
	 * </p>
	 * 
	 * @param ad_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-11
	 * @return AdSchedule
	 * @version 1.0
	 */
	public AdSchedule getAdScheduleStart(Long ad_id) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: getAdScheduleEnd
	 * </p>
	 * <p>
	 * Description:根据广告id获取定时结束
	 * </p>
	 * 
	 * @param ad_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-11
	 * @return AdSchedule
	 * @version 1.0
	 */
	public AdSchedule getAdScheduleEnd(Long ad_id) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adSchedule
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-11
	 * @return void
	 * @version 1.0
	 */
	public void updateStatus(Long ad_id, Integer type, Integer formStatus,
			Integer toStatus) throws Exception;

}
