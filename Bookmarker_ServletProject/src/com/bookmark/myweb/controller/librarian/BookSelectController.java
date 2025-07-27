package com.bookmark.myweb.controller.librarian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.BookDAO;
import com.bookmark.myweb.model.BookWithCategoryVO;

public class BookSelectController implements CommandController {

	BookDAO dao = new BookDAO();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		List<BookWithCategoryVO> bookList = dao.getBookAll();
		
		if(bookList != null) {
			request.setAttribute("books", bookList);
			

		}else {
			System.out.println("책이 없습니다~");
			request.setAttribute("message", "등록된 도서가 없습니다.");
		}
		
		return "librarian/selectBooks.jsp";
	}

}
