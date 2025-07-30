package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.service.AdminMemberService;

public class MemberDeletePostController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberDeletePostController 호출");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("받은 userId: " + userId);
		
		AdminMemberService adminService = new AdminMemberService();

		try {
			adminService.deleteMember(userId);
            return "redirect:/myPage.do";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "회원 삭제 실패: " + e.getMessage());
            return "/error.jsp";
        }
		
	}
}
