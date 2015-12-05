package cn.adwalker.model.operation.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.operation.dao.IDspConfigInfoDao;

/**
 * <p>
 * Title: DspInfoDaoImpl
 * </p>
 * <p>
 * Description:DSPdao业务
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-12-2
 */
@SuppressWarnings("rawtypes")
@Repository("dspConfigInfoDao")
public class DspConfigInfoDaoImpl extends BaseDaoImpl implements IDspConfigInfoDao {

	public DspConfigInfoDaoImpl() {
		setTableName("T_DSP_CONFIG");
	}

	

}
