/*
 * FormatNumberTag.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2012-3-27
 */
package cn.adwalker.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import cn.adwalker.core.util.lang.StringUtil;

/**
 * 功能描述<br>
 * 金额格式化标签
 * 
 * @author liwei
 */
public class SubStringTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	/** Numeric value to be formatted. */
	private String value;

	/**
	 * Maximum number of digits in the fractional portion of the formatted
	 * output.
	 */
	private Integer len;

	/**
	 * 
	 * @return
	 * @throws JspException
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("<span title=\"" + value + "\">"
					+ StringUtil.byteSubstring(value, len, true) + "</span>");
			pageContext.getOut().write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		value = null;
		len = null;

		return EVAL_PAGE;
	}

	/**
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#release()
	 */
	@Override
	public void release() {
		super.release();
		value = null;
		len = null;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public Integer getLen() {
		return len;
	}

	public void setLen(Integer len) {
		this.len = len;
	}
}
