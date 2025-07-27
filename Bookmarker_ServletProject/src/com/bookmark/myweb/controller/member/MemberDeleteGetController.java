package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookmark.myweb.common.CommandController;

public class MemberDeleteGetController implements CommandController {
	/**
	 * 회원 탈퇴 요청(GET 방식)에 대한 컨트롤러
	 * 
	 * 사용자가 로그인 상태일 경우, 비밀번호 확인 폼으로 이동시킨다.
	 * 로그인 상태가 아니라면 로그인 페이지로 리다이렉트한다.
	 * 
	 * @author bs.kim
	 * @return passwordform.jsp 또는 loginForm.jsp로 포워딩
	 */
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");

        if (userid != null) {
            return "common/passwordform.jsp";  // 비밀번호 확인 폼으로 이동
        } else {
            return "common/loginForm.jsp";     // 로그인하지 않았으면 로그인 폼으로 이동
        }
    }
}
