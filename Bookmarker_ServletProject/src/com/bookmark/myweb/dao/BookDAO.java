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

import com.bookmark.myweb.model.BookWithCategoryVO;

public class BookDAO {
	
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
	 * 카테고리 조인한 도서 목록 전체 조회
	 * @return 도서 목록 전체 List<BookWithCategoryVO>
	 */
	public List<BookWithCategoryVO> getBookAll() {
	    Connection con = null;
	    List<BookWithCategoryVO> bookList = new ArrayList<>();

	    try {
	        con = dataSource.getConnection();
	        String sql = "SELECT book_id AS bookId, category_id AS categoryId, category_name AS categoryName, "
	                   + "title AS title, author AS author, publisher AS publisher, total_count AS totalCount, create_at AS createAt "
	                   + "FROM book_with_category_view ORDER BY book_id";

	        PreparedStatement stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            bookList.add(new BookWithCategoryVO(
	                rs.getInt("bookId"),
	                rs.getInt("categoryId"),
	                rs.getString("categoryName"),
	                rs.getString("title"),
	                rs.getString("author"),
	                rs.getString("publisher"),
	                rs.getInt("totalCount"),
	                rs.getDate("createAt")
	            ));
	        }

	        
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	    }
	    

	    return bookList;
	    
	}
	
	

}
