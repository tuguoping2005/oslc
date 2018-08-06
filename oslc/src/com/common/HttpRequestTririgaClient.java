package com.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpRequestTririgaClient {

	@SuppressWarnings({ "resource", "deprecation" })
	public static int httpPost(String json,String uri){
		HttpPost post = null;
		HttpClient httpClient = null;
		String url = uri+"?USERNAME="+Const.USERNAME+"&PASSWORD="+Const.PASSWORD;
		System.out.println(url);
	    try {
			httpClient = new DefaultHttpClient();
	        
	        post = new HttpPost(url);
	        // 构造消息头
	        post.addHeader("Content-type","application/json; charset=utf-8");  
	        post.setHeader("Accept", "application/json");  
	        // 构建消息实体
	        StringEntity entity = new StringEntity(json, Const.CHAR_SET);
	        post.setEntity(entity); 
	            
	        HttpResponse response = httpClient.execute(post);
	            
	        // 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        
	        return statusCode;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally{
	        if(post != null){
	            try {
	                post.releaseConnection();
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        httpClient.getConnectionManager().shutdown();
	    }
	    return Const.ERROR;
	}

	// 此方法没有测试成功，报错401，无法认证通过，待继续调试
	/*public static int httpPost(String json, String uri) {
		// 获取连接客户端工具
		CloseableHttpClient httpClient = HttpClients.createDefault();

		String entityStr = null;
		CloseableHttpResponse response = null;
		String urlString = uri+"?USERNAME="+Const.USERNAME+"&PASSWORD="+Const.PASSWORD;

		try {

			// 创建POST请求对象
			HttpPost httpPost = new HttpPost(urlString);

			// 创建请求参数
			List<NameValuePair> list = new LinkedList<>();
			BasicNameValuePair param1 = new BasicNameValuePair("USERNAME",
					Const.USERNAME);
			BasicNameValuePair param2 = new BasicNameValuePair("PASSWORD",
					Const.PASSWORD);
			list.add(param1);
			list.add(param2);
			// 使用URL实体转换工具
			UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list,
					"UTF-8");
			httpPost.setEntity(entityParam);

			// 浏览器表示
			
			 * httpPost.addHeader("User-Agent",
			 * "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
			 
			httpPost.addHeader("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
			// 传输的类型
			httpPost.addHeader("Content-Type", "application/json");
//			httpPost.addHeader("JSESSIONID", cookieValue);

			// 执行请求
			response = httpClient.execute(httpPost);
			// 看请求是否成功，这儿打印的是http状态码
			int responseCode = response.getStatusLine().getStatusCode();
			System.out.println(responseCode);
			// 获得响应的实体对象
			HttpEntity entity = response.getEntity();
			// 使用Apache提供的工具类进行转换成字符串
			entityStr = EntityUtils.toString(entity, "UTF-8");
			// 打印响应内容
			System.out.println(entityStr);
			System.out.println(Arrays.toString(response.getAllHeaders()));
			return responseCode;
		} catch (ClientProtocolException e) {
			System.err.println("Http协议出现问题");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("解析错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO异常");
			e.printStackTrace();
		} finally {
			// 释放连接
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("释放连接出错");
					e.printStackTrace();
				}
			}
		}

		return Const.ERROR;
	}
*/
	public static String[] httpGet(String uri) {
		// 使用默认的配置的httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		int responseCode;
		String[] strArr = new String[2];
		try {
			CookieStore cookieStore = new BasicCookieStore(); 
            httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			
			/*
			 * 由于GET请求的参数都是拼装在URL地址后方，所以我们要构建一个URL，带参数
			 */
			URIBuilder uriBuilder = new URIBuilder(Const.HOST_NAME);
			List<NameValuePair> list = new LinkedList<>();
			BasicNameValuePair param1 = new BasicNameValuePair("USERNAME",
					Const.USERNAME);
			BasicNameValuePair param2 = new BasicNameValuePair("PASSWORD",
					Const.PASSWORD);
			list.add(param1);
			list.add(param2);
			uriBuilder.setParameters(list);

			// 根据带参数的URI对象构建GET请求对象
			HttpGet httpGet = new HttpGet(uriBuilder.build());

			/*
			 * 添加请求头信息
			 */
			// 浏览器表示
			httpGet.addHeader("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
			// 传输的类型
			httpGet.addHeader("Content-Type",
					"application/x-www-form-urlencoded");

			// 3.执行请求，获取响应
			response = httpClient.execute(httpGet);
            
			// 看请求是否成功，这儿打印的是http状态码
			responseCode = response.getStatusLine().getStatusCode();
			//获得Cookies
			// 4.获取响应的实体内容，就是我们所要抓取得网页内容
			HttpEntity entity = response.getEntity();
			 cookieStore = new BasicCookieStore();
			 List<Cookie> cookies = cookieStore.getCookies();
			 String cookieValue = "";
			 for (int i = 0; i < cookies.size(); i++) {
				 if("JSESSIONID".equals(cookies.get(i))){
					 cookieValue = cookies.get(i).getValue();
				 }
				 
				 
			 }
			// 5.将其打印到控制台上面

			EntityUtils.consume(entity);
			strArr[0] = responseCode+"";
			strArr[1] = cookieValue;
			
			return strArr;
		} catch (ClientProtocolException e) {
			System.err.println("Http协议出现问题");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("解析错误");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			System.err.println("URI解析异常");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO异常");
			e.printStackTrace();
		} finally {
			// 释放连接
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("释放连接出错");
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
