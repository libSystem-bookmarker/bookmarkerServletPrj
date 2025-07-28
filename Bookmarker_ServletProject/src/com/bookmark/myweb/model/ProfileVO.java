package com.bookmark.myweb.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ProfileVO {
	
    private int imageId;       // PK
    private int userId;        // FK to MEMBER
    private String fileName;   // 저장된 파일 이름 (UUID 포함)
    private String filePath;   // 파일 경로 또는 URL
    private Date uploadDate;

}
