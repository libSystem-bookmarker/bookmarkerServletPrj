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
    
    
    a.btn {
	  text-decoration: none;
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
      
      .nav-container{
      	padding: 16px;
      }
    }
    
    		/* nav */
  .nav-container {
      display: flex;
      background-color: #0D326C; /* 네이비색 배경 */
      padding: 5px;   
 	 border-radius:5px;
 	 
 	 max-width: 1200px;
 	 margin: auto;
 	 margin-bottom:30px;
 }

    .nav-tab {
      flex: 1;
      text-align: center;
      padding: 8px 0;
      color: white;
      text-decoration: none;
      font-weight: bold;
      background-color: #0D326C;
      border: none;
      transition: background-color 0.3s;
    }

    .nav-tab:hover {
      background-color: white;
      color: #0D326C;
    }

    .nav-tab.active {
      background-color: white;
      color: #0D326C;
      border-radius: 5px;
    }
    
  </style>


</head>
<body>
<header> <%@ include file="/WEB-INF/views/common/header.jsp" %> </header>
<main>
<section class="content">

     <div class="nav-container">
    <a href="/selectBooks.do" class="nav-tab">도서 조회</a>
    <a href="/selectLoanBooks.do" class="nav-tab">대출 내역</a>
    <a href="/insertBookform.do" class="nav-tab">도서 등록</a>
  </div>
  

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
                  <button class="btn return"
                  onclick="openReturnModal(${loan.bookLoanDetailId}, ${loan.bookId})"
                  >반납</button>
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
	  <form id="returnForm" method="post" action="/updateReturnBook.do">
	  <div class="modal-content">
	    <p>정말 반납하시겠습니까?</p>
	    <input type="hidden" name="bookLoanDetailId" id="modalLoanId">
	    <input type="hidden" name="bookId" id="modalBookId">
	    <div class="modal-buttons">
	      <button type="submit" id="confirmReturn" class="btn"
	      onclick="submitReturn()"
	      >확인</button>
	      <button id="cancelReturn" type="button" class="btn"
	      onclick="closeModal()"
	      >취소</button>
	    </div>
	  </div>
	  </form>
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



</section>
</main>
<footer> <%@ include file="/WEB-INF/views/common/footer.jsp" %> </footer>
</body>

<script>

function openReturnModal(loanId, bookId) {
	  document.getElementById("modalLoanId").value = loanId;
	  document.getElementById("modalBookId").value = bookId;
	  document.getElementById("returnModal").style.display = "flex";
	}
	

function closeModal() {
  document.getElementById("returnModal").style.display = "none";
}


function submitReturn() {
	 document.getElementById("returnForm").submit();
}


document.addEventListener("DOMContentLoaded", function () {
  const returnModal = document.getElementById("returnModal");
  const extendModal = document.getElementById("extendModal");

  // 버튼에 정확히 클릭 이벤트만 걸기 (아이콘 영역 문제 방지)
  document.querySelectorAll(".btn.return").forEach(btn => {
    btn.addEventListener("click", (e) => {
      if (e.currentTarget === btn) {
        extendModal.style.display = "none";
        returnModal.style.display = "flex";
      }
    });
  });

  document.querySelectorAll(".btn.extend").forEach(btn => {
    btn.addEventListener("click", (e) => {
      if (e.currentTarget === btn) {
        returnModal.style.display = "none";
        extendModal.style.display = "flex";
      }
    });
  });

  // 반납 모달 확인 / 취소
  document.getElementById("confirmReturn").addEventListener("click", () => {
    submitReturn();
    alert("반납 처리되었습니다.");
  });
  document.getElementById("cancelReturn").addEventListener("click", () => {
	  closeModal();
  });

  // 연장 모달 확인 / 취소
  document.getElementById("confirmExtend").addEventListener("click", () => {
    extendModal.style.display = "none";
    alert("연장 요청이 완료되었습니다.");
  });
  document.getElementById("cancelExtend").addEventListener("click", () => {
    extendModal.style.display = "none";
  });

  // 바깥 클릭 시 모달 닫기
  window.addEventListener("click", (e) => {
    if (e.target === returnModal) returnModal.style.display = "none";
    if (e.target === extendModal) extendModal.style.display = "none";
  });
  
});


</script>




</html>