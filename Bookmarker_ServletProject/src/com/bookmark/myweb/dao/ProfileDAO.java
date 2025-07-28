package com.bookmark.myweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.bookmark.myweb.model.ProfileVO;

public class ProfileDAO {
	
	private DataSource dataSource;
	
	//insert
	public void insertProfile (ProfileVO profile) {
		String sql = "INSERT INTO MEMBER_IMAGE (IMAGE_ID, USER_ID, FILE_NAME, FILE_PATH) VALUES (MEMBER_IMAGE_SEQ.NEXTVAL, ?, ?, ?)";
		
		try (Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, profile.getUserId());
			pstmt.setString(2, profile.getFileName());
			pstmt.setString(3, profile.getFilePath());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("insertProfile dao error: "+ e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	//select profile -> userId
	public ProfileVO selectUserId(int userId) {
		String sql = "SELECT * FROM PROFILE WHERE USER_ID = ?";
		try (Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				ProfileVO profile = new ProfileVO();
				profile.setImageId(rs.getInt("IMAGE_ID"));
				profile.setUserId(rs.getInt("USER_ID"));
				profile.setFileName(rs.getString("FILE_NAME"));
				profile.setFilePath(rs.getString("FILE_PATH"));
				profile.setUploadDate(rs.getDate("UPLOAD_DATE"));;
				
				return profile;
			}
		} catch (SQLException e) {
			System.out.println("SELECT USER ID PROFILE ERROR: "+e.getMessage());
			throw new RuntimeException(e);
		}
		
		return null;
	}
	
	public void deleteByUserId(int userId) throws SQLException {
	    String sql = "DELETE FROM PROFILE WHERE USER_ID = ?";
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, userId);
	        pstmt.executeUpdate();
	    }
	}

}
