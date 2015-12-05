package cn.adwalker;

import java.util.List;

import cn.adwalker.core.util.PinYinUtil;
import cn.adwalker.model.sys.dao.IPermissionDao;
import cn.adwalker.model.sys.domain.Permission;

public class TestPermissionDao extends TestBase {

	public void testtable() throws Exception {
		IPermissionDao dao = (IPermissionDao) context
				.getBean(IPermissionDao.class);
		List<Permission> list = dao.findAll();
		for (Permission permission:list) {
			dao.updateCode(permission.getId(),PinYinUtil.toPinYin(permission.getName()));
			
		}

	}
}
