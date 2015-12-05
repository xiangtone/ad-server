package cn.adwalker.core.util;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.core.page.IPageInfo;

/**
 *  
 */
public class PageUtil {

	public static final int defaultPageRecord = 10;

	private static final String SEPARATOR_STRING = "&nbsp;&nbsp;";

	/**
	 * 左右分页< 1 2 3 4 5 >
	 * 
	 * @param page
	 * @return
	 */
	public static String fenye(IPageInfo page) {
		int totalPageCount = page.getTotalPage();
		int currentPageIndex = page.getCurrentPage();
		String prefixUrl = page.getPrefixUrl();
		String separator = SEPARATOR_STRING;
		String pagingParam = page.getPagingParam();

		String pageRecordStr = "&pageRecord=" + page.getPageSize();
		String orderStr = "";
		if (!StringUtils.isEmpty(page.getOrderParam())) {
			orderStr = "&order=" + page.getOrderParam();

		}

		int menuLen = page.getMenuLen();
		int recordCount = page.getTotalRow();
		// ==========================得到参数
		if (menuLen < 1) {
			menuLen = 5;
		}

		if (totalPageCount == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();

		// 每页显示条数
		String cif1 = "";
		String cif2 = "";
		String cif3 = "";
		String cif5 = "";
		String cif10 = "";
		String cif15 = "";
		if (page.getPageSize() == 10) {
			cif1 = "selected='selected'";
		}
		if (page.getPageSize() == 20) {
			cif2 = "selected='selected'";
		}
		if (page.getPageSize() == 30) {
			cif3 = "selected='selected'";
		}
		if (page.getPageSize() == 50) {
			cif5 = "selected='selected'";
		}
		if (page.getPageSize() == 100) {
			cif10 = "selected='selected'";
		}
		if (page.getPageSize() == 150) {
			cif15 = "selected='selected'";
		}

		sb.append(
				" <table width='100%' cellpadding='0' cellspacing='1'><tr><td align='left'>每页显示<select name='pageRecord' id='pageRecord' onchange=javascript:goSelect(this.options[this.options.selectedIndex].value) >")
				.append(" <option value='10'").append(cif1)
				.append(" >10</option>").append(" <option value='20'")
				.append(cif2).append(" >20</option>")
				.append(" <option value='30'").append(cif3)
				.append(" >30</option>").append(" <option value='50'")
				.append(cif5).append(" >50</option>");
		sb.append(" <option value='100'").append(cif10)
				.append(" >100</option>");
		sb.append(" <option value='150'").append(cif15)
				.append(" >150</option>");
		sb.append("</select>记录</td><td align='right'>");

		if (!StringUtils.isEmpty(prefixUrl)) {
			if (prefixUrl.indexOf("?") != -1) {
				prefixUrl += "&" + pagingParam + "=";
			} else {
				prefixUrl += "?" + pagingParam + "=";
			}
		}

		int startIndex = ((currentPageIndex - 1) / menuLen) * menuLen;
		int endIndex = ((currentPageIndex - 1) / menuLen + 1) * menuLen - 1;
		if (endIndex > totalPageCount - 1) {
			endIndex = totalPageCount - 1;
		}

		// 分页条数
		sb.append(separator);
		sb.append("结果共" + recordCount + "项");
		sb.append(separator);
		sb.append("共" + totalPageCount + "页");

		// 首页
		sb.append("&nbsp;&nbsp;<a href=\"" + prefixUrl + 1 + pageRecordStr
				+ orderStr);
		sb.append("\">首页</a>");

		if (currentPageIndex - 1 != 0) {
			sb.append("<a href=\"" + prefixUrl + (currentPageIndex - 1)
					+ pageRecordStr + orderStr);
			sb.append("\">上一页</a>");
		}

		for (int i = startIndex; i <= endIndex; i++) {
			if (sb.length() > 0) {
				sb.append(separator);
			}
			if (i != currentPageIndex - 1) {
				sb.append("<a href=\"" + prefixUrl + (i + 1) + pageRecordStr
						+ orderStr);
				sb.append("\">" + (i + 1) + "</a>");
			} else {
				sb.append("<font color=red>" + (i + 1) + "</font>");
			}
		}

		if (currentPageIndex != totalPageCount) {
			if (sb.length() > 0) {
				sb.append(separator);
			}
			sb.append("<a href=\"" + prefixUrl + (currentPageIndex + 1)
					+ pageRecordStr + orderStr);
			sb.append("\">下一页</a>");
		}
		// 尾页
		sb.append("&nbsp;&nbsp;<a href=\"" + prefixUrl + (totalPageCount)
				+ pageRecordStr + orderStr);
		sb.append("\">尾页</a>");
		sb.append("</td></tr></table>");
		sb.append(" <!--动态添加option-->")
				.append("<script type='text/javascript' charset='utf-8'>")
				.append("function goSelect(num){")
				.append(" window.location.href='" + prefixUrl + 1
						+ "&pageRecord='+num;").append("  }")
				.append(" </script>");

		return sb.toString();
	}
}
