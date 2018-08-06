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
	        // ������Ϣͷ
	        post.addHeader("Content-type","application/json; charset=utf-8");  
	        post.setHeader("Accept", "application/json");  
	        // ������Ϣʵ��
	        StringEntity entity = new StringEntity(json, Const.CHAR_SET);
	        post.setEntity(entity); 
	            
	        HttpResponse response = httpClient.execute(post);
	            
	        // ���鷵����
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

	// �˷���û�в��Գɹ�������401���޷���֤ͨ��������������
	/*public static int httpPost(String json, String uri) {
		// ��ȡ���ӿͻ��˹���
		CloseableHttpClient httpClient = HttpClients.createDefault();

		String entityStr = null;
		CloseableHttpResponse response = null;
		String urlString = uri+"?USERNAME="+Const.USERNAME+"&PASSWORD="+Const.PASSWORD;

		try {

			// ����POST�������
			HttpPost httpPost = new HttpPost(urlString);

			// �����������
			List<NameValuePair> list = new LinkedList<>();
			BasicNameValuePair param1 = new BasicNameValuePair("USERNAME",
					Const.USERNAME);
			BasicNameValuePair param2 = new BasicNameValuePair("PASSWORD",
					Const.PASSWORD);
			list.add(param1);
			list.add(param2);
			// ʹ��URLʵ��ת������
			UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list,
					"UTF-8");
			httpPost.setEntity(entityParam);

			// �������ʾ
			
			 * httpPost.addHeader("User-Agent",
			 * "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
			 
			httpPost.addHeader("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
			// ���������
			httpPost.addHeader("Content-Type", "application/json");
//			httpPost.addHeader("JSESSIONID", cookieValue);

			// ִ������
			response = httpClient.execute(httpPost);
			// �������Ƿ�ɹ��������ӡ����http״̬��
			int responseCode = response.getStatusLine().getStatusCode();
			System.out.println(responseCode);
			// �����Ӧ��ʵ�����
			HttpEntity entity = response.getEntity();
			// ʹ��Apache�ṩ�Ĺ��������ת�����ַ���
			entityStr = EntityUtils.toString(entity, "UTF-8");
			// ��ӡ��Ӧ����
			System.out.println(entityStr);
			System.out.println(Arrays.toString(response.getAllHeaders()));
			return responseCode;
		} catch (ClientProtocolException e) {
			System.err.println("HttpЭ���������");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("��������");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO�쳣");
			e.printStackTrace();
		} finally {
			// �ͷ�����
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("�ͷ����ӳ���");
					e.printStackTrace();
				}
			}
		}

		return Const.ERROR;
	}
*/
	public static String[] httpGet(String uri) {
		// ʹ��Ĭ�ϵ����õ�httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		int responseCode;
		String[] strArr = new String[2];
		try {
			CookieStore cookieStore = new BasicCookieStore(); 
            httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			
			/*
			 * ����GET����Ĳ�������ƴװ��URL��ַ�󷽣���������Ҫ����һ��URL��������
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

			// ���ݴ�������URI���󹹽�GET�������
			HttpGet httpGet = new HttpGet(uriBuilder.build());

			/*
			 * �������ͷ��Ϣ
			 */
			// �������ʾ
			httpGet.addHeader("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
			// ���������
			httpGet.addHeader("Content-Type",
					"application/x-www-form-urlencoded");

			// 3.ִ�����󣬻�ȡ��Ӧ
			response = httpClient.execute(httpGet);
            
			// �������Ƿ�ɹ��������ӡ����http״̬��
			responseCode = response.getStatusLine().getStatusCode();
			//���Cookies
			// 4.��ȡ��Ӧ��ʵ�����ݣ�����������Ҫץȡ����ҳ����
			HttpEntity entity = response.getEntity();
			 cookieStore = new BasicCookieStore();
			 List<Cookie> cookies = cookieStore.getCookies();
			 String cookieValue = "";
			 for (int i = 0; i < cookies.size(); i++) {
				 if("JSESSIONID".equals(cookies.get(i))){
					 cookieValue = cookies.get(i).getValue();
				 }
				 
				 
			 }
			// 5.�����ӡ������̨����

			EntityUtils.consume(entity);
			strArr[0] = responseCode+"";
			strArr[1] = cookieValue;
			
			return strArr;
		} catch (ClientProtocolException e) {
			System.err.println("HttpЭ���������");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("��������");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			System.err.println("URI�����쳣");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO�쳣");
			e.printStackTrace();
		} finally {
			// �ͷ�����
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("�ͷ����ӳ���");
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
