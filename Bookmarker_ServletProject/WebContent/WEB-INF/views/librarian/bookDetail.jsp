<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">


<head><link rel="stylesheet" href="/resources/css/librarian/bookDetail.css"></head>

<body>
  <div class="detail-header">
    <h3>도서 상세정보</h3>
    <p>도서의 자세한 정보를 확인하세요</p>
  </div>

  <div class="container">
    <div class="book-detail">
      <div class="book-image">
        <img src="${book.imageUrl}" alt="${book.title}">
      </div>

      <div class="book-info">
        <div class="card">
          <div style="display: flex; justify-content: space-between; align-items: flex-start;">
            <div>
              <h1 class="book-title">${book.title}</h1>
              <span class="category-badge">📚 ${book.categoryName}</span>
            </div>
            <div class="book-id">
              <strong>#${book.bookId}</strong><br>
              도서 ID
            </div>
          </div>
        </div>

        <div class="card">
          <div class="card-header">
            <h2 class="card-title">도서 정보</h2>
          </div>
          <div class="info-grid">
            <div class="info-item">
              <div class="info-icon">👤</div>
              <div class="info-content">
                <h4>저자</h4>
                <p>${book.author}</p>
              </div>
            </div>
            <div class="info-item">
              <div class="info-icon">🏢</div>
              <div class="info-content">
                <h4>출판사</h4>
                <p>${book.publisher}</p>
              </div>
            </div>
            <div class="info-item">
              <div class="info-icon">📖</div>
              <div class="info-content">
                <h4>보유 수량</h4>
                <p>${book.totalCount}권</p>
              </div>
            </div>
            <div class="info-item">
              <div class="info-icon">📅</div>
              <div class="info-content">
                <h4>발행일</h4>
                <p><fmt:formatDate value="${book.createAt}" pattern="yyyy년 M월 d일" /></p>
              </div>
            </div>
          </div>
        </div>

        <div class="card">
          <div class="card-header">
            <h2 class="card-title">카테고리 정보</h2>
          </div>
          <div class="info-item">
            <div class="info-icon">🏷️</div>
            <div class="info-content">
              <h4>${book.categoryName}</h4>
              <p>카테고리 ID: ${book.categoryId}</p>
            </div>
          </div>
        </div>

        <div class="card">
          <div class="card-header">
            <h2 class="card-title">상세 정보</h2>
          </div>

          <div class="stats-grid">
            <div class="stat-card">
              <div class="stat-number">${book.bookId}</div>
              <div class="stat-label">도서 ID</div>
            </div>
            <div class="stat-card">
              <div class="stat-number">${book.categoryId}</div>
              <div class="stat-label">카테고리 ID</div>
            </div>
            <div class="stat-card">
              <div class="stat-number">${book.totalCount}</div>
              <div class="stat-label">보유 수량</div>
            </div>
            <div class="stat-card">
              <div class="stat-number">
                <fmt:formatDate value="${book.createAt}" pattern="yyyy" />
              </div>
              <div class="stat-label">발행일</div>
            </div>
          </div>
        </div>
        
     	<div class="card loan-section">
            <div class="card-header">
                <h2 class="card-title">도서 대출</h2>
            </div>

		   <!-- 대출 버튼 -->
		   <c:if test="${book.totalCount > 0}">
		   <form id="loanForm" action="/insertLoanBook.do" method="post">
<%-- 		       <input type="hidden" name="userId" value="${sessionScope.loginUser.userId}" />
 --%>		       <input type="hidden" name="bookId" value="${book.bookId}" />
		
		       <button type="submit" class="loan-button" id="loanButton">
		       	📚 대출하기
		       </button>
		   </form>
		   </c:if>
		   
		   <c:if test="${book.totalCount == 0}">
			  <div style="color: gray; font-weight: bold; margin-top: 10px;">
			      현재 대출 가능한 수량이 없습니다.(수량 0권)
			  </div>
			</c:if>
                    
                    <div class="loan-info">
                        <div style="font-size:1.3em"><strong>대출 안내</strong></div>
                        <div style="display:flex; justify-content:space-between; margin-top:10px;" >
                        <div><p>🔹 반납 방법</p> <p>• 도서관 내부 반납함</p><p>• 사서를 통해 반납</p>
                        <p>• 대출 기간: 14일</p></div>
                        
                        <div>
                        	<p>🔹 유의 사항</p>
                        	<p>• 도서를 훼손하거나 분실할 경우 동일 도서 또는 시가 변상</p>
							<p>• 타인의 대출증을 사용하는 경우 사용 자격 없음</p>
                        </div>
                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
        <script>
        // 날짜 포맷
        function formatDate(dateString) {
            const date = new Date(dateString);
            return date.toLocaleDateString('ko-KR', {
                year: 'numeric',
                month: 'long',
                day: 'numeric'
            });
        }

        let bookStatus = {
            totalCount: 15,
            availableCount: 12,
            isAvailable: true,
            userHasLoaned: false
        };

    </script>

</body>
</html>
