package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;

public class MemberSelectNameController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberSelectNameController 왓서요");
		return null;
	}

}