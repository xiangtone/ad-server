package cn.adwalker.IOSChannel.picker.dao;

import javax.annotation.Resource;

import cn.adwalker.IOSChannel.picker.statement.StatementFactory;
import cn.adwalker.IOSChannel.picker.util.TemplateDao;

public class CommonDao {
	
	@Resource
	public StatementFactory stmsFactory;
	
	@Resource
	public TemplateDao simpleJdbcTemplate;
    
	
	
}
