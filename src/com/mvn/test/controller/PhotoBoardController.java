package com.mvn.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvn.test.common.ServletFileUtil;
import com.mvn.test.service.PhotoBoardService;
import com.mvn.test.service.Impl.PhotoBoardServiceImpl;
import com.mvn.test.vo.PhotoBoardVO;

@WebServlet("/pboard/*")
public class PhotoBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhotoBoardService pbs = new PhotoBoardServiceImpl();
    private Gson gson = new Gson();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		String cmd = request.getRequestURI().substring(8);
		System.out.println(cmd);
		String json = "";
		PrintWriter pw = response.getWriter();
		if("list".equals(cmd)) {
			List<PhotoBoardVO> pbList = pbs.getPhotoList(null);
			System.out.println(pbList.get(0));
			pw.print(gson.toJson(pbList));
		}
		else if("content".equals(cmd)) {
			int pb_num = Integer.parseInt(request.getParameter("pb_num"));
			PhotoBoardVO pBoard = new PhotoBoardVO();
			pBoard.setPbNum(pb_num);
			json = gson.toJson(pbs.getPhotoContent(pBoard));
			pw.print(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		String cmd = request.getRequestURI().substring(8);
		String json = "";
		String str = null;
		System.out.println(cmd);
		
		if("insert".equals(cmd)) { 
			Map<String,Object> param = ServletFileUtil.parseRequest(request);
			Map<String,String> rMap = pbs.insertPhoto(param);
			response.getWriter().print(gson.toJson(rMap));
		}
		else if("delete".equals(cmd)) {
			BufferedReader br = request.getReader();
			while((str=br.readLine())!=null) {
				json += str;
			}
			PhotoBoardVO pbv = gson.fromJson(json, PhotoBoardVO.class);
			Map<String,String> rMap = pbs.deletePhoto(pbv);
			response.getWriter().print(gson.toJson(rMap));
		}
		else if("update".equals(cmd)) { 
			Map<String,Object> param = ServletFileUtil.parseRequest(request);
			Map<String,String> rMap = pbs.updatePhoto(param);
			response.getWriter().print(gson.toJson(rMap));
		}
	}
	public static void main(String[] args) {
		
	}
}
