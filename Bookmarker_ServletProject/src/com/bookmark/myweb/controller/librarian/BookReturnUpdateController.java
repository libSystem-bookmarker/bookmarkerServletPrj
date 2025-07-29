package com.bookmark.myweb.controller.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.service.BookService;

public class BookReturnUpdateController implements CommandController{
	
	BookService bookService = new BookService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		int bookLoanDetailId = Integer.parseInt(request.getParameter("bookLoanDetailId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		System.out.println("bookLoanDetailId: " + bookLoanDetailId);
		System.out.println("bookId: " + bookId);
		
		bookService.updateReturnBookById(bookLoanDetailId, bookId);
	
		return "redirect:/selectLoanBooks.do";
	}

}
