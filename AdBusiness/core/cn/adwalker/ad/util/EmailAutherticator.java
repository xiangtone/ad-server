package cn.adwalker.ad.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 功能概述：<br>
 *
 *  验证邮箱用户名，密码
 *
 * @author jiaozhichao
 */
public class EmailAutherticator extends Authenticator {
	private String strUser;
	private String strPwd;

	public EmailAutherticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}
