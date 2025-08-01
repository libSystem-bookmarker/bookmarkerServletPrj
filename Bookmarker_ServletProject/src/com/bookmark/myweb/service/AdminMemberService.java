package com.bookmark.myweb.service;

import java.util.List;

import com.bookmark.myweb.dao.AdminMemberDAO;
import com.bookmark.myweb.model.MemberVO;

public class AdminMemberService {

	private AdminMemberDAO adminMemberDAO = new AdminMemberDAO();

	/**
	 * @author ys.kim
	 * @param member 회원 등록 확인
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

	/**
	 * @param member
	 * @return
	 */
	public boolean updateMemberAdmin(MemberVO member) {
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
	
	
    /**
     * @author ys.kim
     * @param userId
     * @param inputPw
     * @return
     * 
     * 로그인 가능 아이디 및 비밀번호 일치 여부 확인
     */
    public MemberVO login(int userId, String inputPw) {
    	System.out.println("member service login method");
        //사용자 아이디 조회
    	MemberVO dbPw = adminMemberDAO.selectMemberId(userId);
        if (dbPw != null && dbPw.getPw().equals(inputPw)) {
        	System.out.println("login success");
            return dbPw; // 로그인 성공 시 해당 회원 정보 리턴
        }
        System.out.println("loginfail");
        return null; // 로그인 실패
    }

	// 전체 회원 조회
	public List<MemberVO> selectAllMembers() {
		return adminMemberDAO.selectAllMembers();
	}

	// 역할 별 회원 조회
	public List<MemberVO> selectRoleMembers(String role) {
		return adminMemberDAO.selectRoleMembers(role);
	}
	
	public List<MemberVO> selectMembers(String role, String keyword) {
	  return adminMemberDAO.findMembers(role, keyword);
	}

	// 관리자 수정
	public void updateAdminMember(int userId, String name, String role, String phone, String email, String address, int unitId) {
	    adminMemberDAO.updateAdminMember(userId, name, role, phone, email, address, unitId);
	}

	// 이름만 수정 (학생/사서용)
	public void updateName(int userId, String name) {
	    adminMemberDAO.updateName(userId, name);
	}

	// 이름을 제외한 정보만 업데이트
	public void updateMemberInfo(int userId, String phone, String email, String address) {
		int result = adminMemberDAO.updateMemberInfo(userId, phone, email, address);
		System.out.println("updateMemberInfo 결과: " + result);
	}


	
	/**
	 * @author bs.kim
	 * @param userId
	 * @return 사용자의 비밀번호 반환 (비밀번호 찾기용)
	 */
	public String findPassword(int userId) {
	    return adminMemberDAO.getPassword(String.valueOf(userId));
	}

	public int updatePassword(int userId, String newPw) {
	    return adminMemberDAO.updatePassword(userId, newPw);
	}

	public boolean existsUser(int userId) {
	    return adminMemberDAO.existsUser(userId);
	}

	public MemberVO selectMemberInfo(int userId) {
		System.out.println(userId + "님의 회원 조회를 위한 service 도착");
		return adminMemberDAO.selectMemberInfo(userId);
	}

	/**
	 *  관리자가 회원 정보 변경
	 * @param userId
	 * @param role
	 * @param unitId
	 * @param name
	 * @param phone
	 * @param email
	 * @param address
	 */
	public void updateMemberInfoByAdmin(int userId, String role, String unitId, String name, String phone, String email,
			String address) {
		int result = adminMemberDAO.updateMemberInfoByAdmin(userId, role, unitId, name, phone, email, address);
		System.out.println("updateMemberInfoByAdmin 결과: " + result);
		
	}

	public void deleteMember(int userId) throws Exception {
		int result = adminMemberDAO.deleteMember(userId);
        if (result == 0) {
            throw new Exception("삭제 실패: 해당 회원이 존재하지 않음");
        }
	}
}
