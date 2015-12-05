package cn.adwalker.ad.admin.report.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.admin.report.bean.AdsageActiviteLog;
import cn.adwalker.ad.admin.report.bean.IOSChannelBean;
import cn.adwalker.ad.admin.report.service.IReportIOSChannelService;
import cn.adwalker.ad.admin.report.util.ResultSetItor;
import cn.adwalker.core.bean.CacheElement;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.report.dao.IReportIOSChannelDao;

/**
 * 
 * <p>
 * Title: ReportIOSChannelServiceImpl
 * </p>
 * <p>
 * Description:ios数据统计
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd重构
 * @date 2013-9-2
 */
@Service("reportIOSChannelService")
public class ReportIOSChannelServiceImpl implements IReportIOSChannelService {
	@Resource
	private IReportIOSChannelDao reDao;

	/**
	 * 使用缓存减少数据库访问次数
	 */
	public static final CacheElement<List<Map<String, Object>>> MA_CACHE_ELEMENT = new CacheElement<List<Map<String, Object>>>(
			10);

	private static final CacheElement<List<Map<String, Object>>> AD_CACHE = new CacheElement<List<Map<String, Object>>>(
			10);

	@SuppressWarnings("unchecked")
	@Override
	public List<AdsageActiviteLog> getadIOSLog(IOSChannelBean bean,
			IPageInfo pageInfo) throws Exception {
		StringBuilder sql = new StringBuilder();
		String tableName = "";
		if ("0".equals(bean.getDataType())) {
			tableName = "T_IOS_ACTION_LOG";
		} else {
			tableName = "t_ios_activite_log";
		}
		sql.append(tableName + " where 1=1");
		if (ObjectUtils.isNotEmpty(bean.getAd_id())) {
			sql.append(" and AD_ID='" + bean.getAd_id() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getChannel())) {
			sql.append(" and CHANNEL='" + bean.getChannel() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getAd_name())) {
			sql.append(" and AD_NAME='" + bean.getAd_name() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getPageType())) {
			sql.append(" and PAGE_TYPE=" + bean.getPageType() + "");
		}
		if (ObjectUtils.isNotEmpty(bean.getOpenUdid())) {
			sql.append(" and OPENUDID='" + bean.getOpenUdid() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getIdfa())) {
			sql.append(" and IDFA='" + bean.getIdfa() + "'");
		}
		if ("1".equals(bean.getDataType())) {
			if (ObjectUtils.isNotEmpty(bean.getSend())) {
				sql.append(" and send=" + bean.getSend() + "");
			}
		}

		if (ObjectUtils.isNotEmpty(bean.getMac())) {
			String[] stringMAC = bean.getMac().split(":");
			String macNew = "";
			for (int i = 0; i < stringMAC.length; i++) {
				macNew += stringMAC[i];
			}
			sql.append(" and MAC='" + macNew + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getSearchStatus())) {
			if (!("-1".equals(bean.getSearchStatus()))) {
				if ("1".equals(bean.getSearchStatus())) {
					sql.append(" and ACTIVITE_STATUS =1");
				} else {
					sql.append(" and STATUS=" + bean.getSearchStatus());
					sql.append(" and ACTIVITE_STATUS=0 ");
				}
			}
		}
		// ad by jief 根据激活时间查询 2013-11-15 start
		if ("0".equals(bean.getTimeType())) {
			// ad by jief 根据激活时间查询 2013-11-15 end
			if (ObjectUtils.isNotEmpty(bean.getStatDate_start())
					&& ObjectUtils.isNotEmpty(bean.getStatDate_end())) {
				sql.append(" and  CREATE_TIME>='" + bean.getStatDate_start()
						+ "' and  CREATE_TIME<='" + bean.getStatDate_end()
						+ "'");
			}
		} else {
			bean.setActiveDate_start(bean.getStatDate_start());
			bean.setActiveDate_end(bean.getStatDate_end());
			if (ObjectUtils.isNotEmpty(bean.getActiveDate_start())
					&& ObjectUtils.isNotEmpty(bean.getActiveDate_end())) {
				Date as = DateUtil.parseDate(bean.getActiveDate_start(),
						DateUtil.PARTTERN_DATE_TIME);
				Date ae = DateUtil.parseDate(bean.getActiveDate_end(),
						DateUtil.PARTTERN_DATE_TIME);
				sql.append(" and  ACTIVITE_DATE  >=" + (as.getTime() / 1000)
						+ " and ACTIVITE_DATE <=" + (ae.getTime() / 1000) + "");
			}
		}
		// 根据不同查询类型查询不同表
		List<AdsageActiviteLog> list = null;
		if ("1".equals(bean.getDataType())) {
			list = (List<AdsageActiviteLog>) reDao
					.findByPage(
							"id,ad_id,mac,openudid,idfa,idfv,activite_date,channel,status,create_time,activite_status,page_type,application_key,send ",
							sql.toString(), " order by CREATE_TIME desc ",
							AdsageActiviteLog.class, pageInfo);
		} else {
			list = (List<AdsageActiviteLog>) reDao
					.findByPage(
							"id,ad_id,mac,openudid,idfa,idfv,activite_date,channel,status,create_time,activite_status,page_type,application_key",
							sql.toString(), " order by CREATE_TIME desc ",
							AdsageActiviteLog.class, pageInfo);
		}
		List<AdsageActiviteLog> rdadsrNew = null;
		if (list != null && list.size() > 0) {
			rdadsrNew = new ArrayList<AdsageActiviteLog>();
			List<Map<String, Object>> channelList = this.getChannelList();
			List<Map<String, Object>> adList = this.getAdList();
			for (int i = 0; i < list.size(); i++) {// 00:26:08:AD:5F:23
				AdsageActiviteLog log = list.get(i);
				String macOne = log.getMac();

				if (!StringUtils.isEmpty(macOne) && macOne.length() == 12) {
					String macNew = "";
					String[] strs = new String[macOne.length() - 1];
					for (int k = 0; k < macOne.length() - 1; k += 2) {
						strs[k] = macOne.substring(k, k + 2);
						macNew += strs[k] + ":";
					}
					macNew = macNew.substring(0, macNew.length() - 1);
					log.setMac(macNew);
				} else {
					log.setMac("");
				}

				for (Map<String, Object> chanMap : channelList) {
					String channelID = String.valueOf(chanMap.get("CHANNEL"));
					String channelName = String.valueOf(chanMap
							.get("CHANNEL_NAME"));
					if (log.getChannel().equals(channelID)) {
						log.setChannel(channelName);
					}
				}

				for (Map<String, Object> adMap : adList) {
					String adID = String.valueOf(adMap.get("AD_KEY"));
					String adName = String.valueOf(adMap.get("AD_NAME"));
					if (log.getAd_id() != null)
						if (log.getAd_id().equals(adID)) {
							log.setAd_name(adName);
						}
				}
				rdadsrNew.add(log);
			}
		}
		return rdadsrNew;
	}

