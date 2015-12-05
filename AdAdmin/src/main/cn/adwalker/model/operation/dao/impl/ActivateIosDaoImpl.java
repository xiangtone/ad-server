package cn.adwalker.model.operation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.operation.dao.IActivateIosDao;
import cn.adwalker.model.operation.domain.ActivateNumIos;
import cn.adwalker.model.operation.domain.PackageActivateAndroid;

/**
 * <p>
 * Title: AdEffectFirstConfirmDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-16
 */
@Repository("activateIosDao")
public class ActivateIosDaoImpl extends BaseDaoImpl<ActivateNumIos> implements
IActivateIosDao {
	
	public ActivateIosDaoImpl() {
		setTableName("T_IOS_ACTIVATE_NUM");
	}
	/**
	 * (non-Javadoc)
	* <p>Title: restConfIosNum</p>
	* @param id
	* @param number
	* @param status
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IActivateIosDao#restConfIosNum(java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void restConfIosNum(Long id, Integer number, Integer status)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("update T_IOS_ACTIVATE_NUM set CONFIRM_AMOUNT=?,status=? where ID=?");
		}
		jdbcTemplate.update(sb.toString(), new Object[] { number, status,
				id });
	}

	/**
	 * (non-Javadoc)
	* <p>Title: updateStatus</p>
	* @param fraction_id
	* @param status
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IActivateIosDao#updateStatus(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public void updateStatus(Long fraction_id, Integer status) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (fraction_id != null) {
			sb.append("update T_IOS_ACTIVATE_NUM set STATUS=" + status);
			sb.append(" where ID=");
			sb.append(fraction_id);
		}
		jdbcTemplate.update(sb.toString());
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: submitConfirmNumber
	 * </p>
	 * <p>
	 * Description:android确认数录入，批量提交
	 * </p>
	 * 
	 * @param ids
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#submitConfirmNumber(java.lang.String)
	 */
	@Override
	public void submitConfNumIos(String ids) {
		if (!StringUtils.isEmpty(ids)) {
			String arr[] = ids.split(",");
			if (arr != null && arr.length > 0) {
				List<Object[]> objects = new ArrayList<Object[]>();
				for (int i = 0; i < arr.length; i++) {
					objects.add(new Object[] { Long.valueOf(arr[i]) });
				}
				/*
				 * T_IOS_ACTIVATE_NUM_DETAIL  parent_id=111;
				 * 判断media_type 
				 * 
				 * 
				 * 
				 * 
				 * 包批次提交数据(包、日期) 1、查询该包先的数据。
				 * 2、如果是平台,广告主确认数=平台运营扣量的数据，不用参与扣量，直接入库。 3、渠道状态改为待扣量,参与分数。
				 */
				String sql = "UPDATE t_ios_activate_num_detail SET confirm_amount=(CASE WHEN (media_type=0) THEN confirm_num ELSE 0 END),STATUS=9 WHERE parent_id=?";
				jdbcTemplate.batchUpdate(sql, objects);
			}
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatu
	 * </p>
	 * 
	 * @param ids
	 * @param status
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#updateStatu(java.lang.String, java.lang.Integer)
	 */
	@Override
	public void updateStatu(String ids, Integer status) throws Exception {
		if (!StringUtils.isEmpty(ids)) {
			String arr[] = ids.split(",");
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE T_IOS_ACTIVATE_NUM SET status=?  WHERE ID=?");
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (int i = 0; i < arr.length; i++) {
				parameters.add(new Object[] { status, arr[i] });
			}
			this.jdbcTemplate.batchUpdate(sb.toString(), parameters);
		}
	}
	/**
	 * (non-Javadoc)
	* <p>Title: getById</p>
	* <p>Description:TODO</p>
	* @param valueOf
	* @return
	* @see cn.adwalker.model.operation.dao.IActivateIosDao#getById(java.lang.Long)
	 */
	@Override
	public ActivateNumIos getById(Long id) throws Exception {
		StringBuffer sb = new StringBuffer();
		 sb.append("select * from T_IOS_ACTIVATE_NUM t where t.id=?");
		 List<ActivateNumIos> list = (List<ActivateNumIos>) jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<ActivateNumIos>(ActivateNumIos.class), id);
		 ActivateNumIos paa = new ActivateNumIos();
			if (list != null && list.size() > 0) {
				paa = list.get(0);
			}
			return paa;
	}
}
