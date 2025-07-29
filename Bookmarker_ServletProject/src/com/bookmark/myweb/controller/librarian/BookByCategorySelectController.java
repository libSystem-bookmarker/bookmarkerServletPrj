package com.bookmark.myweb.controller.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;

public class BookByCategorySelectController implements CommandController{
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		
		
		
		
		return "librarian/selectBooks.do";
	}
}
