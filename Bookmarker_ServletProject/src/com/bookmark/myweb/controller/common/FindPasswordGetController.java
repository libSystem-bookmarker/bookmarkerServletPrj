package com.bookmark.myweb.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookmark.myweb.common.CommandController;

public class FindPasswordGetController implements CommandController {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        return "common/findPwForm.jsp";
    }
}
