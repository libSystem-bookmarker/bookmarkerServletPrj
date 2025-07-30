package com.bookmark.myweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bookmark.myweb.model.MajorVO;

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
	

	public List<MajorVO> getDepartments(int facultyId) {
	    List<MajorVO> list = new ArrayList<>();
	    String sql = "SELECT UNIT_ID, UNIT_NAME FROM ACADEMIC_UNIT WHERE UNIT_TYPE = 'DEPARTMENT' AND PARENT_ID = ?";
	    try (Connection cnn = dataSource.getConnection();
	         PreparedStatement pstmt = cnn.prepareStatement(sql)) {
	        pstmt.setInt(1, facultyId);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            MajorVO vo = new MajorVO();
	            vo.setUnitId(rs.getInt("UNIT_ID"));
	            vo.setUnitName(rs.getString("UNIT_NAME"));
	            list.add(vo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
    public List<MajorVO> selectFacultyList() {
        List<MajorVO> facultyList = new ArrayList<>();

        String sql = "SELECT UNIT_ID, UNIT_NAME, UNIT_TYPE FROM ACADEMIC_UNIT WHERE UNIT_TYPE = 'FACULTY' ORDER BY UNIT_ID";

        try (
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                MajorVO vo = new MajorVO();
                vo.setUnitId(rs.getInt("UNIT_ID"));
                vo.setUnitName(rs.getString("UNIT_NAME"));
                vo.setUnitType(rs.getString("UNIT_TYPE"));
                facultyList.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return facultyList;
    }

}
