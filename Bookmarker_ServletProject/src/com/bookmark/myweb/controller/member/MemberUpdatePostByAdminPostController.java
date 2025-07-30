package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.service.AdminMemberService;

public class MemberUpdatePostByAdminPostController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String role = request.getParameter("role");
		String unitId = request.getParameter("unitId");

		String name = request.getParameter("name");
		String phone = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		System.out.println("userId : " + userId);
		System.out.println("role : " + role);
		System.out.println("unitId : " + unitId);
		System.out.println("name : " + name);
		System.out.println("phone : " + phone);
		System.out.println("email : " + email);
		System.out.println("address : " + address);
		
		AdminMemberService memberService = new AdminMemberService();

		try {

			memberService.updateMemberInfoByAdmin(userId, role, unitId, name, phone, email, address); // 기본 정보 수정
			// 회원 정보 수정 완료 후
			MemberVO updatedMember = memberService.selectMemberInfo(userId); // DB에서 다시 조회

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "회원 정보 수정 실패: " + e.getMessage());
			return "/error.jsp";
		}

		return "redirect:/myPage.do";

	}
}
