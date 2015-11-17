package cn.adwalker.core.tag;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 功能:拼接表头排序图片链接
 * 
 * @author winnie
 * 
 */
public class OrderTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7669257183419521888L;

	private final static Logger logger = Logger.getLogger(OrderTag.class);

	String url;
	String prefix;
	String orderName;
	String orderMode;

	String base;
	Map map = null;

	public Map getMap() {
		return this.map;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderMode() {
		return orderMode;
	}

	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public int doStartTag() throws JspTagException {
		map = new HashMap();
		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() throws JspTagException {
		return SKIP_BODY;
	}

	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
		/** ****** 生成URL参数 ******* */
		String parameters = "";
		if (map != null && map.size() > 0) {
			Iterator<Map.Entry> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = iter.next();
				String key = String.valueOf(entry.getKey());
				String val = String.valueOf(entry.getValue());
				if (StringUtils.isBlank(val)) {
					val = "";
				}
				try {
					val = URLEncoder.encode(val.toString(), "utf-8");
				} catch (Exception e) {
					logger.error("对参数进行Encode出错:", e);
				}
				parameters += key + "=" + val + "&";
			}
		}

		try {
			if (StringUtils.isEmpty(url)) {
				pageContext.getOut().write("");
				return EVAL_PAGE;

			}
			StringBuilder sb = new StringBuilder(url);
			if (url.indexOf("?") != -1) {
				sb.append("&");

			} else {
				sb.append("?");
			}

			// 如果没有任何数据,不显示排序图片链接"
			if (!StringUtils.isEmpty(parameters)) {
				sb.append(parameters);// order=t.dev_id|desc
			}
			String str_asc = sb.toString() + "order=" + orderName + "|asc";
			String str_desc = sb.toString() + "order=" + orderName + "|desc";

			StringBuilder html = new StringBuilder();
			html.append("<img class=\"img01\"  id=\""
					+ replacOrderName(orderName)
					+ "_0\" src=\""
					+ base
					+ "/images/sys/sort_desc.gif\" alt=\"\" onclick=\"changeOrder(this);\" ref=\"\" url=\""
					+ str_asc + "\" />");
			html.append("<img class=\"img01\" id=\""
					+ replacOrderName(orderName)
					+ "_1\" src=\""
					+ base
					+ "/images/sys/sort_asc.gif\" alt=\"\"  onclick=\"changeOrder(this);\" ref=\"\" url=\""
					+ str_desc + "\" />");
			pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			logger.error("拼接排序图片链接出错", e);
		}
		return EVAL_PAGE;
	}

	private String replacOrderName(String orderNum) {
		String str = null;
		if (!StringUtils.isEmpty(orderNum)) {
			if (orderNum.indexOf(".") != -1) {
				String arr[] = orderNum.split("\\.");
				str = arr[0] + "_" + arr[1];

			} else {
				str = orderNum;
			}

		}
		return str;

	}

	public void doInitBody() throws JspTagException {

	}

	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}

}