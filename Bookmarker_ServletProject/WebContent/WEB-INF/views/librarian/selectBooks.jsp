<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/css/common/common.css">
<link rel="stylesheet" href="/resources/css/librarian/selectBooks.css">
<meta charset="UTF-8">


</head>
<body>
<header> <%@ include file="/WEB-INF/views/common/header.jsp" %> </header>
<main>
<section class="content">

 <div class="container">
    <div class="nav-container">
	    <a href="/selectBooks.do" class="nav-tab">도서 조회</a>
	    <a href="/selectLoanBooks.do" class="nav-tab">대출 내역</a>
	    <a href="/insertBookform.do" class="nav-tab">도서 등록</a>
  	</div>

    	<form method="get" action="/selectBooks.do" id="filterForm">
        <div class="search-bar">
            <input type="text" name="keyword" value="${keyword}" class="search-input" placeholder="도서 제목 또는 작가명으로 검색..." />
            
            <select class="category-select" name="categoryId"
            onchange="document.getElementById('filterForm').submit()">
            	<option value="">전체</option>
      			<c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}" 
                    <c:if test="${categoryId != null and category.categoryId == categoryId}">selected</c:if>>
                        ${category.name}
                    </option>
                </c:forEach>
            </select>
            
        </div>
        </form>


	<div class="books-grid">

<!-- 	categoryId를 받으면 선택한 categoryId를 서블릿에서 
		그러면 category..Controller를 /경로 으로 연결해줘야 하는데  -->
	  <c:forEach var="book" items="${books}">
	    <div class="book-card" onclick="goToDetail(${book.bookId})" style="cursor: pointer;">
	    	 <div style="display: flex; justify-content: space-between; margin-bottom:10px">
		      	<a href="/insertBookform.do?bookId=${book.bookId}" class="img-button">
				  <img src="../../resources/img/Edit.png" alt="수정 아이콘" />
				</a>
				
				
<%-- 		      	<a href="/deleteBook.do?bookId=${book.bookId}" class="img-button">
				  <img src="../../resources/img/Delete.png" alt="삭제 아이콘" />
				</a> --%>
				<form id="deleteForm${book.bookId}" action="/deleteBook.do" method="post" style="display:inline;">
		        <input type="hidden" name="bookId" value="${book.bookId}" />
		        <button type="submit" class="img-button" onclick="confirmDelete(${book.bookId}); return false;">
		            <img src="../../resources/img/Delete.png" alt="삭제 아이콘" />
		        </button>
   				 </form>
				
				
				
	  		</div>
	    
	    
	      <img src="${book.imageUrl}" class="book-image">
	      <div class="book-title">${book.title}</div>
	      <div class="book-author">${book.author}</div>
	      <div class="book-publisher">${book.publisher}</div>
	      <div class="book-status">
	        <span class="status-badge
	            <c:choose>
	              <c:when test="${book.totalCount == 0}">status-unavailable</c:when>
	              <c:when test="${book.totalCount == 1}">status-limited</c:when>
	              <c:otherwise>status-available</c:otherwise>
	            </c:choose>">
	            <c:choose>
	              <c:when test="${book.totalCount == 0}">대출불가</c:when>
	              <c:otherwise>대출가능</c:otherwise>
	            </c:choose>
	        </span>
	        <span class="book-quantity">${book.totalCount}/${book.totalCount}권</span>
	      </div>
	      
	  		
	  		
	    </div>
	  </c:forEach>

	</div>
		<c:if test="${empty books}">
	        <div class="no-results">
	           	검색 결과가 없습니다.
	        </div>
        </c:if>
    </div>


</section>
</main>
<footer> <%@ include file="/WEB-INF/views/common/footer.jsp" %> </footer>
</body>

<script>
	function goToDetail(bookId) {
    window.location.href = '/selectBookDetail.do?bookId=' + bookId;
  }
	
	function confirmDelete(event, bookId) {
/* 		event.stopPropagation();
 */	    if (confirm("📕 정말 이 도서를 삭제하시겠습니까?")) {
	      document.getElementById('deleteForm' + bookId).submit();
	    }
	  }
</script>


</html>