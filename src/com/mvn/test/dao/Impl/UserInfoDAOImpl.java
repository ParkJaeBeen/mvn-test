package com.mvn.test.dao.Impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.UserInfoDAO;
import com.mvn.test.vo.UserInfoVO;

public class UserInfoDAOImpl implements UserInfoDAO {
//	private SqlSessionFactory ssf;
	
	@Override
	public List<UserInfoVO> selectUserList(Map<String, String> pUser) {
		SqlSession ss = InitServlet.getSqlSession();	//연결할 세션을 만들어 놓는 것
		try {
			return  ss.selectList("UserInfo.selectUserList",pUser); //UserInfo.xml 의 namespace.id	 - 이상황에서 sql문을 읽어들여서 실행함
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		UserInfoDAO ud = new UserInfoDAOImpl();
		System.out.println(ud.selectUserList(null));
	}

}
