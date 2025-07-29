package com.bookmark.myweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bookmark.myweb.model.BookVO;
import com.bookmark.myweb.model.BookWithCategoryVO;
import com.bookmark.myweb.model.BookWithLoanVO;
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
	 * 제목이나 작가명으로 도서 목록 조회 
	 * @param keyword 검색어
	 * @return keyword를 포함한 도서 목록 List<BookWithCategoryVO>
	 */
	public List<BookWithCategoryVO> selectSearchBooks(String keyword) {

	    String sql = "SELECT "
	               + "  book_id AS bookId, "
	               + "  category_id AS categoryId, "
	               + "  category_name AS categoryName, "
	               + "  title, author, publisher, total_count, create_at "
	               + "FROM book_with_category_view "
	               + "WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ?";

	    List<BookWithCategoryVO> bookList = new ArrayList<>();
	    String searchPattern = "%" + keyword.toLowerCase() + "%";

	    try (
	        Connection con = dataSource.getConnection();
	        PreparedStatement stmt = con.prepareStatement(sql);
	    ) {
	        stmt.setString(1, searchPattern);
	        stmt.setString(2, searchPattern);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                BookWithCategoryVO book = new BookWithCategoryVO(
	                    rs.getInt("bookId"),
	                    rs.getInt("categoryId"),
	                    rs.getString("categoryName"),
	                    rs.getString("title"),
	                    rs.getString("author"),
	                    rs.getString("publisher"),
	                    rs.getInt("total_count"),
	                    rs.getDate("create_at")
	                );
	                bookList.add(book);
	            }
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("🔍 도서 검색 중 오류 발생", e);
	    }

	    return bookList;
	}

	
	
	
	/**
	 * 카테고리별 도서 목록 조회
	 * @param categoryId 카테고리 ID
	 * @return 카테고리별 도서 목록 List<BookWithCategoryVO>
	 */
	public List<BookWithCategoryVO> selectBooksByCategory(int categoryId) {
	    String sql = "SELECT "
	               + "  book_id AS bookId, "
	               + "  category_id AS categoryId, "
	               + "  category_name AS categoryName, "
	               + "  title AS title, "
	               + "  author AS author, "
	               + "  publisher AS publisher, "
	               + "  total_count AS totalCount, "
	               + "  create_at AS createAt "
	               + "FROM book_with_category_view "
	               + "WHERE category_id = ?";

	    List<BookWithCategoryVO> bookList = new ArrayList<>();

	    try (
	        Connection con = dataSource.getConnection();
	        PreparedStatement stmt = con.prepareStatement(sql);
	    ) {
	        stmt.setInt(1, categoryId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                BookWithCategoryVO book = new BookWithCategoryVO(
	                    rs.getInt("bookId"),
	                    rs.getInt("categoryId"),
	                    rs.getString("categoryName"),
	                    rs.getString("title"),
	                    rs.getString("author"),
	                    rs.getString("publisher"),
	                    rs.getInt("totalCount"),
	                    rs.getDate("createAt")
	                );
	                bookList.add(book);
	            }
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("카테고리별 도서 조회 중 오류 발생", e);
	    }

	    return bookList;
	}

	
	
	
	
	
	
	public int updateReturnBookById(int bookLoanDetailId, int bookId) {
		
		Connection con = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        int result1 = 0;
        int result2 = 0;
        
        
        
        try {
        	con = dataSource.getConnection();
        	con.setAutoCommit(false);  // 수동 커밋

        	
        	 // 1. book_loan_detail 테이블에 반납 처리
            String updateLoanSQL = "UPDATE book_loan_detail " +
                                   "SET due_date = SYSDATE, " +
                                   "    loan_status = '반납완료' " +
                                   "WHERE book_loan_detail_id = ?";
            
            stmt1 = con.prepareStatement(updateLoanSQL);
            stmt1.setInt(1, bookLoanDetailId);
            result1 = stmt1.executeUpdate();
            
            if(result1 > 0) {
            	String updateBookSQL = "UPDATE book " +
                        "SET total_count = total_count + 1 " +
                        "WHERE book_id = ?";

            	stmt2 = con.prepareStatement(updateBookSQL);
            	stmt2.setInt(1, bookId);
            	result2 = stmt2.executeUpdate();
            	
            	con.commit(); // 모든 쿼리 성공 시 커밋
            }else {
            	con.rollback(); // 첫 번째 쿼리 실패 시 롤백
            }

            }catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (con != null) con.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            } finally {
                try { if (stmt2 != null) stmt2.close(); } catch (SQLException e) { e.printStackTrace(); }
                try { if (stmt1 != null) stmt1.close(); } catch (SQLException e) { e.printStackTrace(); }
                try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
            }

            return result2; // or result1 to check if 반납 처리되었는지
        }
	
	
	
	
	public List<BookWithLoanVO> selectAllLoanBooks() {
	    List<BookWithLoanVO> bookWithLoans = new ArrayList<>();

	    String sql = "SELECT * FROM view_book_with_loan";

	    try (Connection con = dataSource.getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            BookWithLoanVO vo = new BookWithLoanVO();

	            vo.setBookId(rs.getInt("book_id"));
	            vo.setTitle(rs.getString("title"));
//	            vo.setAuthor(rs.getString("author"));
//	            vo.setPublisher(rs.getString("publisher"));
//	            vo.setTotalCount(rs.getInt("total_count"));
//	            vo.setCategoryId(rs.getInt("category_id"));
//	            vo.setCreateAt(rs.getDate("create_at"));

	            vo.setBookLoanDetailId(rs.getInt("book_loan_detail_id"));
	            vo.setUserId(rs.getInt("user_id"));
	            vo.setUserName(rs.getString("user_name"));
	            vo.setLoanDate(rs.getDate("loan_date"));
	            vo.setReturnDate(rs.getDate("return_date"));
	            vo.setDueDate(rs.getDate("due_date"));
	            vo.setLoanStatus(rs.getString("loan_status"));
	            
	            
	    		// 연체일 계산
    			int daysOver = (int) ChronoUnit.DAYS.between(vo.getReturnDate().toLocalDate(), LocalDate.now());
    			vo.setDaysOver(daysOver);

	            
	            bookWithLoans.add(vo);
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("📛 대출 내역 조회 중 오류 발생", e);
	    }

	    return bookWithLoans;
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
//	        stmt.setString(8,  book.getImageUrl());
	        
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
