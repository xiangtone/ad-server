/**
 * <p>Title: IPageInfor.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-1-16
 * @version 1.0
 */
package cn.adwalker.core.page;

/**
 * <p>
 * Title: IPageInfor
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-1-16
 */
public interface IPageInfo {

	/**
	 * 
	 * <p>
	 * Title: getCurrentPage
	 * </p>
	 * <p>
	 * Description:获取当前页
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-17
	 * @return int
	 * @version 1.0
	 */
	public int getCurrentPage();

	/**
	 * 
	 * <p>
	 * Title: getPrefixUrl
	 * </p>
	 * <p>
	 * Description:获取请求路径及系统参数
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-17
	 * @return String
	 * @version 1.0
	 */
	public String getPrefixUrl();

	/**
	 * 
	 * <p>
	 * Title: getUrl
	 * </p>
	 * <p>
	 * Description:获取路径
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-17
	 * @return String
	 * @version 1.0
	 */
	public String getUrl();

	/**
	 * 
	 * <p>
	 * Title: setTotalRow
	 * </p>
	 * <p>
	 * Description:设置一共xx行
	 * </p>
	 * 
	 * @param totalRow
	 * @author cuidd
	 * @date 2013-7-17
	 * @return void
	 * @version 1.0
	 */
	public void setTotalRow(Integer totalRow);

	/**
	 * <p>
	 * Title: getTotalPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-1-16
	 * @return int
	 * @version 1.0
	 */
	public int getTotalPage();

	/**
	 * <p>
	 * Title: setPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param page
	 * @author cuidd
	 * @date 2013-1-16
	 * @return void
	 * @version 1.0
	 */
	public void setCurrentPage(int page);

	/**
	 * <p>
	 * Title: getPageSize
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-1-16
	 * @return Object
	 * @version 1.0
	 */
	public int getPageSize();

	/**
	 * 
	 * <p>
	 * Title: getTotalRow
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-1-16
	 * @return int
	 * @version 1.0
	 */
	public abstract int getTotalRow();

	/**
	 * <p>
	 * Title: getPagingParam
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-1-16
	 * @return String
	 * @version 1.0
	 */
	public String getPagingParam();

	/**
	 * <p>
	 * Title: getMenuLen
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-1-16
	 * @return int
	 * @version 1.0
	 */
	public int getMenuLen();

	/**
	 * 
	 * <p>
	 * Title: getPageInfoStr
	 * </p>
	 * <p>
	 * Description:获取分页字符串
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-15
	 * @return String
	 * @version 1.0
	 */
	public String getPageInfoStr();

	/**
	 * 
	 * <p>
	 * Title: getOrder
	 * </p>
	 * <p>
	 * Description:获取排序信息
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-17
	 * @return String
	 * @version 1.0
	 */
	public String getOrderParam();
	
	
	public String getOrder();
}
