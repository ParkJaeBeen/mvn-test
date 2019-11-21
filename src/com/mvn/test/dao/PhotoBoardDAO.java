package com.mvn.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface PhotoBoardDAO {
	public List<Map<String, String>> getPhotoBoard(SqlSession ss);
	public Map<String, String> getPhotoContent(Map<String, String> pBoard,SqlSession ss);
	public int insertPhoto(SqlSession ss,Map<String,String> pBoard);
	public int deletePhoto(SqlSession ss,Map<String, Object> pBoard);
	public int updatePhoto(SqlSession ss,Map<String,String> pBoard);
}
