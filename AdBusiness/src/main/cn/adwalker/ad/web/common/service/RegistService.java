package cn.adwalker.ad.web.common.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.config.util.ConfigManager;
import cn.adwalker.ad.model.common.domain.RegDev;
import cn.adwalker.ad.model.developer.dao.DeveloperDao;
import cn.adwalker.ad.model.developer.domain.Developer;
import cn.adwalker.ad.model.platform.dao.MailActivationDao;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.MD5;
import cn.adwalker.ad.util.SendMail;
import cn.adwalker.ad.web.common.form.RegForm;

/**
 * 注册服务层
 */
@Service("registService")
public class RegistService {
	
	@Resource
	private DeveloperDao developerDao;

	@Resource(name = "mailActivationDao")
	private MailActivationDao mailActivationDao;

	public boolean registService(RegForm regForm) throws Exception {
		regForm.setPassword(new MD5().getMD5ofStr(regForm.getPassword()));
		if (regForm != null) {
			RegDev regDev = new RegDev();
			regDev.setCreateTime(new Date());
			regDev.setStatus(AppConstant.BLOCK_NOTUSED);
			regDev.setEmail(regForm.getEmail());
			regDev.setPassword(regForm.getPassword());
			if (developerDao.insert(regDev) != 0) {
				//Developer userDeveloper = developerDao.exists(regForm.getEmail());
				//sendConfrimEmail(userDeveloper.getId(), userDeveloper.getCreateTime(), userDeveloper.getEmail());
				return true;
			}
		}
		return false;
	}

	public boolean exists(String checkParament) throws Exception {
		if (checkParament != null) {
			if (developerDao.exists(checkParament) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 确认账户
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Integer confrimAccount(String key) throws Exception {
		Integer flag = 2;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSSsss");
		String keys[] = key.split("-");
		String id = keys[2];
		String createTime = keys[1];
		String email = keys[3];

		Developer userDeveloper = developerDao.getById(Long.parseLong(id));
		if (userDeveloper != null && userDeveloper.getId() != null && userDeveloper.getCreateTime() != null && userDeveloper.getEmail() != null) {
			if (Long.parseLong(id) == userDeveloper.getId() && createTime.equals(new MD5().getMD5ofStr(sdf.format(userDeveloper.getCreateTime()))) && email.equals(new MD5().getMD5ofStr(userDeveloper.getEmail()))) {
				if (!userDeveloper.getStatus().equals(AppConstant.NO_INTO_T)) {
					userDeveloper.setStatus(AppConstant.BLOCK_USE);
					flag = 1;
				} else {
					flag = 3;
				}
				developerDao.update(userDeveloper);
			}
		}
		return flag;
	}

	/**
	 * 发送确认邮件
	 * 
	 * @param registVo
	 */
	public void sendConfrimEmail(Long id, Date createTime, String email) {
		StringBuffer url = new StringBuffer(ConfigUtil.getString("project.url") + "confrimAccount.action");
		url.append("?key=");
		url.append(createParament(id, createTime, email));
		try {
			StringBuilder mailMessage = new StringBuilder();
			mailMessage.append("<html><head>"+ConfigManager.getConfigData("COMPANY")+"广告激活邮件</head><body><br />尊敬的开发者，您好！<br /><br />欢迎注册"+ConfigManager.getConfigData("COMPANY")+"平台,请激活账户以完成注册；<br /><br />");
			mailMessage.append("<a href='" + url + "'>" + url + "</a>");
			mailMessage.append("如有任何问题，欢迎查阅"+ConfigManager.getConfigData("COMPANY")+"平台<a href='"+ConfigManager.getConfigData("WEB_URL")+"'>常见问题</a>或直接与我们联系，邮件地址：<br /><a href='mailto:"+ConfigManager.getConfigData("SERVICE_CHANGE_PASSWORD_EMAIL")+"'>"+ConfigManager.getConfigData("SERVICE_CHANGE_PASSWORD_EMAIL")+";</a>");
			mailMessage.append("<br />期待与您的合作，谨此致意！<br /><br />"+ConfigManager.getConfigData("COMPANY")+" 服务团队");
			mailMessage.append("</body></html>");
			SendMail.send(ConfigManager.getConfigData("COMPANY")+" 注册激活邮件", ConfigUtil.getString("sendMail"), email, mailMessage.toString());
		} catch (Exception e) {
			System.out.println("发送邮件时发生异常");
			e.printStackTrace();
		}
	}

	/**
	 * 生成参数
	 * 
	 * @param id
	 * @param createTime
	 * @param email
	 * @return
	 */
	private String createParament(Long id, Date createTime, String email) {
		StringBuffer parament = new StringBuffer();
		// 17位时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSSsss");
		// 当前时间
		parament.append(new MD5().getMD5ofStr(System.currentTimeMillis() + ""));
		parament.append("-");
		// 17位创建时间
		parament.append(new MD5().getMD5ofStr(sdf.format(createTime)) + "");
		parament.append("-");
		// 用户id
		parament.append(id);
		parament.append("-");
		// MD5加密邮件地址
		parament.append(new MD5().getMD5ofStr(email));
		parament.append("-");
		parament.append(new MD5().getMD5ofStr(new Date().getTime() + ""));
		return parament.toString();
	}

}
