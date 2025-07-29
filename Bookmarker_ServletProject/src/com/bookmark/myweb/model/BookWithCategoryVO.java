package com.bookmark.myweb.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookWithCategoryVO {
	
	private int bookId; //pk
	private int categoryId; //fk
	private String categoryName; //카테고리 이름
	private String title; //제목
	private String author; //작가
	private String publisher; //출판사
	private int totalCount; //소장 책 수량
	private Date createAt; //출판일
	private String imageUrl; // 이미지 path 추가
	
	
	public BookWithCategoryVO(int bookId, int categoryId, String categoryName, String title, String author,
			String publisher, int totalCount, Date createAt, String imageUrl) {
		this.bookId = bookId;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.totalCount = totalCount;
		this.createAt = createAt;
		this.imageUrl = imageUrl;
	}
	
	
	
	
	
}
