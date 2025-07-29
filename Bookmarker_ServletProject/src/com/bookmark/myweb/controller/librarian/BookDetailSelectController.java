package com.bookmark.myweb.controller.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.BookDAO;
import com.bookmark.myweb.model.BookWithCategoryVO;

public class BookDetailSelectController implements CommandController {

	
	// 조회는 dao로 바로 조회
	BookDAO dao = new BookDAO();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		BookWithCategoryVO book = dao.selectBookById(bookId);

		request.setAttribute("book", book);
		
		
		return "librarian/bookDetail.jsp";
	}

}
