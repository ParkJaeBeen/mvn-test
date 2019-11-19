package com.mvn.test.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mvn.test.dao.UserInfoDAO;
import com.mvn.test.dao.Impl.UserInfoDAOImpl;
import com.mvn.test.service.UserInfoService;
import com.mvn.test.vo.UserInfoVO;

public class UserInfoServiceImpl implements UserInfoService {
	private UserInfoDAO uid = new UserInfoDAOImpl();
	@Override
	public List<UserInfoVO> getUserList(Map<String, String> pUser) {
		return uid.selectUserList(pUser);
	}
	@Override
	public UserInfoVO getUserContent(UserInfoVO pUser) {
		return uid.selectUser(pUser);
	}
	@Override
	public Map<String,String> insertUser(UserInfoVO pUser) {
		Map<String,String> rMap = new HashMap<>();
		rMap.put("msg", "저장 실패!");
		rMap.put("url", "/views/user/insert");
		if(uid.insertUser(pUser)==1) 
		{
			rMap.put("msg", "회원가입 성공!");
			rMap.put("url", "/views/user/list");
		}
		return rMap;
	}
	@Override
	public Map<String,String> deleteUser(UserInfoVO pUser) {
		Map<String,String> rMap = new HashMap<>();
		rMap.put("msg", "삭제 실패!");
		rMap.put("url", "/views/user/list");
		if(uid.deleteUser(pUser)==1) 
		{
			rMap.put("msg", "삭제 성공!");
			rMap.put("url", "/views/user/list");
		}
		return rMap;
	}
	@Override
	public Map<String,String> updateUser(UserInfoVO pUser) {
		Map<String,String> rMap = new HashMap<>();
		rMap.put("msg", "수정 실패!");
		rMap.put("url", "/views/user/list");
		if(uid.updateUser(pUser)==1) 
		{
			rMap.put("msg", "수정 성공!");
			rMap.put("url", "/views/user/list");
		}
		return rMap;
	}

}
