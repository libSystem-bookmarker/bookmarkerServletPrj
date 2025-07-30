<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- admin/selectMember.jsp --%>
<h2>회원 상세 정보</h2>
<table border="1" cellpadding="8" cellspacing="0">
  <tr>
    <th>아이디</th>
    <td>${member.userId}</td>
  </tr>
  <tr>
    <th>이름</th>
    <td>${member.name}</td>
  </tr>
  <tr>
    <th>이메일</th>
    <td>${member.email}</td>
  </tr>
  <tr>
    <th>전화번호</th>
    <td>${member.phoneNumber}</td>
  </tr>
  <tr>
    <th>주소</th>
    <td>${member.address}</td>
  </tr>
  <tr>
    <th>역할</th>
    <td>${member.role}</td>
  </tr>
</table>

<br />

<!-- 수정 버튼: userId를 파라미터로 넘겨서 수정 폼으로 이동 -->
<form action="/updateMemberForm.do" method="get" style="display:inline;">

  <input type="hidden" name="userId" value="${member.userId}" />
  <input type="submit" value="수정" />
</form>

<!-- 삭제 버튼: userId를 파라미터로 넘겨서 삭제 처리 -->
<form action="deleteMember.do" method="post" style="display:inline;" onsubmit="return confirm('정말 삭제하시겠습니까?');">
  <input type="hidden" name="userId" value="${member.userId}" />
  <input type="submit" value="삭제" />
</form>