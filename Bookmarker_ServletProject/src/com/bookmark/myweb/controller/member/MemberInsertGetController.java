package com.bookmark.myweb.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MajorVO;
import com.bookmark.myweb.service.MajorService;

public class MemberInsertGetController implements CommandController {
	public String process(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println("MemberInsertGetController 호출됨");
	    MajorService majorService = new MajorService(); // 이거 누락되었을 수도
	    List<MajorVO> facultyList = majorService.getFacultyList(); // 이 메서드가 null 리턴하는지도 확인

	    request.setAttribute("facultyList", facultyList);
	    return "member/insertMember.jsp";
	}
}
