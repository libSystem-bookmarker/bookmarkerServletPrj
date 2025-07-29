package com.bookmark.myweb.service;

import java.sql.SQLException;
import java.util.List;

import com.bookmark.myweb.dao.BookDAO;
import com.bookmark.myweb.model.BookVO;
import com.bookmark.myweb.model.CategoryVO;

public class BookService {
	
	
	BookDAO dao = new BookDAO();
	
	
	public void updateReturnBookById(int bookLoanDetailId, int bookId) {
		
		int updateRow = dao.updateReturnBookById(bookLoanDetailId, bookId);
		
		System.out.println("updateRow: " + updateRow);
		
		if(updateRow <= 0) {
			throw new RuntimeException("❌ 도서 반납을 실패했습니다.");
		}
	}
	
	
	public void deleteBookById(int bookId) {
		
		int deletedRow = dao.deleteBookById(bookId);
		
        if (deletedRow <= 0) {
        	throw new RuntimeException("❌ 해당 ID의 도서를 찾을 수 없습니다.");
        }
	}
	
	
	
	public void insertBook(BookVO book) {
      try {
            int result = dao.insertBook(book);
            if (result <= 0) {
                throw new RuntimeException("도서 등록 실패");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
		
	}
	
	
	public List<CategoryVO> getCategoryAll(){
		return dao.getCategoryAll();
	}
	
	
	public BookVO selectBookVOById(int bookId) {
		
		return dao.selectBookVOById(bookId);
		
	}
	
	public void updateBook(BookVO book) {
		
		int result = dao.updateBook(book);
		
        if(result <= 0) {
        	throw new RuntimeException("수정된 데이터가 없습니다..");
        }
        
	}
	

}
