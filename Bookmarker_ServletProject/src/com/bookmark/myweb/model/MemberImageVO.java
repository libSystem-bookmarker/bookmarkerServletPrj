package com.bookmark.myweb.model;

import java.sql.Date;

import lombok.Data;
@Data
public class MemberImageVO {
	
	private int imangeId;
	private int userId;
	private String fileName; //파일명
	private String filePath; //파일 경로
	private Date uploadDate;

}
