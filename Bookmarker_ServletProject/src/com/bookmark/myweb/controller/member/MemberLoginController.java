package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.dao.AdminMemberDAO;
import com.bookmark.myweb.common.CommandController;

// 예시 LoginController 내부
public class MemberLoginController implements CommandController {
	/**
	 * 로그인 처리 컨트롤러
	 * 
	 * 사용자로부터 전달받은 아이디와 비밀번호를 확인하여 
	 * 로그인 성공 시 세션에 사용자 아이디를 저장하고 메인 페이지로 이동.
	 * 로그인 실패 시 로그인 폼으로 이동하며 실패 메시지를 전달한다.
	 * 
	 * @author bs.kim
	 * @return 로그인 성공 시 main.jsp, 실패 시 loginForm.jsp로 포워딩
	 */
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        AdminMemberDAO dao = new AdminMemberDAO(); 
        String dbpw = dao.getPassword(userid);
        
        if (dbpw != null && dbpw.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userid", userid);
            return "/WEB-INF/views/common/main.jsp";
        } else {
            request.setAttribute("msg", "로그인 실패");
            return "/WEB-INF/views/common/loginForm.jsp";
        }
    }
}