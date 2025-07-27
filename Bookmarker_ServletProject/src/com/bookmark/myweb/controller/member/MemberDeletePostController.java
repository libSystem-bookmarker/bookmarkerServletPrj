package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.AdminMemberDAO;

/**
 * @author bs.kim
 * @param request - 삭제할 사용자 ID
 * @return 삭제 성공 시 success.jsp, 실패 시 error.jsp로 포워딩
 * 실제로 회원 정보를 삭제하는 컨트롤러
 */
public class MemberDeletePostController implements CommandController {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userid");
        AdminMemberDAO dao = new AdminMemberDAO();

        int result = dao.deleteMember(userId);

        if (result == 1) {
            request.setAttribute("msg", "회원 삭제 성공");
            return "/WEB-INF/views/common/success.jsp";
        } else {
            request.setAttribute("msg", "회원 삭제 실패");
            return "/WEB-INF/views/common/error.jsp";
        }
    }
}
