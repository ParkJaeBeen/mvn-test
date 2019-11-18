package com.mvn.test.service.Impl;

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

}
