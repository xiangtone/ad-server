package cn.adwalker.ad.control.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.AdSchedule;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.vo.AdScheduleVo;

@Repository("adScheduleDao")
public class AdScheduleDao extends BaseDao<AdSchedule>{

	public AdScheduleDao() {
		setClazz(AdSchedule.class);
	}
	
	public int[] saveBatch(final List<AdSchedule> adScheduleList) throws DatabaseException {
		String sql = "insert into T_AD_SCHEDULE (TYPE,AD_ID,STATUS,TASK_TIME,TASK_TYPE) values (?,?,?,?,?)";
		return super.namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public int getBatchSize() {
                return adScheduleList.size();
            }
            public void setValues(PreparedStatement ps, int i)throws SQLException {
            	AdSchedule adSchedule = adScheduleList.get(i);
            	ps.setInt(1, adSchedule.getType());
            	ps.setLong(2, adSchedule.getAdId());
            	ps.setInt(3, adSchedule.getStatus());
            	ps.setDate(4, new java.sql.Date(adSchedule.getTaskTime().getTime()));
            	ps.setInt(5, adSchedule.getTaskType());
            }
        });
	}

	public void batchUpdate(List<Object[]> paramsList) throws DatabaseException {
		String sql = "update T_AD_SCHEDULE set RUN_TIME=?,STATUS=?  where id=?";
		batchUpdate(sql, paramsList);		
	}
	

	public List<AdScheduleVo> findAll(Object[] object, Class<AdScheduleVo> clazz) {
		String sql = "select t.STATUS ad_status,s.* from T_AD_SCHEDULE s left join T_AD t on s.ad_id=t.id where s.run_time is null and s.task_time<= ? and s.status=0 and (t.status is not null and t.status!=-5 and t.status!=-10) and task_type is not null order by s.task_time";
		return super.namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper<AdScheduleVo>(clazz), object);
	}
	
}
