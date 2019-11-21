package com.mvn.test.service;

import java.util.List;
import java.util.Map;

public interface PhotoBoardService {
	public List<Map<String, String>> getPhotoList(Map<String,String> pBoard);
	public Map<String, String> getPhotoContent(Map<String, String> pBoard);
	public Map<String,String> insertPhoto(Map<String, Object> pBoard);
	public Map<String,String> deletePhoto(Map<String, Object> pBoard);
	public Map<String,String> updatePhoto(Map<String, Object> pBoard);

}
