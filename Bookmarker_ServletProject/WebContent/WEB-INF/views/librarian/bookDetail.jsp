<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서 상세 보기</title>
    <style>
        .container {
            width: 600px;
            margin: 30px auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 8px;
            font-family: sans-serif;
        }
        .container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .field {
            margin-bottom: 15px;
        }
        .label {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>📘 도서 상세 정보</h2>
        
        <div class="field">
            <span class="label">도서 ID:</span>
            <span>${book.bookId}</span>
        </div>

        <div class="field">
            <span class="label">카테고리:</span>
            <span>${book.categoryName}</span>
        </div>

        <div class="field">
            <span class="label">제목:</span>
            <span>${book.title}</span>
        </div>

        <div class="field">
            <span class="label">저자:</span>
            <span>${book.author}</span>
        </div>

        <div class="field">
            <span class="label">출판사:</span>
            <span>${book.publisher}</span>
        </div>

        <div class="field">
            <span class="label">보유 수량:</span>
            <span>${book.totalCount}</span>
        </div>

        <div class="field">
            <span class="label">출판일:</span>
            <fmt:formatDate value="${book.createAt}" pattern="yyyy.MM.dd" />
        </div>

        <div style="margin-top: 30px;">
            <a href="/book/updateForm.do?bookId=${book.bookId}">✏️ 수정하기</a> |
            <a href="/book/list.do">📚 목록으로 돌아가기</a>
        </div>
    </div>
</body>
</html>
