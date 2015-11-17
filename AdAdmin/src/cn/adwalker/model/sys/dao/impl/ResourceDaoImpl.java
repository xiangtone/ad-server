/*
 * UserDeveloperDAOImpl.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.model.sys.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.report.domain.TStatReportTable;
import cn.adwalker.model.sys.dao.ISysResourceDao;
import cn.adwalker.model.sys.domain.SysPurview;
import cn.adwalker.model.sys.domain.SysResource;

/**
 * 
 * <p>
 * Title: ResourceDaoImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-15
 */
@Repository("resourceManageDao")
public class ResourceDaoImpl extends BaseDaoImpl<SysResource> implements ISysResourceDao {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(ResourceDaoImpl.class);

	public ResourceDaoImpl() {
		setTableName("T_SYS_RESOURCE");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param dev_id
	 * @param status
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysResourceDao#updateStatus(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public Integer updateStatus(Long id, Integer status) throws Exception {
		String sql = "update T_SYS_RESOURCE set STATUS=?,CREATE_TIME=? where ID=?";
		return jdbcTemplate.update(sql, new Object[] { status, new Date(), id });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * 
	 * @param s
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysResourceDao#saveOrUpdate(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdate(String s) throws Exception {
		if (!StringUtils.isEmpty(s)) {
			List<SysResource> list = (List<SysResource>) findAll("select * from T_SYS_RESOURCE where res_url='" + s + "'", SysResource.class);
			if (list == null || list.size() <= 0) {
				SysResource entity = new SysResource();
				entity.setUrl(s);
				entity.setCreate_time(new Date());
				entity.setStatus(0);
				super.insert(entity);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.sys.dao.ISysResourceDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysResource> findAll() throws Exception {
		return (List<SysResource>) findAll("select ID,NAME name,URL url from T_SYS_RESOURCE where url is not null", SysResource.class);
	}

	@Override
	public void addSysPurview(TStatReportTable table) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_SYS_RESOURCE");
		sql.append("(res_name,res_url,STAT_REPORT_ID,parent_id,create_time)");
		sql.append(" values(:menuName,:menuRequest,:id,3,NOW())");
		super.update(sql.toString(), table);
	}

	@Override
	public void updateSysPurview(TStatReportTable table) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_SYS_RESOURCE set ");
		sql.append("   RES_NAME='" + table.getMenuName() + "'");
		sql.append(" , RES_URL='" + table.getMenuRequest() + "'");
		sql.append(" where STAT_REPORT_ID =" + table.getId());
		jdbcTemplate.update(sql.toString());
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long findByTableId(Long id) {
		String sql = "select id from T_SYS_RESOURCE where STAT_REPORT_ID=" + id;
		return jdbcTemplate.queryForLong(sql);
	}

	@Override
	public int deleteById(Long id) throws Exception {
		String sql = " delete from  T_SYS_RESOURCE where id = ? ";
		return this.jdbcTemplate.update(sql, id);
	}

	@Override
	public int insert(SysPurview anagePurview) throws Exception {
		String sql = "insert into T_SYS_RESOURCE (res_name,res_url,create_Time) values (:resName,:resUrl,:createTime)";
		return super.update(sql, anagePurview);
	}

	@Override
	public int updateById(SysPurview sysPurview) throws Exception {
		String sql = " update T_SYS_RESOURCE set res_name = :resName , res_url = :resUrl where id = :id ";
		return super.update(sql, sysPurview);
	}

	@Override
	public List<SysPurview> findSub() throws Exception {
		String sql = " select * from T_SYS_RESOURCE where parent_id <> 0 ";
		List<SysPurview> list = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysPurview>(SysPurview.class));
		return list;
	}

}