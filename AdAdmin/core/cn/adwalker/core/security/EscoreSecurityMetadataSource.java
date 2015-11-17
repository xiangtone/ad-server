package cn.adwalker.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import cn.adwalker.core.spring.AppContext;
import cn.adwalker.model.sys.dao.ISysResourceDao;
import cn.adwalker.model.sys.domain.SysResource;

//1 加载资源与权限的对应关系
public class EscoreSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private static Logger logger = Logger
			.getLogger(EscoreSecurityMetadataSource.class);
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private static Collection<ConfigAttribute> res = null;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:构造函数加载资源
	 * </p>
	 */
	// 由spring调用
	public EscoreSecurityMetadataSource() {
		try {
			loadResourceDefine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAllConfigAttributes
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return res;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAttributes
	 * </p>
	 * <p>
	 * Description:返回所请求资源所需要的权限
	 * </p>
	 * 
	 * @param arg0
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		logger.debug("requestUrl is " + requestUrl);
		if (resourceMap == null) {
			try {
				loadResourceDefine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("22-----------------" + resourceMap.get(requestUrl));
		return resourceMap.get(requestUrl);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: supports
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param arg0
	 * @return
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	// 加载所有资源与权限的关系
	private void loadResourceDefine() throws Exception {
		ApplicationContext ctx = AppContext.getApplicationContext();
		ISysResourceDao sysResourceDao = (ISysResourceDao) ctx
				.getBean(ISysResourceDao.class);
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			res = new ArrayList<ConfigAttribute>();
			List<SysResource> sysResource = sysResourceDao.findAll();
			for (SysResource resource : sysResource) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// 以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig(
						String.valueOf(resource.getId()));
				configAttributes.add(configAttribute);
				res.add(configAttribute);
				resourceMap.put(resource.getUrl(), configAttributes);
			}
		}

		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap
				.entrySet();
		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet
				.iterator();

	}
}