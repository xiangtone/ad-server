package cn.adwalker.ad.util.page;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.model.page.domain.PageInfo;

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
	@Deprecated
	public static String fenye(PageInfo page) {
		int totalPageCount = page.getTotalPage();
		int currentPageIndex = page.getCurrentPage();
		String prefixUrl = page.getPrefixUrl();
		String separator = page.getSeparator();
		String pagingParam = page.getPagingParam();

		String pageRecordStr = "&pageRecord=" + page.getPageSize();

		int pageCount = page.getPageCount();
		int recordCount = page.getRecordCount();
		// ==========================得到参数
		if (pageCount < 1) {
			pageCount = 5;
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

		sb.append(
				" <table width='100%' cellpadding='0' cellspacing='1'><tr><td align='left'>每页显示<select name='pageRecord' id='pageRecord' onchange=javascript:goSelect(this.options[this.options.selectedIndex].value) >")
				.append(" <option value='10'").append(cif1)
				.append(" >10</option>").append(" <option value='20'")
				.append(cif2).append(" >20</option>")
				.append(" <option value='30'").append(cif3)
				.append(" >30</option>").append(" <option value='50'")
				.append(cif5).append(" >50</option>")
				.append(" <option value='100'").append(cif10)
				.append(" >100</option></select>记录</td><td align='right'>");

		if (prefixUrl.indexOf("?") != -1) {
			prefixUrl += "&amp;" + pagingParam + "=";
		} else {
			prefixUrl += "?" + pagingParam + "=";
		}

		int startIndex = ((currentPageIndex - 1) / pageCount) * pageCount;
		int endIndex = ((currentPageIndex - 1) / pageCount + 1) * pageCount - 1;
		if (endIndex > totalPageCount - 1) {
			endIndex = totalPageCount - 1;
		}

		// 分页条数
		sb.append(separator);
		sb.append("结果共" + recordCount + "项");
		sb.append(separator);
		sb.append("共" + totalPageCount + "页");

		// 首页
		sb.append("&nbsp;&nbsp;<a href=\"" + prefixUrl + 1 + pageRecordStr);
		sb.append("\">首页</a>");

		if (currentPageIndex - 1 != 0) {
			sb.append("<a href=\"" + prefixUrl + (currentPageIndex - 1)
					+ pageRecordStr);
			sb.append("\">上一页</a>");
		}/*
		 * else{ sb.append("上一页"); }
		 */

		for (int i = startIndex; i <= endIndex; i++) {
			if (sb.length() > 0) {
				sb.append(separator);
			}
			if (i != currentPageIndex - 1) {
				sb.append("<a href=\"" + prefixUrl + (i + 1) + pageRecordStr);
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
					+ pageRecordStr);
			sb.append("\">下一页</a>");
		}
		// 尾页
		sb.append("&nbsp;&nbsp;<a href=\"" + prefixUrl + (totalPageCount)
				+ pageRecordStr);
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

		sb.append(
				" <table width='100%' cellpadding='0' cellspacing='1'><tr><td align='left'>每页显示<select name='pageRecord' id='pageRecord' onchange=javascript:goSelect(this.options[this.options.selectedIndex].value) >")
				.append(" <option value='10'").append(cif1)
				.append(" >10</option>").append(" <option value='20'")
				.append(cif2).append(" >20</option>")
				.append(" <option value='30'").append(cif3)
				.append(" >30</option>").append(" <option value='50'")
				.append(cif5).append(" >50</option>")
				.append(" <option value='100'").append(cif10)
				.append(" >100</option></select>记录</td><td align='right'>");

		if (!StringUtils.isEmpty(prefixUrl)) {
			if (prefixUrl.indexOf("?") != -1) {
				prefixUrl += "&amp;" + pagingParam + "=";
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
		sb.append("&nbsp;&nbsp;<a href=\"" + prefixUrl + 1 + pageRecordStr);
		sb.append("\">首页</a>");

		if (currentPageIndex - 1 != 0) {
			sb.append("<a href=\"" + prefixUrl + (currentPageIndex - 1)
					+ pageRecordStr);
			sb.append("\">上一页</a>");
		}

		for (int i = startIndex; i <= endIndex; i++) {
			if (sb.length() > 0) {
				sb.append(separator);
			}
			if (i != currentPageIndex - 1) {
				sb.append("<a href=\"" + prefixUrl + (i + 1) + pageRecordStr);
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
					+ pageRecordStr);
			sb.append("\">下一页</a>");
		}
		// 尾页
		sb.append("&nbsp;&nbsp;<a href=\"" + prefixUrl + (totalPageCount)
				+ pageRecordStr);
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
	

	/**
	 * 左右分页< 1 2 3 4 5 >
	 * 
	 * @param page
	 * @return
	 */
	public static String fenyeNew(IPageInfo page) {
		int totalPageCount = page.getTotalPage();
		int currentPageIndex = page.getCurrentPage();
		String prefixUrl = page.getPrefixUrl();
		String pagingParam = page.getPagingParam();
		String pageRecordStr = "&pageRecord=" + page.getPageSize();
		int menuLen = page.getMenuLen();
		if (menuLen < 1) {
			menuLen = 5;
		}
		if (totalPageCount == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if (!StringUtils.isEmpty(prefixUrl)) {
			if (prefixUrl.indexOf("?") != -1) {
				prefixUrl += "&amp;" + pagingParam + "=";
			} else {
				prefixUrl += "?" + pagingParam + "=";
			}
		}
		int startIndex = currentPageIndex - 3;
		if(startIndex < 1) {
			startIndex = 1;
		}
		int endIndex = currentPageIndex + 3;
		if(endIndex > totalPageCount) {
			endIndex = totalPageCount;
		}
		int previousIndex = currentPageIndex - 1;
		sb.append("<ul class=\"pagination\">");
		if(previousIndex >= 1) {
			sb.append("<li><a href=\"").append(prefixUrl).append(previousIndex).append(pageRecordStr).append("\">&laquo;</a></li>");
		}
		for(int i = startIndex; i<= endIndex; i++) {
			if(i == currentPageIndex) {
				sb.append("<li class=\"active\"><a href=\"").append(prefixUrl).append(i).append(pageRecordStr).append("\">").append(i).append("</a></li>");
			} else {
				sb.append("<li><a href=\"").append(prefixUrl).append(i).append(pageRecordStr).append("\">").append(i).append("</a></li>");
			}
		}
		int nextIndex = currentPageIndex + 1;
		if(nextIndex <= endIndex) {
			sb.append("<li><a href=\"").append(prefixUrl).append(nextIndex + 1).append(pageRecordStr).append("\">&raquo;</a></li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}
	
}