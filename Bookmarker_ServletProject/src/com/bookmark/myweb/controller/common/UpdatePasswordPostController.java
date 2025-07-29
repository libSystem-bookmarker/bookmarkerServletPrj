package com.bookmark.myweb.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.service.AdminMemberService;

public class UpdatePasswordPostController implements CommandController {

    private AdminMemberService memberService = new AdminMemberService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String newPw = request.getParameter("newPw");

            int result = memberService.updatePassword(userId, newPw);
            System.out.println("업데이트 결과: " + result);
            
            if (result > 0) {
                request.setAttribute("successMsg", "비밀번호가 성공적으로 변경되었습니다.");
            } else {
                request.setAttribute("errorMsg", "비밀번호 변경에 실패했습니다.");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "잘못된 사용자 ID입니다.");
        } catch (Exception e) {
            request.setAttribute("errorMsg", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return "common/findPwResult.jsp";
    }
}
