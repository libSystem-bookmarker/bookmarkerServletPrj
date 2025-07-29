package com.bookmark.myweb.controller.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.BookDAO;

public class BookLoanInsertPostController implements CommandController{

	BookDAO dao = new BookDAO();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		

//        int userId = Integer.parseInt(request.getParameter("userId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        int userId = 12;
        
        int result = dao.insertBookLoanDetail(userId, bookId);
		
        
        if (result > 0) {
    		// 마이 페이지 같은 곳
    		return "redirect:/selectBooks.do";
        } else {
            return "redirect:/selectLoanBooks.do";
        }
		
		
		

	}
}
