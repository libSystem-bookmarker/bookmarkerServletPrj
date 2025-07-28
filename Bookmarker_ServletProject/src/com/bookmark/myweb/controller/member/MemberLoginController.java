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
				HttpSession session = request.getSession(); //get session
				session.setAttribute("loginMember", loginMember); //로그인한 사용자 정보 세션에 저장
				return "redirect:/index.do";
			} else {
				return "redirect:/loginForm.do";
			}
		} catch (RuntimeException e) {
			System.out.println("login Process Error: "+e.getMessage());
		}
		return "redirect:/index.do";
	}}
