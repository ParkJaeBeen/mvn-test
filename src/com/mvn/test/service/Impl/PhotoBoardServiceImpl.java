package com.mvn.test.service.Impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.PhotoBoardDAO;
import com.mvn.test.dao.Impl.PhotoBoardDAOImpl;
import com.mvn.test.service.PhotoBoardService;

public class PhotoBoardServiceImpl implements PhotoBoardService {
	private PhotoBoardDAO pbd = new PhotoBoardDAOImpl();
	private String path = "C:\\Users\\Administrator\\eclipse-workspace\\mvn-test\\WebContent\\img\\";
	
	@Override
	public List<Map<String, String>> getPhotoList(Map<String, String> pBoard) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			return pbd.getPhotoBoard(ss);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public Map<String, String> getPhotoContent(Map<String, String> pBoard) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			return pbd.getPhotoContent(pBoard,ss);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public Map<String, String> insertPhoto(Map<String, Object> pBoard) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			String pbTitle = (String) pBoard.get("pbTitle");
			String pbContent = (String) pBoard.get("pbContent");
			FileItem pbImg1 = (FileItem) pBoard.get("pbImg1");
			FileItem pbImg2 = (FileItem) pBoard.get("pbImg2");
			Map<String,String> pbMap = new HashMap<>();
			pbMap.put("pbTitle", pbTitle);
			pbMap.put("pbContent", pbContent);
			pbMap.put("pbImg1", "/img/"+pbImg1.getName());
			pbMap.put("pbImg2", "/img/"+pbImg2.getName());
			int cnt = pbd.insertPhoto(ss, pbMap);
			if(cnt != 1) {
				throw new Exception("저장 실패");
			}
			File targetFile1 = new File(path + pbImg1.getName());
			File targetFile2 = new File(path + pbImg2.getName());
			pbImg1.write(targetFile1);
			pbImg2.write(targetFile2);
			ss.commit();
		} catch(Exception e) {
			ss.rollback();
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public Map<String, String> deletePhoto(Map<String, Object> pBoard) {
		SqlSession ss = InitServlet.getSqlSession();
		Map<String,String> rMap = new HashMap<>();
		try {
			rMap.put("msg", "실패");
			rMap.put("url", "/views/pb/list");
			if(pbd.deletePhoto(ss, pBoard) == 1) {
				rMap.put("msg", "성공");
				rMap.put("url", "/views/pb/list");
			}
			return rMap;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, String> updatePhoto(Map<String, Object> pBoard) {
		return null;
	}
	
	public static void main(String[] args) {
		PhotoBoardService pbs = new PhotoBoardServiceImpl();
		System.out.println(pbs.getPhotoList(null));
	}
}
