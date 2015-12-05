package cn.adwalker.core.tag;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

/**
 * 功能:拼友好型URL
 * 
 * @author Administrator
 * 
 */
public class PaginationWeb extends BodyTagSupport {
	private final static Logger logger = Logger.getLogger(PaginationWeb.class);

	private static final long serialVersionUID = -3490671092671580552L;

	String url;

	int currentPage; // 当前要显示第几页
	int pageNum = 9; // 每页中要显示多少个页面链接
	int totalPage; // 共有多少页

	Map map = null;

	public void setUrl(String c) {
		this.url = c;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setTotalPage(int totalSize) {
		this.totalPage = totalSize;
	}

	public Map getMap() {
		return this.map;
	}

	public int doStartTag() throws JspTagException {
		map = new HashMap();

		return EVAL_BODY_INCLUDE;

		// return SKIP_BODY;

	}

	public int doAfterBody() throws JspTagException {

		return SKIP_BODY;

	}

	public int doEndTag() throws JspException {
		/******** 生成URL参数 ********/
		Object[] obj = map.keySet().toArray();
		String parameters = "";

		Object paraObj = ""; // 单个参数
		for (int i = 0; i < obj.length; i++) {
			// 对参数进行URL Encode(目的是为了防止汉字在翻页时出现乱码)
			paraObj = map.get(obj[i]); // 得到单个参数
			if (paraObj == null)
				paraObj = "";
			try {
				paraObj = URLEncoder.encode(paraObj.toString(), "utf-8");
			} catch (Exception e) {
				logger.error("对参数进行Encode出错:", e);
			}

			parameters = parameters + obj[i] + "=" + paraObj + "&";
		}

		/****** 生成页码 ******/
		int start = 1; // 本页中的开始页码
		int end = 1; // 结束页码

		int temp = (int) Math.floor(pageNum / 2); // 用于算当前页离end和start页的距离
		if (pageNum >= totalPage) {
			start = 1;
			end = totalPage;
		} else if (temp + currentPage < totalPage) {
			if (currentPage <= temp) {
				start = 1;
				end = pageNum;
			} else {
				start = currentPage - temp;
				end = currentPage + temp;
			}
		} else if (temp + currentPage >= totalPage) {
			start = totalPage - pageNum + 1;
			end = totalPage;
		}

		String str = "<br/><div style=\"width:580px;text-align:center\">对不起,没有找到符合条件的数据!</div>";

		try {

			// 如果没有任何数据,则显示"没有找到符合条件的数据库"
			if (totalPage == 0) {
				pageContext.getOut().write(str);
				return EVAL_PAGE;
			}
			if (totalPage == 1) {
				return EVAL_PAGE;
			}

			StringBuffer strB = new StringBuffer("");
			strB.append("<div align='center'>");
			if (currentPage == 1) {
				strB.append("首页");
			} else {
				strB.append("<a href='" + url + "?" + parameters
						+ "currentPage=1'>首页</a>");
			}
			strB.append("&nbsp;&nbsp;");

			for (int i = start; i <= end; i++) {
				if (i != currentPage) {
					strB.append("<a href='" + url + "?" + parameters
							+ "currentPage=" + i + "'>[" + i
							+ "]</a>&nbsp;&nbsp;");
				} else {
					strB.append("<font color=\"#ff0000\">" + i
							+ "</font>&nbsp;&nbsp;");
				}
			}

			// strB.append("  ...  (共").append(totalPage).append("页)&nbsp&nbsp");

			if (currentPage == totalPage) {
				strB.append("尾页");
			} else {
				strB.append(
						"<a href='" + url + "?" + parameters + "currentPage="
								+ totalPage).append("'>尾页</a>");
			}

			strB.append("</div>");
			pageContext.getOut().write(strB.toString());
			pageContext.getOut().write("<br class='clear'/>");
		} catch (IOException e) {
			logger.error("分页出错", e);
		}

		return EVAL_PAGE;

	}

	public void doInitBody() throws JspTagException {

	}

	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}

}
