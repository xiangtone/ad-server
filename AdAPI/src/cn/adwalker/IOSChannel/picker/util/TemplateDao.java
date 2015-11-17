package cn.adwalker.IOSChannel.picker.util;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import cn.adwalker.IOSChannel.picker.statement.Statement;


public class TemplateDao extends SimpleJdbcTemplate{
	public static Logger logger = Logger.getLogger(TemplateDao.class);
	public TemplateDao(DataSource dataSource) {
		super(dataSource);
	}
    
	/**
	 * ��ѯ����list��ݼ�
	 * @param <T>
	 * @param stms
	 * @param toType
	 * @return
	 */
	public <T> List<T>  queryList(Statement stms,Class<T> toType){
		String sql = createSql(stms);
	    return stms.getParams().iterator().hasNext()? query(sql,  BeanPropertyRowMapper.newInstance(toType),stms.getParams().toArray()):query(sql,  BeanPropertyRowMapper.newInstance(toType));
	}
	
	/**
	 * 查询返回list数据集
	 * @param <T>
	 * @param stms
	 * @param toType
	 * @return
	 */
	public List<?>  queryListForObject(Statement stms,RowMapper<String[]> rm){
		String sql = createSql(stms);
		return stms.getParams().iterator().hasNext()? query(sql,rm ,stms.getParams().toArray()):query(sql, rm);
	}
	/**
	 * ��ҳ��ѯ
	 * @param <T>
	 * @param stms
	 * @param page
	 * @param toType
	 
	public <T> void search(Statement stms,Page page,Class<T> toType){
	    Integer count = findCount(stms);
	    page.setTotalRows(count);
		String sql = createPageSql(stms,page.getStartRow(),page.getPageSize());		
		List<T> list =stms.getParams().iterator().hasNext()? query(sql,  BeanPropertyRowMapper.newInstance(toType),stms.getParams().toArray()):query(sql,  BeanPropertyRowMapper.newInstance(toType));
		page.setList(list);
	}
	*/
	/**
	 * find Object
	 * @param <T>
	 * @param stms
	 * @param toType
	 * @return
	 */
	public <T> T findObject(Statement stms,Class<T> toType){
		String sql = createSql(stms);
		try{
			return stms.getParams().iterator().hasNext()?queryForObject(sql,BeanPropertyRowMapper.newInstance(toType),stms.getParams().toArray()):queryForObject(sql, BeanPropertyRowMapper.newInstance(toType));
		}catch(Exception e){
			logger.logInfo("spring jdbcTemplate 查不到数据抛出异常"+e.getMessage()+"sql:["+sql+"]");
			//spring jdbcTemplate在查不到数据是会抛异常.悲催啊.
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T findForObject(Statement stms,Class<T> toType){
		String sql = createSql(stms);
		try{
			Object obj = stms.getParams().iterator().hasNext()?queryForObject(sql,Object.class,stms.getParams().toArray()):queryForObject(sql, Object.class);
			return (T)obj;
		}catch(Exception e){
			e.printStackTrace();
			logger.logError("DataBaseException error:"+e.getMessage());
			return null;
		}
	}
	/**
	 * select count
	 * @param stms
	 * @return
	 */
	public Integer findCount(Statement stms){
		String sql = createCountSql(stms);
		return stms.getParams().iterator().hasNext()?queryForInt(sql):queryForInt(sql, stms.getParams().toArray());
	}
	/**
	 * select count
	 * @param stms
	 * @return
	 */
	public Integer findForInt(Statement stms){
		String sql = createSql(stms);
		return stms.getParams().iterator().hasNext()?queryForInt(sql):queryForInt(sql, stms.getParams().toArray());
	}

	/** insert update delete
	 * @param stms
	 * @return
	 */
	public Integer update(Statement  stms){
		String sql = createSql(stms);
	    return stms.getParams().iterator().hasNext()?update(sql, stms.getParams().toArray()):update(sql);
	}
	
	
	public String createCountSql(Statement stms){
		String sql = createSql(stms);
		StringBuffer sbuf = new StringBuffer(" SELECT count(1) FROM ( ");
		sbuf.append(sql);
		sbuf.append(" )" );
		return sbuf.toString();
	}
	
	public String createPageSql(Statement stms,int startRow,int pageSize){
		Integer maxRow = startRow+pageSize;
		String sql = createSql(stms);
		StringBuffer sbuf = new StringBuffer(" SELECT t.* FROM ( ");
		sbuf.append("SELECT temp.*,ROWNUM num FROM ( ");
		sbuf.append(sql);
		sbuf.append("��) temp where ROWNUM <= "+maxRow );
		sbuf.append("  )  t WHERE��t.num > " + startRow +"");
		return sbuf.toString();
	}

	public String createSql(Statement stms){
		return stms.toString();
	}
 
	
}
