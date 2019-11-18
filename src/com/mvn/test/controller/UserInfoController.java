package com.mvn.test.controller;

import java.io.IOException;
import java.util.List;

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
		System.out.println("2.userinfoCondoGet");
		System.out.println(request.getCharacterEncoding());
		List<UserInfoVO> uiList = uis.getUserList(null); 
		
		response.getWriter().print(gs.toJson(uiList));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
