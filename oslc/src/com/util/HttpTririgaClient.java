package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.common.Const;

/*import org.json.JSONObject;

 import com.config.FinalConfig;*/

public class HttpTririgaClient {

	/*
	 * private String makeUrlString(String uri){ String urlString ="";
	 * 
	 * //ƴ��ǰ׺ if(uri.startsWith(Const.CONFIG_OSLC_ROOT)){ urlString=uri; }else
	 * if(uri.startsWith("/oslc")){
	 * urlString=FinalConfig.CONFIG_OSLC_ROOT+"/"+uri; }else
	 * if(uri.startsWith("oslc")){
	 * urlString=FinalConfig.CONFIG_OSLC_ROOT+"/"+uri; }else
	 * if(uri.startsWith("/")){
	 * urlString=FinalConfig.CONFIG_OSLC_ROOT+"/oslc"+uri; }else{
	 * urlString=FinalConfig.CONFIG_OSLC_ROOT+"/oslc/"+uri; }
	 * 
	 * //ƴ��USERNAME��PASSWORD���� if(uri.contains("?")){
	 * urlString=urlString+"&USERNAME="
	 * +FinalConfig.USERNAME+"&PASSWORD="+FinalConfig.PASSWORD; }else{
	 * urlString=
	 * urlString+"?USERNAME="+FinalConfig.USERNAME+"&PASSWORD="+FinalConfig
	 * .PASSWORD; }
	 * 
	 * //��֤����URL�Ƿ�Ϸ�...δ��� System.out.println(urlString); return urlString; }
	 */

	public String sendJsonToTririga(String urlString, String json, String method) {

		OutputStreamWriter out = null;
		InputStream in = null;
		String s = "";
		HttpURLConnection con = null;
		try {
			// String urlString =this.makeUrlString(uri);
			URL url = new URL(urlString);
			con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod(method);
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "application/json");

			out = new OutputStreamWriter(con.getOutputStream());
			System.out.println("����tririga��");
			System.out.println(method + "	url:" + urlString);
			// System.out.println(json.toString());
			out.write(json);
			out.flush();

			// �����������ڽ���http��Ӧ
			in = con.getInputStream();
			int responseCode = con.getResponseCode();
			System.out.println("responseCode:" + responseCode);

			// ��������ӡhttp��Ӧ,�˶δ��벻��ִ�У���Ϊ������tririga�ɹ���ʱ��responseCode=204����û����Ӧ��ġ�
			if (responseCode == 200) {
				int len = -1;
				byte[] bs = new byte[1024];
				StringBuilder sb = new StringBuilder("");
				while ((len = in.read(bs)) != -1) {
					sb.append(new String(bs, 0, len, "utf-8"));
				}
				s = sb.toString();
				System.out.println("tririga��Ӧ�Ľ���ǣ�" + sb.toString());
				System.out.println("success");

			} else if (responseCode > 300 && responseCode > 200) {
				System.out.println("�첽�ɹ�");
			}

		} catch (Exception e) {
			System.out.println(e);
			s = e.toString();
			int len = -1;
			byte[] bs = new byte[1024];
			StringBuilder sb = new StringBuilder("");
			if (in.equals("") || in == null) {
				try {
					while ((len = in.read(bs)) > 0) {
						sb.append(new String(bs, 0, len, "utf-8"));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			System.out.println("tririga��Ӧ�Ĵ����ǣ�" + s);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return s;

	}
}
