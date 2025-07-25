package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookmark.myweb.common.CommandController;


public class MemberLoginFormController implements CommandController {
	
    public String process(HttpServletRequest request, HttpServletResponse response) {
    	return "common/loginForm.jsp";
}
}