package com.mvn.test.service.Impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.FileTestDAO;
import com.mvn.test.dao.Impl.FileTestDAOImpl;
import com.mvn.test.service.FileTestService;

public class FileTestServiceImpl implements FileTestService {
	private FileTestDAO ftd = new FileTestDAOImpl();
	private String path = "C:\\Users\\Administrator\\eclipse-workspace\\mvn-test\\WebContent\\img\\";
	@Override
	public Map<String, String> insertFileTest(Map<String, Object> param) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			String ftName = (String) param.get("ftName");
			String ftId = (String) param.get("ftId");
			FileItem fi = (FileItem) param.get("ftFile");
			Map<String,String> fileTest = new HashMap<>();
			fileTest.put("ftName", ftName);
			fileTest.put("ftId", ftId);
			fileTest.put("ftFile", "/img/"+fi.getName());
			int cnt = ftd.InsertFileTest(ss, fileTest);
			if(cnt != 1) {	//cnt 가 1이 아닐때  = insert 가 제대로 이루어지지 않았을때 = resultSet 이 1이 아닐때
				throw new Exception("저장 실패");	//1.Exception을 던짐
			}
			File targetFile = new File(path + fi.getName()); //위의 path 경로에 업로드된 파일의 이름
			fi.write(targetFile); //그 경로에 write 한다.	/write 는 Exception 을 강요하는 method / 파일을 저장할때 실패하면 바로 Exception을 내보냄 / 그러므로 35번줄의 commit 은 실패시 실행불가
			ss.commit();
		}catch(Exception e) {
			ss.rollback();	//3. 2번이 실행 된 후 롤백시킴
			e.printStackTrace();	//2.우선적으로 실행되는놈이 이놈
		} finally {
			ss.close();
		}
		return null;
	}
	@Override
	public List<Map<String, String>> selectFileTest(Map<String, String> fileTest) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			return ftd.selectFileTest(ss);
		}catch(Exception e) {
			e.printStackTrace();	//2.우선적으로 실행되는놈이 이놈
		} finally {
			ss.close();
		}
		return null;
		
	}

}
