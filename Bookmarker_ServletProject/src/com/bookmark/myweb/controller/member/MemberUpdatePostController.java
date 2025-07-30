package com.bookmark.myweb.controller.member;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.service.AdminMemberService;

@MultipartConfig // file upload
public class MemberUpdatePostController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

		int userId = loginMember.getUserId();
		String role = loginMember.getRole();

		String name = request.getParameter("name");
		String phone = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		AdminMemberService memberService = new AdminMemberService();

		try {

			memberService.updateMemberInfo(userId, phone, email, address); // 기본 정보 수정
			// 회원 정보 수정 완료 후
			MemberVO updatedMember = memberService.selectMemberInfo(userId); // DB에서 다시 조회
			session.setAttribute("loginMember", updatedMember); // 세션 갱신

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "회원 정보 수정 실패: " + e.getMessage());
			return "/error.jsp";
		}

		return "redirect:/myPage.do";

	}

}
