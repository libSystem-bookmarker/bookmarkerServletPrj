package com.bookmark.myweb.controller.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.BookDAO;
import com.bookmark.myweb.service.BookService;

public class BookDeletePostController implements CommandController {
	
	BookService bookService = new BookService();
	BookDAO dao = new BookDAO();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		 try {
	            int bookId = Integer.parseInt(request.getParameter("bookId"));
	            
	            System.out.println(bookId);

	            // 삭제 시도
//	            bookService.deleteBookById(bookId);
	            dao.deleteBookAndLoanDetailByBookId(bookId);
	            
	            // 성공 시 목록 페이지로 리다이렉트
	            return "redirect:/selectBooks.do";

	        } catch (RuntimeException e) {
	            // 실패 시 에러 메시지를 설정하고 에러 페이지로 포워딩
	            request.setAttribute("errorMessage", e.getMessage());
	            return "librarian/selectBooks.jsp";
	        }
		
		
	}

}
