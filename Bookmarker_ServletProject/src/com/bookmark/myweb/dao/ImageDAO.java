package com.bookmark.myweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.bookmark.myweb.model.MemberImageVO;

//private int imangeId;
//private int userId;
//private String fileName; //파일명
//private String filePath; //파일 경로
//private Date uploadDate;

public class ImageDAO {
	
	//dataSource
	static DataSource dataSource;
	
	//insert
	public int insertProgile (MemberImageVO profile) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		MemberImageVO memberImage = new MemberImageVO();
		String sql = "INSERT INTO MEMNER_IMAGE "
				+ "(IMAGE_ID AS imageId, USER_ID AS userId, FILE_NAME AS fileName, FILE_PATH as filePath, UPLOAD_DATE as uploadDate)"
				+ "VLAUES (IMAGE_SEQ.NEXTVAL, ?,?,?,?)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, profile.getUserId());
			pstmt.setString(2, profile.getFileName());
			pstmt.setString(3, profile.getFilePath());
			pstmt.setDate(3, new java.sql.Date(profile.getUploadDate().getTime()));
			
			rowCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("INSERT PROFILE ERROR: "+e.getMessage());
			throw new RuntimeException();
		}finally {
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
	
	//update
	
	//delete
	
	//select?

}
