<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
</head>
<body>

	<c:if test="${not empty books}">
		<c:url var="selectBook_do" value="/selectBook.do" />
	</c:if>
	<c:if test="${empty books}">
<%-- 		책이 존재하지 않을 때 uri 나 메세지 보여줄 예정
<c:url var="noBook_do" value="/selectBook.do" />
 --%>	</c:if>
 
 	<c:forEach var="book" items="${books}">
 		<div class="card" style="border: 1px solid black">
 		<a href="/insertBookform.do?bookId=${book.bookId}">
 			<div>${book.bookId}</div>
            <div>${book.categoryName}</div>
            <div>${book.title}</div>
            <div>${book.author}</div>
            <div>${book.publisher}</div>
            <div>${book.totalCount}</div>
			<div>
			    출판일: <fmt:formatDate value="${book.createAt}" pattern="yyyy.MM.dd" />
			</div>
  
 		</a>
 		</div>
 	</c:forEach>
 
 


</body>
</html>