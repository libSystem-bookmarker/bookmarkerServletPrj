<%@ page import="com.bookmark.myweb.model.MemberVO" %>
<%
    MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
    String role = loginMember.getRole();
%>


<h2>프로필 정보</h2>

<form action="updateMember.do" method="post">
    <label>이름: <input type="text" name="name" value="<%= loginMember.getName() %>"></label><br>
    <label>전화번호: <input type="text" name="phoneNumber" value="<%= loginMember.getPhoneNumber() %>"></label><br>
    <label>주소: <input type="text" name="address" value="<%= loginMember.getAddress() %>"></label><br>
    <label>이메일: <input type="text" name="email" value="<%= loginMember.getEmail() %>"></label><br>

    <% if ("admin".equals(role)) { %>
        <label>역할: <input type="text" name="role" value="<%= loginMember.getRole() %>" readonly></label><br>
        <label>소속 학과 (UNIT_ID): <input type="number" name="unitId" value="<%= loginMember.getUnitId() %>"></label><br>
    <% } else if ("student".equals(role)) { %>
        <label>학과: <input type="text" value="<%= loginMember.getUnitId() %>" readonly></label><br>
    <% } %>

    <button type="submit">수정하기</button>
</form>
