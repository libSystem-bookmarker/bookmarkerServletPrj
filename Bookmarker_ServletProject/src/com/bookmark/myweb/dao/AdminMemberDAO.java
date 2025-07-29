package com.bookmark.myweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bookmark.myweb.model.MemberVO;

public class AdminMemberDAO {

	static DataSource dataSource;

	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author ys.kim
	 * @param member
	 * @return INSERT MEMBER
	 */
	public int insertMember(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			con = dataSource.getConnection();

			String sql = "INSERT INTO MEMBER (user_id, pw, role, name, phone_number, address, email, unit_id, created_at) "
					+ "VALUES (MEMBER_SEQ.NEXTVAL, '1234', ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getRole());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhoneNumber());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getEmail());
			if (member.getUnitId() == 0) {
				pstmt.setNull(6, java.sql.Types.INTEGER); // 올바른 위치
			} else {
				pstmt.setInt(6, member.getUnitId());
			}
			pstmt.setDate(7, new java.sql.Date(member.getCreatedAt().getTime())); // 7번 위치

			rowCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 예외 발생: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}
	
	//select member info

	/**
	 * @author ys.kim
	 * @param member
	 * @return 회원 정보 수정 기능 - 관리자 용
	 */
	public int updateMemberAdmin(MemberVO member) {
		int result = 0;
		try (Connection con = dataSource.getConnection()) {
			String sql = "UPDATE member SET role = ?, name = ?, unit_id = ? WHERE user_id = ? OR name = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getRole());
			pstmt.setString(2, member.getName());
			if (member.getUnitId() == 0) {
				pstmt.setNull(3, java.sql.Types.INTEGER); // 올바른 위치
			} else {
				pstmt.setInt(3, member.getUnitId());
			}

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateMemberAdmin exception: " + e.getMessage());
			throw new RuntimeException();
		}

		return result;
	}

	/**
	 * @author ys.kim
	 * @return 회원 전체 리스트
	 */
	public List<MemberVO> selectAllMembers() {
		// ser_id, pw, role, name, phone_number, address, email, unit_id, created_at
		List<MemberVO> memberListAll = new ArrayList<>();
		String sql = "SELECT user_id AS userId, pw AS pw, role AS role, name AS name, "
				+ "phone_number AS phoneNumber, address AS address, email AS email, "
				+ "unit_id AS unitId, created_at AS createdAt FROM member";

		try (Connection con = dataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				MemberVO vo = extractMemberFromResultSet(rs);
				memberListAll.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("selectAllMembers exception: " + e.getMessage());
			throw new RuntimeException();
		}

		return memberListAll;
	}

	/**
	 * @author ys.kim
	 * @param role
	 * @return role 카테고리에 따라 회원 목록 출력
	 */
	public List<MemberVO> selectRoleMembers(String role) {
		List<MemberVO> memberRoleList = new ArrayList<>();
		String sql = "SELECT user_id AS userId, pw AS pw, role AS role, name AS name, "
				+ "phone_number AS phoneNumber, address AS address, email AS email, "
				+ "unit_id AS unitId, created_at AS createdAt FROM member WHERE role = ?";
		try (Connection con = dataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, role);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO vo = extractMemberFromResultSet(rs);
				memberRoleList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("selectRoleMembers exception: " + e.getMessage());
			throw new RuntimeException();
		}
		return memberRoleList;
	}

	/**
	 * @author ys.kim
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private MemberVO extractMemberFromResultSet(ResultSet rs) throws SQLException {
		MemberVO member = new MemberVO();
		member.setUserId(rs.getInt("userId"));
		member.setPw(rs.getString("pw"));
		member.setRole(rs.getString("role"));
		member.setName(rs.getString("name"));
		member.setPhoneNumber(rs.getString("phoneNumber"));
		member.setAddress(rs.getString("address"));
		member.setEmail(rs.getString("email"));
		member.setUnitId(rs.getInt("unitId"));
		member.setCreatedAt(rs.getDate("createdAt"));
		return member;
	}

	/**
	 * @author ys.kim
	 * @param userid
	 * @return 비밀번호 확인 -> 정보 수정 시
	 * 
	 */
	public String getPassword(String userid) {
		String sql = "SELECT pw FROM member WHERE user_id = ?";
		try (Connection con = dataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, Integer.parseInt(userid));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("pw"); // pw 컬럼 값 반환
			}

		} catch (SQLException e) {
			System.out.println("get pw error message: " + e.getMessage());
			throw new RuntimeException(e);
		}

		return null; // 조회된 결과 없을 때
	}

	// 로그인
	/**
	 * @author ys.kim
	 * @param userId 로그인을 위해 사용자 정보 찾아옴
	 */
	public MemberVO selectMemberId(int userId) {
		String sql = "SELECT * FROM member WHERE user_id = ?";
		try (Connection con = dataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				MemberVO member = new MemberVO();
				member.setUserId(rs.getInt("user_id"));
				member.setPw(rs.getString("pw"));
				member.setRole(rs.getString("role"));
				member.setName(rs.getString("name"));
				member.setPhoneNumber(rs.getString("phone_number"));
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setUnitId(rs.getInt("unit_id"));
				member.setCreatedAt(rs.getDate("created_at"));

				return member;
			}
		} catch (SQLException e) {
			System.out.println("get member id info error message: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return null;
	}
	/**
	*@author bs kim
	*@param 비밀번호 수정 메서드 추가
	*/
	public int updatePassword(int userId, String newPw) {
		String sql = "UPDATE member SET pw = ? WHERE user_id = ?";
	    try (Connection con = dataSource.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	    	  System.out.println("AutoCommit? " + con.getAutoCommit());
	        pstmt.setString(1, newPw);
	        pstmt.setInt(2, userId);

	        return pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("updatePassword exception: " + e.getMessage());
	        throw new RuntimeException(e);
	    }
	}
	
	public boolean existsUser(int userId) {
	    String sql = "SELECT COUNT(*) FROM member WHERE user_id = ?";
	    try (Connection con = dataSource.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, userId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("사용자 존재 여부 확인 실패", e);
	    }
	    return false;
	}


=======
	
	public void updateAdminMember(int userId, String name, String role, String phone, String email, String address, int unitId) {
	    String sql = "UPDATE MEMBER SET NAME=?, ROLE=?, PHONE_NUMBER=?, EMAIL=?, ADDRESS=?, UNIT_ID=? WHERE USER_ID=?";
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, name);
	        pstmt.setString(2, role);
	        pstmt.setString(3, phone);
	        pstmt.setString(4, email);
	        pstmt.setString(5, address);
	        pstmt.setInt(6, unitId);
	        pstmt.setInt(7, userId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void updateName(int userId, String name) {
	    String sql = "UPDATE MEMBER SET NAME=? WHERE USER_ID=?";
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, name);
	        pstmt.setInt(2, userId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public int updateMemberInfo(int userId, String phone, String email, String address) {
		String sql = "UPDATE MEMBER SET PHONE_NUMBER = ?, EMAIL = ?, ADDRESS = ? WHERE USER_ID = ?";
		try (Connection conn = dataSource.getConnection();
		     PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, phone);
			pstmt.setString(2, email);
			pstmt.setString(3, address);
			pstmt.setInt(4, userId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

>>>>>>> feat/member/updateInfo
}
