<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>회원 전체 목록</title>
</head>
<body>
<!-- select form -> role list -->
<form action="selectAllMember.do" method="get">
  <select name="role">
    <option value="">-- 역할 선택 --</option>
    <option value="librarian" <c:if test="${param.role == 'librarian'}">selected</c:if>>사서</option>
    <option value="student" <c:if test="${param.role == 'student'}">selected</c:if>>학생</option>
  </select>
  <button type="submit">필터</button>
</form>

    <h2>전체 회원 목록</h2>
    <table border="1">
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>역할</th>
            <th>전화번호</th>
            <th>주소</th>
            <th>이메일</th>
            <th>소속 부대</th>
            <th>가입일</th>
        </tr>
        <c:forEach var="member" items="${selectMembersList}">
            <tr>
                <td>${member.userId}</td>
                <td>${member.name}</td>
                <td>${member.role}</td>
                <td>${member.phoneNumber}</td>
                <td>${member.address}</td>
                <td>${member.email}</td>
                <td>${member.unitId}</td>
                <td>${member.createdAt}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
