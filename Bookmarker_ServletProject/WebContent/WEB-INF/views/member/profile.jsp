<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<form action="updateMember.do" method="post" class="profile-form">
	<input type="hidden" name="userId" value="${loginMember.userId}">
<h2>${sessionScope.loginMember.name}의 프로필 정보</h2>

	<label>이름
		<input type="text" name="name" value="${loginMember.name}" readonly>
	</label><br>

	<label>전화번호
		<input type="text" name="phoneNumber" value="${loginMember.phoneNumber}">
	</label><br>

	<label>주소
		<input type="text" name="address" value="${loginMember.address}">
	</label><br>

	<label>이메일
		<input type="text" name="email" value="${loginMember.email}">
	</label><br>

	<label>
		<input type="text" name="role" value="${loginMember.role}" readonly>
	</label><br>

<c:if test="${not empty facultyName}">
    <label>소속 학부:
        <input type="text" value="${facultyName}" readonly>
    </label><br>
</c:if>

<c:if test="${not empty departmentName}">
    <label>소속 학과:
        <input type="text" value="${departmentName}" readonly>
    </label><br>
</c:if>


	<button class="submit" type="submit">수정</button>
	<button class="reset" type="reset">취소</button>
</form>
