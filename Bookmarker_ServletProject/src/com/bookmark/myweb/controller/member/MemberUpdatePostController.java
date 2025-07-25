package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookmark.myweb.common.CommandController;

public class MemberUpdatePostController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		//session
		HttpSession session = request.getSession();
		String userRole = (String) session.getAttribute("role");
		if (userRole.equals("admin")) {
			//
			return null;
		} else if (userRole.equals("student")) {
			return null;
		} else if (userRole.equals("librarian")) {
			//
		}
		return null;
		
		
	}

}
