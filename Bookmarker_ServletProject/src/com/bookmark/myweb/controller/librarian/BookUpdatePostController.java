package com.bookmark.myweb.controller.librarian;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.BookVO;
import com.bookmark.myweb.service.BookService;

public class BookUpdatePostController implements CommandController{
	
	private BookService bookService= new BookService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		
		// 폼에서 전송된 파라미터 받기
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String totalCount = request.getParameter("totalCount");
		String createAt = request.getParameter("createAt");
		String categoryId = request.getParameter("categoryId");
		
		
		// BookVO 객체 생성 및 값 세팅
		BookVO book = new BookVO();
		book.setBookId(Integer.parseInt(bookId));
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setTotalCount(Integer.parseInt(totalCount));
		book.setCategoryId(Integer.parseInt(categoryId));
		book.setCreateAt(Date.valueOf(createAt));
		
		try {
			bookService.updateBook(book);
			// 도서 상세 페이지로, 임시로 도서 목록 페이지 redirect
			return "redirect:/selectBooks.do";
			
		}catch(RuntimeException e){
			return "librarian/insertBookform.jsp";
		}
		
		
	}

}
