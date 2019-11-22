package com.mvn.test.controller;

import java.io.InputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mvn.test.dao.Impl.UserInfoDAOImpl;

@WebServlet(name="Init",urlPatterns= {"/init"},loadOnStartup = 1)	//요청이 일어날때 실행되게 하기 위한 것이 아닌 서버가 켜질때 같이 실행될수 있게 설정해 주는 것
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory ssf;
	static {
		String path="/config/mybatis-config.xml";
		InputStream is = InitServlet.class.getResourceAsStream(path);
		ssf = new SqlSessionFactoryBuilder().build(is);		//SqlSessionFactoryBuilder = build method 외에는 안쓸것이다 / sqlSession 을 만드는 팩토리 를 빌드한다
																									//Factory(Oracle)은 하나만 있으면 된다.
	}
	public void init() {
		
	}
	public static SqlSession getSqlSession() {
		return ssf.openSession();
	}
}
