package com.mvn.test.vo;

import lombok.Data;

@Data		//get,set,toString method 를 자동생성! lombok의 기능중 하나 (@Data + ctrl + shift + o)
public class UserInfoVO {
	private Integer ui_num;
	private String ui_name;
	private String ui_id;
	private String ui_pwd;
	private String credat;
	private String cretim;
	private String moddat;
	private String modtim;
	private String active;
	
	public static void main(String[] args) {
		UserInfoVO uv = new UserInfoVO();
		System.out.println(uv);
	}
}
