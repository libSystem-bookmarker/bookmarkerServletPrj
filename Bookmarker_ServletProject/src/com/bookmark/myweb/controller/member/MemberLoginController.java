package com.bookmark.myweb.controller.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.service.AdminMemberService;

// 예시 LoginController 내부
public class MemberLoginController implements CommandController {

	/**
	 * edit date: 2025-07-28
	 * @author ys.kim
	 * 로그인 메서드 수정 및 서비스, dao 별 기능 분리
	 * 리다이렉트 url 수정
	 */
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("enter the login controller");
		//사용자 아이디 및 비밀번호, 세션
		String userId = request.getParameter("userId");
		String password = request.getParameter("pw");
		
		//memberService -> login method
		AdminMemberService adminMemberService = new AdminMemberService();
		//memberVO -> get login user info
		MemberVO loginMember = adminMemberService.login(Integer.parseInt(userId), password);
		
		try {
			//check login is null
			if(loginMember != null) {
				 request.getSession().setAttribute("loginMember", loginMember);
				return "redirect:/index.do";
			} else {
				return "redirect:/loginForm.do";
			}
		} catch (RuntimeException e) {
			System.out.println("login Process Error: "+e.getMessage());
		}
		return "redirect:/index.do";
	}
	/**
	 * 로그인 처리 컨트롤러
	 * 
	 * 사용자로부터 전달받은 아이디와 비밀번호를 확인하여 
	 * 로그인 성공 시 세션에 사용자 아이디를 저장하고 메인 페이지로 이동.
	 * 로그인 실패 시 로그인 폼으로 이동하며 실패 메시지를 전달한다.
	 * 
	 * @author bs.kim
	 * @return 로그인 성공 시 main.jsp, 실패 시 loginForm.jsp로 포워딩한다..
	 */
//    @Override
//    public String process(HttpServletRequest request, HttpServletResponse response) {
//        String userid = request.getParameter("userid");
//        String password = request.getParameter("password");
//
//        AdminMemberDAO dao = new AdminMemberDAO(); 
//        String dbpw = dao.getPassword(userid);
//        
//        if (dbpw != null && dbpw.equals(password)) {
//            HttpSession session = request.getSession();
//            session.setAttribute("userid", userid);
//            return "/index.jsp";
//        } else {
//            request.setAttribute("msg", "로그인 실패");
//            return "/WEB-INF/views/common/loginForm.jsp";
//        }
//    }
	
	
}