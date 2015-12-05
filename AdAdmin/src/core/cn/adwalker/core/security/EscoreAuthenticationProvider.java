package cn.adwalker.core.security;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.adwalker.core.spring.AppContext;
import cn.adwalker.model.sys.dao.ISysResourceDao;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sys.service.ISysUserService;

public class EscoreAuthenticationProvider implements UserDetailsService {

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: loadUserByUsername
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param arg0
	 * @return
	 * @throws UsernameNotFoundException
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		UserDetails user = null;
		ApplicationContext ctx = AppContext.getApplicationContext();
		ISysUserService sysUserService = (ISysUserService) ctx
				.getBean(ISysResourceDao.class);

		try {
			SysUser manageUser;
			manageUser = sysUserService.getUserByName(userName);
			if (manageUser != null) {
				SysUserVo sysUserVo = sysUserService.manageUserToVo(manageUser);
				sysUserVo.setPassWord("");
				user = new SysUserVo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}