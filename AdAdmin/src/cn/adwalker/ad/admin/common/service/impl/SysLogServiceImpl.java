/**
 * 
 */
package cn.adwalker.ad.admin.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.BeanUtils;
import cn.adwalker.model.common.dao.ISysLogDao;
import cn.adwalker.model.log.domain.SysLog;
import cn.adwalker.model.sys.dao.ISysRoleDao;
import cn.adwalker.model.sys.dao.ISysUserDao;
import cn.adwalker.model.sys.domain.SysRole;
import cn.adwalker.model.sys.domain.SysUser;
import cn.adwalker.ad.admin.common.bean.LogQueryBean;
import cn.adwalker.ad.admin.common.service.ISysLogService;
import cn.adwalker.ad.admin.common.vo.SysManagerLogVo;

/**
 * 
 * <p>
 * Title: EScoreManagerLogServiceImpl
 * </p>
 * <p>
 * Description:TODO   
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-28
 */
@Service(value = "eScoreManagerLogService")
@Transactional
public class SysLogServiceImpl implements ISysLogService {

	@Resource
	private ISysLogDao sysLogDao;

	@Resource
	private ISysUserDao managerUserDao;

	@Resource
	private ISysRoleDao sysRoleDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: eScoreManagerLogToVo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param eScoreManagerLog
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.common.service.ISysLogService#eScoreManagerLogToVo(java.util.List)
	 */
	@Override
	public List<SysManagerLogVo> eScoreManagerLogToVo(
			List<SysLog> eScoreManagerLog) throws Exception {
		List<SysManagerLogVo> eScoreManagerLogVoList = new ArrayList<SysManagerLogVo>();
		SysManagerLogVo eScoreManagerLogVo = null;
		for (SysLog bean : eScoreManagerLog) {
			eScoreManagerLogVo = new SysManagerLogVo();
			BeanUtils.copyProperties(eScoreManagerLogVo, bean);
			// 用户、角色
			SysRole role = this.sysRoleDao.get(bean.getRoleId(), SysRole.class);
			SysUser user = this.managerUserDao.findOneById(bean.getUserId());
			if (role != null) {
				eScoreManagerLogVo.setRoleName(role.getName());
			}
			eScoreManagerLogVo.setUserName(user.getUserName());

			eScoreManagerLogVoList.add(eScoreManagerLogVo);
		}
		return eScoreManagerLogVoList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @see cn.adwalker.ad.admin.common.service.ISysLogService#findByPage(cn.adwalker.ad.admin.common.bean.LogQueryBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysLog> findByPage(LogQueryBean bean,
			IPageInfo pageInfo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" T_LOG_SYS_MANAGER where 1=1 ");

		// 按菜单搜索
		if (!StringUtils.isEmpty(bean.getMenu())) {
			sb.append(" and ");
			sb.append(" menu like ");
			sb.append(" '%" + bean.getMenu() + "%' ");
		}
		// 按角色搜索
		if (!StringUtils.isEmpty(bean.getRoleId())) {
			sb.append(" and ");
			sb.append(" role_id = ");
			sb.append(Long.valueOf(bean.getRoleId()));
		}
		// 按用户名搜索
		if (!StringUtils.isEmpty(bean.getValue())
				&& !StringUtils.isEmpty(bean.getKeyword())
				&& "user_name".equals(bean.getKeyword())) {
			sb.append(" and ");
			sb.append(" user_id in ");
			sb.append("( select id from manage_user mu where ");
			sb.append(bean.getKeyword());
			sb.append(" like ");
			sb.append(" '%" + bean.getValue() + "%')");
		}

		// /按时间搜索
		if (!StringUtils.isEmpty(bean.getBeginDate())
				&& !StringUtils.isEmpty(bean.getEndDate())) {
			sb.append(" and ");
			sb.append(" date_format(create_time,'%Y-%m-%d') >= ");
			sb.append(" '" + bean.getBeginDate() + "' ");
			sb.append(" and ");
			sb.append(" date_format(create_time,'%Y-%m-%d') <= ");
			sb.append(" '" + bean.getEndDate() + "' ");

		}

		return (List<SysLog>) sysLogDao.findByPage("*",
				sb.toString(), " order by create_time desc ",
				SysLog.class, pageInfo);
	}

}
