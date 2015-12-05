package cn.adwalker.model.operation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.ad.vo.AdChannelEditVo;
import cn.adwalker.ad.admin.channel.vo.ChannelVo;
import cn.adwalker.ad.admin.operation.vo.OperationPlacementPackageVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IPackageActivateDao;
import cn.adwalker.model.operation.domain.PackageActivate;
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
@Repository
public class PackageActivateDaoImpl extends BaseDaoImpl<PackageActivate> implements IPackageActivateDao {
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationNumber
	 * </p>
	 * 
	 * @param id
	 * @param number
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#confirmationNumber(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public void confirmationNumber(Long id, Integer number, Integer status) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("update T_PACKAGE_ACTIVATE set CONFIRM_AMOUNT=?,status=? where ID=?");
		}
		jdbcTemplate.update(sb.toString(), new Object[] { number, status, id });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: statusConfirm
	 * </p>
	 * 
	 * @param fraction_id
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#statusConfirm(java.lang.Long)
	 */
	@Override
	public void updateStatus(Long fraction_id, Integer status) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (fraction_id != null) {
			sb.append("update T_PACKAGE_ACTIVATE set STATUS=" + status);
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
	public void submitConfirmNumber(String ids) {
		if (!StringUtils.isEmpty(ids)) {
			String arr[] = ids.split(",");
			if (arr != null && arr.length > 0) {
				List<Object[]> objects = new ArrayList<Object[]>();
				for (int i = 0; i < arr.length; i++) {
					objects.add(new Object[] { Long.valueOf(arr[i]) });
				}
				/*
				 * 包批次提交数据(包、日期) 1、查询该包先的数据。 2、如果是平台,广告主确认数=平台运营扣量的数据，不用参与扣量，直接入库。 3、渠道状态改为待扣量,参与分数。
				 */
				String sb = "UPDATE t_package_activate_detail SET confirm_amount=(CASE WHEN (media_type=0) THEN confirm_num ELSE 0 END),STATUS=(CASE WHEN (media_type=0) THEN 1 ELSE 0 END) WHERE parent_id=?";
				jdbcTemplate.batchUpdate(sb, objects);
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
			sb.append(" UPDATE T_PACKAGE_ACTIVATE SET status=?  WHERE ID=?");
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (int i = 0; i < arr.length; i++) {
				parameters.add(new Object[] { status, arr[i] });
			}
			this.jdbcTemplate.batchUpdate(sb.toString(), parameters);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationCpd
	 * </p>
	 * <p>
	 * Description:更新cpd投放的渠道成本
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#confirmationCpd(java.lang.Long, java.lang.Double)
	 */
	@Override
	public void confirmationCpd(Long id, Double amount) throws Exception {
		// 更新cpd投放的渠道成本
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("update T_PACKAGE_ACTIVATE_DETAIL set SYS_COST=");
			if (ObjectUtils.isNotEmpty(amount)) {
				sb.append(amount);
			}
			sb.append(",status=1 where ID=");
			sb.append(id);
		}
		jdbcTemplate.update(sb.toString());
	}
	/**
	 * (non-Javadoc)
	* <p>Title: getById</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IPackageActivateDao#getById(java.lang.Long)
	 */
	@Override
	public PackageActivateAndroid getById(Long id) throws Exception {
		StringBuffer sb = new StringBuffer();
		 sb.append("select * from T_PACKAGE_ACTIVATE t where t.id=?");
		 List<PackageActivateAndroid> list = (List<PackageActivateAndroid>) jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<PackageActivateAndroid>(PackageActivateAndroid.class), id);
		 PackageActivateAndroid paa = new PackageActivateAndroid();
			if (list != null && list.size() > 0) {
				paa = list.get(0);
			}
			return paa;
	}

}
