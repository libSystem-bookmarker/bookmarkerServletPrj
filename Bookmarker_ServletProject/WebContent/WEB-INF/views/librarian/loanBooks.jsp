<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>도서 대출 내역</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background-color: #f9fafb;
      margin: 0;
      padding: 20px;
    }

    .container {
      background: white;
      border-radius: 12px;
      padding: 24px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
      max-width: 1200px;
      margin: auto;
    }

    .table-wrapper {
        overflow-x: auto;
    }

    h3 {
      display: flex;
      align-items: center;
      font-size: 24px;
      margin-bottom: 20px;
    }

    h3::before {
        content: '';
        display: inline-block;
        width: 24px;
        height: 24px;
        margin-right: 10px;
        background-image: url('../../resources/img/Users.png'); /* 여기에 이미지 경로 */
        background-size: contain;
        background-repeat: no-repeat;
        vertical-align: middle;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      min-width: 1000px;
    }

    th, td {
      padding: 12px 16px;
      text-align: center;
      white-space: nowrap;
    }

    th {
      background-color: #f3f4f6;
      font-weight: bold;
    }

    tr:nth-child(even) {
      background-color: #fafafa;
    }
    
    tr:hover {
	  background-color: #f3f4f6; /* 연한 회색으로 강조 */
	  transition: background-color 0.2s ease-in-out;
	}

    .badge {
      padding: 6px 10px;
      border-radius: 9999px;
      font-size: 14px;
      display: inline-block;
    }

    .badge.red {
      background-color: #f87171;
      color: white;
    }

    .badge.gray {
      background-color: #e5e7eb;
      color: #374151;
    }

    .badge.black {
      background-color: #111827;
      color: white;
    }

    .btn {
      padding: 6px 12px;
      border-radius: 8px;
      font-size: 14px;
      
      position: relative; /**/
	  display: inline-flex; /**/
	  align-items: center;

	  background-color: #2968C2;
	  color: white;
	  border: none;
	  border-radius: 8px;
	  cursor: pointer;
    }

    .btn.return {
      background-color: #2968C2;
      color: white;
      margin-right: 8px;
    }
    
    
    .btn.return::before{
      content: '';
	  display: inline-block;
	  width: 20px;
	  height: 20px;
	  margin-right: 8px;
	  background-image: url('../../resources/img/Rotateccw.svg'); /* 원하는 이미지 경로 */
	  background-size: contain;
	  background-repeat: no-repeat;
  }
    

    .btn.extend {
      background-color: #f9fafb;
      border: 1px solid #d1d5db;
      color: #111827;
      margin-right: 8px;
    }
    
    
    .btn.extend::before{
    	content: '';
	  display: inline-block;
	  width: 20px;
	  height: 20px;
	  margin-right: 8px;
	  background-image: url('../../resources/img/Checkcircle.svg'); /* 원하는 이미지 경로 */
	  background-size: contain;
	  background-repeat: no-repeat;
    }
    
    .btn:hover {
	  opacity: 0.9;
	  transform: translateY(-1px);
	  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
	  transition: all 0.2s ease;
	}
	
	.btn.return:hover {
	  background-color: #21549c; /* 진한 초록 */
	}
	
	.btn.extend:hover {
	  background-color: #e5e7eb; /* 더 진한 회색 */
	  color: #000;
	}
	
	
	
	
	/* 모달 스타일 */
