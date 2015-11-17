/**
* <p>Title: ChnnelTest.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date Dec 23, 2012
* @version 1.0
*/
package cn.adwalker.IOSChannel.test;

/**
* <p>Title: IOSTest.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-23
* @version 1.0
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * <p>Title: IOSTest</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-23
 */
public class ChnnelTest {
	public static void main(String[] args) throws Exception {
		String count = "";
		int size = 1;
		for (int i = 0; i < size; i++) {
			count += "01:26:08:A1:A5:51";
			System.out.println(i);
			if (i != size - 1) {
				count += ",";
			}
		}
		System.out.println("数据准备完成");
	    String params ="{'deviceid':'00:21:E9:71:BB:33','appid':'xiaoyishu','source':'adwalker','eventtime':'1356228086359','data1':'HTC'}";

		String k =sendPost("http://221.122.127.84:8085/EScore_IOS_Service/common/channelCheck.do",params);
		System.out.println("params:" + params);
		System.out.println("result:" + k);
	}

	public static String sendPost(String url, String params) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		try {

			// 创建URL对象
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			httpConn.setRequestProperty("Accept-Charset", "UTF-8");
			httpConn.setRequestProperty("Content-Type", "application/json");
			// 设置POST方式
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数
			out.write(params);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
