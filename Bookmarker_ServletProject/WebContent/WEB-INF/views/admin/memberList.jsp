<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>회원 목록</h1>
  <!-- 필터/검색 영역 -->
  <form class="memberfilterForm" action="memberInfo.do" method="get">
    <input type="hidden" name="tab" value="memberList" />

    <label for="role">역할:</label>
    <select name="role" id="role">
      <option value="">전체</option>
      <option value="admin" ${param.role == 'admin' ? 'selected' : ''}>관리자</option>
      <option value="librarian" ${param.role == 'librarian' ? 'selected' : ''}>사서</option>
      <option value="student" ${param.role == 'student' ? 'selected' : ''}>학생</option>
    </select>

<div class="buttons">
    <label for="searchKeyword">검색:</label>
    <input type="text"  name="searchKeyword" placeholder="이름, 학과명 등" value="${param.searchKeyword}" />
    
    <button type="submit" class="searchbtn">검색</button>
    <button type="button" class="deletebtn" onclick="deleteSelected()">삭제</button>
  </div>
  </form>

  <!-- 회원 목록 테이블 -->
  <div class="table-memberList">
  <table border="1" class="member-table">
    <thead>
      <tr>
        <th><input type="checkbox" id="selectAll" /></th>
        <th>아이디</th>
        <th>이름</th>
        <th>역할</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="member" items="${selectMembersList}">
        <tr>
          <td><input type="checkbox" name="selectedIds" value="${member.userId}" /></td>
          <td><a href="memberInfo.do?tab=selectMember&userId=${member.userId}">${member.userId}</a></td>
          <td>${member.name}</td>
          <td>${member.role}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  </div>