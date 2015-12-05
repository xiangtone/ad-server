package cn.adwalker.ad.admin.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.sys.dao.IDataMonitorDao;
import cn.adwalker.model.sys.domain.DateMonitor;
import cn.adwalker.ad.admin.sys.bean.DataMonitorBean;
import cn.adwalker.ad.admin.sys.service.IDataMonitorService;
import cn.adwalker.ad.admin.sys.vo.DataMonitorVo;

/**
 * <p>
 * Title: DataMonitorServiceImpl
 * </p>
 * <p>
 * Description:定时数据监控
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-8-22
 */
@Service
@Transactional
public class DataMonitorServiceImpl implements IDataMonitorService {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(DataMonitorServiceImpl.class);
	/** 資源管理 */
	@Resource
	private IDataMonitorDao dataMonitorDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.IDataMonitorService#findByPage(cn.adwalker.ad.admin.sys.bean.DataMonitorBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataMonitorVo> findByPage(DataMonitorBean bean,
			IPageInfo pageInfor) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("T_LOG_MONITOR where 1=1 ");
		if (ObjectUtils.isNotEmpty((bean.getStatus()))) {
			sb.append(" and STATUS= ");
			sb.append(bean.getStatus());
		}
		if (ObjectUtils.isNotEmpty((bean.getTask()))) {
			sb.append(" and TASK_TYPE= ");
			sb.append(bean.getTask());
		}
		return (List<DataMonitorVo>) dataMonitorDao.findByPage("*",
				sb.toString(), list.toArray(), " order by create_time desc ",
				DataMonitorVo.class, pageInfor);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.sys.service.IDataMonitorService#update(java.lang.Long)
	 */
	@Override
	public void update(Long id) throws Exception {
		if (id != null) {
			DateMonitor timingDate = new DateMonitor();
			dataMonitorDao.update(timingDate);

		}
	}

}
