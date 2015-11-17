/**
* <p>Title: ScoreAPIServiceImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-7-18
* @version 1.0
*/
package cn.adwalker.ad.api.dev.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.retry.RetryPolicies;
import cn.adwalker.retry.RetryPolicy;
import cn.adwalker.IOSChannel.logger.DevScoreSendLogger;
import cn.adwalker.ad.api.cache.IAppRelCache;
import cn.adwalker.ad.api.dev.service.IScoreAPIService;
import cn.adwalker.ad.dao.IAppRelDao;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;

/**
 * <p>Title: ScoreAPIServiceImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-7-18
 */
@Service("scoreAPIService")
public class ScoreAPIServiceImpl implements IScoreAPIService {
	private static final Logger log = LoggerFactory
			.getLogger(ScoreAPIServiceImpl.class);
	
	@Resource
	private IAppRelDao appRelDao;
	@Resource
	private IAppRelCache appRelCache;
	
	/**  
	* <p>Title: sendData</p>
	* <p>Description:开发者积分回调</p>
	* @param uuid
	* @param userID
	* @param score
	* @param exchangetime
	* @param plat
	* @param appName
	* @param packageName
	* @param adId
	* @see cn.adwalker.ad.api.dev.service.IScoreAPIService#sendData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public void sendData(String uuid, String userID, String score,
			String exchangetime, String plat, String appName,
			String packageName, String adId,String appId,String idfa,String fastTask,String icon_url) {
		
		String url = appRelCache.getAppRel(appId);
		if(url==null){
			return;
		}
		// add by jief 2013-11-12 for 掌上惠
		if("1".equals(plat) && AppConstant.containt(appId)){
			if(uuid!=null && uuid.length()>12){
				uuid=idfa;
			}
		 }
		String sendURL="";
		if(url.endsWith("&")){
			sendURL=url+"uuid=" + uuid + "&userID=" + userID + ""
					+ "&score=" + score + "&exchangetime="
					+ exchangetime + "&plat=" + plat
					+ "&appName=" + appName+"&idfa="+idfa+"&adId="+adId;
		}else{
			sendURL=url+"?uuid=" + uuid + "&userID=" + userID + ""
					+ "&score=" + score + "&exchangetime="
					+ exchangetime + "&plat=" + plat
					+ "&appName=" + appName+"&idfa="+idfa+"&adId="+adId;
		}
		if(packageName!=null){
			sendURL = sendURL+"&packageName="+ packageName;
		}
		if (fastTask!=null) {
			sendURL = sendURL+"&fastTask="+ fastTask;
		}
		
		if (icon_url!=null) {
			sendURL = sendURL+"&icon_url="+ icon_url;
		}
		
		DevScoreSendLogger devLoger=new DevScoreSendLogger();
		devLoger.logInfo(sendURL,"");
		try{
			sendGet(sendURL,Integer.parseInt(ConfigUtil.getString("maxRetries")));
		}catch(Exception e){
			log.error(e.getMessage()+"--->"+sendURL,e);
		}
	}
	
	//add by jief for http retry start 2013-11-06 
	private final  RetryPolicy connectionRetryPolicy = RetryPolicies.retryUpToMaximumCountWithFixedSleep(
					Integer.parseInt(ConfigUtil.getString("maxRetries")), Integer.parseInt(ConfigUtil.getString("sleepTime")), TimeUnit.SECONDS);

	private void handleConnectionFailure(int curRetries, int maxRetries,
			IOException ioe, HttpURLConnection connection) throws IOException {
		log.info("SocketTimeoutException :Thread"
				+ Thread.currentThread().getName() + "重发次数" + curRetries);
		connection.disconnect();
		// throw the exception if the maximum number of retries is reached
		if (curRetries >= maxRetries) {
			throw ioe;
		}
		// otherwise back off and retry
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ignored) {
		}
	}

	private void handleConnectionFailure(int curRetries, IOException ioe,
			HttpURLConnection connection) throws IOException {
		log.info("io Exception :Thread" + Thread.currentThread().getName()
				+ "重发次数" + curRetries);
		connection.disconnect();
		final boolean retry;
		try {
			retry = connectionRetryPolicy.shouldRetry(ioe, curRetries);
		} catch (Exception e) {
			throw e instanceof IOException ? (IOException) e : new IOException(
					e);
		}
		if (!retry) {
			throw ioe;
		}
	}
	/**
	 * <p>
	 * Title: sendGet
	 * </p>
	 * <p>
	 * Description:发送请求方法，修改关闭资源方法，待修改使用统一方法 增加了重发策略
	 * </p>
	 * @param url
	 * @param maxNum
	 * @author jief
	 * @date 2013-11-06
	 * @return void
	 * @version 1.1
	 * @throws
	 */
	private void sendGet(String url, int maxNum) throws Exception {
		String result = "";// 返回的结果
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		int curRetries = 0;
		while (true) {
			try {
				URL getUrl = new URL(url);
				// 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
				// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
				connection = (HttpURLConnection) getUrl.openConnection();
				// 进行连接，但是实际上get
				// request要在下一句的connection.getInputStream()函数中才会真正发到
				connection.setConnectTimeout(Integer.parseInt(ConfigUtil
						.getString("connectionTimeOut")));
				connection.setReadTimeout(Integer.parseInt(ConfigUtil
						.getString("readTimeOut")));
				connection.connect();
				// 取得输入流，并使用Reader读取
				reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null) {
					result += line;
				}
				log.info(result);
				reader.close();
				connection.disconnect();
				return;
			} catch (SocketTimeoutException e) {
				log.error("连接超时准备重发");
				handleConnectionFailure(curRetries++, maxNum, e, connection);

			} catch (IOException ie) {
				log.error("io 异常准备重发");
				handleConnectionFailure(curRetries++, ie, connection);
			}
		}
	}
}
