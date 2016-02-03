package cn.adwalker.ad.model.application.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.application.domain.Application;
import cn.adwalker.ad.model.page.dao.BaseDao;
import cn.adwalker.ad.util.ObjectUtils;
import cn.adwalker.ad.util.StatusConstant;

/**
 * 功能概述：<br>
 * 开发者SDK应用实现类
 */
@Repository("applicationDao")
public class ApplicationDao extends BaseDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Long insert(Application developedApp) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_APPLICATION(");
		sql.append("DEV_ID,");
		sql.append("CATEGORY_ID,");
		sql.append("NAME,");
		sql.append("KEYWORD,");
		sql.append("LONG_DESC,");
		sql.append("PROJECT_URL,");
		sql.append("STATUS,");
		sql.append("SCALE,");
		sql.append("MANAGER_ID,");
		sql.append("CHECK_TIME,");
		sql.append("APP_RES,");
		sql.append("CREATE_TIME,");
		sql.append("APPKEY,");
		sql.append("RELEASE_TIME,");
		sql.append("RESOURCE_SIZE,");
		sql.append("PACKAGE_NAME,");
		sql.append("VERSION_NAME,");
		sql.append("VERSION_CODE,");
		sql.append("APP_MANAGER,");
		sql.append("CHECK_MSG,");
		sql.append("LAST_UPDATE_MAN,");
		sql.append("UPDATE_TIME,");
		sql.append("OS,PLACEMENT)");
		sql.append(" values ( ");
		sql.append(":dev_id,");
		sql.append(":categoryId,");
		sql.append(":name,");
		sql.append(":keyword,");
		sql.append(":longDesc,");
		sql.append(":projectUrl,");
		sql.append(":status,");
		sql.append(":scale,");
		sql.append(":managerId,");
		sql.append(":checkTime,");
		sql.append(":app_res,");
		sql.append(":createTime,");
		sql.append(":appkey,");
		sql.append(":releaseTime,");
		sql.append(":resourceSize,");
		sql.append(":packageName,");
		sql.append(":versionName,");
		sql.append(":versionCode,");
		sql.append(":app_manager,");
		sql.append(":checkMsg,");
		sql.append(":lastUpdateMan,");
		sql.append(":updateTime,");
		sql.append(":os,0)");
		namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(developedApp), keyHolder);
		return keyHolder.getKey().longValue();
	}

	public List<Application> findByDeveloper(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_APPLICATION where DEV_ID = ? and status<>" + StatusConstant.STATUS_STOP + " order by CREATE_TIME desc");
		return namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<Application>(Application.class), id);
	}

	public Application findById(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_APPLICATION where id = ? and status<>" + StatusConstant.STATUS_STOP);
		List<Application> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<Application>(Application.class), id);
		if (objects != null && objects.size() > 0) {
			return objects.get(0);
		}
		return null;
	}

	public Application findByDeveloper(Long id, Long developerId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_APPLICATION where id = ? and dev_id= ? and status<>" + StatusConstant.STATUS_STOP);
		List<Application> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<Application>(Application.class), id, developerId);
		if (objects != null && objects.size() > 0) {
			return objects.get(0);
		}
		return null;
	}

	public Long update(Application application) throws Exception {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_APPLICATION set ");
			if (ObjectUtils.isNotEmpty(application.getDev_id())) {
				sql.append("DEV_ID=:dev_id,");
			}
			if (ObjectUtils.isNotEmpty(application.getCategoryId())) {
				sql.append("CATEGORY_ID=:categoryId,");
			}
			if (ObjectUtils.isNotEmpty(application.getName())) {
				sql.append("NAME=:name,");
			}
			if (ObjectUtils.isNotEmpty(application.getKeyword())) {
				sql.append("KEYWORD=:keyword,");
			}
			if (ObjectUtils.isNotEmpty(application.getShortDesc())) {
				sql.append("SHORT_DESC=:shortDesc,");
			}
			if (ObjectUtils.isNotEmpty(application.getIconUrl())) {
				sql.append("ICON_URL=:iconUrl,");
			}
			if (ObjectUtils.isNotEmpty(application.getLongDesc())) {
				sql.append("LONG_DESC=:longDesc,");
			}
			if (ObjectUtils.isNotEmpty(application.getLongDesc())) {
				sql.append("MARKET_URL=:marketUrl,");
			}
			if (ObjectUtils.isNotEmpty(application.getProjectUrl())) {
				sql.append("PROJECT_URL=:projectUrl,");
			}
			if (ObjectUtils.isNotEmpty(application.getStatus())) {
				sql.append("STATUS=:status,");
			}
			if (ObjectUtils.isNotEmpty(application.getManagerId())) {
				sql.append("MANAGER_ID=:managerId,");
			}
			if (ObjectUtils.isNotEmpty(application.getCheckTime())) {
				sql.append("CHECK_TIME=:checkTime,");
			}
			if (ObjectUtils.isNotEmpty(application.getAppkey())) {
				sql.append("APPKEY=:appkey,");
			}
			if (ObjectUtils.isNotEmpty(application.getOs())) {
				sql.append("OS=:os,");
			}
			if (ObjectUtils.isNotEmpty(application.getReleaseTime())) {
				sql.append("RELEASE_TIME=:releaseTime,");
			}
			if (ObjectUtils.isNotEmpty(application.getResourceSize())) {
				sql.append("RESOURCE_SIZE=:resourceSize,");
			}
			if (ObjectUtils.isNotEmpty(application.getPackageName())) {
				sql.append("PACKAGE_NAME=:packageName,");
			}
			if (ObjectUtils.isNotEmpty(application.getVersionName())) {
				sql.append("VERSION_NAME=:versionName,");
			}
			if (ObjectUtils.isNotEmpty(application.getVersionCode())) {
				sql.append("VERSION_CODE=:versionCode,");
			}
			if (ObjectUtils.isNotEmpty(application.getCheckMsg())) {
				sql.append("CHECK_MSG=:checkMsg,");
			}
			if (ObjectUtils.isNotEmpty(application.getLastUpdateMan())) {
				sql.append("LAST_UPDATE_MAN=:lastUpdateMan,");
			}
			if (ObjectUtils.isNotEmpty(application.getType_id())) {
				sql.append("TYPE_ID=:type_id,");
			}
			if (ObjectUtils.isNotEmpty(application.getDown_time())) {
				sql.append("DOWN_TIME=:down_time,");
			}
			sql.append("UPDATE_TIME=:updateTime");
			sql.append(" where ID=:id");
			namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(application));
			return application.getDev_id();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}