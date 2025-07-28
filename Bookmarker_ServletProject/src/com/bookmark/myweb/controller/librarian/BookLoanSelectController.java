package com.bookmark.myweb.controller.librarian;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.BookDAO;
import com.bookmark.myweb.model.BookWithLoanVO;

public class BookLoanSelectController implements CommandController{

	BookDAO dao = new BookDAO();
	
	public String process(HttpServletRequest request, HttpServletResponse response) {

		
		List<BookWithLoanVO> bookWithLoans = dao.selectAllLoanBooks();
		
		// 연체일 계산
		for(BookWithLoanVO loan : bookWithLoans) {
			int daysOver = (int) ChronoUnit.DAYS.between(loan.getReturnDate().toLocalDate(), LocalDate.now());
			loan.setDaysOver(daysOver);
		}
		
		
		if(bookWithLoans != null) {
			request.setAttribute("bookWithLoans", bookWithLoans);

		} else {
			request.setAttribute("noBook", "대출 내역이 없습니다.");
		}
		

		return "librarian/loanBooks.jsp"; 
	}
	
}
