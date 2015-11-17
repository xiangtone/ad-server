/*
 * BeanUtils.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 08-Dec-2011
 */
package cn.adwalker.ad.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import com.sun.org.apache.commons.beanutils.PropertyUtils;

/**
 * 功能概述：<br>
 * javaBean 工具类
 * 
 * @author zhaozengbin
 */
public class BeanUtils {
	/**
	 * 复制属性
	 * 
	 * @param domain
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static Object copyProperties(Object target, Object source)
			throws Exception {
		try {
			com.sun.org.apache.commons.beanutils.BeanUtils.copyProperties(
					target, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * 复制属性
	 * 
	 * @param domain
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static <T, K> List copyList(Class<? extends Object> taregtType,
			List<T> target, List<K> source) throws Exception {
		try {
			Iterator iter = source.iterator();
			while (iter.hasNext()) {
				K k = (K) iter.next();
				T t = (T) taregtType.newInstance();
				Object newObject = new Object();
				com.sun.org.apache.commons.beanutils.BeanUtils.copyProperties(
						t, k);
				target.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * 取得指定对象的属性值
	 * 
	 * @param obj
	 * @param property
	 * @return
	 */
	public static Object getProperty(Object obj, String property) {
		Object value = null;
		try {
			value = PropertyUtils.getProperty(obj, property);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return value;
	}

}
