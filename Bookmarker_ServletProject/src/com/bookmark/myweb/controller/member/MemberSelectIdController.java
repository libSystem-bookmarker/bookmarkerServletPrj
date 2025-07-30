package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.service.AdminMemberService;

public class MemberSelectIdController implements CommandController {
	/*
	 * 아이디 클릭 시 관련 회원 정보 조회 컨트롤러
	 * 아이디로 조회하여 사용자에게 회원 정보를 전달한다.
	 */
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberSelectIdController 호출");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("받은 userId: " + userId);
		
		AdminMemberService adminService = new AdminMemberService();
		MemberVO member = adminService.selectMemberInfo(userId);

		request.setAttribute("member", member);
		request.setAttribute("includePage", "/admin/memberList.jsp");
		
		return "redirect:/myPage.do?tab=memberList";
	}

}
