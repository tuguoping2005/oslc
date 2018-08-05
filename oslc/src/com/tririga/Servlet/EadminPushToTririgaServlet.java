package com.tririga.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.Const;
import com.util.JsonUtil;

//@WebServlet("/tririga")
public class EadminPushToTririgaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CHAR_SET = "UTF-8";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取请求体中的json数据
		InputStream in = request.getInputStream();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = new byte[1024];
		int len = -1;
		while ((len = in.read(bs)) != -1) {
			String str = new String(bs, 0, len, CHAR_SET);
			sb.append(str);
		}
		String srcJson = sb.toString();
//		System.out.println(srcJson);
		
		//将数据拆分成Contract 和Allocation 并分别请求接口
		//boolean isAllocation = srcJson.contains(Const.CONTRACT_ALLOCATION_NODE_NAME);
		//if(isAllocation){
		ArrayList<String> list = JsonUtil.resolveJson(srcJson, Const.INTERFACE_ASSET_LEASE_FIELD);
		
		for(String str : list){
			System.out.println(str);
		}
			
		
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
