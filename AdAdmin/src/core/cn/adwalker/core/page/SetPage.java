/**
 * <p>Title: StdPage.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-1-16
 * @version 1.0
 */
package cn.adwalker.core.page;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.bind.ServletRequestUtils;

import cn.adwalker.core.util.PageUtil;

/**
 * <p>
 * Title: StdPage
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
public class SetPage implements IPageInfo, Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 622998354912211950L;
	private int currentPage;
	private int pageSize;
	private String url;
	private String prefixUrl;
	private int totalRow;
	private String order;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param pageIndex
	 */
	public SetPage(int pageIndex) {
		super();
		this.currentPage = pageIndex;
	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 */
	public SetPage() {
		super();
	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 */

	public SetPage(HttpServletRequest request) {
		super();
		init(request, null);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init(HttpServletRequest request, Integer _pageSize) {
		// 当前第几页
		String str = ServletRequestUtils.getStringParameter(request,
				"pageIndex", "");
		if (!StringUtils.isEmpty(str) && NumberUtils.isNumber(str)) {
			currentPage = Integer.parseInt(ServletRequestUtils
					.getStringParameter(request, "pageIndex", ""));
		} else {
			currentPage = 1;
		}
		String pageRecord = request.getParameter("pageRecord");
		pageSize = StringUtils.isNotEmpty(pageRecord) ? Integer
				.parseInt(pageRecord) : (_pageSize != null ? _pageSize
				: PageUtil.defaultPageRecord);
		StringBuilder sb = new StringBuilder();
		Map<String, String[]> map = request.getParameterMap();
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String stype = (String) e.nextElement();
			if (!stype.equals("pageIndex") && !stype.equals("pageRecord")
					&& !stype.equals("order")) {
				String arr[] = map.get(stype);
				if (arr != null && arr.length > 0) {
					for (int i = 0; i < arr.length; i++) {
						sb.append(stype).append("=").append(arr[i]).append("&");
					}
				}

			}
		}
		String prefixUrl = request.getRequestURI();
		if (sb.length() > 0) {
			sb = sb.deleteCharAt(sb.length() - 1);
			if (prefixUrl.indexOf("?") != -1) {
				prefixUrl = prefixUrl + "&" + sb.toString();
			} else {
				prefixUrl = prefixUrl + "?" + sb.toString();
			}
		}
		this.prefixUrl = prefixUrl;// url前缀
		this.url = addParam(this.prefixUrl, "pageRecord=" + pageSize);
		order = ServletRequestUtils.getStringParameter(request, "order", "");

	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 */
	public SetPage(HttpServletRequest request, Integer pageSize) {
		super();
		init(request, pageSize);

	}

	private String addParam(String url, String param) {
		String s = null;
		if (!StringUtils.isEmpty(url)) {
			if (!StringUtils.isEmpty(param)) {
				if (url.indexOf("?") != -1) {
					s = url + "&" + param;
				} else {
					s = url + "?" + param;
				}
			}
		}
		return s;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getPage()
	 */
	@Override
	public int getCurrentPage() {
		if (currentPage <= 0) {
			this.currentPage = 1;
		}
		return this.currentPage;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: setTotalRow
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param totalRow
	 * @see cn.adwalker.core.page.IPageInfo#setTotalRow(java.lang.Integer)
	 */
	@Override
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getTotalPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getTotalPage()
	 */
	@Override
	public int getTotalPage() {
		if (pageSize == 0) {
			pageSize = 10;
		}
		if (this.totalRow % this.pageSize == 0) {
			return (this.totalRow / this.pageSize);
		}
		return (this.totalRow / this.pageSize + 1);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: setPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param page
	 * @see cn.adwalker.core.page.IPageInfo#setPage(int)
	 */
	@Override
	public void setCurrentPage(int page) {

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPageSize
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getPageSize()
	 */
	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getTotalRow
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getTotalRow()
	 */
	@Override
	public int getTotalRow() {
		return totalRow;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPrefixUrl
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getPrefixUrl()
	 */
	@Override
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPagingParam
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getPagingParam()
	 */
	@Override
	public String getPagingParam() {
		return "pageIndex";
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getMenuLen
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getMenuLen()
	 */
	@Override
	public int getMenuLen() {
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPageInfoStr
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getPageInfoStr()
	 */
	@Override
	public String getPageInfoStr() {
		return PageUtil.fenye(this);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getOrder
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getOrder()
	 */
	@Override
	public String getOrder() {
		String str = null;
		if (!StringUtils.isEmpty(order) && order.indexOf("|") != -1) {
			String arr[] = order.split("\\|");
			str = "order by " + arr[0] + " " + arr[1];

		}
		return str;
	}
	
	
	@Override
	public String getOrderParam() {
		return order;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getUrl
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.core.page.IPageInfo#getUrl()
	 */
	@Override
	public String getUrl() {
		return url;
	}

}
