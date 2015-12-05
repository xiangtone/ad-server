package cn.adwalker.core.log;

import java.io.OutputStream;
import java.util.Date;

import jxl.read.biff.SheetImpl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import cn.adwalker.core.context.Context;
import cn.adwalker.core.exception.SysException;
import cn.adwalker.core.log.service.SysLogger;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.spring.AppContext;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.model.sys.domain.SysResource;
import cn.adwalker.ad.admin.common.vo.SysUserVo;

/**
 * 
 * <p>
 * Title: LogInterceptor
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-27
 */
@Component("myInterceptor")
public class LogInterceptor implements MethodInterceptor {
	private final Logger logger = Logger.getLogger(LogInterceptor.class);

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: invoke
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param invocation
	 * @return
	 * @throws Throwable
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		String info = invocation.getMethod().getDeclaringClass() + "."
				+ invocation.getMethod().getName() + "()";
		Object[] objects = invocation.getArguments();
		for (int i = 0; i < objects.length; i++) {
			if (!(objects[i] instanceof SheetImpl)
					&& !(objects[i] instanceof OutputStream)) {
				logger.debug(JacksonMapper.objectToJsonString(objects[i]));
			} else {
				objects[i] = "file";
			}
		}

		try {
			result = invocation.proceed();
			SysUserVo currentUser = Context.getInstance().getCurrentUser();
			ApplicationContext ctx = AppContext.getApplicationContext();
			SpringDatePool springDatePool = (SpringDatePool) ctx
					.getBean("springDatePool");
			String uri = Context.getInstance().getRequestURI();
			if (!StringUtils.isEmpty(uri)) {
				if (uri.indexOf("/") != -1) {
					uri = uri.substring(uri.lastIndexOf("/"), uri.length());// 截取
																			// ("\\/")[1];
				}
			}
			if (currentUser != null && currentUser.getId() != null
					&& currentUser.getRoleId() != null) {
				SysResource res = springDatePool.getResource(uri);
				SysLogger.info(currentUser.getId(), currentUser.getRoleId(),
						info, JacksonMapper.objectToJsonString(objects),
						res != null ? res.getName() : uri,
						res != null ? res.getId() : null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(DateUtil.formatDateTime(new Date()) + "--"
					+ e.getLocalizedMessage());
			throw new SysException("调用服务组件出错:" + e.getLocalizedMessage());
		} finally {
			logger.debug(info);
			try {
				logger.debug(JacksonMapper.objectToJsonString(result));
			} catch (Exception e2) {
			}

		}
		return result;

	}
}