package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookmark.myweb.common.CommandController;

/**
 * @author bs.kim
 * @return 회원 삭제 폼 페이지로 이동 (deleteMember.jsp)
 * 회원 삭제 요청 화면으로 이동하는 컨트롤러
 */
public class MemberDeleteGetController implements CommandController {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/views/member/deleteMember.jsp";
    }
}
