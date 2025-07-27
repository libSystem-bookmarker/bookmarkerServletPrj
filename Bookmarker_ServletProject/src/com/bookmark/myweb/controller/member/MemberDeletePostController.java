package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.AdminMemberDAO;

public class MemberDeletePostController implements CommandController {
	/**
	 * 회원 탈퇴 처리(POST 방식)에 대한 컨트롤러
	 * 
	 * 사용자가 입력한 비밀번호가 DB와 일치하면 회원 탈퇴 처리 및 세션 종료 후 메인으로 이동.
	 * 비밀번호가 일치하지 않으면 예외 발생.
	 * 로그인하지 않은 경우도 예외 처리.
	 * 
	 * @author bs.kim
	 * @return "/"로 리다이렉트 또는 예외 발생
	 */
    AdminMemberDAO dao = new AdminMemberDAO();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");

        if (userid != null) {
            String password = request.getParameter("password");
            String dbpw = dao.getPassword(userid);  // DB에서 비밀번호 조회

            if (dbpw != null && dbpw.equals(password)) {
                dao.deleteMember(userid);   // DB에서 회원 삭제
                session.invalidate();       // 세션 무효화
                return "redirect:/";        // 메인으로 리다이렉트
            } else {
                throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new RuntimeException("로그인된 사용자만 탈퇴할 수 있습니다.");
        }
    }
}
