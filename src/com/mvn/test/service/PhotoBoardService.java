package com.mvn.test.service;

import java.util.List;
import java.util.Map;

import com.mvn.test.vo.PhotoBoardVO;
import com.mvn.test.vo.UserInfoVO;

public interface PhotoBoardService {
	public List<PhotoBoardVO> getPhotoList(Map<String,String> pBoard);
	public PhotoBoardVO getPhotoContent(PhotoBoardVO pBoard);
	public Map<String,String> insertPhoto(Map<String, Object> pBoard);
	public Map<String,String> deletePhoto(PhotoBoardVO pbv);
	public Map<String,String> updatePhoto(Map<String, Object> pBoard);

}
