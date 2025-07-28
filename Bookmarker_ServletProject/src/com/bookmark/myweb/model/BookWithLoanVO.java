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
    
    private Date loanDate;
    private Date returnDate;
    private Date dueDate;
    private String isOverdue; // 'Y' or 'N'

}
