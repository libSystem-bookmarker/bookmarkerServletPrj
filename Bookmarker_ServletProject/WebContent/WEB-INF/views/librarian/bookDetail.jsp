<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  	   <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            line-height: 1.6;
            color: #333;
        }

        .detail-header {
            background-color: #193F75;
            color: white;
            padding: 1rem 0;
            text-align: center;
        }

        .detail-header h3 {
            font-size: 2rem;
            margin-bottom: 0.5rem;
        }

        .detail-header p {
            color: #b3c6e7;
            font-size: 1.1rem;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem 1rem;
        }

        .book-detail {
            display: grid;
            grid-template-columns: 1fr 2fr;
            gap: 2rem;
            margin-top: 2rem;
        }

        .book-image {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .book-image img {
            width: 100%;
            height: auto;
            display: block;
        }

        .book-info {
            display: flex;
            flex-direction: column;
            gap: 1.5rem;
        }

        .card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 1.5rem;
        }

        .card-header {
            border-bottom: 2px solid #f1f3f4;
            padding-bottom: 1rem;
            margin-bottom: 1rem;
        }

        .card-title {
            color: #193F75;
            font-size: 1.5rem;
            font-weight: bold;
            margin-bottom: 0.5rem;
        }

        .book-title {
            color: #193F75;
            font-size: 2rem;
            font-weight: bold;
            margin-bottom: 1rem;
        }

        .category-badge {
            background-color: #193F75;
            color: white;
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-size: 0.9rem;
            display: inline-block;
            margin-bottom: 1rem;
        }

        .book-id {
            color: #666;
            font-size: 0.9rem;
            text-align: right;
        }

        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 1.5rem;
        }

        .info-item {
            display: flex;
            align-items: center;
            gap: 1rem;
        }

        .info-icon {
            width: 40px;
            height: 40px;
            background-color: #193F75;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 1.2rem;
        }

        .info-content h4 {
            color: #666;
            font-size: 0.9rem;
            margin-bottom: 0.25rem;
        }

        .info-content p {
            font-weight: 600;
            color: #333;
        }

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
            gap: 1rem;
            margin-top: 1rem;
        }

        .stat-card {
            background-color: #f8f9fa;
            padding: 1.5rem;
            border-radius: 8px;
            text-align: center;
        }

        .stat-number {
            font-size: 2rem;
            font-weight: bold;
            color: #193F75;
            margin-bottom: 0.5rem;
        }

        .stat-label {
            color: #666;
            font-size: 0.9rem;
        }

        .separator {
            height: 1px;
            background-color: #e9ecef;
            margin: 1rem 0;
        }

        @media (max-width: 768px) {
            .book-detail {
                grid-template-columns: 1fr;
                gap: 1rem;
            }

            .header h1 {
                font-size: 2rem;
            }

            .book-title {
                font-size: 1.5rem;
            }

            .info-grid {
                grid-template-columns: 1fr;
            }

            .stats-grid {
                grid-template-columns: repeat(2, 1fr);
            }
        }
        
            .loan-section {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 1.5rem;
            text-align: center;
        }

        .loan-button {
            background-color: #193F75;
            color: white;
            border: none;
            padding: 1rem 2rem;
            border-radius: 8px;
            font-size: 1.1rem;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
            width: 100%;
            max-width: 300px;
            margin: 1rem 0;
        }

        .loan-button:hover {
            background-color: #0f2d54;
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(25, 63, 117, 0.3);
        }

        .loan-button:disabled {
            background-color: #ccc;
            cursor: not-allowed;
            transform: none;
            box-shadow: none;
        }

        .loan-status {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.5rem;
            margin-bottom: 1rem;
            font-weight: 600;
        }

        .status-available {
            color: #28a745;
        }

        .status-unavailable {
            color: #dc3545;
        }

        .loan-info {
            background-color: #f8f9fa;
            padding: 1rem;
            border-radius: 8px;
            margin-top: 1rem;
            font-size: 0.9rem;
            color: #666;
            text-align: left;
        }
        
        
        
        
    </style>


</head>
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
		       <input type="hidden" name="userId" value="${sessionScope.loginUser.userId}" />
		       <input type="hidden" name="bookId" value="${book.bookId}" />
		
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
