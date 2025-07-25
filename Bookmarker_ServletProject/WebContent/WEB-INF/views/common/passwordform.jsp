<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>비밀번호 확인</title></head>
<body>
<h2>회원 탈퇴 - 비밀번호 확인</h2>
<form method="post" action="<c:url value='/member/delete.do'/>">
    비밀번호: <input type="password" name="password"><br>
    <input type="submit" value="탈퇴">
</form>
</body>
</html>