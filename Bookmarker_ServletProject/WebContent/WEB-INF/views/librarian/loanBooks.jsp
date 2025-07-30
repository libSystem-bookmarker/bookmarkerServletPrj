<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/css/common/common.css">
<link rel="stylesheet" href="/resources/css/librarian/loanBooks.css">
<meta charset="UTF-8">

<style>
    
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
<!--                   <button class="btn extend">연장</button>
 -->                </c:if>
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