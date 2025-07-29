package com.bookmark.myweb.controller.librarian;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.model.BookVO;
import com.bookmark.myweb.service.BookService;



public class BookInsertPostController implements CommandController {

	private BookService bookService= new BookService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		// 폼에서 전송된 파라미터 받기
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String totalCount = request.getParameter("totalCount");
		String createAt = request.getParameter("createAt");
		String categoryId = request.getParameter("categoryId");
		Part filePart;
		
		String imageUrl = "";
		try {
			filePart = request.getPart("imageFile");
			String fileName = filePart.getSubmittedFileName(); 
			
//			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			
			System.out.println("filePart: " + filePart);
			System.out.println("fileName: " + fileName);
			
			
			 File uploadDir = new File("C:/upload/book/");
		      if (!uploadDir.exists()) uploadDir.mkdirs();
			
			imageUrl = (fileName != null) ? "/upload/book/" + fileName : null;
			
			//파일 저장
			filePart.write("C:/upload/book/" + fileName);
			
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

			

		
		// BookVO 객체 생성 및 값 세팅
		BookVO book = new BookVO();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setTotalCount(Integer.parseInt(totalCount));
		book.setCategoryId(Integer.parseInt(categoryId));
		// 날짜는 java.sql.Date로 변환
		book.setCreateAt(Date.valueOf(createAt));
		book.setImageUrl(imageUrl);
		
		bookService.insertBook(book);
		
		// 등록 실패 시 다시 등록 페이지로 /insertBookform.do
		
		return "redirect:/selectBooks.do";

	}

}
