package com.mvn.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvn.test.service.UserInfoService;
import com.mvn.test.service.Impl.UserInfoServiceImpl;
import com.mvn.test.vo.UserInfoVO;

@WebServlet(name="/UserInfoController",urlPatterns= {"/user/*"})
public class UserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoService uis = new UserInfoServiceImpl();
	private Gson gs = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
//		
//		System.out.println("2.userinfoCondoGet");
//		System.out.println(request.getCharacterEncoding());
		String cmd = request.getRequestURI().substring(6);
		System.out.println(cmd);
		if("list".equals(cmd))
		{
			List<UserInfoVO> uiList = uis.getUserList(null); 
			response.getWriter().print(gs.toJson(uiList));
		}
		else if("view".equals(cmd))
		{
			int ui_num = Integer.parseInt(request.getParameter("ui_num"));
			UserInfoVO uv = new UserInfoVO();
			uv.setUi_num(ui_num);
			UserInfoVO user = uis.getUserContent(uv);
			response.getWriter().print(gs.toJson(user));
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI().substring(6);
		System.out.println(cmd);
		response.setContentType("application/json;charset=utf-8");
		BufferedReader br = request.getReader();
		String str = null;
		String json = "";
		while((str=br.readLine()) != null)
		{
				json += str;
		}
		UserInfoVO param = gs.fromJson(json, UserInfoVO.class);
		if("insert".equals(cmd)) {
			json = gs.toJson(uis.insertUser(param));
			System.out.println(param);
		}
		else if("delete".equals(cmd)) {
			json = gs.toJson(uis.deleteUser(param));
			System.out.println(param);
		}
		else if("update".equals(cmd)) {
			json = gs.toJson(uis.updateUser(param));
			System.out.println(param);
		}
		response.getWriter().print(json);
	}
}
