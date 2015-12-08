package cn.adwalker.ad.dao;

import javax.annotation.Resource;

import cn.adwalker.ad.picker.dao.WalkerJdbcTemplate;
import cn.adwalker.ad.picker.statement.StatementFactory;
import cn.adwalker.ad.picker.statement.mysql.StatementFactoryImpl;

public class CommonDao {
	@Resource
	public WalkerJdbcTemplate simpleJdbcTemplate;

	@Resource(type = StatementFactoryImpl.class)
	public StatementFactory stmsFactory;
}
