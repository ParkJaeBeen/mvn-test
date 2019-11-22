package com.mvn.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mvn.test.vo.PhotoBoardVO;

public interface PhotoBoardDAO {
	public List<PhotoBoardVO> getPhotoBoard(SqlSession ss);
	public PhotoBoardVO getPhotoContent(PhotoBoardVO pBoard,SqlSession ss);
	public int insertPhoto(SqlSession ss,PhotoBoardVO pbvo);
	public int deletePhoto(SqlSession ss,PhotoBoardVO pBoard);
	public int updatePhoto(SqlSession ss,PhotoBoardVO pBoard);
}
