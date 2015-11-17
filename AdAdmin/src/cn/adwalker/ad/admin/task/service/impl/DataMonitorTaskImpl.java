package cn.adwalker.ad.admin.task.service.impl;import java.io.IOException;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;import java.util.concurrent.ThreadPoolExecutor;import javax.annotation.Resource;import org.apache.commons.lang.StringUtils;import org.apache.log4j.Logger;import org.springframework.context.ApplicationContext;import org.springframework.stereotype.Service;import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;import cn.adwalker.core.pool.ThreadPoolUtil;import cn.adwalker.core.repository.impl.BaseServiceImpl;import cn.adwalker.core.spring.AppContext;import cn.adwalker.core.util.ConfigUtil;import cn.adwalker.model.news.domain.Mail;import cn.adwalker.model.sys.dao.IDataMonitorDao;import cn.adwalker.model.sys.domain.DateMonitor;import cn.adwalker.ad.admin.task.service.IDataMonitorTaskService;import cn.adwalker.ad.task.SendMailTask;import freemarker.template.Configuration;import freemarker.template.Template;import freemarker.template.TemplateException;/** * <p> * Title: DataMonitorTaskImpl * </p> * <p> * Description:TODO * </p> * <p> * Company: emar * </p> *  * @author lichuang * @date 2013-8-22 */@Servicepublic class DataMonitorTaskImpl extends BaseServiceImpl implements		IDataMonitorTaskService {	@SuppressWarnings("unused")	private static Logger logger = Logger.getLogger(DataMonitorTaskImpl.class);	@Resource	private IDataMonitorDao dataMonitorDao;	/**	 * (non-Javadoc)	 * <p>	 * Title: doTask	 * </p>	 * <p>	 * Description:TODO	 * </p>	 * 	 * @param date	 * @throws Exception	 * @see cn.adwalker.ad.admin.task.service.emar.admin.task.service.IDataMonitorTaskService#doTask(java.util.Date)	 */	@Override	public void doTask() throws Exception {		List<DateMonitor> list = this.findDate();		String email = ConfigUtil.getString("send_mail_for_timing");		if (list != null && list.size() > 0 && !StringUtils.isEmpty(email)) {			List<Mail> mList = new ArrayList<Mail>();			ApplicationContext ctx = AppContext.getApplicationContext();			Configuration freemarkerConfig = (Configuration) ctx					.getBean("freemarkerConfigurer");			Template temp = freemarkerConfig					.getTemplate("/template/sys/data_monitor.ftl");// 修改bug，之前模版文件引用错误			for (DateMonitor entity : list) {				Mail vo = new Mail();				vo.setTitle("【数据告警" + entity.getStatic_date() + "】");				vo.setContent(getMailContent(temp, entity));				mList.add(vo);			}			String arr[] = email.split(",");			ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();			for (Mail mail : mList) {				for (int j = 0; j < arr.length; j++) {					threadPool.execute(new SendMailTask(mail, arr[j]));				}			}		}		List<Object[]> idList = new ArrayList<Object[]>();		for (DateMonitor entity : list) {			idList.add(new Object[] { 1, entity.getId() });		}		dataMonitorDao.batchUpdate(				"update T_LOG_MONITOR set SEND_STATUS=? where id=?", idList);	}	private String getMailContent(Template temp, DateMonitor entity)			throws IOException, TemplateException {		String s = null;		Map<String, Object> map = new HashMap<String, Object>();		map.put("vo", entity);		s = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);		return s;	}	/**	 * <p>	 * Title: findDate	 * </p>	 * <p>	 * Description:TODO	 * </p>	 * 	 * @param bean	 * @return	 * @throws Exception	 * @author lichuang	 * @date 2013-8-22	 * @return List<DataMonitorVo>	 * @version 1.0	 */	@SuppressWarnings("unchecked")	private List<DateMonitor> findDate() throws Exception {		List<Object> list = new ArrayList<Object>();		StringBuffer sb = new StringBuffer(				"T_LOG_MONITOR where 1=1 and STATUS =1 and SEND_STATUS IS NULL ");		return (List<DateMonitor>) dataMonitorDao.findAll("*", sb.toString(),				list.toArray(), DateMonitor.class);	}}