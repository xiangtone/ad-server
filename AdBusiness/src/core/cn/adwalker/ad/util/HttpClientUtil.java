package cn.adwalker.ad.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpClientUtil {

	/**
	 * 
	 * <p>
	 * Title: sendGet
	 * </p>
	 * 
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		System.out.println(url);
		String result = "";// 返回的结果
		try {
			String getURL = url + "" + URLEncoder.encode("", "utf-8");
			URL getUrl = new URL(getURL);
			// 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			// 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
			// 服务器
			connection.connect();
			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String lines;
			while ((lines = reader.readLine()) != null) {
				result += lines + "\r\n";
			}
			reader.close();
			// 断开连接
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

}
