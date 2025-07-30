package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.service.AdminMemberService;

public class MemberUpdateGetController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberUpdateGetController 호출됨");

		int userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("받은 userId: " + userId);

		AdminMemberService adminService = new AdminMemberService();
		MemberVO member = adminService.selectMemberInfo(userId);

		request.setAttribute("member", member);

		return "admin/updateMember.jsp";
	}

}
