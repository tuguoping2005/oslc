package com.tririga.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.Const;
import com.common.HttpRequestTririgaClient;
import com.common.ResponseCode;
import com.common.ServerResponse;
import com.google.gson.Gson;
import com.util.JsonUtil;

public class EAdminPushToTririgaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(EAdminPushToTririgaServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServerResponse<String> serverResponse = null;
		// 获取请求体中的json数据
		InputStream in = request.getInputStream();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = new byte[1024];
		int len = -1;
		while ((len = in.read(bs)) != -1) {
			String str = new String(bs, 0, len, Const.CHAR_SET);
			sb.append(str);
		}
		String srcJson = sb.toString();
		System.out.println(srcJson);
		
		int code = sendRequestToTririga(srcJson);

		if (Const.SUCCESS == code) {
			serverResponse = ServerResponse.createBySuccess();
		} else if (Const.ASY_SUCCESS == code) {
			serverResponse = ServerResponse
					.createBySuccess(ResponseCode.ASY_SUCCESS.getDesc());
		} else {
			ServerResponse.createByErrorMessage("请求资源失败");
		}

		// 将请求结果响应到前端
		Gson gson = new Gson();
		
		response.setCharacterEncoding(Const.CHAR_SET);
		PrintWriter writer = response.getWriter();
		String respJson = gson.toJson(serverResponse);
		writer.println(respJson);
	}

	/**
	 * 请求Tririga Server
	 */
	public int sendRequestToTririga(String srcJson) {
		// 将数据拆分成Contract 和Allocation 并分别请求接口
		Map<String, List<String>> map = JsonUtil.resolveJson(srcJson,
				Const.INTERFACE_ASSET_LEASE_FIELD);
		int responseCode = 0;
		String uri = "";
		for (String s : map.keySet()) {
			if ("assetLeaseTemplate".equalsIgnoreCase(s)) {
				List<String> list = map.get(s);
				String assetLeaseTemplateJson = list.get(0);
				uri = Const.HOST_NAME + Const.CREATE_ASSET_LEASE_URI;
				System.out.println(uri);
				System.out.println(assetLeaseTemplateJson);
				responseCode = HttpRequestTririgaClient.httpPost(
						assetLeaseTemplateJson, uri);

			} else if ("allocations".equalsIgnoreCase(s)) {
				if (Const.ASY_SUCCESS == responseCode) {
					uri = Const.HOST_NAME
							+ Const.CREATE_ASSET_LEASE_ALLOCATION_URI;
					List<String> list = map.get(s);
					for (String allocationsJson : list) {
						System.out.println(allocationsJson);
						responseCode = HttpRequestTririgaClient.httpPost(
								allocationsJson, uri);
					}
				}
			}

		}
		return responseCode;
	}

	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
