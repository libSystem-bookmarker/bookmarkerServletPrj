package com.bookmark.myweb.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandController {
	String process(HttpServletRequest request, HttpServletResponse response);
}
