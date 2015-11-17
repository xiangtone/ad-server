package cn.adwalker.ad.core.page;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.bind.ServletRequestUtils;

import cn.adwalker.ad.util.page.PageUtil;

public class SetPage implements IPageInfo, Serializable {

	private static final long serialVersionUID = 622998354912211950L;
	private int currentPage;
	private int pageSize;
	private String url;
	private String prefixUrl;
	private int totalRow;
	private String order;

	public SetPage(int pageIndex) {
		super();
		this.currentPage = pageIndex;
	}

	public SetPage() {
		super();
	}

	@SuppressWarnings({"rawtypes" })
	public SetPage(HttpServletRequest request) {
		super();
		// 当前第几页
		String str = ServletRequestUtils.getStringParameter(request, "pageIndex", "");
		if (!StringUtils.isEmpty(str) && NumberUtils.isNumber(str)) {
			currentPage = Integer.parseInt(ServletRequestUtils.getStringParameter(request, "pageIndex", ""));
		} else {
			currentPage = 1;
		}
		String pageRecord = request.getParameter("pageRecord");
		pageSize = StringUtils.isNotEmpty(pageRecord) ? Integer.parseInt(pageRecord) : PageUtil.defaultPageRecord;
		StringBuilder sb = new StringBuilder();
		Map<String, String[]> map = request.getParameterMap();
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String stype = (String) e.nextElement();
			if (!stype.equals("pageIndex") && !stype.equals("pageRecord") && !stype.equals("order")) {
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

	@Override
	public int getCurrentPage() {
		if (currentPage <= 0) {
			this.currentPage = 1;
		}
		return this.currentPage;
	}

	@Override
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

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

	@Override
	public void setCurrentPage(int page) {

	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public int getTotalRow() {
		return totalRow;
	}

	@Override
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

	@Override
	public String getPagingParam() {
		return "pageIndex";
	}

	@Override
	public int getMenuLen() {
		return 0;
	}

	@Override
	public String getPageInfoStr() {
		return PageUtil.fenye(this);
	}

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
	public String getUrl() {
		return url;
	}

}
