package com.mvn.test.service;

import java.util.List;
import java.util.Map;

import com.mvn.test.vo.UserInfoVO;

public interface UserInfoService {
	public List<UserInfoVO> getUserList(Map<String,String> pBoard);
	public UserInfoVO getUserContent(UserInfoVO pBoard);
	public Map<String,String> insertUser(UserInfoVO pBoard);
	public Map<String,String> deleteUser(UserInfoVO pBoard);
	public Map<String,String> updateUser(UserInfoVO pBoard);
}
