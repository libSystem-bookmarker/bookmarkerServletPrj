<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>📚 도서 등록</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${empty book.bookId}">📖 도서 등록</c:when>
            <c:otherwise>📘 도서 수정</c:otherwise>
        </c:choose>
    </h2>
    
    <%-- 등록 / 수정 구분 --%>
    <c:choose>
        <c:when test="${empty book.bookId}">
            <c:url var="book_do" value="/insertBook.do" />
        </c:when>
        <c:otherwise>
            <c:url var="book_do" value="/updateBook.do" />
        </c:otherwise>
    </c:choose>

    <form action="${book_do}" method="post">
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <td>도서 제목</td>
                <td><input type="text" name="title" value="${book.title}" required /></td>
            </tr>
            <tr>
                <td>작가</td>
                <td><input type="text" name="author" value="${book.author}" required /></td>
            </tr>
            <tr>
                <td>출판사</td>
                <td><input type="text" name="publisher" value="${book.publisher}" required /></td>
            </tr>
            <tr>
                <td>소장 책 수량</td>
                <td><input type="number" name="totalCount" value="${book.totalCount}" required min="1" /></td>
            </tr>
            <tr>
                <td>출판일</td>
                <td><input type="date" name="createAt" value="${book.createAt}" required /></td>
            </tr>
            <tr>
                <td>카테고리</td>
                <td>
                    <select name="categoryId" required>
                        <option value="">-- 선택하세요 --</option>
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.categoryId}" 
                                <c:if test="${category.categoryId == book.categoryId}">selected</c:if>>
                                ${category.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>

        <br>
        <input type="hidden" name="bookId" value="${book.bookId}" />

        <c:choose>
            <c:when test="${empty book.bookId}">
                <input type="submit" value="도서 등록하기" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="수정하기" />
                <input type="button" value="취소" onclick="history.back()" />
            </c:otherwise>
        </c:choose>
    </form>
</body>

</html>

