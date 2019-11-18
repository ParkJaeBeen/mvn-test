package com.mvn.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	private String charSet = "utf-8";
	
    public EncodingFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;		//캐스팅을 하는 이유 - 2019.11.18 수업자료 참조
		req.setCharacterEncoding(this.charSet);									//request(요청을 받았을때 설정해주었으니)
		System.out.println("1.doFilter");
		System.out.println(req.getCharacterEncoding());
		HttpServletResponse res = (HttpServletResponse)response;	//	response(응답에서도 설정해줘야 한다.)
//		res.setContentType("application/json;charset="+this.charSet);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String charSet = fConfig.getInitParameter("charSet");
		if(charSet != null) {
			this.charSet = charSet;
		}
		System.out.println(charSet);
	}

}
