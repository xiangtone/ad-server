/** * <p>Title: WallService.java</p> * <p>Description: </p> * <p>Copyright: Copyright (c) </p> * <p>Company: adwalker</p> * @author jiangwei * @date Aug 20, 2012 * @version 1.0 */package cn.adwalker.ad.admin.task.service;import java.util.Date;/** *  * <p> * Title: IApiFinanceTaskService * </p> * <p> * Description:TODO * </p> * <p> * Company: adwalker * </p> *  * @author cuidd * @date 2013-7-22 */public interface IApiFinanceTaskService {	/**	 * 	 * <p>	 * Title: doTask	 * </p>	 * <p>	 * Description:定时任务执行主方法	 * </p>	 * 	 * @param date	 * @author cuidd	 * @date 2013-9-2	 * @return void	 * @version 1.0	 * @throws Exception	 */	public void doTask(Date date) throws Exception;}