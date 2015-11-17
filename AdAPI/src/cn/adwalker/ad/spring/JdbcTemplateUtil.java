package cn.adwalker.ad.spring;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcTemplateUtil {
	public static long getId(String seqName, SimpleJdbcTemplate simpleJdbcTemplate) {
		String sql = "SELECT  SEQ_" + seqName + ".NEXTVAL FROM DUAL";
		return simpleJdbcTemplate.queryForLong(sql);
	}
}
