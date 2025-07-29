<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/css/common/common.css">
<meta charset="UTF-8">
<title>BOOKMARK</title>

 <style>
 
 		body{
 			display:flex;
 			justify-content: space-between;
 		}

        header{
        	position: fixed;
		      top: 0;
		      left: 0;
		      width: 100%;
		      z-index: 1000;
        }
        
        footer{

        	bottom:0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem 1rem;
            margin-top: 60px;
            flex: 1;
        }

        .search-bar {
            display: flex;
            gap: 1rem;
            margin-bottom: 2rem;
            flex-wrap: wrap;
        }

        .search-input {
            flex: 1;
            padding: 0.75rem;
            border: 1px solid #d1d5db;
            border-radius: 0.375rem;
            font-size: 0.875rem;
        }

        .category-select {
            padding: 0.75rem;
            border: 1px solid #d1d5db;
            border-radius: 0.375rem;
            font-size: 0.875rem;
            min-width: 150px;
        }

        .books-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 1.5rem;
        }

        .book-card {
            background: white;
            border: 1px solid #e5e7eb;
            border-radius: 0.5rem;
            padding: 1rem;
            padding-top: 0.5rem;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .book-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .book-image {
            width: 80px;
            height: 120px;
            object-fit: cover;
            border-radius: 0.25rem;
            margin: 0 auto 1rem auto;
            display: block;
            background-color: #f3f4f6;
        }

        .book-title {
            font-size: 1rem;
            font-weight: bold;
            margin-bottom: 0.5rem;
            line-height: 1.4;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }

        .book-author {
            color: #6b7280;
            font-size: 0.875rem;
            margin-bottom: 0.25rem;
        }

        .book-publisher {
            color: #9ca3af;
            font-size: 0.75rem;
            margin-bottom: 1rem;
        }

        .book-status {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .status-badge {
            padding: 0.25rem 0.5rem;
            border-radius: 0.25rem;
            font-size: 0.75rem;
            font-weight: 500;
        }

        .status-available {
            background-color: #10b981;
            color: white;
        }

        .status-unavailable {
            background-color: #ef4444;
            color: white;
        }

        .status-limited {
            background-color: #f59e0b;
            color: white;
        }

        .book-quantity {
            font-size: 0.75rem;
            color: #6b7280;
        }

        .no-results {
            text-align: center;
            padding: 3rem;
            color: #6b7280;
        }

        @media (max-width: 768px) {
            .search-bar {
                flex-direction: column;
            }
            
            .books-grid {
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 1rem;
            }
            
            .container {
                padding: 1rem 0.5rem;
            }
        }
        
        .img-button {
		  display: inline-flex;
		  align-items: center;
		  padding: 8px 0px;
/* 		  padding-left: 15px; */

		}

		.img-button img {
		  width: 16px;
		  height: 16px;
		}
        
        
    </style>




</head>
<body>
<header> <%@ include file="/WEB-INF/views/common/header.jsp" %> </header>
<main>
<section class="content">

 <div class="container">

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
		      	<a href="/deleteBook.do?bookId=${book.bookId}" class="img-button">
				  <img src="../../resources/img/Delete.png" alt="삭제 아이콘" />
				</a>
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
</script>


</html>