.modal {
  position: fixed;
  z-index: 9999;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0,0,0,0.4); /* 배경 흐림 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fff;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0,0,0,0.2);
  width: 360px;
  text-align: center;
}

.modal-buttons {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  gap: 12px;
}
    

    @media (max-width: 768px) {
      .container {
        padding: 16px;
      }
      h2 {
        font-size: 20px;
      }
    }
  </style>
</head>
<body>
  <div class="container">
    <h3>도서 대출 내역</h3>
    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>학생 ID</th>
            <th>학생명</th>
            <th>도서명</th>
            <th>대출일</th>
            <th>반납예정일</th>
            <th>남은 일수</th>
            <th>상태</th>
            <th>작업</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="loan" items="${bookWithLoans}">
            <tr>
              <td>${loan.userId}</td>
              <td>${loan.userName}</td>
              <td>${loan.title}</td>
              <td>${loan.loanDate}</td>
              <td>${loan.returnDate}</td>

              <td>
                <c:choose>
                  <c:when test="${empty loan.dueDate and loan.daysOver > 0}">
                    <span class="badge red">${loan.daysOver}일 연체</span>
                  </c:when>
                  <c:when test="${empty loan.dueDate and loan.daysOver < 0}">
                    <span class="badge gray">${(-loan.daysOver)}일 </span>
                  </c:when>
                </c:choose>
              </td>

              <td>
                <c:choose>
				  <c:when test="${loan.loanStatus eq '대출중'}">
				    <span class="badge black">대출중</span>
				  </c:when>
				  <c:when test="${loan.loanStatus eq '연체'}">
				    <span class="badge red">연체</span>
				  </c:when>
				  <c:when test="${loan.loanStatus eq '반납완료'}">
				    <span class="badge gray">반납완료</span>
				  </c:when>
				</c:choose>

              </td>

              <td>
                <c:if test="${empty loan.dueDate}">
                  <button class="btn return">반납</button>
                  <button class="btn extend">연장</button>
                </c:if>
              </td>
              
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
  
  
  
  <!-- 반납 모달 알림창 -->
  <div id="returnModal" class="modal" style="display: none;">
	  <div class="modal-content">
	    <p>정말 반납 처리하시겠습니까?</p>
	    <div class="modal-buttons">
	      <button id="confirmReturn" class="btn">확인</button>
	      <button id="cancelReturn" class="btn">취소</button>
	    </div>
	  </div>
  </div>
  
  
  <!-- 연장 모달창 -->
	<div id="extendModal" class="modal" style="display: none;">
	  <div class="modal-content">
	    <p>대출 기간을 연장하시겠습니까?</p>
	    <div class="modal-buttons">
	      <button id="confirmExtend" class="btn">확인</button>
	      <button id="cancelExtend" class="btn">취소</button>
	    </div>
	  </div>
	</div>
  
  
  
  
  
</body>

	<script>
	  // 모달 요소
	  const modal = document.getElementById("returnModal");
	  const confirmBtn = document.getElementById("confirmReturn");
	  const cancelBtn = document.getElementById("cancelReturn");
	
	  // 모든 반납 버튼에 이벤트 등록
	  document.querySelectorAll(".btn.return").forEach(button => {
	    button.addEventListener("click", ()=> {
	      modal.style.display = "flex";
	    });
	  });
	
	  // 취소 버튼 클릭 시 모달 닫기
	  cancelBtn.addEventListener("click", () => {
	    modal.style.display = "none";
	  });
	
	  // 확인 버튼 클릭 시 처리
	  confirmBtn.addEventListener("click", () => {
	    modal.style.display = "none";
	    alert("반납 처리되었습니다."); // 실제 반납 처리는 ajax나 form으로 추가
	  });
	
	  // ESC 키로 닫기, 외부 클릭으로 닫기 (선택사항)
	  window.addEventListener("click", (e) => {
	    if (e.target === modal) {
	      modal.style.display = "none";
	    }
	  });
	  
	  
	  
	// 연장 모달 요소
	  const extendModal = document.getElementById("extendModal");
	  const confirmExtendBtn = document.getElementById("confirmExtend");
	  const cancelExtendBtn = document.getElementById("cancelExtend");

	  // 모든 연장 버튼에 이벤트 등록
	  document.querySelectorAll(".btn.extend").forEach(button => {
	    button.addEventListener("click", () => {
	      extendModal.style.display = "flex";
	    });
	  });

	  // 연장 취소
	  cancelExtendBtn.addEventListener("click", () => {
	    extendModal.style.display = "none";
	  });

	  // 연장 확인
	  confirmExtendBtn.addEventListener("click", () => {
	    extendModal.style.display = "none";
	    alert("연장 요청이 완료되었습니다."); // 실제 처리 필요 시 AJAX 등 추가
	  });

	  // 바깥 클릭 시 닫기
	  window.addEventListener("click", (e) => {
	    if (e.target === modal) {
	      modal.style.display = "none";
	    }
	    if (e.target === extendModal) {
	      extendModal.style.display = "none";
	    }
	  });
	  
	</script>
	

</html>
