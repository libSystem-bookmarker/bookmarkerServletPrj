package com.bookmark.myweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bookmark.myweb.model.BookVO;
import com.bookmark.myweb.model.BookWithCategoryVO;
import com.bookmark.myweb.model.CategoryVO;

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
	 * 도서 삭제
	 * bookId에 해당하는 도서 삭제
	 * @param bookId
	 */
	public int deleteBookById(int bookId) {

	    String sql = "DELETE FROM book WHERE book_id = ?";

	    try (
		        Connection con = dataSource.getConnection();
		        PreparedStatement stmt = con.prepareStatement(sql);	
	    	){

	        stmt.setInt(1, bookId);
	        int deletedRow = stmt.executeUpdate();
	        
	        return deletedRow;
	        

	    } catch (SQLException e) {
	        throw new RuntimeException("도서 삭제 중 오류 발생", e);

	    }
	}

	
	
	
	
	/**
	 * 도서 수정
	 * @author yunha
	 * @param book 
	 */
	public int updateBook(BookVO book) {
		
	    String sql = "UPDATE book SET title = ?, author = ?, publisher = ?, create_at = ?, total_count = ?, category_id = ? WHERE book_id = ?";
	    
	    try(
	    	Connection con = dataSource.getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql);
	    ){		
	        stmt.setString(1, book.getTitle());
	        stmt.setString(2, book.getAuthor());
	        stmt.setString(3, book.getPublisher());
	        stmt.setDate(4, (Date)book.getCreateAt());
	        stmt.setInt(5, book.getTotalCount());
	        stmt.setInt(6, book.getCategoryId());
	        stmt.setInt(7, book.getBookId());
	        
	        int rowCount = stmt.executeUpdate();
	        
	        return rowCount;
	        
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	
	/**
	 * 카테고리 조인한 도서 목록 전체 조회
	 * @author yunha
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
	
	
	
	/**
	 * 카테고리 목록 조회
	 * @author yunha
	 * @return 카테고리 전체 목록 List<CategoryVO>
	 */
	public List<CategoryVO> getCategoryAll() {
	    Connection con = null;
	    List<CategoryVO> categoryList = new ArrayList<>();

	    try {
	        con = dataSource.getConnection();
	        String sql = "SELECT category_id AS categoryId, name AS name FROM category";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            categoryList.add(new CategoryVO(rs.getInt("categoryId"), rs.getString("name")));
	        }

	        
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	    }
	    
	    return categoryList;
	}
	
	
	
	
	/**
	 * 도서 등록
	 * @author yunha
	 * @param book 입력한 도서 정보
	 */
	public int insertBook(BookVO book) {
	    Connection con = null;
	    String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    int rowCount = 0;
	    try {
	        con = dataSource.getConnection();
	        PreparedStatement stmt = con.prepareStatement(sql);

	        stmt.setInt(1, book.getBookId());
	        stmt.setInt(2, book.getCategoryId());
	        stmt.setString(3, book.getTitle());
	        stmt.setString(4, book.getAuthor());
	        stmt.setString(5, book.getPublisher());
	        stmt.setInt(6, book.getTotalCount());
	        stmt.setDate(7, (Date) book.getCreateAt());
	        
	        rowCount = stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	    	throw new RuntimeException("도서가 등록되지 않았습니다. [도서 등록 실패]");
	        
	    } finally {
	        if (con != null) {
	            try {
	                con.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
	    }
	    
	    return rowCount;
	}
	
	
	/**
	 * bookId에 따른 도서 한 권 조회
	 * @author yunha
	 * @param bookId
	 * @return 도서 한 권 BookVO
	 */
	public BookVO selectBookById(int bookId) {
		
		Connection con = null;
		BookVO book = new BookVO();
		
	    String sql = "SELECT "
	               + "  book_id AS bookId, "
	               + "  category_id AS categoryId, "
	               + "  title, "
	               + "  author, "
	               + "  publisher, "
	               + "  total_count AS totalCount, "
	               + "  create_at AS createAt "
	               + "FROM book_with_category_view "
	               + "WHERE book_id = ?";
	    
	    try {
		    con = dataSource.getConnection();
	        PreparedStatement stmt = con.prepareStatement(sql);
	    	stmt.setInt(1, bookId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            
	            book.setBookId(rs.getInt("bookId"));
	            book.setCategoryId(rs.getInt("categoryId"));
	            book.setTitle(rs.getString("title"));
	            book.setAuthor(rs.getString("author"));
	            book.setPublisher(rs.getString("publisher"));
	            book.setTotalCount(rs.getInt("totalCount"));
	            book.setCreateAt(rs.getDate("createAt"));
	            
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("❌ 해당 ID의 도서가 존재하지 않습니다.");
	    }finally {
	    	if (con != null) {
	            try {
	                con.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
		}
	    
	    return book;
	}

	
	
	
	
	
	

}
