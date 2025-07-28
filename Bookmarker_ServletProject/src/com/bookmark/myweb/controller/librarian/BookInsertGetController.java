package com.bookmark.myweb.controller.librarian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.BookVO;
import com.bookmark.myweb.model.CategoryVO;
import com.bookmark.myweb.service.BookService;

public class BookInsertGetController implements CommandController {

	
	private BookService bookService = new BookService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
			
		List<CategoryVO> categoryList = bookService.getCategoryAll();
		request.setAttribute("categoryList", categoryList);
		
	
		// parameter로 넘긴 bookId를 받아서 조회하고 보내줘야하는데
		String updateBookId = request.getParameter("bookId");
		

		BookVO book = new BookVO();
		if(updateBookId != null) {
			int bookId = Integer.parseInt(updateBookId);
			book = bookService.selectBookById(bookId);
			request.setAttribute("book", book);
		}else {
			
		}

		
		return "librarian/insertBookform.jsp";
	}

}
