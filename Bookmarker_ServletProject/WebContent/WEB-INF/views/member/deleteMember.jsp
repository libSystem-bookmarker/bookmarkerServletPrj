<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>회원 삭제</title></head>
<body>
    <h2>회원 삭제</h2>
    <form method="post" action="<c:url value='/member/delete.do'/>">
        삭제할 회원 ID: <input type="text" name="userid" required>
        <br><br>
        <input type="submit" value="삭제하기">
    </form>
    <br>
    <a href="<c:url value='/member/selectAll.do'/>">회원 목록으로 돌아가기</a>
</body>
</html>