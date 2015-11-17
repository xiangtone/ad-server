/*
 * EQueryFunction.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 20-Dec-2011
 */
package cn.adwalker.core.util;

/**
 * 功能概述：<br>
 * 查询函数
 * 
 * @author zhaozengbin
 */
public enum EQueryFunction {
	NONE("", "无函数"), MAX("max", "求最大"), MIN("min", "求最小"), SUM("sum", "相加"), ROUND(
			"round", "四舍五入"), AVG("avg", "取平均值");
	private String function;
	private String desc;

	private EQueryFunction(String function, String desc) {
		this.function = function;
		this.desc = desc;
	}

	/**
	 * @return Returns the function.
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            The function to set.
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return Returns the desc.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            The desc to set.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
