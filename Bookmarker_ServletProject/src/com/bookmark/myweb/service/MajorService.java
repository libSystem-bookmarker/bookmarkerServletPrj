package com.bookmark.myweb.service;

import java.util.List;
import java.util.Map;

import com.bookmark.myweb.dao.MajorDAO;
import com.bookmark.myweb.model.MajorVO;

public class MajorService {
	
	private MajorDAO dao = new MajorDAO();
	
	public Map<String, String> getDeptNameAndFacultyName(int unitId) {
		return dao.getDeptAndFaculty(unitId);
	}

	public List<MajorVO> getDepartmentsByFacultyId(int facultyId) {
	    return dao.getDepartments(facultyId);
	}

	public List<MajorVO> getFacultyList() {
	    return dao.selectFacultyList(); // 예시: DAO에서 학부만 조회
	}


}
