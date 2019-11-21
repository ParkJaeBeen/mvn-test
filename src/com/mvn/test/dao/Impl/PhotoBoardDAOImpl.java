package com.mvn.test.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.PhotoBoardDAO;

public class PhotoBoardDAOImpl implements PhotoBoardDAO {

	@Override
	public List<Map<String, String>> getPhotoBoard(SqlSession ss) {
		return ss.selectList("PhotoBoard.selectPhotoBoard");
	}

	@Override
	public Map<String, String> getPhotoContent(Map<String, String> pBoard,SqlSession ss) {
		return ss.selectOne("PhotoBoard.selectPhotoContent",pBoard);
	}

	@Override
	public int insertPhoto(SqlSession ss, Map<String,String> pBoard) {
		return ss.insert("PhotoBoard.insertPhotoBoard",pBoard);
	}

	@Override
	public int deletePhoto(SqlSession ss, Map<String,Object> pBoard) {
		return ss.delete("PhotoBoard.deletePhotoBoard",pBoard);
	}

	@Override
	public int updatePhoto(SqlSession ss, Map<String,String> pBoard) {
		return 0;
	}
	
	public static void main(String[] args) {
		PhotoBoardDAO pbd = new PhotoBoardDAOImpl();
		SqlSession ss = InitServlet.getSqlSession();
		Map<String,String> pBoard = new HashMap<>();
		pBoard.put("pbNum", "2");
		String ss1= ss.selectOne("PhotoBoard.selectPhotoContent",pBoard);
		System.out.println(ss1);
	}
}
