package cn.adwalker.ad.control.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LongRowMapper implements RowMapper<Long> {

	private static LongRowMapper longRowMapper = null;
	
	public static LongRowMapper getLongRowMapper() {
		if(longRowMapper == null) {
			longRowMapper = new LongRowMapper();
		}
		return longRowMapper;
	}
	public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getLong(1);
	}
	
}
