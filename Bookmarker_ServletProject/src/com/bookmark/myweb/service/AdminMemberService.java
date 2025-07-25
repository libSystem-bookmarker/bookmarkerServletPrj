package com.bookmark.myweb.service;

import java.sql.SQLException;

import com.bookmark.myweb.dao.AdminMemberDAO;
import com.bookmark.myweb.model.MemberVO;

public class AdminMemberService {

	private AdminMemberDAO adminMemberDAO = new AdminMemberDAO();

	/**
	 * @author ys.kim
	 * @param member
	 * 회원 등록 확인
	 */
	public boolean insertMember(MemberVO member) {
	    boolean isStudentWithUnit = member.getUnitId() != 0 && "student".equals(member.getRole());
	    boolean isLibrarianWithoutUnit = member.getUnitId() == 0 && "librarian".equals(member.getRole());

	    System.out.println("회원 등록 조건 확인: " + member);

	    if (isStudentWithUnit || isLibrarianWithoutUnit) {
	        int result = adminMemberDAO.insertMember(member);
	        System.out.println("insert 결과: " + result);
	        return result > 0;
	    } else {
	        System.out.println("조건 불충족으로 등록 실패");
	        return false;
	    }
	}
	
	public boolean updateMemberAdmin (MemberVO member) {
	    boolean isStudentWithUnit = member.getUnitId() != 0 && "student".equals(member.getRole());
	    boolean isLibrarianWithoutUnit = member.getUnitId() == 0 && "librarian".equals(member.getRole());

	    System.out.println("회원 수정 조건 확인: " + member);

	    if (isStudentWithUnit || isLibrarianWithoutUnit) {
	        int result = adminMemberDAO.updateMemberAdmin(member);
	        System.out.println("update 결과: " + result);
	        return result > 0;
	    } else {
	        System.out.println("조건 불충족으로 수정 실패");
	        return false;
	    }
	}


}
