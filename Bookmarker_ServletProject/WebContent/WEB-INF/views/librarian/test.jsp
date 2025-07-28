<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>도서 목록 - 도서관 관리 시스템</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f9fafb;
            color: #374151;
        }

        .header {
            background-color: #193f75;
            color: white;
            padding: 1rem;
            text-align: center;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem 1rem;
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
    </style>
</head>
<body>
    <div class="header">
        <h1>📚 도서관 관리 시스템 - 도서 목록</h1>
    </div>

    <div class="container">
        <div class="search-bar">
            <input type="text" id="searchInput" class="search-input" placeholder="도서 제목 또는 작가명으로 검색...">
            <select id="categorySelect" class="category-select">
                <option value="">전체 분류</option>
                <option value="프로그래밍">프로그래밍</option>
                <option value="컴퓨터과학">컴퓨터과학</option>
                <option value="문학">문학</option>
                <option value="과학">과학</option>
                <option value="역사">역사</option>
                <option value="예술">예술</option>
            </select>
        </div>

<div class="books-grid">
  <c:forEach var="book" items="${books}">
    <div class="book-card">
      <img src="../../resources/img/book1.jpg" class="book-image">
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

        <div id="noResults" class="no-results" style="display: none;">
            검색 결과가 없습니다.
        </div>
    </div>

</body>
</html>
