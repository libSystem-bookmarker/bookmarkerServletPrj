package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.dao.AdminMemberDAO;
import com.bookmark.myweb.common.CommandController;

// 예시 LoginController 내부
public class MemberLoginController implements CommandController {

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