package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookmark.myweb.common.CommandController;


public class MemberLoginFormController implements CommandController {
	/**
	 * 로그인 폼 요청 컨트롤러
	 * 
	 * 로그인 폼 페이지로 이동시켜 사용자에게 아이디/비밀번호 입력을 받도록 한다.
	 * 
	 * @author bs.kim
	 * @return loginForm.jsp로 포워딩한다..
	 */
    public String process(HttpServletRequest request, HttpServletResponse response) {
    	return "common/loginForm.jsp";
}
}