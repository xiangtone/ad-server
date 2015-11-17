package cn.adwalker.IOSChannel.picker.statement.mysql;

import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.picker.statement.Statement;
import cn.adwalker.IOSChannel.picker.statement.StatementFactory;


@Service("stmsFactory")
public class StatementFactoryImpl implements StatementFactory {

	public Statement createNativeStatement(String sql) {
		Statement stmt = new StatementImpl(sql);
		stmt.setNativeSQL(true);
		return stmt;
	}

	public Statement createNativeStatement(String sql, String where) {
		Statement stmt = new StatementImpl(sql, where);
		stmt.setNativeSQL(true);
		return stmt;
	}

}
