/*
 * TldUtil.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-1-4
 */
package cn.adwalker.ad.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.adwalker.ad.web.common.vo.LoginVo;

/**
 * 功能概述：<br>
 * 标签
 * 
 * @author 赵增斌
 */
public class SpringBeanTag extends BodyTagSupport {

	/**   */
	private static final long serialVersionUID = 1L;

	/** spring id */
	private String springId;

	/** pageContext save id name */
	private String saveId;

	/**
	 * 
	 * @return
	 * @throws JspException
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {

		WebApplicationContext springContext = (WebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		Object object = springContext.getBean(springId);

		if (springId.equals("currentUser")) {// 表示判断session
			try {
				object.toString();
				pageContext.setAttribute(saveId, object);
			} catch (Exception e) {
				pageContext.setAttribute(saveId, new LoginVo());
			}
		} else {
			pageContext.setAttribute(saveId, object);
		}

		springId = null;
		saveId = null;

		return EVAL_PAGE;
	}

	/**
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#release()
	 */
	@Override
	public void release() {
		super.release();
		springId = null;
		saveId = null;
	}

	/**
	 * @return Returns the saveId.
	 */
	public String getSaveId() {
		return saveId;
	}

	/**
	 * @param saveId
	 *            The saveId to set.
	 */
	public void setSaveId(String saveId) {
		this.saveId = saveId;
	}

	/**
	 * @return Returns the springId.
	 */
	public String getSpringId() {
		return springId;
	}

	/**
	 * @param springId
	 *            The springId to set.
	 */
	public void setSpringId(String springId) {
		this.springId = springId;
	}
}
