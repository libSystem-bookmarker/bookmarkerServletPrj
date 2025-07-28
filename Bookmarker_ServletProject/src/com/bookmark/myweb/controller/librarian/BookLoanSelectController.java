package com.bookmark.myweb.controller.librarian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.BookWithCategoryVO;

public class BookLoanSelectController implements CommandController{

	
	public String process(HttpServletRequest request, HttpServletResponse response) {


		return "librarian/test.jsp"; 
	}
	
}