	/*****
	 * 汇总信息
	 */
	public int getSumActivete(IOSChannelBean iocVo) {
		return this.reDao.getSumActivete(iocVo);
	}

	@Override
	public List<Map<String, Object>> getChannelList() {
		List<Map<String, Object>> map = null;
		if (MA_CACHE_ELEMENT != null && MA_CACHE_ELEMENT.getElement() != null) {
			map = MA_CACHE_ELEMENT.getElement();
		} else {
			map = reDao.getChannelList();
			MA_CACHE_ELEMENT.setElement(map);
		}

		return map;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.ad.admin.report.service.manager.report.service.IReportIOSChannelService#getAdList()
	 */
	@Override
	public List<Map<String, Object>> getAdList() {
		if (AD_CACHE.getElement() == null) {
			List<Map<String, Object>> list = reDao.getAdList();
			AD_CACHE.setElement(list);
		}
		return AD_CACHE.getElement();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdsageActiviteLog> getIOSExcel(IOSChannelBean bean)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		String tableName = "";
		if ("0".equals(bean.getDataType())) {
			tableName = "T_IOS_ACTION_LOG";
		} else {
			tableName = "t_ios_activite_log";
		}
		// add by jief openudid,idfa,idfv
		sql.append("select id,ad_id,mac,activite_date,channel,status,create_time,activite_status,page_type,application_key,openudid,idfa from "
				+ tableName + " where 1=1");
		if (ObjectUtils.isNotEmpty(bean.getAd_id())) {
			sql.append(" and AD_ID='" + bean.getAd_id() + "'");
		}

		if (ObjectUtils.isNotEmpty(bean.getChannel())) {
			sql.append(" and CHANNEL='" + bean.getChannel() + "'");
		}

		if (ObjectUtils.isNotEmpty(bean.getPageType())) {
			sql.append(" and PAGE_TYPE=" + bean.getPageType() + "");
		}

		// ad by jief 根据激活时间查询 2013-11-15 start
		if ("0".equals(bean.getTimeType())) {
			// ad by jief 根据激活时间查询 2013-11-15 end
			if (ObjectUtils.isNotEmpty(bean.getStatDate_start())
					&& ObjectUtils.isNotEmpty(bean.getStatDate_end())) {
				sql.append(" and  CREATE_TIME>='" + bean.getStatDate_start()
						+ "' and  CREATE_TIME<='" + bean.getStatDate_end()
						+ "'");
			}
		} else {
			bean.setActiveDate_start(bean.getStatDate_start());
			bean.setActiveDate_end(bean.getStatDate_end());
			if (ObjectUtils.isNotEmpty(bean.getActiveDate_start())
					&& ObjectUtils.isNotEmpty(bean.getActiveDate_end())) {
				Date as = DateUtil.parseDate(bean.getActiveDate_start(),
						DateUtil.PARTTERN_DATE_TIME);
				Date ae = DateUtil.parseDate(bean.getActiveDate_end(),
						DateUtil.PARTTERN_DATE_TIME);
				sql.append(" and  ACTIVITE_DATE  >=" + (as.getTime() / 1000)
						+ " and ACTIVITE_DATE <=" + (ae.getTime() / 1000) + "");
			}
		}

		if (ObjectUtils.isNotEmpty(bean.getSearchStatus())) {
			if ("1".equals(bean.getSearchStatus())) {
				sql.append(" and ACTIVITE_STATUS = 1");
			} else if ("0".equals(bean.getSearchStatus())) {
				sql.append(" and ACTIVITE_STATUS = 0 ");
			}
		}
		List<AdsageActiviteLog> list = this.reDao.findAll(sql.toString(),
				AdsageActiviteLog.class);

		return list;
	}

	@Override
	public void copyActionLog() {
		Date bengin = DateUtil.getDateAddHourMS(new Date(), -1, -5, 0);
		Date end = DateUtil.getDateAddHourMS(new Date(), -1, 0, 0);
		String beginTime = DateUtil.getDateStringByPattern(bengin,
				"yyyy-MM-dd HH:mm:ss");
		String endTime = DateUtil.getDateStringByPattern(end,
				"yyyy-MM-dd HH:mm:ss");
		reDao.copyActionLog(beginTime, endTime);
	}

	@Override
	public List<Map<String, Object>> getAdListAutoCom(String ad_name)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public File getIOSExcelFromResultSet(IOSChannelBean bean) throws Exception {
		File csvFile = new File(ConfigUtil.getString("file.path") + "\\ios.csv");
		File parent = csvFile.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		csvFile.createNewFile();
		BufferedWriter csvFileOutputStream = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"),
				1024);
		csvFileOutputStream
				.write("接收时间,激活时间,广告名,渠道名,mac地址,IDFA,OPENUDID,渠道发送状态\r\n");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ResultSetItor<AdsageActiviteLog> rsi = reDao.getResultItor(bean);
		while (rsi.next()) {
			AdsageActiviteLog log = rsi.getNext();
			String macOne = log.getMac();
			String macNew = "";
			// add By jief 验证mac 长度的有效性
			if (!StringUtils.isEmpty(macOne) && macOne.length() == 12) {
				String[] strs = new String[macOne.length() - 1];
				for (int k = 0; k < macOne.length() - 1; k += 2) {
					strs[k] = macOne.substring(k, k + 2);
					macNew += strs[k] + ":";
				}
				macNew = macNew.substring(0, macNew.length() - 1);
			}
			String dd = format.format(log.getCreate_time());
			String activeTime = "";
			if (null != log.getActivite_date()) {
				activeTime = DateUtil.getDateStrbysec(
						log.getActivite_date() * 1000,
						DateUtil.PARTTERN_DATE_TIME);
			}
			String sendstatus = "";
			if (null != log.getSend()) {
				if (log.getSend() == 0) {
					sendstatus = "作弊嫌疑扣量";
				} else if (log.getSend() == 1) {
					sendstatus = "正常发送";
				} else if (log.getSend() == 2) {
					sendstatus = "协议扣量";
				}
			}
			// add byjief output openudid,idfa
			csvFileOutputStream.write(dd + "," + activeTime + ","
					+ log.getAd_name() + "," + log.getChannel_name() + ","
					+ macNew + "," + log.getIdfa() + "," + log.getOpenUdid()
					+ "," + sendstatus + "\r\n");
		}
		rsi.close();
		csvFileOutputStream.close();
		File file = new File(ConfigUtil.getString("file.path") + "\\ios.csv");
		return file;
	}
}
