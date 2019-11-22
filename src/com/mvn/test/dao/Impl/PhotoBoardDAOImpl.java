package com.mvn.test.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.PhotoBoardDAO;
import com.mvn.test.vo.PhotoBoardVO;
import com.mvn.test.vo.UserInfoVO;

public class PhotoBoardDAOImpl implements PhotoBoardDAO {

	@Override
	public List<PhotoBoardVO> getPhotoBoard(SqlSession ss) {
		return ss.selectList("PhotoBoard.selectPhotoBoard");
	}

	@Override
	public PhotoBoardVO getPhotoContent(PhotoBoardVO pBoard,SqlSession ss) {
		return ss.selectOne("PhotoBoard.selectPhotoContent",pBoard);
	}

	@Override
	public int insertPhoto(SqlSession ss, PhotoBoardVO pbvo) {
		return ss.insert("PhotoBoard.insertPhotoBoard",pbvo);
	}

	@Override
	public int deletePhoto(SqlSession ss,PhotoBoardVO pBoard) {
		return ss.delete("PhotoBoard.deletePhotoBoard",pBoard);
	}

	@Override
	public int updatePhoto(SqlSession ss, PhotoBoardVO pBoard) {
		return ss.update("PhotoBoard.updatePhotoBoard",pBoard);
	}
	
	public static void main(String[] args) {
		PhotoBoardDAO pbd = new PhotoBoardDAOImpl();
		SqlSession ss = InitServlet.getSqlSession();
		PhotoBoardVO pbv = new PhotoBoardVO();
		pbv.setPbTitle("123123");
		pbv.setPbContent("123123123");
		pbv.setPbNum(24);
		pbv.setCreusr(3);
		pbv.setPbImg1("123.jpg");
		pbv.setPbImg1("123.jpg");
		System.out.println(ss.update("PhotoBoard.updatePhotoBoard",pbv));
		
	}
}
