package com.mvn.test.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.mvn.test.service.PhotoBoardService;
import com.mvn.test.service.Impl.PhotoBoardServiceImpl;

@WebServlet("/pb/*")
public class PhotoBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhotoBoardService pbs = new PhotoBoardServiceImpl();
    private Gson gson = new Gson();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		String cmd = request.getRequestURI().substring(4);
		System.out.println(cmd);
		PrintWriter pw = response.getWriter();
		if("list".equals(cmd)) {
			List<Map<String,String>> pbList = pbs.getPhotoList(null);
			System.out.println(pbList.get(0));
			pw.print(gson.toJson(pbList));
		}
		else if("content".equals(cmd)) {
			String pb_num = request.getParameter("pb_num");
			Map<String,String> pBoard = new HashMap<>();
			pBoard.put("pbNum", pb_num);
			Map<String,String> pMap = pbs.getPhotoContent(pBoard);
			System.out.println(pMap);
			pw.print(gson.toJson(pMap));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI().substring(4);
		String json = "";
		System.out.println(cmd);
		int memSize = 1024 * 1024*5;	//5mb
		int totalSize = 1024 * 1024 * 400; //400mb
		int fileSize = 1024 * 1024 * 400;	//400mb
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(memSize);
		dfif.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setFileSizeMax(fileSize);	
		sfu.setSizeMax(totalSize);
		System.out.println(request);
		if("insert".equals(cmd)) {
		boolean isForm = ServletFileUpload.isMultipartContent(request);
		if(isForm) {
			try {
				List<FileItem> fList = sfu.parseRequest(request);
				Map<String,Object> pBoard = new HashMap<>();
				for(FileItem fi : fList) {
					String key = fi.getFieldName();
					if(fi.isFormField()) {
						String value = fi.getString("utf-8");
						pBoard.put(key, value);
					} else {
						pBoard.put(key, fi);
					}
				}
				System.out.println(pBoard);
				pbs.insertPhoto(pBoard);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		} else {
			throw new ServletException("파일 형식이 잘못되었습니다");
		}
		}
		else if("delete".equals(cmd)) {
			Map<String,Object> param = gson.fromJson(json, Map.class);
			System.out.println(param);
			json = gson.toJson(pbs.deletePhoto(param));
		}
	}

}
