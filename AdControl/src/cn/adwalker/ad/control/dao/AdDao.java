package cn.adwalker.ad.control.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.Ad;
import cn.adwalker.ad.control.exception.DatabaseException;

@Repository("adDao")
public class AdDao extends BaseDao<Ad> {
	
	public AdDao() {
		setClazz(Ad.class);
	}
	public int[] updateAdStatusBatch(final List<Long> adIdList, final Integer status) throws DatabaseException {
		String sql = "UPDATE T_AD SET STATUS=? WHERE ID=?";
		return super.namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public int getBatchSize() {
                return adIdList.size();
            }
            public void setValues(PreparedStatement ps, int i) throws SQLException {
            	ps.setInt(1, status);
            	ps.setLong(2, adIdList.get(i));
            }
        });
	}
	
	public int updateAdStatus(Long adId, Integer status) throws DatabaseException {
		String sql = "UPDATE T_AD SET STATUS=? WHERE ID=?";
		return update(sql, status, adId);
	}
	
	public Ad getAdById(Long id) throws DatabaseException {
		String sql = "SELECT id,placement_id,media_id,budget_day,budget_type,STATUS,ad_type,online_time,offline_time FROM t_ad WHERE id=?";
		return getById(sql, id);
	}
	
	public int[] batchUpdate(List<Object[]> paramsList) throws DatabaseException {
		String sql = "update t_ad set status=?,online_time=?,offline_time=? where id=?";
		return batchUpdate(sql, paramsList);	
	}
	
}
