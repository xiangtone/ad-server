package cn.adwalker.model.operation.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.operation.dao.IDspInfoDao;
import cn.adwalker.model.operation.domain.DspInfo;

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
@Repository("dspInfoDao")
public class DspInfoDaoImpl extends BaseDaoImpl implements IDspInfoDao {

	public DspInfoDaoImpl() {
		setTableName("T_DSP_INFO");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAdvInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IDspInfoDao#findAdvInfo(java.lang.Long)
	 */
	@Override
	public DspInfo findAdvInfo(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_DSP_INFO  where 1=1 and dsp_id= ?");
		List<DspInfo> list = (List<DspInfo>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DspInfo>(DspInfo.class), id);
		DspInfo vo = new DspInfo();
		if (list != null && list.size() > 0) {
			vo = list.get(0);
		}
		return vo;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateDspService</p>
	* <p>Description:TODO</p>
	* @param dI
	* @throws Exception
	* @see cn.adwalker.model.operation.dao.IDspInfoDao#updateDspService(cn.adwalker.model.operation.domain.DspInfo)
	 */
	@Override
	public int updateDspService(DspInfo dI) throws Exception {
		int result = -1;
		if (dI != null && dI.getDsp_id() != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_DSP_INFO SET BEAN = ?,DSP_DESC=?,DSP_KEY=?,DSP_NAME=?,DSP_URL=?,SERVICE=?,SOURCE_STR=?  where dsp_id= ?");
			result = jdbcTemplate.update(sql.toString(), new Object[] { dI.getBean(),dI.getDsp_desc(),dI.getDsp_key(),dI.getDsp_name(),dI.getDsp_url(),dI.getService(),dI.getSource_str(), dI.getDsp_id() });
		}
		return result;
	}

}
