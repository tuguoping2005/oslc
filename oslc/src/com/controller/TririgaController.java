package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.Const;
import com.common.HttpRequestTririgaClient;
import com.common.ResponseCode;
import com.common.TririgaResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.util.JsonUtil;
import com.util.PropertiesUtil;

@Path("/tririgaController")
public class TririgaController {
	private static Logger log = LoggerFactory
			.getLogger(TririgaController.class);

	@POST
	@Path("/createOrUpdate")
	// 一个资源可以返回的 MIME 类型
	@Produces(MediaType.APPLICATION_JSON)
	// 一个资源可以传入的 MIME 类型
	@Consumes(MediaType.APPLICATION_JSON)
	public TririgaResponse createOrUpdate(
			@QueryParam("username") String username,
			@QueryParam("password") String password,
			@Context HttpServletRequest request) throws IOException {
		TririgaResponse tririgaResponse = null;

		// 验证用户名，密码
		if (!isCheck(username, password)) {
			return TririgaResponse.createByErrorCodeMessage(
					ResponseCode.NEED_LOGIN.getCode(),
					ResponseCode.NEED_LOGIN.getDesc());
		}

		// 以二进制流的形式获取请求体中的json数据
		InputStream in = request.getInputStream();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = new byte[1024];
		int len = -1;
		while ((len = in.read(bs)) != -1) {
			String str = new String(bs, 0, len, Const.CHAR_SET);
			sb.append(str);
		}
		String srcJson = sb.toString();
//		log.info("请求体json字符串为:{}", srcJson);

		// 校验srcJson是否合法
		if (!this.isValid(srcJson)) {
			return TririgaResponse.createByErrorCodeMessage(
					ResponseCode.ILLEGAL_JSON.getCode(),
					ResponseCode.ILLEGAL_JSON.getDesc());
		}

		// 发数据请求Tririga
		int code = this.sendRequestToTririga(srcJson);
		log.info("请求Tririga响应code:{}", code);

		if (Const.SUCCESS == code) {
			tririgaResponse = TririgaResponse.createBySuccessCodeMessage(
					ResponseCode.SUCCESS.getCode(),
					ResponseCode.SUCCESS.getDesc());
		} else if (Const.CREATE_SUCCESS == code) {
			tririgaResponse = TririgaResponse.createBySuccessCodeMessage(
					ResponseCode.CREATE_SUCCESS.getCode(),
					ResponseCode.CREATE_SUCCESS.getDesc());
		} else if (Const.ILLEGAL_ARGUMENT == code) {
			tririgaResponse = TririgaResponse.createBySuccessCodeMessage(
					ResponseCode.ILLEGAL_ARGUMENT.getCode(),
					ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		} else if (Const.ILLEGAL_JSON == code) {
			tririgaResponse = TririgaResponse.createBySuccessCodeMessage(
					ResponseCode.ILLEGAL_JSON.getCode(),
					ResponseCode.ILLEGAL_JSON.getDesc());
		} else if (Const.NULL_JSON == code) {
			tririgaResponse = TririgaResponse.createBySuccessCodeMessage(
					ResponseCode.NULL_JSON.getCode(),
					ResponseCode.NULL_JSON.getDesc());
		} else if (Const.ILLEGAL_PERCENT == code) {
			tririgaResponse = TririgaResponse.createBySuccessCodeMessage(
					ResponseCode.ILLEGAL_PERCENT.getCode(),
					ResponseCode.ILLEGAL_PERCENT.getDesc());
		} else {
			tririgaResponse = TririgaResponse.createByErrorMessage("请求资源失败");
		}

		return tririgaResponse;
	}

	/**
	 * 校验请求的json字符串
	 * 
	 * @param srcJson
	 * @return
	 */
	private boolean isValid(String srcJson) {
		if (!StringUtils.isBlank(srcJson)) {
			JsonObject jo = new JsonParser().parse(srcJson).getAsJsonObject();
			String[] strArr = Const.INTERFACE_ASSET_LEASE_FIELD;
			for (int i = 0; i < strArr.length; i++) {
				String s = "spi:" + strArr[i];
				if (jo.has(s)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 校验用户名和密码
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private boolean isCheck(String username, String password) {
		String user = PropertiesUtil.getProperty(Const.USER_INFO);
		String pwd = PropertiesUtil.getProperty(Const.PWD_INFO);
//		log.info("username:{} password:{}", username, password);
		if (StringUtils.isNotBlank(username)
				&& StringUtils.isNotBlank(password)) {
			if (user.contains(username) && pwd.contains(password)) {
				String[] userArr = user.split(",");
				String[] pwdArr = pwd.split(",");
				for (int i = 0; i < userArr.length; i++) {
					if (userArr[i].equalsIgnoreCase(username)
							&& pwdArr[i].equalsIgnoreCase(password)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * 校验摊销百分比是否等于100
	 * @param map
	 * @return
	 */
	private boolean checkPercent(Map<String, List<String>> map) {
		for (String s : map.keySet()) {
			if ("percentages".equalsIgnoreCase(s)) {
				List<String> perList = map.get(s);
				int totalPercent = 0;
				for (String per : perList) {
					totalPercent += Integer.parseInt(per.trim());
				}
				log.info("摊销百分比总和:"+totalPercent );
				return totalPercent != 100;
			}
		}

		return false;
	}

	/**
	 * 请求Tririga Server
	 */
	public int sendRequestToTririga(String srcJson) {
		// 将数据拆分成Contract 和Allocation 并分别请求接口
		Map<String, List<String>> map = JsonUtil.resolveJson(srcJson,
				Const.INTERFACE_ASSET_LEASE_FIELD);

		int responseCode = 0;

		// 22 map为null
		if (map == null) {
			return ResponseCode.NULL_JSON.getCode();
		}

		// 23 摊销百分比不等于100，则返回
		if (this.checkPercent(map)) {
			return ResponseCode.ILLEGAL_PERCENT.getCode();
		}

		for (String s : map.keySet()) {
			if ("assetLeaseTemplate".equalsIgnoreCase(s)) {
				List<String> list = map.get(s);
				String assetLeaseTemplateJson = list.get(0);
//				log.info("assetLeaseTemplateJson :{}", assetLeaseTemplateJson);
				responseCode = HttpRequestTririgaClient.httpPost(
						assetLeaseTemplateJson, Const.ASSET_LEASE_TEMPLATE);

			} else if ("allocations".equalsIgnoreCase(s)) {
				if (Const.CREATE_SUCCESS == responseCode) {
					List<String> list = map.get(s);
					for (String allocationsJson : list) {
//						log.info("allocationsJson :{}", allocationsJson);
						responseCode = HttpRequestTririgaClient.httpPost(
								allocationsJson, Const.ALLOCATIONS);
					}
				}
			}
		}
		return responseCode;
	}
}
