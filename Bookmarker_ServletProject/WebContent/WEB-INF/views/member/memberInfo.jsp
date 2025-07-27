<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.bookmark.myweb.model.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 상세 정보</title>
</head>
<body>
    <h2>회원 상세 정보</h2>
    <%
        MemberVO member = (MemberVO) request.getAttribute("member");
        if (member != null) {
    %>
        <ul>
            <li><strong>아이디:</strong> <%= member.getUserId() %></li>
            <li><strong>이름:</strong> <%= member.getName() %></li>
            <li><strong>이메일:</strong> <%= member.getEmail() %></li>
            <li><strong>전화번호:</strong> <%= member.getPhoneNumber() %></li>
            <li><strong>주소:</strong> <%= member.getAddress() %></li>
            <li><strong>역할:</strong> <%= member.getRole() %></li>
            <li><strong>학과번호:</strong> <%= member.getUnitId() %></li>
            <li><strong>등록일:</strong> <%= member.getCreatedAt() != null ? member.getCreatedAt() : "" %></li>
        </ul>
        <a href="MemberController?action=updateForm&userid=<%= member.getUserId() %>">수정</a> |
        <a href="MemberController?action=delete&userid=<%= member.getUserId() %>" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a> |
        <a href="MemberController?action=list">목록으로</a>
    <%
        } else {
    %>
        <p>회원 정보를 찾을 수 없습니다.</p>
    <%
        }
    %>
</body>
</html>

