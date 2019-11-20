package com.mvn.test.controller;

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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.mvn.test.service.FileTestService;
import com.mvn.test.service.Impl.FileTestServiceImpl;


@WebServlet("/file")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FileTestService fts = new FileTestServiceImpl();
    private Gson gs = new Gson();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		String cmd = request.getRequestURI();
		System.out.println(cmd);
		PrintWriter pw = response.getWriter();
		List<Map<String,String>> rtList = fts.selectFileTest(null);
		pw.print(gs.toJson(rtList));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memSize = 1024 * 1024*5;	//5mb
		int totalSize = 1024 * 1024 * 50; //50mb
		int fileSize = 1024 * 1024 * 10;	//10mb
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(memSize);	//메모리에 5MB 정도를 기억해 놓는다 / 기억할수 있는 메모리 용량을 지정하는 것 / 잠깐 기억해놓을 공간!
		dfif.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setFileSizeMax(fileSize);		//서버에 올릴 수 있는 파일 한개의 용량과
		sfu.setSizeMax(totalSize);			//서버에 올릴 수 있는 파일들의 총합 용량을 정하는 것
		boolean isForm = ServletFileUpload.isMultipartContent(request);
		if(isForm) {
			try {
				List<FileItem> fList = sfu.parseRequest(request);
				Map<String,Object> param = new HashMap<>();
				for(FileItem ff: fList) {	
					String key = ff.getFieldName();
					if(ff.isFormField()) {	//true 일때 / FileItem 이 파일을 제외한 그밖의 form 데이터일때
						String value = ff.getString("utf-8");
						param.put(key, value);
						System.out.println(key + " : " + value);
					}else {	//false 일때 / 받아온 데이터가 파일형식일때
//						String path = "C:\\Users\\Administrator\\eclipse-workspace\\mvn-test\\WebContent\\img";
//						String filename = ff.getName();	//위의 path 경로에 업로드된 파일의 이름
//						File targetFile = new File(path + "\\" + filename);	//경로에 역슬래시(\\) 뒤에 내가 업로드하는 사진 이름을 붙여서 경로를 재설정하고
//						ff.write(targetFile);	//그 경로에 write 한다.
						param.put(key, ff);
					}
				}
				System.out.println(param);
				fts.insertFileTest(param);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			throw new ServletException("파일 형식이 잘못되었습니다");
		}
		//System.out.println(isForm);
		
	}
}
