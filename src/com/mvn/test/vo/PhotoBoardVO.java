package com.mvn.test.vo;

import org.apache.commons.fileupload.FileItem;

import lombok.Data;

@Data
public class PhotoBoardVO {
	private Integer pbNum;
	private String pbContent;
	private String pbImg1;
	private String pbImg2;
	private FileItem pbImgFile1;
	private FileItem pbImgFile2;
	private String pbTitle;
	private String credat;
	private String cretim;
	private Integer creusr;
	private String moddat;
	private String modtim;
	private Integer modusr;
	private Integer pbCnt;
	
	public static void main(String[] args) {
		PhotoBoardVO uv = new PhotoBoardVO();
		System.out.println(uv);
	}
}
