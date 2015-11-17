package cn.adwalker.ad.api.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.api.app.form.App;
import cn.adwalker.ad.api.app.form.Dev;
import cn.adwalker.ad.api.app.form.DevAccount;
import cn.adwalker.ad.api.app.service.IApiService;
import cn.adwalker.ad.api.app.util.ApiUtils;
import cn.adwalker.ad.api.app.vo.CallBack;
import cn.adwalker.ad.api.app.vo.FinanceByDay;
import cn.adwalker.ad.api.app.vo.FinanceByHour;
import cn.adwalker.ad.api.app.vo.StaticByDay;
import cn.adwalker.ad.api.app.vo.StaticByHour;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.api.dao.IApiApplyMoneyDao;
import cn.adwalker.model.api.domain.ApiApplyMoney;
import cn.adwalker.model.app.dao.IAppCurrencyDao;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.model.app.domain.AppCurrency;
import cn.adwalker.model.app.domain.ApplicationEntity;
import cn.adwalker.model.app.domain.DeveloperEntity;
import cn.adwalker.model.common.dao.IPageDao;

/**
 * 
 * <p>
 * Title: RegistAdvServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-19
 */
@Service
public class ApiServiceImpl implements IApiService {

	Logger logger = Logger.getLogger(ApiServiceImpl.class);

	@Resource
	private IApplicationDao applicationDao;

	@Resource
	private IDeveloperDao developerDao;

	@Resource
	private IPageDao pageDao;

	@Resource
	private IAppCurrencyDao appCurrencyDao;

