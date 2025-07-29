package com.bookmark.myweb.controller.librarian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.BookDAO;
import com.bookmark.myweb.model.BookWithCategoryVO;
import com.bookmark.myweb.model.CategoryVO;
import com.bookmark.myweb.service.BookService;

public class BookSelectController implements CommandController {

	BookService bookService = new BookService();
	BookDAO dao = new BookDAO();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		
		List<CategoryVO> categoryList = bookService.getCategoryAll();
		request.setAttribute("categoryList", categoryList);
		
		
		
		// 카테고리 선택시
		
		
		// 검색 시
		
		
		
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
