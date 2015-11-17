package cn.adwalker.ad.control.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpClientUtils {

	public static String sendGet(String url) {
		System.out.println(url);
		String result = "";// 返回的结果
		try {
			String getURL = url + "" + URLEncoder.encode("", "utf-8");
			URL getUrl = new URL(getURL);
			// 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			// 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
			// 服务器
			connection.connect();
			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

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
	
	public static void httpRequestPost(String urlStr, String param) {
		System.out.println("send:" + urlStr);
		System.out.println("param:" + param);
		try {
			URL url = new URL(urlStr);

			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();

			// 设置是否向connection输出，因为是post请求，参数要放在 http正文内，因此需要设为true
			httpConn.setDoOutput(true);

			// 是否向connection输入，默认为true
			httpConn.setDoInput(true);

			// POST请求方式
			httpConn.setRequestMethod("POST");

			// post请求不能使用缓存
			httpConn.setUseCaches(false);

			// 是否自动执行重定向,默认为true
			httpConn.setInstanceFollowRedirects(true);

			// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数
			// 下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码
			httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			// 连接，从openConnection()至此的配置必须要在connect之前完成，
			// 注意的是connection.getOutputStream会隐含的进行connect。
			httpConn.connect();

			DataOutputStream dos = new DataOutputStream(
					httpConn.getOutputStream());

			// 发送正文，正文内容其实跟get的URL中'?'后的参数字符串一致 word=wait&tn=news&from=news
			String content = param;

			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			dos.writeBytes(content);

			// 刷新流
			dos.flush();

			// 关闭流
			dos.close();

			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));

			System.out.println("=========post request接收数据内容开始============");
			StringBuilder sb = new StringBuilder();
			String lines;
			while ((lines = reader.readLine()) != null) {
				sb.append(lines);
			}
			reader.close();
			System.out.println(sb.toString());
			System.out.println("=========post request接收数据内容结束============");
			httpConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
