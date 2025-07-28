package com.bookmark.myweb.model;

import java.sql.Date;

import lombok.Data;

@Data
public class BookWithLoanVO {
	
	 // Book 테이블
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private int totalCount;
    private int categoryId;
    private Date createAt;

    // Book_Loan_Detail 테이블
    private int bookLoanDetailId;
    
    // member 테이블
    private int userId;
    private String userName;
    
    private Date loanDate; // 대출일
    private Date returnDate; // 반납 예정일
    private Date dueDate; // 실제 반납일
    private String loanStatus; // 상태 : 대출, 반납, 연체
    private int daysOver = 0; // 연체일

}
