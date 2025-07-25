package com.bookmark.myweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			pstmt.setDate(7, new java.sql.Date(member.getCreateAt().getTime())); // 7번 위치

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

	// update
	/**
	 * @author y.kim
	 * @param member
	 * @return
	 * 회원 정보 수정 기능 - 관리자 용
	 */
	public int updateMemberAdmin (MemberVO member) {
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
			System.out.println("updateMember exception: " + e.getMessage());
			throw new RuntimeException();
		}

		return result;
	}

}
