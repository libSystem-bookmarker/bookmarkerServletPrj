package com.bookmark.myweb.controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.MajorVO;
import com.bookmark.myweb.service.MajorService;
import com.google.gson.Gson;

public class GetDepartmentsController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        String facultyId = request.getParameter("facultyId");
	        MajorService service = new MajorService();
	        List<MajorVO> departments = service.getDepartmentsByFacultyId(Integer.parseInt(facultyId));

	        response.setContentType("application/json; charset=UTF-8");
	        Gson gson = new Gson();
	        response.getWriter().write(gson.toJson(departments));
	    } catch (IOException e) {
	        e.printStackTrace(); // 또는 로깅 처리
	    }

	    return null;
	}


}
