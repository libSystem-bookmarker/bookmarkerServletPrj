package com.bookmark.myweb.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.service.AdminMemberService;

public class MemberSelectNameController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberSelectNameController 왓서요");
		String role = request.getParameter("role");
	    String keyword = request.getParameter("searchKeyword");

	    AdminMemberService service = new AdminMemberService();
	    List<MemberVO> members = service.selectMembers(role, keyword);

	    request.setAttribute("selectMembersList", members);
	    request.setAttribute("includePage", "/admin/memberList.jsp");
	    return "member/memberInfo.jsp"; // 전체 레이아웃 JSP
	}

}