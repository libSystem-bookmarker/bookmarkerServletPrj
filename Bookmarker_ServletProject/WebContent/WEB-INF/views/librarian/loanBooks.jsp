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
        background-image: url('/img/Users.png'); /* 여기에 이미지 경로 */
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
      border: none;
      cursor: pointer;
    }

    .btn.return {
      background-color: #10b981;
      color: white;
      margin-right: 8px;
    }

    .btn.extend {
      background-color: #f9fafb;
      border: 1px solid #d1d5db;
      color: #111827;
      margin-right: 8px;
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

<%--               <td>
                <c:choose>
                  <c:when test="${loan.daysOverdue > 0}">
                    <span class="badge red">${loan.daysOverdue}일 연체</span>
                  </c:when>
                  <c:otherwise>
                    <span class="badge gray">정상</span>
                  </c:otherwise>
                </c:choose>
              </td> --%>

              <td>
                <c:choose>
                  <c:when test="${loan.isOverdue eq 'N'}">
                    <span class="badge black">대출중</span>
                  </c:when>
                  <c:when test="${loan.isOverdue eq 'Y'}">
                    <span class="badge gray">반납완료</span>
                  </c:when>
<%--                   <c:when test="${loan.isOverdue eq '연체'}">
                    <span class="badge red">연체</span>
                  </c:when> --%>
                </c:choose>
              </td>

<%--               <td>
                <c:if test="${loan.status ne '반납완료'}">
                  <button class="btn return">반납</button>
                  <button class="btn extend">연장</button>
                </c:if>
              </td> --%>
              
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>
