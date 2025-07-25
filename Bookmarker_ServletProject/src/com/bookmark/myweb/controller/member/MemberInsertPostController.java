package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.service.AdminMemberService;

public class MemberInsertPostController implements CommandController {
	
	private AdminMemberService adminMemberService = new AdminMemberService();

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberInsertPostController 호출됨");
		MemberVO member = new MemberVO();

		//role, name, phoneNumber, address, unitId, createdAt
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String unitId = request.getParameter("unitId");
		
		//set vo
		member.setRole(role);
		member.setName(name);
		member.setPhoneNumber(phoneNumber);
		member.setAddress(address);
		member.setEmail(email);
		member.setUnitId(unitId == null || unitId.isEmpty() ? 0 : Integer.parseInt(unitId));
		member.setCreateAt(new java.sql.Date(System.currentTimeMillis()));

		boolean result = adminMemberService.insertMember(member);

		if (result) {
		    return "redirect:/index.do";
		} else {
		   return "redirect:/insertMemberForm.do?error=fail"; // 다시 입력 폼

		}
	}

}

