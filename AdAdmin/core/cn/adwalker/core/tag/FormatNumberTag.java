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

import cn.adwalker.core.util.lang.MathUtil;

/**
 * 功能描述<br>
 * 日期格式化标签
 *
 * @author guoyongxiang
 */
public class FormatNumberTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	/** Numeric value to be formatted. */
	private Double value;

	/** Maximum number of digits in the fractional portion of the formatted output. */
	private Integer maxFractionDigits;
	
	/** Default number. */
	private final static Integer DEF_NUM = 2;

	/**
	 * 
	 * @return
	 * @throws JspException
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {

		try {
			if(maxFractionDigits == null || "".equals(maxFractionDigits)){
				maxFractionDigits = DEF_NUM;
			}
			pageContext.getOut().write(String.valueOf(MathUtil.truncF(value, maxFractionDigits)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		value = null;
		maxFractionDigits = null;

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
		maxFractionDigits = null;
	}

	

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * @return the maxFractionDigits
	 */
	public Integer getMaxFractionDigits() {
		return maxFractionDigits;
	}

	/**
	 * @param maxFractionDigits the maxFractionDigits to set
	 */
	public void setMaxFractionDigits(Integer maxFractionDigits) {
		this.maxFractionDigits = maxFractionDigits;
	}

}
