package com.bookmark.myweb.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;

public class FindPasswordGetController implements CommandController {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        // 이 컨트롤러는 단순히 폼 보여주기라 예외 발생 가능성 낮음
        return "common/findPwForm.jsp";  // /WEB-INF/views/common/findPwForm.jsp
    }
}
