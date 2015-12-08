package cn.adwalker.ad.picker.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import cn.adwalker.ad.picker.statement.Statement;
import cn.adwalker.ad.picker.util.Column;
import cn.adwalker.ad.picker.util.Table;


public class WalkerJdbcTemplate extends SimpleJdbcTemplate{
	Logger logger = Logger.getLogger(WalkerJdbcTemplate.class);
	
	public WalkerJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
    
	/**
	 * 查询返回list数据集
	 * @param <T>
	 * @param stms
	 * @param toType
	 * @return
	 */
	public <T> List<T>  queryList(Statement stms,Class<T> toType){
		String sql = createSql(stms);
	    return stms.getParams().iterator().hasNext()? query(sql,  BeanPropertyRowMapper.newInstance(toType),stms.getParams().toArray()):query(sql,  BeanPropertyRowMapper.newInstance(toType));
	}
	
	
	public  List<?>  findAll(Statement stms,Class<?> toType){
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
	public List<?>  queryListForObject(Statement stms,RowMapper<String> rm){
		String sql = createSql(stms);
		/**
		ParameterizedRowMapper rm = new ParameterizedRowMapper<String>() {
            public String mapRow(ResultSet rs, int index)throws SQLException {
            return rs.getString(1); 
        }
		};*/
		return stms.getParams().iterator().hasNext()? query(sql,rm ,stms.getParams().toArray()):query(sql, rm);
	}
	/**
	 * 分页查询
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
			//e.printStackTrace();
			logger.error("DataBaseException error:"+e.getMessage());
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
			//e.printStackTrace();
			logger.error("DataBaseException error:"+e.getMessage());
			return null;
		}
	}
	
	public <T> Integer queryCount(Statement stms){
		String sql = createSql(stms);
		try{
			return stms.getParams().iterator().hasNext()?queryForInt(sql,stms.getParams().toArray()):queryForInt(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
    public <T> T saveObject(Object obj){
		Table t = obj.getClass().getAnnotation(Table.class);
		StringBuffer sbuf = new StringBuffer("INSERT INTO ");
		StringBuffer colBuf = new StringBuffer("(");
		StringBuffer values = new StringBuffer(" VALUES (");
		List<Object> list = new ArrayList<Object>();
		if(null!=t){
			String table = t.table();
			sbuf.append(" "+table +" ");
			Field[] fs = obj.getClass().getFields();
			boolean b = true;
			for(Field f:fs){
				Column c = f.getAnnotation(Column.class);
				if(c!=null){
					String seq = c.seq();
					if(!"".equals(seq)){
						 colBuf.append(b?c.column():(" ,"+c.column()));
						 values.append(b?seq+".NEXTVAL ":" ,"+seq+".NEXTVAL ");
						 if(b){b=false;}
					}else{
						 colBuf.append(b?c.column():(" ,"+c.column()));
						 values.append(b?" ? ":" ,? ");
						 if(b){b=false;}
						 list.add(getMethodValue(obj, f.getName()));
					}
				   
				}
				
			}
			colBuf.append(") ");
			values.append(") ");
			sbuf.append(colBuf.toString());
			sbuf.append(values.toString());
			logger.info(sbuf.toString());
			
			if(!b){
				//System.out.println(list.size());
				update(sbuf.toString(),list.toArray());
				//queryForRowSet(sbuf.toString(), list);
			}	
		}else{
			logger.info("Annotation Table Null.");
		}
		
		
		
	  	return null;
	}
    private Object getMethodValue(Object obj,String fieldName){
    	StringBuffer sb = new StringBuffer();       
        sb.append("get");       
        sb.append(fieldName.substring(0, 1).toUpperCase());       
        sb.append(fieldName.substring(1));  
        try{
        	 Method m = obj.getClass().getMethod(sb.toString());
        	 return m.invoke(obj, null);
        }catch(Exception e){
        	logger.info(e.getMessage());
        }
        return null;
    }
    
	/**
	 * select count
	 * @param stms
	 * @return
	 */
	public Integer findCount(Statement stms){
		String sql = createCountSql(stms);
		return stms.getParams().iterator().hasNext()?queryForInt(sql, stms.getParams().toArray()):queryForInt(sql);
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
		StringBuffer sbuf = new StringBuffer(" SELECT count(*) FROM ( ");
		sbuf.append(sql);
		sbuf.append("　)" );
		return sbuf.toString();
	}
	
	public String createPageSql(Statement stms,int startRow,int pageSize){
		Integer maxRow = startRow+pageSize;
		String sql = createSql(stms);
		StringBuffer sbuf = new StringBuffer(" SELECT t.* FROM ( ");
		sbuf.append("SELECT temp.*,ROWNUM num FROM ( ");
		sbuf.append(sql);
		sbuf.append("　) temp where ROWNUM <= "+maxRow );
		sbuf.append("  )  t WHERE　t.num > " + startRow +"");
		return sbuf.toString();
	}

	
	public String createSql(Statement stms){
		logger.info(stms.toString());
		return stms.toString();
	}
	
 
	
}
