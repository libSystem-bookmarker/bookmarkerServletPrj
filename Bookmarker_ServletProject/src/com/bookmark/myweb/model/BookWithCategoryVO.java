package com.bookmark.myweb.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookWithCategoryVO {
	
	private int bookId; //pk
	private int categoryId; //fk
	private String categoryName; //카테고리 이름
	private String title; //제목
	private String author; //작가
	private String publisher; //출판사
	private int totalCount; //소장 책 수량
	private Date createAt; //출판일

}
