<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보 입력</h1>
<h2>회원 등록</h2>
<c:if test="${param.error eq 'fail'}">
    <p style="color:red;">회원 등록에 실패했습니다. 다시 시도해주세요.</p>
</c:if>
<c:url var = "member_do" value="/insertMember.do" />
<form action="${member_do}" method="post">
    <label for="role">역할 (학생/사서):</label>
    <select name="role" id="role" required>
        <option value="student">학생</option>
        <option value="librarian">사서</option>
    </select>

    <label for="name">이름:</label>
    <input type="text" id="name" name="name" required>

    <label for="phoneNumber">전화번호:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required>

    <label for="address">주소:</label>
    <input type="text" id="address" name="address" required>

    <label for="email">이메일:</label>
    <input type="email" id="email" name="email" required>

    <label for="unitId">소속 ID:</label>
    <input type="number" id="unitId" name="unitId">

    <input type="submit" value="회원 등록">
</form>

</body>
</html>