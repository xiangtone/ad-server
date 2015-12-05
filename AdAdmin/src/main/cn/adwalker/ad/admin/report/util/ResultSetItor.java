package cn.adwalker.ad.admin.report.util;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;
/**
 * <p>采用游标的方式导出数据，防止一次性导出内存溢出</p>
 * @author jef
 * @param <T>
 */
public class ResultSetItor<T> {
	private Logger logger=Logger.getLogger(ResultSetItor.class);
	private ResultSet rs;
	private Connection conn;
	private Statement sta;
	private String sql;
	private Class<T> mappedClass;
	/** Whether we're strictly validating */
	private boolean checkFullyPopulated = false;
	/** Whether we're defaulting primitives when mapping a null value */
	private boolean primitivesDefaultedForNullValue = false;

	/** Map of the fields we provide mapping for */
	private Map<String, PropertyDescriptor> mappedFields;

	/** Set of bean properties we provide mapping for */
	private Set<String> mappedProperties;
	public ResultSetItor(Class<T> mappedClass,Connection conn,String sql) throws Exception{
		this.conn=conn;
		this.mappedClass=mappedClass;
		this.sql=sql;
		initialize(mappedClass);
	}
	/**
	 * <p></p>
	 * @return
	 * @throws SQLException
	 */
	public boolean next()throws SQLException{
		return rs.next();
	}
	/**
	 * <p>关闭数据库连接</p>
	 */
	public void close(){
		try {
			if(this.rs!=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(this.sta!=null)
				sta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(this.conn!=null)
			  conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public T getNext() throws Exception{
		Assert.state(this.mappedClass != null, "Mapped class was not specified");
		T mappedObject = BeanUtils.instantiate(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
		initBeanWrapper(bw);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Set<String> populatedProperties = (isCheckFullyPopulated() ? new HashSet<String>() : null);

		for (int index = 1; index <= columnCount; index++) {
			String column = JdbcUtils.lookupColumnName(rsmd, index);
			PropertyDescriptor pd = this.mappedFields.get(column.replaceAll(" ", "").toLowerCase());
			if (pd != null) {
				try {
					Object value = getColumnValue(rs, index, pd);
//					if (logger.isDebugEnabled() && rowNumber == 0) {
//						logger.debug("Mapping column '" + column + "' to property '" +
//								pd.getName() + "' of type " + pd.getPropertyType());
//					}
					try {
						bw.setPropertyValue(pd.getName(), value);
					}
					catch (TypeMismatchException e) {
						if (value == null && primitivesDefaultedForNullValue) {
//							logger.debug("Intercepted TypeMismatchException for row " + rowNumber +
//									" and column '" + column + "' with value " + value +
//									" when setting property '" + pd.getName() + "' of type " + pd.getPropertyType() +
//									" on object: " + mappedObject);
						}
						else {
							throw e;
						}
					}
					if (populatedProperties != null) {
						populatedProperties.add(pd.getName());
					}
				}
				catch (NotWritablePropertyException ex) {
					throw new DataRetrievalFailureException(
							"Unable to map column " + column + " to property " + pd.getName(), ex);
				}
			}
		}

		if (populatedProperties != null && !populatedProperties.equals(this.mappedProperties)) {
			throw new InvalidDataAccessApiUsageException("Given ResultSet does not contain all fields " +
					"necessary to populate object of class [" + this.mappedClass + "]: " + this.mappedProperties);
		}

		return mappedObject;
	}
	
	/**
	 * Return whether we're strictly validating that all bean properties have been
	 * mapped from corresponding database fields.
	 */
	public boolean isCheckFullyPopulated() {
		return this.checkFullyPopulated;
	}
	/**
	 * Initialize the mapping metadata for the given class.
	 * @param mappedClass the mapped class.
	 */
	protected void initialize(Class<T> mappedClass)throws Exception {
		this.mappedClass = mappedClass;
		this.mappedFields = new HashMap<String, PropertyDescriptor>();
		this.mappedProperties = new HashSet<String>();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null) {
				this.mappedFields.put(pd.getName().toLowerCase(), pd);
				String underscoredName = underscoreName(pd.getName());
				if (!pd.getName().toLowerCase().equals(underscoredName)) {
					this.mappedFields.put(underscoredName, pd);
				}
				this.mappedProperties.add(pd.getName());
			}
		}
		this.sta=this.conn.createStatement();
		this.rs=sta.executeQuery(this.sql);	
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	private String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			result.append(name.substring(0, 1).toLowerCase());
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase())) {
					result.append("_");
					result.append(s.toLowerCase());
				}
				else {
					result.append(s);
				}
			}
		}
		return result.toString();
	}
	/**
	 * 
	 * @param rs
	 * @param index
	 * @param pd
	 * @return
	 * @throws SQLException
	 */
	protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
		return JdbcUtils.getResultSetValue(rs, index, pd.getPropertyType());
	}
	/**
	 * 
	 * @param bw
	 */
	protected void initBeanWrapper(BeanWrapper bw) {
	}
	
	
	public static void main(String args[]){
		
	}
	
}
