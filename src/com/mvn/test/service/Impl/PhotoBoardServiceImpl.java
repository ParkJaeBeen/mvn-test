package com.mvn.test.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;

import com.mvn.test.common.ServletFileUtil;
import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.PhotoBoardDAO;
import com.mvn.test.dao.Impl.PhotoBoardDAOImpl;
import com.mvn.test.service.PhotoBoardService;
import com.mvn.test.vo.PhotoBoardVO;
import com.mvn.test.vo.UserInfoVO;

public class PhotoBoardServiceImpl implements PhotoBoardService {
	private PhotoBoardDAO pbd = new PhotoBoardDAOImpl();
	private String path = "C:\\Users\\Administrator\\eclipse-workspace\\mvn-test\\WebContent\\img\\";
	
	@Override
	public List<PhotoBoardVO> getPhotoList(Map<String, String> pBoard) {
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
	public PhotoBoardVO getPhotoContent(PhotoBoardVO pBoard) {
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
		PhotoBoardVO pbvo = new PhotoBoardVO();
		pbvo.setPbTitle((String)pBoard.get("pbTitle"));
		pbvo.setPbContent((String)pBoard.get("pbContent"));
		pbvo.setCreusr(Integer.parseInt((String)pBoard.get("creusr")));
		
		Map<String,String> rMap = new HashMap<>();
		rMap.put("msg","실패");
		rMap.put("result", "false");
		SqlSession ss = InitServlet.getSqlSession();
		try {
			if(pBoard.get("pbImg1")!=null) {
				FileItem fi = (FileItem) pBoard.get("pbImg1");
				String fileName = ServletFileUtil.saveFile(fi);
				pbvo.setPbImg1("/img/"+fileName);
			}
			if(pBoard.get("pbImg2")!=null) {
				FileItem fi = (FileItem) pBoard.get("pbImg2");
				String fileName = ServletFileUtil.saveFile(fi);
				pbvo.setPbImg2("/img/"+fileName);
			}
			int cnt = pbd.insertPhoto(ss, pbvo);
			if(cnt==1) {
				rMap.put("msg","성공");
				rMap.put("result", "true");
			}
			ss.commit();
		}catch(Exception e) {
			ss.rollback();
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return rMap;		
	}

	@Override
	public Map<String, String> deletePhoto(PhotoBoardVO pBoard) {
		SqlSession ss = InitServlet.getSqlSession();
		Map<String,String> rMap = new HashMap<>();
		try {
			rMap.put("msg", "실패");
			rMap.put("url", "/views/pboard/pblist");
			if(pbd.deletePhoto(ss, pBoard) == 1) {
				rMap.put("msg", "성공");
				rMap.put("url", "/views/pboard/pblist");
			}
			ss.commit();
			return rMap;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public Map<String, String> updatePhoto(Map<String, Object> pBoard) {
		PhotoBoardVO pbvo = new PhotoBoardVO();
		pbvo.setPbTitle((String)pBoard.get("pbTitle"));
		pbvo.setPbContent((String)pBoard.get("pbContent"));
		pbvo.setCreusr(Integer.parseInt((String)pBoard.get("creusr")));
		pbvo.setPbNum(Integer.parseInt((String)pBoard.get("pbNum")));
		
		Map<String,String> rMap = new HashMap<>();
		rMap.put("msg","실패");
		rMap.put("url", "/views/pboard/pblist");
		SqlSession ss = InitServlet.getSqlSession();
		try {
			if(pBoard.get("pbImg1")!=null) {
				FileItem fi = (FileItem) pBoard.get("pbImg1");
				String fileName = ServletFileUtil.saveFile(fi);
				pbvo.setPbImg1("/img/"+fileName);
			}
			if(pBoard.get("pbImg2")!=null) {
				FileItem fi = (FileItem) pBoard.get("pbImg2");
				String fileName = ServletFileUtil.saveFile(fi);
				pbvo.setPbImg2("/img/"+fileName);
			}
			int cnt = pbd.updatePhoto(ss, pbvo);
			if(cnt==1) {
				rMap.put("msg","성공");
				rMap.put("url", "/views/pboard/pblist");
			}
			ss.commit();
		}catch(Exception e) {
			ss.rollback();
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return rMap;		
	}
	
	public static void main(String[] args) {
		PhotoBoardService pbs = new PhotoBoardServiceImpl();
		PhotoBoardVO pbvo = new PhotoBoardVO();
		Map<String,Object> aa = new HashMap<>();
		aa.put("pbTitle","1231231");
		aa.put("pbContent","1231231");
		aa.put("creusr","1");
		aa.put("pbNum", 43);
		SqlSession ss = InitServlet.getSqlSession();
		System.out.println(pbs.updatePhoto(aa));
	}
}
