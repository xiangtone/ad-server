package cn.adwalker.model.operation.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.operation.dao.IOperationInterfaceIosDao;
import cn.adwalker.model.operation.domain.CollocationIos;
import cn.adwalker.ad.admin.operation.vo.InterfaceChannelVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;

/**
 * <p>
 * Title: OperationInterfaceIosDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-5
 */
@Repository("interfaceIosDao")
public class OperationInterfaceIosDaoImpl extends BaseDaoImpl implements IOperationInterfaceIosDao {

	public OperationInterfaceIosDaoImpl() {
		super();
		this.setTableName("T_CAMPAIGN_CONFIG");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getIos
	 * </p>
	 * 
	 * @param campaign_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationInterfaceIosDao#getIos(java.lang.Long)
	 */
	@Override
	public CollocationIos getIos(Long placement_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_CAMPAIGN_CONFIG  where 1=1 and placement_id= ?");
		List<CollocationIos> list = (List<CollocationIos>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<CollocationIos>(CollocationIos.class), placement_id);
		CollocationIos ios = new CollocationIos();
		if (list != null && list.size() > 0) {
			ios = list.get(0);
		}
		return ios;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insertIos
	 * </p>
	 * 
	 * @param collocation
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationInterfaceIosDao#insertIos(cn.adwalker.model.operation.domain.CollocationIos)
	 */
	@Override
	public int insertIos(CollocationIos collocation) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_CAMPAIGN_CONFIG (AD_KEY,URL,ADID_STR ,DEVICEID_PARA ,SOURSE_STR,EVENTTIME_PARA,SEND_TYPE,SOURCE,CLIENT_IP,AD_NAME,PLACEMENT_ID,CREATE_TIME) ");
		sql.append(" values(:ad_key,:url,:adid_str,:deviceid_para,:sourse_str,:eventtime_para,:send_type,:source,:client_ip,:ad_name,:placement_id,:create_time) ");
		return super.update(sql.toString(), collocation);

	}

	@Override
	public int updatePlacementIos(CollocationIos collocation) throws Exception {
		String sql = "update T_PLACEMENT set CONFIG_ID=? where ID=?";
		return jdbcTemplate.update(sql, new Object[] { collocation.getId(), collocation.getPlacement_id() });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getChannel
	 * </p>
	 * 
	 * @param channel
	 * @return
	 * @see cn.adwalker.model.operation.dao.IOperationInterfaceIosDao#getChannel(java.lang.String)
	 */
	@Override
	public InterfaceChannelVo getChannel(String channel) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_CHANNEL_CONFIG  where CHANNEL=?");
		List<InterfaceChannelVo> list = (List<InterfaceChannelVo>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<InterfaceChannelVo>(InterfaceChannelVo.class), channel);
		InterfaceChannelVo vo = new InterfaceChannelVo();
		if (list != null && list.size() > 0) {
			vo = list.get(0);
		}
		return vo;
	}

}
