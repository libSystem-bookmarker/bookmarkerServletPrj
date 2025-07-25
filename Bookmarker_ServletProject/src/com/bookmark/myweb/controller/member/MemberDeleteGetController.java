package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookmark.myweb.common.CommandController;

public class MemberDeleteGetController implements CommandController {

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
