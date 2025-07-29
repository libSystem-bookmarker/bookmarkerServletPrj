<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>비밀번호 결과</title>
</head>
<body>
    <h2>비밀번호 조회 결과</h2>
    
    <c:if test="${not empty pw}">
        <p>찾은 비밀번호는 <strong>${pw}</strong> 입니다.</p>
    </c:if>
    
    <c:if test="${not empty errorMsg}">
        <p style="color:red;">${errorMsg}</p>
    </c:if>
    
    <a href="${pageContext.request.contextPath}/loginForm.do">로그인 페이지로</a>
</body>
</html>
