package com.bookmark.myweb.controller.librarian;

import java.util.ArrayList;
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
		
		
		List<BookWithCategoryVO> bookList = new ArrayList<BookWithCategoryVO>();
		int categoryId = 0;
		
		// 카테고리 선택시
		String selectedCategoryId = request.getParameter("categoryId");
		String keyword = request.getParameter("keyword");
		
		System.out.println("selectedCategoryId: " + selectedCategoryId); // id는 잘 받음

		
		
		// 검색 시
		// <input> 태그에서 값이 비어 있어도, 그 값은 "" (빈 문자열) 이고, 이는 Java에서 null과 다릅니다.
//		if(keyword != null) {
//			bookList = dao.selectSearchBooks(keyword);
//		} else {
//			if(selectedCategoryId == null || selectedCategoryId.isEmpty()) {
//				bookList = dao.getBookAll();
//			}else {
//				categoryId = Integer.parseInt(selectedCategoryId);
//				bookList = dao.selectBooksByCategory(categoryId);
//			}
//			
//		}

		
		// "" (빈 문자열)은 null이 아니기 때문에 keyword.trim.isEmpty를 and 조건문으로 주었다.
		if (keyword != null && !keyword.trim().isEmpty()) {
		    // 키워드 검색 우선
		    bookList = dao.selectSearchBooks(keyword);
		} else if (selectedCategoryId != null && !selectedCategoryId.isEmpty()) {
		    categoryId = Integer.parseInt(selectedCategoryId);
		    bookList = dao.selectBooksByCategory(categoryId);
		} else {
		    bookList = dao.getBookAll();
		}

		
		
		request.setAttribute("books", bookList);
		request.setAttribute("categoryId", categoryId);


		
		return "librarian/selectBooks.jsp";
		
	}

}
