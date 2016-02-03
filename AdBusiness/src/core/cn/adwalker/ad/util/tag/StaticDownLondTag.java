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
import cn.adwalker.ad.util.ObjectUtils;

/**
 * 功能概述：<br>
 * 标签
 * 
 * @author 
 */
public class StaticDownLondTag extends BodyTagSupport {

	/**   */
	private static final long serialVersionUID = 1L;

	private boolean sdk;

	private boolean devmanual;

	private boolean demo;

	/**
	 * 
	 * @return
	 * @throws JspException
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		pageContext.getResponse().setCharacterEncoding("utf-8");
		JspWriter out = pageContext.getOut();
		try {
			if (sdk == true) {
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("sdk.version"))) {
					out.print("<div>版本:" + ConfigUtil.getString("sdk.version") + "</div>");
				}
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("sdk.release"))) {
					out.print("<div>发布时间:" + ConfigUtil.getString("sdk.release") + "</div>");
				}
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("download.sdk.path"))) {
					out.print("<div><a href=\"" + ConfigUtil.getString("download.sdk.path") + "\">下载SDK</a>");
				}
			} else if (devmanual == true) {
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("sdk.version"))) {
					out.print("<div>版本:" + ConfigUtil.getString("sdk.version") + "</div>");
				}
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("sdk.release"))) {
					out.print("<div>发布时间:" + ConfigUtil.getString("sdk.release") + "</div>");
				}
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("download.devmanual.path"))) {
					out.print("<div><a href=\"" + ConfigUtil.getString("download.devsdkep.path") + "\">下载开发者手册</a>");
				}
			} else if (demo == true) {
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("sdk.version"))) {
					out.print("<div>版本:" + ConfigUtil.getString("sdk.version") + "</div>");
				}
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("sdk.release"))) {
					out.print("<div>发布时间:" + ConfigUtil.getString("sdk.release") + "</div>");
				}
				if (ObjectUtils.isNotEmpty(ConfigUtil.getString("download.demo.path"))) {
					out.print("<div><a href=\"" + ConfigUtil.getString("download.demo.path") + "\">下载项目范例</a>");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#release()
	 */
	public void release() {
		super.release();
	}

	/**
	 * @return Returns the sdk.
	 */
	public boolean isSdk() {
		return sdk;
	}

	/**
	 * @param sdk
	 *            The sdk to set.
	 */
	public void setSdk(boolean sdk) {
		this.sdk = sdk;
	}

	/**
	 * @return Returns the devmanual.
	 */
	public boolean isDevmanual() {
		return devmanual;
	}

	/**
	 * @param devmanual
	 *            The devmanual to set.
	 */
	public void setDevmanual(boolean devmanual) {
		this.devmanual = devmanual;
	}

	/**
	 * @return Returns the demo.
	 */
	public boolean isDemo() {
		return demo;
	}

	/**
	 * @param demo
	 *            The demo to set.
	 */
	public void setDemo(boolean demo) {
		this.demo = demo;
	}

}
