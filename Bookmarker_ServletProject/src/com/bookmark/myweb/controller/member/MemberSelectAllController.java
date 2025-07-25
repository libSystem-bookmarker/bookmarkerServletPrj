package com.bookmark.myweb.controller.member;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.AdminMemberDAO;
import com.bookmark.myweb.model.MemberVO;

public class MemberSelectAllController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberSelectAllController 호출");
		//DAO
		AdminMemberDAO adminMemberDAO = new AdminMemberDAO();
		//get role
		String role = request.getParameter("role");
		//list
		List<MemberVO> selectMembersList;
		role = Objects.requireNonNullElse(role, "").trim();
		if (role.isEmpty()) {
			selectMembersList = adminMemberDAO.selectAllMembers();
		} else {
			selectMembersList = adminMemberDAO.selectRoleMembers(role);
		}
		
		
		//회원 목록 속성 저장
		request.setAttribute("selectMembersList", selectMembersList);
		return "member/memberList";
	}

}
