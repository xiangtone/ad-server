package cn.adwalker.model.app.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.domain.Application;
import cn.adwalker.model.app.domain.ApplicationEntity;

/**
 * 功能概述：<br>
 * 开发者SDK应用实现类
 * 
 * @author guoyongxiang
 */
@Repository("developedAppDao")
public class ApplicationDaoImpl extends BaseDaoImpl<ApplicationEntity> implements IApplicationDao {

	public ApplicationDaoImpl() {
		setTableName("T_APPLICATION");
	}

	/**
	 * @see cn.adwalker.model.application.dao.IApplicationDao.escore.server.developer.dao.impl.IDevelopedAppDao#insert(cn.adwalker.model.app.domain.Application)
	 */
	@Override
	public Long insert(Application developedApp) {
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
		return insert(sql.toString(), developedApp);
	}

	/**
	 * @see cn.adwalker.model.app.dao.IApplicationDao#deleteById(int)
	 */
	@Override
	public Integer deleteById(Long id) {
		String sql = "delete from T_APPLICATION where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	/**
	 * @see cn.adwalker.model.app.dao.IApplicationDao#findAll()
	 */
	@Override
	public List<Application> findByUser(Long id) {
		String sql = "select * from T_APPLICATION where DEV_ID = ?  order by CREATE_TIME desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Application>(Application.class), id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatePrice
	 * </p>
	 * 
	 * @param id
	 * @param rate
	 * @see cn.adwalker.model.app.dao.IApplicationDao#updatePrice(java.lang.Long, java.lang.Double)
	 */
	@Override
	public void updateAppRate(Long id, Double rate) {
		String sql = "update T_PAGE set RATE =? where ID=?";
		jdbcTemplate.update(sql.toString(), new Object[] { rate, id });

	}

	/**
	 * @see cn.adwalker.model.app.dao.IApplicationDao#update(cn.adwalker.model.app.domain.Application)
	 */
	@Override
	public void update(Application developedApp) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" update T_APPLICATION set ");
			if (ObjectUtils.isNotEmpty(developedApp.getCategoryId())) {
				sb.append("CATEGORY_ID=:categoryId,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getName())) {
				sb.append("NAME=:name,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getKeyword())) {
				sb.append("KEYWORD=:keyword,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getLongDesc())) {
				sb.append("LONG_DESC=:longDesc,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getProjectUrl())) {
				sb.append("PROJECT_URL=:projectUrl,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getStatus())) {
				sb.append("STATUS=:status,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getManagerId())) {
				sb.append("MANAGER_ID=:managerId,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getCheckTime())) {
				sb.append("CHECK_TIME=:checkTime,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getDel())) {
				sb.append("DEL=:del,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getAppkey())) {
				sb.append("APPKEY=:appkey,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getOs())) {
				sb.append("OS=:os,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getApp_manager())) {
				sb.append("APP_MANAGER=:app_manager,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getReleaseTime())) {
				sb.append("RELEASE_TIME=:releaseTime,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getResourceSize())) {
				sb.append("RESOURCE_SIZE=:resourceSize,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getPackageName())) {
				sb.append("PACKAGE_NAME=:packageName,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getVersionName())) {
				sb.append("VERSION_NAME=:versionName,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getVersionCode())) {
				sb.append("VERSION_CODE=:versionCode,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getCheckMsg())) {
				sb.append("CHECK_MSG=:checkMsg,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getLastUpdateMan())) {
				sb.append("LAST_UPDATE_MAN=:lastUpdateMan,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getType_id())) {
				sb.append("TYPE_ID=:type_id,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getDown_time())) {
				sb.append("DOWN_TIME=:down_time,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getScale())) {
				sb.append("scale=:scale,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getIs_coordinate())) {
				sb.append("IS_COORDINATE=:is_coordinate,");
			}
			if (ObjectUtils.isNotEmpty(developedApp.getPlacement())) {
				sb.append("PLACEMENT=:placement,");
			}
			
			if (ObjectUtils.isNotEmpty(developedApp.getApp_manager_id())) {
				sb.append("app_manager_id=:app_manager_id,");
			}
			
			if (ObjectUtils.isNotEmpty(developedApp.getApp_res())) {
				sb.append("app_res=:app_res,");
			}
			
			sb.append("UPDATE_TIME=:updateTime");
			sb.append(" where ID=:id");
			super.update(sb.toString(), developedApp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see cn.adwalker.model.app.dao.IApplicationDao#update(cn.adwalker.model.app.domain.Application)
	 */
	@Override
	public Long updatePath(Application developedApp) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_APPLICATION set ");
		if (ObjectUtils.isNotEmpty(developedApp.getProjectUrl())) {
			sql.append("PROJECT_URL=:projectUrl,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getResourceSize())) {
			sql.append("RESOURCE_SIZE=:resourceSize,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getPackageName())) {
			sql.append("PACKAGE_NAME=:packageName,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getVersionName())) {
			sql.append("VERSION_NAME=:versionName,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getVersionCode())) {
			sql.append("VERSION_CODE=:versionCode,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getType_id())) {
			sql.append("TYPE_ID=:type_id,");
		}
		String sql1 = sql.substring(0, sql.length() - 1);
		sql = new StringBuffer();
		sql.append(sql1);
		sql.append(" where ID=:id");
		super.update(sql.toString(), developedApp);
		return developedApp.getDev_id();
	}

	/**
	 * 根据开发者ID批量修改
	 * 
	 * @param developedApp
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long updateByDevId(Application developedApp) throws Exception {
		developedApp.setUpdateTime(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_APPLICATION set ");
		if (ObjectUtils.isNotEmpty(developedApp.getStatus())) {
			sql.append("STATUS=:status,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getManagerId())) {
			sql.append("MANAGER_ID=:managerId,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getCheckTime())) {
			sql.append("CHECK_TIME=:checkTime,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getDel())) {
			sql.append("DEL=:del,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getCheckMsg())) {
			sql.append("CHECK_MSG=:checkMsg,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getLastUpdateMan())) {
			sql.append("LAST_UPDATE_MAN=:lastUpdateMan,");
		}
		if (ObjectUtils.isNotEmpty(developedApp.getType_id())) {
			sql.append("TYPE_ID=:type_id,");
		}
		sql.append("UPDATE_TIME=:updateTime");
		sql.append(" where DEV_ID=:dev_id");
		super.update(sql.toString(), developedApp);
		return developedApp.getDev_id();
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findCount
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param developerID
	 * @return
	 * @see cn.adwalker.model.app.dao.IApplicationDao#findCount(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public long findCountByDevId(Long devID) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from T_APPLICATION  where dev_id = " + devID + " order by id");
		return jdbcTemplate.queryForLong(sql.toString());
	}

	@Override
	public void updateMediaRating(Long mediaId, Double score) {
		String sql = "update T_APPLICATION set SCALE=? where ID=?";
		jdbcTemplate.update(sql, new Object[] { score, mediaId });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * 
	 * @param entity
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IApplicationDao#saveOrUpdate(cn.adwalker.model.app.domain.ApplicationEntity)
	 */
	@Override
	public void saveOrUpdate(ApplicationEntity app) throws Exception {
		ApplicationEntity entity = (ApplicationEntity) this.get(app.getId(), ApplicationEntity.class);
		boolean flag = false;
		if (entity != null) {
			flag = true;
		} else {
			entity = new ApplicationEntity();
			entity.setId(app.getId());
			entity.setAppkey(app.getAppkey());
			entity.setDev_id(app.getDev_id());
		}
		entity.setCategory_id(app.getCategory_id());
		entity.setCreate_time(app.getCreate_time());
		entity.setKeyword(app.getKeyword());
		entity.setLong_desc(app.getLong_desc());
		entity.setName(app.getName());
		entity.setOs(app.getOs());
		entity.setPackage_name(app.getPackage_name());
		entity.setPlacement(0 + "");
		entity.setProject_url(app.getProject_url());
		entity.setRelease_time(app.getRelease_time());
		entity.setResource_size(app.getResource_size());
		entity.setUpdate_time(app.getUpdate_time());
		entity.setVersion_code(app.getVersion_code());
		entity.setVersion_name(app.getVersion_name());
		entity.setStatus(app.getStatus());
		entity.setDel(0);
		if (flag) {
			update(entity);
		} else {
			entity.setApp_res(1);
			insertAPP(entity);
		}

	}

	/**
	 * <p>
	 * Title: insertAPP
	 * </p>
	 * 
	 * @param entity
	 * @author cuidd
	 * @date 2013-8-1
	 * @return void
	 * @version 1.0
	 */

	private void insertAPP(ApplicationEntity entity) {
		Field[] fields = entity.getClass().getDeclaredFields();
		List<String> arr = new ArrayList<String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!fields[i].getName().toUpperCase().equals("SERIALVERSIONUID")) {
				if (!fields[i].getName().equals(super.ID)) {
					arr.add(fields[i].getName());
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into " + tableName + "(");
		for (String s : arr) {
			sb.append(s + ",");
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		sb.append(" values (");
		for (String s : arr) {
			if (!s.equals(super.ID)) {
				sb.append(":" + s + ",");
			}
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		super.update(sb.toString(), entity);

	}
}