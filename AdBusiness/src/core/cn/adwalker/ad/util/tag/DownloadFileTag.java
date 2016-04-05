/*
 * TldUtil.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-1-4
 */
package cn.adwalker.ad.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import cn.adwalker.ad.util.ConfigUtil;

/**
 * 功能概述：<br>
 * 标签
 * 
 * @author 赵增斌
 */
public class DownloadFileTag extends BodyTagSupport {

	/**   */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return
	 * @throws JspException
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print(ConfigUtil.getString("resources.url.prefix"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#release()
	 */
	@Override
	public void release() {
		super.release();
	}
}
