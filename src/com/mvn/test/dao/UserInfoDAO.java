package com.mvn.test.dao;

import java.util.List;
import java.util.Map;

import com.mvn.test.vo.UserInfoVO;

public interface UserInfoDAO 
{
	public List<UserInfoVO> selectUserList(Map<String,String> pUser);
	public UserInfoVO selectUser(UserInfoVO pUser);
	public int insertUser(UserInfoVO pUser);
	public int deleteUser(UserInfoVO pUser);
	public int updateUser(UserInfoVO pUser);
}
