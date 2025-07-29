package com.bookmark.myweb.controller.member;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.service.MajorService;

public class MemberPageGetController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub//session role에 따라 분기
		MemberVO loginMember = (MemberVO) request.getSession().getAttribute("loginMember");
		System.out.println(loginMember);

		if (loginMember == null) {
			return "common/loginForm.jsp";
		}

		// get Role
		String role = loginMember.getRole();
		String tab = request.getParameter("tab");
		String includePage = "";
		
		if (role.equals("student") && loginMember.getUnitId() > 0) {
			MajorService service = new MajorService();
			Map<String, String> names = service.getDeptNameAndFacultyName(loginMember.getUnitId());

		    request.setAttribute("departmentName", names.get("departmentName"));
		    request.setAttribute("facultyName", names.get("facultyName"));
		}

		if (tab == null || tab.isEmpty() || tab.equals("profile")) {
			includePage = "/member/" + tab + ".jsp";
		} else {
			// get Page
			includePage = "/" + role + "/" + tab + ".jsp";
		}
		
		try {
			if ("true".equals(request.getParameter("isAjax"))) {
				RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views" + includePage);
				disp.forward(request, response);
				return null;
			}
		} catch (Exception e) {
			System.out.println("memberInfoGetController: "+e.getMessage());
			
		}


        System.out.println("MemberPageGetController: "+includePage);
        return "member/memberInfo.jsp";
    
	}

}
