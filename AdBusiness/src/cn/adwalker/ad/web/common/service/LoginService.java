package cn.adwalker.ad.web.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.config.util.ConfigManager;
import cn.adwalker.ad.model.common.dao.UserLoginDao;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.MD5;
import cn.adwalker.ad.util.RandomUtil;
import cn.adwalker.ad.util.SendMail;
import cn.adwalker.ad.web.common.vo.ViewUserVo;

@Service("loginService")
public class LoginService {
	
	@Resource
	private UserLoginDao userLoginDao;

	public ViewUserVo login(String email, String password) throws Exception {
		password = new MD5().getMD5ofStr(password);
		ViewUserVo vo = userLoginDao.login(email, password);
		return vo;
	}

	public int resetPassword(String email) throws Exception {
		int ad = 0;
		try {
			// 邮箱存在，返回1
			ad = userLoginDao.byEmail(email);
			if (ad != 0) {
				// 重置密码,生成6位随机字符串
				String pwd = RandomUtil.random(6);
				String mdpwd = new MD5().getMD5ofStr(pwd);
				userLoginDao.resetPassword(email, mdpwd, ad);
				// 发送邮件
				try {
					StringBuilder sb = new StringBuilder();
					sb.append("<html><head>"+ConfigManager.getConfigData("COMPANY")+"密码重置邮件</head><body><br />尊敬的开发者，您好！<br /><br />此为密码重置邮件，您的密码已经被重置为：");
					sb.append(pwd);
					sb.append("，请登录后尽快修改密码。<br />如有任何问题，欢迎查阅"+ConfigManager.getConfigData("COMPANY")+"平台常见问题或直接与我们联系：客服QQ "+ConfigManager.getConfigData("SERVICE_CHANGE_PASSWORD_QQ")+"，技术支持群 "+ConfigManager.getConfigData("SERVICE_CHANGE_PASSWORD_GROUP")+"，客服电话"+ConfigManager.getConfigData("SERVICE_CHANGE_PASSWORD_PHONE")+"。<br /><br />"+ConfigManager.getConfigData("COMPANY")+" 服务团队");
					sb.append("</body></html>");
					SendMail.send(ConfigManager.getConfigData("COMPANY")+" 密码重置邮件", ConfigUtil.getString("sendMail"), email, sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 1;
			} else {
				// 邮箱不存在
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean userExists(String email) throws Exception {
		int count=userLoginDao.existEmail(email);
		return (count>0);
	}
}
