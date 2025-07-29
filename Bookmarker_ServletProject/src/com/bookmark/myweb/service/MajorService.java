package com.bookmark.myweb.service;

import java.util.Map;

import com.bookmark.myweb.dao.MajorDAO;

public class MajorService {
	
	private MajorDAO dao = new MajorDAO();
	
	public Map<String, String> getDeptNameAndFacultyName(int unitId) {
		return dao.getDeptAndFaculty(unitId);
	}

}
