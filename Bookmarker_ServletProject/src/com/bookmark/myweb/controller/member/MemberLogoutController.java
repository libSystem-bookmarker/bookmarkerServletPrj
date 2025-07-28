package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;

/**
 * @author ys.kim
 * 로그아우 컨트롤러
 *
 */
public class MemberLogoutController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "redirect:/index.do";
	}

}
