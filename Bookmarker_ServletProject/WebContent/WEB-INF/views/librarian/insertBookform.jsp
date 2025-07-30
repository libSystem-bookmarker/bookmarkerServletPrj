<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/librarian/insertBookform.css" />
<meta charset="UTF-8">
</head>



<body>
<header> <%@ include file="/WEB-INF/views/common/header.jsp" %> </header>
<main>
<section class="content">

<!-- 등록 / 수정 구분 -->
  <c:choose>
    <c:when test="${empty book.bookId}">
      <c:url var="book_do" value="/insertBook.do" />
    </c:when>
    <c:otherwise>
      <c:url var="book_do" value="/updateBook.do" />
    </c:otherwise>
  </c:choose>

  <form action="${book_do}" method="post" class="container" enctype="multipart/form-data">
<%--     <form action="${book_do}" method="post" class="container">
 --%>  <div class="form-header">
      <div style="font-weight: bold; font-size: 20px; margin-bottom: 10px;">
        <c:choose>
          <c:when test="${empty book.bookId}">+ 도서 등록 신청서</c:when>
          <c:otherwise>+ 도서 수정 신청서</c:otherwise>
        </c:choose>
      </div>
      <small>※ 모든 항목을 정확히 입력해주시기 바랍니다.</small>
    </div>

    <!-- 1. 도서 기본 정보 -->
    <div class="form-section">
      <h3>1. 도서 기본 정보</h3>

      <div class="form-group">
        <label for="title">도서명 *</label>
        <input type="text" name="title" id="title" value="${book.title}" placeholder="정확한 도서명을 입력하세요" required />
      </div>

      <div class="form-group">
        <label for="author">저자명 *</label>
        <input type="text" name="author" id="author" value="${book.author}" placeholder="저자명을 입력하세요" required />
      </div>

      <div class="form-group">
        <label for="publisher">출판사 *</label>
        <input type="text" name="publisher" id="publisher" value="${book.publisher}" placeholder="출판사를 입력하세요" required />
      </div>

      <div class="form-group">
        <label for="createAt">출판일 *</label>
        <input type="date" name="createAt" id="createAt" value="${book.createAt}" required />
      </div>
    </div>

    <!-- 2. 분류 및 수량 정보 -->
    <div class="form-section">
      <h3>2. 분류 및 수량 정보</h3>

      <div class="form-group">
        <label for="categoryId">도서 분류 *</label>
        <select name="categoryId" id="categoryId" required>
          <option value="">-- 분류를 선택하세요 --</option>
          <c:forEach var="category" items="${categoryList}">
            <option value="${category.categoryId}"
              <c:if test="${category.categoryId == book.categoryId}">selected</c:if>>
              ${category.name}
            </option>
          </c:forEach>
        </select>
      </div>

      <div class="form-group">
        <label for="totalCount">보유 수량 *</label>
        <input type="number" name="totalCount" id="totalCount" value="${book.totalCount}" min="1" required />
      </div>
    </div>

    <!-- 3. 이미지 첨부 -->
    <div class="form-section">
      <h3>3. 이미지 첨부</h3>
      <div class="form-group">
        <label for="imageFile">도서 이미지 업로드</label>
        <input type="file" name="imageFile" id="imageFile" accept="image/*" />
      </div>

      <div class="image-preview" id="previewContainer" style="display: none;">
        <div class="image-box">
          <img id="previewImage" src="#" alt="미리보기" />
        </div>
      </div>
    </div>

    <input type="hidden" name="bookId" value="${book.bookId}" />

    <div style="text-align: center; display: flex">
      <c:choose>
        <c:when test="${empty book.bookId}">
          <button type="submit" class="submit-btn">도서 등록하기</button>
        </c:when>
        <c:otherwise>
          <button type="submit" class="submit-btn">수정하기</button>
          <button type="button" class="submit-btn" onclick="history.back()">취소</button>
        </c:otherwise>
      </c:choose>
    </div>
  </form>

  <script>
    const imageFileInput = document.getElementById("imageFile");
    const previewImage = document.getElementById("previewImage");
    const previewContainer = document.getElementById("previewContainer");

    imageFileInput?.addEventListener("change", function () {
      const file = this.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
          previewImage.src = e.target.result;
          previewContainer.style.display = "block";
        };
        reader.readAsDataURL(file);
      } else {
        previewContainer.style.display = "none";
      }
    });
  </script>


</section>
</main>
<footer> <%@ include file="/WEB-INF/views/common/footer.jsp" %> </footer>
</body>
</html></html>