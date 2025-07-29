<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>비밀번호 찾기</title>
</head>
<body>
<header> <%@ include file="/WEB-INF/views/common/header.jsp" %> </header>
<main>
    <h2>비밀번호 찾기</h2>
    <form action="${pageContext.request.contextPath}/findpwresult.do" method="post">
        <label for="userId">아이디 입력:</label>
        <input type="text" id="userId" name="userId" required />
        <button type="submit">찾기</button>
    </form>
</main>
<footer> <%@ include file="/WEB-INF/views/common/footer.jsp" %> </footer>
</body>
</html>
