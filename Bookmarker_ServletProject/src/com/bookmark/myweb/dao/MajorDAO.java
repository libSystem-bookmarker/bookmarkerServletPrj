package com.bookmark.myweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MajorDAO {
	
	static DataSource dataSource;
	
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//getdeptName Facult
	public Map<String, String> getDeptAndFaculty (int unitId) {
		Map<String, String> result = new HashMap<>();
		String sql = "SELECT \r\n" + 
				"    CASE \r\n" + 
				"        WHEN dept.UNIT_TYPE = 'DEPARTMENT' THEN dept.UNIT_NAME\r\n" + 
				"        ELSE NULL\r\n" + 
				"    END AS department_name,\r\n" + 
				"    CASE \r\n" + 
				"        WHEN dept.UNIT_TYPE = 'DEPARTMENT' THEN faculty.UNIT_NAME\r\n" + 
				"        ELSE dept.UNIT_NAME\r\n" + 
				"    END AS faculty_name\r\n" + 
				"FROM ACADEMIC_UNIT dept\r\n" + 
				"LEFT JOIN ACADEMIC_UNIT faculty \r\n" + 
				"    ON dept.PARENT_ID = faculty.UNIT_ID\r\n" + 
				"WHERE dept.UNIT_ID = ?\r\n";
		
		 try (Connection con = dataSource.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql);) {
	            pstmt.setInt(1, unitId);
	           
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                result.put("departmentName", rs.getString("department_name"));
	                result.put("facultyName", rs.getString("faculty_name"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return result;
	}
}
