package cn.adwalker.ad.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import cn.adwalker.ad.bean.Result;
import cn.adwalker.ad.exception.ErrorInfo;
import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.picker.memcached.PickerCache;
import cn.adwalker.ad.service.impl.CheckService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.IpUtil;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.util.RequestUtils;
import cn.adwalker.ad.util.StringUtil;
import cn.adwalker.core.spring.AppContext;

/**
 * 
 * <p>
 * Title: ServiceInvokerFilter
 * </p>
 * <p>
 * Description:接口调用权限检查过滤器
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author caiqiang
 * @date 2013-1-10
 */
public class ServiceInvokerFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(ServiceInvokerFilter.class);
	@Override
	public void init(FilterConfig config) throws ServletException {

	}
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setContentType("application/json;charset=UTF-8");
		String servletPath = request.getServletPath();
//		String query = request.getQueryString();
		loggerParams(request,servletPath);
		//logger.info(servletPath+"?"+query);
		ErrorInfo vo = null;
		ApplicationContext ctx = AppContext.getApplicationContext();
		CheckService checkService = (CheckService) ctx.getBean("checkService");
		//黑名单
		if(isBlackIp(request)){
			logger.info("blackipduan :"+IpUtil.getIp(request));
			vo=PublicUtil.installErrorVO(ExceptionCode.WALL_LOAD_EMPTY);
		}else{
			if (servletPath != null&& (servletPath.contains("/ios/"))) { //ios
				vo = checkService.iosFilter(request);
			}else if(servletPath != null&& (servletPath.contains("/android/"))){ //android
				vo = checkService.androidFilter(request);
//			}else if(servletPath != null&& (servletPath.contains("/agent/"))){
//				System.out.println(request.getQueryString());
//				vo = checkService.portalFilter(request);
			}else{
				chain.doFilter(request, response);
				return ;
			}
		}
		
		
		if (vo==null||!vo.getCode().equals("200")) {
			Result rvo = new Result(AppConstant.STATUS_ERROR, vo);
			PrintWriter out=null;
			try {
				 out = response.getWriter();
			} catch (IOException e) {
				logger.error("checking end. result is fail."+(vo!=null?vo.getCode():"vo 为空!!"));
			}finally{
				out.write(rvo.toString());
				out.close();
			}
			return;
		}
		logger.debug("checking access end. result is pass.");
		chain.doFilter(request, response);
	}
	//ip判断是否是黑名单 
	private boolean isBlackIp(HttpServletRequest request){
		String ip= IpUtil.getIp(request);
		Map<String,String> blackMap = PickerCache.blackIpMap();
		String ipduan = StringUtil.ipdo(ip);
		return blackMap.containsKey(ipduan);
	}
	
	private void loggerParams(HttpServletRequest request,String url){
		logger.info(url+""+RequestUtils.getQueryString(request));
	}
}
