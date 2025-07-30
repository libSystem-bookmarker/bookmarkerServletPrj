<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty facultyList}">
    <p style="color:red;">학부 리스트가 비어 있습니다. controller에서 전달되었는지 확인하세요.</p>
</c:if>

<c:if test="${param.error eq 'fail'}">
    <p style="color:red;">회원 등록에 실패했습니다. 다시 시도해주세요.</p>
</c:if>
<c:url var = "member_do" value="/insertMember.do" />
<form class="insertMemberForm" action="${member_do}" method="post">
<h1>회원 정보 입력</h1>
<label for="role">역할:</label>
<select name="role" id="role" onchange="toggleFacultySelector()">
	<option value="">-- 역할 선택 --</option>
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

    <div id="faculty-section" style="display: none;">
  <label for="faculty">학부:</label>
  <select id="faculty" name="facultyId" onchange="loadDepartments();">
    <option value="">학부를 선택하세요</option>
    <c:forEach var="faculty" items="${facultyList}">
      <option value="${faculty.unitId}">${faculty.unitName}</option>
    </c:forEach>
  </select>

  <label for="unitId">학과:</label>
  <select name="unitId" id="department">
    <option value="">학과를 선택하세요</option>
    <!-- 이후 JavaScript가 option을 동적으로 채움 -->
  </select>
</div>

    <input type="submit" value="회원 등록">
</form>