	@Resource
	private IApiApplyMoneyDao apiApplyMoneyDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param dev
	 * @throws Exception
	 * @see cn.adwalker.ad.api.app.service.IApiService#saveOrUpdate(cn.adwalker.ad.api.app.form.Dev)
	 */
	@Override
	public void saveOrUpdateDev(Dev dev) throws Exception {
		if (dev != null && dev.getId() != null) {
			DeveloperEntity entity = (DeveloperEntity) developerDao.get(
					dev.getId(), DeveloperEntity.class);
			if (entity != null && entity.getId() != null) {

			} else {
				entity = new DeveloperEntity();
				entity.setResource_code("KUAIYOU");
				entity.setId(dev.getId());
				entity.setEmail(dev.getEmail());
			}

			entity.setPassword(dev.getPassword());
			entity.setCompany_name(dev.getCompanyName());
			entity.setCompany_address(dev.getCompanyAddress());
			entity.setMailing_address(dev.getMailingAddress());
			entity.setType(dev.getType());
			entity.setPost_code(dev.getPostCode());
			entity.setQq(dev.getQq());
			entity.setReal_name(dev.getRealName());
			entity.setMobile_phone(dev.getMobilePhone());
			entity.setCard_type(dev.getCard_type());
			entity.setCard_code(dev.getCardCode());

			entity.setUpdate_time(DateUtil.parseDate(dev.getUpdatetime(),
					DateUtil.PARTTERN_DATE_TIME));
			entity.setCreate_time(DateUtil.parseDate(dev.getCreatetime(),
					DateUtil.PARTTERN_DATE_TIME));
			entity.setStatus(1);

			developerDao.saveOrUpdate(entity);
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveOrUpdateApp
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param app
	 * @throws Exception
	 * @throws NumberFormatException
	 * @see cn.adwalker.ad.api.app.service.IApiService#saveOrUpdateApp(cn.adwalker.ad.api.app.form.App)
	 */
	@Override
	public void saveOrUpdateApp(App app) throws NumberFormatException,
			Exception {
		if (app != null && app.getId() != null) {
			ApplicationEntity entity = applicationDao.get(
					Long.valueOf(app.getId()), ApplicationEntity.class);
			if (entity == null) {
				DeveloperEntity dev = getByEmail(app.getDev_email());
				if (dev != null) {
					entity = new ApplicationEntity();
					entity.setAppkey(app.getAppkey());
					entity.setDev_id(dev.getId());
				} else {
					logger.error("api app add has error  " + app.getDev_email());
				}

			}
			entity.setId(Long.valueOf(app.getId()));
			entity.setCategory_id(Long.valueOf(app.getCategory_id()));
			entity.setCreate_time(DateUtil.parseDate(app.getCreate_time()));
			entity.setKeyword(app.getKeyword());
			entity.setLong_desc(app.getLong_desc());
			entity.setName(app.getName());
			entity.setOs(app.getOs());
			entity.setPackage_name(app.getPackage_name());
			entity.setPlacement(0 + "");
			entity.setProject_url(app.getProject_url());
			entity.setRelease_time(DateUtil.parseDate(app.getRelease_time()));
			entity.setResource_size(!StringUtils.isEmpty(app.getResource_size()) ? Long
					.valueOf(app.getResource_size()) : 0);
			entity.setUpdate_time(DateUtil.parseDate(app.getUpdate_time()));
			entity.setVersion_code(app.getVersion_code());
			entity.setVersion_name(app.getVersion_name());
			entity.setStatus(1);
			applicationDao.saveOrUpdate(entity);
			pageDao.saveOrUpdate(entity.getId(), app.getPage_type());
			AppCurrency currency = new AppCurrency();
			currency.setApp_id(entity.getId());
			if (app.getCurrency_value() != null) {
				currency.setExchange_rate_rmb(String.valueOf(app.getCurrency_value()));
			} else {
				currency.setExchange_rate_rmb(String.valueOf(100));
			}
			if (!StringUtil.isEmpty(app.getCurrency_name())) {
				currency.setVirtual_currency_name(app.getCurrency_name());
			} else {
				currency.setVirtual_currency_name("积分");
			}

			currency.setStatus(1);
			appCurrencyDao.saveOrUpdate(currency);
			CacheUtils.updateAppCache(entity.getId());
		}
	}

	/**
	 * 
	 * <p>
	 * Title: getByEmail
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-8-2
	 * @return DeveloperEntity
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private DeveloperEntity getByEmail(String email) throws Exception {
		DeveloperEntity dev = null;
		if (!StringUtils.isEmpty(email)) {
			List<DeveloperEntity> list = (List<DeveloperEntity>) developerDao
					.findAll(
							"select * from T_DEVELOPER where email=? and resource_code=?",
							new Object[] { email, "KUAIYOU" },
							DeveloperEntity.class);
			if (list != null && list.size() > 0) {
				dev = list.get(0);
			}
		}
		return dev;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: exportCsv
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @throws NumberFormatException
	 * @throws Exception
	 * @see cn.adwalker.ad.api.app.service.IApiService#exportCsv()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void exportCsv() throws NumberFormatException, Exception {
		int hour = DateUtil.getHourOfDay();
		Date date = new Date();
		if (hour == 0) {
			hour = 23;
			date = DateUtil.addDays(date, -1);
		} else {
			hour = hour - 1;
		}
		String hour_str = (hour >= 10 ? "" : "0") + hour;
		List<StaticByHour> list = (List<StaticByHour>) applicationDao
				.findAll(
						"select *  from  T_STATIC_APP_BYHOUR where static_date=? and  app_id in (SELECT  APP.ID  FROM T_APPLICATION  app ,T_DEVELOPER dev where app.dev_id=dev.id and dev.resource_code=?) and static_hour=? ",
						new Object[] { DateUtil.formatDate(date), "KUAIYOU",
								hour_str }, StaticByHour.class);
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (StaticByHour d : list) {
				sb.append(chaeckObj(d.getClick())).append(",");
				sb.append(chaeckObj(d.getStatic_date())).append(",");
				sb.append(chaeckObj(d.getApp_id())).append(",");
				sb.append(chaeckObj(d.getType_id())).append(",");
				sb.append(chaeckObj(d.getPospv())).append(",");
				sb.append(chaeckObj(d.getPv())).append(",");
				sb.append(chaeckObj(d.getDownload())).append(",");
				sb.append(chaeckObj(d.getActivate())).append(",");
				sb.append(chaeckObj(d.getCost())).append(",");
				sb.append(
						chaeckObj(DateUtil.formatDate(date) + " "
								+ d.getStatic_hour() + ":0:0")).append(",");
				sb.append(chaeckObj(d.getSyspv())).append(",");
				sb.append(chaeckObj(d.getSysclick())).append(",");
				sb.append(chaeckObj(d.getSysdownload())).append(",");
				sb.append(chaeckObj(d.getSysactivate())).append(",");
				sb.append(chaeckObj(d.getSyspospv())).append(",");
				sb.append(chaeckObj(d.getClickd())).append(",");
				sb.append(chaeckObj(d.getSysclickd()));
				sb.append("\r\n");
			}

		}
		String fileName = DateUtil.formatDate(date) + "_" + hour_str + ".csv";
		FileUtils.writeStringToFile(
				new File(ConfigUtil.getString("api.file.byhour") + "/"
						+ DateUtil.formatDate(date) + "/" + fileName),
				sb.toString());
		FinanceByHour result = new FinanceByHour(DateUtil.formatDate(date));
		result.setFilename(fileName);
		result.setHour(hour_str);
		ApiUtils.sendStaticByHour(JacksonMapper.objectToJsonString(result));
	}

	private Object chaeckObj(Object o) {
		Object obj = null;
		if (ObjectUtils.isNotEmpty(o)) {
			obj = o;
		} else {
			obj = "";
		}
		return obj;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: exportCsvByDay
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @throws Exception
	 * @see cn.adwalker.ad.api.app.service.IApiService#exportCsvByDay()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void exportCsvByDay(Date date) throws Exception {
		if (date != null) {
			List<StaticByDay> list = (List<StaticByDay>) applicationDao
					.findAll(
							"select * from  T_STATIC_APP_BYDAY where static_date=? and  app_id in (SELECT  APP.ID  FROM T_APPLICATION  app ,T_DEVELOPER dev where app.dev_id=dev.id and dev.resource_code=?)  ",
							new Object[] { DateUtil.formatDate(date), "KUAIYOU" },
							StaticByDay.class);
			StringBuilder sb = new StringBuilder();
			if (list != null && list.size() > 0) {

				for (StaticByDay d : list) {
					sb.append(chaeckObj(d.getClick())).append(",");
					sb.append(chaeckObj(d.getStatic_date())).append(",");
					sb.append(chaeckObj(d.getApp_id())).append(",");
					sb.append(chaeckObj(d.getType_id())).append(",");
					sb.append(chaeckObj(d.getPospv())).append(",");
					sb.append(chaeckObj(d.getPv())).append(",");
					sb.append(chaeckObj(d.getDownload())).append(",");
					sb.append(chaeckObj(d.getActivate())).append(",");
					sb.append(chaeckObj(d.getCost())).append(",");
					sb.append(chaeckObj(d.getSyspv())).append(",");
					sb.append(chaeckObj(d.getSysclick())).append(",");
					sb.append(chaeckObj(d.getSysdownload())).append(",");
					sb.append(chaeckObj(d.getSysactivate())).append(",");
					sb.append(chaeckObj(d.getSyspospv())).append(",");
					sb.append(chaeckObj(d.getClickd())).append(",");
					sb.append(chaeckObj(d.getSysclickd()));
					sb.append("\r\n");
				}
			}
			String fileName = DateUtil.formatDate(date) + ".csv";
			FileUtils.writeStringToFile(
					new File(ConfigUtil.getString("api.file.byday") + "/"
							+ fileName), sb.toString());
			FinanceByDay result = new FinanceByDay();
			result.setFilename(fileName);
			ApiUtils.sendStaticByDay(JacksonMapper.objectToJsonString(result));
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: sendApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @throws IOException
	 * @see cn.adwalker.ad.api.app.service.IApiService#sendApp()
	 */
	@Override
	public void sendApp(Long app_id, String aduit, Integer status, Double scale)
			throws IOException {
		CallBack entity = new CallBack();
		entity.setApp_id(String.valueOf(app_id));
		entity.setCheck_msg(aduit);
		entity.setStatus(String.valueOf(status));
		entity.setScale(String.valueOf(scale));
		ApiUtils.sendApp(JacksonMapper.objectToJsonString(entity));

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveApplyMoney
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param applyMoney
	 * @throws Exception
	 * @see cn.adwalker.ad.api.app.service.IApiService#saveApplyMoney(cn.adwalker.ad.api.app.form.DevAccount)
	 */
	@Override
	public void saveApplyMoney(DevAccount applyMoney) throws Exception {
		if (applyMoney != null) {
			ApiApplyMoney entity = new ApiApplyMoney();
			entity.setApplay_money(applyMoney.getApplay_money());
			entity.setApply_date(applyMoney.getDate());
			entity.setDev_id(applyMoney.getDev_id());
			entity.setCreate_time(new Date());
			apiApplyMoneyDao.insert(entity);
		}
	}

}
