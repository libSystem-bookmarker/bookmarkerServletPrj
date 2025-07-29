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
            String pw = memberService.findPassword(userId);

            if (pw != null) {
                request.setAttribute("pw", pw);
            } else {
                request.setAttribute("errorMsg", "비밀번호를 찾을 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "유효하지 않은 사용자 ID입니다.");
        } catch (Exception e) {
            request.setAttribute("errorMsg", "오류가 발생했습니다. 다시 시도해주세요.");
            e.printStackTrace();  // 로그 출력 (선택사항)
        }
        return "common/findPwResult.jsp";
    }
}
