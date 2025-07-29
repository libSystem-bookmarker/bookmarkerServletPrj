package com.bookmark.myweb.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.service.AdminMemberService;

public class FindPasswordPostController implements CommandController {

    private AdminMemberService memberService = new AdminMemberService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            boolean exists = memberService.existsUser(userId);

            if (exists) {
                request.setAttribute("userId", userId);
            } else {
                request.setAttribute("errorMsg", "존재하지 않는 사용자입니다.");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "잘못된 사용자 ID입니다.");
        } catch (Exception e) {
            request.setAttribute("errorMsg", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return "common/findPwResult.jsp";  // DispatcherServlet 기준
    }
}
