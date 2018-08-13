package com.common;

import java.net.URI;
import java.net.URL;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class HttpRequestTririgaClient {
	private static Logger log = LoggerFactory.getLogger(HttpRequestTririgaClient.class);
	
	@SuppressWarnings("deprecation")
	public static int httpPost(String json,String marker){
		CloseableHttpClient httpClient = HttpClients.createDefault();
    	CloseableHttpResponse response = null;
    	HttpPost httpPost = null;
    	String uri = "";
    	int tririgaResponseCode=Const.UN_RESPONSE;
    	try {
			// ��װ�������
            List<NameValuePair> paramsList = Lists.newArrayList();
            paramsList.add(new BasicNameValuePair("USERNAME", Const.USERNAME));
            paramsList.add(new BasicNameValuePair("PASSWORD", Const.PASSWORD));
            // ת��Ϊ��ֵ��  
            String params = EntityUtils.toString(new UrlEncodedFormEntity(paramsList, Consts.UTF_8));
            // �����ַ
            if(marker!=null && Const.ALLOCATIONS.equalsIgnoreCase(marker)){
            	uri = Const.HOST_NAME+Const.CREATE_ASSET_LEASE_ALLOCATION_URI;
            }else if(Const.ASSET_LEASE_TEMPLATE.equalsIgnoreCase(marker)){
            	uri = Const.HOST_NAME+Const.CREATE_ASSET_LEASE_URI;
            }
            uri += "?" + params;
            log.info(uri);
	        // ����httpPost.
            URL url1 = new URL(uri); 
            URI uri2 = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
            httpPost = new HttpPost(uri2);  
            httpPost.addHeader("Content-type","application/json; charset=utf-8");  
            httpPost.setHeader("Accept", "application/json");  
	        // ������Ϣʵ��
	        StringEntity entity = new StringEntity(json, "utf-8");
	        httpPost.setEntity(entity); 
            
	        // ͨ����������ȡ��Ӧ����
	        response = httpClient.execute(httpPost);
	        
	        // �ж���������״̬���Ƿ�����(0--201��������)
	        tririgaResponseCode = response.getStatusLine().getStatusCode();
    	} catch (Exception e) {
	        e.printStackTrace();
	    }finally{
	        if(httpPost != null){
	            try {
	            	httpPost.releaseConnection();
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	            	log.error(e.getMessage());
	            }
	        }
	        httpClient.getConnectionManager().shutdown();
	    }
	    return tririgaResponseCode;
	}
}
