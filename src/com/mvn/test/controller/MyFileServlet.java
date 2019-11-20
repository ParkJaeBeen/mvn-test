package com.mvn.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/textfile")
public class MyFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mSize = 1024*1024*7;
		int tSize = 1024*1024*100;
		int fSize = 1024*1024*15;
		
		DiskFileItemFactory df = new DiskFileItemFactory();
		df.setSizeThreshold(mSize);
		df.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload su = new ServletFileUpload(df);
		su.setFileSizeMax(fSize);
		su.setSizeMax(tSize);
		boolean isForm = ServletFileUpload.isMultipartContent(request);
		if(isForm) {
			List<FileItem> fList;
			try {
				fList = su.parseRequest(request);
				for(FileItem fi : fList) {
					String key = fi.getFieldName();
					if(fi.isFormField()) {	
						String value = fi.getString("utf=8");
						System.out.println(key+":"+value);
					} else {
						String path = "C:\\Users\\Administrator\\eclipse-workspace\\mvn-test\\WebContent\\text";
						String textfile = fi.getName();
						File targetFile = new File(path+"\\"+textfile);
						fi.write(targetFile);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			throw new ServletException("파일 형식이 잘못되었습니다");
		}
	}

}
