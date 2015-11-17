package cn.adwalker.ad.web.common.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.model.channel.domain.UserChannels;
import cn.adwalker.ad.model.developer.domain.Developer;
import cn.adwalker.ad.web.common.vo.JumpPageVo;
import cn.adwalker.ad.web.common.vo.LoginVo;

import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * 功能概述：<br>
 * 控制层父类
 */
public abstract class AbstractControllerParent {
	
	@Resource
	private LoginVo currentUser;

	/**
	 * 获取昨天日期yyyy-MM-dd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getYesterday() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}

	/**
	 * 根据key 从session里面取value
	 * 
	 * @param session
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Object getSession(HttpSession session, String key) throws Exception {
		return session.getAttribute(key);
	}

	/**
	 * 根据key 从session里面取value
	 * 
	 * @param session
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public void setSession(HttpSession session, String key, Object value) throws Exception {
		session.setAttribute(key, value);
	}

	/**
	 * 获取当前登录开发者
	 * 
	 * @return
	 * @throws Exception
	 */
	public Developer getUserDeveloper() throws Exception {
		Developer userDeveloper = new Developer();
		BeanUtils.copyProperties(userDeveloper, currentUser);
		return userDeveloper;
	}

	/**
	 * 获取当前登录开发者
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setUserDeveloper(Developer userDeveloper) {
		try {
			BeanUtils.copyProperties(currentUser, userDeveloper);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		currentUser.setUserType(AppConstant.USER_DEVELOPER);
	}

	/**
	 * 获取当前登录渠道
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setUserChannels(UserChannels userChannels) {
		try {
			BeanUtils.copyProperties(currentUser, userChannels);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		currentUser.setUserType(AppConstant.USER_CHANNELS);
	}

	/**
	 * 获取当
	 * 
	 * @return
	 * @throws
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public UserChannels getUserChannels() throws Exception {
		UserChannels userChannels = new UserChannels();
		BeanUtils.copyProperties(userChannels, currentUser);
		return userChannels;
	}

	/**
	 * @param currentUser The currentUser to set.
	 */
	public void setCurrentUser(LoginVo currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * 设置跳转信息
	 * 
	 * @param jumpInfo
	 * @param jumpUrl
	 * @param infoType
	 * @return @
	 */
	public String jumpPage(Map<String, Object> model, String jumpInfo, String jumpUrl, String infoType) {
		model.put("jumpInfo", new JumpPageVo(jumpInfo, jumpUrl, infoType));
		return "../../jumppage";
	}

	/**
	 * 设置跳转信息2
	 * 
	 * @param jumpInfo
	 * @param jumpUrl
	 * @param infoType
	 * @return @
	 */
	public String jumpPage(Map<String, Object> model, String jumpInfo, String jumpUrl, String infoType, String email) {
		model.put("jumpInfo", new JumpPageVo(jumpInfo, jumpUrl, infoType, email));
		return "../../jumppage";
	}

	/**
	 * 设置跳转信息
	 * 
	 * @param jumpInfo
	 * @param jumpUrl
	 * @param infoType
	 * @return @
	 */
	public String jumpPage(HttpServletRequest request, String jumpInfo, String jumpUrl, String infoType) {
		request.setAttribute("jumpInfo", new JumpPageVo(jumpInfo, jumpUrl, infoType));
		return "../../jumppage";
	}

}
